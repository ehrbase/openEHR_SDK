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
package org.ehrbase.openehr.sdk.webtemplate.parser;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternField;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity;

/**
 *  Parses the pattern of a C_DATE, C_TIME or C_DATE_TIME constraint into a {@link TemporalPattern}.
 *  <p>Example shape: yyyy-mm-?? | letters mean mandatory fields; ?? means optional fields; XX means prohibited
 *  fields</p>
 *  <ul>
 *  <li>The year of a date, the hour of a time are always present.</li>
 *  <li>Date patterns constrain months and days</li>
 *  <li>Time patterns constrain minutes and seconds</li>
 *  <li>Date-time patterns constrain months, days, hours, minutes and seconds</li>
 *  <li>A timezone suffix on a time or date-time pattern requires an offset: Z a zero offset (the spec equates Z with
 *  +00:00), ±hh a whole number of hours, ±hh:mm and ±hhmm any offset. The text form of the offset is not checked.
 *  An offset needs a time part, so a required offset also makes the hour mandatory</li>
 *  <li>Patterns are case-insensitive when matched</li>
 *  <li>Patterns that do not match any accepted format (date, time, datetime) are not supported and yield null.</li>
 *  </ul>
 */
public final class TemporalPatternParser {

    private static final String MM = "(MM|\\?\\?|XX)";
    private static final String DD = "(DD|\\?\\?|XX)";
    private static final String HH = "(HH|\\?\\?|XX)";
    private static final String SS = "(SS|\\?\\?|XX)";

    // HH and MM here spell the offset suffix (+hh:mm), captured as the last group
    private static final String TIMEZONE = "(Z|[+-]HH(?::?MM)?)?";
    // the hours-only form of the suffix (+hh), which requires a whole-hours offset
    private static final Pattern TIMEZONE_HOUR_SUFFIX = Pattern.compile("[+-]HH", Pattern.CASE_INSENSITIVE);

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

    private TemporalPatternParser() {}

    // Parses the received pattern into a TemporalPattern, or null if blank or not supported.
    // The first char decides the format: y for a date (with an optional time part), h for a time.
    public static TemporalPattern parse(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            return null;
        }
        String normalized = pattern.strip();
        char first = Character.toUpperCase(normalized.charAt(0));

        if (first == 'Y') {
            Matcher dateTime = DATE_TIME.matcher(normalized);
            return dateTime.matches() ? build(dateTime, DATE_TIME_FIELDS) : null;
        }
        if (first == 'H') {
            Matcher time = TIME.matcher(normalized);
            return time.matches() ? build(time, TIME_FIELDS) : null;
        }
        return null;
    }

    private static TemporalPattern build(Matcher matcher, List<TemporalPatternField> fields) {
        Map<TemporalPatternField, TemporalPatternValidity> validities = new EnumMap<>(TemporalPatternField.class);
        for (int i = 0; i < fields.size(); i++) {
            String group = matcher.group(i + 1);
            // a null group is a part the pattern does not have: the time part of a date pattern, or the timezone suffix
            if (group != null) {
                validities.put(fields.get(i), validityOf(group));
            }
        }
        return new TemporalPattern(validities);
    }

    private static TemporalPatternValidity validityOf(String token) {
        if ("??".equals(token)) {
            return TemporalPatternValidity.OPTIONAL;
        }
        if ("XX".equalsIgnoreCase(token)) {
            return TemporalPatternValidity.PROHIBITED;
        }
        if ("Z".equalsIgnoreCase(token)) {
            return TemporalPatternValidity.MANDATORY_UTC;
        }
        return TIMEZONE_HOUR_SUFFIX.matcher(token).matches()
                ? TemporalPatternValidity.MANDATORY_HOURS
                : TemporalPatternValidity.MANDATORY;
    }
}
