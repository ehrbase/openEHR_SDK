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
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

public class FlatJsonTest {

  @Test
  public void roundTrip() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "Corona_Anamnese");

    String flat =
        IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors).filteredOn(s -> s.startsWith("Missing")).containsExactlyInAnyOrder();

    assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();
  }

  @Test
  public void roundTripVitalSigns() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "EHRN Vital signs.v2");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.VITALSIGNS.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.VITALSIGNS.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors).filteredOn(s -> s.startsWith("Missing")).containsExactlyInAnyOrder();

    assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();
  }

  @Test
  public void roundTripIcd() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "Adverse Reaction List.v1");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ADVERSE_REACTION_LIST.getStream(),
            StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ADVERSE_REACTION_LIST.getStream(),
            StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors).filteredOn(s -> s.startsWith("Missing")).containsExactlyInAnyOrder();

    assertThat(errors).filteredOn(s -> s.startsWith("Extra")).containsExactlyInAnyOrder();
  }

  @Test
  public void roundTripDeterioriationAssessment() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "EREACT - Deterioriation assessment.v0");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT.getStream(),
            StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.DETERIORIATION_ASSESSMENT.getStream(),
            StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: deterioration_assessment/assessment/news2/respirations/rate|magnitude, value: 110.0",
            "Missing path: deterioration_assessment/assessment/news2/pulse/pulse_rate|magnitude, value: 80.0",
            "Missing path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|denominator, value: 100.0",
            "Missing path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|numerator, value: 80.0",
            "Missing path: deterioration_assessment/assessment/news2/blood_pressure/diastolic|magnitude, value: 60.0",
            "Missing path: deterioration_assessment/assessment/news2/blood_pressure/systolic|magnitude, value: 96.0");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: deterioration_assessment/assessment/news2/blood_pressure/diastolic|magnitude, value: 60",
            "Extra path: deterioration_assessment/assessment/news2/blood_pressure/systolic|magnitude, value: 96",
            "Extra path: deterioration_assessment/assessment/news2/pulse/pulse_rate|magnitude, value: 80",
            "Extra path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|denominator, value: 100",
            "Extra path: deterioration_assessment/assessment/news2/pulse_oximetry/spo|numerator, value: 80",
            "Extra path: deterioration_assessment/assessment/news2/respirations/rate|magnitude, value: 110");
  }

  @Test
  public void roundTripAll() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "test_all_types.en.v1");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
            "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
            "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0");
  }

  @Test
  public void roundTripAlt() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "AlternativeEvents");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.ALTERNATIVE_EVENTS.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62.0");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55",
            "Extra path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60",
            "Extra path: bericht/körpergewicht:0/any_event_en:2/gewicht|magnitude, value: 61",
            "Extra path: bericht/körpergewicht:0/any_event_en:3/gewicht|magnitude, value: 62",
            "Extra path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30");
  }

  @Test
  public void roundTripMulti() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
        new FlatJasonProvider(templateProvider)
            .buildFlatJson(FlatFormat.SIM_SDT, "ehrbase_multi_occurrence.de.v1");

    String flat =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
        IOUtils.toString(
            CompositionTestDataSimSDTJson.MULTI_OCCURRENCE.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
            "Missing path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11.0",
            "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
            "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
            "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11");
  }

  @Test
  public void roundTripMissingCount() throws IOException {
    TestDataTemplateProvider templateProvider = new TestDataTemplateProvider();
    FlatJson cut =
            new FlatJasonProvider(templateProvider)
                    .buildFlatJson(FlatFormat.SIM_SDT, "ehrbase_multi_occurrence.de.v1");

    String flat =
            IOUtils.toString(
                    CompositionTestDataSimSDTJson.MISSING_COUNT.getStream(), StandardCharsets.UTF_8);
    Composition unmarshal = cut.unmarshal(flat);

    assertThat(unmarshal).isNotNull();

    String actual = cut.marshal(unmarshal);

    String expected =
            IOUtils.toString(
                    CompositionTestDataSimSDTJson.MISSING_COUNT.getStream(), StandardCharsets.UTF_8);

    List<String> errors = compere(actual, expected);

    assertThat(errors)
            .filteredOn(s -> s.startsWith("Missing"))
            .containsExactlyInAnyOrder(
                    "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
                    "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
                    "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0",
                    "Missing path: encounter/body_temperature:0/any_event:0/temperature|unit, value: Cel",
                    "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|terminology, value: local",
                    "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|value, value: Appropriate clothing/bedding",
                    "Missing path: encounter/body_temperature:0/any_event:0/body_exposure|code, value: at0033",
                    "Missing path: encounter/body_temperature:0/any_event:0/current_day_of_menstrual_cycle, value: 3",
                    "Missing path: encounter/body_temperature:0/any_event:0/time, value: 2020-10-06T13:30:34.328873+02:00");

    assertThat(errors)
            .filteredOn(s -> s.startsWith("Extra"))
            .containsExactlyInAnyOrder(
                    "Extra path: encounter/body_temperature:0/any_event/temperature|magnitude, value: 22",
                    "Extra path: encounter/body_temperature:0/any_event/temperature|unit, value: Cel",
                    "Extra path: encounter/body_temperature:0/any_event/body_exposure|code, value: at0033",
                    "Extra path: encounter/body_temperature:0/any_event/body_exposure|value, value: Appropriate clothing/bedding",
                    "Extra path: encounter/body_temperature:0/any_event/body_exposure|terminology, value: local",
                    "Extra path: encounter/body_temperature:0/any_event/current_day_of_menstrual_cycle, value: 3",
                    "Extra path: encounter/body_temperature:0/any_event/time, value: 2020-10-06T13:30:34.328873+02:00",
                    "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
                    "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11");
  }
}
