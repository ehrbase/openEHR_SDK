/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.render;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class AqlRendererTest {

    @Test
    void renderAS() {
        String aql =
                "SELECT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM EHR e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderLoweCase() {
        String aql =
                "SELECT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value, e/ehr_id/value FROM EHR e CONTAINS observation o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderDISTINCT() {
        String aql =
                "SELECT DISTINCT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM EHR e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderSimplePath() {
        String aql =
                "SELECT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM EHR e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderPathWithName() {

        String aql = "SELECT "
                + "c[openEHR-EHR-COMPOSITION.report.v1 and name/value='something']/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value AS has_cough "
                + "FROM EHR e "
                + "CONTAINS Composition c [openEHR-EHR-COMPOSITION.report.v1]";

        test(
                aql,
                "SELECT c[openEHR-EHR-COMPOSITION.report.v1, 'something']/content[openEHR-EHR-SECTION.adhoc.v1, 'Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0, 'Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value AS has_cough FROM EHR e CONTAINS Composition c[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    void renderPathWithArbitraryPath() {
        String aql =
                "SELECT o/data[at0001]/events[at0002 AND time/value>'2021-12-03T16:05:19.513542+01:00']/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM EHR e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderPrimitive() {
        String aql =
                "SELECT 1, e/ehr_id/value AS Ehr_id FROM EHR e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderAggregateFunction() {
        String aql = "SELECT"
                + " e/ehr_id/value AS EhrId,"
                + " MAX(o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude) AS max_Systolic,"
                + " MIN(o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude) AS min_Systolic,"
                + " AVG(o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude) AS mean_Systolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]";

        test(aql, aql);
    }

    @Test
    void renderSingleRowFunction() {
        String aql = "SELECT"
                + " CONCAT_WS(':', $Pos, o/data[at0001]/events[at0006]/state[at0007]/items[at0008]/value/value) AS position_"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]";

        test(aql, aql);
    }

    @Test
    void renderContainAnd() {
        String aql = "SELECT"
                + " e/ehr_id/value AS Ehr_id,"
                + " c/context/start_time AS start_time,"
                + " o1/data[at0001]/events[at0006]/state[at0007]/items[at0008]/value/value AS position_,"
                + " o2/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude AS body_weight"
                + " FROM EHR e"
                + " CONTAINS COMPOSITION c"
                + " CONTAINS"
                + " ((OBSERVATION o1[openEHR-EHR-OBSERVATION.blood_pressure.v2] CONTAINS POINT_EVENT) AND OBSERVATION o2[openEHR-EHR-OBSERVATION.body_weight.v2])";

        test(aql, aql);
    }

    @Test
    void renderContainAnd2() {
        String aql = "SELECT"
                + " e/ehr_id/value AS Ehr_id,"
                + " c/context/start_time AS start_time,"
                + " o1/data[at0001]/events[at0006]/state[at0007]/items[at0008]/value/value AS position_"
                + " FROM EHR e"
                + " CONTAINS COMPOSITION c"
                + " CONTAINS"
                + " (OBSERVATION o1[openEHR-EHR-OBSERVATION.blood_pressure.v2] CONTAINS POINT_EVENT AND CLUSTER)";

        String expected = "SELECT"
                + " e/ehr_id/value AS Ehr_id,"
                + " c/context/start_time AS start_time,"
                + " o1/data[at0001]/events[at0006]/state[at0007]/items[at0008]/value/value AS position_"
                + " FROM EHR e"
                + " CONTAINS COMPOSITION c"
                + " CONTAINS OBSERVATION o1[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " CONTAINS (POINT_EVENT AND CLUSTER)";

        test(aql, expected);
    }

    @Test
    void renderContainNOT() {
        String aql = "SELECT"
                + " e/ehr_id/value AS Ehr_id"
                + " FROM EHR e"
                + " NOT CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        test(aql, aql);
    }

    @Test
    void renderWhere() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > 40";

        test(aql, aql);
    }

    @Test
    void renderWherePath() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude AS Diastolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude";

        test(aql, aql);
    }

    @Test
    void renderWhereParameter() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude AS Diastolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > $systolicCriteria";

        test(aql, aql);
    }

    @Test
    void renderWhereFunction() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " CEIL(o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude) = 50";

        test(aql, aql);
    }

    @Test
    void renderWhereExist() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " EXISTS o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude";

        test(aql, aql);
    }

    @Test
    void renderWhereAND() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude AS Diastolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " (o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude > 50"
                + " AND"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude <= 80)";

        test(aql, aql);
    }

    @Test
    void renderWhereNot() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " WHERE"
                + " NOT EXISTS o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude";

        test(aql, aql);
    }

    @Test
    void renderWhereLike() {
        String aql = "SELECT"
                + " o/data[at0002]/events[at0003.1]/state[at0008.1]/items[at0009]/value/value AS state_of_dress"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.body_weight.v2]"
                + " WHERE"
                + " o/data[at0002]/events[at0003.1]/state[at0008.1]/items[at0009]/value/value LIKE 'Fully?'";

        test(aql, aql);
    }

    @Test
    void renderWhereMatches() {
        String aql = "SELECT"
                + " o/data[at0002]/events[at0003.1]/state[at0008.1]/items[at0009]/value/value AS state_of_dress"
                + " FROM EHR e"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.body_weight.v2]"
                + " WHERE"
                + " o/data[at0002]/events[at0003.1]/state[at0008.1]/items[at0009]/value/defining_code/code_string MATCHES {'at0028', 'at0010'}";

        test(aql, aql);
    }

    @Test
    void renderOrderBy() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude AS Diastolic"
                + " FROM EHR e"
                + " CONTAINS COMPOSITION c"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " ORDER BY"
                + " c/context/start_time DESC,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude ASC";

        test(aql, aql);
    }

    @Test
    void renderLimit() {
        String aql = "SELECT"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value/magnitude AS Systolic,"
                + " o/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value/magnitude AS Diastolic"
                + " FROM EHR e"
                + " CONTAINS COMPOSITION c"
                + " CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]"
                + " ORDER BY"
                + " c/context/start_time/value DESC"
                + " LIMIT 10 OFFSET 5";

        test(aql, aql);
    }

    @Test
    void renderVersionedObject() {
        String aql = "SELECT v/time_created AS first_created " + "FROM EHR e "
                + "CONTAINS VERSIONED_OBJECT v CONTAINS COMPOSITION";

        test(aql, aql);
    }

    @Test
    void renderLatestVersion() {
        String aql = "SELECT v/commit_audit/time_committed/value AS time_commited, o AS observation "
                + "FROM EHR e "
                + "CONTAINS VERSION v[LATEST_VERSION] "
                + "CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]";

        test(aql, aql);
    }

    @Test
    void renderALLVersion() {
        String aql = "SELECT v/commit_audit/time_committed/value AS time_commited, o AS observation "
                + "FROM EHR e "
                + "CONTAINS VERSION v[ALL_VERSIONS] "
                + "CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]";

        test(aql, aql);
    }

    @Test
    void renderPredicateVersion() {
        String aql = "SELECT v/commit_audit/time_committed/value AS time_commited, o AS observation "
                + "FROM EHR e "
                + "CONTAINS VERSION v[commit_audit/time_committed>'2021-12-03T16:05:19.514097+01:00'] "
                + "CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.blood_pressure.v2]";

        test(aql, aql);
    }

    @Test
    void renderPredicateTerminology() {
        String aql = "SELECT c/context/start_time, p/data/items[at0002]/value "
                + "FROM EHR e[ehr_id/value='1234'] "
                + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.problem_list.v1] "
                + "CONTAINS EVALUATION p[openEHR-EHR-EVALUATION.problem-diagnosis.v1] "
                + "WHERE "
                + "c/name/value='Current Problems' AND "
                + "p/data/items[at0002]/value/defining_code/code_string MATCHES TERMINOLOGY('expand','hl7.org/fhir/4.0','http://snomed.info/sct?fhir_vs=isa/50697003')";

        test(
                aql,
                "SELECT c/context/start_time, p/data/items[at0002]/value FROM EHR e[ehr_id/value='1234'] "
                        + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.problem_list.v1] CONTAINS EVALUATION p[openEHR-EHR-EVALUATION.problem-diagnosis.v1] "
                        + "WHERE (c/name/value = 'Current Problems' AND "
                        + "p/data/items[at0002]/value/defining_code/code_string MATCHES {TERMINOLOGY('expand','hl7.org/fhir/4.0','http://snomed.info/sct?fhir_vs=isa/50697003')})");
    }

    @Test
    void renderPredicateTerminologyII() {
        String aql = "SELECT c/context/start_time, p/data/items[at0002]/value FROM EHR e[ehr_id/value='1234'] "
                + "CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.problem_list.v1] CONTAINS EVALUATION p[openEHR-EHR-EVALUATION.problem-diagnosis.v1] "
                + "WHERE (c/name/value = 'Current Problems' AND "
                + "p/data/items[at0002]/value/defining_code/code_string MATCHES {TERMINOLOGY('expand','hl7.org/fhir/4.0','http://snomed.info/sct?fhir_vs=isa/50697003')})";

        test(aql, aql);
    }

    private static void test(String aql, String expected) {
        AqlQuery aqlQuery = AqlQueryParser.parse(aql);

        String rendered = AqlRenderer.render(aqlQuery);

        assertThat(rendered).isEqualTo(expected);
    }

    @Test
    void encodeString() {
        assertThat(AqlRenderer.encodeString(null)).isNull();

        assertThat(AqlRenderer.encodeString("foo bar baz")).isEqualTo("'foo bar baz'");
        assertThat(AqlRenderer.encodeString("☮€𝄞")).isEqualTo("'☮€𝄞'");

        assertThat(AqlRenderer.encodeString("f'oo\tbä\r\nbaz\b\\\"")).isEqualTo("'f\\'oo\\tbä\\r\\nbaz\\b\\\\\"'");
    }
}
