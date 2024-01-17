/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.response.dto.ehrscape;

import java.time.OffsetDateTime;
import java.util.*;
import javax.annotation.Nullable;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.query.ResultHolder;

public class QueryResultDto {

    private OffsetDateTime created;
    private String executedAQL;
    private Long limit;
    private Long offset;
    private List<List<String>> explain;
    private Map<String, String> variables;
    private List<ResultHolder> resultSet;

    public QueryResultDto() {
        this(OffsetDateTime.now());
    }

    public QueryResultDto(OffsetDateTime created) {
        this.created = created;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public List<ResultHolder> getResultSet() {
        return resultSet;
    }

    public void setResultSet(List<ResultHolder> resultSet) {
        this.resultSet = resultSet;
    }

    public String getExecutedAQL() {
        return executedAQL;
    }

    public void setExecutedAQL(String executedAQL) {
        this.executedAQL = executedAQL;
    }

    public @Nullable Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public @Nullable Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    public List<List<String>> getExplain() {
        return explain;
    }

    public void setExplain(List<List<String>> explain) {
        this.explain = explain;
    }

    public Map<String, String> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public boolean variablesIsEmpty() {
        return Optional.ofNullable(variables).orElse(Collections.emptyMap()).isEmpty();
    }

    public boolean variablesContainsColumnId(String columnId) {
        return getVariables().containsKey(columnId);
    }

    public String variablesPath(String columnId) {
        return getVariables().get(columnId);
    }

    public Iterator<Map.Entry<String, String>> variablesIterator() {
        return getVariables().entrySet().iterator();
    }
}
