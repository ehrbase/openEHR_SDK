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

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.temporal.TemporalAccessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class OpenEHRDateTimeParseUtilsTest {
    enum DateParsingTestData {
        NULL_INPUT(null, (TemporalAccessor) null),

        // ------Extended valid format and values
        EXTENDED_PRECISION_DAY("2023-01-01", LocalDate.of(2023, 1, 1)),
        EXTENDED_PRECISION_MONTH("2023-01", YearMonth.of(2023, 1)),
        EXTENDED_PRECISION_YEAR("2023", Year.of(2023)),

        // ------Extended valid format, invalid values
        EXTENDED_INVALID_NO_LEAP_YEAR(
                "2023-02-29",
                IllegalArgumentException.class,
                "Text '2023-02-29' could not be parsed: Invalid date 'February 29' as '2023' is not a leap year:2023-02-29"),
        EXTENDED_INVALID_DAY_LOWER(
                "2023-01-00",
                IllegalArgumentException.class,
                "Text '2023-01-00' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:2023-01-00"),
        EXTENDED_INVALID_DAY_UPPER(
                "2023-01-32",
                IllegalArgumentException.class,
                "Text '2023-01-32' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32:2023-01-32"),
        EXTENDED_INVALID_MONTH_LOWER(
                "2023-00-01",
                IllegalArgumentException.class,
                "Text '2023-00-01' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0:2023-00-01"),
        EXTENDED_INVALID_MONTH_UPPER(
                "2023-13-01",
                IllegalArgumentException.class,
                "Text '2023-13-01' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13:2023-13-01"),

        // Extended invalid field width
        // There are no invalid values for 4-digit years, so no test case for that
        EXTENDED_INVALID_WIDTH_SMALLER_YEAR("202-01-01", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_YEAR("02023-01-01", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_MONTH("2023-1-01", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_MONTH("2023-001-01", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_DAY("2023-01-1", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_DAY("2023-01-001", IllegalArgumentException.class),

        // ------Extended invalid format strings
        EXTENDED_INVALID_DATE_TIME_STRING("2023-01-01T12:13:14.123456789Z", IllegalArgumentException.class),
        EXTENDED_INVALID_TIME_STRING("12:13:14.123456789Z", IllegalArgumentException.class),

        // ------Compact valid format and values
        COMPACT_PRECISION_DAY("20230101", LocalDate.of(2023, 1, 1)),
        COMPACT_PRECISION_MONTH("202301", YearMonth.of(2023, 1)),
        COMPACT_PRECISION_YEAR("2023", Year.of(2023)),

        // ------Compact valid format, invalid values
        COMPACT_INVALID_NO_LEAP_YEAR(
                "20230229",
                IllegalArgumentException.class,
                "Text '20230229' could not be parsed: Invalid date 'February 29' as '2023' is not a leap year:20230229"),
        // There are no invalid values for 4digit years, so no test case for that
        COMPACT_INVALID_MONTH_LOWER(
                "20230001",
                IllegalArgumentException.class,
                "Text '20230001' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0:20230001"),
        COMPACT_INVALID_MONTH_UPPER(
                "20231301",
                IllegalArgumentException.class,
                "Text '20231301' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13:20231301"),
        COMPACT_INVALID_DAY_LOWER(
                "20230100",
                IllegalArgumentException.class,
                "Text '20230100' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:20230100"),
        COMPACT_INVALID_DAY_UPPER(
                "20230132",
                IllegalArgumentException.class,
                "Text '20230132' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32:20230132"),

        // ------Compact invalid field width
        // checking width of individual fields is not possible in compact time format as there are no separators, so we
        // check the separated groups
        COMPACT_INVALID_WIDTH_SMALLER_YYYYMMDD("2023011", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_YYYYMMDD("202301001", IllegalArgumentException.class),

        // ------Compact invalid format strings
        COMPACT_INVALID_DATE_TIME_STRING("20230101T121314.123456789Z", IllegalArgumentException.class),
        COMPACT_INVALID_TIME_STRING("121314.123456789Z", IllegalArgumentException.class),

        // ------General invalid format strings
        INVALID_NOT_OPENEHR_DATE_STRING("31th January 2023", IllegalArgumentException.class),
        INVALID_NON_DATE_STRING("foo-bar", IllegalArgumentException.class),
        INVALID_EMPTY_STRING("", IllegalArgumentException.class);

        private final String input;
        private final TemporalAccessor expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        DateParsingTestData(String input, TemporalAccessor expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        DateParsingTestData(String input, Class<? extends Exception> expectedExceptionType) {
            this(input, expectedExceptionType, null);
        }

        DateParsingTestData(
                String input, Class<? extends Exception> expectedExceptionType, String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(DateParsingTestData.class)
    void parseDate(DateParsingTestData data) {
        if (data.shouldThrowException) {
            Exception exception =
                    assertThrows(data.expectedExceptionType, () -> OpenEHRDateTimeParseUtils.parseDate(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeParseUtils.parseDate(data.input));
        }
    }

    enum TimeParsingTestData {
        NULL_INPUT(null, (TemporalAccessor) null),

        // ------Extended valid format and values
        EXTENDED_PRECISION_NANOSECOND_ZULU_OFFSET(
                "12:13:14.123456789Z", OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0))),
        EXTENDED_PRECISION_NANOSECOND_WITH_OFFSET(
                "12:13:14.123456789+01:30", OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_MILLISECOND_WITH_OFFSET(
                "12:13:14.123+01:30", OffsetTime.of(12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_SECOND_WITH_OFFSET(
                "12:13:14+01:30", OffsetTime.of(12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_MINUTE_WITH_OFFSET(
                "12:13+01:30", OffsetTime.of(12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_HOUR_WITH_OFFSET("12+01:30", OffsetTime.of(12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_NANOSECOND_POINT("12:13:14.123456789", LocalTime.of(12, 13, 14, 123456789)),
        EXTENDED_PRECISION_NANOSECOND_COMMA("12:13:14,123456789", LocalTime.of(12, 13, 14, 123456789)),
        EXTENDED_PRECISION_MILLISECOND_POINT("12:13:14.123", LocalTime.of(12, 13, 14, 123000000)),
        EXTENDED_PRECISION_MILLISECOND_COMMA("12:13:14,123", LocalTime.of(12, 13, 14, 123000000)),
        EXTENDED_PRECISION_SECOND("12:13:14", LocalTime.of(12, 13, 14)),
        EXTENDED_PRECISION_MINUTE("12:13", LocalTime.of(12, 13, 0)),
        EXTENDED_PRECISION_HOUR("12", LocalTime.of(12, 0, 0)),

        // ------Extended valid format, invalid values
        EXTENDED_INVALID_OFFSET_LOWER(
                "12:13:14.123456789+18:01",
                IllegalArgumentException.class,
                "Zone offset not in valid range: -18:00 to +18:00:12:13:14.123456789+18:01"),
        EXTENDED_INVALID_OFFSET_UPPER(
                "12:13:14.123456789-18:01",
                IllegalArgumentException.class,
                "Zone offset not in valid range: -18:00 to +18:00:12:13:14.123456789-18:01"),
        EXTENDED_INVALID_SECONDS_UPPER(
                "12:13:60.000000000+01:30",
                IllegalArgumentException.class,
                "Text '12:13:60.000000000+01:30' could not be parsed: Invalid value for SecondOfMinute (valid values 0 - 59): 60:12:13:60.000000000+01:30"),
        EXTENDED_INVALID_MINUTES_UPPER(
                "12:60:00.000000000+01:30",
                IllegalArgumentException.class,
                "Text '12:60:00.000000000+01:30' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): 60:12:60:00.000000000+01:30"),
        EXTENDED_INVALID_HOURS_UPPER(
                "24:00:00.000000000+01:30",
                IllegalArgumentException.class,
                "Text '24:00:00.000000000+01:30' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:24:00:00.000000000+01:30"),
        EXTENDED_INVALID_FRACTIONAL_MINUTES("12:13.000000000+01:30", IllegalArgumentException.class),
        EXTENDED_INVALID_FRACTIONAL_HOURS("12.000000000+01:30", IllegalArgumentException.class),

        // ------Extended invalid field width
        EXTENDED_INVALID_WIDTH_SMALLER_OFFSET_MINUTES("00:00:00.000000000+00:0", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_OFFSET_MINUTES("00:00:00.000000000+00:000", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_OFFSET_HOURS("00:00:00.000000000+0:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_OFFSET_HOURS("00:00:00.000000000+000:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_NANOSECONDS("00:00:00.0000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_SECONDS("00:00:0.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_SECONDS("00:00:000.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_MINUTES("00:0:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_MINUTES("00:000:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_HOURS("0:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_HOURS("000:00:00.000000000+00:00", IllegalArgumentException.class),

        // ------Extended invalid format strings
        EXTENDED_INVALID_DATE_TIME_STRING("2023-01-01T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_DATE_STRING("2023-01-01", IllegalArgumentException.class),

        // ------Compact valid format and values
        COMPACT_PRECISION_NANOSECOND_ZULU_OFFSET(
                "121314.123456789Z", OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0))),
        COMPACT_PRECISION_NANOSECOND_WITH_OFFSET(
                "121314.123456789+0130", OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_MILLISECOND_WITH_OFFSET(
                "121314.123+0130", OffsetTime.of(12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_SECOND_WITH_OFFSET(
                "121314+0130", OffsetTime.of(12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_MINUTE_WITH_OFFSET(
                "1213+0130", OffsetTime.of(12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_HOUR_WITH_OFFSET("12+0130", OffsetTime.of(12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_NANOSECOND_POINT("121314.123456789", LocalTime.of(12, 13, 14, 123456789)),
        COMPACT_PRECISION_NANOSECOND_COMMA("121314,123456789", LocalTime.of(12, 13, 14, 123456789)),
        COMPACT_PRECISION_MILLISECOND_POINT("121314.123", LocalTime.of(12, 13, 14, 123000000)),
        COMPACT_PRECISION_MILLISECOND_COMMA("121314,123", LocalTime.of(12, 13, 14, 123000000)),
        COMPACT_PRECISION_SECOND("121314", LocalTime.of(12, 13, 14)),
        COMPACT_PRECISION_MINUTE("1213", LocalTime.of(12, 13, 0)),
        COMPACT_PRECISION_HOUR("12", LocalTime.of(12, 0, 0)),

        // ------Compact valid format, invalid values
        COMPACT_INVALID_OFFSET_LOWER(
                "121314.123456789+1801",
                IllegalArgumentException.class,
                "Zone offset not in valid range: -18:00 to +18:00:121314.123456789+1801"),
        COMPACT_INVALID_OFFSET_UPPER(
                "121314.123456789-1801",
                IllegalArgumentException.class,
                "Zone offset not in valid range: -18:00 to +18:00:121314.123456789-1801"),
        COMPACT_INVALID_SECONDS_UPPER(
                "121360.000000000+0130",
                IllegalArgumentException.class,
                "Text '121360.000000000+0130' could not be parsed: Invalid value for SecondOfMinute (valid values 0 - 59): 60:121360.000000000+0130"),
        COMPACT_INVALID_MINUTES_UPPER(
                "126000.000000000+0130",
                IllegalArgumentException.class,
                "Text '126000.000000000+0130' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): 60:126000.000000000+0130"),
        COMPACT_INVALID_HOURS_UPPER(
                "240000.000000000+0130",
                IllegalArgumentException.class,
                "Text '240000.000000000+0130' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:240000.000000000+0130"),
        COMPACT_INVALID_FRACTIONAL_MINUTES("1213.000000000+0130", IllegalArgumentException.class),
        COMPACT_INVALID_FRACTIONAL_HOURS("12.000000000+0130", IllegalArgumentException.class),

        // ------Compact invalid field width
        // checking width of individual fields is not possible in compact time format as there are no separators, so we
        // check the separated groups
        COMPACT_INVALID_WIDTH_SMALLER_OFFSET("000000.000000000+000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_OFFSET("000000.000000000+00000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_NANOSECONDS("000000.0000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_SMALLER_HHMMSS("00000.000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_HHMMSS("0000000.000000000+0000", IllegalArgumentException.class),

        // ------Compact invalid format strings
        COMPACT_INVALID_DATE_TIME_STRING("20230101T000000.000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_DATE_STRING("20230101", IllegalArgumentException.class),

        // ------General invalid format strings
        INVALID_NOT_OPENEHR_TIME_STRING("1:30 pm UTC", IllegalArgumentException.class),
        INVALID_NON_DATE_STRING("foo-bar", IllegalArgumentException.class),
        INVALID_EMPTY_STRING("", IllegalArgumentException.class);

        private final String input;
        private final TemporalAccessor expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        TimeParsingTestData(String input, TemporalAccessor expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        TimeParsingTestData(String input, Class<? extends Exception> expectedExceptionType) {
            this(input, expectedExceptionType, null);
        }

        TimeParsingTestData(
                String input, Class<? extends Exception> expectedExceptionType, String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(TimeParsingTestData.class)
    void parseTime(TimeParsingTestData data) {
        if (data.shouldThrowException) {
            Exception exception =
                    assertThrows(data.expectedExceptionType, () -> OpenEHRDateTimeParseUtils.parseTime(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeParseUtils.parseTime(data.input));
        }
    }

    enum DateTimeParsingTestData {
        NULL_INPUT(null, (TemporalAccessor) null),

        // Extended valid format and values
        EXTENDED_PRECISION_NANOSECOND_ZULU_OFFSET(
                "2023-01-01T12:13:14.123456789Z",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0))),
        EXTENDED_PRECISION_NANOSECOND_WITH_OFFSET(
                "2023-01-01T12:13:14.123456789+01:30",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_MILLISECOND_WITH_OFFSET(
                "2023-01-01T12:13:14.123+01:30",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_SECOND_WITH_OFFSET(
                "2023-01-01T12:13:14+01:30",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_MINUTE_WITH_OFFSET(
                "2023-01-01T12:13+01:30",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_HOUR_WITH_OFFSET(
                "2023-01-01T12+01:30", OffsetDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        EXTENDED_PRECISION_NANOSECOND_POINT(
                "2023-01-01T12:13:14.123456789", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123456789)),
        EXTENDED_PRECISION_NANOSECOND_COMMA(
                "2023-01-01T12:13:14,123456789", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123456789)),
        EXTENDED_PRECISION_MILLISECOND_POINT(
                "2023-01-01T12:13:14.123", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123000000)),
        EXTENDED_PRECISION_MILLISECOND_COMMA(
                "2023-01-01T12:13:14,123", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123000000)),
        EXTENDED_PRECISION_SECOND("2023-01-01T12:13:14", LocalDateTime.of(2023, 1, 1, 12, 13, 14)),
        EXTENDED_PRECISION_MINUTE("2023-01-01T12:13", LocalDateTime.of(2023, 1, 1, 12, 13, 0)),
        EXTENDED_PRECISION_HOUR("2023-01-01T12", LocalDateTime.of(2023, 1, 1, 12, 0, 0)),
        EXTENDED_PRECISION_DAY("2023-01-01", LocalDate.of(2023, 1, 1)),
        EXTENDED_PRECISION_MONTH("2023-01", YearMonth.of(2023, 1)),
        EXTENDED_PRECISION_YEAR("2023", Year.of(2023)),

        // -------Extended valid format, invalid values
        EXTENDED_INVALID_NO_LEAP_YEAR(
                "2023-02-29T12:13:14.123456789Z",
                IllegalArgumentException.class,
                "Text '2023-02-29T12:13:14.123456789Z' could not be parsed: Invalid date 'February 29' as '2023' is not a leap year:2023-02-29T12:13:14.123456789Z"),
        EXTENDED_INVALID_OFFSET_LOWER(
                "2023-01-01T12:13:14.123456789+18:01",
                IllegalArgumentException.class,
                "Text '2023-01-01T12:13:14.123456789+18:01' could not be parsed: Zone offset not in valid range: -18:00 to +18:00:2023-01-01T12:13:14.123456789+18:01"),
        EXTENDED_INVALID_OFFSET_UPPER(
                "2023-01-01T12:13:14.123456789-18:01",
                IllegalArgumentException.class,
                "Text '2023-01-01T12:13:14.123456789-18:01' could not be parsed: Zone offset not in valid range: -18:00 to +18:00:2023-01-01T12:13:14.123456789-18:01"),
        EXTENDED_INVALID_SECONDS_UPPER(
                "2023-01-01T12:13:60.000000000+01:30",
                IllegalArgumentException.class,
                "Text '2023-01-01T12:13:60.000000000+01:30' could not be parsed: Invalid value for SecondOfMinute (valid values 0 - 59): 60:2023-01-01T12:13:60.000000000+01:30"),
        EXTENDED_INVALID_MINUTES_UPPER(
                "2023-01-01T12:60:00.000000000+01:30",
                IllegalArgumentException.class,
                "Text '2023-01-01T12:60:00.000000000+01:30' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): 60:2023-01-01T12:60:00.000000000+01:30"),
        EXTENDED_INVALID_HOURS_UPPER(
                "2023-01-01T24:00:00.000000000+01:30",
                IllegalArgumentException.class,
                "Text '2023-01-01T24:00:00.000000000+01:30' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:2023-01-01T24:00:00.000000000+01:30"),
        EXTENDED_INVALID_FRACTIONAL_MINUTES("2023-01-01T12:13.000000000+01:30", IllegalArgumentException.class),
        EXTENDED_INVALID_FRACTIONAL_HOURS("2023-01-01T12.000000000+01:30", IllegalArgumentException.class),
        EXTENDED_INVALID_DAY_LOWER(
                "2023-01-00T12:13:14.123456789+01:30",
                IllegalArgumentException.class,
                "Text '2023-01-00T12:13:14.123456789+01:30' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:2023-01-00T12:13:14.123456789+01:30"),
        EXTENDED_INVALID_DAY_UPPER(
                "2023-01-32T12:13:14.123456789+01:30",
                IllegalArgumentException.class,
                "Text '2023-01-32T12:13:14.123456789+01:30' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32:2023-01-32T12:13:14.123456789+01:30"),
        EXTENDED_INVALID_MONTH_LOWER(
                "2023-00-01T12:13:14.123456789+01:30",
                IllegalArgumentException.class,
                "Text '2023-00-01T12:13:14.123456789+01:30' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0:2023-00-01T12:13:14.123456789+01:30"),
        EXTENDED_INVALID_MONTH_UPPER(
                "2023-13-01T12:13:14.123456789+01:30",
                IllegalArgumentException.class,
                "Text '2023-13-01T12:13:14.123456789+01:30' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13:2023-13-01T12:13:14.123456789+01:30"),

        // -------Extended invalid field width
        EXTENDED_INVALID_WIDTH_SMALLER_OFFSET_MINUTES(
                "2023-01-01T00:00:00.000000000+00:0", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_OFFSET_MINUTES(
                "2023-01-01T00:00:00.000000000+00:000", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_OFFSET_HOURS(
                "2023-01-01T00:00:00.000000000+0:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_OFFSET_HOURS(
                "2023-01-01T00:00:00.000000000+000:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_NANOSECONDS(
                "2023-01-01T00:00:00.0000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_SECONDS("2023-01-01T00:00:0.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_SECONDS("2023-01-01T00:00:000.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_MINUTES("2023-01-01T00:0:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_MINUTES("2023-01-01T00:000:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_HOURS("2023-01-01T0:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_HOURS("2023-01-01T000:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_DAY("2023-01-1T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_DAY("2023-01-001T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_MONTH("2023-1-01T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_MONTH("2023-001-01T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_SMALLER_YEAR("202-01-01T00:00:00.000000000+00:00", IllegalArgumentException.class),
        EXTENDED_INVALID_WIDTH_BIGGER_YEAR("02023-01-01T00:00:00.000000000+00:00", IllegalArgumentException.class),

        // -------Extended invalid formats
        EXTENDED_INVALID_TRAILING_T("2023-02-291T", IllegalArgumentException.class),
        EXTENDED_INVALID_TIME_STRING("12:13:14.123456789Z", IllegalArgumentException.class),

        // -------Compact valid format and values
        COMPACT_PRECISION_NANOSECOND_ZULU_OFFSET(
                "20230101T121314.123456789Z",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0))),
        COMPACT_PRECISION_NANOSECOND_WITH_OFFSET(
                "20230101T121314.123456789+0130",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_MILLISECOND_WITH_OFFSET(
                "20230101T121314.123+0130",
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_SECOND_WITH_OFFSET(
                "20230101T121314+0130", OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_MINUTE_WITH_OFFSET(
                "20230101T1213+0130", OffsetDateTime.of(2023, 1, 1, 12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_HOUR_WITH_OFFSET(
                "20230101T12+0130", OffsetDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30))),
        COMPACT_PRECISION_NANOSECOND_POINT(
                "20230101T121314.123456789", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123456789)),
        COMPACT_PRECISION_NANOSECOND_COMMA(
                "20230101T121314,123456789", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123456789)),
        COMPACT_PRECISION_MILLISECOND_POINT("20230101T121314.123", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123000000)),
        COMPACT_PRECISION_MILLISECOND_COMMA("20230101T121314,123", LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123000000)),
        COMPACT_PRECISION_SECOND("20230101T121314", LocalDateTime.of(2023, 1, 1, 12, 13, 14)),
        COMPACT_PRECISION_MINUTE("20230101T1213", LocalDateTime.of(2023, 1, 1, 12, 13, 0)),
        COMPACT_PRECISION_HOUR("20230101T12", LocalDateTime.of(2023, 1, 1, 12, 0, 0)),
        COMPACT_PRECISION_DAY("20230101", LocalDate.of(2023, 1, 1)),
        COMPACT_PRECISION_MONTH("202301", YearMonth.of(2023, 1)),
        COMPACT_PRECISION_YEAR("2023", Year.of(2023)),

        // ------Compact valid format, invalid field values
        COMPACT_INVALID_NO_LEAP_YEAR(
                "20230229T121314.123456789Z",
                IllegalArgumentException.class,
                "Text '20230229T121314.123456789Z' could not be parsed: Invalid date 'February 29' as '2023' is not a leap year:20230229T121314.123456789Z"),
        COMPACT_INVALID_OFFSET_LOWER(
                "20230101T121314.123456789+1801",
                IllegalArgumentException.class,
                "Text '20230101T121314.123456789+1801' could not be parsed: Zone offset not in valid range: -18:00 to +18:00:20230101T121314.123456789+1801"),
        COMPACT_INVALID_OFFSET_UPPER(
                "20230101T121314.123456789-1801",
                IllegalArgumentException.class,
                "Text '20230101T121314.123456789-1801' could not be parsed: Zone offset not in valid range: -18:00 to +18:00:20230101T121314.123456789-1801"),
        COMPACT_INVALID_SECONDS_UPPER(
                "20230101T121360.000000000+0130",
                IllegalArgumentException.class,
                "Text '20230101T121360.000000000+0130' could not be parsed: Invalid value for SecondOfMinute (valid values 0 - 59): 60:20230101T121360.000000000+0130"),
        COMPACT_INVALID_MINUTES_UPPER(
                "20230101T126000.000000000+0130",
                IllegalArgumentException.class,
                "Text '20230101T126000.000000000+0130' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): 60:20230101T126000.000000000+0130"),
        COMPACT_INVALID_HOURS_UPPER(
                "20230101T240000.000000000+0130",
                IllegalArgumentException.class,
                "Text '20230101T240000.000000000+0130' could not be parsed: Invalid value for HourOfDay (valid values 0 - 23): 24:20230101T240000.000000000+0130"),
        COMPACT_INVALID_FRACTIONAL_MINUTES("20230101T1213.000000000+0130", IllegalArgumentException.class),
        COMPACT_INVALID_FRACTIONAL_HOURS("20230101T12.000000000+0130", IllegalArgumentException.class),
        COMPACT_INVALID_DAY_LOWER(
                "20230100T121314.123456789+0130",
                IllegalArgumentException.class,
                "Text '20230100T121314.123456789+0130' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 0:20230100T121314.123456789+0130"),
        COMPACT_INVALID_DAY_UPPER(
                "20230132T121314.123456789+0130",
                IllegalArgumentException.class,
                "Text '20230132T121314.123456789+0130' could not be parsed: Invalid value for DayOfMonth (valid values 1 - 28/31): 32:20230132T121314.123456789+0130"),
        COMPACT_INVALID_MONTH_LOWER(
                "20230001T121314.123456789+0130",
                IllegalArgumentException.class,
                "Text '20230001T121314.123456789+0130' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 0:20230001T121314.123456789+0130"),
        COMPACT_INVALID_MONTH_UPPER(
                "20231301T121314.123456789+0130",
                IllegalArgumentException.class,
                "Text '20231301T121314.123456789+0130' could not be parsed: Invalid value for MonthOfYear (valid values 1 - 12): 13:20231301T121314.123456789+0130"),

        // ------Compact width
        // checking width of individual fields is not possible in compact time format as there are no separators, so we
        // check the separated groups
        COMPACT_INVALID_WIDTH_SMALLER_OFFSET("20230101T000000.000000000+000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_OFFSET("20230101T000000.000000000+00000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_NANOSECONDS("20230101T000000.0000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_SMALLER_HHMMSS("20230101T00000.000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_HHMMSS("20230101T0000000.000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_SMALLER_YYYYMMDD("2023011T000000.000000000+0000", IllegalArgumentException.class),
        COMPACT_INVALID_WIDTH_BIGGER_YYYYMMDD("202301001T000000.000000000+0000", IllegalArgumentException.class),

        // ------Compact illegal formats
        COMPACT_INVALID_TRAILING_T("202302291T", IllegalArgumentException.class),
        COMPACT_INVALID_TIME_STRING("121314.123456789Z", IllegalArgumentException.class),

        // ------General invalid format
        INVALID_NOT_OPENEHR_DATE_STRING("31th January 2023, 1:30pm UTC", IllegalArgumentException.class),
        INVALID_NON_DATE_STRING("foo-bar", IllegalArgumentException.class),
        INVALID_EMPTY_STRING("", IllegalArgumentException.class);

        private final String input;
        private final TemporalAccessor expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        DateTimeParsingTestData(String input, TemporalAccessor expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        DateTimeParsingTestData(String input, Class<? extends Exception> expectedExceptionType) {
            this(input, expectedExceptionType, null);
        }

        DateTimeParsingTestData(
                String input, Class<? extends Exception> expectedExceptionType, String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(DateTimeParsingTestData.class)
    void parseDateTime(DateTimeParsingTestData data) {
        if (data.shouldThrowException) {
            Exception exception =
                    assertThrows(data.expectedExceptionType, () -> OpenEHRDateTimeParseUtils.parseDateTime(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeParseUtils.parseDateTime(data.input));
        }
    }
}
