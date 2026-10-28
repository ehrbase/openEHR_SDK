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

import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parsed form of a C_DATE, C_TIME or C_DATE_TIME pattern: the validity of each field the pattern mentions.
 * <p>Example: yyyy-mm-ddThh:??:XX, a date-time with mandatory date and hour, optional minutes and no seconds.</p>
 * <ul>
 *     <li>Mandatory fields are marked by letters, optional fields by ??, prohibited fields by XX.</li>
 *     <li>Patterns are case insensitive when matched.</li>
 *     <li>Date patterns constrain months and days, time patterns constrain minutes and seconds, and date-time
 *     patterns can constrain months, days, hours, minutes and seconds.</li>
 *     <li>The year of a date must be present. The hour of a time must be present.</li>
 *     <li>An optional field is followed only by optional or prohibited fields.</li>
 *     <li>A prohibited field is followed only by other prohibited fields.</li>
 *     <li>A timezone suffix on a time or date-time pattern requires an offset: Z a zero offset (the spec equates Z with
 *     +00:00), ±hh a whole number of hours, ±hh:mm and ±hhmm any offset; the text form of the offset is not checked.
 *     The suffix is not subject to the ordering rules (HH:XX:XXZ is supported). An offset needs a time part, so
 *     with an optional hour only values with a time pass, and a prohibited hour is not supported
 *     (yyyy-mm-ddTXX:XX:XXZ).</li>
 *     <li>A pattern applies only to an input of its own kind: a date pattern to DATE, a time pattern to TIME, a
 *     date-time pattern to DATETIME. Parsing does not know the input; the OPTParser checks the kind with
 *     {@link #isForInputType(String)}.</li>
 *     <li>{@link #parse(String)} returns null for any other pattern.</li>
 * </ul>
 * <p>Created by {@link #parse} when the template is parsed and kept on {@link WebTemplateValidation} next to the
 * pattern string; enforced by the validation module.</p>
 * <p>Note: archie keeps the pattern but does not evaluate it, see
 * {@code com.nedap.archie.aom.primitives.CTemporal#isValidValue}.</p>
 */
public record TemporalPattern(Map<TemporalPatternField, TemporalPatternValidity> validities) implements Serializable {

    private static final String MM = "(MM|\\?\\?|XX)";
    private static final String DD = "(DD|\\?\\?|XX)";
    private static final String HH = "(HH|\\?\\?|XX)";
    private static final String SS = "(SS|\\?\\?|XX)";

    // HH and MM here spell the offset suffix (+hh:mm), captured as the last group
    private static final String TIMEZONE = "(Z|[+-]HH(?::?MM)?)?";

    private static final Pattern DATE_TIME = Pattern.compile(
            "YYYY-" + MM + "-" + DD + "(?:T" + HH + ":" + MM + ":" + SS + TIMEZONE + ")?", Pattern.CASE_INSENSITIVE);
    private static final Pattern TIME = Pattern.compile("HH:" + MM + ":" + SS + TIMEZONE, Pattern.CASE_INSENSITIVE);

    // the fields in the order of the capturing groups of the patterns above
    private static final List<TemporalPatternField> DATE_TIME_FIELDS = List.of(
            TemporalPatternField.MONTH,
            TemporalPatternField.DAY,
            TemporalPatternField.HOUR,
            TemporalPatternField.MINUTE,
            TemporalPatternField.SECOND,
            TemporalPatternField.OFFSET);
    private static final List<TemporalPatternField> TIME_FIELDS =
            List.of(TemporalPatternField.MINUTE, TemporalPatternField.SECOND, TemporalPatternField.OFFSET);

    public TemporalPattern {
        Map<TemporalPatternField, TemporalPatternValidity> copy = new EnumMap<>(TemporalPatternField.class);
        copy.putAll(validities);
        validities = Collections.unmodifiableMap(copy);
    }

    /**
     * Maps the pattern fields to their ChronoField; timezone in pattern makes offset mandatory
     */
    public enum TemporalPatternField {
        MONTH(ChronoField.MONTH_OF_YEAR),
        DAY(ChronoField.DAY_OF_MONTH),
        HOUR(ChronoField.HOUR_OF_DAY),
        MINUTE(ChronoField.MINUTE_OF_HOUR),
        SECOND(ChronoField.SECOND_OF_MINUTE),
        OFFSET(ChronoField.OFFSET_SECONDS);

        private final ChronoField chronoField;

        TemporalPatternField(ChronoField chronoField) {
            this.chronoField = chronoField;
        }

        public ChronoField chronoField() {
            return chronoField;
        }
    }

    /**
     * Field validity, with added offset-only entries for timezones
     */
    public enum TemporalPatternValidity {
        MANDATORY,
        OPTIONAL,
        PROHIBITED,
        // offset only: Z requires a zero offset, ±hh a whole number of hours
        MANDATORY_UTC,
        MANDATORY_HOURS
    }

    /**
     * Parses the received pattern into a TemporalPattern, or null if blank or not supported.
     * The first char decides the format: y for a date (with an optional time part), h for a time.
     */
    public static TemporalPattern parse(String pattern) {
        if (pattern == null || pattern.isBlank()) {
            return null;
        }
        String normalized = pattern.strip();
        return switch (normalized.charAt(0)) {
            case 'y', 'Y' -> parse(DATE_TIME.matcher(normalized), DATE_TIME_FIELDS);
            case 'h', 'H' -> parse(TIME.matcher(normalized), TIME_FIELDS);
            default -> null;
        };
    }

    /** Whether this pattern is for the web template input type DATE, TIME or DATETIME */
    public boolean isForInputType(String inputType) {
        boolean date = validities.containsKey(TemporalPatternField.DAY);
        boolean time = validities.containsKey(TemporalPatternField.MINUTE);
        return switch (inputType) {
            case "DATE" -> date && !time;
            case "TIME" -> time && !date;
            case "DATETIME" -> date && time;
            default -> false;
        };
    }

    private static TemporalPattern parse(Matcher matcher, List<TemporalPatternField> fields) {
        if (!matcher.matches()) {
            return null;
        }
        EnumMap<TemporalPatternField, TemporalPatternValidity> validities = new EnumMap<>(TemporalPatternField.class);
        for (int i = 0; i < fields.size(); i++) {
            String group = matcher.group(i + 1);
            // a null group is a part the pattern does not have: the time part of a date pattern, or the timezone suffix
            if (group != null) {
                validities.put(fields.get(i), validityOf(group));
            }
        }
        return hasFieldOutOfOrder(validities) || hasOffsetWithProhibitedHour(validities)
                ? null
                : new TemporalPattern(validities);
    }

    private static TemporalPatternValidity validityOf(String token) {
        return switch (token.charAt(0)) {
            case '?' -> TemporalPatternValidity.OPTIONAL;
            case 'x', 'X' -> TemporalPatternValidity.PROHIBITED;
            case 'z', 'Z' -> TemporalPatternValidity.MANDATORY_UTC;
            // +hh requires whole hours, +hh:mm and +hhmm any offset
            case '+', '-' ->
                token.length() == 3 ? TemporalPatternValidity.MANDATORY_HOURS : TemporalPatternValidity.MANDATORY;
            default -> TemporalPatternValidity.MANDATORY;
        };
    }

    private static boolean hasFieldOutOfOrder(EnumMap<TemporalPatternField, TemporalPatternValidity> validities) {
        TemporalPatternValidity previous = TemporalPatternValidity.MANDATORY;
        for (Map.Entry<TemporalPatternField, TemporalPatternValidity> entry : validities.entrySet()) {
            if (entry.getKey() == TemporalPatternField.OFFSET) {
                continue;
            }
            TemporalPatternValidity current = entry.getValue();
            if ((previous == TemporalPatternValidity.OPTIONAL && current == TemporalPatternValidity.MANDATORY)
                    || (previous == TemporalPatternValidity.PROHIBITED
                            && current != TemporalPatternValidity.PROHIBITED)) {
                return true;
            }
            previous = current;
        }
        return false;
    }

    /** An offset needs a time part, so no value can satisfy a timezone suffix with a prohibited hour */
    private static boolean hasOffsetWithProhibitedHour(
            EnumMap<TemporalPatternField, TemporalPatternValidity> validities) {
        return validities.containsKey(TemporalPatternField.OFFSET)
                && validities.get(TemporalPatternField.HOUR) == TemporalPatternValidity.PROHIBITED;
    }
}
