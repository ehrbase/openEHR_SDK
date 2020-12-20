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

package org.ehrbase.client.openehrclient.defaultrestclient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nedap.archie.datetime.DateTimeParsers;
import java.io.IOException;
import java.time.temporal.TemporalAccessor;

public class TemporalAccessorDeSerializer extends StdDeserializer<TemporalAccessor> {

  public TemporalAccessorDeSerializer() {
    super(TemporalAccessor.class);
  }

  @Override
  public TemporalAccessor deserialize(
      JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

    String value = jsonParser.getValueAsString();
    try {
      return DateTimeParsers.parseTimeValue(value);
    } catch (RuntimeException e1) {
      try {
        return DateTimeParsers.parseDateValue(value);
      } catch (RuntimeException e2) {
        return DateTimeParsers.parseDateTimeValue(value);
      }
    }
  }
}
