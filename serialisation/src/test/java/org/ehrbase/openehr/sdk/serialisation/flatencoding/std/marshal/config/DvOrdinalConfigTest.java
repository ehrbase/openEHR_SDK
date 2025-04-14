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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.junit.jupiter.api.Test;

class DvOrdinalConfigTest {

    private Map<String, Object> buildChildValues(String currentTerm, DvOrdinal dvOrdinal) {
        return new DvOrdinalConfig().buildChildValues(currentTerm, dvOrdinal, new Context<>());
    }

    @Test
    void buildChildValuesOrdinalOnlySymbolNullSafe() {

        Map<String, Object> childValues =
                buildChildValues("nested:0/current_activity/ordinal", new DvOrdinal(1L, null));
        assertThat(childValues).hasSize(1).containsEntry("nested:0/current_activity/ordinal|ordinal", 1L);
    }

    @Test
    void buildChildValuesWithSymbol() {

        Map<String, Object> childValues = buildChildValues(
                "some_other/current_activity/ordinal",
                new DvOrdinal(42L, new DvCodedText("lorem ipsum", "PWVGUTHASM")));
        assertThat(childValues)
                .hasSize(3)
                .containsEntry("some_other/current_activity/ordinal|ordinal", 42L)
                .containsEntry("some_other/current_activity/ordinal|code", "PWVGUTHASM")
                .containsEntry("some_other/current_activity/ordinal|value", "lorem ipsum");
    }
}
