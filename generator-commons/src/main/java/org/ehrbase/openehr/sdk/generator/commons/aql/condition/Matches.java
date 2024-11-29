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
package org.ehrbase.openehr.sdk.generator.commons.aql.condition;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.AqlValue;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.Parameter;

public class Matches<T> implements Condition {

    private final SelectAqlField<T> field;
    private final AqlValue[] values;
    private final Parameter<T>[] parameters;

    Matches(SelectAqlField<T> field, T... values) {
        this.field = field;
        this.values = Arrays.stream(values).map(AqlValue::new).toArray(AqlValue[]::new);
        this.parameters = null;
    }

    Matches(SelectAqlField<T> field, Parameter<T>... parameters) {
        this.field = field;
        this.values = null;
        this.parameters = parameters;
    }

    @Override
    public String buildAql(Containment ehrContainment) {
        StringBuilder sb = new StringBuilder();
        sb.append(field.buildAQL(ehrContainment)).append(" matches {");

        if (values != null) {
            sb.append(Arrays.stream(values).map(AqlValue::buildAql).collect(Collectors.joining(",")));
        } else {
            sb.append(Arrays.stream(parameters).map(Parameter::getAqlParameter).collect(Collectors.joining(",")));
        }
        sb.append("}");

        return sb.toString();
    }
}
