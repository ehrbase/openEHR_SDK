/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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
import static org.ehrbase.openehr.sdk.response.dto.util.JacksonTestUtil.objectMapper;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.response.dto.util.DTOFixtures;
import org.junit.jupiter.api.Test;

public class MetaDataTest {

    @Test
    void serializedJSONMinimal() throws JsonProcessingException {

        MetaData metaData = new MetaData();
        metaData.setType(MetaData.RESULTSET);
        metaData.setSchemaVersion("1.0.4");
        metaData.setCreated(OffsetDateTime.parse("2017-08-19T00:25:47.568+02:00"));
        metaData.setExecutedAql("SELECT e/ehr_id/value FROM EHR e");

        String json = objectMapper.writeValueAsString(metaData);

        assertEquals(
                """
                        {
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T00:25:47.568+02:00",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e"
                        }""",
                json);
    }

    @Test
    void serializedJSON() throws JsonProcessingException {

        MetaData metaData = new MetaData();
        metaData.setHref("https://example.com/subpath/ehrbase/rest/openehr/v1/query/aql");
        metaData.setType(MetaData.RESULTSET);
        metaData.setSchemaVersion("1.0.4");
        metaData.setCreated(OffsetDateTime.parse("2017-08-19T12:30:00.568+02:00"));
        metaData.setGenerator("DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)");
        metaData.setExecutedAql("SELECT e/ehr_id/value FROM EHR e");
        // debug info
        metaData.setOffset(100L);
        metaData.setFetch(50L);

        String json = objectMapper.writeValueAsString(metaData);

        assertEquals(
                """
                        {
                          "_href" : "https://example.com/subpath/ehrbase/rest/openehr/v1/query/aql",
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T12:30:00.568+02:00",
                          "_generator" : "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e",
                          "_fetch" : 50,
                          "_offset" : 100
                        }""",
                json);
    }

    @Test
    void deserializedJSONMinimal() throws JsonProcessingException {

        MetaData metaData = objectMapper.readValue(
                """
                        {
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T00:25:47.568+02:00",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e"
                        }""",
                MetaData.class);

        assertNull(metaData.getHref());
        assertNull(metaData.getGenerator());
        assertNull(metaData.getFetch());
        assertNull(metaData.getOffset());

        assertEquals(MetaData.RESULTSET, metaData.getType());
        assertEquals("1.0.4", metaData.getSchemaVersion());
        assertEquals(
                OffsetDateTime.parse("2017-08-19T00:25:47.568+02:00").atZoneSameInstant(ZoneOffset.UTC),
                metaData.getCreated().atZoneSameInstant(ZoneOffset.UTC));
        assertEquals("SELECT e/ehr_id/value FROM EHR e", metaData.getExecutedAql());
    }

    @Test
    void deserializeJSON() throws JsonProcessingException {

        MetaData metaData = objectMapper.readValue(
                """
                        {
                          "_href" : "https://example.com/ehrbase/rest/openehr/v1/query/aql",
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T00:25:47.568+02:00",
                          "_generator" : "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e",
                          "_fetch" : 50,
                          "_offset" : 100
                        }""",
                MetaData.class);

        assertEquals("https://example.com/ehrbase/rest/openehr/v1/query/aql", metaData.getHref());
        assertEquals(MetaData.RESULTSET, metaData.getType());
        assertEquals("1.0.4", metaData.getSchemaVersion());
        assertEquals(
                OffsetDateTime.parse("2017-08-19T00:25:47.568+02:00").atZoneSameInstant(ZoneOffset.UTC),
                metaData.getCreated().atZoneSameInstant(ZoneOffset.UTC));
        assertEquals(
                "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)", metaData.getGenerator());
        assertEquals("SELECT e/ehr_id/value FROM EHR e", metaData.getExecutedAql());
        assertEquals(50, metaData.getFetch());
        assertEquals(100, metaData.getOffset());
    }

    @Test
    void deserializeRealWorldExample() throws IOException {

        QueryResponseData queryResponseData = objectMapper.readValue(
                IOUtils.resourceToString("/query_response.json", UTF_8), QueryResponseData.class);
        MetaData metaData = queryResponseData.getMeta();

        assertNotNull(metaData);

        assertNull(metaData.getFetch());
        assertNull(metaData.getOffset());

        assertEquals("... the URI for the executed AQL - used only for GET executions ...", metaData.getHref());
        assertEquals(
                OffsetDateTime.parse("2017-08-19T00:25:47.568+02:00").atZoneSameInstant(ZoneOffset.UTC),
                metaData.getCreated().atZoneSameInstant(ZoneOffset.UTC));
        assertEquals(
                "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)", metaData.getGenerator());
        assertEquals("1.0.0", metaData.getSchemaVersion());
        assertEquals("... the executed aql ...", metaData.getExecutedAql());
    }

    @Test
    void fromQueryResult() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals(metaData.getType(), "RESULTSET");
        assertEquals(metaData.getExecutedAql(), "SELECT e/ehr_id/value FROM EHR e LIMIT 100 OFFSET 1");
        assertEquals(
                metaData.getCreated().atZoneSameInstant(ZoneOffset.UTC),
                OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00").atZoneSameInstant(ZoneOffset.UTC));

        assertNull(metaData.getFetch());
        assertNull(metaData.getOffset());
        assertNull(metaData.getHref());
        assertNull(metaData.getGenerator());
    }

    @Test
    void fromQueryResultDoesContainFetchDefaultOffset() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
            resultDto.setLimit(100L);
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals(metaData.getFetch(), 100L);
        assertEquals(metaData.getOffset(), 0L);
    }

    @Test
    void fromQueryResultContainsFetchOffset() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
            resultDto.setLimit(100L);
            resultDto.setOffset(50L);
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals(metaData.getFetch(), 100L);
        assertEquals(metaData.getOffset(), 50L);
    }
}
