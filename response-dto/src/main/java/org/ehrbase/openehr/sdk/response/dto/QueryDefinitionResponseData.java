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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.format.DateTimeFormatter;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryDefinitionResultDto;

@JacksonXmlRootElement
public class QueryDefinitionResponseData {

    // the initial query without substitution (!)
    @JsonProperty(value = "q")
    private String query;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "version")
    private String version;

    @JsonProperty(value = "saved")
    private String saved;

    public QueryDefinitionResponseData(QueryDefinitionResultDto definitionResultDto) {
        this.query = definitionResultDto.getQueryText();
        this.name = definitionResultDto.getQualifiedName();
        this.version = definitionResultDto.getVersion();
        this.saved = definitionResultDto.getSaved().format(DateTimeFormatter.ISO_DATE_TIME);
        this.type = definitionResultDto.getType();
    }

    public String getQuery() {
        return query;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public String getSaved() {
        return saved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
