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
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.collections4.SetUtils;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;

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
        attributeNames.add("context");
        attributeNames.add("timing");
        attributeNames.add("expiry_time");
        attributeNames.add("lower");
        attributeNames.add("upper");
        attributeNames.add("ism_transition");
        attributeNames.add("location");
        attributeNames.add("lower_included");
        attributeNames.add("upper_included");
        attributeNames.add("sample_count");

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

    switch (config.getOptimizerSetting()) {
      case ALL:
        return !node.getChildren().isEmpty()
            && node.getMax() == 1
            && !node.getRmType().equals("COMPOSITION")
            && (!isEvent(node) || parent.getChildren().stream().filter(this::isEvent).count() == 1);
      case SECTION:
        return !node.getChildren().isEmpty()
            && node.getMax() == 1
            && (!node.isArchetype() || node.getRmType().equals("SECTION"))
            && (!isEvent(node) || parent.getChildren().stream().filter(this::isEvent).count() == 1);
      default:
        return !node.getChildren().isEmpty()
            && node.getMax() == 1
            && !node.isArchetype()
            && (!isEvent(node) || parent.getChildren().stream().filter(this::isEvent).count() == 1);
    }
  }

  private boolean isEvent(WebTemplateNode node) {
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
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

      node.getChildren().remove(merged);
    }
  }
}
