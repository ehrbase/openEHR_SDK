/*
 * Copyright 2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ehrbase.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDuration;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvDurationValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvDurationValidator validator = new DvDurationValidator();

  @Test
  void testValidate_Duration() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_duration.json");

    var result = validator.validate(new DvDuration("PT10H20M10S"), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvDuration("PT20H45M45S"), node);
    assertFalse(result.isEmpty());
  }

  @Test
  void testValidate_Period() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_period.json");

    var result = validator.validate(new DvDuration("P10Y5M3D"), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvDuration("P5M3D"), node);
    assertFalse(result.isEmpty());
    result = validator.validate(new DvDuration("P1Y3D"), node);
    assertFalse(result.isEmpty());
  }

  @Test
  void testValidate_PeriodDuration() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_duration_period_duration.json");

    var result = validator.validate(new DvDuration("P10Y5M2W3DT5H30M5S"), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvDuration("P10Y5M3DT12H30M5S"), node);
    assertEquals(2, result.size());
  }
}
