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
package org.ehrbase.client.aql.funtion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.operand.AQLFunction;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;

/**
 * @author Stefan Spiska
 */
public abstract class AbstractFunction<T> implements Function, SelectAqlField<T> {

    private final List<SelectAqlField<?>> parameters = new ArrayList<>();

    private final AQLFunction function;

    private final String name;

    protected AbstractFunction(List<SelectAqlField<?>> parameters, AQLFunction function, String name) {
        this.parameters.addAll(parameters);
        this.function = function;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<SelectAqlField<?>> getParameters() {
        return parameters;
    }

    @Override
    public String buildAQL(Containment ehrContainment) {
        StringBuilder sb = new StringBuilder();

        sb.append(function.toString()).append("(");

        if (parameters != null) {

            sb.append(parameters.stream().map(f -> f.buildAQL(ehrContainment)).collect(Collectors.joining(",")));
        }

        sb.append(")");
        return sb.toString();
    }

    protected static <X> AbstractFunction<X> create(
            List<SelectAqlField<?>> parameters, AQLFunction function, String name, Class<X> aClass) {

        return new AbstractFunction<X>(parameters, function, name) {
            @Override
            public Containment getContainment() {
                return null;
            }

            @Override
            public String getPath() {
                return function.name();
            }

            @Override
            public Class<?> getEntityClass() {
                return null;
            }

            @Override
            public Class<X> getValueClass() {
                return aClass;
            }

            @Override
            public boolean isMultiValued() {
                return false;
            }
        };
    }
}
