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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeSerializationUtils;

/**
 * @author Stefan Spiska
 */
public class TemporalPrimitive extends StringPrimitive {

    private TemporalAccessor temporal;

    public TemporalPrimitive() {}

    public TemporalPrimitive(String value) {
        setValue(value);
    }

    @JsonProperty
    @Override
    public void setValue(String value) {
        this.temporal = parseTemporal(value);
        super.setValue(value);
    }

    static TemporalAccessor parseTemporal(String value) {
        if (value == null) {
            return null;
        }
        if (StringUtils.containsAny(value, 'T', '-')) {
            return OpenEHRDateTimeParseUtils.parseDateTime(value);
        }
        if (StringUtils.containsAny(value, ':', '.', '+', '-', 'Z') || value.length() == 6) {
            return OpenEHRDateTimeParseUtils.parseTime(value);
        }
        return OpenEHRDateTimeParseUtils.parseDateTime(value);
    }

    @JsonIgnore
    public TemporalAccessor getTemporal() {
        return temporal;
    }

    public void setTemporal(TemporalAccessor temporal) {
        this.temporal = temporal;
        if (temporal == null) {
            setValue(null);
        } else {
            setValue(
                    temporal.isSupported(ChronoField.YEAR)
                            ? OpenEHRDateTimeSerializationUtils.formatDateTime(temporal)
                            : OpenEHRDateTimeSerializationUtils.formatTime(temporal));
        }
    }

    public TemporalPrimitive immutable() {
        return (TemporalPrimitive) super.immutable();
    }
}
