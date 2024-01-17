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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryResultDto;

public class MetaData {

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

    @JsonProperty(value = "_fetch")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long fetch;

    @JsonProperty(value = "_offset")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long offset;

    public MetaData() {}

    public MetaData(QueryResultDto queryResultDto) {

        // initialize basic response meta data
        this.type = MetaData.RESULTSET;
        this.created = queryResultDto.getCreated();
        this.executedAql = queryResultDto.getExecutedAQL();

        // apply _fetch/_offset as needed - note in case only a limit was used we define the default offset 0L
        Long limit = queryResultDto.getLimit();
        Long offset = queryResultDto.getOffset();
        if (limit != null) {
            this.fetch = limit;
            this.offset = offset != null ? offset : 0L;
        }
        // setHref() and setSchemaVersion() needs to be specified by the application
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

    /**
     * Extracted from the query <code>fetch</code> parameter or from the server default.
     * <pre>
     * Not part of the standard spec, but returned by openEHR since version <code>1.0.0</code> for debugging purposes.
     * </pre>
     */
    public Long getFetch() {
        return fetch;
    }

    /**
     * @see #getFetch()
     */
    public void setFetch(Long fetch) {
        this.fetch = fetch;
    }

    /**
     * Extracted from the query <code>limit</code> parameter.
     * <pre>
     * Not part of the standard spec, but returned by openEHR since version <code>1.0.0</code> for debugging purposes.
     * </pre>
     */
    public Long getOffset() {
        return offset;
    }

    /**
     * @see #getOffset()
     */
    public void setOffset(Long offset) {
        this.offset = offset;
    }
}
