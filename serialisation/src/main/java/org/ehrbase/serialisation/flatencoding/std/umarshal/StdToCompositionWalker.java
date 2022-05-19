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

package org.ehrbase.serialisation.flatencoding.std.umarshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor.UnmarshalPostprocessor;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.NodeId;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;
import org.ehrbase.webtemplate.util.WebTemplateUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.util.rmconstants.RmConstants.*;

public class StdToCompositionWalker extends ToCompositionWalker<Map<FlatPathDto, String>> {

  private static final Map<Class<?>, RMUnmarshaller> UNMARSHALLER_MAP =
      ReflectionHelper.buildMap(RMUnmarshaller.class);
  private static final Map<Class<?>, UnmarshalPostprocessor> POSTPROCESSOR_MAP =
      ReflectionHelper.buildMap(UnmarshalPostprocessor.class);

  private Set<String> consumedPaths;

  @Override
  public void walk(
      Composition composition,
      Map<FlatPathDto, String> object,
      WebTemplate webTemplate,
      DefaultValues defaultValues,
      String templateId) {
    consumedPaths = new HashSet<>();
    if (defaultValues != null
        && BooleanUtils.isTrue(defaultValues.getDefaultValue(DefaultValuePath.COMPOSER_SELF))) {
      object.put(
          new FlatPathDto(webTemplate.getTree().getId() + "/composer|_type"),
          StringUtils.wrap(PARTY_SELF, '"'));
    }
    super.walk(composition, object, webTemplate, defaultValues, templateId);
  }

  @Override
  protected Map<FlatPathDto, String> extract(
      Context<Map<FlatPathDto, String>> context,
      WebTemplateNode child,
      boolean isChoice,
      Integer count) {

    context.getNodeDeque().push(child);

    Integer oldCount = null;
    if (count != null) {
      oldCount = context.getCountMap().get(new NodeId(child));
      context.getCountMap().put(new NodeId(child), count);
    }
    String path;
    path = buildNamePathWithElementHandling(context);
    context.getNodeDeque().remove();
    context.getCountMap().remove(new NodeId(child));

    if (oldCount != null) {
      context.getCountMap().put(new NodeId(child), oldCount);
    }

    Map<FlatPathDto, String> subValues =
        context.getObjectDeque().peek().entrySet().stream()
            .filter(e -> e.getKey().startsWith(path))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (isChoice && !isMatchingNode(subValues, context, child, new FlatPathDto(path))) {
      subValues = Collections.emptyMap();
    }

    if (!subValues.isEmpty()) {
      return subValues;
    } else {
      return null;
    }
  }

  public static <T> String buildNamePathWithElementHandling(Context<T> context) {
    String path;
    WebTemplateNode child = context.getNodeDeque().peek();

    // if skipped Element Path = path to Element value
    if (child.getRmType().equals(ELEMENT)
        && context.getFlatHelper().skip(context.getNodeDeque().peek(), child)) {

      WebTemplateNode valueNode =
          child.getChildren().stream()
              .filter(n -> n.getId().endsWith("value"))
              .findAny()
              .orElseThrow();

      context.getNodeDeque().push(valueNode);
      path = context.getFlatHelper().buildNamePath(context, true);
      context.getNodeDeque().remove();
    } else {

      path = context.getFlatHelper().buildNamePath(context, true);
    }
    return path;
  }

  @Override
  protected ImmutablePair<Map<FlatPathDto, String>, RMObject> extractPair(
      Context<Map<FlatPathDto, String>> context,
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      WebTemplateNode childNode,
      Integer i) {

    if (
      // Nodes with children need to be put on the stack even if there are skip since the might have
      // children. If there are empty there will be removed in ToCompositionWalker::normalise
        (CollectionUtils.isEmpty(childNode.getChildren())
            && context.getFlatHelper().skip(childNode, currentNode))
            //  NonMandatoryRmAttribute are handled in the UnmarshalPostprocessor
            || (currentNode != null
            && context.getFlatHelper().isNonMandatoryRmAttribute(childNode, currentNode))) {
      return new ImmutablePair<>(null, null);
    }
    return super.extractPair(context, currentNode, choices, childNode, i);
  }

  private boolean isMatchingNode(
      Map<FlatPathDto, String> subValues,
      Context<Map<FlatPathDto, String>> context,
      WebTemplateNode child,
      FlatPathDto currentFlatPath) {

    if (child.getRmType().equals(POINT_EVENT)) {
      return !FlatHelper.isExactlyIntervalEvent(subValues, currentFlatPath.format());
    } else if (child.getRmType().equals(INTERVAL_EVENT)) {
      return FlatHelper.isExactlyIntervalEvent(subValues, currentFlatPath.format());
    } else if (child.getRmType().equals(PARTY_SELF)) {
      return FlatHelper.isExactlyPartySelf(subValues, currentFlatPath.format(), child);
    } else if (child.getRmType().equals(PARTY_IDENTIFIED)) {
      return FlatHelper.isExactlyPartyIdentified(subValues, currentFlatPath.format(), child);
    } else if (child.getRmType().equals(PARTY_RELATED)) {
      return FlatHelper.isExactlyPartyRelated(subValues, currentFlatPath.format(), child);
    } else if (visitChildren(child)) {
      for (WebTemplateNode n : child.getChildren()) {
        context.getNodeDeque().push(n);
        String path = context.getFlatHelper().buildNamePath(context, true);
        context.getNodeDeque().remove();
        subValues =
            subValues.entrySet().stream()
                .filter(e -> !e.getKey().startsWith(path))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      }

      return subValues.isEmpty();
    } else if (child.getRmType().equals(DV_CODED_TEXT)) {
      return FlatHelper.isExactlyDvCodedText(subValues, currentFlatPath.format());
    } else if (child.getRmType().equals(DV_TEXT)) {
      return !FlatHelper.isExactlyDvCodedText(subValues, currentFlatPath.format());
    } else {
      // End Nodes which are Choice always have unique flat paths
      return true;
    }
  }

  @Override
  protected void preHandle(Context<Map<FlatPathDto, String>> context) {

    // Handle if at an End-Node
    if (!isRaw(context)
        && !visitChildren(context.getNodeDeque().peek())
        && !context.getFlatHelper().skip(context)) {

      if (context.getRmObjectDeque().peek().getClass().isAssignableFrom(DvCodedText.class)
          && context.getObjectDeque().peek().keySet().stream()
          .anyMatch(k -> "other".equals(k.getLast().getAttributeName()))) {
        replaceRmObject(context, new DvText());
      }

      RMUnmarshaller rmUnmarshaller =
          findRMUnmarshaller(context.getRmObjectDeque().peek().getClass());
      String namePath = buildNamePathWithElementHandling(context);

      rmUnmarshaller.handle(
          namePath,
          context.getRmObjectDeque().peek(),
          context.getObjectDeque().peek(),
          context,
          consumedPaths);
    }
  }

  public static <T extends RMObject> RMUnmarshaller<T> findRMUnmarshaller(Class<T> aClass) {
    return UNMARSHALLER_MAP.getOrDefault(aClass, new DefaultRMUnmarshaller());
  }

  private void handleRaw(Context<Map<FlatPathDto, String>> context) {
    ObjectMapper om = ArchieObjectMapperProvider.getObjectMapper();
    try {
      Map.Entry<FlatPathDto, String> current =
          context.getObjectDeque().peek().entrySet().stream().findAny().orElseThrow();

      RMObject newRmObject =
          new CanonicalJson()
              .unmarshal(
                  om.readValue(current.getValue(), String.class)
                      // In case better RAW encoding is used instead of canonical
                      .replace("\"@class\"", "\"_type\""),
                  RMObject.class);

      // Replace old skeleton
      replaceRmObject(context, newRmObject);
      consumedPaths.add(current.getKey().format());

    } catch (JsonProcessingException e) {
      throw new UnmarshalException(e.getMessage());
    }
  }

  private void replaceRmObject(Context<Map<FlatPathDto, String>> context, RMObject newRmObject) {
    RMObject oldRM = context.getRmObjectDeque().poll();
    RMObject parentRM = context.getRmObjectDeque().peek();
    WebTemplateNode currentNode = context.getNodeDeque().poll();
    WebTemplateNode parentNode = context.getNodeDeque().peek();
    // since flat skips Elements we might need to keep it
    if (oldRM instanceof Element && !(newRmObject instanceof Element)) {
      WebTemplateNode valueNode =
          currentNode.getChildren().stream()
              .filter(n -> n.getId().contains("value"))
              .findAny()
              .orElseThrow();
      WebTemplateSkeletonBuilder.insert(currentNode, oldRM, valueNode, newRmObject);
      context.getRmObjectDeque().push(oldRM);
    } else {
      WebTemplateSkeletonBuilder.remove(parentNode, parentRM, currentNode, oldRM);
      WebTemplateSkeletonBuilder.insert(parentNode, parentRM, currentNode, newRmObject);
      context.getRmObjectDeque().push(newRmObject);
    }
    context.getNodeDeque().push(currentNode);
  }

  private boolean isRaw(Context<Map<FlatPathDto, String>> context) {

    if (context.getObjectDeque().peek().size() != 1) {
      return false;
    }

    Map.Entry<FlatPathDto, String> current =
        context.getObjectDeque().peek().entrySet().stream().findAny().orElseThrow();
    return Objects.equals(current.getKey().getLast().getAttributeName(), "raw")
        // last flat path segment matches node_id ( starting '_' marks optional flat path )
        && Objects.equals(
        StringUtils.removeStart(context.getNodeDeque().peek().getId(false), "_"),
        StringUtils.removeStart(current.getKey().getLast().getName(), "_"));
  }

  @Override
  protected void postHandle(Context<Map<FlatPathDto, String>> context) {

    super.postHandle(context);

    if (isRaw(context)) {

      handleRaw(context);
    }

    WebTemplateNode currentNode = context.getNodeDeque().peek();

    currentNode
        .getChildren()
        .forEach(
            childNode -> {

              // Check for Raw in optional (skipped Nodes)
              if (context.getFlatHelper().skip(childNode, currentNode)) {

                context.getNodeDeque().push(childNode);
                context.getRmObjectDeque().push(new RMObject() {
                });

                String path = context.getFlatHelper().buildNamePath(context, true);
                Map<FlatPathDto, String> subValues =
                    context.getObjectDeque().peek().entrySet().stream()
                        .filter(e -> e.getKey().startsWith(path + "/_" + childNode.getId()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                context.getObjectDeque().push(subValues);
                if (isRaw(context)) {

                  handleRaw(context);
                }
                context.getNodeDeque().poll();
                context.getRmObjectDeque().poll();
                context.getObjectDeque().poll();
              }
            });

    List<? extends UnmarshalPostprocessor<? extends RMObject>> postprocessor =
        findUnmarshalPostprocessors(context.getRmObjectDeque().peek().getClass());
    String namePath = buildNamePathWithElementHandling(context);

    if (Entry.class.isAssignableFrom(context.getRmObjectDeque().peek().getClass())) {
      if (((Entry) context.getRmObjectDeque().peek()).getSubject() instanceof PartyRelated) {
        Optional.ofNullable(context.getNodeDeque().peek())
            .flatMap(c -> c.findChildById("subject"))
            .flatMap(c -> c.findChildById("relationship"))
            .stream()
            .map(WebTemplateNode::getInputs)
            .flatMap(List::stream)
            .filter(i -> "code".equals(i.getSuffix()))
            .map(WebTemplateInput::getList)
            .map(l -> l.size() == 1 ? l.get(0) : null)
            .filter(Objects::nonNull)
            .findAny()
            .ifPresent(
                v ->
                    ((PartyRelated) ((Entry) context.getRmObjectDeque().peek()).getSubject())
                        .setRelationship(
                            new DvCodedText(
                                v.getLabel(),
                                new CodePhrase(new TerminologyId("openehr"), v.getValue()))));
      }
    }

    postprocessor.forEach(
        p ->
            ((UnmarshalPostprocessor) p)
                .process(
                    namePath,
                    context.getRmObjectDeque().peek(),
                    context.getObjectDeque().peek(),
                    consumedPaths,
                    context));
  }

  public static <T extends RMObject> List<UnmarshalPostprocessor<T>> findUnmarshalPostprocessors(
      Class<T> aClass) {
    List<UnmarshalPostprocessor<T>> postprocessor = new ArrayList<>();

    Class<?> currentClass = aClass;

    while (currentClass != null) {
      if (POSTPROCESSOR_MAP.containsKey(currentClass)) {
        postprocessor.add(POSTPROCESSOR_MAP.get(currentClass));
      }

      currentClass = currentClass.getSuperclass();
    }
    return postprocessor;
  }

  @Override
  protected void handleInheritance(WebTemplateNode currentNode) {
    if (currentNode.getRmType().equals("ELEMENT")
        && WebTemplateUtils.isChoiceDvCodedTextAndDvText(currentNode)) {
      handleDVTextInternal(currentNode);
    } else {
      super.handleInheritance(currentNode);
    }
  }

  public static void handleDVTextInternal(WebTemplateNode node) {
    WebTemplateNode merged = Filter.mergeDVText(node);
    node.getChildren()
        .removeIf(
            childNode -> List.of("coded_text_value", "text_value").contains(childNode.getId()));
    node.getChildren().add(merged);
  }

  @Override
  protected int calculateSize(
      Context<Map<FlatPathDto, String>> context, WebTemplateNode childNode) {

    Integer oldCount = context.getCountMap().get(new NodeId(childNode));
    String namePath = context.getFlatHelper().buildNamePath(context, true);

    String finalNamePath = namePath;
    Integer count =
        context.getObjectDeque().peek().keySet().stream()
            .filter(s -> s.startsWith(finalNamePath))
            .map(s -> FlatPathDto.removeStart(s, new FlatPathDto(finalNamePath)))
            .filter(n -> n != null && n.getName().equals(childNode.getId()))
            .map(n -> Optional.ofNullable(n.getCount()).orElse(0))
            .sorted()
            .reduce((first, second) -> second)
            .map(i -> i + 1)
            .orElse(0);
    if (oldCount != null) {
      context.getCountMap().put(new NodeId(childNode), oldCount);
    }
    return count;
  }

  public Set<String> getConsumedPaths() {
    return consumedPaths;
  }
}
