/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.render;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class AqlRenderTest {

    @Test
    void render() {

        String aql =
                "select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";
        String expected = aql;

        test(aql, expected);
    }

    private static void test(String aql, String expected) {
        AqlDto aqlDto = new AqlToDtoParser().parse(aql);

        String render = new AqlRender(aqlDto).render();

        assertThat(render).isEqualTo(expected);
    }
}
