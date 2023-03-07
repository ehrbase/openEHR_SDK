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
import org.ehrbase.serialisation.exception.MarshalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class OpenEHRDateTimeSerializationUtilsTest {

    enum DateTestData {
        NULL_INPUT(null, null),

        // ------valid precisions
        PRECISION_DAY(LocalDate.of(2023, 1, 1), "2023-01-01"),
        PRECISION_MONTH(YearMonth.of(2023, 1), "2023-01"),
        PRECISION_YEAR(Year.of(2023), "2023"),

        // -------invalid precision
        MIN_PRECISION_NOT_PRESENT(
                LocalTime.of(0, 0),
                MarshalException.class,
                "The given TemporalAccessor does not support the minimal resolution defined by openEHR: YEAR");

        private final TemporalAccessor input;
        private final String expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        DateTestData(TemporalAccessor input, String expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        DateTestData(
                TemporalAccessor input,
                Class<? extends Exception> expectedExceptionType,
                String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(DateTestData.class)
    void formatDate(DateTestData data) {
        if (data.shouldThrowException) {
            Exception exception = assertThrows(
                    data.expectedExceptionType, () -> OpenEHRDateTimeSerializationUtils.formatDate(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeSerializationUtils.formatDate(data.input));
        }
    }

    enum TimeTestData {
        NULL_INPUT(null, null),

        // ------Extended valid format and values
        PRECISION_NANOSECOND_ZULU_OFFSET(
                OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0)), "12:13:14.123456789Z"),
        PRECISION_NANOSECOND_WITH_OFFSET(
                OffsetTime.of(12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30)), "12:13:14.123456789+01:30"),
        PRECISION_MILLISECOND_WITH_OFFSET(
                OffsetTime.of(12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30)), "12:13:14.123+01:30"),
        PRECISION_SECOND_WITH_OFFSET(OffsetTime.of(12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30)), "12:13:14+01:30"),
        PRECISION_MINUTE_WITH_OFFSET(OffsetTime.of(12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)), "12:13:00+01:30"),
        PRECISION_HOUR_WITH_OFFSET(OffsetTime.of(12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)), "12:00:00+01:30"),
        PRECISION_NANOSECOND(LocalTime.of(12, 13, 14, 123456789), "12:13:14.123456789"),
        PRECISION_MILLISECOND(LocalTime.of(12, 13, 14, 123000000), "12:13:14.123"),
        PRECISION_SECOND(LocalTime.of(12, 13, 14), "12:13:14"),
        PRECISION_MINUTE(LocalTime.of(12, 13, 0), "12:13:00"),
        PRECISION_HOUR(LocalTime.of(12, 0, 0), "12:00:00"),

        // -------invalid precision
        MIN_PRECISION_NOT_PRESENT(
                Year.of(0),
                MarshalException.class,
                "The given TemporalAccessor does not support the minimal resolution defined by openEHR: HOUR_OF_DAY");

        private final TemporalAccessor input;
        private final String expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        TimeTestData(TemporalAccessor input, String expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        TimeTestData(
                TemporalAccessor input,
                Class<? extends Exception> expectedExceptionType,
                String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(TimeTestData.class)
    void formatTime(TimeTestData data) {
        if (data.shouldThrowException) {
            Exception exception = assertThrows(
                    data.expectedExceptionType, () -> OpenEHRDateTimeSerializationUtils.formatTime(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeSerializationUtils.formatTime(data.input));
        }
    }

    enum DateTimeTestData {
        NULL_INPUT(null, null),

        // Extended valid format and values
        PRECISION_NANOSECOND_ZULU_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(0, 0)),
                "2023-01-01T12:13:14.123456789Z"),
        PRECISION_NANOSECOND_WITH_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123456789, ZoneOffset.ofHoursMinutes(1, 30)),
                "2023-01-01T12:13:14.123456789+01:30"),
        PRECISION_MILLISECOND_WITH_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 123000000, ZoneOffset.ofHoursMinutes(1, 30)),
                "2023-01-01T12:13:14.123+01:30"),
        PRECISION_SECOND_WITH_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 13, 14, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                "2023-01-01T12:13:14+01:30"),
        PRECISION_MINUTE_WITH_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 13, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                "2023-01-01T12:13:00+01:30"),
        PRECISION_HOUR_WITH_OFFSET(
                OffsetDateTime.of(2023, 1, 1, 12, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                "2023-01-01T12:00:00+01:30"),
        PRECISION_NANOSECOND(LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123456789), "2023-01-01T12:13:14.123456789"),
        PRECISION_MILLISECOND(LocalDateTime.of(2023, 1, 1, 12, 13, 14, 123000000), "2023-01-01T12:13:14.123"),
        PRECISION_SECOND(LocalDateTime.of(2023, 1, 1, 12, 13, 14), "2023-01-01T12:13:14"),
        PRECISION_MINUTE(LocalDateTime.of(2023, 1, 1, 12, 13, 0), "2023-01-01T12:13:00"),
        PRECISION_HOUR(LocalDateTime.of(2023, 1, 1, 12, 0, 0), "2023-01-01T12:00:00"),
        PRECISION_DAY(LocalDate.of(2023, 1, 1), "2023-01-01"),
        PRECISION_MONTH(YearMonth.of(2023, 1), "2023-01"),
        PRECISION_YEAR(Year.of(2023), "2023"),

        // -------invalid precision
        MIN_PRECISION_NOT_PRESENT(
                LocalTime.of(0, 0),
                MarshalException.class,
                "The given TemporalAccessor does not support the minimal resolution defined by openEHR: YEAR");

        private final TemporalAccessor input;
        private final String expected;
        private final boolean shouldThrowException;
        private final Class<? extends Exception> expectedExceptionType;
        private final String expectedExceptionMessage;

        DateTimeTestData(TemporalAccessor input, String expected) {
            this.input = input;
            this.expected = expected;
            this.shouldThrowException = false;
            this.expectedExceptionType = null;
            this.expectedExceptionMessage = null;
        }

        DateTimeTestData(
                TemporalAccessor input,
                Class<? extends Exception> expectedExceptionType,
                String expectedExceptionMessage) {
            this.input = input;
            this.expected = null;
            this.shouldThrowException = true;
            this.expectedExceptionType = expectedExceptionType;
            this.expectedExceptionMessage = expectedExceptionMessage;
        }
    }

    @ParameterizedTest
    @EnumSource(DateTimeTestData.class)
    void formatDateTime(DateTimeTestData data) {
        if (data.shouldThrowException) {
            Exception exception = assertThrows(
                    data.expectedExceptionType, () -> OpenEHRDateTimeSerializationUtils.formatDateTime(data.input));
            if (data.expectedExceptionMessage != null) {
                Assertions.assertEquals(data.expectedExceptionMessage, exception.getMessage());
            }
        } else {
            Assertions.assertEquals(data.expected, OpenEHRDateTimeSerializationUtils.formatDateTime(data.input));
        }
    }
}
