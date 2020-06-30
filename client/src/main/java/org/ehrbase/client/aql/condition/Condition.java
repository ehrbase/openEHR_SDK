/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.condition;

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
        return new Equal<T>(field, value);
    }

    static <T> Condition equal(SelectAqlField<T> field, Parameter<T> parameter) {
        return new Equal<T>(field, parameter);
    }

    static <T> Condition equal(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new Equal<T>(field, compereField);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, T value) {
        return new NotEqual<T>(field, value);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new NotEqual<T>(field, parameter);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new NotEqual<T>(field, compereField);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, T value) {
        return new GreaterOrEqual<>(field, value);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new GreaterOrEqual<T>(field, parameter);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new GreaterOrEqual<T>(field, compereField);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, T value) {
        return new GreaterThan<>(field, value);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, Parameter<T> parameter) {
        return new GreaterThan<>(field, parameter);
    }

    static <T> Condition greaterThan(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new GreaterThan<T>(field, compereField);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, T value) {
        return new LessOrEqual<T>(field, value);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new LessOrEqual<>(field, parameter);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new LessOrEqual<T>(field, compereField);
    }

    static <T> Condition lessThan(SelectAqlField<T> field, T value) {
        return new LessThan<>(field, value);
    }

    static <T> Condition lessThan(SelectAqlField<T> field, Parameter<T> parameter) {
        return new LessThan<>(field, parameter);
    }

    static <T> Condition lessThan(SelectAqlField<T> field, SelectAqlField<T> compereField) {
        return new LessThan<T>(field, compereField);
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
        if (Or.class.isAssignableFrom(condition.getClass())) {
            return and(not(((Or) condition).getCondition1()), not(((Or) condition).getCondition2()));
        } else if (And.class.isAssignableFrom(condition.getClass())) {
            return or(not(((And) condition).getCondition1()), not(((And) condition).getCondition2()));
        } else {
            return new Not(condition);
        }
    }

    static Condition exists(SelectAqlField<?> field) {
        return new Exists(field);
    }

    String buildAql();
}
