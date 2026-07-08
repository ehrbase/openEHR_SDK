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
package org.ehrbase.openehr.sdk.generator.commons.aql.parameter;

import org.apache.commons.lang3.StringUtils;

public class ParameterValue<T> {
    private final String name;
    private final T value;

    public ParameterValue(Parameter<T> parameter, T value) {
        this.name = parameter.getName();
        this.value = value;
    }

    public ParameterValue(String parameterName, T value) {
        this.name = normalizeName(parameterName);
        this.value = value;
        // check that is valid AQl value
        buildAql();
    }

    public static <U> ParameterValue<U> of(String parameterName, U value) {
        return new ParameterValue<>(parameterName, value);
    }

    static String normalizeName(String name) {
        return StringUtils.normalizeSpace(name).replace(' ', '_');
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public String buildAql() {
        return new AqlValue(value).buildAql();
    }
}
