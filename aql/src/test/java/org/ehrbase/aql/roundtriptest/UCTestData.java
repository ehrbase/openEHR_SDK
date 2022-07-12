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
 *     href="https://github.com/ehrbase/ehrbase/tree/v0.21.1/service/src/test/java/org/ehrbase/aql/sql/queryimpl/translator/testcase">UC*
 *     in ehrbase</a>
 */
public enum UCTestData implements AqlTestDto {
    UC1("select e/ehr_id/value from EHR e", "Select e/ehr_id/value as F1 from EHR e", "UC1"),
    UC2(
            "select e/ehr_id/value from EHR e " + "where e/ehr_id/value = '30580007'",
            "Select e/ehr_id/value as F1 from EHR e " + "where e/ehr_id/value = '30580007'",
            "UC1"),
    UC3(
            "select c/composer/name from EHR e " + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "Select c/composer/name as F1 from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "UC3"),
    UC4(
            "select c/composer/name from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "order by c/context/start_time/value DESC",
            "Select c/composer/name as F1 from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "order by c/context/start_time/value DESCENDING",
            "UC4"),
    UC5(
            "select a/description[at0001]/items[at0002]/value/value from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "Select a/description[at0001]/items[at0002]/value/value as F1 from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "UC5"),
    UC6(
            "select a/description[at0001]/items[at0002]/value/value as description from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + "order by description ASC",
            "Select a/description[at0001]/items[at0002]/value/value as description from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + " order by a/description[at0001]/items[at0002]/value/value ASCENDING",
            "UC6"),
    UC7(
            "select a/description[at0001]/items[at0002]/value/value from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + "where a/description[at0001]/items[at0002]/value/value = 'Hepatitis A'",
            "Select a/description[at0001]/items[at0002]/value/value as F1 from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + " where a/description[at0001]/items[at0002]/value/value = 'Hepatitis A'",
            "UC7"),
    UC8(
            "select c from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "Select c as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "UC8"),
    UC9(
            "select a from EHR e " + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "Select a as F1 from EHR e " + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "UC9"),
    UC10(
            "select e/ehr_id/value from EHR e LIMIT 10 OFFSET 5",
            "Select e/ehr_id/value as F1 from EHR e LIMIT 10 OFFSET 5",
            "UC10"),
    UC11("select TOP 5 e/ehr_id/value from EHR e", "Select TOP 5 FORWARD e/ehr_id/value as F1 from EHR e", "UC11"),
    UC12(
            "select e/ehr_id/value from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + "where a/description[at0001]/items[at0002]/value/value matches {'Hepatitis A','Hepatitis B'} ",
            "Select e/ehr_id/value as F1 from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + " where a/description[at0001]/items[at0002]/value/value matches {'Hepatitis A','Hepatitis B'}",
            "UC12"),
    UC13(
            "select"
                    + "  count (d/description[at0001]/items[at0004]/value/magnitude) as count_magnitude"
                    + " from EHR e"
                    + "  contains COMPOSITION"
                    + "  contains ACTION d[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "Select"
                    + " COUNT(d/description[at0001]/items[at0004]/value/magnitude) as count_magnitude"
                    + " from EHR e"
                    + " contains COMPOSITION c0"
                    + " contains ACTION d[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "UC13"),
    UC14(
            "select c/composer/name from EHR e " + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.unknown.v1]",
            "Select c/composer/name as F1 from EHR e " + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.unknown.v1]",
            "UC14"),

    UC15(
            "select e/ehr_status/other_details from EHR e[ehr_id/value='2a3b673f-d1b1-44c5-9e38-dcadf67ff2fc']",
            "Select e/ehr_status/other_details as F1 from EHR e where e/ehr_id/value = '2a3b673f-d1b1-44c5-9e38-dcadf67ff2fc'",
            "UC15"),

    UC16(
            "select a/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value/value "
                    + "from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + "WHERE a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true "
                    + "OR "
                    + "("
                    + "a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true "
                    + "AND"
                    + " a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true"
                    + ")",
            "Select a/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value/value as F1 "
                    + "from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]"
                    + " where (a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true "
                    + "or "
                    + "("
                    + "a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true "
                    + "and"
                    + " a/description[at0001]/items[at0001]/items[at0002]/items[at0003]/value/value = true"
                    + "))",
            "UC16"),
    UC17(
            "select a from EHR e [ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1']"
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "Select a as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1] where e/ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1'",
            "UC17"),
    UC18(
            "select a from EHR e [ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1']"
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1] "
                    + "where c/template_id='openEHR-EHR-COMPOSITION.health_summary.v1'",
            "Select a as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1] where (c/template_id = 'openEHR-EHR-COMPOSITION.health_summary.v1' and e/ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1')",
            "UC18"),
    UC19(
            "select c/category from EHR e [ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1']"
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "Select c/category as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] where e/ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1'",
            "UC19"),
    UC20(
            "select c/category/defining_code from EHR e [ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1']"
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "Select c/category/defining_code as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] where e/ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1'",
            "UC20"),
    UC21(
            "select c/category/defining_code/terminology_id/value from EHR e [ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1']"
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]",
            "Select c/category/defining_code/terminology_id/value as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] where e/ehr_id/value = '4a7c01cf-bb1c-4d3d-8385-4ae0674befb1'",
            "UC21"),
    UC22(
            "select count(a/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value/value,"
                    + "a/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0004]/value/value)"
                    + "from EHR e "
                    + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]  "
                    + "contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "Select COUNT(a/description[at0001]/items[openEHR-EHR-CLUSTER.test_all_types.v1]/items[at0001]/items[at0002]/items[at0003]/value/value) as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] contains ACTION a[openEHR-EHR-ACTION.immunisation_procedure.v1]",
            "UC22"),
    UC23("SELECT count(e/time_created) FROM EHR e", "Select COUNT(e/time_created) as F1 from EHR e", "UC23"),

    UC24(
            "select c" + " from EHR e"
                    + " contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1]"
                    + " WHERE NOT EXISTS c/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]",
            "Select c as F1 from EHR e contains COMPOSITION c[openEHR-EHR-COMPOSITION.health_summary.v1] where NOT EXISTS c/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0]",
            "UC24");

    private final String testAql;
    private final String expectedAql;

    UCTestData(String testAql, String expectedAql, String description) {
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
