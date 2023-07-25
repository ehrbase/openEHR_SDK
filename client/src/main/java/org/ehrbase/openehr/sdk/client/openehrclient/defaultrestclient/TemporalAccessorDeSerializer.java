/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;

public class TemporalAccessorDeSerializer extends StdDeserializer<TemporalAccessor> {

    public TemporalAccessorDeSerializer() {
        super(TemporalAccessor.class);
    }

    @Override
    public TemporalAccessor deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException {

        String value = jsonParser.getValueAsString();
        try {
            return OpenEHRDateTimeParseUtils.parseTime(value);
        } catch (RuntimeException e1) {
            try {
                return OpenEHRDateTimeParseUtils.parseDate(value);
            } catch (RuntimeException e2) {
                return OpenEHRDateTimeParseUtils.parseDateTime(value);
            }
        }
    }
}
