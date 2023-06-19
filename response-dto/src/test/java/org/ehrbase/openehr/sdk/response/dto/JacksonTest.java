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

import static java.nio.charset.StandardCharsets.UTF_8;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JacksonTest {

    private final ObjectMapper objectMapper;

    public JacksonTest() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testReadJson() throws IOException {
        QueryResponseData response = objectMapper.readValue(
                IOUtils.resourceToString("/query_response.json", UTF_8), QueryResponseData.class);

        MetaData meta = response.getMeta();
        Assertions.assertNotNull(response.getMeta());
        Assertions.assertEquals("... the URI for the executed AQL - used only for GET executions ...", meta.getHref());
        Assertions.assertEquals(
                "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)", meta.getGenerator());
        Assertions.assertEquals("1.0.0", meta.getSchemaVersion());
        Assertions.assertEquals("... the executed aql ...", meta.getExecutedAql());
    }

    @Test
    void testWriteJson() throws IOException {
        MetaData meta = new MetaData();
        meta.setHref("https://rest.ehrscape.com/rest/openehr/v1/query/aql?q=select+c+from+composition+c");
        meta.setExecutedAql("select c from composition c");
        meta.setType("RESULTSET");
        QueryResponseData response = new QueryResponseData();
        response.setMeta(meta);

        String json = objectMapper.writeValueAsString(response);

        Assertions.assertTrue(
                json.contains("https://rest.ehrscape.com/rest/openehr/v1/query/aql?q=select+c+from+composition+c"));
        Assertions.assertTrue(json.contains("select c from composition c"));
        Assertions.assertTrue(json.contains("RESULTSET"));
    }
}
