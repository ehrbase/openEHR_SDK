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
package org.ehrbase.aql.roundtriptest;

/**
 * @author Stefan Spiska
 */
public enum PerformanceTestCase implements AqlTestDto {
    Query1(
            "SELECT c/uid/value as composition_id, c/context/start_time/value as start_time, o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as body_temperature"
                    + "  FROM EHR e"
                    + "    CONTAINS COMPOSITION c"
                    + "    CONTAINS OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2]"
                    + " WHERE e/ehr_id/value = '96940df3-c979-4d96-9a51-34b6c5222777'"
                    + "    AND o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude > 37.5"
                    + " LIMIT 10 OFFSET 10"
                    + " ORDER BY c/context/start_time/value DESC",
            "Select c/uid/value as composition_id, c/context/start_time/value as start_time, o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as body_temperature "
                    + "from EHR e "
                    + "contains COMPOSITION c "
                    + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.body_temperature.v2] "
                    + "where (e/ehr_id/value = '96940df3-c979-4d96-9a51-34b6c5222777' "
                    + "and o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude > 37.5) "
                    + "LIMIT 10 OFFSET 10 "
                    + "order by c/context/start_time/value DESCENDING",
            "Get second page of measurements with page size 10 which indicated high temperature and their time"),
    Query2(
            "SELECT c as composition"
                    + "  FROM EHR e"
                    + "    CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]"
                    + " WHERE e/ehr_id/value = '96940df3-c979-4d96-9a51-34b6c5222777' "
                    + "       AND c/archetype_details/template_id/value = 'Corona_Anamnese'"
                    + " LIMIT 1"
                    + " ORDER BY c/context/start_time/value DESC",
            "Select c as composition " + "from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                    + "where (e/ehr_id/value = '96940df3-c979-4d96-9a51-34b6c5222777' "
                    + "and c/archetype_details/template_id/value = 'Corona_Anamnese') "
                    + "LIMIT 1 "
                    + "order by c/context/start_time/value DESCENDING",
            "Get latest 'Corona_Anamnese' document for a specific patient"),
    Query3(
            "SELECT DISTINCT e/ehr_id/value "
                    + "FROM EHR e "
                    + "CONTAINS COMPOSITION c "
                    + "CONTAINS EVALUATION ev[openEHR-EHR-EVALUATION.problem_diagnosis.v1] "
                    + "WHERE c/context/health_care_facility/name/value='hcf0' "
                    + " AND ev/data[at0001]/items[at0002]/value/value='Problem/Diagnosis name 10'",
            "Select DISTINCT e/ehr_id/value as F1 " + "from EHR e "
                    + "contains COMPOSITION c "
                    + "contains EVALUATION ev[openEHR-EHR-EVALUATION.problem_diagnosis.v1] "
                    + "where (c/context/health_care_facility/name/value = 'hcf0' "
                    + "and ev/data[at0001]/items[at0002]/value/value = 'Problem/Diagnosis name 10')",
            "Get latest 'Corona_Anamnese' document for a specific patient");

    private final String testAql;
    private final String expectedAql;

    PerformanceTestCase(String testAql, String expectedAql, String description) {
        this.testAql = testAql;
        this.expectedAql = expectedAql;
    }

    @Override
    public String getTestAql() {
        return testAql;
    }

    @Override
    public String getExpectedAql() {
        return expectedAql;
    }
}
