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

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;

/**
 * Allows Year and YearMonth to behave like LocalDate
 */
public final class PartialDate implements ChronoLocalDate {

    private final Temporal partial;
    private final LocalDate localDate;

    private PartialDate(Temporal value) {
        this.partial = value;

        int year = value.get(ChronoField.YEAR);
        int month = value.isSupported(ChronoField.MONTH_OF_YEAR)
                ? value.get(ChronoField.MONTH_OF_YEAR)
                : Month.JANUARY.getValue();
        int day = value.isSupported(ChronoField.DAY_OF_MONTH) ? value.get(ChronoField.DAY_OF_MONTH) : 1;
        this.localDate = LocalDate.of(year, month, day);
    }

    public static PartialDate of(Year year) {
        return new PartialDate(year);
    }

    public static PartialDate of(YearMonth yearMonth) {
        return new PartialDate(yearMonth);
    }

    @Override
    public Chronology getChronology() {
        return localDate.getChronology();
    }

    @Override
    public int lengthOfMonth() {
        return 0;
    }

    @Override
    public boolean isSupported(TemporalField field) {
        return localDate.isSupported(field);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        return localDate.until(endExclusive, unit);
    }

    @Override
    public ChronoPeriod until(ChronoLocalDate endDateExclusive) {
        return localDate.until(endDateExclusive);
    }

    @Override
    public long getLong(TemporalField field) {
        return localDate.getLong(field);
    }

    public static PartialDate from(TemporalAccessor temporal) {
        if (temporal.isSupported(ChronoField.MONTH_OF_YEAR)) {
            return of(YearMonth.from(temporal));
        } else if (temporal.isSupported(ChronoField.YEAR)) {
            return of(Year.from(temporal));
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return partial.toString();
    }

    public TemporalAccessor getPartial() {
        return partial;
    }

    public LocalDate toLocalDate() {
        return localDate;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartialDate pd && partial.equals(pd.partial);
    }

    @Override
    public int hashCode() {
        return partial.hashCode();
    }
}
