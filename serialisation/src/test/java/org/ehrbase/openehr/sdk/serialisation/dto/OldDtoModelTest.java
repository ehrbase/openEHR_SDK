/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.dto;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.groups.Tuple;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.CoronaAnamneseComposition;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition.VorhandenDefiningcode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.TestAllTypesEnV1Composition;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.openehr.sdk.serialisation.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataCanonicalJson;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataSimSDTJson;
import org.junit.Test;

public class OldDtoModelTest {

    class TestCase {
        long id;
        CompositionTestDataSimSDTJson simSDTJson;
        String templateId;
        Class<?> dtoClass;
        String[] missing;
        String[] extra;

        public TestCase(
                long id,
                CompositionTestDataSimSDTJson simSDTJson,
                String templateId,
                Class<?> dtoClass,
                String[] missing,
                String[] extra) {
            this.id = id;
            this.simSDTJson = simSDTJson;
            this.templateId = templateId;
            this.dtoClass = dtoClass;
            this.missing = missing;
            this.extra = extra;
        }
    }

    @Test
    public void testRoundTrip() throws IOException {

        List<TestCase> testCaseList = new ArrayList<>();

        testCaseList.add(new TestCase(
                1,
                CompositionTestDataSimSDTJson.ALL_TYPES,
                "test_all_types.en.v1",
                TestAllTypesEnV1Composition.class,
                new String[] {
                    "Missing path: test_all_types/test_all_types:0/identifier|id, value: 55175056",
                    "Missing path: test_all_types/test_all_types:0/proportion_any|type, value: 1"
                },
                new String[] {
                    "Extra path: test_all_types/test_all_types:0/identifier, value: 55175056",
                    "Extra path: test_all_types/test_all_types:0/proportion_any|type, value: 1.0",
                    "Extra path: test_all_types/test_all_types:0/coded_text|value, value: value1",
                    "Extra path: test_all_types/test_all_types:0/coded_text_terminology|value, value: .HCXbqCyQtseLkDyKS,QLpOdDZxrEJ",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing, value: P1D",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing|formalism, value: ISO8601",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|code, value: 526",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|value, value: planned",
                    "Extra path: test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|terminology, value: openehr"
                }));
        testCaseList.add(new TestCase(
                2,
                CompositionTestDataSimSDTJson.CORONA,
                "Corona_Anamnese",
                CoronaAnamneseComposition.class,
                new String[] {},
                new String[] {}));

        SoftAssertions softly = new SoftAssertions();

        for (TestCase testCase : testCaseList) {
            checkTestCase(testCase, softly);
        }

        softly.assertAll();
    }

    @Test
    public void testFlattenCorona() throws IOException {
        Composition composition = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter cut = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        CoronaAnamneseComposition actual = cut.toGeneratedDto(composition, CoronaAnamneseComposition.class);
        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getSymptome()).isNotNull();
        Assertions.assertThat(actual.getSymptome().getHeiserkeit()).isNotNull();
        Assertions.assertThat(actual.getSymptome().getHeiserkeit().getVorhandenDefiningcode())
                .isEqualTo(VorhandenDefiningcode.NICHT_VORHANDEN);
    }

    @Test
    public void testUnflattenCorona() throws IOException {
        Composition expected = new CanonicalJson()
                .unmarshal(
                        IOUtils.toString(CompositionTestDataCanonicalJson.CORONA.getStream(), StandardCharsets.UTF_8),
                        Composition.class);
        RmToGeneratedDtoConverter flattener = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        CoronaAnamneseComposition coronaAnamneseComposition =
                flattener.toGeneratedDto(expected, CoronaAnamneseComposition.class);

        GeneratedDtoToRmConverter cut = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        Composition actual = (Composition) cut.toRMObject(coronaAnamneseComposition);
        assertThat(actual).isNotNull();
        assertThat(actual.getContent())
                .extracting(Locatable::getNameAsString, Locatable::getArchetypeNodeId)
                .containsExactlyInAnyOrder(actual.getContent().stream()
                        .map(c -> new Tuple(c.getNameAsString(), c.getArchetypeNodeId()))
                        .toArray(Tuple[]::new));
    }

    public void checkTestCase(TestCase testCase, SoftAssertions softly) throws IOException {

        String value = IOUtils.toString(testCase.simSDTJson.getStream(), UTF_8);
        RMDataFormat flatJson = new FlatJasonProvider(new TestDataTemplateProvider())
                .buildFlatJson(FlatFormat.SIM_SDT, testCase.templateId);

        Composition composition = flatJson.unmarshal(value);

        RmToGeneratedDtoConverter flattener = new RmToGeneratedDtoConverter(new TestDataTemplateProvider());
        Object flatten = flattener.toGeneratedDto(composition, testCase.dtoClass);

        GeneratedDtoToRmConverter unflattener = new GeneratedDtoToRmConverter(new TestDataTemplateProvider());
        RMObject actual = unflattener.toRMObject(flatten);

        String actualFlat = flatJson.marshal(actual);

        List<String> errors = compare(actualFlat, value);

        softly.assertThat(errors)
                .filteredOn(s -> s.startsWith("Missing"))
                .as("Test Case %s", testCase.id)
                .containsExactlyInAnyOrder(testCase.missing);

        softly.assertThat(errors)
                .filteredOn(s -> s.startsWith("Extra"))
                .as("Test Case %s", testCase.id)
                .containsExactlyInAnyOrder(testCase.extra);
    }

    private static List<String> compare(String actualJson, String expectedJson) throws JsonProcessingException {
        List<String> errors = new ArrayList<>();
        ObjectMapper objectMapper = ArchieObjectMapperProvider.getObjectMapper();

        Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
        Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

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
