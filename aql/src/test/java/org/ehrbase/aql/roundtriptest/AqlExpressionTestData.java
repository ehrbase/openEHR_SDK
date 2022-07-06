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
 * @see <a
 *     href="https://github.com/ehrbase/ehrbase/blob/v0.21.1/service/src/test/java/org/ehrbase/aql/compiler/AqlExpressionTest.java">AqlExpressionTest
 *     in ehrbase</a>
 */
public enum AqlExpressionTestData implements AqlTestDto {
    DUMP(
            "SELECT o/data[at0002]/events[at0003] AS systolic "
                    + "FROM EHR [ehr_id/value='1234'] "
                    + "CONTAINS COMPOSITION c [openEHR-EHR-COMPOSITION.encounter.v1] "
                    + "CONTAINS OBSERVATION o [openEHR-EHR-OBSERVATION.blood_pressure.v1]"
                    + "WHERE o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/value > 140",
            "Select o/data[at0002]/events[at0003] as systolic "
                    + "from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.encounter.v1] "
                    + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                    + "where (o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/value > 140 "
                    + "and e/ehr_id/value = '1234')",
            "testDump"),
    WHERE_EXPRESSION_WITH_PARENTHESIS(
            "select e/ehr_id, a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude, a_a/data[at0002]/events[at0003]/time/value "
                    + "from EHR e "
                    + "contains COMPOSITION a "
                    + "contains OBSERVATION a_a[openEHR-EHR-OBSERVATION.body_temperature.v1] "
                    + "where a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude>38 "
                    + "AND  a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C' "
                    + "AND e/ehr_id/value MATCHES {"
                    + "'849bf097-bd16-44fc-a394-10676284a012',"
                    + "'34b2e263-00eb-40b8-88f1-823c87096457'}"
                    + "OR (a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C' AND a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C')",
            "Select e/ehr_id as F1, a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as F2, a_a/data[at0002]/events[at0003]/time/value as F3 "
                    + "from EHR e "
                    + "contains COMPOSITION a "
                    + "contains OBSERVATION a_a[openEHR-EHR-OBSERVATION.body_temperature.v1] "
                    + "where (("
                    + "a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude > 38 "
                    + "and a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C' "
                    + "and e/ehr_id/value matches {'849bf097-bd16-44fc-a394-10676284a012','34b2e263-00eb-40b8-88f1-823c87096457'}) "
                    + "or (a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C' and a_a/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/units = '°C'))",
            "testWhereExpressionWithParenthesis");

    private final String testAql;
    private final String expectedAql;

    AqlExpressionTestData(String testAql, String expectedAql, String description) {
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
