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

package org.ehrbase.serialisation.walker;

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.FilteredWebTemplate;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

public abstract class Walker<T> {

  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();
  public static final String DV_CODED_TEXT = "DV_CODED_TEXT";

  public void walk(
      Composition composition, T object, WebTemplate webTemplate, DefaultValues defaultValues) {
    Map<Pair<String, String>, Deque<WebTemplateNode>> filteredNodeMap = null;
    if (webTemplate instanceof FilteredWebTemplate) {

      filteredNodeMap = ((FilteredWebTemplate) webTemplate).filteredNodeMap;
    }
    walk(composition, object, webTemplate.getTree(), filteredNodeMap, defaultValues);
  }

  public void walk(RMObject composition, T object, WebTemplateNode root) {
    walk(composition, object, root, null, null);
  }

  public void walk(
      RMObject composition,
      T object,
      WebTemplateNode root,
      Map<Pair<String, String>, Deque<WebTemplateNode>> filteredNodeMap,
      DefaultValues defaultValues) {

    Context<T> context = new Context<>();

    context.getNodeDeque().push(root);
    context.getObjectDeque().push(object);
    context.getRmObjectDeque().push(composition);
    context.setFilteredNodeMap(filteredNodeMap);

    if (defaultValues != null) {
      context.setDefaultValues(defaultValues);
    } else {
      context.setDefaultValues(new DefaultValues());
    }

    handle(context);
  }

  private void handle(Context<T> context) {

    preHandle(context);
    WebTemplateNode currentNode = context.getNodeDeque().peek();
    if (visitChildren(currentNode)) {

      Map<String, List<WebTemplateNode>> choices = currentNode.getChoicesInChildren();
      List<WebTemplateNode> children = new ArrayList<>(currentNode.getChildren());

      // unwrap DV_CODED_TEXT
      for (WebTemplateNode codeNode : new ArrayList<>(children)) {
        if (codeNode.getRmType().equals(DV_CODED_TEXT)
            && codeNode.getInputs().stream()
                .map(WebTemplateInput::getSuffix)
                .anyMatch("other"::equals)) {
          WebTemplateNode textNode = new WebTemplateNode(codeNode);
          textNode.setRmType("DV_TEXT");
          choices.put(textNode.getAqlPath(), List.of(codeNode, textNode));
          children.add(textNode);
        }
      }

      // Add dummy DV_CODED_TEXT
      for (WebTemplateNode textNode : new ArrayList<>(children)) {
        if (textNode.getRmType().equals("DV_TEXT")
            && choices.values().stream().flatMap(List::stream).noneMatch(textNode::equals)) {
          WebTemplateNode codeNode = new WebTemplateNode(textNode);
          codeNode.setRmType(DV_CODED_TEXT);
          choices.put(codeNode.getAqlPath(), List.of(textNode, codeNode));
          children.add(codeNode);
        }
      }

      if (children.stream().anyMatch(n -> n.getRmType().equals("EVENT"))) {
        WebTemplateNode event =
            children.stream().filter(n -> n.getRmType().equals("EVENT")).findAny().orElseThrow();

        EventHelper eventHelper = new EventHelper(event).invoke();
        WebTemplateNode pointEvent = eventHelper.getPointEvent();
        WebTemplateNode intervalEvent = eventHelper.getIntervalEvent();
        choices.put(intervalEvent.getAqlPath(), List.of(intervalEvent, pointEvent));
        children.add(intervalEvent);
        children.add(pointEvent);
        children.remove(event);
      }

      Collection<List<WebTemplateNode>> childChoices =
          children.stream()
              .collect(Collectors.groupingBy(WebTemplateNode::getAqlPath))
              .entrySet()
              .stream()
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
              .values();

      for (List<WebTemplateNode> choces : childChoices) {

        if (choces.stream().noneMatch(WebTemplateNode::isMulti)) {

          for (WebTemplateNode childNode : choces) {
            ImmutablePair<T, RMObject> pair =
                extractPair(context, currentNode, choices, childNode, null);
            T childObject = pair.getLeft();
            RMObject child = pair.getRight();

            if (child != null && childObject != null) {
              context.getNodeDeque().push(childNode);
              context.getObjectDeque().push(childObject);
              context.getRmObjectDeque().push(child);
              handle(context);
            }
          }
        } else {
          int size = calculateSize(context, choces.get(0));

          List<Triple<T, RMObject, WebTemplateNode>> pairs = new ArrayList<>();
          for (int i = 0; i < size; i++) {
            for (WebTemplateNode childNode : choces) {
              ImmutablePair<T, RMObject> pair =
                  extractPair(context, currentNode, choices, childNode, i);
              if (pair.getLeft() != null && pair.getRight() != null) {
                pairs.add(new ImmutableTriple<>(pair.getLeft(), pair.getRight(), childNode));
              }
            }
          }

          for (int i = 0; i < Math.min(size, pairs.size()); i++) {
            RMObject currentChild = null;
            T childObject = null;
            childObject = pairs.get(i).getLeft();
            currentChild = pairs.get(i).getMiddle();
            WebTemplateNode childNode = pairs.get(i).getRight();
            if (currentChild != null && childObject != null) {
              context.getNodeDeque().push(childNode);
              context.getObjectDeque().push(childObject);
              context.getRmObjectDeque().push(currentChild);
              context.getCountMap().put(childNode, i);
              handle(context);
            }
          }
        }
      }
    }
    postHandle(context);
    insertDefaults(context);
    context.getRmObjectDeque().remove();
    context.getNodeDeque().remove();
    context.getObjectDeque().remove();
  }

  protected abstract ImmutablePair<T, RMObject> extractPair(
      Context<T> context,
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      WebTemplateNode childNode,
      Integer i);

  protected Object extractRMChild(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice,
      Integer count,
      Deque<WebTemplateNode> skippedNodes) {

    RMObject incrementalRm = currentRM;
    WebTemplateNode incrementalNode = currentNode;

    if (skippedNodes != null) {
      for (Iterator<WebTemplateNode> it = skippedNodes.descendingIterator(); it.hasNext(); ) {
        WebTemplateNode node = it.next();
        if (incrementalRm != null) {
          Object incrementalchild =
              extractRMChild(incrementalRm, incrementalNode, node, false, null);
          if (incrementalchild instanceof List) {
            if (((List<?>) incrementalchild).isEmpty()) {
              incrementalRm = null;
            } else {
              incrementalRm = (RMObject) ((List<?>) incrementalchild).get(0);
            }
          } else {
            incrementalRm = (RMObject) incrementalchild;
          }
        }
        incrementalNode = node;
      }
    }

    Object child;
    if (incrementalRm != null) {
      child = extractRMChild(incrementalRm, incrementalNode, childNode, isChoice, count);
    } else {
      child = null;
    }

    return child;
  }

  protected abstract Object extractRMChild(
      RMObject currentRM,
      WebTemplateNode currentNode,
      WebTemplateNode childNode,
      boolean isChoice,
      Integer count);

  protected boolean visitChildren(WebTemplateNode node) {
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null
        && (Locatable.class.isAssignableFrom(typeInfo.getJavaClass())
            || EventContext.class.isAssignableFrom(typeInfo.getJavaClass())
            || DvInterval.class.isAssignableFrom(typeInfo.getJavaClass())
            || IsmTransition.class.isAssignableFrom(typeInfo.getJavaClass()));
  }

  protected abstract T extract(
      Context<T> context, WebTemplateNode child, boolean isChoice, Integer i);

  protected abstract void preHandle(Context<T> context);

  protected abstract void postHandle(Context<T> context);

  protected void insertDefaults(Context<T> context) {}

  protected abstract int calculateSize(Context<T> context, WebTemplateNode childNode);

  protected RMObject deepClone(RMObject rmObject) {
    if (rmObject == null) {
      return null;
    }
    CanonicalJson canonicalXML = new CanonicalJson();
    return canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
  }

  protected String buildNamePath(Context<T> context, boolean addCount) {
    StringBuilder sb = new StringBuilder();
    for (Iterator<WebTemplateNode> iterator = context.getNodeDeque().descendingIterator();
        iterator.hasNext(); ) {
      WebTemplateNode node = iterator.next();
      sb.append(node.getId());
      if (node.getMax() != 1 && context.getCountMap().containsKey(node) && (addCount ||  context.getCountMap().get(node) != 0)) {
        sb.append(":").append(context.getCountMap().get(node));
      }
      if (iterator.hasNext()) {
        sb.append("/");
      }
    }
    return sb.toString();
  }

  public static class EventHelper {
    private WebTemplateNode event;
    private WebTemplateNode pointEvent;
    private WebTemplateNode intervalEvent;

    public EventHelper(WebTemplateNode event) {
      this.event = event;
    }

    public WebTemplateNode getPointEvent() {
      return pointEvent;
    }

    public WebTemplateNode getIntervalEvent() {
      return intervalEvent;
    }

    public EventHelper invoke() {
      pointEvent = new WebTemplateNode(event);
      intervalEvent = new WebTemplateNode(event);
      pointEvent.setRmType("POINT_EVENT");
      intervalEvent.setRmType("INTERVAL_EVENT");

      WebTemplateNode width = new WebTemplateNode();
      width.setId("width");
      width.setName("width");
      width.setRmType("DV_DURATION");
      width.setMax(1);
      width.setAqlPath(event.getAqlPath() + "/width");
      intervalEvent.getChildren().add(width);

      WebTemplateNode math = new WebTemplateNode();
      math.setId("math_function");
      math.setName("math_function");
      math.setRmType(DV_CODED_TEXT);
      math.setMax(1);
      math.setAqlPath(event.getAqlPath() + "/math_function");
      intervalEvent.getChildren().add(math);

      WebTemplateNode sampleCount = new WebTemplateNode();
      sampleCount.setId("sample_count");
      sampleCount.setName("sample_count");
      sampleCount.setRmType("LONG");
      sampleCount.setMax(1);
      sampleCount.setAqlPath(event.getAqlPath() + "/sample_count");
      intervalEvent.getChildren().add(sampleCount);

      return this;
    }
  }
}
