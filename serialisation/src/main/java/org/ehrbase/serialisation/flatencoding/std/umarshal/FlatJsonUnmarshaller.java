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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.building.webtemplateskeletnbuilder.WebTemplateSkeletonBuilder;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlatJsonUnmarshaller {

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  /**
   * Unmarshal flat Json to Composition
   *
   * @param flat the flat Json
   * @param introspect the introspect belonging to the template
   * @return
   */
  public Composition unmarshal(String flat, WebTemplate introspect) {

    Set<String> consumedPath;

    Map<String, String> currentValues;
    consumedPath = new HashSet<>();

    try {

      currentValues = new HashMap<>();
      for (Iterator<Map.Entry<String, JsonNode>> it = OBJECT_MAPPER.readTree(flat).fields();
          it.hasNext(); ) {
        Map.Entry<String, JsonNode> e = it.next();
        currentValues.put(e.getKey(), e.getValue().toString());
      }

      Composition generate = WebTemplateSkeletonBuilder.build(introspect, false);

      StdToCompositionWalker walker = new StdToCompositionWalker();
      DefaultValues defaultValues = new DefaultValues(currentValues);
      // put default for the defaults
      if (!defaultValues.containsDefaultValue(DefaultValuePath.TIME)) {
        defaultValues.addDefaultValue(DefaultValuePath.TIME, OffsetDateTime.now());
      }
      if (!defaultValues.containsDefaultValue(DefaultValuePath.SETTING)) {
        defaultValues.addDefaultValue(DefaultValuePath.SETTING, Setting.OTHER_CARE);
      }

      String templateId = generate.getArchetypeDetails().getTemplateId().getValue();
      walker.walk(
          generate,
          currentValues.entrySet().stream()
              .collect(Collectors.toMap(e1 -> new FlatPathDto(e1.getKey()), Map.Entry::getValue)),
          introspect,
          defaultValues,
          templateId);
      consumedPath = walker.getConsumedPaths();
      if (!CollectionUtils.isEmpty(getUnconsumed(consumedPath, currentValues))) {

        throw new UnmarshalException(
            String.format(
                "Could not consume Parts %s", getUnconsumed(consumedPath, currentValues)));
      }

      return generate;
    } catch (JsonProcessingException e) {
      throw new UnmarshalException(e.getMessage(), e);
    }
  }

  private static Set<String> getUnconsumed(
      Set<String> consumedPath, Map<String, String> currentValues) {
    if (currentValues != null && consumedPath != null) {
      HashSet<String> set = new HashSet<>(currentValues.keySet());
      set.removeAll(consumedPath);
      return set.stream().filter(p -> !p.startsWith("ctx")).collect(Collectors.toSet());
    } else {
      return Collections.emptySet();
    }
  }
}
