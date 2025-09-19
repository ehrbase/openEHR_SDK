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
package org.ehrbase.openehr.sdk.serialisation.flatencoding;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import com.nedap.archie.rmobjectvalidator.ValidationConfiguration;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.openehr.sdk.serialisation.MarshalOption;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataStructuredJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

class StructuredJsonTest {

    private static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

    private static RMDataFormat rmDataFormat(OperationalTemplateTestData operationalTemplateTestData) {
        return RMDataFormat.sdtStructuredJSON(templateProvider, operationalTemplateTestData.getTemplateId());
    }

    private static Composition unmarshall(
            CompositionTestDataStructuredJson structuredJson, OperationalTemplateTestData operationalTemplateTestData) {

        String value = null;
        try {
            value = IOUtils.toString(structuredJson.getStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            fail(e);
        }
        assertThat(value).isNotNull();
        return rmDataFormat(operationalTemplateTestData).unmarshal(value);
    }

    @Test
    void marshallDefaultPrettyPrint() {

        OperationalTemplateTestData operationalTemplateTestData = OperationalTemplateTestData.MULTI_LIST;
        Composition composition = unmarshall(CompositionTestDataStructuredJson.MULTI_LIST, operationalTemplateTestData);
        String value = rmDataFormat(operationalTemplateTestData).marshal(composition);

        assertStartsWith(
                value,
                """
                {
                  "multi_list" : {
                    "category" : [ {
                      "|code" : "433",
                      "|value" : "event",
                      "|terminology" : "openehr"
                    } ],
                    "context" :""");
    }

    /**
     * Line separators of prefix are converted to system default for comparison
     * @param actual
     * @param prefix
     */
    private static void assertStartsWith(String actual, String prefix) {
        assertThat(actual).startsWith(prefix.replaceAll("\\R", System.lineSeparator()));
    }

    @Test
    void marshallWithOptionsNoPrettyPrint() {

        OperationalTemplateTestData operationalTemplateTestData = OperationalTemplateTestData.CORONA_ANAMNESE;
        Composition composition = unmarshall(CompositionTestDataStructuredJson.CORONA, operationalTemplateTestData);
        String value = rmDataFormat(operationalTemplateTestData).marshalWithOptions(composition);

        assertThat(value)
                .startsWith(
                        """
                {"bericht":{"category":[{"|code":"433","|value":"event","|terminology":"openehr"}],"context":""");
    }

    @Test
    void marshallWithOptionsPrettyPrint() {

        OperationalTemplateTestData operationalTemplateTestData = OperationalTemplateTestData.CORONA_ANAMNESE;
        Composition composition = unmarshall(CompositionTestDataStructuredJson.CORONA, operationalTemplateTestData);
        String value =
                rmDataFormat(operationalTemplateTestData).marshalWithOptions(composition, MarshalOption.PRETTY_PRINT);

        assertStartsWith(
                value,
                """
                        {
                          "bericht" : {
                            "category" : [ {
                              "|code" : "433",
                              "|value" : "event",
                              "|terminology" : "openehr"
                            } ],
                            "context" :""");
    }

    @Test
    void testRoundTrip() throws IOException {

        test(CompositionTestDataStructuredJson.MULTI_LIST, OperationalTemplateTestData.MULTI_LIST);
    }

    @Test
    void testRoundTripCorona() throws IOException {

        test(CompositionTestDataStructuredJson.CORONA, OperationalTemplateTestData.CORONA_ANAMNESE);
    }

    private void test(
            CompositionTestDataStructuredJson testData, OperationalTemplateTestData operationalTemplateTestData)
            throws IOException {

        Composition unmarshal = unmarshall(testData, operationalTemplateTestData);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(unmarshal).isNotNull();

        RMObjectValidator rmObjectValidator = new RMObjectValidator(
                ArchieRMInfoLookup.getInstance(),
                s -> null,
                new ValidationConfiguration.Builder()
                        .failOnUnknownTerminologyId(false)
                        .build());

        softAssertions
                .assertThat(rmObjectValidator.validate(unmarshal))
                .filteredOn(m -> !m.getMessage().contains("Inv_null_flavour_indicated"))
                .containsExactlyInAnyOrder();

        String actual = rmDataFormat(operationalTemplateTestData).marshal(unmarshal);

        String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

        JSONAssert.assertEquals(expected, actual, JSONCompareMode.NON_EXTENSIBLE);
    }
}
