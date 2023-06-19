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
package org.ehrbase.openehr.sdk.generator.commons.aql.condition;

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.AqlValue;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.Parameter;

public class ComparisonOperator<T> implements Condition {
    protected final SelectAqlField<T> field;
    protected final AqlValue value;
    protected final Parameter<T> parameter;
    protected final SelectAqlField<T> compareField;
    private final String symbol;

    public ComparisonOperator(String symbol, SelectAqlField<T> field, T value) {
        this.symbol = symbol;
        this.field = field;
        this.value = new AqlValue(value);
        this.parameter = null;
        this.compareField = null;
    }

    public ComparisonOperator(String symbol, SelectAqlField<T> field, Parameter<T> parameter) {
        this.symbol = symbol;
        this.field = field;
        this.parameter = parameter;
        this.value = null;
        this.compareField = null;
    }

    public ComparisonOperator(String symbol, SelectAqlField<T> field, SelectAqlField<T> compereField) {
        this.symbol = symbol;
        this.field = field;
        this.value = null;
        this.parameter = null;
        this.compareField = compereField;
    }

    public static <T> Condition valueComparison(SelectAqlField<T> field, String symbol, T value) {
        return new ComparisonOperator<>(symbol, field, value);
    }

    public static <T> Condition parameterComparison(SelectAqlField<T> field, String symbol, Parameter<T> parameter) {
        return new ComparisonOperator<>(symbol, field, parameter);
    }

    public static <T> Condition fieldComparison(
            SelectAqlField<T> field, String symbol, SelectAqlField<T> compareField) {
        return new ComparisonOperator<>(symbol, field, compareField);
    }

    @Override
    public String buildAql(Containment ehrContainment) {
        StringBuilder sb = new StringBuilder();
        sb.append(field.buildAQL(ehrContainment))
                .append(" ")
                .append(getSymbol())
                .append(" ");
        if (value != null) {
            sb.append(value.buildAql());
        } else if (parameter != null) {
            sb.append(parameter.getAqlParameter());
        } else {
            sb.append(compareField.buildAQL(ehrContainment));
        }
        return sb.toString();
    }

    protected String getSymbol() {
        return symbol;
    }
}
