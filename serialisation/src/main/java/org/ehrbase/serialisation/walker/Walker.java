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

import com.nedap.archie.openehrtestrm.Element;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.quantity.DvInterval;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.util.rmconstants.RmConstants.DV_CODED_TEXT;
import static org.ehrbase.util.rmconstants.RmConstants.DV_TEXT;

public abstract class Walker<T> {

  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();


  public void walk(
      Composition composition, T object, WebTemplate webTemplate, DefaultValues defaultValues) {

    walk(composition, object, webTemplate.getTree(), defaultValues);
  }

  public void walk(RMObject composition, T object, WebTemplateNode root) {
    walk(composition, object, root, null);
  }

  public void walk(
      RMObject composition, T object, WebTemplateNode root, DefaultValues defaultValues) {

    Context<T> context = new Context<>();

    context.getNodeDeque().push(root);
    context.getObjectDeque().push(object);
    context.getRmObjectDeque().push(composition);

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

      List<WebTemplateNode> ismTransitionList =
              currentNode.getChildren().stream()
                      .filter(n -> "ISM_TRANSITION".equals(n.getRmType()))
                      .collect(Collectors.toList());
      if (!ismTransitionList.isEmpty()) {
        currentNode.getChildren().removeAll(ismTransitionList);
        currentNode.getChildren().add(ismTransitionList.get(0));
      }


      Map<String, List<WebTemplateNode>> choices = currentNode.getChoicesInChildren();
      List<WebTemplateNode> children = new ArrayList<>(currentNode.getChildren());

      handleDVText(currentNode, choices, children);

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

      Map<String, List<WebTemplateNode>> map = new LinkedHashMap<>();
      for (WebTemplateNode webTemplateNode : children) {
        map.computeIfAbsent(webTemplateNode.getAqlPath(), k -> new ArrayList<>())
            .add(webTemplateNode);
      }
      Map<String, List<WebTemplateNode>> result = new LinkedHashMap<>();
      for (Map.Entry<String, List<WebTemplateNode>> stringListEntry : map.entrySet()) {
        if (result.put(stringListEntry.getKey(), stringListEntry.getValue()) != null) {
          throw new IllegalStateException("Duplicate key");
        }
      }
      Collection<List<WebTemplateNode>> childChoices = result.values();

      for (List<WebTemplateNode> choice : childChoices) {

        if (choice.stream().noneMatch(WebTemplateNode::isMulti)) {

          for (WebTemplateNode childNode : choice) {
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

          int size = calculateSize(context, choice.get(0));

          List<Triple<T, RMObject, WebTemplateNode>> pairs = new ArrayList<>();
          for (int i = 0; i < size; i++) {
            for (WebTemplateNode childNode : choice) {
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
              context.getCountMap().put(new NodeId(childNode), i);
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

  protected void handleDVText(
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      List<WebTemplateNode> children) {
    // unwrap DV_CODED_TEXT
    for (WebTemplateNode codeNode : new ArrayList<>(children)) {
      if (codeNode.getRmType().equals(DV_CODED_TEXT)
          && codeNode.getInputs().stream()
              .map(WebTemplateInput::getSuffix)
              .anyMatch("other"::equals)) {
        WebTemplateNode textNode = new WebTemplateNode(codeNode);
        textNode.setRmType(DV_TEXT);
        choices.put(textNode.getAqlPath(), List.of(codeNode, textNode));
        children.add(textNode);
      }
    }

    // Add dummy DV_CODED_TEXT
    for (WebTemplateNode textNode : new ArrayList<>(children)) {
      if (textNode.getRmType().equals(DV_TEXT)
          && choices.values().stream().flatMap(List::stream).noneMatch(textNode::equals)) {
        WebTemplateNode codeNode = new WebTemplateNode(textNode);
        codeNode.setRmType(DV_CODED_TEXT);
        choices.put(codeNode.getAqlPath(), List.of(textNode, codeNode));
        children.add(codeNode);
      }
    }
  }

  protected abstract ImmutablePair<T, RMObject> extractPair(
      Context<T> context,
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      WebTemplateNode childNode,
      Integer i);

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
            || IsmTransition.class.isAssignableFrom(typeInfo.getJavaClass())
            || Element.class.isAssignableFrom(typeInfo.getJavaClass()));
  }

  protected abstract T extract(
      Context<T> context, WebTemplateNode child, boolean isChoice, Integer i);

  protected abstract void preHandle(Context<T> context);

  protected abstract void postHandle(Context<T> context);

  protected void insertDefaults(Context<T> context) {}

  protected Object wrap(Object child) {
    if (child != null) {
      if (String.class.isAssignableFrom(child.getClass())) {
        child = new RmString((String) child);
      } else if (Long.class.isAssignableFrom(child.getClass())) {
        child = new RmLong((Long) child);
      }
      if (Boolean.class.isAssignableFrom(child.getClass())) {
        child = new RmBoolean((Boolean) child);
      }
    }
    return child;
  }

  protected abstract int calculateSize(Context<T> context, WebTemplateNode childNode);

  protected RMObject deepClone(RMObject rmObject) {
    if (rmObject == null) {
      return null;
    }
    CanonicalJson canonicalXML = new CanonicalJson();
    return canonicalXML.unmarshal(canonicalXML.marshal(rmObject), rmObject.getClass());
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
