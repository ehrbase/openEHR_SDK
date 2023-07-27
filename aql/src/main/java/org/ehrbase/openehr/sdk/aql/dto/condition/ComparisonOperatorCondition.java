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

import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.ComparisonLeftOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Operand;

public class ComparisonOperatorCondition implements WhereCondition {

    private ComparisonLeftOperand statement;
    private ComparisonOperatorSymbol symbol;
    private Operand value;

    public ComparisonLeftOperand getStatement() {
        return this.statement;
    }

    public ComparisonOperatorSymbol getSymbol() {
        return this.symbol;
    }

    public Operand getValue() {
        return this.value;
    }

    public void setStatement(ComparisonLeftOperand statement) {
        this.statement = statement;
    }

    public void setSymbol(ComparisonOperatorSymbol symbol) {
        this.symbol = symbol;
    }

    public void setValue(Operand value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComparisonOperatorCondition that = (ComparisonOperatorCondition) o;
        return Objects.equals(statement, that.statement) && symbol == that.symbol && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, symbol, value);
    }

    @Override
    public String toString() {
        return "ComparisonOperatorCondition{" + "statement="
                + statement + ", symbol="
                + symbol + ", value="
                + value + '}';
    }
}
