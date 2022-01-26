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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvTimeValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvTimeValidator validator = new DvTimeValidator();

  @Test
  void testValidate() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_time.json");

    var result = validator.validate(new DvTime(LocalTime.now()), node);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidate_Range() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_time_range.json");
    OffsetDateTime value;

    var result = validator.validate(new DvTime(LocalTime.of(11, 30)), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvTime(LocalTime.of(8, 30)), node);
    assertEquals(1, result.size());
    result = validator.validate(new DvTime(LocalTime.of(20, 30)), node);
    assertEquals(1, result.size());
  }

  @Test
  void testValidate_Pattern() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_time_pattern.json");

    var result = validator.validate(new DvTime(LocalTime.of(11, 30)), node);
    assertTrue(result.isEmpty());
  }
}
