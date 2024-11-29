/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.util;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DecimalStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Optional;
import org.apache.commons.lang3.tuple.Pair;

/**
 * This helper class is used to work around some Archie issues with date/time parsing accepting invalid years or not following the specified format fully (i.e. 2023-13, ignoring leap years)
 */
public final class OpenEHRDateTimeSerializationUtils {

    // archie does not specify width of YEAR
    public static final DateTimeFormatter ISO_8601_DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.YEAR, 4)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.MONTH_OF_YEAR, 2)
            .optionalStart()
            .appendLiteral('-')
            .appendValue(ChronoField.DAY_OF_MONTH, 2)
            .optionalEnd()
            .optionalEnd()
            .toFormatter();

    // use fixed '.' decimal separator instead of archies ','
    public static final DateTimeFormatter ISO_8601_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendValue(ChronoField.HOUR_OF_DAY, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.MINUTE_OF_HOUR, 2)
            .optionalStart()
            .appendLiteral(':')
            .appendValue(ChronoField.SECOND_OF_MINUTE, 2)
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .optionalStart()
            .appendOffsetId()
            .optionalEnd()
            .toFormatter()
            .withDecimalStyle(DecimalStyle.STANDARD.withDecimalSeparator('.'));
    // archie does not provide a formatter for all resolutions, but uses unnecessary selection logic
    // also: use fixed '.' decimal separator instead of archies ','
    public static final DateTimeFormatter ISO_8601_DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
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
            .optionalStart()
            .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .optionalStart()
            .appendOffsetId()
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .optionalEnd()
            .toFormatter()
            .withDecimalStyle(DecimalStyle.STANDARD.withDecimalSeparator('.'));

    private static final LocalTime DEFAULT_TIME = LocalTime.of(0, 0);

    private OpenEHRDateTimeSerializationUtils() {}

    public static String formatDate(TemporalAccessor date) {
        return format(date, ISO_8601_DATE_FORMATTER, ChronoField.YEAR);
    }

    public static String formatTime(TemporalAccessor time) {
        return format(time, ISO_8601_TIME_FORMATTER, ChronoField.HOUR_OF_DAY);
    }

    public static String formatDateTime(TemporalAccessor dateTime) {
        return format(dateTime, ISO_8601_DATE_TIME_FORMATTER, ChronoField.YEAR);
    }

    private static String format(TemporalAccessor temporal, DateTimeFormatter formatter, ChronoField lowestResolution) {
        if (temporal == null) {
            return null;
        }

        if (!temporal.isSupported(lowestResolution)) {
            throw new IllegalArgumentException(
                    "The given TemporalAccessor does not support the minimal resolution defined by openEHR: "
                            + lowestResolution.name());
        }
        return formatter.format(temporal);
    }

    /**
     * Archie does not support partial dates for getMagnitude. This method will assume first day/month if missing.
     * As the openEHR spec defines year as the minimum, a non-null input will throw an Exception if {@link ChronoField}.YEAR is not supported.
     * @param date
     * @return
     */
    public static Long toMagnitude(DvDate date) {
        if (date == null || date.getValue() == null) {
            return null;
        }

        return toLocalDate(date.getValue()).toEpochDay() + DvDate.DAYS_BETWEEN_0001_AND_1970;
    }

    /**
     * Archie does not support partial date-times for getMagnitude. This method will assume first day/month and 0 for time components if missing.
     * As the openEHR spec defines year as the minimum, a non-null input will throw an Exception if {@link ChronoField}.YEAR is not supported.
     * @param dateTime
     * @return
     */
    public static Long toMagnitude(DvDateTime dateTime) {
        if (dateTime == null || dateTime.getValue() == null) {
            return null;
        }

        TemporalAccessor value = dateTime.getValue();
        LocalDate date = toLocalDate(value);
        final Long epochSecond;
        if (value.isSupported(ChronoField.HOUR_OF_DAY)) {
            Pair<LocalTime, ZoneOffset> localTimeWithTz = toLocalTimeAndTz(value);
            epochSecond = date.toEpochSecond(localTimeWithTz.getLeft(), localTimeWithTz.getRight());
        } else {
            epochSecond = date.toEpochSecond(DEFAULT_TIME, ZoneOffset.UTC);
        }

        return epochSecond + DvDateTime.SECONDS_BETWEEN_0001_AND_1970;
    }

    /**
     * Archie does not support partial times for getMagnitude. This method will assume 0 for fields not within the precision.
     * As the openEHR spec defines hour as the minimum, a non-null input will throw an Exception if {@link ChronoField}.MINUTE_OF_HOUR is not supported.
     * @param time
     * @return
     */
    public static Double toMagnitude(DvTime time) {
        if (time == null || time.getValue() == null) {
            return null;
        }

        TemporalAccessor value = time.getValue();
        return (double) toLocalTimeAndTz(value).getLeft().toSecondOfDay();
    }

    private static LocalDate toLocalDate(TemporalAccessor value) {

        // Most common cases (the ones we parse ISO strings with a date component to) where creating a new LocalDate is
        // not necessary
        if (value instanceof OffsetDateTime) {
            return ((OffsetDateTime) value).toLocalDate();
        }

        if (value instanceof LocalDateTime) {
            return ((LocalDateTime) value).toLocalDate();
        }

        if (value instanceof LocalDate) {
            return (LocalDate) value;
        }

        // more exotic cases
        int year = value.get(ChronoField.YEAR);
        int month = value.isSupported(ChronoField.MONTH_OF_YEAR) ? value.get(ChronoField.MONTH_OF_YEAR) : 1;
        int day = value.isSupported(ChronoField.DAY_OF_MONTH) && value.isSupported(ChronoField.MONTH_OF_YEAR)
                ? value.get(ChronoField.DAY_OF_MONTH)
                : 1;
        return LocalDate.of(year, month, day);
    }

    private static Pair<LocalTime, ZoneOffset> toLocalTimeAndTz(TemporalAccessor value) {

        // Most common cases (the ones we parse ISO strings with a time component to) where creating a new LocalTime is
        // not necessary
        if (value instanceof OffsetTime) {
            return Pair.of(((OffsetTime) value).toLocalTime(), ((OffsetTime) value).getOffset());
        }

        if (value instanceof LocalTime) {
            return Pair.of((LocalTime) value, ZoneOffset.UTC);
        }

        if (value instanceof OffsetDateTime) {
            return Pair.of(((OffsetDateTime) value).toLocalTime(), ((OffsetDateTime) value).getOffset());
        }

        if (value instanceof LocalDateTime) {
            return Pair.of(((LocalDateTime) value).toLocalTime(), ZoneOffset.UTC);
        }

        // More exotic cases
        int hour = value.get(ChronoField.HOUR_OF_DAY);
        int minute = value.isSupported(ChronoField.MINUTE_OF_HOUR) ? value.get(ChronoField.MINUTE_OF_HOUR) : 0;
        int second = value.isSupported(ChronoField.SECOND_OF_MINUTE) && value.isSupported(ChronoField.MINUTE_OF_HOUR)
                ? value.get(ChronoField.SECOND_OF_MINUTE)
                : 0;
        int nanoSecond = value.isSupported(ChronoField.NANO_OF_SECOND)
                        && value.isSupported(ChronoField.SECOND_OF_MINUTE)
                        && value.isSupported(ChronoField.MINUTE_OF_HOUR)
                ? value.get(ChronoField.NANO_OF_SECOND)
                : 0;
        return Pair.of(
                LocalTime.of(hour, minute, second, nanoSecond),
                Optional.of(TemporalQueries.offset()).map(value::query).orElse(ZoneOffset.UTC));
    }
}
