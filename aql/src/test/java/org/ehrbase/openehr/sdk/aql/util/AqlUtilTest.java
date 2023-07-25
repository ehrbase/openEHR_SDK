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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.Test;

public class AqlUtilTest {

    @Test
    public void removeParameter() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid";

        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    public void removeParameterWithOr() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid2";

        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid2");
    }

    @Test
    public void removeParameterWithTripleOr() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid2 or e/ehr_id/value = $ehrid3";

        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 or e/ehr_id/value = $ehrid3)");
    }

    @Test
    public void removeParameterMultiple() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid or e/ehr_id/value = $ehrid";

        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    public void removeParameterWithMixedOrAnd() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

        String actual = AqlUtil.removeParameter(aql, "ehrid");

        assertThat(actual)
                .isEqualToIgnoringCase(
                        "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3)");
    }

    @Test
    public void removeParameterWithMixedAndOr() {

        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where  (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

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
}
