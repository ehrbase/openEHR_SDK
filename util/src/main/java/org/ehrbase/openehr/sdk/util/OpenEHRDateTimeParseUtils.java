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
package org.ehrbase.openehr.sdk.util;

import java.text.ParsePosition;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.function.Predicate;

/**
 * This helper class is used to work around some Archie issues with date/time parsing accepting invalid years or not following the specified format fully (i.e. 2023-13, ignoring leap years)
 */
public final class OpenEHRDateTimeParseUtils {

    // archie version allows comma without specifying fraction
    public static final DateTimeFormatter ISO8601_OPTIONAL_NANOSECONDS = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            // nanoseconds, decimal fraction, ISO 31-0: comma [,] or full stop [.]
            .optionalStart() // nano seconds ,
            .appendLiteral(',')
            .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, false)
            .optionalEnd() // nano seconds ,
            .optionalStart() // nano seconds .
            .appendFraction(ChronoField.NANO_OF_SECOND, 1, 9, true)
            .optionalEnd() // nano seconds .
            .toFormatter();

    public static final DateTimeFormatter ISO8601_TIME_ZONE = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .optionalStart()
            .appendOffset("+HH:mm", "Z")
            .optionalEnd()
            .toFormatter();

    public static final DateTimeFormatter ISO8601_COMPACT_TIME_ZONE = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .optionalStart()
            .appendOffset("+HHmm", "Z")
            .optionalEnd()
            .toFormatter();

    // archie version does not specify width of YEAR field (YYYY)
    public static final DateTimeFormatter ISO_8601_DATE_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    // archie version does not specify width of HOUR_OF_DAY (hh)
    public static final DateTimeFormatter ISO_8601_TIME_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .append(ISO8601_OPTIONAL_NANOSECONDS)
            .optionalEnd()
            .optionalEnd()
            .append(ISO8601_TIME_ZONE)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    public static final DateTimeFormatter ISO_8601_DATE_TIME_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .optionalStart()
            .appendLiteral('T')
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .append(ISO8601_OPTIONAL_NANOSECONDS)
            .optionalEnd()
            .optionalEnd()
            .append(ISO8601_TIME_ZONE)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    // partial dates are allowed in compact format but archie does not implement that
    public static final DateTimeFormatter ISO_8601_DATE_COMPACT_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart()
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    // partial times are allowed in compact format but archie does not implement that
    public static final DateTimeFormatter ISO_8601_TIME_COMPACT_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .optionalStart()
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .append(ISO8601_OPTIONAL_NANOSECONDS)
            .optionalEnd()
            .optionalEnd()
            .append(ISO8601_COMPACT_TIME_ZONE)
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    // partial date-times are allowed in compact format but archie does not implement that
    public static final DateTimeFormatter ISO_8601_DATE_TIME_COMPACT_PARSER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart()
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .optionalStart()
            .appendLiteral('T')
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .optionalStart()
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .append(ISO8601_OPTIONAL_NANOSECONDS)
            .optionalEnd()
            .optionalEnd()
            .append(ISO8601_COMPACT_TIME_ZONE)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    private OpenEHRDateTimeParseUtils() {}

    private static boolean isCompactDate(String s) {
        return s.length() > 4 && s.charAt(4) != '-';
    }

    private static boolean isCompactTime(String s) {
        if (s.length() <= 2) {
            return false;
        }
        char ch = s.charAt(2);
        return switch (ch) {
            case ':' -> false;
            // '12+02:00' vs. '12+0200'
            case '+', '-' -> s.length() > 5 && s.charAt(5) != ':';
            default -> true;
        };
    }

    public static Temporal parseDate(String isoDate) {
        return parseOpenEhrTemporal(
                isoDate, OpenEHRDateTimeParseUtils::isCompactDate, ISO_8601_DATE_COMPACT_PARSER, ISO_8601_DATE_PARSER);
    }

    public static TemporalAccessor parseTime(String isoTime) {
        return parseOpenEhrTemporal(
                isoTime, OpenEHRDateTimeParseUtils::isCompactTime, ISO_8601_TIME_COMPACT_PARSER, ISO_8601_TIME_PARSER);
    }

    public static TemporalAccessor parseDateTime(String isoDateTime) {
        return parseOpenEhrTemporal(
                isoDateTime,
                OpenEHRDateTimeParseUtils::isCompactDate,
                ISO_8601_DATE_TIME_COMPACT_PARSER,
                ISO_8601_DATE_TIME_PARSER);
    }

    private static OpenEhrTemporal parseOpenEhrTemporal(
            final String isoDate,
            final Predicate<String> isCompactPredicate,
            final DateTimeFormatter compactParser,
            final DateTimeFormatter extendedParser) {
        if (isoDate == null) {
            return null;
        }
        try {
            final DateTimeFormatter parser;
            if (isCompactPredicate.test(isoDate)) {
                parser = compactParser;
            } else {
                parser = extendedParser;
            }

            return parseOpenEhrTemporal(isoDate, parser);
        } catch (DateTimeException e) {
            // This wrapping does not necessarily make sense, but since this is a workaround we keep the archie
            // behaviour
            throw new IllegalArgumentException(e.getMessage() + ":" + isoDate, e);
        }
    }

    private static OpenEhrTemporal parseOpenEhrTemporal(final CharSequence text, DateTimeFormatter parser) {
        ParsePosition pos = new ParsePosition(0);
        TemporalAccessor result = parser.parseUnresolved(text, pos);
        if (pos.getErrorIndex() >= 0 || pos.getIndex() < text.length()) {
            String abbr;
            if (text.length() > 64) {
                abbr = text.subSequence(0, 64) + "...";
            } else {
                abbr = text.toString();
            }
            if (pos.getErrorIndex() >= 0) {
                throw new DateTimeParseException(
                        "Text '" + abbr + "' could not be parsed at index " + pos.getErrorIndex(),
                        text,
                        pos.getErrorIndex());
            } else {
                throw new DateTimeParseException(
                        "Text '" + abbr + "' could not be parsed, unparsed text found at index " + pos.getIndex(),
                        text,
                        pos.getIndex());
            }
        }
        try {
            return new OpenEhrTemporal(result);
        } catch (IllegalArgumentException e) {
            throw new DateTimeException(
                    "%s does not provide any field required for the possible precisions: %s".formatted(text, text));
        }
    }
}
