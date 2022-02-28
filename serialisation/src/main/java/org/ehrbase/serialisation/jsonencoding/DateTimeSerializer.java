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
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTemporal;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.temporal.ChronoField;

import static com.nedap.archie.datetime.DateTimeSerializerFormatters.ISO_8601_DATE;



/**
 * custom serializer for DvDateTime instance with dot delimiter instead of comma
 */
public class DateTimeSerializer extends JsonSerializer<DvDateTime> {

  static final DateTimeFormatter ISO_8601_TIME =
      (new DateTimeFormatterBuilder())
          .parseCaseInsensitive()
          .appendValue(ChronoField.HOUR_OF_DAY, 2)
          .optionalStart()
          .appendLiteral(':')
          .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
          .optionalStart()
          .appendLiteral(':')
          .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
          .optionalStart()
          .appendFraction(ChronoField.NANO_OF_SECOND, 3, 9, true)
          .optionalEnd()
          .optionalEnd()
          .optionalEnd()
          .optionalStart()
          .appendOffsetId()
          .optionalEnd()
          .toFormatter()
          .withDecimalStyle(DecimalStyle.STANDARD.withDecimalSeparator('.'));

  public static final DateTimeFormatter ISO_8601_DATE_TIME =
      new DateTimeFormatterBuilder()
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
    writeDvTemporal(jsonGenerator, dvDateTime, mapper);
    jsonGenerator.writeEndObject();
  }

  static void writeDvTemporal(
      JsonGenerator jsonGenerator, DvTemporal dvTemporal, ObjectMapper mapper) throws IOException {
    if (dvTemporal.getNormalStatus() != null) {
      jsonGenerator.writeFieldName("normal_status");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvTemporal.getNormalStatus()));
    }
    if (dvTemporal.getNormalRange() != null) {
      jsonGenerator.writeFieldName("normal_range");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvTemporal.getNormalRange()));
    }
    if (!CollectionUtils.isEmpty(dvTemporal.getOtherReferenceRanges())) {
      jsonGenerator.writeFieldName("other_reference_ranges");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvTemporal.getOtherReferenceRanges()));
    }
    if (dvTemporal.getMagnitudeStatus() != null) {
      jsonGenerator.writeFieldName("magnitude_status");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvTemporal.getMagnitudeStatus()));
    }
    if (dvTemporal.getNormalStatus() != null) {
      jsonGenerator.writeFieldName("accuracy");
      jsonGenerator.writeRawValue(mapper.writeValueAsString(dvTemporal.getAccuracy()));
    }
  }
}
