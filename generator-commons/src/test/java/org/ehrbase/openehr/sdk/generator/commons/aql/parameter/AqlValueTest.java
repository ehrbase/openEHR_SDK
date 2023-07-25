/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.generator.commons.aql.parameter;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class AqlValueTest {

    @Test
    public void testBuildAql() {
        Assertions.assertThat(new AqlValue(123).buildAql()).isEqualTo("123");
        Assertions.assertThat(new AqlValue(345566576767L).buildAql()).isEqualTo("345566576767");
        Assertions.assertThat(new AqlValue(34.566f).buildAql()).isEqualTo("34.566");
        Assertions.assertThat(new AqlValue(34.56634556d).buildAql()).isEqualTo("34.56634556");
        Assertions.assertThat(new AqlValue("Test").buildAql()).isEqualTo("'Test'");
        Assertions.assertThat(new AqlValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC)).buildAql())
                .isEqualTo("'2019-04-03T22:00:00Z'");
        Assertions.assertThat(new AqlValue(true).buildAql()).isEqualTo("true");
    }
}
