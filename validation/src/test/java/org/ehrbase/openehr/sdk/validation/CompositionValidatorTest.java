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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.xml.JAXBUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.openehr.sdk.validation.webtemplate.TestDataTemplateProvider;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

/**
 *
 */
@SuppressWarnings("java:S5976")
class CompositionValidatorTest {

    private final CompositionValidator validator = new CompositionValidator();

    @Test
    void validateInternationalPatientSummary() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.IPS);
        var composition = getComposition(CompositionTestDataCanonicalJson.IPS);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateReSPECT() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.RE_SPECT);
        var composition = getComposition(CompositionTestDataSimSDTJson.RE_SPECT);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validIsmTransition() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.ISM_VAILD);
        var composition = getComposition(CompositionTestDataSimSDTJson.ISM_VAILD);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void ismTransitionWithInvalidCurrentState() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.ISM_INVALID_STATE);
        var composition = getComposition(CompositionTestDataSimSDTJson.ISM_INVALID_STATE);

        var result = validator.validate(composition, template);
        assertFalse(result.isEmpty());
    }

    @Test
    void ismTransitionWithWrongState() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.ISM_WRONG);
        var composition = getComposition(CompositionTestDataSimSDTJson.ISM_WRONG);

        var result = validator.validate(composition, template);
        assertFalse(result.isEmpty());
    }

    @Test
    void missingIsmTransition() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.ISM_MISSING);
        var composition = getComposition(CompositionTestDataSimSDTJson.ISM_MISSING);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateInternationalPatientSummary_Invalid() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.IPS);
        var composition = getComposition(CompositionTestDataCanonicalJson.IPS_INVALID);

        var result = validator.validate(composition, template);
        assertEquals(11, result.size());
        result.forEach(System.out::println);
    }

    @Test
    void validateCorona() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.CORONA_ANAMNESE);
        var composition = getComposition(CompositionTestDataCanonicalJson.CORONA);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateDuration() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.DURATION_VALIDATION);
        var composition = getComposition(CompositionTestDataSimSDTJson.DURATION_VALIDATION);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void cardinality() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.SECTION_CARDINALITY);
        var composition = getComposition(CompositionTestDataCanonicalJson.SECTION_CARDINALITY);

        var result = validator.validate(composition, template);
        assertEquals(4, result.size());
        result.forEach(System.out::println);
    }

    @Test
    void allTypes() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.ALL_TYPES);
        var composition = getComposition(CompositionTestDataCanonicalJson.ALL_TYPES);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateElementWithChoice() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.VIROLOGY_FINDING);
        var composition = getComposition(CompositionTestDataCanonicalJson.CHOICE_ELEMENT);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void rippleConformance() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.RIPPLE_CONFORMANCE_TEST);
        var composition = getComposition("RIPPLE-ConformanceTest.xml");

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateLaboratoryTestReport() throws Exception {
        var composition = getComposition("IDCR-LabReportRAW1.xml");
        var template = getOperationalTemplate("IDCR-LaboratoryTestReport.opt");

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateProblemList() throws Exception {
        var composition = getComposition("IDCR Problem List.v1.xml");
        var template = getOperationalTemplate("IDCR Problem List.v1.opt");

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateAdverseReaction() throws Exception {
        var composition = getComposition("IDCR - Adverse Reaction List.v1.xml");
        var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void validateAdverseReaction_BadCodePhrase() throws Exception {
        var composition = getComposition("IDCR - Adverse Reaction List  Bad CodePhrase at0021.v1.xml");
        var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

        var result = validator.validate(composition, template);
        assertEquals(1, result.size());
        result.forEach(System.out::println);
    }

    @Test
    void validateAdverseReaction_BadCodedValue() throws Exception {
        var composition = getComposition("IDCR - Adverse Reaction List Bad Coded Value.v1.xml");
        var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

        var result = validator.validate(composition, template);
        assertEquals(1, result.size());
        result.forEach(System.out::println);
    }

    @Test
    void validateName() throws Exception {
        var composition = getCompositionJson("name-test.json");
        var template = getOperationalTemplate("name-test.ehrbase.org.v0.opt");

        var result = validator.validate(composition, template);
        assertEquals(0, result.size());
        result.forEach(System.out::println);
    }

    @Test
    void validateNameWrongType() throws Exception {
        var composition = getCompositionJson("name-test.json");
        var template = getOperationalTemplate("name-test.ehrbase.org.v0.opt");

        List<Cluster> objects = composition
                .itemsAtPath(
                        "/content[openEHR-EHR-OBSERVATION.name_test.v0]/data[at0001]/events[at0002]/data[at0003]/items")
                .stream()
                .map(Cluster.class::cast)
                .toList();

        repaceName(objects, "NameOne", new DvText("NameOne"));

        var result = validator.validate(composition, template);
        assertEquals(1, result.size());

        assertThat(result)
                .extracting(ConstraintViolation::getAqlPath, ConstraintViolation::getMessage)
                .containsExactly(new Tuple(
                        "/content[openEHR-EHR-OBSERVATION.name_test.v0]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.name_code.v0 and name/value='NameOne']/name",
                        "Expected a DV_CODED_TEXT but got DV_TEXT"));
    }

    private void repaceName(List<Cluster> cluster, String nameOne, DvText dvText) {

        cluster.stream().filter(c -> c.getName().getValue().equals(nameOne)).forEach(c -> c.setName(dvText));
    }

    @Test
    void compositionValidationCRSDK120() throws Exception {
        var template = getOperationalTemplate(OperationalTemplateTestData.BEFUND_DER_BLUTGASANALYSE);
        var composition = getComposition(CompositionTestDataCanonicalJson.CHOICE_DV_QUANTITY);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    @Test
    void exampleComposition() throws IOException, XmlException {
        var template = getOperationalTemplate(OperationalTemplateTestData.MINIMAL_ACTION_2);
        var composition = getComposition(CompositionTestDataCanonicalJson.MINIMAL_ACTION_2);

        var result = validator.validate(composition, template);
        assertTrue(result.isEmpty());
    }

    private Composition getComposition(CompositionTestDataCanonicalJson composition) throws IOException {
        return new CanonicalJson()
                .unmarshal(IOUtils.toString(composition.getStream(), StandardCharsets.UTF_8), Composition.class);
    }

    private Composition getComposition(CompositionTestDataSimSDTJson composition) throws IOException {
        return new FlatJasonProvider(new TestDataTemplateProvider())
                .buildFlatJson(FlatFormat.SIM_SDT, composition.getTemplate().getTemplateId())
                .unmarshal(IOUtils.toString(composition.getStream(), StandardCharsets.UTF_8), Composition.class);
    }

    private Composition getComposition(String name) throws IOException, JAXBException {
        var unmarshaller = JAXBUtil.createRMContext().createUnmarshaller();
        return (Composition) unmarshaller.unmarshal(new FileInputStream("./src/test/resources/composition/" + name));
    }

    private Composition getCompositionJson(String name) throws IOException {
        var unmarshaller = new CanonicalJson();
        return (Composition) unmarshaller.unmarshal(IOUtils.toString(
                new FileInputStream("./src/test/resources/composition/" + name), StandardCharsets.UTF_8));
    }

    private OPERATIONALTEMPLATE getOperationalTemplate(OperationalTemplateTestData template)
            throws IOException, XmlException {
        return TemplateDocument.Factory.parse(template.getStream()).getTemplate();
    }

    private OPERATIONALTEMPLATE getOperationalTemplate(String name) throws IOException, XmlException {
        return TemplateDocument.Factory.parse(new FileInputStream("./src/test/resources/operational_templates/" + name))
                .getTemplate();
    }
}
