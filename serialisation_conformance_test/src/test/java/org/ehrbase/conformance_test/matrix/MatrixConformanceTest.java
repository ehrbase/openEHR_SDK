/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.conformance_test.matrix;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.conformance_test.templateprovider.TestDataTemplateProvider;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.serialisation.matrix.MatrixFormat;
import org.ehrbase.test_data.composition.CompositionTestDataConformanceSDTJson;
import org.ehrbase.validation.CompositionValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MatrixConformanceTest {

    private static final TestDataTemplateProvider TEMPLATE_PROVIDER = new TestDataTemplateProvider();

    private static List<Arguments> testRoundTripArguments() {
        List<Arguments> arguments = new ArrayList<>();

        Arrays.stream(CompositionTestDataConformanceSDTJson.values()).forEach(test -> {
            switch (test) {
                default:
                    arguments.add(Arguments.of(test, new String[] {}, new String[] {}, new String[] {}));
            }
        });

        return arguments;
    }

    @ParameterizedTest
    @MethodSource("testRoundTripArguments")
    void testRoundTrip(
            CompositionTestDataConformanceSDTJson testData,
            String[] expectedMissing,
            String[] expectedExtra,
            String[] expectedValidationErrorPath)
            throws IOException {

        String templateId = testData.getTemplate().getTemplateId();

        RMDataFormat flatJson = new FlatJasonProvider(TEMPLATE_PROVIDER).buildFlatJson(FlatFormat.SIM_SDT, templateId);

        String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
        Composition composition = flatJson.unmarshal(flat);

        MatrixFormat cut = new MatrixFormat(TEMPLATE_PROVIDER);

        String db_encoded = cut.marshal(composition);

        Composition actual = cut.unmarshal(db_encoded);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(actual).isNotNull();

        CompositionValidator rmObjectValidator = new CompositionValidator();

        softAssertions
                .assertThat(rmObjectValidator.validate(
                        actual, TEMPLATE_PROVIDER.buildIntrospect(templateId).orElseThrow()))
                .filteredOn(d -> !ArrayUtils.contains(expectedValidationErrorPath, d.getAqlPath()))
                .isEmpty();

        String actualString = flatJson.marshal(actual);

        String expected = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);

        List<String> errors = compere(actualString, expected);

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

    private List<String> compere(String actualJson, String expectedJson) throws JsonProcessingException {
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
