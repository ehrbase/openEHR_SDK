/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.webtemplate.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.test_data.webtemplate.WebTemplateTestData;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInputValue;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class OPTParserTest {

  @Test
  public void parseCoronaAnamnese() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(WebTemplateTestData.CORONA.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "LocalizedNames not equal [de=event] != [de=] in inputValue.code:433 id=category aql=/category",
          "InContext not equal null != true in  id=math_function aql=/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_event.v0]/data[at0001]/events[at0002]/math_function",
          "InContext not equal null != true in  id=width aql=/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_event.v0]/data[at0001]/events[at0002]/width"
        });
  }

  @Test
  public void parseTestingTemplateN() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.TESTING_TEMPLATE_N.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.TESTING_TEMPLATE_N.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "Extra Node id=external_terminology aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0016]/value",
          "Missing Node id=external_terminology aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0016 and name/value='External terminology']/value",
          "InputValue not equal 1234:Hello world != 1234:null in id=testing_dv_text aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0026]/value",
          "LocalizedNames not equal [en=event] != [en=event, sl=] in inputValue.code:433 id=category aql=/category",
          "LocalizedNames not equal [en=Hello world] != [en=, sl=] in inputValue.code:1234 id=testing_dv_text aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0026]/value",
          "LocalizedDescriptions not equal {en=} != {} in inputValue.code:1234 id=testing_dv_text aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0026]/value",
          "DefaultValue not equal null != 1234 in input.suffix:code id=testing_dv_text aql=/content[openEHR-EHR-OBSERVATION.testing.v1]/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.testing.v1]/items[at0026]/value"
        });
  }

  @Test
  public void parseGECCODiagnose() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.GECCO_DIAGNOSE.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.GECCO_DIAGNOSE.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors =
        compareWebTemplate(actual, expected).stream()
            .filter(
                f ->
                    !f.startsWith("LocalizedDescriptions not equal")
                        && !f.startsWith("LocalizedNames not equal")
                        && !f.startsWith("Annotations not equal"))
            .collect(Collectors.toList());

    checkErrors(
        errors,
        new String[] {
          "Extra Input code:TEXT in id=name_des_problems_der_diagnose aql=/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Vorliegende Diagnose']/data[at0001]/items[at0002]/value",
          "Extra Input value:TEXT in id=name_des_problems_der_diagnose aql=/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Vorliegende Diagnose']/data[at0001]/items[at0002]/value",
          "Missing Input null:TEXT in id=name_des_problems_der_diagnose aql=/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1 and name/value='Vorliegende Diagnose']/data[at0001]/items[at0002]/value"
        });
  }

  @Test
  public void parseAltEvents() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALT_EVENTS.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.ALTERNATIVE_EVENTS.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);

    // Nodes wich are generated by the parser but are not in the example
    checkErrors(
        errors,
        new String[] {
          "LocalizedNames not equal [de=event] != [de=] in inputValue.code:433 id=category aql=/category",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=1000000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=1000000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:g id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0026]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=1000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=1000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:kg id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0026]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=2000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=2000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:[lb_av] id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0026]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=1000000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=1000000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:g id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=1000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=1000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:kg id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=2000.0, maxOp=LT_EQ}, pattern='null'} != WebTemplateValidation{precision=null, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=2000, maxOp=LT_EQ}, pattern='null'} in inputValue.code:[lb_av] id=gewicht aql=/content[openEHR-EHR-OBSERVATION.body_weight.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value"
        });
  }

  @Test
  public void parseMultiOccurrence() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(
        errors,
        new String[] {
          "Validation not equal WebTemplateValidation{precision=WebTemplateValidationInterval{min=1, minOp=GT_EQ, max=1, maxOp=LT_EQ}, range=WebTemplateValidationInterval{min=0.0, minOp=GT_EQ, max=100.0, maxOp=LT}, pattern='null'} != WebTemplateValidation{precision=WebTemplateValidationInterval{min=1, minOp=GT_EQ, max=1, maxOp=LT_EQ}, range=WebTemplateValidationInterval{min=0, minOp=GT_EQ, max=100, maxOp=LT}, pattern='null'} in inputValue.code:Cel id=temperature aql=/content[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value",
          "Validation not equal WebTemplateValidation{precision=WebTemplateValidationInterval{min=1, minOp=GT_EQ, max=1, maxOp=LT_EQ}, range=WebTemplateValidationInterval{min=30.0, minOp=GT_EQ, max=200.0, maxOp=LT}, pattern='null'} != WebTemplateValidation{precision=WebTemplateValidationInterval{min=1, minOp=GT_EQ, max=1, maxOp=LT_EQ}, range=WebTemplateValidationInterval{min=30, minOp=GT_EQ, max=200, maxOp=LT}, pattern='null'} in inputValue.code:[degF] id=temperature aql=/content[openEHR-EHR-OBSERVATION.body_temperature.v2]/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value"
        });
  }

  @Test
  public void parseAny() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.GECCO_SEROLOGISCHER_BEFUND.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.GECCO_SEROLOGISCHER_BEFUND.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(
        errors.stream()
            .filter(
                e ->
                    List.of(
                            "Annotations not equal",
                            "InputValue not equal",
                            "LocalizedNames not equal",
                            "LocalizedDescriptions not equal")
                        .stream()
                        .noneMatch(e::startsWith))
            .collect(Collectors.toList()),
        new String[] {});
  }

  @Test
  public void parseInitialAssessment() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.INITIAL_ASSESSMENT.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.INITIAL_ASSESSMENT.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(errors, new String[] {});
  }

  @Test
  public void parseAddiction() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ADDICTION.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(WebTemplateTestData.ADDICTION.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(
        errors,
        new String[] {
          "LocalizedNames not equal [de=event] != [de=] in inputValue.code:433 id=category aql=/category"
        });
  }

  @Test
  public void parseConstrainTest() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CONSTRAIN_TEST.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(
                WebTemplateTestData.CONSTRAIN_TEST.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(
        errors,
        new String[] {
          "DefaultValue not equal 100.0 != null in input.suffix:denominator id=total_body_surface_area_tbsa_affected aql=/content[openEHR-EHR-OBSERVATION.affected_body_surface_area.v0]/data[at0001]/events[at0002]/data[at0003]/items[at0014]/value"
        });
  }

  @Test
  public void parseLanguageTest() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.LANGUAGE_TEST.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(WebTemplateTestData.LANGUAGE_TEST.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<String> errors = compareWebTemplate(actual, expected);
    checkErrors(
        errors,
        new String[] {
          "InputValue not equal 146:mean != 146:Durchschnitt in id=math_function aql=/content[openEHR-EHR-OBSERVATION.blood_pressure.v2]/data[at0001]/events[at1042]/math_function",
          "LocalizedNames not equal [de=event, ar-sy=event, en=event, fi=event] != [de=, fi=, en=event, ar-sy=] in inputValue.code:433 id=category aql=/category",
          "LocalizedNames not equal [de=mean, ar-sy=mean, en=mean, fi=mean] != [de=Durchschnitt, fi=, en=mean, ar-sy=] in inputValue.code:146 id=math_function aql=/content[openEHR-EHR-OBSERVATION.blood_pressure.v2]/data[at0001]/events[at1042]/math_function"
        });
  }

  @Test
  public void parseAllTypes() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
            .getTemplate();

    OPTParser cut = new OPTParser(template);
    WebTemplate actual = cut.parse();
    actual = new Filter().filter(actual);
    assertThat(actual).isNotNull();

    ObjectMapper objectMapper = new ObjectMapper();
    WebTemplate expected =
        objectMapper.readValue(
            IOUtils.toString(WebTemplateTestData.ALL_TYPES.getStream(), StandardCharsets.UTF_8),
            WebTemplate.class);

    List<WebTemplateNode> dvOrdinalList =
        actual.getTree().findMatching(n -> n.getRmType().equals("DV_ORDINAL"));
    assertThat(dvOrdinalList).size().isEqualTo(1);
    assertThat(dvOrdinalList.get(0).getInputs())
        .flatExtracting(WebTemplateInput::getList)
        .extracting(
            WebTemplateInputValue::getLabel,
            WebTemplateInputValue::getValue,
            WebTemplateInputValue::getOrdinal)
        .containsExactlyInAnyOrder(
            new Tuple("ord1", "at0014", 0),
            new Tuple("ord1", "at0015", 1),
            new Tuple("ord3", "at0016", 2));

    List<WebTemplateNode> dvQuantityList =
        actual.getTree().findMatching(n -> n.getRmType().equals("DV_QUANTITY"));
    assertThat(dvQuantityList)
        .flatExtracting(WebTemplateNode::getInputs)
        .flatExtracting(WebTemplateInput::getList)
        .extracting(WebTemplateInputValue::getLabel, WebTemplateInputValue::getValue)
        .containsExactlyInAnyOrder(
            new Tuple("mg", "mg"),
            new Tuple("kg", "kg"),
            new Tuple("mm[H20]", "mm[H20]"),
            new Tuple("mm[Hg]", "mm[Hg]"));
    List<WebTemplateNode> dvCodedTextList =
        actual.getTree().findMatching(n -> n.getRmType().equals("DV_CODED_TEXT"));
    assertThat(dvCodedTextList)
        .flatExtracting(WebTemplateNode::getInputs)
        .extracting(
            WebTemplateInput::getTerminology,
            i ->
                i.getList().stream()
                    .map(v -> v.getValue() + ":" + v.getLabel())
                    .collect(Collectors.joining(";")))
        .containsExactlyInAnyOrder(
            new Tuple("openehr", "433:event"),
            new Tuple("local", "at0006:value1;at0007:value2;at0008:value3"),
            new Tuple("local", ""),
            new Tuple("local", ""),
            new Tuple("SNOMED-CT", ""),
            new Tuple("SNOMED-CT", ""),
            new Tuple("local", "at0003:Planned;at0004:Active;at0005:Completed"),
            new Tuple("openehr", "526:planned;245:active;532:completed"),
            new Tuple(null, ""),
            new Tuple(null, ""),
            new Tuple(null, ""),
            new Tuple(null, ""));

    List<String> errors = compareWebTemplate(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "LocalizedNames not equal [en=active] != [] in inputValue.code:245 id=current_state aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/current_state",
          "LocalizedNames not equal [en=completed] != [] in inputValue.code:532 id=current_state aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/current_state",
          "LocalizedNames not equal [en=planned] != [] in inputValue.code:526 id=current_state aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/current_state",
          "LocalizedDescriptions not equal {en=*} != {} in inputValue.code:at0005 id=careflow_step aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/careflow_step",
          "LocalizedDescriptions not equal {en=*} != {} in inputValue.code:at0004 id=careflow_step aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/careflow_step",
          "LocalizedDescriptions not equal {en=*} != {} in inputValue.code:at0003 id=careflow_step aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]/ism_transition/careflow_step",
          "InContext not equal true != null in  id=action_archetype_id aql=/content[openEHR-EHR-SECTION.test_all_types.v1]/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]/activities[at0001]/action_archetype_id"
        });
  }

  @Test
  public void parseMultimediaTest() throws Exception {
    var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTIMEDIA_TEST.getStream())
            .getTemplate();
    var parser = new OPTParser(optTemplate);

    var webTemplate = parser.parse();
    assertThat(webTemplate).isNotNull();

    var nodes = webTemplate.getTree()
        .findMatching(node ->  node.getRmType().equals("PARTICIPATION"));
    assertThat(nodes.size()).isEqualTo(2L);
  }

  public void checkErrors(List<String> errors, String[] expectedErrors) {

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(errors).containsAll(Arrays.asList(expectedErrors));

    errors.removeAll(Arrays.asList(expectedErrors));

    // Nodes wich are generated by the parser but are not in the example
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra Node"))
        .containsExactlyInAnyOrder();

    // Inputs wich are generated by the parser but are not in the example
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra Input"))
        .containsExactlyInAnyOrder();

    // Nodes wich are in the example but are not generated by the parser
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing Node"))
        .containsExactlyInAnyOrder();

    // Inputs wich are in the example but are not generated by the parser
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing Input"))
        .containsExactlyInAnyOrder();

    // Non equal validations
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Validation not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("ListOpen not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("ProportionTypes not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("InContext not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Annotations not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Cardinalities not equal"))
        .containsExactlyInAnyOrder();

    // Non equal InputValue
    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing InputValue"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra InputValue"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("InputValue not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("DefaultValue not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("LocalizedNames not equal"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("LocalizedDescriptions not equal"))
        .containsExactlyInAnyOrder();

    softAssertions.assertAll();
  }

  public List<String> compareWebTemplate(WebTemplate actual, WebTemplate expected) {
    return new ArrayList<>(compareNode(actual.getTree(), expected.getTree()));
  }

  public List<String> compareNodes(List<WebTemplateNode> actual, List<WebTemplateNode> expected) {
    List<String> errors = new ArrayList<>();
    Map<ImmutablePair<String, String>, WebTemplateNode> actualMap =
        actual.stream()
            .collect(
                Collectors.toMap(
                    n -> new ImmutablePair<>(n.getAqlPath(true), n.getId()), Function.identity()));
    Map<ImmutablePair<String, String>, WebTemplateNode> expectedMap =
        expected.stream()
            .collect(
                Collectors.toMap(
                    n -> new ImmutablePair<>(n.getAqlPath(true), n.getId()), Function.identity()));

    for (ImmutablePair<String, String> pair : actualMap.keySet()) {
      if (expectedMap.containsKey(pair)) {
        errors.addAll(compareNode(actualMap.get(pair), expectedMap.get(pair)));

      } else {
        errors.add(String.format("Extra Node id=%s aql=%s", pair.getRight(), pair.getLeft()));
      }
    }
    for (ImmutablePair<String, String> pair : expectedMap.keySet()) {
      if (!actualMap.containsKey(pair)) {
        errors.add(String.format("Missing Node id=%s aql=%s", pair.getRight(), pair.getLeft()));
      }
    }
    return errors;
  }

  public List<String> compareNode(WebTemplateNode actual, WebTemplateNode expected) {

    List<String> errors = new ArrayList<>();
    errors.addAll(compareNodes(actual.getChildren(), expected.getChildren()));

    errors.addAll(compereInputs(actual, actual.getInputs(), expected.getInputs()));

    if (!CollectionUtils.isEqualCollection(
        actual.getProportionTypes(), expected.getProportionTypes())) {
      errors.add(
          String.format(
              "ProportionTypes not equal %s != %s in  id=%s aql=%s",
              actual.getProportionTypes(),
              expected.getProportionTypes(),
              actual.getId(),
              actual.getAqlPath()));
    }

    if (!Objects.equals(actual.getAnnotations(), expected.getAnnotations())) {
      errors.add(
          String.format(
              "Annotations not equal %s != %s in  id=%s aql=%s",
              actual.getAnnotations(),
              expected.getAnnotations(),
              actual.getId(),
              actual.getAqlPath()));
    }

    if (!Objects.equals(actual.getCardinalities(), expected.getCardinalities())) {
      errors.add(
          String.format(
              "Cardinalities not equal %s != %s in  id=%s aql=%s",
              actual.getCardinalities(),
              expected.getCardinalities(),
              actual.getId(),
              actual.getAqlPath()));
    }

    if (!CollectionUtils.isEqualCollection(
        actual.getLocalizedNames().entrySet(), expected.getLocalizedNames().entrySet())) {
      errors.add(
          String.format(
              "LocalizedNames not equal %s != %s in  id=%s aql=%s",
              actual.getLocalizedNames().entrySet(),
              expected.getLocalizedNames().entrySet(),
              actual.getId(),
              actual.getAqlPath()));
    }

    if (!Objects.equals(actual.getInContext(), expected.getInContext())) {
      errors.add(
          String.format(
              "InContext not equal %s != %s in  id=%s aql=%s",
              actual.getInContext(), expected.getInContext(), actual.getId(), actual.getAqlPath()));
    }

    if (!CollectionUtils.isEqualCollection(
        actual.getLocalizedDescriptions().entrySet(),
        expected.getLocalizedDescriptions().entrySet())) {
      errors.add(
          String.format(
              "LocalizedDescriptions not equal %s != %s in  id=%s aql=%s",
              actual.getLocalizedDescriptions(),
              expected.getLocalizedDescriptions(),
              actual.getId(),
              actual.getAqlPath()));
    }
    return errors;
  }

  private Collection<String> compereInputs(
      WebTemplateNode node, List<WebTemplateInput> actual, List<WebTemplateInput> expected) {
    List<String> errors = new ArrayList<>();

    Map<String, WebTemplateInput> actualMap =
        actual.stream()
            .collect(
                Collectors.toMap(
                    webTemplateInput ->
                        webTemplateInput.getSuffix() + ":" + webTemplateInput.getType(),
                    Function.identity()));
    Map<String, WebTemplateInput> expectedMap =
        expected.stream()
            .collect(
                Collectors.toMap(
                    webTemplateInput ->
                        webTemplateInput.getSuffix() + ":" + webTemplateInput.getType(),
                    Function.identity()));

    for (Map.Entry<String, WebTemplateInput> entry : actualMap.entrySet()) {
      if (!expectedMap.containsKey(entry.getKey())) {
        errors.add(
            String.format(
                "Extra Input %s in id=%s aql=%s", entry.getKey(), node.getId(), node.getAqlPath()));
      } else {
        errors.addAll(compareInput(node, entry.getValue(), expectedMap.get(entry.getKey())));
      }
    }

    for (Map.Entry<String, WebTemplateInput> entry : expectedMap.entrySet()) {
      if (!actualMap.containsKey(entry.getKey())) {
        errors.add(
            String.format(
                "Missing Input %s in id=%s aql=%s",
                entry.getKey(), node.getId(), node.getAqlPath()));
      }
    }

    return errors;
  }

  private List<String> compareInput(
      WebTemplateNode node, WebTemplateInput actual, WebTemplateInput expected) {
    List<String> errors = new ArrayList<>();

    if (!Objects.equals(actual.getValidation(), expected.getValidation())) {
      errors.add(
          String.format(
              "Validation not equal %s != %s in input.suffix:%s id=%s aql=%s",
              actual.getValidation(),
              expected.getValidation(),
              actual.getSuffix(),
              node.getId(),
              node.getAqlPath()));
    }
    if (!Objects.equals(actual.getListOpen(), expected.getListOpen())) {
      errors.add(
          String.format(
              "ListOpen not equal %s != %s in input.suffix:%s id=%s aql=%s",
              actual.getListOpen(),
              expected.getListOpen(),
              actual.getSuffix(),
              node.getId(),
              node.getAqlPath()));
    }

    if (!Objects.equals(actual.getDefaultValue(), expected.getDefaultValue())) {
      errors.add(
          String.format(
              "DefaultValue not equal %s != %s in input.suffix:%s id=%s aql=%s",
              actual.getDefaultValue(),
              expected.getDefaultValue(),
              actual.getSuffix(),
              node.getId(),
              node.getAqlPath()));
    }

    errors.addAll(compereInputValues(node, actual.getList(), expected.getList()));
    return errors;
  }

  private Collection<String> compereInputValues(
      WebTemplateNode node,
      List<WebTemplateInputValue> actual,
      List<WebTemplateInputValue> expected) {
    List<String> errors = new ArrayList<>();

    Map<String, WebTemplateInputValue> actualMap =
        actual.stream()
            .collect(Collectors.toMap(WebTemplateInputValue::getValue, Function.identity()));

    Map<String, WebTemplateInputValue> expectedMap =
        expected.stream()
            .collect(Collectors.toMap(WebTemplateInputValue::getValue, Function.identity()));

    for (Map.Entry<String, WebTemplateInputValue> entry : actualMap.entrySet()) {
      if (!expectedMap.containsKey(entry.getKey())) {
        errors.add(
            String.format(
                "Extra InputValue %s in id=%s aql=%s",
                entry.getKey(), node.getId(), node.getAqlPath()));
      } else {
        errors.addAll(compareInputValue(node, entry.getValue(), expectedMap.get(entry.getKey())));
      }
    }

    for (Map.Entry<String, WebTemplateInputValue> entry : expectedMap.entrySet()) {
      if (!actualMap.containsKey(entry.getKey())) {
        errors.add(
            String.format(
                "Missing InputValue %s in id=%s aql=%s",
                entry.getKey(), node.getId(), node.getAqlPath()));
      }
    }

    return errors;
  }

  private Collection<String> compareInputValue(
      WebTemplateNode node, WebTemplateInputValue actual, WebTemplateInputValue expected) {
    List<String> errors = new ArrayList<>();

    if (!Objects.equals(actual.getValue(), expected.getValue())
        || !Objects.equals(actual.getLabel(), expected.getLabel())) {
      errors.add(
          String.format(
              "InputValue not equal %s != %s in id=%s aql=%s",
              actual.getValue() + ":" + actual.getLabel(),
              expected.getValue() + ":" + expected.getLabel(),
              node.getId(),
              node.getAqlPath()));
    }

    if (!Objects.equals(actual.getValidation(), expected.getValidation())) {
      errors.add(
          String.format(
              "Validation not equal %s != %s in inputValue.code:%s id=%s aql=%s",
              actual.getValidation(),
              expected.getValidation(),
              actual.getValue(),
              node.getId(),
              node.getAqlPath()));
    }

    if (!CollectionUtils.isEqualCollection(
        actual.getLocalizedLabels().entrySet(), expected.getLocalizedLabels().entrySet())) {
      errors.add(
          String.format(
              "LocalizedNames not equal %s != %s in inputValue.code:%s id=%s aql=%s",
              actual.getLocalizedLabels().entrySet(),
              expected.getLocalizedLabels().entrySet(),
              actual.getValue(),
              node.getId(),
              node.getAqlPath()));
    }
    if (!CollectionUtils.isEqualCollection(
        actual.getLocalizedDescriptions().entrySet(),
        expected.getLocalizedDescriptions().entrySet())) {
      errors.add(
          String.format(
              "LocalizedDescriptions not equal %s != %s in inputValue.code:%s id=%s aql=%s",
              actual.getLocalizedDescriptions(),
              expected.getLocalizedDescriptions(),
              actual.getValue(),
              node.getId(),
              node.getAqlPath()));
    }

    return errors;
  }
}
