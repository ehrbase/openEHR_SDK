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
package org.ehrbase.openehr.sdk.generator.commons.aql.parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StoredQueryParameter extends StoredQueryDefinition {

    private Integer offset;
    private Integer fetch;
    private final List<ParameterValue> parameters = new ArrayList<>();

    public StoredQueryParameter(String qualifiedQueryName, String version) {
        super(qualifiedQueryName, version);
    }

    public StoredQueryParameter offset(Integer value) {
        this.offset = value;
        return this;
    }

    public StoredQueryParameter fetch(Integer value) {
        this.fetch = value;
        return this;
    }

    public StoredQueryParameter addQueryParam(String parameterName, Object value) {
        parameters.add(new ParameterValue(parameterName, value));
        return this;
    }

    public Optional<Integer> getOffset() {
        return Optional.ofNullable(this.offset);
    }

    public Optional<Integer> getFetch() {
        return Optional.ofNullable(this.fetch);
    }

    public List<ParameterValue> getQueryParams() {
        return parameters;
    }
}
