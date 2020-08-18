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
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Predicate;


public class FlatJsonMarshallerTest {

    @Test
    public void toFlatJson() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMMNESE.getStream()).getTemplate();
        TemplateIntrospect introspect = new TemplateIntrospect(template);
        Composition composition = new CanonicalJson().unmarshal(IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8), Composition.class);
        FlatJsonMarshaller cut = new FlatJsonMarshaller(introspect);
        String actual = cut.toFlatJson(composition);
        Assertions.assertThat(actual).isNotNull();

        String expected = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        compere(actual, expected, c -> true, c1 -> true);
    }

    public static void compere(String actualJson, String expectedJson, Predicate<Map.Entry<String, Object>> actualFilter, Predicate<Map.Entry<String, Object>> expectedFilter) throws JsonProcessingException {

        ObjectMapper objectMapper = JacksonUtil.getObjectMapper();

        Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
        Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

        Assertions.assertThat(actual.entrySet())
                .filteredOn(actualFilter)
                .extracting(Map.Entry::getKey, Map.Entry::getValue)
                .containsExactlyInAnyOrder(expected.entrySet().stream()
                        .filter(expectedFilter)
                        .map(e -> new Tuple(e.getKey(), e.getValue()))
                        .toArray(Tuple[]::new)
                );

    }
}