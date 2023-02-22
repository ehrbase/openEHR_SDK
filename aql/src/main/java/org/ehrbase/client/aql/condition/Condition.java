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
package org.ehrbase.client.aql.condition;

import static org.ehrbase.client.aql.condition.ComparisonOperator.fieldComparison;
import static org.ehrbase.client.aql.condition.ComparisonOperator.parameterComparison;
import static org.ehrbase.client.aql.condition.ComparisonOperator.valueComparison;

import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.LikeOperatorDto;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;

public interface Condition {

    default Condition and(Condition condition) {
        return and(this, condition);
    }

    default Condition or(Condition condition) {
        return or(this, condition);
    }

    default Condition not() {
        return not(this);
    }

    static <T> Condition equal(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.EQ.getSymbol(), value);
    }

    static <T> Condition equal(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.EQ.getSymbol(), parameter);
    }

    static <T> Condition equal(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.EQ.getSymbol(), compareField);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.NEQ.getSymbol(), value);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.NEQ.getSymbol(), parameter);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.NEQ.getSymbol(), compareField);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.GT_EQ.getSymbol(), value);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.GT_EQ.getSymbol(), parameter);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.GT_EQ.getSymbol(), compareField);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.GT.getSymbol(), value);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.GT.getSymbol(), parameter);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.GT.getSymbol(), compareField);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.LT_EQ.getSymbol(), value);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.LT_EQ.getSymbol(), parameter);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.LT_EQ.getSymbol(), compareField);
    }

    static <T> Condition lessThan(SelectAqlField<T> field, T value) {
        return valueComparison(field, ConditionComparisonOperatorSymbol.LT.getSymbol(), value);
    }

    static <T> Condition lessThCan(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, ConditionComparisonOperatorSymbol.LT.getSymbol(), parameter);
    }

    static <T> Condition lessThan(SelectAqlField<T> field, SelectAqlField<T> compareField) {
        return fieldComparison(field, ConditionComparisonOperatorSymbol.LT.getSymbol(), compareField);
    }

    static <T> Condition matches(SelectAqlField<T> field, T... value) {
        return new Matches<>(field, value);
    }

    @SafeVarargs
    static <T> Condition matches(SelectAqlField<T> field, Parameter<T>... parameter) {
        return new Matches<>(field, parameter);
    }

    static Condition and(Condition condition1, Condition condition2) {
        return new And(condition1, condition2);
    }

    static Condition or(Condition condition1, Condition condition2) {
        return new Or(condition1, condition2);
    }

    static Condition not(Condition condition) {

        return new Not(condition);
    }

    static Condition exists(SelectAqlField<?> field) {
        return new Exists(field);
    }

    static <T> Condition like(SelectAqlField<T> field, T value) {
        return valueComparison(field, LikeOperatorDto.SYMBOL, value);
    }

    static <T> Condition like(SelectAqlField<T> field, Parameter<T> parameter) {
        return parameterComparison(field, LikeOperatorDto.SYMBOL, parameter);
    }

    String buildAql(Containment ehrContainment);
}
