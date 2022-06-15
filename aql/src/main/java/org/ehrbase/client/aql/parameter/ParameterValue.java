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
package org.ehrbase.client.aql.parameter;

public class ParameterValue<T> {
    private final Parameter<T> parameter;
    private final T value;

    public ParameterValue(Parameter<T> parameter, T value) {
        this.parameter = parameter;
        this.value = value;
    }

    public ParameterValue(String parameterName, T value) {
        this.parameter = new Parameter(parameterName);
        this.value = value;
        // check that is valid AQl value
        buildAql();
    }

    public Parameter<T> getParameter() {
        return parameter;
    }

    public T getValue() {
        return value;
    }

    public String buildAql() {
        return new AqlValue(value).buildAql();
    }
}
