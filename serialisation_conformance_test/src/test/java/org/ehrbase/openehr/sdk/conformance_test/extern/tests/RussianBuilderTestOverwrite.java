/*
 * Copyright (c) 2021 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import care.better.platform.web.template.RussianBuilderTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.ehrbase.openehr.sdk.conformance_test.extern.Helper;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.junit.Test;

public class RussianBuilderTestOverwrite extends RussianBuilderTest {

    @Override
    /*
    contains null values https://jira.vitagroup.ag/browse/CDR-12
    */
    public void testCardioligistExaminationValidation() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void testPrevaccinalExaminationValidation() throws Exception {}

    @Override
    /*
    see https://jira.vitagroup.ag/browse/PEM-492
    */
    public void missingInstruction() throws Exception {}

    @Override
    @Test
    public void testVaccinationCardYear() throws Exception {

        String template = this.getFileContent("/res/openEHR-EHR-COMPOSITION.vaccination_card.opt");
        String structuredJson = this.getFileContent("/res/ru-composition.json")
                .replace(",\"end_time\":[\"2019-05-06T14:56:57.670+03:00\"]", "");

        Composition marshal =
                Helper.getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredJson);

        String flatJson = Helper.getFlatJson(template, FlatFormat.SIM_SDT).marshal(marshal);

        Map<String, Object> retrieve = new ObjectMapper().readValue(flatJson, Map.class);
        Assertions.assertThat(retrieve)
                .contains(Assertions.entry(
                        "карта_профилактических_прививок/туберкулезные_пробы/заготовка_заголовка:0/результат_иммунодиагностики/дата",
                        "2013"));
    }

    @Override
    /*
    see https://jira.vitagroup.ag/browse/CDR-132
    */
    public void testTherapistExaminationContent() throws Exception {}
}
