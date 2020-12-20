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

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.serialisation.flatencoding.std.marshal.FlatJsonMarshallerTest.compere;

import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.ehrbase.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Test;

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
            "Extra path: encounter/context/_end_time, value: 2020-10-06T13:30:34.317875+02:00",
            "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
            "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11");
  }
}
