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
package org.ehrbase.aql.render;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class AqlRenderTest {

    @Test
    void renderAS() {

        String aql =
                "SELECT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM ehr e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderDISTINCT() {

        String aql =
                "SELECT DISTINCT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM ehr e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderSimplePath() {

        String aql =
                "SELECT o/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM ehr e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderPathWithName() {

        String aql = "SELECT "
                + "c/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value AS has_cough "
                + "FROM ehr e "
                + "CONTAINS Composition c [openEHR-EHR-COMPOSITION.report.v1]";

        test(
                aql,
                "SELECT c/content[openEHR-EHR-SECTION.adhoc.v1,'Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0,'Husten']/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value AS has_cough FROM ehr e CONTAINS Composition c[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    void renderPathWithArbitraryPath() {

        String aql =
                "SELECT o/data[at0001]/events[at0002 and time/value>'2021-12-03T16:05:19.513542+01:00']/data[at0042]/items[at0057]/value AS Presence_of_exposure, e/ehr_id/value AS Ehr_id FROM ehr e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    @Test
    void renderPrimitive() {

        String aql =
                "SELECT 1, e/ehr_id/value AS Ehr_id FROM ehr e CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.exposure_assessment.v0]";

        test(aql, aql);
    }

    private static void test(String aql, String expected) {
        AqlDto aqlDto = new AqlToDtoParser().parse(aql);

        String render = new AqlRender(aqlDto).render();

        assertThat(render).isEqualTo(expected);
    }
}
