/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.model;

import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/**
 * Parsed form of a C_DATE, C_TIME or C_DATE_TIME pattern: the validity of each field the pattern mentions.
 * <p>Created by {@link org.ehrbase.openehr.sdk.webtemplate.parser.TemporalPatternParser} when the template is parsed
 * and kept on {@link WebTemplateValidation} next to the pattern string; enforced by the validation module.</p>
 * <p>Note: archie keeps the pattern but does not evaluate it, see
 * {@code com.nedap.archie.aom.primitives.CTemporal#isValidValue}.</p>
 */
public record TemporalPattern(Map<TemporalPatternField, TemporalPatternValidity> validities) {

    public TemporalPattern {
        Map<TemporalPatternField, TemporalPatternValidity> copy = new EnumMap<>(TemporalPatternField.class);
        // Map.copyOf rejects null keys and values, the EnumMap keeps the field order
        copy.putAll(Map.copyOf(validities));
        validities = Collections.unmodifiableMap(copy);
    }

    // Maps the pattern fields to their ChronoField
    public enum TemporalPatternField {
        MONTH(ChronoField.MONTH_OF_YEAR),
        DAY(ChronoField.DAY_OF_MONTH),
        HOUR(ChronoField.HOUR_OF_DAY),
        MINUTE(ChronoField.MINUTE_OF_HOUR),
        SECOND(ChronoField.SECOND_OF_MINUTE),
        OFFSET(ChronoField.OFFSET_SECONDS); // a timezone in the pattern makes the offset mandatory

        private final ChronoField chronoField;

        TemporalPatternField(ChronoField chronoField) {
            this.chronoField = chronoField;
        }

        public ChronoField chronoField() {
            return chronoField;
        }
    }

    // Field validity, with added offset-only entries for timezones
    public enum TemporalPatternValidity {
        MANDATORY,
        OPTIONAL,
        PROHIBITED,
        // offset only: Z requires a zero offset, ±hh a whole number of hours
        MANDATORY_UTC,
        MANDATORY_HOURS
    }
}
