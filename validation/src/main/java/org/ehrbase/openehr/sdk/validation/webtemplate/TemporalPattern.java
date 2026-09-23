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
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/// Pattern used by C_DATE, C_TIME, C_DATE_TIME constraints.
/// Example shape: yyyy-mm-?? | letters mean mandatory fields; ?? means optional fields; XX means prohibited fields
/// - The year of a date, the hour of a time are always present.
/// - Date patterns constrain months and days
/// - Time patterns constrain minutes and seconds
/// - Date-time patterns constrain months, days, hours, minutes and seconds
/// - Timezones are accepted, not enforced
/// - Values are checked by the fields they carry, so both compact (dashless) and extended forms are accepted
/// - Patterns are case-insensitive when matched
/// - Patterns that do not match any accepted format (date, time, datetime) are ignored and logged.
/// Note: archie keeps the pattern but does not evaluate it, see
/// `com.nedap.archie.aom.primitives.CTemporal#isValidValue`.

public final class TemporalPattern {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporalPattern.class);

    private static final String MONTH_OR_MINUTE = "(MM|\\?\\?|XX)";
    private static final String DAY = "(DD|\\?\\?|XX)";
    private static final String HOUR = "(HH|\\?\\?|XX)";
    private static final String SECOND = "(SS|\\?\\?|XX)";
    private static final String TIMEZONE = "(?:Z|[+-]HH(?::?MM)?)?";

    private static final Pattern DATE = Pattern.compile("YYYY-" + MONTH_OR_MINUTE + "-" + DAY);
    private static final Pattern TIME = Pattern.compile("HH:" + MONTH_OR_MINUTE + ":" + SECOND + TIMEZONE);
    private static final Pattern DATE_TIME = Pattern.compile(
            "YYYY-" + MONTH_OR_MINUTE + "-" + DAY + "T" + HOUR + ":" + MONTH_OR_MINUTE + ":" + SECOND + TIMEZONE);

    // Associates, labels and orders the ChronoFields
    private enum Field {
        MONTH(ChronoField.MONTH_OF_YEAR),
        DAY(ChronoField.DAY_OF_MONTH),
        HOUR(ChronoField.HOUR_OF_DAY),
        MINUTE(ChronoField.MINUTE_OF_HOUR),
        SECOND(ChronoField.SECOND_OF_MINUTE);

        private final ChronoField chronoField;

        Field(ChronoField chronoField) {
            this.chronoField = chronoField;
        }
    }

    private enum Validity {
        MANDATORY,
        OPTIONAL,
        PROHIBITED;

        static Validity of(String input) {
            return switch (input) {
                case "??" -> OPTIONAL;
                case "XX" -> PROHIBITED;
                default -> MANDATORY;
            };
        }
    }

    private final Map<Field, Validity> validities = new EnumMap<>(Field.class);

    private TemporalPattern(Matcher matcher, Field... fields) {
        for (int i = 0; i < fields.length; i++) {
            validities.put(fields[i], Validity.of(matcher.group(i + 1)));
        }
    }

    /// Validates the value against the input pattern (if exists)
    /// Returns the violation with the value, pattern and fields (if present)
    public static List<ConstraintViolation> validate(String aqlPath, TemporalAccessor value, WebTemplateInput input) {
        if (value == null || input == null || !WebTemplateValidationUtils.hasValidationPattern(input)) {
            return List.of();
        }
        String pattern = input.getValidation().getPattern();
        TemporalPattern temporalPattern = parse(pattern);
        // pattern does not match any accepted patterns
        if (temporalPattern == null) {
            LOGGER.debug("Ignoring unsupported date/time pattern '{}' at {}", pattern, aqlPath);
            return List.of();
        }

        List<String> violations = temporalPattern.violations(value);
        if (violations.isEmpty()) {
            return List.of();
        }
        return List.of(new ConstraintViolation(
                aqlPath,
                "The value %s does not conform to the pattern %s (%s)"
                        .formatted(value, pattern, String.join(", ", violations))));
    }

    // Parses the received pattern into a TemporalPattern, or null if not supported.
    private static TemporalPattern parse(String pattern) {
        String normalized = pattern.strip().toUpperCase(Locale.ROOT);

        Matcher date = DATE.matcher(normalized);
        if (date.matches()) {
            return new TemporalPattern(date, Field.MONTH, Field.DAY);
        }
        Matcher time = TIME.matcher(normalized);
        if (time.matches()) {
            return new TemporalPattern(time, Field.MINUTE, Field.SECOND);
        }
        Matcher dateTime = DATE_TIME.matcher(normalized);
        if (dateTime.matches()) {
            return new TemporalPattern(dateTime, Field.MONTH, Field.DAY, Field.HOUR, Field.MINUTE, Field.SECOND);
        }
        return null;
    }

    /// Checks through the fields and validities of the temporal pattern against this value.
    /// If a field is mandatory, it must be present; if it is prohibited, it must not be present.
    /// Returns the fields of the value which do not respect the pattern (shape ex: "month is mandatory") (if any)
    private List<String> violations(TemporalAccessor value) {
        List<String> result = new ArrayList<>();
        validities.forEach((field, validity) -> {
            boolean present = value.isSupported(field.chronoField);
            String name = field.name().toLowerCase(Locale.ROOT);
            if (validity == Validity.MANDATORY && !present) {
                result.add(name + " is mandatory");
            } else if (validity == Validity.PROHIBITED && present) {
                result.add(name + " is prohibited");
            }
        });
        return result;
    }
}
