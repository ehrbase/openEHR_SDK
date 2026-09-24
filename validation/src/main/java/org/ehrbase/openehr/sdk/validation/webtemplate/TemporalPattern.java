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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Pattern used by C_DATE, C_TIME, C_DATE_TIME constraints.
 *  <p>Example shape: yyyy-mm-?? | letters mean mandatory fields; ?? means optional fields; XX means prohibited fields</p>
 *  <ul>
 *  <li>The year of a date, the hour of a time are always present.</li>
 *  <li>Date patterns constrain months and days</li>
 *  <li>Time patterns constrain minutes and seconds</li>
 *  <li>Date-time patterns constrain months, days, hours, minutes and seconds</li>
 *  <li>A timezone suffix on a time or date-time pattern requires an offset: Z a zero offset (the spec equates Z with
 *  +00:00), ±hh a whole number of hours, ±hh:mm and ±hhmm any offset. The text form of the offset is not checked.
 *  An offset needs a time part, so a required offset also makes the hour mandatory</li>
 *  <li>Values are checked by the fields they carry, so both compact (dashless) and extended forms are accepted</li>
 *  <li>Patterns are case-insensitive when matched</li>
 *  <li>Patterns that do not match any accepted format (date, time, datetime) are ignored and logged.</li>
 *  </ul>
 *  <p>Note: archie keeps the pattern but does not evaluate it, see
 *  {@code com.nedap.archie.aom.primitives.CTemporal#isValidValue}.</p>
 */
public final class TemporalPattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalPattern.class);

    private static final String MM = "(MM|\\?\\?|XX)";
    private static final String DD = "(DD|\\?\\?|XX)";
    private static final String HH = "(HH|\\?\\?|XX)";
    private static final String SS = "(SS|\\?\\?|XX)";

    // HH and MM here spell the offset suffix (+hh:mm), they are not the field tokens above; captured as the last group
    private static final String TIMEZONE = "(Z|[+-]HH(?::?MM)?)?";
    // the hours-only form of the suffix (+hh), which requires a whole-hours offset
    private static final Pattern TIMEZONE_HOUR_SUFFIX = Pattern.compile("[+-]HH", Pattern.CASE_INSENSITIVE);

    private static final Pattern DATE_TIME = Pattern.compile(
            "YYYY-" + MM + "-" + DD + "(?:T" + HH + ":" + MM + ":" + SS + TIMEZONE + ")?", Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME = Pattern.compile("HH:" + MM + ":" + SS + TIMEZONE, Pattern.CASE_INSENSITIVE);

    // Maps the pattern fields to their ChronoField
    private enum TemporalPatternField {
        MONTH(ChronoField.MONTH_OF_YEAR),
        DAY(ChronoField.DAY_OF_MONTH),
        HOUR(ChronoField.HOUR_OF_DAY),
        MINUTE(ChronoField.MINUTE_OF_HOUR),
        SECOND(ChronoField.SECOND_OF_MINUTE),
        // a timezone suffix in the pattern makes the offset mandatory
        OFFSET(ChronoField.OFFSET_SECONDS);

        private final ChronoField chronoField;

        TemporalPatternField(ChronoField chronoField) {
            this.chronoField = chronoField;
        }
    }

    private enum TemporalPatternValidity {
        MANDATORY,
        OPTIONAL,
        PROHIBITED,
        // offset only: Z requires a zero offset, ±hh a whole number of hours
        MANDATORY_UTC,
        MANDATORY_HOURS;

        static TemporalPatternValidity of(String input) {
            if ("??".equals(input)) {
                return OPTIONAL;
            }
            if ("XX".equalsIgnoreCase(input)) {
                return PROHIBITED;
            }
            if ("Z".equalsIgnoreCase(input)) {
                return MANDATORY_UTC;
            }
            return TIMEZONE_HOUR_SUFFIX.matcher(input).matches() ? MANDATORY_HOURS : MANDATORY;
        }
    }

    private static final List<TemporalPatternField> DATE_TIME_FIELDS = List.of(
            TemporalPatternField.MONTH,
            TemporalPatternField.DAY,
            TemporalPatternField.HOUR,
            TemporalPatternField.MINUTE,
            TemporalPatternField.SECOND,
            TemporalPatternField.OFFSET);
    private static final List<TemporalPatternField> TIME_FIELDS =
            List.of(TemporalPatternField.MINUTE, TemporalPatternField.SECOND, TemporalPatternField.OFFSET);

    private final Map<TemporalPatternField, TemporalPatternValidity> validities =
            new EnumMap<>(TemporalPatternField.class);

    private TemporalPattern(Matcher matcher, List<TemporalPatternField> fields) {
        for (int i = 0; i < fields.size(); i++) {
            String group = matcher.group(i + 1);
            // a null group is a part the pattern does not have: the time part of a date pattern, or the timezone suffix
            if (group != null) {
                validities.put(fields.get(i), TemporalPatternValidity.of(group));
            }
        }
    }

    // Validates the value against the input pattern (if any)
    // Returns the violation with the value and the pattern, or null when the value conforms or nothing is to check
    public static @Nullable ConstraintViolation validate(
            String aqlPath, TemporalAccessor value, WebTemplateInput input) {
        if (value == null || input == null || !WebTemplateValidationUtils.hasValidationPattern(input)) {
            return null;
        }

        String pattern = input.getValidation().getPattern();
        TemporalPattern temporalPattern = parse(pattern);
        // pattern does not match any accepted patterns
        if (temporalPattern == null) {
            LOGGER.debug("Ignoring unsupported date/time pattern '{}' at {}", pattern, aqlPath);
            return null;
        }
        if (temporalPattern.conforms(value)) {
            return null;
        }
        return new ConstraintViolation(aqlPath, "The value %s does not match the pattern %s".formatted(value, pattern));
    }

    // Parses the received pattern into a TemporalPattern, or null if not supported.
    // The first char decides the format: y for a date (with an optional time part), h for a time.
    private static TemporalPattern parse(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return null;
        }
        String normalized = pattern.strip();
        char first = Character.toUpperCase(normalized.charAt(0));

        if (first == 'Y') {
            Matcher dateTime = DATE_TIME.matcher(normalized);
            return dateTime.matches() ? new TemporalPattern(dateTime, DATE_TIME_FIELDS) : null;
        }
        if (first == 'H') {
            Matcher time = TIME.matcher(normalized);
            return time.matches() ? new TemporalPattern(time, TIME_FIELDS) : null;
        }
        return null;
    }

    // Checks the fields of the value against the validities of the temporal pattern.
    // A mandatory field must be present, a prohibited one must not; optional fields are not checked.
    private boolean conforms(TemporalAccessor value) {
        for (Map.Entry<TemporalPatternField, TemporalPatternValidity> entry : validities.entrySet()) {
            ChronoField field = entry.getKey().chronoField;
            boolean present = value.isSupported(field);
            boolean valid =
                    switch (entry.getValue()) {
                        case MANDATORY -> present;
                        case PROHIBITED -> !present;
                        case MANDATORY_UTC -> present && value.getLong(field) == 0;
                        case MANDATORY_HOURS -> present && value.getLong(field) % 3600 == 0;
                        case OPTIONAL -> true;
                    };
            if (!valid) {
                return false;
            }
        }
        return true;
    }
}
