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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.ehrbase.building.OptSkeletonBuilder;
import org.ehrbase.normalizer.Normalizer;
import org.ehrbase.serialisation.exception.UnmarshalException;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class FlatJsonUnmarshaller {

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();
  public static final OptSkeletonBuilder OPT_SKELETON_BUILDER = new OptSkeletonBuilder();
  public static final Normalizer NORMALIZER = new Normalizer();

  private Set<String> consumedPath;

  private Map<String, String> currentValues;

  /**
   * Unmarshal flat Json to Composition
   *
   * @param flat the flat Json
   * @param introspect the introspect belonging to the template
   * @param operationalTemplate the template of the flat json
   * @return
   */
  public Composition unmarshal(
      String flat, WebTemplate introspect, OPERATIONALTEMPLATE operationalTemplate) {

    consumedPath = new HashSet<>();

    try {

      currentValues = new HashMap<>();
      for (Iterator<Map.Entry<String, JsonNode>> it = OBJECT_MAPPER.readTree(flat).fields();
          it.hasNext(); ) {
        Map.Entry<String, JsonNode> e = it.next();
        currentValues.put(e.getKey(), e.getValue().toString());
      }

      Composition generate = (Composition) OPT_SKELETON_BUILDER.generate(operationalTemplate);

      StdToCompositionWalker walker = new StdToCompositionWalker();
      walker.walk(generate, currentValues, introspect);
      consumedPath = walker.getConsumedPaths();

      return NORMALIZER.normalize(generate);
    } catch (JsonProcessingException e) {
      throw new UnmarshalException(e.getMessage(), e);
    }
  }

  public Set<String> getUnconsumed() {
    if (currentValues != null && consumedPath != null) {
      HashSet<String> set = new HashSet<>(currentValues.keySet());
      set.removeAll(consumedPath);
      return set;
    } else {
      return Collections.emptySet();
    }
  }
}
