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

package org.ehrbase.serialisation.flatencoding.std.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatJsonMarshallerTest {

  @Test
  public void toFlatJson() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream())
            .getTemplate();
    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                Composition.class);
    FlatJsonMarshaller cut = new FlatJsonMarshaller();
    String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
    assertThat(actual).isNotNull();

    String expected =
        IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors).filteredOn(s -> s.startsWith("Missing")).containsExactlyInAnyOrder();

    assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();
  }

  @Test
  public void toFlatJsonAltEvents() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALT_EVENTS.getStream())
            .getTemplate();
    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.ALTERNATIVE_EVENTS.getStream(),
                    StandardCharsets.UTF_8),
                Composition.class);
    FlatJsonMarshaller cut = new FlatJsonMarshaller();
    String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
    assertThat(actual).isNotNull();

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS_2.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "Missing path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55.0",
          "Missing path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60.0",
          "Missing path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30.0"
        },
        new String[] {
          "Extra path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55",
          "Extra path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60",
          "Extra path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30"
        });
  }

  @Test
  public void toFlatJsonMultiOccurrence() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream())
            .getTemplate();
    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.MULTI_OCCURRENCE.getStream(),
                    StandardCharsets.UTF_8),
                Composition.class);
    FlatJsonMarshaller cut = new FlatJsonMarshaller();
    String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
    assertThat(actual).isNotNull();

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
          "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0",
          "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
          "Missing path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11.0"
        },
        new String[] {
          "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
          "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
          "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
          "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11"
        });
  }

  @Test
  public void toFlatJsonAllTypes() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream())
            .getTemplate();

    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8),
                Composition.class);
    FlatJsonMarshaller cut = new FlatJsonMarshaller();
    String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
    assertThat(actual).isNotNull();

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
          "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1",
          "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/_name, value: List",
          "Missing path: test_all_types/test_all_types3:0/section_2/test_all_types:0/_name, value: single",
          "Missing path: test_all_types/test_all_types:0/proportion_any|precision, value: 0"
        },
        new String[] {
          "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
          "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0"
        });
  }

  @Test
  public void toFlatJsonIps() throws Exception {
    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(OperationalTemplateTestData.IPS.getStream())
            .getTemplate();

    Composition composition =
        new CanonicalJson()
            .unmarshal(
                IOUtils.toString(
                    CompositionTestDataCanonicalJson.IPS.getStream(), StandardCharsets.UTF_8),
                Composition.class);

    FlatJsonMarshaller marshaller = new FlatJsonMarshaller();

    String actual = marshaller.toFlatJson(composition, new OPTParser(template).parse());
    assertThat(actual).isNotNull();

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.IPS.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    checkErrors(
        errors,
        new String[] {
          "Missing path: international_patient_summary/medication_summary/medication_statement/order_id:0|id, value: 9a0e5173-07c8-443d-b414-24432b9d95ca",
          "Missing path: international_patient_summary/medical_devices/device_use_statement/device_details:0/medical_device/unique_device_identifier_udi|id, value: 73b166ae-1c28-4ce0-8c08-a9587d8fd95a",
          "Missing path: international_patient_summary/medical_devices/device_use_statement/device_details:0/medical_device/other_identifier:0|id, value: 60287ff3-ec0f-4cd5-9000-2c05af2e6a84",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/requester_order_identifier|id, value: 38a6687c-5136-4e75-9f1c-126e8f0e112b",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/receiver_order_identifier|id, value: 9fc6db02-81de-4ec9-afe4-f365c42019e1",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/report_identifier|id, value: a147525c-4763-4070-ba22-26e6b33348f4",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/reported_image:0/image_identifier|id, value: 5462ef5c-2275-47c2-8fb5-f9f1d7a19613",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/reported_image:0/dicom_series_identifier|id, value: 55dd86d7-52ff-4064-8dc7-f8d9b2bc22e7",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/comparison_image:0/image_identifier|id, value: a6c20273-7b53-4c04-9b2c-2d4c218893b2",
          "Missing path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/comparison_image:0/dicom_series_identifier|id, value: 1ba8eff7-8f3f-4625-9432-05aa01726073",
          "Missing path: international_patient_summary/vital_signs/pulse_oximetry/any_event:0/spo, value: 0.8920999999999999",
          "Missing path: international_patient_summary/plan_of_care/care_plan/care_plan_id|id, value: 0942fb74-27f8-48c6-869e-10192740c371",
          "Missing path: international_patient_summary/plan_of_care/service_request/current_activity:0/action_archetype_id, value: /.*/",
          "Missing path: international_patient_summary/medication_summary/medication_statement/dosage/timing_-_daily/frequency/quantity_value|precision, value: 0",
          "Missing path: international_patient_summary/medication_summary/medication_statement/timing_-_non-daily/frequency/quantity_value|precision, value: 0",
          "Missing path: international_patient_summary/vital_signs/respiration/any_event:0/rate|precision, value: 0",
          "Missing path: international_patient_summary/vital_signs/pulse_heart_beat/any_event:0/rate|precision, value: 0",
          "Missing path: international_patient_summary/vital_signs/body_temperature/any_event:0/temperature|precision, value: 1",
          "Missing path: international_patient_summary/vital_signs/body_mass_index/any_event:0/body_mass_index|precision, value: 1",
          "Missing path: international_patient_summary/vital_signs/blood_pressure/any_event:0/systolic|precision, value: 0",
          "Missing path: international_patient_summary/vital_signs/blood_pressure/any_event:0/diastolic|precision, value: 0",
          "Missing path: international_patient_summary/social_history/alcohol_consumption_summary/per_episode:0/typical_consumption_alcohol_units|precision, value: 1"
        },
        new String[] {
          "Extra path: international_patient_summary/medication_summary/medication_statement/order_id:0, value: 9a0e5173-07c8-443d-b414-24432b9d95ca",
          "Extra path: international_patient_summary/medical_devices/device_use_statement/device_details:0/medical_device/unique_device_identifier_udi, value: 73b166ae-1c28-4ce0-8c08-a9587d8fd95a",
          "Extra path: international_patient_summary/medical_devices/device_use_statement/device_details:0/medical_device/other_identifier:0, value: 60287ff3-ec0f-4cd5-9000-2c05af2e6a84",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/requester_order_identifier, value: 38a6687c-5136-4e75-9f1c-126e8f0e112b",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/receiver_order_identifier, value: 9fc6db02-81de-4ec9-afe4-f365c42019e1",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/report_identifier, value: a147525c-4763-4070-ba22-26e6b33348f4",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/reported_image:0/image_identifier, value: 5462ef5c-2275-47c2-8fb5-f9f1d7a19613",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/reported_image:0/dicom_series_identifier, value: 55dd86d7-52ff-4064-8dc7-f8d9b2bc22e7",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/comparison_image:0/image_identifier, value: a6c20273-7b53-4c04-9b2c-2d4c218893b2",
          "Extra path: international_patient_summary/diagnostic_results/imaging_examination_result/examination_request_details:0/comparison_image:0/dicom_series_identifier, value: 1ba8eff7-8f3f-4625-9432-05aa01726073",
          "Extra path: international_patient_summary/vital_signs/pulse_oximetry/any_event:0/spo, value: 0.8921",
          "Extra path: international_patient_summary/plan_of_care/care_plan/care_plan_id, value: 0942fb74-27f8-48c6-869e-10192740c371"
        });
  }

  public void checkErrors(List<String> errors, String[] missing, String[] extra) {

    SoftAssertions softAssertions = new SoftAssertions();

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(missing);

    softAssertions
        .assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(extra);

    softAssertions.assertAll();
  }

  public static List<String> compere(String actualJson, String expectedJson)
      throws JsonProcessingException {
    List<String> errors = new ArrayList<>();
    ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

    Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
    Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

    actual.forEach(
        (key, value) -> {
          if (!expected.containsKey(key) || !expected.get(key).equals(value)) {
            errors.add(String.format("Missing path: %s, value: %s", key, value));
          }
        });

    expected.forEach(
        (key, value) -> {
          if (!actual.containsKey(key) || !actual.get(key).equals(value)) {
            errors.add(String.format("Extra path: %s, value: %s", key, value));
          }
        });

    return errors;
  }
}
