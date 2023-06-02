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
package org.ehrbase.aql.dto.path.predicate;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;

/**
 * @author Stefan Spiska
 */
public class PredicateLogicalOrOperation
        implements PredicateDto, LogicalOperatorDto<PredicateLogicalOperatorSymbol, SimplePredicateDto>, Serializable {

    private final PredicateLogicalOperatorSymbol symbol = PredicateLogicalOperatorSymbol.OR;
    private List<SimplePredicateDto> values;

    public PredicateLogicalOrOperation(SimplePredicateDto... values) {
        this.values = List.of(values);
    }

    PredicateLogicalOrOperation() {
        this.values = List.of();
    }

    @Override
    public PredicateLogicalOperatorSymbol getSymbol() {
        return symbol;
    }

    @Override
    public List<SimplePredicateDto> getValues() {
        return values;
    }

    @Override
    public PredicateLogicalOrOperation addValues(Stream<SimplePredicateDto> valuesStream) {
        SimplePredicateDto[] newValues =
                Stream.concat(values.stream(), valuesStream).toArray(SimplePredicateDto[]::new);
        return new PredicateLogicalOrOperation(newValues);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PredicateLogicalOrOperation that = (PredicateLogicalOrOperation) o;
        return symbol == that.symbol && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, values);
    }

    @Override
    public String toString() {
        return "PredicateLogicalOrOperation{" + "symbol=" + symbol + ", values=" + values + '}';
    }
}
