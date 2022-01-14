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
