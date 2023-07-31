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
package org.ehrbase.openehr.sdk.aql.util;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.junit.Test;

public class AqlUtilTest {

    @Test
    public void removeParameter() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where e/ehr_id/value = $ehrid""";
        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    public void removeParameterWithOr() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where  e/ehr_id/value = $ehrid
                or e/ehr_id/value = $ehrid2""";
        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid2");
    }

    @Test
    public void removeParameterWithTripleOr() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where e/ehr_id/value = $ehrid
                or e/ehr_id/value = $ehrid2
                or e/ehr_id/value = $ehrid3""";
        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 or e/ehr_id/value = $ehrid3)");
    }

    @Test
    public void removeParameterMultiple() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where e/ehr_id/value = $ehrid
                or e/ehr_id/value = $ehrid
                or e/ehr_id/value = $ehrid""";
        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    public void removeParameterWithMixedOrAnd() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where (
                  e/ehr_id/value = $ehrid
                  or (
                    e/ehr_id/value = $ehrid2
                    and e/ehr_id/value = $ehrid3
                  )
                )""";
        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3)");
    }

    @Test
    public void removeParameterWithMixedAndOr() {
        String aql =
                """
                Select c0 as F1, e/ehr_id/value
                from EHR e
                contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]
                where (
                  e/ehr_id/value = $ehrid
                  or (
                    e/ehr_id/value = $ehrid2
                    and e/ehr_id/value = $ehrid3
                  )
                )""";
        String actual = AqlUtil.removeParameter(aql, "ehrid2");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid3)");
    }

    @Test
    public void removeParameterParameterNotfound() {
        String aql =
                "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

        String actual = AqlUtil.removeParameter(aql, "ehrid9999");

        assertThat(actual).isEqualToIgnoringCase(aql);
    }

    @Test
    public void removeParameterNoWhere() {
        String aql =
                "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        String actual = AqlUtil.removeParameter(aql, "ehrid9999");

        assertThat(actual).isEqualToIgnoringCase(aql);
    }

    @Test
    public void containmentExpressionsByIdentifier() {
        AqlQuery aqlQuery = AqlQueryParser.parse(
                """
        SELECT o1/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude AS Systolic
        FROM EHR e
        CONTAINS (
          COMPOSITION
          and (
            (
              SECTION s_en[openEHR-EHR-SECTION.adhoc.v1, 'Vital Signs']
              NOT CONTAINS OBSERVATION o1[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]
            )
            or
            (
             SECTION s_de[openEHR-EHR-SECTION.adhoc.v1, 'Vitalzeichen']
             NOT CONTAINS OBSERVATION o2[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]
             )
          )
        )""");

        Map<String, AbstractContainmentExpression> cut = AqlUtil.containmentExpressionsByIdentifier(aqlQuery.getFrom());

        assertThat(cut).containsOnlyKeys("e", "s_en", "o_658", "s_de", "o_659");
    }
}
