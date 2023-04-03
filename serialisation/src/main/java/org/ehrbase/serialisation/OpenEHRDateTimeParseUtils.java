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
package org.ehrbase.serialisation;

import static com.nedap.archie.datetime.DateTimeFormatters.ISO8601_OPTIONAL_NANOSECONDS;
import static com.nedap.archie.datetime.DateTimeFormatters.ISO8601_TIME_ZONE;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.function.Predicate;

/**
 * This helper class is used to work around some Archie issues with date/time parsing accepting invalid years or not following the specified format fully (i.e. 2023-13, ignoring leap years)
 */
public final class OpenEHRDateTimeParseUtils {

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
            .append(ISO8601_TIME_ZONE)
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
            .append(ISO8601_TIME_ZONE)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    private static final Predicate<String> COMPACT_DATE_PREDICATE = s -> s.length() > 4 && s.charAt(4) != '-';
    private static final Predicate<String> COMPACT_TIME_PREDICATE = s -> s.length() > 2 && s.charAt(2) != ':';

    private OpenEHRDateTimeParseUtils() {}

    public static Temporal parseDate(String isoDate) {
        if (isoDate == null) {
            return null;
        }
        try {
            final TemporalAccessor parsed;
            if (COMPACT_DATE_PREDICATE.test(isoDate)) {
                parsed = ISO_8601_DATE_COMPACT_PARSER.parse(isoDate);
            } else {
                parsed = ISO_8601_DATE_PARSER.parse(isoDate);
            }

            if (parsed.isSupported(ChronoField.DAY_OF_MONTH)) {
                return LocalDate.from(parsed);
            }

            if (parsed.isSupported(ChronoField.MONTH_OF_YEAR)) {
                return YearMonth.from(parsed);
            }

            if (parsed.isSupported(ChronoField.YEAR)) {
                return Year.from(parsed);
            }
            throw new DateTimeException(
                    isoDate + " does not provide any field required for the possible precisions:" + isoDate);
        } catch (DateTimeException e) {
            // This wrapping does not necessarily make sense, but since this is a workaround we keep the archie
            // behaviour
            throw new IllegalArgumentException(e.getMessage() + ":" + isoDate, e);
        }
    }

    public static TemporalAccessor parseTime(String isoTime) {
        if (isoTime == null) {
            return null;
        }
        try {

            final TemporalAccessor parsed;
            if (COMPACT_TIME_PREDICATE.test(isoTime)) {
                parsed = ISO_8601_TIME_COMPACT_PARSER.parse(isoTime);
            } else {
                parsed = ISO_8601_TIME_PARSER.parse(isoTime);
            }

            if (parsed.isSupported(ChronoField.HOUR_OF_DAY) && parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                // Do not use OffsetTime::from, so we do not have to unwrap exceptions to get meaningful messages
                return OffsetTime.of(LocalTime.from(parsed), ZoneOffset.from(parsed));
            }

            if (parsed.isSupported(ChronoField.HOUR_OF_DAY)) {
                return LocalTime.from(parsed);
            }
            throw new DateTimeException(
                    isoTime + " does not provide any field required for the possible precisions:" + isoTime);
        } catch (DateTimeException e) {
            // This wrapping does not necessarily make sense, but since this is a workaround we keep the archie
            // behaviour to avoid breaking stuff
            throw new IllegalArgumentException(e.getMessage() + ":" + isoTime, e);
        }
    }

    public static TemporalAccessor parseDateTime(String isoDateTime) {
        if (isoDateTime == null) {
            return null;
        }
        try {
            final TemporalAccessor parsed;
            // We can use the date predicate as a date-time will always start with a date
            if (COMPACT_DATE_PREDICATE.test(isoDateTime)) {
                parsed = ISO_8601_DATE_TIME_COMPACT_PARSER.parse(isoDateTime);
            } else {
                parsed = ISO_8601_DATE_TIME_PARSER.parse(isoDateTime);
            }

            if (parsed.isSupported(ChronoField.HOUR_OF_DAY)) {
                // Do not use OffsetTime::from, so we do not have to unwrap exceptions to get meaningful messages
                if (parsed.isSupported(ChronoField.OFFSET_SECONDS)) {
                    return OffsetDateTime.of(LocalDate.from(parsed), LocalTime.from(parsed), ZoneOffset.from(parsed));
                } else {
                    return LocalDateTime.of(LocalDate.from(parsed), LocalTime.from(parsed));
                }
            }

            if (parsed.isSupported(ChronoField.DAY_OF_MONTH)) {
                return LocalDate.from(parsed);
            }

            if (parsed.isSupported(ChronoField.MONTH_OF_YEAR)) {
                return YearMonth.from(parsed);
            }

            if (parsed.isSupported(ChronoField.YEAR)) {
                return Year.from(parsed);
            }
            throw new DateTimeException(
                    isoDateTime + " does not provide any field required for the possible precisions:" + isoDateTime);
        } catch (DateTimeException e) {
            // This wrapping does not necessarily make sense, but since this is a workaround we keep the archie
            // behaviour
            throw new IllegalArgumentException(e.getMessage() + ":" + isoDateTime, e);
        }
    }
}
