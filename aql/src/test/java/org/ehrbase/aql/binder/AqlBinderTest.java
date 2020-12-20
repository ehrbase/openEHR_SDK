/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.aql.binder;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.groups.Tuple;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.client.aql.query.EntityQuery;
import org.ehrbase.client.aql.record.Record;
import org.junit.Test;

public class AqlBinderTest {
  private static class TestCase {
    int id;
    String json;
    String expectedAql;
    Map<String, String> expectedMap;

    public TestCase(int id, String json, String expectedAql, Map<String, String> expectedMap) {
      this.id = id;
      this.json = json;
      this.expectedAql = expectedAql;
      this.expectedMap = expectedMap;
    }
  }

  private AqlBinder cut = new AqlBinder();
  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void bind() {

    List<TestCase> testCaseList = new ArrayList<>();
    testCaseList.add(
        new TestCase(
            1,
            "{\n"
                + "    \"select\": {\n"
                + "        \"topCount\": 13,\n"
                + "        \"topDirection\": \"FORWARD\",\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Bericht ID::value\",\n"
                + "                \"aqlPath\": \"/context/other_context[at0001]/items[at0002]/value/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-COMPOSITION.report.v1\"\n"
                + "    }\n"
                + "}",
            "Select TOP 13 FORWARD c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]",
            Map.of()));

    testCaseList.add(
        new TestCase(
            2,
            "{\n"
                + "    \"select\": {\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Bericht ID::value\",\n"
                + "                \"aqlPath\": \"/context/other_context[at0001]/items[at0002]/value/value\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 2,\n"
                + "                \"name\": \"Geschichte::value\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 3,\n"
                + "                \"name\": \"Bezeichnung des Symptoms oder Anzeichens.::value\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 4,\n"
                + "                \"name\": \"Kommentar::value\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-COMPOSITION.report.v1\",\n"
                + "        \"contains\": {\n"
                + "            \"_type\": \"LogicalOperator\",\n"
                + "            \"symbol\": \"AND\",\n"
                + "            \"values\": [\n"
                + "                {\n"
                + "                    \"_type\": \"Containment\",\n"
                + "                    \"id\": 2,\n"
                + "                    \"archetypeId\": \"openEHR-EHR-OBSERVATION.story.v1\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"_type\": \"Containment\",\n"
                + "                    \"id\": 3,\n"
                + "                    \"archetypeId\": \"openEHR-EHR-OBSERVATION.symptom_sign_screening.v0\"\n"
                + "                },\n"
                + "                {\n"
                + "                    \"_type\": \"Containment\",\n"
                + "                    \"id\": 4,\n"
                + "                    \"archetypeId\": \"openEHR-EHR-OBSERVATION.exposure_assessment.v0\"\n"
                + "                }\n"
                + "            ]\n"
                + "        }\n"
                + "    }\n"
                + "}",
            "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, o1/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value as Geschichte__value, o2/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] and OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])",
            Map.of()));

    testCaseList.add(
        new TestCase(
            3,
            "{\n"
                + "    \"select\": {\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Bericht ID::value\",\n"
                + "                \"aqlPath\": \"/context/other_context[at0001]/items[at0002]/value/value\"\n"
                + "            },\n"
                + "                        {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 0,\n"
                + "                \"name\": \"ehr_id\",\n"
                + "                \"aqlPath\": \"/ehr_id/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"ehr\":{\n"
                + "        \"containmentId\":0\n"
                + "    }\n"
                + "    ,\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-COMPOSITION.report.v1\"\n"
                + "    }\n"
                + "}",
            "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, e/ehr_id/value as ehr_id from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]",
            Map.of()));

    testCaseList.add(
        new TestCase(
            4,
            "{\n"
                + "    \"select\": {\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Systolic::magnitude\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "            },\n"
                + "                        {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 0,\n"
                + "                \"name\": \"ehr_id\",\n"
                + "                \"aqlPath\": \"/ehr_id/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"ehr\":{\n"
                + "        \"containmentId\":0\n"
                + "    }\n"
                + "    ,\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-OBSERVATION.sample_blood_pressure.v1\"\n"
                + "    },\n"
                + "    \"where\":{\n"
                + "        \"_type\": \"ComparisonOperator\",\n"
                + "        \"statement\":  {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Systolic::magnitude\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "            },\n"
                + "        \"symbol\":\"EQ\",\n"
                + "        \"value\":{\n"
                + "             \"_type\": \"Simple\",\n"
                + "             \"value\": 1.1\n"
                + "        }    \n"
                + "    }\n"
                + "}",
            "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e  contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude = 1.1",
            Map.of()));

    testCaseList.add(
        new TestCase(
            5,
            "{\n"
                + "    \"select\": {\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Systolic::magnitude\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 0,\n"
                + "                \"name\": \"ehr_id\",\n"
                + "                \"aqlPath\": \"/ehr_id/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"ehr\": {\n"
                + "        \"containmentId\": 0\n"
                + "    },\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-OBSERVATION.sample_blood_pressure.v1\"\n"
                + "    },\n"
                + "    \"where\": {\n"
                + "        \"_type\": \"LogicalOperator\",\n"
                + "        \"symbol\": \"AND\",\n"
                + "        \"values\": [\n"
                + "            {\n"
                + "                \"_type\": \"ComparisonOperator\",\n"
                + "                \"statement\": {\n"
                + "                    \"_type\": \"SelectField\",\n"
                + "                    \"containmentId\": 1,\n"
                + "                    \"name\": \"Systolic::magnitude\",\n"
                + "                    \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "                },\n"
                + "                \"symbol\": \"GT_EQ\",\n"
                + "                \"value\": {\n"
                + "                    \"_type\": \"Parameter\",\n"
                + "                    \"name\": \"magnitude\",\n"
                + "                    \"type\": \"DOUBLE\"\n"
                + "                }\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"ComparisonOperator\",\n"
                + "                \"statement\": {\n"
                + "                    \"_type\": \"SelectField\",\n"
                + "                    \"containmentId\": 1,\n"
                + "                    \"name\": \"Systolic::magnitude\",\n"
                + "                    \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "                },\n"
                + "                \"symbol\": \"LT\",\n"
                + "                \"value\": {\n"
                + "                    \"_type\": \"Simple\",\n"
                + "                    \"value\": 1.1\n"
                + "                }\n"
                + "            }\n"
                + "        ]\n"
                + "    }\n"
                + "}",
            "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e  contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)",
            Map.of("magnitude", "DOUBLE")));

    testCaseList.add(
        new TestCase(
            6,
            "{\n"
                + "    \"select\": {\n"
                + "        \"statement\": [\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Systolic::magnitude\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "            },\n"
                + "            {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 0,\n"
                + "                \"name\": \"ehr_id\",\n"
                + "                \"aqlPath\": \"/ehr_id/value\"\n"
                + "            }\n"
                + "        ]\n"
                + "    },\n"
                + "    \"ehr\": {\n"
                + "        \"containmentId\": 0\n"
                + "    },\n"
                + "    \"contains\": {\n"
                + "        \"_type\": \"Containment\",\n"
                + "        \"id\": 1,\n"
                + "        \"archetypeId\": \"openEHR-EHR-OBSERVATION.sample_blood_pressure.v1\"\n"
                + "    },\n"
                + "    \"orderBy\": [\n"
                + "        {\n"
                + "            \"symbol\": \"ASC\",\n"
                + "            \"statement\": {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 1,\n"
                + "                \"name\": \"Systolic::magnitude\",\n"
                + "                \"aqlPath\": \"/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude\"\n"
                + "            }\n"
                + "        },\n"
                + "      {\n"
                + "            \"symbol\": \"DESC\",\n"
                + "            \"statement\":  {\n"
                + "                \"_type\": \"SelectField\",\n"
                + "                \"containmentId\": 0,\n"
                + "                \"name\": \"ehr_id\",\n"
                + "                \"aqlPath\": \"/ehr_id/value\"\n"
                + "            }\n"
                + "        }\n"
                + "    ]\n"
                + "}",
            "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e  contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASCENDING, e/ehr_id/value DESCENDING",
            Map.of()));

    testCaseList.forEach(this::test);
  }

  public void test(TestCase testCase) {

    final AqlDto aqlDto;
    try {
      aqlDto = objectMapper.readValue(testCase.json, AqlDto.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    Pair<EntityQuery<Record>, List<ParameterValue>> actual = cut.bind(aqlDto);

    assertThat(actual.getLeft().buildAql())
        .as("Test: %s ", testCase.id)
        .isEqualTo(testCase.expectedAql);

    assertThat(actual.getRight())
        .extracting(ParameterValue::getName, ParameterValue::getType)
        .as("Test: %s ", testCase.id)
        .containsExactlyInAnyOrder(
            testCase.expectedMap.entrySet().stream()
                .map(e -> new Tuple(e.getKey(), e.getValue()))
                .toArray(Tuple[]::new));
  }
}
