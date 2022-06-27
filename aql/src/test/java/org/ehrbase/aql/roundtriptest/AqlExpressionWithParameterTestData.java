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
 * @see <a href="https://github.com/ehrbase/ehrbase/blob/v0.21.1/service/src/test/java/org/ehrbase/aql/compiler/AqlExpressionWithParametersTest.java">AqlExpressionWithParametersTest in ehrbase</a>
 */
public enum AqlExpressionWithParameterTestData implements AqlTestDto {
    DUMP(
            "select o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0005, $nameValue1]/value/magnitude as diastolic, o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0004, $nameValue2]/value/magnitude as systolic "
                    + "from EHR e[ehr_id/value=$ehrId] "
                    + "contains COMPOSITION a "
                    + "contains OBSERVATION o_bp[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                    + "where o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude < $max_value "
                    + "and o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > $another_value",
            "Select o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0005,$nameValue1]/value/magnitude as diastolic, o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0004,$nameValue2]/value/magnitude as systolic "
                    + "from EHR e "
                    + "contains COMPOSITION a "
                    + "contains OBSERVATION o_bp[openEHR-EHR-OBSERVATION.blood_pressure.v1] "
                    + "where (o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude < $max_value "
                    + "and o_bp/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > $another_value "
                    + "and e/ehr_id/value = '$ehrId')",
            "testDump");

    private final String testAql;
    private final String expectedAql;

    AqlExpressionWithParameterTestData(String testAql, String expectedAql, String description) {
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
