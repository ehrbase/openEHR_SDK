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
package org.ehrbase.openehr.sdk.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PartialDateTimeTest {

    @Test
    void checkFieldOrder() {
        for (int i = 1; i < PartialDateTime.CHRONO_FIELDS.length - 1; i++) {
            assertThat(PartialDateTime.CHRONO_FIELDS[i].ordinal())
                    .isLessThan(PartialDateTime.CHRONO_FIELDS[i - 1].ordinal());
        }
    }

    @Test
    void ofYear() {
        var c = new PartialDateTime(Year.of(2024));

        assertThat(LocalDateTime.from(c)).isEqualTo(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertThat(LocalDate.from(c)).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(LocalTime.from(c)).isEqualTo(LocalTime.of(0, 0));
    }

    @Test
    void ofYearMonth() {
        var c = new PartialDateTime(YearMonth.of(2024, 3));

        assertThat(LocalDateTime.from(c)).isEqualTo(LocalDateTime.of(2024, 3, 1, 0, 0));
        assertThat(LocalDate.from(c)).isEqualTo(LocalDate.of(2024, 3, 1));
        assertThat(LocalTime.from(c)).isEqualTo(LocalTime.of(0, 0));
    }

    public static Stream<Arguments> fieldsMatch() {
        return Stream.of(LocalDateTime.of(2024, 3, 7, 1, 2, 3), YearMonth.of(2024, 3), Year.of(2024))
                .map(Arguments::of);
    }

    @ParameterizedTest
    @MethodSource
    void fieldsMatch(TemporalAccessor temporal) {
        assertFieldsMatch(new PartialDateTime(temporal), temporal);
    }

    private void assertFieldsMatch(PartialDateTime from, TemporalAccessor expected) {
        ChronoField[] relevantFields = new ChronoField[] {
            ChronoField.YEAR,
            ChronoField.MONTH_OF_YEAR,
            ChronoField.DAY_OF_MONTH,
            ChronoField.HOUR_OF_DAY,
            ChronoField.MINUTE_OF_HOUR,
            ChronoField.SECOND_OF_MINUTE,
            ChronoField.NANO_OF_SECOND,
            ChronoField.OFFSET_SECONDS
        };

        for (ChronoField field : relevantFields) {
            if (expected.isSupported(field)) {
                assertThat(from.get(field)).isEqualTo(expected.get(field));
            } else {
                assertThat(from.isSupported(field)).isFalse();
            }
        }
    }

    @Test
    void to_string() {
        YearMonth yearMonth = YearMonth.of(2024, 3);
        assertThat(new PartialDateTime(yearMonth)).hasToString(yearMonth.toString());

        Year year = Year.of(2024);
        assertThat(new PartialDateTime(year)).hasToString(year.toString());
    }

    @Test
    void dvDateTimeMagnitude() {
        PartialDateTime partialDate = new PartialDateTime(YearMonth.of(2024, 3));
        DvDateTime d = new DvDateTime(partialDate);
        assertThat(d.getMagnitude())
                .isGreaterThan(DvDateTime.SECONDS_BETWEEN_0001_AND_1970 + 54 * 365 * 24 * 3600)
                .isLessThan(DvDateTime.SECONDS_BETWEEN_0001_AND_1970 + 55 * 365 * 24 * 3600);
    }
}
