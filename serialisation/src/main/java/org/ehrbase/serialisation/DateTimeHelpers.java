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
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQuery;

/**
 * This helper class is used to work around some Archie issues with date/time parsing accepting invalid years or not following the specified format fully (i.e. 2023-13, ignoring leap years)
 * @deprecated TODO: this should be removed when the issue is fixed in archie
 */
@Deprecated
public final class DateTimeHelpers {

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
            .append(ISO_8601_DATE_PARSER)
            .optionalStart()
            .appendLiteral('T')
            .append(ISO_8601_TIME_PARSER)
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);
    // partial times are allowed in compact format but archie does not implement that
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
            .append(ISO_8601_DATE_COMPACT_PARSER)
            .optionalStart()
            .appendLiteral('T')
            .append(ISO_8601_TIME_COMPACT_PARSER)
            .optionalEnd()
            .toFormatter()
            .withResolverStyle(ResolverStyle.STRICT);

    private DateTimeHelpers() {}

    public static Temporal parseDate(String isoDate) {
        return (Temporal) parse(
                isoDate,
                ISO_8601_DATE_PARSER,
                ISO_8601_DATE_COMPACT_PARSER,
                LocalDate::from,
                YearMonth::from,
                Year::from);
    }

    public static TemporalAccessor parseTime(String isoTime) {
        return parse(isoTime, ISO_8601_TIME_PARSER, ISO_8601_TIME_COMPACT_PARSER, OffsetTime::from, LocalTime::from);
    }

    public static TemporalAccessor parseDateTime(String isoDateTime) {
        return parse(
                isoDateTime,
                ISO_8601_DATE_TIME_PARSER,
                ISO_8601_DATE_TIME_COMPACT_PARSER,
                OffsetDateTime::from,
                LocalDateTime::from,
                LocalDate::from,
                YearMonth::from,
                Year::from);
    }

    public static TemporalAccessor parse(
            String isoString,
            DateTimeFormatter extendedFormatter,
            DateTimeFormatter compactFormatter,
            TemporalQuery<?>... types) {
        if (isoString == null) {
            return null;
        }

        TemporalAccessor parsed;
        try {
            // We assume that extended format is used most of the time -> try that first
            parsed = extendedFormatter.parseBest(isoString, types);
        } catch (DateTimeException e) {
            parsed = compactFormatter.parseBest(isoString, types);
        }

        // The partial date implementations sometimes do not check ranges of the fields even with strict parsing
        // -> do it manually
        if (ChronoField.YEAR.isSupportedBy(parsed)) {
            ChronoField.YEAR.checkValidValue(parsed.get(ChronoField.YEAR));
        }
        if (ChronoField.MONTH_OF_YEAR.isSupportedBy(parsed)) {
            ChronoField.MONTH_OF_YEAR.checkValidValue(parsed.get(ChronoField.MONTH_OF_YEAR));
        }

        return parsed;
    }
}
