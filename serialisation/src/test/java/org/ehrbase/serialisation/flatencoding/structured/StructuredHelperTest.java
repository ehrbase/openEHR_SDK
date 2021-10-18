/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.structured;

import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.flatencoding.FlatJson;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.composition.CompositionTestDataStructuredJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;


public class StructuredHelperTest {

    @Test
    public void convert() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.CORONA;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.CORONA;
        String templateId = OperationalTemplateTestData.CORONA_ANAMNESE.getTemplateId();


        test(structuredJson, simSDTJson, templateId);
    }

    @Test
    public void convertMultiList() throws IOException {

        CompositionTestDataStructuredJson structuredJson = CompositionTestDataStructuredJson.MULTI_LIST;
        CompositionTestDataSimSDTJson simSDTJson = CompositionTestDataSimSDTJson.MULTI_LIST;
        String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();


        test(structuredJson, simSDTJson, templateId);
    }
    private void test(CompositionTestDataStructuredJson structuredJson, CompositionTestDataSimSDTJson simSDTJson, String templateId) throws IOException {
        String flat =
                IOUtils.toString(structuredJson.getStream(), StandardCharsets.UTF_8);

        String actual = StructuredHelper.convert(flat);


        String expected =
                IOUtils.toString(simSDTJson.getStream(), StandardCharsets.UTF_8);


        FlatJson flatJson = new FlatJasonProvider(new TestDataTemplateProvider()).buildFlatJson(FlatFormat.SIM_SDT, templateId);

        List<String> errors = compere(flatJson.marshal( flatJson.unmarshal(actual)), expected);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.  assertThat(errors).filteredOn(s -> s.startsWith("Missing")).containsExactlyInAnyOrder();

        softAssertions.  assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();

        softAssertions.assertAll();
    }
}