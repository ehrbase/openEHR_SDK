/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.serialisation.jsonencoding;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.nedap.archie.rm.datavalues.SingleValuedDataValue;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTemporal;
import java.io.IOException;
import org.apache.commons.collections4.CollectionUtils;

public abstract class AbstractDvTemporalSerializer<V, T extends DvTemporal<T, ?> & SingleValuedDataValue<V>>
        extends JsonSerializer<T> {

    protected abstract String typeName();

    protected abstract String format(V toFormat);

    @Override
    public void serialize(T temporal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        toJson(jsonGenerator, temporal);
    }

    @Override
    public void serializeWithType(
            T temporal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSer)
            throws IOException {
        toJson(jsonGenerator, temporal);
    }

    private void toJson(JsonGenerator jsonGenerator, T temporal) throws IOException {
        V value = temporal.getValue();

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("_type", typeName());
        if (value != null) {
            jsonGenerator.writeStringField("value", format(value));
        }
        writeDvTemporal(jsonGenerator, temporal, (ObjectMapper) jsonGenerator.getCodec());
        jsonGenerator.writeEndObject();
    }

    protected void writeDvTemporal(JsonGenerator jsonGenerator, DvTemporal<T, ?> dvTemporal, ObjectMapper mapper)
            throws IOException {
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
