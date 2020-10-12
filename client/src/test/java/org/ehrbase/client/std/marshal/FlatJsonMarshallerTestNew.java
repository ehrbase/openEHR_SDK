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

package org.ehrbase.client.std.marshal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.introspect.TemplateIntrospect;
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


public class FlatJsonMarshallerTestNew {

    @Test
    public void toFlatJson() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream()).getTemplate();
        TemplateIntrospect introspect = new TemplateIntrospect(template);
        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        FlatJsonMarshallerNew cut = new FlatJsonMarshallerNew(introspect);
        String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
        assertThat(actual).isNotNull();

        String expected = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        List<String> errors = compere(actual, expected);

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .containsExactlyInAnyOrder();

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Extra"))
                .containsExactlyInAnyOrder();
    }

    @Test
    public void toFlatJsonAllTypes() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream()).getTemplate();
        TemplateIntrospect introspect = new TemplateIntrospect(template);
        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8), Composition.class);
        FlatJsonMarshallerNew cut = new FlatJsonMarshallerNew(introspect);
        String actual = cut.toFlatJson(composition, new OPTParser(template).parse());
        assertThat(actual).isNotNull();

        String expected = IOUtils.toString(CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

        List<String> errors = compere(actual, expected);

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .containsExactlyInAnyOrder(
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/active/current_state|terminology, value: openehr",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/active/current_state|value, value: planned",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/completed/current_state|value, value: planned",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/planned/current_state|value, value: planned",
                        "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/planned/current_state|code, value: 526",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/completed/current_state|code, value: 526",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/planned/current_state|terminology, value: openehr",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/completed/current_state|terminology, value: openehr",
                        "Missing path: test_all_types/test_all_types:0/ordinal|code, value: at0014",
                        "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1",
                        "Missing path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/active/current_state|code, value: 526"
                );

        assertThat(errors)
                .filteredOn(s -> s.startsWith("Extra"))
                .containsExactlyInAnyOrder(
                        "Extra path: test_all_types/test_all_types:0/ordinal|code, value: at0015",
                        "Extra path: test_all_types/test_all_types:0/ordinal|value, value: NIQAMVFFOQMQOOM",
                        "Extra path: test_all_types/test_all_types:0/ordinal|ordinal, value: 1",
                        "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
                        "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing, value: P1D",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing|formalism, value: ISO8601",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/narrative, value: WAZotVojPPlPDKcOwCqQGlJjRdMC.ujKPgVAmyWABEoOgtrRSznogUWokmqmfgxjZOYmUEAcyvmnfz,KhtTC FrpwevrLJFNiJKzkzOwfOPRFQJjRmQCTW,ewdqvscogOkayvONWrOTPcCFnZsUod CAQ fuhLaBA.pFWdEyzyFgDODbueIlUjIGBdnIDDpMfjzWivfUDSEvTUsncmCgPdHnpONCuNGXUwFTaiSHHHJBZYssxGne.BLIbBQzXhG",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|code, value: 526",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|value, value: planned",
                        "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|terminology, value: openehr"
                );
    }

    public static List<String> compere(String actualJson, String expectedJson) throws JsonProcessingException {
        List<String> errors = new ArrayList<>();
        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

        Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
        Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);
/*
        Assertions.assertThat(actual.entrySet())
                .filteredOn(actualFilter)
                .extracting(Map.Entry::getKey, Map.Entry::getValue)
                .containsExactlyInAnyOrder(expected.entrySet().stream()
                        .filter(expectedFilter)
                        .map(e -> new Tuple(e.getKey(), e.getValue()))
                        .toArray(Tuple[]::new)
                );
*/
        actual.forEach((key, value) -> {
            if (!expected.containsKey(key) || !expected.get(key).equals(value)) {
                errors.add(String.format("Missing path: %s, value: %s", key, value));
            }
        });

        expected.forEach((key, value) -> {
            if (!actual.containsKey(key) || !actual.get(key).equals(value)) {
                errors.add(String.format("Extra path: %s, value: %s", key, value));
            }
        });

        return errors;
    }
}