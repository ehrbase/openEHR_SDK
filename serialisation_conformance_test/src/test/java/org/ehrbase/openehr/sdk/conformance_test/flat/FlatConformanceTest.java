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
package org.ehrbase.openehr.sdk.conformance_test.flat;

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
import org.ehrbase.openehr.sdk.conformance_test.templateprovider.TestDataTemplateProvider;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatJasonProvider;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.test_data.composition.CompositionTestDataConformanceSDTJson;
import org.ehrbase.openehr.sdk.validation.CompositionValidator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FlatConformanceTest {

    private static final TestDataTemplateProvider TEMPLATE_PROVIDER = new TestDataTemplateProvider();

    private static List<Arguments> testRoundTripArguments() {
        List<Arguments> arguments = new ArrayList<>();

        Arrays.stream(CompositionTestDataConformanceSDTJson.values()).forEach(test -> {
            switch (test) {
                case EHRBASE_CONFORMANCE_DATA_TYPES_DV_DURATION:
                    arguments.add(Arguments.of(test, new String[] {}, new String[] {}, new String[] {
                        "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value",
                        "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/other_reference_ranges[1]/range/interval",
                        "/content[openEHR-EHR-SECTION.conformance_section.v0, 1]/items[openEHR-EHR-OBSERVATION.conformance_observation.v0, 1]/data[at0001]/events[at0002, 1]/data[at0003]/items[at0018, 1]/value/normal_range/interval"
                    }));
                    break;
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

        RMDataFormat cut = new FlatJasonProvider(TEMPLATE_PROVIDER).buildFlatJson(FlatFormat.SIM_SDT, templateId);

        String flat = IOUtils.toString(testData.getStream(), StandardCharsets.UTF_8);
        Composition unmarshal = cut.unmarshal(flat);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(unmarshal).isNotNull();

        CompositionValidator rmObjectValidator = new CompositionValidator();

        softAssertions
                .assertThat(rmObjectValidator.validate(
                        unmarshal, TEMPLATE_PROVIDER.buildIntrospect(templateId).orElseThrow()))
                .filteredOn(d -> !ArrayUtils.contains(expectedValidationErrorPath, d.getAqlPath()))
                .isEmpty();

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
