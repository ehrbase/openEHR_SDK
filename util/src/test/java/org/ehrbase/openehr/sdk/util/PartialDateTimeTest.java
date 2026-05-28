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
import org.junit.jupiter.api.Test;

class PartialDateTimeTest {

    @Test
    void ofYear() {
        var c = PartialDateTime.of(Year.of(2024));

        assertThat(LocalDateTime.from(c)).isEqualTo(LocalDateTime.of(2024, 1, 1, 0, 0));
        assertThat(LocalDate.from(c)).isEqualTo(LocalDate.of(2024, 1, 1));
        assertThat(LocalTime.from(c)).isEqualTo(LocalTime.of(0, 0));
    }

    @Test
    void ofYearMonth() {
        var c = PartialDateTime.of(YearMonth.of(2024, 3));

        assertThat(LocalDateTime.from(c)).isEqualTo(LocalDateTime.of(2024, 3, 1, 0, 0));
        assertThat(LocalDate.from(c)).isEqualTo(LocalDate.of(2024, 3, 1));
        assertThat(LocalTime.from(c)).isEqualTo(LocalTime.of(0, 0));
    }

    @Test
    void from() {
        assertThat(PartialDateTime.from(LocalDateTime.of(2024, 3, 7, 1, 2, 3)))
                .isNotNull()
                .extracting(PartialDateTime::getPartial)
                .isEqualTo(YearMonth.of(2024, 3));
        assertThat(PartialDateTime.from(YearMonth.of(2024, 3)))
                .isNotNull()
                .extracting(PartialDateTime::getPartial)
                .isEqualTo(YearMonth.of(2024, 3));
        assertThat(PartialDateTime.from(Year.of(2024)))
                .isNotNull()
                .extracting(PartialDateTime::getPartial)
                .isEqualTo(Year.of(2024));
    }

    @Test
    void getPartial() {
        YearMonth yearMonth = YearMonth.of(2024, 3);
        var c = PartialDateTime.of(yearMonth);

        assertThat(c.getPartial()).isSameAs(yearMonth);
    }

    @Test
    void to_string() {
        YearMonth yearMonth = YearMonth.of(2024, 3);
        assertThat(PartialDateTime.of(yearMonth)).hasToString(yearMonth.toString());

        Year year = Year.of(2024);
        assertThat(PartialDateTime.of(year)).hasToString(year.toString());
    }

    @Test
    void toLocalTime() {
        PartialDateTime partialDate = PartialDateTime.of(YearMonth.of(2024, 3));
        assertThat(partialDate.toLocalTime()).isEqualTo(LocalTime.MIDNIGHT);
    }

    @Test
    void toLocalDate() {
        PartialDateTime partialDate = PartialDateTime.of(YearMonth.of(2024, 3));
        assertThat(partialDate.toLocalDate()).isEqualTo(LocalDate.of(2024, 3, 1));
    }

    @Test
    void toLocalDateTime() {
        PartialDateTime partialDate = PartialDateTime.of(YearMonth.of(2024, 3));
        assertThat(partialDate.toLocalDateTime()).isEqualTo(LocalDateTime.of(2024, 3, 1, 0, 0));
    }

    @Test
    void dvDateTimeMagnitude() {
        PartialDateTime partialDate = PartialDateTime.of(YearMonth.of(2024, 3));
        DvDateTime d = new DvDateTime(partialDate);
        assertThat(d.getMagnitude())
                .isGreaterThan(DvDateTime.SECONDS_BETWEEN_0001_AND_1970 + 54 * 365 * 24 * 3600)
                .isLessThan(DvDateTime.SECONDS_BETWEEN_0001_AND_1970 + 55 * 365 * 24 * 3600);
    }
}
