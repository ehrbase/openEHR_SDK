/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.flatencoding.structured;

import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.composition.CompositionTestDataStructuredJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class StructuredHelperTest {

    @Test
    public void convert() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.CORONA;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.CORONA;
        String templateId = OperationalTemplateTestData.CORONA_ANAMNESE.getTemplateId();

        testStructuredToFlat(structuredJson, simSDTJson, templateId);
    }

    @Test
    public void convertRevert() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.CORONA;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.CORONA;

        testFlatToStructured(structuredJson, simSDTJson);
    }

    private void testFlatToStructured(
            CompositionTestDataStructuredJson structuredJson, CompositionTestDataSimSDTJson simSDTJson)
            throws IOException {
        String flat = IOUtils.toString(simSDTJson.getStream(), StandardCharsets.UTF_8);

        String actual = StructuredHelper.convertFlatToStructured(flat);

        String expected = IOUtils.toString(structuredJson.getStream(), StandardCharsets.UTF_8);
        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }

    @Test
    public void convertMultiList() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.MULTI_LIST;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.MULTI_LIST;
        String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

        testStructuredToFlat(structuredJson, simSDTJson, templateId);
    }

    @Test
    public void convertRevertMultiList() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.MULTI_LIST;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.MULTI_LIST;

        testFlatToStructured(structuredJson, simSDTJson);
    }

    private void testStructuredToFlat(
            CompositionTestDataStructuredJson structuredJson,
            CompositionTestDataSimSDTJson simSDTJson,
            String templateId)
            throws IOException {
        String flat = IOUtils.toString(structuredJson.getStream(), StandardCharsets.UTF_8);

        String actual = StructuredHelper.convertStructuredToFlat(flat);

        String expected = IOUtils.toString(simSDTJson.getStream(), StandardCharsets.UTF_8);

        RMDataFormat flatJson =
                new FlatJasonProvider(new TestDataTemplateProvider()).buildFlatJson(FlatFormat.SIM_SDT, templateId);

        List<String> errors = compere(flatJson.marshal(flatJson.unmarshal(actual)), expected);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .containsExactlyInAnyOrder();

        softAssertions.assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();

        softAssertions.assertAll();
    }
}
