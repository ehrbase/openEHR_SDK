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

package org.ehrbase.client.classgenerator;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.collections4.SetUtils;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.util.rmconstants.RmConstants;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;
import org.ehrbase.webtemplate.util.WebTemplateUtils;

import java.util.*;
import java.util.stream.Collectors;

public class FlattFilter extends Filter {
  private static final Map<Class<?>, RmIntrospectConfig> configMap =
      ReflectionHelper.buildMap(RmIntrospectConfig.class);
  private ClassGeneratorConfig config;

  public FlattFilter(ClassGeneratorConfig config) {

    this.config = config;
  }

  @Override
  protected boolean skip(WebTemplateNode node, WebTemplate context, Deque<WebTemplateNode> deque) {
    WebTemplateNode parent = deque.peek();

    if (isTrivialNode(node, parent)) {
      return true;
    } else {
      if (parent != null) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
        Set<String> attributeNames =
            Optional.ofNullable(configMap.get(typeInfo.getJavaClass()))
                .map(RmIntrospectConfig::getNonTemplateFields)
                .orElse(Collections.emptySet())
                .stream()
                .map(s -> new SnakeCase(s).camelToSnake())
                .collect(Collectors.toSet());

        attributeNames.addAll(Set.of(
            "context",
            "timing",
            "expiry_time",
            "lower",
            "upper",
            "ism_transition",
            "location",
            "lower_included",
            "upper_included",
            "sample_count"
        ));

        deque.poll();
        if (!isTrivialNode(parent, deque.peek())
            && Locatable.class.isAssignableFrom(typeInfo.getJavaClass())) {
          attributeNames.add("feeder_audit");
        }
        deque.push(parent);

        if (config.isAddNullFlavor()) {
          attributeNames.add("null_flavour");
        }

        SetUtils.SetView<String> difference =
            SetUtils.difference(typeInfo.getAttributes().keySet(), attributeNames);
        if (difference.contains(node.getName())) {
          return true;
        }
      }
      return false;
    }
  }

  private boolean isTrivialNode(WebTemplateNode node, WebTemplateNode parent) {

    boolean commonConditions =
            //Only nodes with children need to be filtered
            !node.getChildren().isEmpty()
            && node.getMax() == 1
            && isSkippableInterval(parent, node)
            && (!isEvent(node) || isSkippableEvent(parent, node));

    if (!commonConditions) {
      return false;
    }

    switch (config.getOptimizerSetting()) {
      case ALL:
        return !node.getRmType().equals("COMPOSITION");
      case SECTION:
        return (!node.isArchetype() || node.getRmType().equals("SECTION"));
      default:
        return !node.isArchetype();
    }
  }

  private boolean isSkippableEvent(WebTemplateNode parent, WebTemplateNode node) {
    if (node.getRmType().equals(RmConstants.EVENT)
        && (config.isGenerateChoicesForSingleEvent() || node.isMulti())) {
      return false;
    }
    return !node.isMulti() && parent.getChildren().stream().filter(this::isEvent).count() == 1;
  }

  private boolean isSkippableInterval(WebTemplateNode parent, WebTemplateNode node) {

    if (parent != null
        && parent.getRmType().equals(RmConstants.ELEMENT)
        && node.getRmType().startsWith(RmConstants.DV_INTERVAL)) {
      return WebTemplateUtils.getTrueChildrenElement(parent).size() == 1;
    }
    return true;
  }

  @Override
  protected void preHandle(WebTemplateNode node) {

    if (node.getAqlPathDto().getLastNode().getName().equals("null_flavour")) {
      node.setName("null_flavour");
    }

    List<WebTemplateNode> ismTransitionList =
            node.getChildren().stream()
                    .filter(n -> RmConstants.ISM_TRANSITION.equals(n.getRmType()))
                    .collect(Collectors.toList());
    if (!ismTransitionList.isEmpty()) {
      node.getChildren().removeAll(ismTransitionList);
      node.getChildren().add(ismTransitionList.get(0));
    }
  }
}
