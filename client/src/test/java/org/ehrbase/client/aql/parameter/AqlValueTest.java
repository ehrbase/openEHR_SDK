/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.parameter;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.Test;

public class AqlValueTest {

  @Test
  public void testBuildAql() {
    assertThat(new AqlValue(123).buildAql()).isEqualTo("123");
    assertThat(new AqlValue(345566576767L).buildAql()).isEqualTo("345566576767");
    assertThat(new AqlValue(34.566f).buildAql()).isEqualTo("34.566");
    assertThat(new AqlValue(34.56634556d).buildAql()).isEqualTo("34.56634556");
    assertThat(new AqlValue("Test").buildAql()).isEqualTo("'Test'");
    assertThat(
            new AqlValue(OffsetDateTime.of(2019, 04, 03, 22, 00, 00, 00, ZoneOffset.UTC))
                .buildAql())
        .isEqualTo("'2019-04-03T22:00:00Z'");
    assertThat(new AqlValue(true).buildAql()).isEqualTo("true");
  }
}
