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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.util.LinkedHashMap;
import java.util.Map;
import org.ehrbase.serialisation.exception.MarshalException;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.webtemplate.model.WebTemplate;

public class FlatJsonMarshaller {

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  public FlatJsonMarshaller() {}

  /**
   * Marshal the composition to flat json
   *
   * @param composition
   * @return
   */
  public String toFlatJson(Composition composition, WebTemplate webTemplate) {

    Map<String, Object> result = new LinkedHashMap<>();

    new StdFromCompositionWalker().walk(composition, result, webTemplate);

    try {
      return OBJECT_MAPPER.writeValueAsString(result);
    } catch (JsonProcessingException e) {
      throw new MarshalException(e.getMessage(), e);
    }
  }
}
