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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class OpenEHRDateTimeParseUtilsTest {

    @Test
    void parseDateExtended() {
        assertEquals(LocalDate.of(2023, 1, 21), OpenEHRDateTimeParseUtils.parseDate("2023-01-21"));
        assertEquals(YearMonth.of(2023, 1), OpenEHRDateTimeParseUtils.parseDate("2023-01"));
        assertEquals(Year.of(2023), OpenEHRDateTimeParseUtils.parseDate("2023"));

        // Field ranges checked
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-01-00"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-01-32"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-00-01"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-13-01"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-00"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-13"));
        // Leap year check
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-02-29"));

        // Field width checked
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-01-001"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("2023-001-01"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("02023-01-01"));

        // DateTime and Time strings should throw an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDate("2023-01-30T12:13:14.123456789+01:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("12:13:14.123456789+01:00"));
    }

    @Test
    void parseDateCompact() {
        assertEquals(LocalDate.of(2023, 1, 21), OpenEHRDateTimeParseUtils.parseDate("20230121"));
        assertEquals(YearMonth.of(2023, 1), OpenEHRDateTimeParseUtils.parseDate("202301"));
        assertEquals(Year.of(2023), OpenEHRDateTimeParseUtils.parseDate("2023"));

        // Field ranges checked
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("20230100"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("20230132"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("20230001"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("20231301"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("202300"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("202313"));
        // Leap year check
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("20230229"));

        // Field width checked
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("202301001"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("202300101"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("020230101"));

        // DateTime and Time strings should throw an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDate("20230130T121314.123456789+0100"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDate("121314.123456789+0100"));
    }

    @Test
    void parseTimeExtended() {
        // With offset
        assertEquals(
                OffsetTime.of(13, 14, 15, 987654321, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13:14:15.987654321+01:30"));
        assertEquals(
                OffsetTime.of(13, 14, 15, 987000000, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13:14:15.987+01:30"));
        assertEquals(
                OffsetTime.of(13, 14, 15, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13:14:15+01:30"));
        assertEquals(
                OffsetTime.of(13, 14, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13:14+01:30"));
        assertEquals(
                OffsetTime.of(13, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13+01:30"));
        // Without offset
        assertEquals(LocalTime.of(13, 14, 15, 987654321), OpenEHRDateTimeParseUtils.parseTime("13:14:15.987654321"));
        assertEquals(LocalTime.of(13, 14, 15, 987000000), OpenEHRDateTimeParseUtils.parseTime("13:14:15.987"));
        assertEquals(LocalTime.of(13, 14, 15, 0), OpenEHRDateTimeParseUtils.parseTime("13:14:15"));
        assertEquals(LocalTime.of(13, 14, 0, 0), OpenEHRDateTimeParseUtils.parseTime("13:14"));
        assertEquals(LocalTime.of(13, 0, 0, 0), OpenEHRDateTimeParseUtils.parseTime("13"));

        // Field ranges checked
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:14:15.000000000-18:01"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:14:15.000000000+18:01"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:14:60.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:60:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("24:00:00.000000000+01:00"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:14:60.000000000"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("13:60:00.000000000"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("24:00:00.000000000"));

        // Field width checked
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("00:00:00.000000000+00:000"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("00:00:00.000000000+000:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("00:00:00.0000000000+00:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("00:00:000.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("00:000:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("000:00:00.000000000+01:00"));

        // DateTime and Date strings should throw an exception
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("2023-01-30"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseTime("2023-01-30T12:13:14.123456789+01:00"));
    }

    @Test
    void parseTimeCompact() {
        // With offset
        assertEquals(
                OffsetTime.of(13, 14, 15, 987654321, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("131415.987654321+0130"));
        assertEquals(
                OffsetTime.of(13, 14, 15, 987000000, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("131415.987+0130"));
        assertEquals(
                OffsetTime.of(13, 14, 15, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("131415+0130"));
        assertEquals(
                OffsetTime.of(13, 14, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("1314+0130"));
        assertEquals(
                OffsetTime.of(13, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseTime("13+0130"));
        // Without offset
        assertEquals(LocalTime.of(13, 14, 15, 987654321), OpenEHRDateTimeParseUtils.parseTime("131415.987654321"));
        assertEquals(LocalTime.of(13, 14, 15, 987000000), OpenEHRDateTimeParseUtils.parseTime("131415.987"));
        assertEquals(LocalTime.of(13, 14, 15, 0), OpenEHRDateTimeParseUtils.parseTime("131415"));
        assertEquals(LocalTime.of(13, 14, 0, 0), OpenEHRDateTimeParseUtils.parseTime("1314"));
        assertEquals(LocalTime.of(13, 0, 0, 0), OpenEHRDateTimeParseUtils.parseTime("13"));

        // Field ranges checked
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("131415.000000000-1801"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("131415.000000000+1801"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("131460.000000000+0100"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("136000.000000000+0100"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("240000.000000000+0100"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("131460.000000000"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("136000.000000000"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("240000.000000000"));

        // Field width checked
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("000000.000000000+00000"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("000000.0000000000+0000"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("0000000.000000000+0000"));

        // DateTime and Date strings should throw an exception
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseTime("20230130"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseTime("20230130T121314.123456789+0100"));
    }

    @Test
    void parseDateTimeExtended() {
        // With offset
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 987654321, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.987654321+01:30"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 987000000, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.987+01:30"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15+01:30"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14+01:30"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13+01:30"));
        // Without offset
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 987654321),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.987654321"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 987000000),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.987"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 0),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 0, 0),
                OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 0, 0, 0), OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13"));
        // Without time
        assertEquals(LocalDate.of(2023, 1, 21), OpenEHRDateTimeParseUtils.parseDate("2023-01-21"));
        assertEquals(YearMonth.of(2023, 1), OpenEHRDateTimeParseUtils.parseDate("2023-01"));
        assertEquals(Year.of(2023), OpenEHRDateTimeParseUtils.parseDate("2023"));

        // Field ranges checked
        // Note: This does not cover all combinations of invalid values with every precision
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.000000000-18:01"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:15.000000000+18:01"));
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:14:60"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T13:60"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T24"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-00"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-32"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-00"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-13"));

        // Leap year checked
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-02-29T00:00:00.000000000"));

        // Field width checked
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T00:00:00.000000000+00:000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T00:00:00.000000000+000:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T00:00:00.0000000000+00:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T00:00:000.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T00:000:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T000:00:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-030T00:00:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-001-30T00:00:00.000000000+01:00"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230-01-30T00:00:00.000000000+01:00"));

        // Time and Date with trailing T should throw an exception
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("13:14:15.987654321+01:30"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("2023-01-30T"));
    }

    @Test
    void parseDateTimeCompact() {
        // With offset
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 987654321, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.987654321+0130"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 987000000, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.987+0130"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 15, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415+0130"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 14, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T1314+0130"));
        assertEquals(
                OffsetDateTime.of(2023, 1, 30, 13, 0, 0, 0, ZoneOffset.ofHoursMinutes(1, 30)),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T13+0130"));
        // Without offset
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 987654321),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.987654321"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 987000000),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.987"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 15, 0),
                OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 14, 0, 0), OpenEHRDateTimeParseUtils.parseDateTime("20230130T1314"));
        assertEquals(
                LocalDateTime.of(2023, 1, 30, 13, 0, 0, 0), OpenEHRDateTimeParseUtils.parseDateTime("20230130T13"));
        // Without time
        assertEquals(LocalDate.of(2023, 1, 21), OpenEHRDateTimeParseUtils.parseDate("20230121"));
        assertEquals(YearMonth.of(2023, 1), OpenEHRDateTimeParseUtils.parseDate("202301"));
        assertEquals(Year.of(2023), OpenEHRDateTimeParseUtils.parseDate("2023"));

        // Field ranges checked
        // Note This does not cover all combinations of invalid values with every precision
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.000000000-1801"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T131415.000000000+1801"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T131460"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T1360"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T24"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230100"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230132"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("202300"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("202313"));

        // Leap year checked
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230229T000000.000000000"));

        // Field width checked
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230101T000000.000000000+00000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230101T000000.0000000000+0000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("20230101T0000000.000000000+0000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("202301010T000000.000000000+0000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("202301001T000000.000000000+0000"));
        assertThrows(
                IllegalArgumentException.class,
                () -> OpenEHRDateTimeParseUtils.parseDateTime("202300101T000000.000000000+0000"));

        // Time and Date with trailing T should throw an exception
        assertThrows(
                IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("131415.987654321+0130"));
        assertThrows(IllegalArgumentException.class, () -> OpenEHRDateTimeParseUtils.parseDateTime("20230130T"));
    }
}
