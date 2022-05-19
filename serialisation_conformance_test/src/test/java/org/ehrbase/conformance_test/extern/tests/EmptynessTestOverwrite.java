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
package org.ehrbase.conformance_test.extern.tests;

import static org.ehrbase.conformance_test.extern.tests.WorkflowIdTestOverwrite.OBJECT_MAPPER;

import care.better.platform.web.template.EmptynessTest;
import care.better.platform.web.template.context.CompositionBuilderContextKey;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.ImmutableMap;
import java.util.Collections;
import org.assertj.core.api.Assertions;
import org.ehrbase.conformance_test.extern.CompositionConverterImp;
import org.junit.jupiter.api.Test;

public class EmptynessTestOverwrite extends EmptynessTest {

    @Override
    /*
    we do not automatically remove empty / null values
     */
    public void emptyEvaluation() throws Exception {}

    @Override
    @Test
    public void emptyComposition() throws Exception {

        String template = this.getFileContent("/res/openEHR-EHR-COMPOSITION.t_allergist_examination_child_lanit.opt");
        JsonNode rawComposition = new CompositionConverterImp()
                .convertStructuredToRaw(
                        template,
                        "ru",
                        OBJECT_MAPPER.writeValueAsString(Collections.emptyMap()),
                        ImmutableMap.of(
                                CompositionBuilderContextKey.LANGUAGE.getKey(),
                                "ru",
                                CompositionBuilderContextKey.TERRITORY.getKey(),
                                "RU",
                                CompositionBuilderContextKey.COMPOSER_NAME.getKey(),
                                "Composer"),
                        OBJECT_MAPPER);
        Assertions.assertThat(rawComposition).isNotNull();
        Assertions.assertThat(rawComposition.get("content")).isNull();
    }
}
