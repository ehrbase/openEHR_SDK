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
package org.ehrbase.openehr.sdk.serialisation.flatencoding;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataStructuredJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class StructuredJsonTest {

    private static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

    @Test
    void testRoundTrip() throws IOException {
        CompositionTestDataStructuredJson testData = CompositionTestDataStructuredJson.MULTI_LIST;
        String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

        test(testData, templateId);
    }

    @Test
    void testRoundTripCorona() throws IOException {
        CompositionTestDataStructuredJson testData = CompositionTestDataStructuredJson.CORONA;
        String templateId = OperationalTemplateTestData.CORONA_ANAMNESE.getTemplateId();

        test(testData, templateId);
    }

    private void test(CompositionTestDataStructuredJson testData, String templateId) throws IOException {
        RMDataFormat cut = new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.STRUCTURED, templateId);

        String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
        Composition unmarshal = cut.unmarshal(flat);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(unmarshal).isNotNull();

        RMObjectValidator rmObjectValidator = new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

        softAssertions
                .assertThat(rmObjectValidator.validate(unmarshal))
                .filteredOn(m -> !m.getMessage().contains("Inv_null_flavour_indicated"))
                .containsExactlyInAnyOrder();

        String actual = cut.marshal(unmarshal);

        String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }
}
