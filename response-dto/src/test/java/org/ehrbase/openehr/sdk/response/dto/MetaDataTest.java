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

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.openehr.sdk.response.dto.util.JacksonTestUtil.objectMapper;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
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

        assertThat(json)
                .isEqualToIgnoringWhitespace(
                        """
                        {
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T00:25:47.568+02:00",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e"
                        }""");
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
        // additional properties
        metaData.setAdditionalProperty("string_value", "a_string");
        metaData.setAdditionalProperty("numeric_value", 50);
        metaData.setAdditionalProperty("boolean_value", true);
        metaData.setAdditionalProperty("json_value", Map.of("key", "value"));

        String json = objectMapper.writeValueAsString(metaData);

        // the additional properties are ordered by key
        assertThat(json)
                .isEqualToNormalizingWhitespace(
                        """
                        {
                          "_href" : "https://example.com/subpath/ehrbase/rest/openehr/v1/query/aql",
                          "_type" : "RESULTSET",
                          "_schema_version" : "1.0.4",
                          "_created" : "2017-08-19T12:30:00.568+02:00",
                          "_generator" : "DIPS.OpenEhr.ResultSets.Serialization.Json.ResultSetJsonWriter (5.0.0.0)",
                          "_executed_aql" : "SELECT e/ehr_id/value FROM EHR e",
                          "boolean_value" : true,
                          "json_value" : {
                            "key" : "value"
                          },
                          "numeric_value" : 50,
                          "string_value" : "a_string"
                        }""");
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
        assertNull(metaData.getAdditionalProperty("fetch"));
        assertNull(metaData.getAdditionalProperty("offset"));
        assertNull(metaData.getAdditionalProperty("resultsize"));

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
                          "boolean_value" : true,
                          "json_value" : {
                            "key" : "value"
                          },
                          "numeric_value" : 50,
                          "string_value" : "a_string"
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

        // additional properties
        assertEquals("a_string", metaData.getAdditionalProperty("string_value"));
        assertEquals(50, metaData.getAdditionalProperty("numeric_value", Integer.class));
        assertEquals(true, metaData.getAdditionalProperty("boolean_value"));
        assertEquals(Map.of("key", "value"), metaData.getAdditionalProperty("json_value"));
    }

    @Test
    @Deprecated(forRemoval = true)
    @SuppressWarnings("removal")
    void fromQueryResult() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals("RESULTSET", metaData.getType());
        assertEquals("SELECT e/ehr_id/value FROM EHR e LIMIT 100 OFFSET 1", metaData.getExecutedAql());
        assertEquals(
                OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00").atZoneSameInstant(ZoneOffset.UTC),
                metaData.getCreated().atZoneSameInstant(ZoneOffset.UTC));
        assertEquals(0, metaData.getAdditionalProperty("resultsize"));
        assertNull(metaData.getAdditionalProperty("fetch"));
        assertNull(metaData.getAdditionalProperty("offset"));
        assertNull(metaData.getHref());
        assertNull(metaData.getGenerator());
    }

    @Test
    @Deprecated(forRemoval = true)
    @SuppressWarnings("removal")
    void fromQueryResultDoesContainFetchDefaultOffset() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
            resultDto.setLimit(100);
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals(100, metaData.getAdditionalProperty("fetch"));
        assertEquals(0, metaData.getAdditionalProperty("offset"));
        assertEquals(0, metaData.getAdditionalProperty("resultsize"));
    }

    @Test
    @Deprecated(forRemoval = true)
    @SuppressWarnings("removal")
    void fromQueryResultContainsFetchOffset() {

        MetaData metaData = new MetaData(DTOFixtures.fixtureQueryResultResultDto(resultDto -> {
            resultDto.setCreated(OffsetDateTime.parse("2017-02-16T13:50:11.308+01:00"));
            resultDto.setLimit(100);
            resultDto.setOffset(50);
        }));

        assertNotNull(metaData, "Expected meta to be null");
        assertEquals(100, metaData.getAdditionalProperty("fetch"));
        assertEquals(50, metaData.getAdditionalProperty("offset"));
        assertEquals(0, metaData.getAdditionalProperty("resultsize"));
    }
}
