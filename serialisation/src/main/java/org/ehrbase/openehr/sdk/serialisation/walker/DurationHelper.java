/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.walker;

import java.io.Serializable;
import java.time.Duration;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateComparisonSymbol;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInput;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateInterval;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateValidation;
import org.threeten.extra.PeriodDuration;

public class DurationHelper {
    private DurationHelper() {
        // Utility class
    }

    public enum MIN_MAX {
        MIN(WebTemplateInterval::getMin, WebTemplateInterval::getMinOp),
        MAX(WebTemplateInterval::getMax, WebTemplateInterval::getMaxOp);

        private final Function<WebTemplateInterval, Serializable> valueFunc;

        private final Function<WebTemplateInterval, WebTemplateComparisonSymbol> opFunc;

        MIN_MAX(
                Function<WebTemplateInterval, Serializable> valueFunc,
                Function<WebTemplateInterval, WebTemplateComparisonSymbol> opFunc) {
            this.valueFunc = valueFunc;
            this.opFunc = opFunc;
        }

        public <T extends Serializable> T getValue(WebTemplateInterval<T> v) {
            return (T) valueFunc.apply(v);
        }

        public WebTemplateComparisonSymbol getOp(WebTemplateInterval v) {
            return opFunc.apply(v);
        }
    }

    /**
     * Returns the total validation Range
     *
     * @param node
     * @param minMax
     * @return
     */
    public static Optional<TemporalAmount> buildTotalRange(WebTemplateNode node, MIN_MAX minMax) {
        return Optional.ofNullable(node).map(WebTemplateNode::getInputs).stream()
                .flatMap(List::stream)
                .flatMap(input -> Optional.of(input)
                        .map(WebTemplateInput::getValidation)
                        .map(WebTemplateValidation::getRange)
                        .map(r -> minMax.getValue(r))
                        .filter(Integer.class::isInstance)
                        .map(value -> build(input, (Integer) value))
                        .stream())
                .reduce((a, b) -> PeriodDuration.from(a).plus(b));
    }

    /**
     * Returns the total validation {@link WebTemplateComparisonSymbol}
     *
     * @param node
     * @param minMax
     * @return
     */
    public static WebTemplateComparisonSymbol getTotalComparisonSymbol(WebTemplateNode node, MIN_MAX minMax) {
        Iterator<WebTemplateComparisonSymbol> symbols =
                Optional.ofNullable(node).map(WebTemplateNode::getInputs).stream()
                        .flatMap(List::stream)
                        .map(WebTemplateInput::getValidation)
                        .filter(Objects::nonNull)
                        .map(WebTemplateValidation::getRange)
                        .filter(Objects::nonNull)
                        .map(minMax::getOp)
                        .filter(Objects::nonNull)
                        .iterator();

        if (!symbols.hasNext()) {
            return null;
        }

        WebTemplateComparisonSymbol first = symbols.next();
        if (first == WebTemplateComparisonSymbol.GT || first == WebTemplateComparisonSymbol.LT) {
            return first;
        }
        while (symbols.hasNext()) {
            WebTemplateComparisonSymbol next = symbols.next();
            if (next == WebTemplateComparisonSymbol.GT || next == WebTemplateComparisonSymbol.LT) {
                return next;
            }
        }
        return first;
    }

    private static TemporalAmount build(WebTemplateInput input, Integer integer) {
        return switch (input.getSuffix()) {
            case "year" -> Period.ofYears(integer);
            case "month" -> Period.ofMonths(integer);
            case "day" -> Period.ofDays(integer);
            case "week" -> Period.ofWeeks(integer);
            case "hour" -> Duration.ofHours(integer);
            case "minute" -> Duration.ofMinutes(integer);
            case "second" -> Duration.ofSeconds(integer);
            default -> throw new IllegalArgumentException("Unsupported suffix: " + input.getSuffix());
        };
    }
}
