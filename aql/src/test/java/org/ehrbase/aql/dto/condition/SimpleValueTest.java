package org.ehrbase.aql.dto.condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

class SimpleValueTest {

    @Test
    void forImmutableObject() {
        assertThat(SimpleValue.forImmutableObject(null)).isSameAs(SimpleValue.NULL_VALUE);

        String exampleString = "test";

        Stream.<Object>of(exampleString, 1, 2L, 3., LocalDateTime.now(), false)
                .forEach(v ->
                        assertThat(SimpleValue.forImmutableObject(v).getValue()).isSameAs(v));

        SimpleValue exampleSimpleValue = new SimpleValue(exampleString);
        assertThat(SimpleValue.forImmutableObject(exampleSimpleValue)).isSameAs(exampleSimpleValue);
        assertThat(SimpleValue.forImmutableObject(exampleSimpleValue).getValue())
                .isSameAs(exampleString);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> SimpleValue.forImmutableObject(new StringBuilder(exampleString)));
    }
}