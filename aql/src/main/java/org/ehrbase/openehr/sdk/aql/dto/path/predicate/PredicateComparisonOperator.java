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
package org.ehrbase.openehr.sdk.aql.dto.path.predicate;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Primitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.util.exception.SdkException;

public final class PredicateComparisonOperator implements DisjunctableAqlPredicate, Serializable {

    private String statement;
    private ComparisonOperatorSymbol symbol;
    private PathPredicateOperand value;

    public PredicateComparisonOperator(PredicateComparisonOperator other) {
        this.statement = other.statement;
        this.symbol = other.symbol;

        if (other.value instanceof QueryParameter) {
            this.value = new QueryParameter((QueryParameter) other.value);
        } else {
            try {
                this.value = other.getValue()
                        .getClass()
                        .getConstructor(Primitive.class)
                        .newInstance(((Primitive) other.value).getValue());
            } catch (InstantiationException
                    | NoSuchMethodException
                    | InvocationTargetException
                    | IllegalAccessException e) {
                throw new SdkException(e.getMessage());
            }
        }
    }

    public PredicateComparisonOperator(String statement, ComparisonOperatorSymbol symbol, PathPredicateOperand value) {
        this.statement = statement;
        this.symbol = symbol;
        this.value = value;
    }

    PredicateComparisonOperator() {}

    public String getStatement() {
        return this.statement;
    }

    public ComparisonOperatorSymbol getSymbol() {
        return this.symbol;
    }

    public PathPredicateOperand getValue() {
        return this.value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof PredicateComparisonOperator)) return false;
        final PredicateComparisonOperator other = (PredicateComparisonOperator) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$statement = this.getStatement();
        final Object other$statement = other.getStatement();
        if (this$statement == null ? other$statement != null : !this$statement.equals(other$statement)) return false;
        final Object this$symbol = this.getSymbol();
        final Object other$symbol = other.getSymbol();
        if (this$symbol == null ? other$symbol != null : !this$symbol.equals(other$symbol)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof PredicateComparisonOperator;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $statement = this.getStatement();
        result = result * PRIME + ($statement == null ? 43 : $statement.hashCode());
        final Object $symbol = this.getSymbol();
        result = result * PRIME + ($symbol == null ? 43 : $symbol.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "ComparisonOperatorCondition(statement="
                + this.getStatement()
                + ", symbol="
                + this.getSymbol()
                + ", value="
                + this.getValue()
                + ")";
    }
}
