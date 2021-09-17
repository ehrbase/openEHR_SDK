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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractUnmarshalPostprocessor<T extends RMObject>
    implements UnmarshalPostprocessor<T> {

  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();


  private final Logger log = LoggerFactory.getLogger(getClass());



  /**
   * Sets the {@code consumer} to the value in {@code values} corresponding to {@code term} and
   * {@code propertyName}
   *
   * @param <S>
   * @param term
   * @param propertyName
   * @param values
   * @param consumer
   * @param clazz
   * @param consumedPaths
   */
  protected <S> void setValue(
          String term,
          String propertyName,
          Map<FlatPathDto, String> values,
          Consumer<S> consumer,
          Class<S> clazz, Set<String> consumedPaths) {
    String key = propertyName != null ? term + "|" + propertyName : term;
    Map.Entry<FlatPathDto, String> entry = FlatPathDto.get(values, key);
    String jasonValue = entry.getValue();
    if (StringUtils.isNotBlank(jasonValue)) {
      try {
        S value = OBJECT_MAPPER.readValue(jasonValue, clazz);
        consumer.accept(value);
        consumedPaths.add(entry.getKey().format());
      } catch (JsonProcessingException e) {
        log.error(e.getMessage());
      }
    } else {

    }
  }
}

