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

    String buildAql();


    static <T> Condition equal(SelectAqlField<T> field, T value) {
        return new Equal<T>(field, value);
    }

    static <T> Condition equal(SelectAqlField<T> field, Parameter<T> parameter) {
        return new Equal<T>(field, parameter);
    }


    static <T> Condition notEqual(SelectAqlField<T> field, T value) {
        return new NotEqual<T>(field, value);
    }

    static <T> Condition notEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new NotEqual<T>(field, parameter);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, T value) {
        return new GreaterOrEqual<>(field, value);
    }

    static <T> Condition greaterOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new GreaterOrEqual<T>(field, parameter);
    }

    static <T> Condition greaterThen(SelectAqlField<T> field, T value) {
        return new GreaterThan<>(field, value);
    }

    static <T> Condition greaterThen(SelectAqlField<T> field, Parameter<T> parameter) {
        return new GreaterThan<>(field, parameter);
    }


    static <T> Condition lessOrEqual(SelectAqlField<T> field, T value) {
        return new LessOrEqual<T>(field, value);
    }

    static <T> Condition lessOrEqual(SelectAqlField<T> field, Parameter<T> parameter) {
        return new LessOrEqual<>(field, parameter);
    }

    static <T> Condition lessThen(SelectAqlField<T> field, T value) {
        return new LessThan<>(field, value);
    }

    static <T> Condition lessThen(SelectAqlField<T> field, Parameter<T> parameter) {
        return new LessThan<>(field, parameter);
    }

    static Condition and(Condition condition1, Condition condition2) {
        return new And(condition1, condition2);
    }

    static Condition or(Condition condition1, Condition condition2) {
        return new Or(condition1, condition2);
    }
}
