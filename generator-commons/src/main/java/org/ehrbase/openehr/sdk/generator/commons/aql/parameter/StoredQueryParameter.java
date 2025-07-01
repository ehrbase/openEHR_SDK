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
package org.ehrbase.openehr.sdk.generator.commons.aql.parameter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;

public class StoredQueryParameter {

    private final String qualifiedQueryName;
    private final String version;
    private Integer offset;
    private Integer fetch;
    private String type;
    private final Map<String, String> parameterMap = new HashMap<>();

    public StoredQueryParameter(String qualifiedQueryName, String version) {
        this.qualifiedQueryName = qualifiedQueryName;
        this.version = version;
    }

    public StoredQueryParameter offset(int value) {
        this.offset = value;
        return this;
    }

    public StoredQueryParameter fetch(int value) {
        this.fetch = value;
        return this;
    }

    public StoredQueryParameter type(String value) {
        this.type = value;
        return this;
    }

    public StoredQueryParameter addQueryParam(String parameterName, String value) {
        parameterMap.put(parameterName, value);
        return this;
    }

    public Optional<Integer> getOffset() {
        return Optional.ofNullable(this.offset);
    }

    public Optional<Integer> getFetch() {
        return Optional.ofNullable(this.fetch);
    }

    public Optional<String> getType() {
        return Optional.ofNullable(this.type);
    }

    public boolean isValid() {
        return !StringUtils.isEmpty(qualifiedQueryName) && !StringUtils.isEmpty(version);
    }

    public String getPath() {
        return qualifiedQueryName + "/" + version;
    }

    public Map<String, String> getQueryParams() {
        return parameterMap;
    }
}
