/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.xml.JAXBUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBException;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.CompositionValidator;
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
  void testValidateInternationalPatientSummary() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.IPS);
    var composition = getComposition(CompositionTestDataCanonicalJson.IPS);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateInternationalPatientSummary_Invalid() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.IPS);
    var composition = getComposition(CompositionTestDataCanonicalJson.IPS_INVALID);

    var result = validator.validate(composition, template);
    assertEquals(11, result.size());
    result.forEach(System.out::println);
  }

  @Test
  void testValidateCorona() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.CORONA_ANAMNESE);
    var composition = getComposition(CompositionTestDataCanonicalJson.CORONA);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testCardinality() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.SECTION_CARDINALITY);
    var composition = getComposition(CompositionTestDataCanonicalJson.SECTION_CARDINALITY);

    var result = validator.validate(composition, template);
    assertEquals(4, result.size());
    result.forEach(System.out::println);
  }

  @Test
  void testAllTypes() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.ALL_TYPES);
    var composition = getComposition(CompositionTestDataCanonicalJson.ALL_TYPES);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateElementWithChoice() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.VIROLOGY_FINDING);
    var composition = getComposition(CompositionTestDataCanonicalJson.CHOICE_ELEMENT);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testRippleConformance() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.RIPPLE_CONFORMANCE_TEST);
    var composition = getComposition("RIPPLE-ConformanceTest.xml");

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateLaboratoryTestReport() throws Exception {
    var composition = getComposition("IDCR-LabReportRAW1.xml");
    var template = getOperationalTemplate("IDCR-LaboratoryTestReport.opt");

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateProblemList() throws Exception {
    var composition = getComposition("IDCR Problem List.v1.xml");
    var template = getOperationalTemplate("IDCR Problem List.v1.opt");

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateAdverseReaction() throws Exception {
    var composition = getComposition("IDCR - Adverse Reaction List.v1.xml");
    var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidateAdverseReaction_BadCodePhrase() throws Exception {
    var composition = getComposition("IDCR - Adverse Reaction List  Bad CodePhrase at0021.v1.xml");
    var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

    var result = validator.validate(composition, template);
    assertEquals(1, result.size());
    result.forEach(System.out::println);
  }

  @Test
  void testValidateAdverseReaction_BadCodedValue() throws Exception {
    var composition = getComposition("IDCR - Adverse Reaction List Bad Coded Value.v1.xml");
    var template = getOperationalTemplate("IDCR - Adverse Reaction List.v1.opt");

    var result = validator.validate(composition, template);
    assertEquals(1, result.size());
    result.forEach(System.out::println);
  }

  @Test
  void compositionValidationCRSDK120() throws Exception {
    var template = getOperationalTemplate(OperationalTemplateTestData.BEFUND_DER_BLUTGASANALYSE);
    var composition = getComposition(CompositionTestDataCanonicalJson.CHOICE_DV_QUANTITY);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  @Test
  void testExampleComposition() throws IOException, XmlException {
    var template = getOperationalTemplate(OperationalTemplateTestData.MINIMAL_ACTION_2);
    var composition = getComposition(CompositionTestDataCanonicalJson.MINIMAL_ACTION_2);

    var result = validator.validate(composition, template);
    assertTrue(result.isEmpty());
  }

  private Composition getComposition(CompositionTestDataCanonicalJson composition)
      throws IOException {
    return new CanonicalJson().unmarshal(
        IOUtils.toString(composition.getStream(), StandardCharsets.UTF_8), Composition.class);
  }

  private Composition getComposition(String name) throws IOException, JAXBException {
    var unmarshaller = JAXBUtil.createRMContext().createUnmarshaller();
    return (Composition) unmarshaller.unmarshal(
        new FileInputStream("./src/test/resources/composition/" + name));
  }

  private OPERATIONALTEMPLATE getOperationalTemplate(OperationalTemplateTestData template)
      throws IOException, XmlException {
    return TemplateDocument.Factory.parse(template.getStream()).getTemplate();
  }

  private OPERATIONALTEMPLATE getOperationalTemplate(String name) throws IOException, XmlException {
    return TemplateDocument.Factory.parse(
        new FileInputStream("./src/test/resources/operational_templates/" + name)).getTemplate();
  }
}
