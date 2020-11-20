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

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.apache.commons.collections4.SetUtils;
import org.ehrbase.serialisation.util.SnakeCase;
import org.ehrbase.util.reflection.ReflectionHelper;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.parser.config.RmIntrospectConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class FlattFilter extends Filter {
  private static final Map<Class<?>, RmIntrospectConfig> configMap = ReflectionHelper.buildMap(RmIntrospectConfig.class);

  @Override
  protected boolean skip(WebTemplateNode node, WebTemplate context, WebTemplateNode parent) {
    if (!node.getChildren().isEmpty() && node.getMax() == 1 && !node.isArchetype() && (  !isEvent(node) || parent.getChildren().stream().filter(this::isEvent).count() == 1)){
      return true;
    } else {
      if (parent != null) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
        Set<String> attributeNames = Optional.ofNullable(configMap.get(typeInfo.getJavaClass())).map(RmIntrospectConfig::getNonTemplateFields).orElse(Collections.emptySet()).stream().map(s -> new SnakeCase(s).camelToSnake()).collect(Collectors.toSet());
        attributeNames.add("context");
        attributeNames.add("timing");
        attributeNames.add("expiry_time");
        attributeNames.add("lower");
        attributeNames.add("upper");
        attributeNames.add("ism_transition");
        attributeNames.add("location");
        SetUtils.SetView<String> difference = SetUtils.difference(typeInfo.getAttributes().keySet(), attributeNames);
        if (difference.contains(node.getName())) {
          return true;
        }
      }
      return false;
    }
  }

  private boolean isEvent(WebTemplateNode node){
    RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
    return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
  }
}
