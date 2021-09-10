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

import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Entry;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.generic.PartyRelated;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor.UnmarshalPostprocessor;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.DefaultRMUnmarshaller;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.RMUnmarshaller;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.NodeId;
import org.ehrbase.serialisation.walker.ToCompositionWalker;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.InputHandler;

import java.util.*;
import java.util.stream.Collectors;

import static org.ehrbase.util.rmconstants.RmConstants.*;

public class StdToCompositionWalker extends ToCompositionWalker<Map<String, String>> {

  private static final Map<Class<?>, RMUnmarshaller> UNMARSHALLER_MAP =
      ReflectionHelper.buildMap(RMUnmarshaller.class);
  private static final Map<Class<?>, UnmarshalPostprocessor> POSTPROCESSOR_MAP =
      ReflectionHelper.buildMap(UnmarshalPostprocessor.class);

  private static InputHandler inputHandler = new InputHandler(Collections.emptyMap());

  private Set<String> consumedPaths;

  @Override
  public void walk(
      Composition composition,
      Map<String, String> object,
      WebTemplate webTemplate,
      DefaultValues defaultValues,
      String templateId
  ) {
    consumedPaths = new HashSet<>();
    super.walk(composition, object, webTemplate, defaultValues, templateId);
  }

  @Override
  protected Map<String, String> extract(
      Context<Map<String, String>> context,
      WebTemplateNode child,
      boolean isChoice,
      Integer count) {

    context.getNodeDeque().push(child);

    Integer oldCount = null;
    if (count != null) {
      oldCount = context.getCountMap().get(new NodeId(child));
      context.getCountMap().put(new NodeId(child), count);
    }
    String pathWithoutCount = context.getFlatHelper().buildNamePath(context, false);
    String path = context.getFlatHelper().buildNamePath(context, true);
    context.getNodeDeque().remove();
    context.getCountMap().remove(new NodeId(child));
    if (oldCount != null) {
      context.getCountMap().put(new NodeId(child), oldCount);
    }

    Map<String, String> subValues =
        context.getObjectDeque().peek().entrySet().stream()
            .filter(e -> e.getKey().startsWith(path))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    if (subValues.isEmpty()) {
      subValues =
          context.getObjectDeque().peek().entrySet().stream()
              .filter(e -> e.getKey().startsWith(pathWithoutCount))
              .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    if (isChoice && !isMatchingNode(subValues, context, child)) {
      subValues = Collections.emptyMap();
    }

    if (!subValues.isEmpty()) {
      return subValues;
    } else {
      return null;
    }
  }

  @Override
  protected ImmutablePair<Map<String, String>, RMObject> extractPair(
      Context<Map<String, String>> context,
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      WebTemplateNode childNode,
      Integer i) {

    if (CollectionUtils.isEmpty(childNode.getChildren())
        && context.getFlatHelper().skip(childNode, currentNode)) {
      return new ImmutablePair<>(null, null);
    }
    return super.extractPair(context, currentNode, choices, childNode, i);
  }

  private boolean isMatchingNode(
      Map<String, String> subValues, Context<Map<String, String>> context, WebTemplateNode child) {

    if (child.getRmType().equals("POINT_EVENT")) {
      return subValues.entrySet().stream().allMatch((e -> !e.getKey().endsWith("width")));
    } else if (child.getRmType().equals("INTERVAL_EVENT")) {
      return subValues.entrySet().stream().anyMatch((e -> e.getKey().endsWith("width")));
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
      return subValues.entrySet().stream().anyMatch(e -> e.getKey().endsWith("code"));
    } else if (child.getRmType().equals(DV_TEXT)) {
      return subValues.entrySet().stream().allMatch((e -> !e.getKey().endsWith("code")));
    } else {
      // End Nodes which are Choice always have unique flat paths
      return true;
    }
  }

  @Override
  protected void preHandle(Context<Map<String, String>> context) {

    // Handle if at a End-Node
    if (!visitChildren(context.getNodeDeque().peek()) && !context.getFlatHelper().skip(context)) {
      RMUnmarshaller rmUnmarshaller =
          UNMARSHALLER_MAP.getOrDefault(
              context.getRmObjectDeque().peek().getClass(), new DefaultRMUnmarshaller());
      String namePath = getNamePath(context);
      rmUnmarshaller.handle(
          namePath, context.getRmObjectDeque().peek(), context.getObjectDeque().peek(), context);
      consumedPaths.addAll(rmUnmarshaller.getConsumedPaths());
    }
  }

  @Override
  protected void postHandle(Context<Map<String, String>> context) {

    super.postHandle(context);

    List<UnmarshalPostprocessor<? super RMObject>> postprocessor = new ArrayList<>();

    Class<?> currentClass = context.getRmObjectDeque().peek().getClass();

    while (currentClass != null) {
      if (POSTPROCESSOR_MAP.containsKey(currentClass)) {
        postprocessor.add(POSTPROCESSOR_MAP.get(currentClass));
      }

      currentClass = currentClass.getSuperclass();
    }
    String namePath = getNamePath(context);

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
                v -> {
                  ((PartyRelated) ((Entry) context.getRmObjectDeque().peek()).getSubject())
                      .setRelationship(
                          new DvCodedText(
                              v.getLabel(),
                              new CodePhrase(new TerminologyId("openehr"), v.getValue())));
                });
      }
    }

    postprocessor.forEach(
        p -> {
          p.process(namePath, context.getRmObjectDeque().peek(), context.getObjectDeque().peek());
          consumedPaths.addAll(p.getConsumedPaths());
        });
  }

  private String getNamePath(Context<Map<String, String>> context) {
    String namePath = context.getFlatHelper().buildNamePath(context, true);
    String finalNamePath = namePath;
    if (context.getObjectDeque().peek().entrySet().stream()
        .noneMatch(e -> e.getKey().startsWith(finalNamePath))) {
      namePath = context.getFlatHelper().buildNamePath(context, false);
    }
    return namePath;
  }

  @Override
  protected void handleDVText(
      WebTemplateNode currentNode) {
    if (currentNode.getRmType().equals(ELEMENT)) {
      List<WebTemplateNode> trueChildren =
          currentNode.getChildren().stream()
              .filter(
                  n ->
                      !List.of("null_flavour", "feeder_audit").contains(n.getName())
                          || !n.isNullable())
              .collect(Collectors.toList());
      if (trueChildren.stream()
              .map(WebTemplateNode::getRmType)
              .collect(Collectors.toList())
              .containsAll(List.of(DV_TEXT, DV_CODED_TEXT))
          && currentNode.getChoicesInChildren().size() > 0
          && trueChildren.size() == 2) {
        handleDVTextInternal(currentNode);
      } else {
        super.handleDVText(currentNode);
      }
    } else {
      super.handleDVText(currentNode);
    }
  }

  public static void handleDVTextInternal(
      WebTemplateNode node

    ) {

    if (node.getRmType().equals(ELEMENT)) {
      List<WebTemplateNode> trueChildren =
          node.getChildren().stream()
              .filter(
                  n ->
                      !List.of("null_flavour", "feeder_audit").contains(n.getName())
                          || !n.isNullable())
              .collect(Collectors.toList());
      if (trueChildren.stream()
              .map(WebTemplateNode::getId)
              .collect(Collectors.toList())
              .containsAll(List.of("coded_text_value", "text_value"))
          && node.getChoicesInChildren().size() > 0
          && trueChildren.size() == 2) {
        WebTemplateNode merged = Filter.mergeDVText(node);

        node.getChildren()
            .removeIf(n -> List.of("coded_text_value", "text_value").contains(n.getId()));
        node.getChildren().add(merged);
      }
    }
  }

  @Override
  protected int calculateSize(Context<Map<String, String>> context, WebTemplateNode childNode) {

    Integer oldCount = context.getCountMap().get(new NodeId(childNode));
    context.getCountMap().remove(new NodeId((childNode)));
    context.getNodeDeque().push(childNode);
    String namePath = context.getFlatHelper().buildNamePath(context, true);

    // simple Elements
    if (childNode.getRmType().equals(ELEMENT) && context.getFlatHelper().skip(context)) {
      namePath = StringUtils.removeEnd( namePath,"/")+"/" + childNode.getId();
    }

    String finalNamePath = namePath;
    Integer count =
        context.getObjectDeque().peek().keySet().stream()
            .filter(s -> StringUtils.startsWith(s, finalNamePath))
            .map(s -> StringUtils.substringAfter(s, finalNamePath + ":"))
            .map(s -> StringUtils.substringBefore(s, "/"))
            .map(s -> StringUtils.substringBefore(s, "|"))
            .filter(StringUtils::isNotBlank)
            .map(Integer::parseInt)
            .sorted()
            .reduce((first, second) -> second)
            .map(i -> i + 1)
            .orElse(0);
    if (count == 0
        && context.getObjectDeque().peek().keySet().stream()
            .anyMatch(
                s ->
                    StringUtils.startsWith(
                        s, context.getFlatHelper().buildNamePath(context, false)))) {
      count = 1;
    }
    context.getNodeDeque().poll();
    if (oldCount != null) {
      context.getCountMap().put(new NodeId(childNode), oldCount);
    }
    return count;
  }

  public Set<String> getConsumedPaths() {
    return consumedPaths;
  }
}
