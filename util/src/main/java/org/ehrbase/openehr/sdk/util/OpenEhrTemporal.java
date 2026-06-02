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

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.chrono.IsoChronology;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalQueries;
import java.time.temporal.TemporalQuery;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;

/**
 * Allows Year and YearMonth to behave like LocalDateTime
 */
public final class OpenEhrTemporal implements Temporal {

    static final ChronoField[] CHRONO_FIELDS = new ChronoField[] {
        ChronoField.YEAR,
        ChronoField.MONTH_OF_YEAR,
        ChronoField.DAY_OF_MONTH,
        //
        ChronoField.HOUR_OF_DAY,
        ChronoField.MINUTE_OF_HOUR,
        ChronoField.SECOND_OF_MINUTE,
        ChronoField.NANO_OF_SECOND,
        //
        ChronoField.OFFSET_SECONDS
    };

    private static final int OFFSET_POS = 7;

    private final Integer[] fields = new Integer[CHRONO_FIELDS.length];

    private final ChronoField resolution;

    public OpenEhrTemporal(TemporalAccessor value) {
        this(value, ChronoField.NANO_OF_SECOND);
    }

    OpenEhrTemporal(TemporalAccessor value, ChronoField maxResolution) {
        boolean dateBased = value.isSupported(ChronoField.YEAR);

        if (!dateBased
                && (value.isSupported(ChronoField.MONTH_OF_YEAR)
                        || value.isSupported(ChronoField.DAY_OF_MONTH)
                        || !value.isSupported(ChronoField.HOUR_OF_DAY))) {
            throw new IllegalArgumentException("Unsupported date format: " + value);
        }

        ChronoField res = dateBased ? ChronoField.YEAR : ChronoField.HOUR_OF_DAY;

        int i = dateBased ? 0 : 3;
        fields[i++] = value.get(res);

        for (; i < OFFSET_POS; i++) {
            ChronoField f = CHRONO_FIELDS[i];
            if (f.ordinal() >= maxResolution.ordinal() && value.isSupported(f)) {
                res = f;
                fields[i] = value.get(f);
            } else {
                break;
            }
        }
        resolution = res;

        if (fields[0] != null && fields[2] != null) {
            checkValidDate();
        }

        if (value.isSupported(ChronoField.OFFSET_SECONDS)) {
            if (fields[3] == null) {
                throw new IllegalArgumentException("offset is not supported");
            }
            fields[OFFSET_POS] = value.get(ChronoField.OFFSET_SECONDS);
        }
    }

    /**
     * @see LocalDate#create(int, int, int)
     */
    private void checkValidDate() {
        int year = fields[0];
        int month = fields[1];
        int dayOfMonth = fields[2];
        if (dayOfMonth > 28) {
            int dom =
                    switch (month) {
                        case 2 -> (IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
                        case 4, 6, 9, 11 -> 30;
                        default -> 31;
                    };
            if (dayOfMonth > dom) {
                if (dayOfMonth == 29) {
                    throw new DateTimeException("Invalid date 'February 29' as '" + year + "' is not a leap year");
                } else {
                    throw new DateTimeException(
                            "Invalid date '" + Month.of(month).name() + " " + dayOfMonth + "'");
                }
            }
        }
    }

    @Override
    public boolean isSupported(TemporalField field) {
        int i = ArrayUtils.indexOf(CHRONO_FIELDS, field);
        if (i < 0) {
            return false;
        }
        return fields[i] != null;
    }

    @Override
    public <R> R query(TemporalQuery<R> query) {
        Object result;
        if (TemporalQueries.localDate() == query) {
            if (fields[0] == null) {
                // time only
                result = null;
            } else {
                result = LocalDate.of(
                        fields[0], ObjectUtils.firstNonNull(fields[1], 1), ObjectUtils.firstNonNull(fields[2], 1));
            }
        } else if (TemporalQueries.localTime() == query) {
            result = LocalTime.of(
                    ObjectUtils.firstNonNull(fields[3], 0),
                    ObjectUtils.firstNonNull(fields[4], 0),
                    ObjectUtils.firstNonNull(fields[5], 0),
                    ObjectUtils.firstNonNull(fields[6], 0));
        } else if (TemporalQueries.zone() == query || TemporalQueries.zoneId() == query) {
            result = fields[7] == null ? null : ZoneOffset.ofTotalSeconds(fields[7]);
        } else if (TemporalQueries.chronology() == query) {
            result = IsoChronology.INSTANCE;
        } else if (TemporalQueries.offset() == query) {
            result = fields[7] == null ? null : ZoneOffset.ofTotalSeconds(fields[7]);
        } else if (TemporalQueries.precision() == query) {
            result = resolution.getBaseUnit();
        } else {
            result = null;
        }
        return (R) result;
    }

    @Override
    public int get(TemporalField field) {
        int i = ArrayUtils.indexOf(CHRONO_FIELDS, field);
        if (i < 0) {
            throw new IllegalArgumentException(field + " is not supported");
        }
        return fields[i];
    }

    @Override
    public long getLong(TemporalField field) {
        return get(field);
    }

    @Override
    public String toString() {
        return fields[0] == null
                ? OpenEHRDateTimeSerializationUtils.formatTime(this)
                : OpenEHRDateTimeSerializationUtils.formatDateTime(this);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OpenEhrTemporal pd && Arrays.equals(fields, pd.fields);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(fields);
    }

    @Override
    public boolean isSupported(TemporalUnit unit) {
        return false;
    }

    @Override
    public Temporal with(TemporalField field, long newValue) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Temporal plus(long amountToAdd, TemporalUnit unit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long until(Temporal endExclusive, TemporalUnit unit) {
        throw new UnsupportedOperationException();
    }
}
