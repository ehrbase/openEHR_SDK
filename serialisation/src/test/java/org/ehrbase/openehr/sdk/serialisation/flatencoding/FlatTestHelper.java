/*
 * Copyright (c) 2024 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.flatencoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;

public class FlatTestHelper {

    public record Error(Type type, String path, Object value) {

        public enum Type {
            MISSING, // String.format("Missing path: '%s', value: '%s'", key, value)
            EXTRA // String.format("Extra path: '%s', value: '%s'", key, value)
        }

        public String describe() {
            return switch (type) {
                case MISSING -> String.format("Missing path: %s, value: %s", path, value);
                case EXTRA -> String.format("Extra path: %s, value: %s", path, value);
            };
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Error> compere(String actualJson, String expectedJson) throws JsonProcessingException {
        List<Error> errors = new ArrayList<>();
        ObjectMapper objectMapper = ArchieObjectMapperProvider.getObjectMapper();

        Map<String, Object> actual = objectMapper.readValue(actualJson, Map.class);
        Map<String, Object> expected = objectMapper.readValue(expectedJson, Map.class);

        actual.forEach((key, value) -> {
            Object expectedValue = expected.get(key);
            if (expectedValue == null || !expectedValue.equals(value)) {
                errors.add(new Error(Error.Type.MISSING, key, value));
            }
        });

        expected.forEach((key, value) -> {
            Object actualValue = actual.get(key);
            if (actualValue == null || !actualValue.equals(value)) {
                errors.add(new Error(Error.Type.EXTRA, key, value));
            }
        });

        return errors;
    }

    public static void assertErrors(List<FlatTestHelper.Error> errors, List<String> missing, List<String> extra) {

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions
                .assertThat(errors)
                .filteredOn(s -> s.type() == FlatTestHelper.Error.Type.MISSING)
                .map(FlatTestHelper.Error::describe)
                .containsExactlyInAnyOrderElementsOf(missing);

        softAssertions
                .assertThat(errors)
                .filteredOn(s -> s.type() == FlatTestHelper.Error.Type.EXTRA)
                .map(FlatTestHelper.Error::describe)
                .containsExactlyInAnyOrderElementsOf(extra);

        softAssertions.assertAll();
    }
}
