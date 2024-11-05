/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.openehr.sdk.serialisation.walker.DurationHelper;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateComparisonSymbol;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.threeten.extra.PeriodDuration;

/**
 * {@link ConstraintValidator} that validates a <code>DV_DURATION</code> object.
 *
 * @see com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration
 * @since 1.7
 */
@SuppressWarnings("unused")
public class DvDurationValidator implements ConstraintValidator<DvDuration> {

    /** {@inheritDoc} */
    @Override
    public Class<DvDuration> getAssociatedClass() {
        return DvDuration.class;
    }

    /** {@inheritDoc} */
    @Override
    public List<ConstraintViolation> validate(DvDuration dvDuration, WebTemplateNode node) {
        if (!WebTemplateValidationUtils.hasInputs(node)) {
            return List.of();
        }

        var duration = PeriodDuration.from(dvDuration.getValue());

        List<ConstraintViolation> result = List.of();
        for (DurationHelper.MIN_MAX m : DurationHelper.MIN_MAX.values()) {
            WebTemplateComparisonSymbol s = DurationHelper.getTotalComparisonSymbol(node, m);
            if (s == null) {
                continue;
            }
            ConstraintViolation violation = validate(
                    node, duration, s, DurationHelper.buildTotalRange(node, m).orElse(PeriodDuration.ZERO));
            if (violation != null) {
                result = ConstraintValidator.concat(result, List.of(violation));
            }
        }
        return result;
    }

    private ConstraintViolation validate(
            WebTemplateNode node, PeriodDuration duration, WebTemplateComparisonSymbol symbol, TemporalAmount range) {
        int durationCmp = new DvDuration(duration).compareTo(new DvDuration(range));
        boolean valid =
                switch (symbol) {
                    case GT_EQ -> durationCmp >= 0;
                    case GT -> durationCmp > 0;
                    case LT_EQ -> durationCmp <= 0;
                    case LT -> durationCmp < 0;
                };

        if (valid) {
            return null;
        } else {
            return new ConstraintViolation(
                    node.getAqlPath(),
                    String.format("The value %s must be %s %s", duration, symbol.getSymbol(), range));
        }
    }

    private Long getValue(PeriodDuration duration, String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit must not be null");
        }

        switch (unit) {
            case "year":
                return duration.get(ChronoUnit.YEARS);
            case "month":
                return duration.get(ChronoUnit.MONTHS);
            case "day":
                return duration.get(ChronoUnit.DAYS);
            case "week":
                return duration.get(ChronoUnit.DAYS) / 7;
            case "hour":
                return (long) duration.getDuration().toHoursPart();
            case "minute":
                return (long) duration.getDuration().toMinutesPart();
            case "second":
                return (long) duration.getDuration().toSecondsPart();
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }

    boolean isNegativ(PeriodDuration periodDuration) {
        return periodDuration.getPeriod().isNegative()
                || (periodDuration.isZero() && periodDuration.getDuration().isNegative());
    }
}
