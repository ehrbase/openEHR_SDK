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

package org.ehrbase.serialisation.flatencoding;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rmobjectvalidator.RMObjectValidator;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.Validator;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

public class FlatJsonTest {

  public static final TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();

  @Test
  public void roundTrip() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.CORONA_WITH_OTHER_PARTICIPATION;
    String templateId = "Corona_Anamnese";

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripAction() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.EREACT_COVID_MANAGEMENT;
    String templateId = OperationalTemplateTestData.EREACT_COVID_MANAGEMENT.getTemplateId();

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripVitalSigns() throws IOException {
    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.VITALSIGNS;
    String templateId = "EHRN Vital signs.v2";

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripIcd() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.ADVERSE_REACTION_LIST;
    String templateId = "Adverse Reaction List.v1";

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundMulitList() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.MULTI_LIST;
    String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripDeterioriationAssessment() throws IOException {

    String templateId = "EREACT - Deterioriation assessment.v0";
    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT;

    String[] expectedMissing = {
      "Missing path: deterioration_assessment/assessment/news2/respirations/rate|magnitude, value: 110.0",
      "Missing path: deterioration_assessment/assessment/news2/pulse/pulse_rate|magnitude, value: 80.0",
      "Missing path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|denominator, value: 100.0",
      "Missing path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|numerator, value: 80.0",
      "Missing path: deterioration_assessment/assessment/news2/blood_pressure/diastolic|magnitude, value: 60.0",
      "Missing path: deterioration_assessment/assessment/news2/blood_pressure/systolic|magnitude, value: 96.0"
    };

    String[] expectedExtra = {
      "Extra path: deterioration_assessment/assessment/news2/blood_pressure/diastolic|magnitude, value: 60",
      "Extra path: deterioration_assessment/assessment/news2/blood_pressure/systolic|magnitude, value: 96",
      "Extra path: deterioration_assessment/assessment/news2/pulse/pulse_rate|magnitude, value: 80",
      "Extra path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|denominator, value: 100",
      "Extra path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|numerator, value: 80",
      "Extra path: deterioration_assessment/assessment/news2/respirations/rate|magnitude, value: 110"
    };

    check(templateId, testData, expectedMissing, expectedExtra);
  }

  @Test
  public void roundTripAll() throws IOException {
    String templateId = "test_all_types.en.v1";
    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.ALL_TYPES;

    String[] expectedMissing = {
      "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
      "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1"
    };

    String[] expectedExtra = {
      "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
      "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0"
    };

    check(templateId, testData, expectedMissing, expectedExtra);
  }

  @Test
  public void roundTripAlt() throws IOException {

    String templateId = "AlternativeEvents";
    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS;

    String[] expectedMissing = {
      "Missing path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30.0",
      "Missing path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60.0",
      "Missing path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61.0",
      "Missing path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55.0",
      "Missing path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62.0"
    };

    String[] expectedExtra = {
      "Extra path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55",
      "Extra path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60",
      "Extra path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61",
      "Extra path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62",
      "Extra path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30"
    };

    check(templateId, testData, expectedMissing, expectedExtra);
  }

  @Test
  public void roundTripMulti() throws IOException {

    String templateId = "ehrbase_multi_occurrence.de.v1";
    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.MULTI_OCCURRENCE;

    String[] expectedMissing = {
      "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
      "Missing path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11.0",
      "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
      "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0"
    };

    String[] expectedExtra = {
      "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
      "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
      "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
      "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11"
    };

    check(templateId, testData, expectedMissing, expectedExtra);
  }

  @Test
  public void roundTripMissingCount() throws IOException {
    String templateId = "ehrbase_multi_occurrence.de.v1";
    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.MISSING_COUNT;

    String[] expectedMissing = {
      "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
      "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
      "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0",
      "Missing path: encounter/body_temperature:0/any_event:0/temperature|unit, value: Cel",
      "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|terminology, value: local",
      "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|value, value: Appropriate clothing/bedding",
      "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|code, value: at0033",
      "Missing path: encounter/body_temperature:0/any_event:0/current_day_of_menstrual_cycle, value: 3",
      "Missing path: encounter/body_temperature:0/any_event:0/time, value: 2020-10-06T13:30:34.328873+02:00"
    };

    String[] expectedExtra = {
      "Extra path: encounter/body_temperature:0/any_event/temperature|magnitude, value: 22",
      "Extra path: encounter/body_temperature:0/any_event/temperature|unit, value: Cel",
      "Extra path: encounter/body_temperature:0/any_event/body_exposure|code, value: at0033",
      "Extra path: encounter/body_temperature:0/any_event/body_exposure|value, value: Appropriate clothing/bedding",
      "Extra path: encounter/body_temperature:0/any_event/body_exposure|terminology, value: local",
      "Extra path: encounter/body_temperature:0/any_event/current_day_of_menstrual_cycle, value: 3",
      "Extra path: encounter/body_temperature:0/any_event/time, value: 2020-10-06T13:30:34.328873+02:00",
      "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
      "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11"
    };

    check(templateId, testData, expectedMissing, expectedExtra);
  }

  private void check(
      String templateId,
      CompositionTestDataSimSDTJson testData,
      String[] expectedMissing,
      String[] expectedExtra)
      throws IOException {

    FlatJson cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(unmarshal).isNotNull();

    try {
      new Validator(templateProvider.find(templateId).get()).check(unmarshal);
    } catch (Exception e) {
      softAssertions.fail(e.getMessage());
    }

    RMObjectValidator rmObjectValidator =
        new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

    softAssertions
        .assertThat(rmObjectValidator.validate(unmarshal))
        .filteredOn(m -> !m.getMessage().contains("Inv_null_flavour_indicated"))
        .containsExactlyInAnyOrder();

    String actual = cut.marshal(unmarshal);

    String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(expectedMissing);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(expectedExtra);

    softAssertions.assertAll();
  }
}
