/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

class WebTemplateValidationTest {

    @Test
    void temporalPatternFollowsThePattern() {
        WebTemplateValidation validation = new WebTemplateValidation();
        assertThat(validation.getTemporalPattern()).isNull();

        validation.setPattern("yyyy-mm-dd");
        TemporalPattern parsed = validation.getTemporalPattern();
        assertThat(parsed).isNotNull().isSameAs(validation.getTemporalPattern());

        validation.setPattern("HH:MM:SS");
        assertThat(validation.getTemporalPattern()).isNotNull().isNotSameAs(parsed);

        validation.setPattern("not a temporal pattern");
        assertThat(validation.getTemporalPattern()).isNull();
    }

    @Test
    void temporalPatternIsNotPartOfTheJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        WebTemplateValidation validation = new WebTemplateValidation();
        validation.setPattern("yyyy-mm-dd");

        String json = objectMapper.writeValueAsString(validation);
        assertThat(json).contains("\"pattern\":\"yyyy-mm-dd\"").doesNotContain("temporalPattern");

        WebTemplateValidation read = objectMapper.readValue(json, WebTemplateValidation.class);
        assertThat(read).isEqualTo(validation);
        assertThat(read.getTemporalPattern()).isNotNull();
    }
}
