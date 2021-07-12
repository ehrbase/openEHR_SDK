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

package org.ehrbase.serialisation.flatencoding.std.marshal;

import com.nedap.archie.rm.RMObject;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.DefaultStdConfig;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.StdConfig;
import org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor.MarshalPostprocessor;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FromCompositionWalker;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.model.WebTemplateNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.flatencoding.std.umarshal.StdToCompositionWalker.handleDVTextInternal;

public class StdFromCompositionWalker extends FromCompositionWalker<Map<String, Object>> {

  private static final Map<Class<? extends RMObject>, StdConfig> configMap =
      ReflectionHelper.buildMap(StdConfig.class);
  private static final Map<Class<? extends RMObject>, MarshalPostprocessor> POSTPROCESSOR_MAP =
      ReflectionHelper.buildMap(MarshalPostprocessor.class);
  public static final DefaultStdConfig DEFAULT_STD_CONFIG = new DefaultStdConfig();

  @Override
  protected Map<String, Object> extract(
      Context<Map<String, Object>> context, WebTemplateNode child, boolean isChoice, Integer i) {

    return context.getObjectDeque().peek();
  }

  @Override
  protected void preHandle(Context<Map<String, Object>> context) {

    // Handle if at a End-Node
    WebTemplateNode node = context.getNodeDeque().poll();

    WebTemplateNode parent = context.getNodeDeque().peek();
    context.getNodeDeque().push(node);
    if (!visitChildren(node) && !flatHelper.skip(node, parent)) {
      RMObject currentObject = context.getRmObjectDeque().peek();

      StdConfig stdConfig = configMap.getOrDefault(currentObject.getClass(), DEFAULT_STD_CONFIG);

      context
          .getObjectDeque()
          .peek()
          .putAll(
              stdConfig.buildChildValues(
                  flatHelper.buildNamePath(context, true), currentObject, context));
    }
  }

  @Override
  protected void postHandle(Context<Map<String, Object>> context) {
    List<MarshalPostprocessor<? super RMObject>> postprocessor = new ArrayList<>();

    Class<?> currentClass = context.getRmObjectDeque().peek().getClass();

    while (currentClass != null) {
      if (POSTPROCESSOR_MAP.containsKey(currentClass)) {
        postprocessor.add(POSTPROCESSOR_MAP.get(currentClass));
      }

      currentClass = currentClass.getSuperclass();
    }

    Collections.reverse(postprocessor);

    postprocessor.forEach(
        p ->
            p.process(
                flatHelper.buildNamePath(context, true),
                context.getRmObjectDeque().peek(),
                context.getObjectDeque().peek()));
  }

  @Override
  protected void handleDVText(
      WebTemplateNode currentNode,
      Map<String, List<WebTemplateNode>> choices,
      List<WebTemplateNode> children) {
    if (currentNode.getRmType().equals("ELEMENT")) {
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
              .containsAll(List.of("DV_TEXT", DV_CODED_TEXT))
          && currentNode.getChoicesInChildren().size() > 0
          && trueChildren.size() == 2) {
        handleDVTextInternal(currentNode, choices, children);
      } else {
        super.handleDVText(currentNode, choices, children);
      }
    } else {
      super.handleDVText(currentNode, choices, children);
    }
  }
}
