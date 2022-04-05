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
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.ehrbase.util.rmconstants.RmConstants.*;

public abstract class Walker<T> {

  public static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

  public void walk(
      Composition composition,
      T object,
      WebTemplate webTemplate,
      DefaultValues defaultValues,
      String templateId) {

    walk(composition, object, webTemplate.getTree(), defaultValues, templateId);
  }

  public void walk(RMObject composition, T object, WebTemplateNode root, String templateId) {
    walk(composition, object, root, null, templateId);
  }

  public void walk(
      RMObject composition,
      T object,
      WebTemplateNode root,
      DefaultValues defaultValues,
      String templateId) {

    Context<T> context = new Context<>();

    context.getNodeDeque().push(new WebTemplateNode(root));
    context.getObjectDeque().push(object);
    context.getRmObjectDeque().push(composition);
    context.setTemplateId(templateId);

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

      if (ACTION.equals(currentNode.getRmType())) {
        List<WebTemplateNode> ismTransitionList =
            currentNode.getChildren().stream()
                .filter(n -> ISM_TRANSITION.equals(n.getRmType()))
                .collect(Collectors.toList());
        if (!ismTransitionList.isEmpty()) {
          currentNode.getChildren().removeAll(ismTransitionList);
          currentNode.getChildren().add(ismTransitionList.get(0));
        }
      }
      handleInheritance(currentNode);

      Map<?, List<WebTemplateNode>> childrenByPath =  currentNode.getChildren().stream()
              .collect(Collectors.groupingBy(WebTemplateNode::getAqlPathDto, LinkedHashMap::new, Collectors.toList()));

      Map<String, List<WebTemplateNode>> allChoices = currentNode.getChoicesInChildren();

      for (List<WebTemplateNode> childrenForPath : childrenByPath.values()) {

        boolean isMulti = ! currentNode.getRmType().equals(ELEMENT)
                && childrenForPath.stream().anyMatch(WebTemplateNode::isMulti);

        Stream<NodeConstellation> childConstellations;
        if (!isMulti) {
          childConstellations = streamChildConstellations(context, currentNode, allChoices, childrenForPath, null);

        } else {
          //Number of entries to be added
          int size = calculateSize(context, childrenForPath.get(0));
          childConstellations = IntStream.range(0, size)
                  .mapToObj(Integer::valueOf)
                  .flatMap(index ->
                          streamChildConstellations(context, currentNode, allChoices, childrenForPath, index)
                          //for each index at most one of the choices is retained
                          .findFirst()
                          //an index may be skipped if none of the choices is accepted
                          .stream()
                  );
        }

        childConstellations.forEach(
                constellation -> {
                  WebTemplateNode childNode = constellation.getNode();
                  context.getNodeDeque().push(childNode);
                  context.getObjectDeque().push(constellation.getObject());
                  context.getRmObjectDeque().push(constellation.getRmObject());
                  if (constellation.getIndex() != null) {
                    context.getCountMap().put(new NodeId(childNode), constellation.getIndex());
                  }
                  handle(context);
                });
      }
    }
    postHandle(context);
    insertDefaults(context);
    context.getRmObjectDeque().remove();
    context.getNodeDeque().remove();
    context.getObjectDeque().remove();
  }

  private Stream<NodeConstellation> streamChildConstellations(Context<T> context, WebTemplateNode currentNode, Map<String, List<WebTemplateNode>> choices, List<WebTemplateNode> childrenForPath, Integer index) {
    return childrenForPath.stream()
            .map(childNode -> {
              var pair = extractPair(context, currentNode, choices, childNode, index);
              if (ObjectUtils.anyNull(pair.getLeft(), pair.getRight())) {
                return null;
              } else {
                return new NodeConstellation(index, pair.getLeft(), pair.getRight(), childNode);
              }
            })
            .filter(Objects::nonNull);
  }

  private final class NodeConstellation {
    private final Integer index;
    private final T object;
    private final RMObject rmObject;
    private final WebTemplateNode node;

    private NodeConstellation(Integer index, T object, RMObject rmObject, WebTemplateNode node) {
      this.index = index;
      this.object = object;
      this.rmObject = rmObject;
      this.node = node;
    }

    public Integer getIndex() {
      return index;
    }

    public T getObject() {
      return object;
    }

    public RMObject getRmObject() {
      return rmObject;
    }

    public WebTemplateNode getNode() {
      return node;
    }
  }

  /**
   * Add inheritance classes as explicit choices
   *
   * @param currentNode
   */
  protected void handleInheritance(WebTemplateNode currentNode) {

    for (WebTemplateNode childNode : new ArrayList<>(currentNode.getChildren())) {

      // Add explicit DV_CODED_TEXT
      if (childNode.getRmType().equals(DV_TEXT)
          && currentNode.getChildren().stream()
              .filter(n -> n.getAqlPath(true).equals(childNode.getAqlPath(true)))
              .noneMatch(n -> DV_CODED_TEXT.equals(n.getRmType()))) {

        currentNode.getChildren().add(buildCopy(childNode, DV_CODED_TEXT));
      }

      // Add explicit Party
      if (childNode.getRmType().equals(PARTY_PROXY)) {

        currentNode.getChildren().add(buildCopy(childNode, PARTY_SELF));
        currentNode.getChildren().add(buildCopy(childNode, PARTY_IDENTIFIED));

        WebTemplateNode party = buildCopy(childNode, PARTY_RELATED);
        WebTemplateNode relationship = new WebTemplateNode();
        relationship.setId("relationship");
        relationship.setName("relationship");
        relationship.setRmType(DV_CODED_TEXT);
        relationship.setMax(1);
        relationship.setMin(1);
        relationship.setAqlPath(party.getAqlPath() + "/relationship");
        party.getChildren().add(relationship);

        currentNode.getChildren().add(party);

        currentNode.getChildren().remove(childNode);
      }

      // Add explicit Party related
      if (childNode.getRmType().equals(PARTY_IDENTIFIED)) {

        WebTemplateNode party = buildCopy(childNode, PARTY_RELATED);
        WebTemplateNode relationship = new WebTemplateNode();
        relationship.setId("relationship");
        relationship.setName("relationship");
        relationship.setRmType(DV_CODED_TEXT);
        relationship.setMax(1);
        relationship.setMin(1);
        relationship.setAqlPath(party.getAqlPath() + "/relationship");
        party.getChildren().add(relationship);

        currentNode.getChildren().add(party);
      }

      // Add explicit Event
      if (childNode.getRmType().equals(EVENT)) {

        WebTemplateNode intervalEvent = buildCopy(childNode, INTERVAL_EVENT);

        WebTemplateNode width = new WebTemplateNode();
        width.setId("width");
        width.setName("width");
        width.setRmType("DV_DURATION");
        width.setMax(1);
        width.setMin(1);
        width.setAqlPath(intervalEvent.getAqlPath() + "/width");
        intervalEvent.getChildren().add(width);

        WebTemplateNode math = new WebTemplateNode();
        math.setId("math_function");
        math.setName("math_function");
        math.setRmType(DV_CODED_TEXT);
        math.setMax(1);
        math.setMin(1);
        math.setAqlPath(intervalEvent.getAqlPath() + "/math_function");
        intervalEvent.getChildren().add(math);

        WebTemplateNode sampleCount = new WebTemplateNode();
        sampleCount.setId("sample_count");
        sampleCount.setName("sample_count");
        sampleCount.setRmType("LONG");
        sampleCount.setMax(1);
        sampleCount.setAqlPath(intervalEvent.getAqlPath() + "/sample_count");
        intervalEvent.getChildren().add(sampleCount);

        currentNode.getChildren().add(intervalEvent);

        currentNode.getChildren().add(buildCopy(childNode, POINT_EVENT));

        currentNode.getChildren().remove(childNode);
      }
    }
  }

  private WebTemplateNode buildCopy(WebTemplateNode childNode, String rmType) {
    WebTemplateNode partyIdent = new WebTemplateNode(childNode);
    partyIdent.setRmType(rmType);
    return partyIdent;
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
      width.setMin(1);
      width.setAqlPath(event.getAqlPath() + "/width");
      intervalEvent.getChildren().add(width);

      WebTemplateNode math = new WebTemplateNode();
      math.setId("math_function");
      math.setName("math_function");
      math.setRmType(DV_CODED_TEXT);
      math.setMax(1);
      math.setMin(1);
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
