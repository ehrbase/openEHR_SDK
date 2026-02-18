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
package org.ehrbase.openehr.sdk.validation;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.validation.webtemplate.TestDataTemplateProvider;
import org.junit.jupiter.api.Test;

/**
 *
 */
@SuppressWarnings("java:S5976")
class FlatValidationTest {

    private final LocatableValidator validator;

    private final TestDataTemplateProvider templateProvider;

    private final FlatJasonProvider flatJsonProvider;

    public FlatValidationTest() {
        validator = new LocatableValidator();
        templateProvider = new TestDataTemplateProvider();
        flatJsonProvider = new FlatJasonProvider(templateProvider);
    }

    @Test
    void roundTrip() throws IOException {
        var testData = CompositionTestDataSimSDTJson.CORONA_WITH_OTHER_PARTICIPATION;
        var templateId = "Corona_Anamnese";

        validate(templateId, testData);
    }

    @Test
    void roundTripFeederAudit() throws IOException {
        var testData = CompositionTestDataSimSDTJson.CORONA_WITH_FEEDER_AUDIT;
        var templateId = "Corona_Anamnese";

        validate(templateId, testData);
    }

    @Test
    void roundTripFeederAuditRaw() throws IOException {
        var testData = CompositionTestDataSimSDTJson.CORONA_WITH_FEEDER_AUDIT_RAW;
        var templateId = "Corona_Anamnese";

        validate(templateId, testData);
    }

    @Test
    void roundTripRaw() throws IOException {
        var testData = CompositionTestDataSimSDTJson.CORONA_WITH_RAW;
        var templateId = "Corona_Anamnese";

        validate(templateId, testData);
    }

    @Test
    void validateCorona() throws IOException {
        var testData = CompositionTestDataSimSDTJson.CORONA;
        var templateId = "Corona_Anamnese";

        validate(templateId, testData);
    }

    @Test
    void roundTripAction() throws IOException {
        var testData = CompositionTestDataSimSDTJson.EREACT_COVID_MANAGEMENT;
        var templateId = OperationalTemplateTestData.EREACT_COVID_MANAGEMENT.getTemplateId();

        validate(templateId, testData);
    }

    @Test
    void roundTripNCD() throws IOException {
        var testData = CompositionTestDataSimSDTJson.NCD;
        var templateId = OperationalTemplateTestData.NCD.getTemplateId();

        validate(templateId, testData);
    }

    @Test
    void roundTripVitalSigns() throws IOException {
        var testData = CompositionTestDataSimSDTJson.VITALSIGNS;
        var templateId = "EHRN Vital signs.v2";

        validate(templateId, testData);
    }

    @Test
    void roundTripIcd() throws IOException {
        var testData = CompositionTestDataSimSDTJson.ADVERSE_REACTION_LIST;
        var templateId = "Adverse Reaction List.v1";

        validate(templateId, testData);
    }

    @Test
    void roundMultiList() throws IOException {
        var testData = CompositionTestDataSimSDTJson.MULTI_LIST;
        var templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

        validate(templateId, testData);
    }

    @Test
    void roundNameWithAnd() throws IOException {
        var testData = CompositionTestDataSimSDTJson.WORD_WITH_AND;
        var templateId = OperationalTemplateTestData.WORD_WITH_AND.getTemplateId();

        validate(templateId, testData);
    }

    @Test
    void roundTripDeterioriationAssessment() throws IOException {
        var templateId = "EREACT - Deterioriation assessment.v0";
        var testData = CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT;

        validate(templateId, testData);
    }

    @Test
    void roundTripAll() throws IOException {
        var templateId = "test_all_types.en.v1";
        var testData = CompositionTestDataSimSDTJson.ALL_TYPES;

        validate(templateId, testData);
    }

    @Test
    void roundTripAlt() throws IOException {
        var templateId = "AlternativeEvents";
        var testData = CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS;

        validate(templateId, testData);
    }

    @Test
    void roundTripMulti() throws IOException {
        var templateId = "ehrbase_multi_occurrence.de.v1";
        var testData = CompositionTestDataSimSDTJson.MULTI_OCCURRENCE;

        validate(templateId, testData);
    }

    @Test
    void roundTripMissingCount() throws IOException {
        var templateId = "ehrbase_multi_occurrence.de.v1";
        var testData = CompositionTestDataSimSDTJson.MISSING_COUNT;

        validate(templateId, testData);
    }

    @Test
    void validateNested() throws IOException {
        var templateId = "nested.en.v1";
        var testData = CompositionTestDataSimSDTJson.NESTED;

        validate(templateId, testData);
    }

    private void validate(String templateId, CompositionTestDataSimSDTJson testData) throws IOException {
        var unmarshaller = flatJsonProvider.buildFlatJson(FlatFormat.SIM_SDT, templateId);

        var json = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
        var composition = unmarshaller.unmarshal(json);

        var existingTemplate = templateProvider.find(templateId);
        assertTrue(existingTemplate.isPresent());

        var result = validator.validate(composition, existingTemplate.get());
        assertTrue(result.isEmpty());
    }
}
