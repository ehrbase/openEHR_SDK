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
package org.ehrbase.openehr.sdk.aql.webtemplatepath.predicate;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.ehrbase.openehr.sdk.aql.dto.LogicalOperator;

/**
 * @author Stefan Spiska
 */
public final class PredicateLogicalAndOperation
        implements LogicalOperator<PredicateLogicalOperatorSymbol, PredicateComparisonOperator>,
                DisjunctablePredicate,
                Serializable {

    private final PredicateLogicalOperatorSymbol symbol = PredicateLogicalOperatorSymbol.AND;
    private List<PredicateComparisonOperator> values;

    public PredicateLogicalAndOperation() {
        this.values = List.of();
    }

    public PredicateLogicalAndOperation(PredicateComparisonOperator... values) {
        this.values = List.of(values);
    }

    @Override
    public PredicateLogicalOperatorSymbol getSymbol() {
        return symbol;
    }

    @Override
    public List<PredicateComparisonOperator> getValues() {
        return values;
    }

    @Override
    public PredicateLogicalAndOperation addValues(Stream<PredicateComparisonOperator> valuesStream) {
        PredicateComparisonOperator[] newValues =
                Stream.concat(values.stream(), valuesStream).toArray(PredicateComparisonOperator[]::new);
        return new PredicateLogicalAndOperation(newValues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PredicateLogicalAndOperation that = (PredicateLogicalAndOperation) o;
        return symbol == that.symbol && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, values);
    }

    @Override
    public String toString() {
        return "PredicateLogicalAndOperation{" + "symbol=" + symbol + ", values=" + values + '}';
    }
}
