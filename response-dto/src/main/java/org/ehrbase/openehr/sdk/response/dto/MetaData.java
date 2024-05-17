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
package org.ehrbase.openehr.sdk.response.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.*;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryResultDto;

/**
 * Represents a query result <code>meta</code> object.
 *
 * @see <a href="https://specifications.openehr.org/releases/ITS-REST/latest/query.html#tag/RESULT_SET_schema">Query API - RESULT_SET_schema</a>
 */
public class MetaData {

    /**
     * {@link MetaData} type for an AQL result.
     */
    public static final String RESULTSET = "RESULTSET";

    @JsonProperty(value = "_href")
    private String href;

    @JsonProperty(value = "_type")
    private String type;

    @JsonProperty(value = "_schema_version")
    private String schemaVersion;

    @JsonProperty(value = "_created")
    private OffsetDateTime created;

    @JsonProperty(value = "_generator")
    private String generator;

    @JsonProperty(value = "_executed_aql")
    private String executedAql;

    /**
     * Meta additional property of type <code>any</code>.
     * The entries are ordered by key.
     */
    private final Map<String, Object> additionalProperties = new TreeMap<>();

    public MetaData() {}

    @Deprecated(forRemoval = true)
    public MetaData(QueryResultDto queryResultDto) {

        // initialize basic response meta data
        this.type = MetaData.RESULTSET;
        this.created = queryResultDto.getCreated();
        this.executedAql = queryResultDto.getExecutedAQL();

        // we always add the response set size - also in case it is empty
        setAdditionalProperty(
                "resultsize",
                Optional.ofNullable(queryResultDto.getResultSet())
                        .map(List::size)
                        .orElse(0));

        // apply fetch/offset as needed - note in case only a limit was used we define the default offset as0L
        Integer resultLimit = queryResultDto.getLimit();
        Integer resultOffset = queryResultDto.getOffset();
        if (resultLimit != null) {
            setAdditionalProperty("fetch", resultLimit);
            setAdditionalProperty("offset", resultOffset != null ? resultOffset : 0);
        }
        // the following properties needs to be specified by the application
        this.href = null;
        this.schemaVersion = null;
        this.generator = null;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchemaVersion() {
        return schemaVersion;
    }

    public void setSchemaVersion(String schemaVersion) {
        this.schemaVersion = schemaVersion;
    }

    public OffsetDateTime getCreated() {
        return created;
    }

    public void setCreated(OffsetDateTime created) {
        this.created = created;
    }

    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getExecutedAql() {
        return executedAql;
    }

    public void setExecutedAql(String executedAql) {
        this.executedAql = executedAql;
    }

    public <T> T getAdditionalProperty(String name, Class<T> type) {
        return type.cast(getAdditionalProperty(name));
    }

    public Object getAdditionalProperty(String name) {
        return additionalProperties.get(name);
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        additionalProperties.put(name, value);
    }

    @JsonAnyGetter
    private Map<String, Object> additionalProperties() {
        return additionalProperties;
    }
}
