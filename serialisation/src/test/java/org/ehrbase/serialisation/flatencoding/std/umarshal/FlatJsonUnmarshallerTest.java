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

package org.ehrbase.serialisation.flatencoding.std.umarshal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.generic.PartySelf;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.Validator;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Assert;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class FlatJsonUnmarshallerTest {

  @Test
  public void unmarshal() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate);

    assertThat(actual).isNotNull();

    Observation observation =
        (Observation) actual.itemAtPath("/content[openEHR-EHR-OBSERVATION.story.v1]");
    assertThat(observation.getData().getOrigin().getValue()).hasToString(
        "2020-05-11T22:53:12.039139+02:00");
    assertThat(observation.getSubject()).isNotNull();
    assertThat(observation.getSubject().getClass()).isEqualTo(PartySelf.class);

    try {
      new Validator(template).check(actual);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void unmarshalMulti() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate);

    assertThat(actual).isNotNull();
    try {
      new Validator(template).check(actual);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void unmarshalAlt() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALT_EVENTS.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS.getStream(), StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate);

    assertThat(actual).isNotNull();
    try {
      new Validator(template).check(actual);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void unmarshalAllTypes() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate);

    assertThat(actual).isNotNull();

    // Choice Node was correctly parsed
    Object choice =
        actual.itemAtPath(
            "/content[openEHR-EHR-EVALUATION.test_all_types.v1]/data[at0001]/items[at0009]/value");
    assertThat(choice).isNotNull();
    assertThat(choice.getClass()).isEqualTo(DvQuantity.class);
    assertThat(((DvQuantity) choice).getMagnitude()).isEqualTo(148.01210165023804d);
    assertThat(((DvQuantity) choice).getUnits()).isEqualTo("mm[H20]");

    try {
      new Validator(template).check(actual);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void unmarshalDeterioriationAssessment() throws IOException, XmlException {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.DETERIORIATION_ASSESSMENT.getStream())
            .getTemplate();
    WebTemplate webTemplate = new OPTParser(template).parse();

    FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT.getStream(),
            StandardCharsets.UTF_8);

    Composition actual = cut.unmarshal(flat, webTemplate);

    assertThat(actual).isNotNull();

    try {
      new Validator(template).check(actual);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

  @Test
  public void unmarshallNestedComposition() throws Exception {
    var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.NESTED.getStream())
        .getTemplate();
    var webTemplate = new OPTParser(optTemplate).parse();

    var json = IOUtils.toString(CompositionTestDataSimSDTJson.NESTED.getStream(),
        StandardCharsets.UTF_8);

    var composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);

    assertThat(composition).isNotNull();

    assertDoesNotThrow(() -> new Validator(optTemplate).check(composition));
  }


  @Test
  public void unmarshallIpsComposition() throws Exception {
    var optTemplate = TemplateDocument.Factory.parse(OperationalTemplateTestData.IPS.getStream())
        .getTemplate();
    var webTemplate = new OPTParser(optTemplate).parse();

    var json = IOUtils.toString(CompositionTestDataSimSDTJson.IPS.getStream(),
        StandardCharsets.UTF_8);

    var composition = new FlatJsonUnmarshaller().unmarshal(json, webTemplate);

    assertThat(composition).isNotNull();

    assertDoesNotThrow(() -> new Validator(optTemplate).check(composition));
  }
}
