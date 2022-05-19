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
package org.ehrbase.serialisation.walker;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.ehrbase.webtemplate.model.WebTemplateComparisonSymbol;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.model.WebTemplateValidation;
import org.threeten.extra.PeriodDuration;

public class DurationHelper {
    private DurationHelper() {
        // Utility class
    }

    public enum MIN_MAX {
        MIN,
        MAX;
    }

    /**
     * Returns the total validation Range
     *
     * @param node
     * @param minMax
     * @return
     */
    public static Optional<TemporalAmount> buildTotalRange(WebTemplateNode node, MIN_MAX minMax) {

        List<TemporalAmount> temporalAmounts = Optional.ofNullable(node).map(WebTemplateNode::getInputs).stream()
                .flatMap(List::stream)
                .filter(i -> Optional.of(i)
                        .map(WebTemplateInput::getValidation)
                        .map(WebTemplateValidation::getRange)
                        .map(v -> MIN_MAX.MAX.equals(minMax) ? v.getMax() : v.getMin())
                        .map(Object::getClass)
                        .filter(Integer.class::isAssignableFrom)
                        .isPresent())
                .map(input -> build(
                        input,
                        MIN_MAX.MAX.equals(minMax)
                                ? ((Integer) input.getValidation().getRange().getMax())
                                : ((Integer) input.getValidation().getRange().getMin())))
                .collect(Collectors.toList());

        if (temporalAmounts.isEmpty()) {

            return Optional.empty();
        } else {

            PeriodDuration offset = PeriodDuration.ZERO;
            for (TemporalAmount amount : temporalAmounts) {

                offset = offset.plus(amount);
            }
            return Optional.of(offset);
        }
    }

    /**
     * Returns the total validation {@link WebTemplateComparisonSymbol}
     *
     * @param node
     * @param minMax
     * @return
     */
    public static Optional<WebTemplateComparisonSymbol> getTotalComparisonSymbol(WebTemplateNode node, MIN_MAX minMax) {

        List<WebTemplateComparisonSymbol> symbols = Optional.ofNullable(node).map(WebTemplateNode::getInputs).stream()
                .flatMap(List::stream)
                .map(WebTemplateInput::getValidation)
                .filter(Objects::nonNull)
                .map(WebTemplateValidation::getRange)
                .filter(Objects::nonNull)
                .map(v -> MIN_MAX.MAX.equals(minMax) ? v.getMaxOp() : v.getMinOp())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return symbols.stream()
                .filter(s -> List.of(WebTemplateComparisonSymbol.GT, WebTemplateComparisonSymbol.LT)
                        .contains(s))
                .findAny()
                .or(() -> symbols.stream().findAny());
    }

    private static TemporalAmount build(WebTemplateInput input, Integer integer) {

        String suffix = input.getSuffix();

        switch (suffix) {
            case "year":
                return Period.ofYears(integer);
            case "month":
                return Period.ofMonths(integer);
            case "day":
                return Period.ofDays(integer);
            case "week":
                return Period.ofWeeks(integer);
            case "hour":
                return Duration.ofHours(integer);
            case "minute":
                return Duration.ofMinutes(integer);
            case "second":
                return Duration.ofSeconds(integer);
            default:
                throw new IllegalArgumentException("Unsupported suffix: " + suffix);
        }
    }
}
