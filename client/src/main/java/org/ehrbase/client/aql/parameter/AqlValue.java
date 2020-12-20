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

package org.ehrbase.client.aql.parameter;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;

public class AqlValue {

  private final Object value;

  public AqlValue(Object value) {
    Objects.requireNonNull(value);
    this.value = value;
  }

  public String buildAql() {
    if (Long.class.isAssignableFrom(value.getClass())
        || Integer.class.isAssignableFrom(value.getClass())) {
      return value.toString();
    } else if (Double.class.isAssignableFrom(value.getClass())
        || Float.class.isAssignableFrom(value.getClass())) {
      return value.toString();
    }
    if (Boolean.class.isAssignableFrom(value.getClass())) {
      return value.toString();
    } else if (String.class.isAssignableFrom(value.getClass())
        || UUID.class.isAssignableFrom(value.getClass())) {
      return StringUtils.wrap(value.toString(), "'");
    } else if (TemporalAccessor.class.isAssignableFrom(value.getClass())) {
      String valueAsString = null;
      try {
        valueAsString = JacksonUtil.getObjectMapper().writeValueAsString(value);
      } catch (JsonProcessingException e) {
        throw new ClientException(e.getMessage(), e);
      }
      return StringUtils.wrap(valueAsString.replace("\"", ""), "'");
    } else {
      throw new ClientException(String.format("%s is not an valid AQL Value", value.getClass()));
    }
  }
}
