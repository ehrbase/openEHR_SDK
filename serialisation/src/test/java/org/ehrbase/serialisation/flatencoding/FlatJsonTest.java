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
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
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
  public void roundTripFeederAudit() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.CORONA_WITH_FEEDER_AUDIT;
    String templateId = "Corona_Anamnese";

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripFeederAuditRaw() throws IOException {

    CompositionTestDataSimSDTJson testData =
        CompositionTestDataSimSDTJson.CORONA_WITH_FEEDER_AUDIT_RAW;
    String templateId = "Corona_Anamnese";

    RMDataFormat cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(unmarshal).isNotNull();

    RMObjectValidator rmObjectValidator =
        new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

    softAssertions
        .assertThat(rmObjectValidator.validate(unmarshal))
        .filteredOn(m -> !m.getMessage().contains("Inv_null_flavour_indicated"))
        .containsExactlyInAnyOrder();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.CORONA_WITH_FEEDER_AUDIT.getStream(),
            StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder();

    softAssertions.assertAll();
  }

  @Test
  public void roundTripRaw() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.CORONA_WITH_RAW;
    String templateId = "Corona_Anamnese";

    RMDataFormat cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(unmarshal).isNotNull();

    RMObjectValidator rmObjectValidator =
        new RMObjectValidator(ArchieRMInfoLookup.getInstance(), s -> null);

    softAssertions
        .assertThat(rmObjectValidator.validate(unmarshal))
        .filteredOn(m -> !m.getMessage().contains("Inv_null_flavour_indicated"))
        .containsExactlyInAnyOrder();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder();

    softAssertions.assertAll();
  }

  @Test
  public void roundTripAction() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.EREACT_COVID_MANAGEMENT;
    String templateId = OperationalTemplateTestData.EREACT_COVID_MANAGEMENT.getTemplateId();

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundTripNCD() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.NCD;
    String templateId = OperationalTemplateTestData.NCD.getTemplateId();

    check(
        templateId,
        testData,
        new String[] {
          "Missing path: ncd/body_temperature/any_event:0/temperature|magnitude, value: 30.0",
          "Missing path: ncd/blood_pressure/any_event:0/systolic|magnitude, value: 120.0",
          "Missing path: ncd/blood_pressure/any_event:0/diastolic|magnitude, value: 80.0",
          "Missing path: ncd/pulse_heart_beat/any_event:0/rate|magnitude, value: 80.0",
          "Missing path: ncd/pulse_heart_beat/any_event:0/time, value: 2021-11-09T10:15:21.05Z",
          "Missing path: ncd/height_length/any_event:0/height_length|magnitude, value: 168.0",
          "Missing path: ncd/height_length/any_event:0/time, value: 2021-11-09T10:15:21.05Z",
          "Missing path: ncd/body_weight/any_event:0/weight|magnitude, value: 74.0",
          "Missing path: ncd/body_weight/any_event:0/time, value: 2021-11-09T10:15:21.05Z",
          "Missing path: ncd/waist_circumference/any_event:0/waist_circumference|magnitude, value: 42.0",
          "Missing path: ncd/waist_circumference/any_event:0/time, value: 2021-11-09T10:15:21.05Z",
          "Missing path: ncd/hip_circumference/hip_circumference|magnitude, value: 90.0",
          "Missing path: ncd/hip_circumference/time, value: 2021-11-09T10:15:21.05Z",
          "Missing path: ncd/pulse_oximetry/any_event:0/spo|numerator, value: 2.0",
          "Missing path: ncd/pulse_oximetry/any_event:0/spo|denominator, value: 100.0",
          "Missing path: ncd/pulse_oximetry/any_event:0/spo, value: 0.02",
          "Missing path: ncd/laboratory_test_result/any_event:0/haemoglobin/haemoglobin|magnitude, value: 13.0",
          "Missing path: ncd/laboratory_test_result/any_event:0/cholesterol/cholesterol|magnitude, value: 36.0"
        },
        new String[] {
          "Extra path: ncd/body_temperature/any_event:0/temperature|magnitude, value: 30",
          "Extra path: ncd/blood_pressure/any_event:0/systolic|magnitude, value: 120",
          "Extra path: ncd/blood_pressure/any_event:0/diastolic|magnitude, value: 80",
          "Extra path: ncd/pulse_heart_beat/any_event:0/rate|magnitude, value: 80",
          "Extra path: ncd/pulse_heart_beat/any_event:0/time, value: 2021-11-09T10:15:21.050Z",
          "Extra path: ncd/height_length/any_event:0/height_length|magnitude, value: 168",
          "Extra path: ncd/height_length/any_event:0/time, value: 2021-11-09T10:15:21.050Z",
          "Extra path: ncd/body_weight/any_event:0/weight|magnitude, value: 74",
          "Extra path: ncd/body_weight/any_event:0/time, value: 2021-11-09T10:15:21.050Z",
          "Extra path: ncd/waist_circumference/any_event:0/waist_circumference|magnitude, value: 42",
          "Extra path: ncd/waist_circumference/any_event:0/time, value: 2021-11-09T10:15:21.050Z",
          "Extra path: ncd/hip_circumference/hip_circumference|magnitude, value: 90",
          "Extra path: ncd/hip_circumference/time, value: 2021-11-09T10:15:21.050Z",
          "Extra path: ncd/pulse_oximetry/any_event:0/spo|numerator, value: 2",
          "Extra path: ncd/pulse_oximetry/any_event:0/spo|denominator, value: 100",
          "Extra path: ncd/laboratory_test_result/any_event:0/haemoglobin/haemoglobin|magnitude, value: 13",
          "Extra path: ncd/laboratory_test_result/any_event:0/cholesterol/cholesterol|magnitude, value: 36",
          "Extra path: ncd/urinalysis/point_in_time:0/glucose|terminology, value: undefined",
          "Extra path: ncd/urinalysis/point_in_time:0/protein|terminology, value: undefined"
        });
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
  public void roundMultiList() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.MULTI_LIST;
    String templateId = OperationalTemplateTestData.MULTI_LIST.getTemplateId();

    check(templateId, testData, new String[] {}, new String[] {});
  }

  @Test
  public void roundNameWithAnd() throws IOException {

    CompositionTestDataSimSDTJson testData = CompositionTestDataSimSDTJson.WORD_WITH_AND;
    String templateId = OperationalTemplateTestData.WORD_WITH_AND.getTemplateId();

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

    RMDataFormat cut =
        new FlatJasonProvider(templateProvider).buildFlatJson(FlatFormat.SIM_SDT, templateId);

    String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions.assertThat(unmarshal).isNotNull();

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
