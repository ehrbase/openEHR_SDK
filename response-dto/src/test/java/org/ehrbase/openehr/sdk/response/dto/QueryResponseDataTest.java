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

import static org.ehrbase.openehr.sdk.response.dto.util.JacksonTestUtil.objectMapper;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.QueryResultDto;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.query.ResultHolder;
import org.junit.jupiter.api.Test;

class QueryResponseDataTest {

    @Test
    void serializedJSONMinimal() throws JsonProcessingException {

        QueryResponseData queryResponseData = new QueryResponseData();
        queryResponseData.setQuery("SELECT e/ehr_id/value FROM EHR e");
        queryResponseData.setColumns(Collections.emptyList());
        queryResponseData.setRows(Collections.emptyList());

        String json = objectMapper.writeValueAsString(queryResponseData);

        assertEquals(
                """
                {
                  "q" : "SELECT e/ehr_id/value FROM EHR e",
                  "columns" : [ ],
                  "rows" : [ ]
                }""",
                json);
    }

    @Test
    void serializedJSON() throws JsonProcessingException {

        QueryResponseData queryResponseData = new QueryResponseData();
        queryResponseData.setQuery("SELECT e/ehr_id/value FROM EHR e");
        queryResponseData.setName("all.ehr.ids::query");
        queryResponseData.setColumns(List.of(new TreeMap<>(Map.of(
                "path", "e/ehr_id/value",
                "name", "#0"))));
        queryResponseData.setRows(List.of(List.of("c631b8d4-c2b7-4908-812f-83403b4c866d")));

        var metaData = new MetaData();
        metaData.setType("RESULTSET");
        metaData.setSchemaVersion("1.0.4");
        metaData.setCreated(OffsetDateTime.parse("2024-01-10T12:30:00.123456+01:00"));
        metaData.setExecutedAql("SELECT e/ehr_id/value FROM EHR LIMIT 100 OFFSET 0");
        queryResponseData.setMeta(metaData);

        String json = objectMapper.writeValueAsString(queryResponseData);

        assertEquals(
                """
                {
                  "meta" : {
                    "_type" : "RESULTSET",
                    "_schema_version" : "1.0.4",
                    "_created" : "2024-01-10T12:30:00.123456+01:00",
                    "_executed_aql" : "SELECT e/ehr_id/value FROM EHR LIMIT 100 OFFSET 0"
                  },
                  "q" : "SELECT e/ehr_id/value FROM EHR e",
                  "name" : "all.ehr.ids::query",
                  "columns" : [ {
                    "name" : "#0",
                    "path" : "e/ehr_id/value"
                  } ],
                  "rows" : [ [ "c631b8d4-c2b7-4908-812f-83403b4c866d" ] ]
                }""",
                json);
    }

    @Test
    void deserializedJSONMinimal() throws JsonProcessingException {

        QueryResponseData queryResponseData = objectMapper.readValue(
                """
                {
                  "q" : "SELECT e/ehr_id/value FROM EHR e",
                  "columns" : [ ],
                  "rows" : [ ]
                }""",
                QueryResponseData.class);

        assertNull(queryResponseData.getMeta());
        assertNull(queryResponseData.getName());
        assertEquals("SELECT e/ehr_id/value FROM EHR e", queryResponseData.getQuery());
        assertEquals(Collections.emptyList(), queryResponseData.getColumns());
        assertEquals(Collections.emptyList(), queryResponseData.getRows());
    }

    @Test
    void deserializeJSON() throws JsonProcessingException {

        QueryResponseData queryResponseData = objectMapper.readValue(
                """
                {
                  "meta" : {
                    "_type" : "RESULTSET",
                    "_schema_version" : "1.0.4",
                    "_created" : "2024-01-10T12:30:00.123456+01:00",
                    "_executed_aql" : "SELECT e/ehr_id/value FROM EHR LIMIT 100 OFFSET 0"
                  },
                  "q" : "SELECT e/ehr_id/value FROM EHR e",
                  "name" : "all.ehr.ids::query",
                  "columns" : [ {
                    "name" : "#0",
                    "path" : "e/ehr_id/value"
                  } ],
                  "rows" : [ [ "c631b8d4-c2b7-4908-812f-83403b4c866d" ] ]
                }""",
                QueryResponseData.class);

        assertNotNull(queryResponseData.getMeta());
        assertEquals("all.ehr.ids::query", queryResponseData.getName());
        assertEquals("SELECT e/ehr_id/value FROM EHR e", queryResponseData.getQuery());
        List<Map<String, String>> columns = queryResponseData.getColumns();
        {
            assertEquals(1, columns.size());
            Map<String, String> column = columns.get(0);
            assertEquals(2, column.size());
            assertEquals("#0", column.get("name"));
            assertEquals("e/ehr_id/value", column.get("path"));
        }
        List<List<Object>> rows = queryResponseData.getRows();
        {
            assertEquals(1, rows.size());
            assertEquals(List.of("c631b8d4-c2b7-4908-812f-83403b4c866d"), rows.get(0));
        }
    }

    private QueryResultDto fixtureQueryResultResultDto() {
        return fixtureQueryResultResultDto(null);
    }

    private QueryResultDto fixtureQueryResultResultDto(@Nullable Consumer<QueryResultDto> customize) {

        QueryResultDto queryResultDto = new QueryResultDto();
        queryResultDto.setExecutedAQL("SELECT e/ehr_id/value FROM EHR e LIMIT 100 OFFSET 1");

        if (null != customize) {
            customize.accept(queryResultDto);
        }

        return queryResultDto;
    }

    @Test
    void fromQueryResultDoesNotUseExecutedAql() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto());

        // response `q` property shall be the in initial request query, not the executed one with substitution
        assertNull(queryResponseData.getQuery(), "Expected query to be null");
    }

    @Test
    void fromQueryResultDoesContainMetaData() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto());

        assertNull(queryResponseData.getMeta(), "Expected meta to be null");
    }

    @Test
    void fromQueryResultDoesContainName() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto());

        assertNull(queryResponseData.getName(), "Expected name to be null");
    }

    @Test
    void fromQueryResultNothingSelected() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto(dto -> {
            dto.setVariables(null);
            dto.setResultSet(null);
        }));

        assertTrue(queryResponseData.getColumns().isEmpty(), "Expected no result columns");
        assertTrue(queryResponseData.getRows().isEmpty(), "Expected result rows");
    }

    @Test
    void fromQueryResultEmptyResult() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto(dto -> {
            dto.setVariables(Map.of("#0", "e/ehr_id/value"));
            dto.setResultSet(Collections.emptyList());
        }));

        List<Map<String, String>> columns = queryResponseData.getColumns();
        assertEquals(1, columns.size(), "Expected one column to exist");
        {
            Map<String, String> column = columns.get(0);
            assertEquals(column.size(), 2);
            assertEquals("#0", column.get("name"));
            assertEquals("e/ehr_id/value", column.get("path"));
        }

        assertTrue(queryResponseData.getRows().isEmpty(), "Expected result rows");
    }

    @Test
    void fromQueryResultSingleEntry() {

        QueryResponseData queryResponseData = new QueryResponseData(fixtureQueryResultResultDto(dto -> {
            dto.setVariables(Map.of("#0", "e/ehr_id/value"));

            ResultHolder resultHolder = new ResultHolder();
            resultHolder.putResult("#0", UUID.fromString("c631b8d4-c2b7-4908-812f-83403b4c866d"));
            dto.setResultSet(List.of(resultHolder));
        }));

        List<Map<String, String>> columns = queryResponseData.getColumns();
        assertEquals(columns.size(), 1, "Expected one column to exist");
        {
            Map<String, String> column = columns.get(0);
            assertEquals(column.size(), 2);
            assertEquals("#0", column.get("name"));
            assertEquals("e/ehr_id/value", column.get("path"));
        }

        List<List<Object>> rows = queryResponseData.getRows();
        assertEquals(1, rows.size(), "Expected one row to exist");
        {
            List<Object> result = rows.get(0);
            assertEquals(1, result.size());
            assertEquals(UUID.fromString("c631b8d4-c2b7-4908-812f-83403b4c866d"), result.get(0));
        }
    }
}
