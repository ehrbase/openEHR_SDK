/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.LogicalOperator;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition.ConditionLogicalOperatorSymbol;

public class LogicalOperatorCondition
        implements WhereCondition, LogicalOperator<ConditionLogicalOperatorSymbol, WhereCondition> {

    private ConditionLogicalOperatorSymbol symbol;
    private List<WhereCondition> values;

    @Override
    public ConditionLogicalOperatorSymbol getSymbol() {
        return this.symbol;
    }

    @Override
    public List<WhereCondition> getValues() {
        return this.values;
    }

    public void setSymbol(ConditionLogicalOperatorSymbol symbol) {
        this.symbol = symbol;
    }

    public void setValues(List<WhereCondition> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogicalOperatorCondition that = (LogicalOperatorCondition) o;
        return symbol == that.symbol && Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, values);
    }

    @Override
    public String toString() {
        return "LogicalOperatorCondition{" + "symbol=" + symbol + ", values=" + values + '}';
    }

    public enum ConditionLogicalOperatorSymbol {
        OR(4),
        AND(2);

        private final int precedence;

        ConditionLogicalOperatorSymbol(int precedence) {
            this.precedence = precedence;
        }

        public int getPrecedence() {
            return precedence;
        }
    }
}
