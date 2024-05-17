/*
 * Copyright (c) 2022 vitasystems GmbH.
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
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.util.List;
import java.util.Map;

@JacksonXmlRootElement
public class StoredQueryResponseData {

    @JsonProperty
    private String name;

    @JsonProperty
    private String type;

    @JsonProperty
    private String version;

    @JsonProperty
    private DvDateTime saved;

    @JsonProperty(value = "q")
    private String aqlQuery;

    // TODO: the answer is returning colums & rows which is not valid according to spec
    @JsonProperty(value = "columns")
    private List<Map<String, String>> columns;

    // TODO: the answer is returning colums & rows which is not valid according to spec
    @JsonProperty(value = "rows")
    private List<List<Object>> rows;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getAqlQuery() {
        return aqlQuery;
    }
}
