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
package org.ehrbase.openehr.sdk.response.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryDefinitionResultDto;

@JacksonXmlRootElement
public class QueryDefinitionListResponseData {

    @JsonProperty
    private List<Map<String, String>> versions;

    public QueryDefinitionListResponseData(List<QueryDefinitionResultDto> definitionResultDtos) {
        this.versions = new ArrayList<>();

        for (QueryDefinitionResultDto queryDefinitionResultDto : definitionResultDtos) {
            Map<String, String> definition = new HashMap<>();
            definition.put("name", queryDefinitionResultDto.getQualifiedName());
            definition.put("version", queryDefinitionResultDto.getVersion());
            definition.put("saved", queryDefinitionResultDto.getSaved().format(DateTimeFormatter.ISO_DATE_TIME));
            definition.put("type", queryDefinitionResultDto.getType());
            versions.add(definition);
        }
    }

    public int size() {
        return versions.size();
    }
}
