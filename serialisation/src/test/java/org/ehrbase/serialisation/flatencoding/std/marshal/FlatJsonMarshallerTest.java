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

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

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

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60.0",
            "Missing path: bericht/körpergewicht:0/any_event_en:1/math_function|value, value: mean",
            "Missing path: bericht/körpergewicht:0/any_event_en:1/math_function|terminology, value: openehr",
            "Missing path: bericht/körpergewicht:0/any_event_en:1/math_function|code, value: 146",
            "Missing path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30.0");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: bericht/körpergewicht:0/any_event_en:0/gewicht|magnitude, value: 55",
            "Extra path: bericht/körpergewicht:0/any_event_en:1/gewicht|magnitude, value: 60",
            "Extra path: bericht/körpergewicht:0/birth_en/gewicht|magnitude, value: 30");
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

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Missing"))
        .containsExactlyInAnyOrder(
            "Missing path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22.0",
            "Missing path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11.0",
            "Missing path: encounter/context/end_time, value: 2020-10-06T13:30:34.317875+02:00",
            "Missing path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22.0",
            "Missing path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11.0");

    assertThat(errors)
        .filteredOn(s -> s.startsWith("Extra"))
        .containsExactlyInAnyOrder(
            "Extra path: encounter/context/_end_time, value: 2020-10-06T13:30:34.317875+02:00",
            "Extra path: encounter/body_temperature:0/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:0/any_event:1/temperature|magnitude, value: 11",
            "Extra path: encounter/body_temperature:1/any_event:0/temperature|magnitude, value: 22",
            "Extra path: encounter/body_temperature:1/any_event:1/temperature|magnitude, value: 11");
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
