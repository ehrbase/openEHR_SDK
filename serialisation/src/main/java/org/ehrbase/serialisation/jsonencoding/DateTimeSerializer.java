/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.jsonencoding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;

import static com.nedap.archie.datetime.DateTimeSerializerFormatters.ISO_8601_DATE;
import static com.nedap.archie.datetime.DateTimeSerializerFormatters.ISO_8601_TIME;

/**
 * custom serializer for DvDateTime instance with dot delimiter instead of comma
 */
public class DateTimeSerializer extends JsonSerializer<DvDateTime> {

    public static final DateTimeFormatter ISO_8601_DATE_TIME = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .append(ISO_8601_DATE)
            .appendLiteral('T')
            .append(ISO_8601_TIME)
            .toFormatter()
            .withDecimalStyle(DecimalStyle.STANDARD.withDecimalSeparator('.'));

    @Override
    public void serialize(DvDateTime dvDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        toJson(jsonGenerator, dvDateTime);
        }

  @Override
  public void serializeWithType(
      DvDateTime dvDateTime,
      JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider,
      TypeSerializer typeSer)
      throws IOException {
    toJson(jsonGenerator, dvDateTime);
  }

  private void toJson(JsonGenerator jsonGenerator, DvDateTime dvDateTime) throws IOException {

    ObjectMapper mapper = (ObjectMapper) jsonGenerator.getCodec();

    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("_type", "DV_DATE_TIME");
    jsonGenerator.writeStringField("value", ISO_8601_DATE_TIME.format(dvDateTime.getValue()));
    if (dvDateTime.getNormalStatus() != null) {
      jsonGenerator.writeFieldName("normal_status");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvDateTime.getNormalStatus()));
    }
    if (dvDateTime.getNormalRange() != null) {
      jsonGenerator.writeFieldName("normal_range");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvDateTime.getNormalRange()));
    }
    if (!CollectionUtils.isEmpty(dvDateTime.getOtherReferenceRanges())) {
      jsonGenerator.writeFieldName("other_reference_ranges");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvDateTime.getOtherReferenceRanges()));
    }
    if (dvDateTime.getMagnitudeStatus() != null) {
      jsonGenerator.writeFieldName("magnitude_status");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvDateTime.getMagnitudeStatus()));
    }
    if (dvDateTime.getNormalStatus() != null) {
      jsonGenerator.writeFieldName("accuracy");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvDateTime.getAccuracy()));
    }
    jsonGenerator.writeEndObject();
  }
}
