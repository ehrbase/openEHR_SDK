/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate.filter;

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;

public class Filter implements WebTemplateFilter {

  private static final Map<Class<?>, RmIntrospectConfig> configMap =
      ReflectionHelper.buildMap(RmIntrospectConfig.class);
  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  @Override
  public FilteredWebTemplate filter(WebTemplate webTemplate) {
    FilteredWebTemplate clone = new FilteredWebTemplate(webTemplate);
    Pair<List<WebTemplateNode>, Map<Pair<String, String>, Deque<WebTemplateNode>>> filter =
        filter(clone.getTree(), webTemplate, new ArrayDeque<>());
    clone.setTree(filter.getLeft().get(0));
    clone.setFilteredNodeMap(filter.getRight());

    return clone;
  }

  protected Pair<List<WebTemplateNode>, Map<Pair<String, String>, Deque<WebTemplateNode>>> filter(
      WebTemplateNode node, WebTemplate context, Deque<WebTemplateNode> deque) {

    WebTemplateNode oldNode = new WebTemplateNode(node);
    preHandle(node);
    List<WebTemplateNode> nodes;
    List<WebTemplateNode> filteredChildren = new ArrayList<>();
    Map<Pair<String, String>, Deque<WebTemplateNode>> nodeMap = new HashMap<>();
    deque.push(node);
    node.getChildren().stream()
        .map(n -> filter(n, context, deque))
        .forEach(
            p -> {
              filteredChildren.addAll(p.getLeft());
              nodeMap.putAll(p.getRight());
            });
    deque.poll();
    node.getChildren().clear();
    node.getChildren().addAll(filteredChildren);
    if (skip(node, context, deque)) {
      nodes = filteredChildren;
      for (WebTemplateNode child : filteredChildren) {
        nodeMap.get(new ImmutablePair<>(child.getAqlPath(), child.getRmType())).addLast(oldNode);
      }
    } else {
      nodes = Collections.singletonList(node);
      nodeMap.put(new ImmutablePair<>(node.getAqlPath(), node.getRmType()), new ArrayDeque<>());
    }
    OPTParser.makeIdUnique(node);
    return new ImmutablePair<>(nodes, nodeMap);
  }

  protected void preHandle(WebTemplateNode node) {

    List<WebTemplateNode> ismTransitionList =
        node.getChildren().stream()
            .filter(n -> "ISM_TRANSITION".equals(n.getRmType()))
            .collect(Collectors.toList());
    if (!ismTransitionList.isEmpty()) {
      node.getChildren().removeAll(ismTransitionList);
      node.getChildren().add(ismTransitionList.get(0));
    }

    if (node.getRmType().equals("ELEMENT")
        && node.getChildren().stream()
            .filter(n -> !List.of("null_flavour", "feeder_audit").contains(n.getName()))
            .map(WebTemplateNode::getRmType)
            .collect(Collectors.toList())
            .containsAll(List.of("DV_TEXT", "DV_CODED_TEXT"))) {
      WebTemplateNode merged = node.findChildById(node.getId(false)).orElseThrow();
      node.getChildren().clear();
      node.getChildren().add(merged);
    }
  }

  protected boolean skip(WebTemplateNode node, WebTemplate context, Deque<WebTemplateNode> deque) {
    WebTemplateNode parent = deque.peek();
    if (node.isArchetypeSlot()) {
      return true;
    }
    if (parent != null && isNonMandatoryRmAttribute(node, parent)) {
      return true;
    }

    if (List.of("HISTORY", "ITEM_TREE", "ITEM_LIST", "ITEM_SINGLE", "ITEM_TABLE", "ITEM_STRUCTURE")
        .contains(node.getRmType())) {
      return true;
    } else if (parent != null && isEvent(node)) {
      return parent.getChildren().stream().filter(this::isEvent).count() == 1 && node.getMax() == 1;
    } else if (node.getRmType().equals("ELEMENT")) {
      return node.getChildren().size() == 1;
    } else if (node.getRmType().equals("CODE_PHRASE") && parent != null) {
      return parent.getRmType().equals("DV_CODED_TEXT");
    }
    return false;
  }

  protected boolean isEvent(WebTemplateNode node) {
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
  }

  protected boolean isNonMandatoryRmAttribute(WebTemplateNode node, WebTemplateNode parent) {
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
    boolean nonMandatoryRmAttribute =
        typeInfo.getAttributes().containsKey(node.getName()) && node.getMin() == 0;
    boolean mandatoryNotInWebTemplate =
        List.of(
                "name",
                "archetype_node_id",
                "origin",
                "media_type",
                "upper_included",
                "lower_included",
                "upper_unbounded",
                "lower_unbounded")
            .contains(node.getName());
    boolean nonMandatoryInWebTemplate =
        typeInfo.getRmName().equals("ACTIVITY") && node.getName().equals("timing")
            || typeInfo.getRmName().equals("INSTRUCTION") && node.getName().equals("expiry_time")
            || typeInfo.getRmName().equals("ISM_TRANSITION") && node.getName().equals("transition");

    return (nonMandatoryRmAttribute || mandatoryNotInWebTemplate) && !nonMandatoryInWebTemplate;
  }
}
