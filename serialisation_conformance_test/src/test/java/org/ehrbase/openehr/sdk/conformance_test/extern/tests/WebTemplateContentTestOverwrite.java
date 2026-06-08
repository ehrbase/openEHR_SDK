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
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import care.better.platform.web.template.WebTemplateContentTest;
import care.better.platform.web.template.context.CompositionBuilderContextKey;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class WebTemplateContentTestOverwrite extends WebTemplateContentTest {

    @Override
    @Test
    public void contextEndTimeReturned() throws Exception {
        String template = this.getFileContent("/res/Demo Vitals.opt");
        Map<String, Object> flatComposition = (new ImmutableMap.Builder())
                .put("vitals/vitals/body_temperature/any_event/temperature|magnitude", 39.1)
                .put("vitals/vitals/body_temperature/any_event/temperature|unit", "°C")
                .put("vitals/vitals/body_temperature/any_event/body_exposure", "at0031")
                .build();
        JsonNode rawComposition = this.getCompositionConverter()
                .convertFlatToRaw(
                        template,
                        "sl",
                        this.getObjectMapper().writeValueAsString(flatComposition),
                        ImmutableMap.of(
                                CompositionBuilderContextKey.LANGUAGE.getKey(),
                                "sl",
                                CompositionBuilderContextKey.TERRITORY.getKey(),
                                "SI",
                                CompositionBuilderContextKey.COMPOSER_NAME.getKey(),
                                "composer",
                                CompositionBuilderContextKey.START_TIME.getKey(),
                                OffsetDateTime.of(2017, 11, 1, 1, 30, 0, 0, ZoneOffset.UTC),
                                CompositionBuilderContextKey.END_TIME.getKey(),
                                OffsetDateTime.of(2017, 12, 1, 1, 30, 0, 0, ZoneOffset.UTC)),
                        this.getObjectMapper());
        Map<String, Object> retrivedFlat = this.getCompositionConverter()
                .convertRawToFlat(template, "sl", rawComposition.toString(), this.getObjectMapper());
        Assertions.assertThat(retrivedFlat)
                .contains(Assertions.entry("vitals/context/start_time", "2017-11-01T01:30:00.0Z"));
        Assertions.assertThat(retrivedFlat)
                .contains(Assertions.entry("vitals/context/_end_time", "2017-12-01T01:30:00.0Z"));
    }

    @Override
    @Test
    public void contextLocationReturned() throws Exception {
        String template = this.getFileContent("/res/Demo Vitals.opt");
        Map<String, Object> flatComposition = (new ImmutableMap.Builder())
                .put("vitals/vitals/body_temperature/any_event/temperature|magnitude", 39.1)
                .put("vitals/vitals/body_temperature/any_event/temperature|unit", "°C")
                .put("vitals/vitals/body_temperature/any_event/body_exposure", "at0031")
                .build();
        Map<String, Object> context = (new ImmutableMap.Builder())
                .put(CompositionBuilderContextKey.LANGUAGE.getKey(), "sl")
                .put(CompositionBuilderContextKey.TERRITORY.getKey(), "SI")
                .put(CompositionBuilderContextKey.COMPOSER_NAME.getKey(), "composer")
                .put(
                        CompositionBuilderContextKey.START_TIME.getKey(),
                        OffsetDateTime.of(2017, 11, 1, 1, 30, 0, 0, ZoneOffset.UTC))
                .put(
                        CompositionBuilderContextKey.END_TIME.getKey(),
                        OffsetDateTime.of(2017, 12, 1, 1, 30, 0, 0, ZoneOffset.UTC))
                .put(CompositionBuilderContextKey.LOCATION.getKey(), "I am here!")
                .build();
        JsonNode rawComposition = this.getCompositionConverter()
                .convertFlatToRaw(
                        template,
                        "sl",
                        this.getObjectMapper().writeValueAsString(flatComposition),
                        context,
                        this.getObjectMapper());
        Map<String, Object> retrivedFlat = this.getCompositionConverter()
                .convertRawToFlat(template, "sl", rawComposition.toString(), this.getObjectMapper());
        Assertions.assertThat(retrivedFlat)
                .contains(Assertions.entry("vitals/context/start_time", "2017-11-01T01:30:00.0Z"));
        Assertions.assertThat(retrivedFlat)
                .contains(Assertions.entry("vitals/context/_end_time", "2017-12-01T01:30:00.0Z"));
        Assertions.assertThat(retrivedFlat).contains(Assertions.entry("vitals/context/_location", "I am here!"));
    }
}
