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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoZonedDateTime;
import java.time.chrono.Chronology;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;

/**
 * Allows Year and YearMonth to behave like LocalDateTime
 */
public final class PartialDateTime implements ChronoLocalDateTime<LocalDate> {

    private final Temporal partial;
    private final LocalDateTime localDateTime;

    private PartialDateTime(Temporal value) {
        this.partial = value;

        int year = value.get(ChronoField.YEAR);
        int month = value.isSupported(ChronoField.MONTH_OF_YEAR)
                ? value.get(ChronoField.MONTH_OF_YEAR)
                : Month.JANUARY.getValue();
        int day = value.isSupported(ChronoField.DAY_OF_MONTH) ? value.get(ChronoField.DAY_OF_MONTH) : 1;
        this.localDateTime = LocalDateTime.of(LocalDate.of(year, month, day), LocalTime.MIDNIGHT);
    }

    public static PartialDateTime of(Year year) {
        return new PartialDateTime(year);
    }

    public static PartialDateTime of(YearMonth yearMonth) {
        return new PartialDateTime(yearMonth);
    }

    public static PartialDateTime from(TemporalAccessor temporal) {
        if (temporal.isSupported(ChronoField.MONTH_OF_YEAR)) {
            return of(YearMonth.from(temporal));
        } else if (temporal.isSupported(ChronoField.YEAR)) {
            return of(Year.from(temporal));
        } else {
            return null;
        }
    }

    @Override
    public Chronology getChronology() {
        return localDateTime.getChronology();
    }

    @Override
    public LocalDate toLocalDate() {
        return localDateTime.toLocalDate();
    }

    public LocalDateTime toLocalDateTime() {
        return localDateTime;
    }

    @Override
    public LocalTime toLocalTime() {
        return LocalTime.MIDNIGHT;
    }

    @Override
    public boolean isSupported(TemporalField field) {
        return partial.isSupported(field);
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        if (TemporalQueries.localDate() == query) {
            return (R) toLocalDate();
        }
        return ChronoLocalDateTime.super.query(query);
    }

    @Override
    public ChronoLocalDateTime<LocalDate> with(TemporalField field, long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChronoLocalDateTime<LocalDate> plus(long amountToAdd, TemporalUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ChronoZonedDateTime<LocalDate> atZone(ZoneId zone) {
        return localDateTime.atZone(zone);
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        return localDateTime.until(endExclusive, unit);
    }

    @Override
    public long getLong(TemporalField field) {
        return localDateTime.getLong(field);
    }

    @Override
    public String toString() {
        return partial.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PartialDateTime pd && partial.equals(pd.partial);
    }

    @Override
    public int hashCode() {
        return partial.hashCode();
    }
}
