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
import java.time.temporal.TemporalAccessor;
import java.util.regex.Pattern;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.ehrbase.openehr.sdk.util.OpenEhrTemporal;
import org.ehrbase.openehr.sdk.util.RegexUtils;

/**
 * @author Stefan Spiska
 */
public final class TemporalPrimitive extends StringPrimitive {
    private static final Pattern TIME_REGEX;

    static {
        String HOUR = RegexUtils.nonCapturing(RegexUtils.or("[01][0-9]", "[2][0-3]"));
        String MINUTE = "[0-5][0-9]";
        String SECOND = MINUTE;
        String TIME_SHORT = HOUR + MINUTE + SECOND;
        String TIME_LONG = HOUR + ':' + MINUTE + ':' + SECOND;
        String TIMEZONE =
                RegexUtils.or("Z", RegexUtils.nonCapturing("[-+]", HOUR, RegexUtils.optional("[:]?" + MINUTE)));
        String MILLISECOND = ".[0-9][0-9][0-9]";
        TIME_REGEX = Pattern.compile("^" + RegexUtils.or(TIME_SHORT, TIME_LONG) + RegexUtils.optional(MILLISECOND)
                + RegexUtils.optional(TIMEZONE) + "$");
    }

    private TemporalAccessor temporal;

    public TemporalPrimitive() {}

    public static StringPrimitive fromString(String value) {
        TemporalAccessor temporal;
        try {
            temporal = parseTemporal(value);
        } catch (IllegalArgumentException e) {
            return new StringPrimitive(value);
        }

        TemporalPrimitive temporalPrimitive = new TemporalPrimitive();
        temporalPrimitive.setTemporal(temporal);

        return temporalPrimitive;
    }

    @JsonProperty
    @Override
    public void setValue(String value) {
        this.temporal = parseTemporal(value);
        super.setValue(this.temporal.toString());
    }

    static TemporalAccessor parseTemporal(String value) {
        if (value == null) {
            return null;
        }
        if (TIME_REGEX.matcher(value).matches()) {
            return OpenEHRDateTimeParseUtils.parseTime(value);
        }
        if (value.indexOf('T') >= 0) {
            return OpenEHRDateTimeParseUtils.parseDateTime(value);
        } else {
            return OpenEHRDateTimeParseUtils.parseDate(value);
        }
    }

    @JsonIgnore
    public TemporalAccessor getTemporal() {
        return temporal;
    }

    public void setTemporal(TemporalAccessor temporal) {
        if (temporal == null) {
            this.temporal = null;
            super.setValue(null);
        } else {
            this.temporal = new OpenEhrTemporal(temporal);
            super.setValue(this.temporal.toString());
        }
    }

    public TemporalPrimitive frozen() {
        return (TemporalPrimitive) super.frozen();
    }
}
