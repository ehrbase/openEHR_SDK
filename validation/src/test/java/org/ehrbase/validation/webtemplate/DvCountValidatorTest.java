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

import com.nedap.archie.rm.datavalues.quantity.DvCount;
import com.nedap.archie.rmobjectvalidator.RMObjectValidationMessage;
import java.util.List;
import java.util.NoSuchElementException;
import org.ehrbase.validation.ConstraintViolation;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvCountValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvCountValidator validator = new DvCountValidator();

  @Test
  void testValidate() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_count.json");

    var result = validator.validate(new DvCount(10L), node);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidate_Range() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_count_range.json");

    var result = validator.validate(new DvCount(10L), node);
    assertTrue(result.isEmpty());
    result = validator.validate(new DvCount(1L), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvCount(0L), node);
    assertEquals(1, result.size());
    result = validator.validate(new DvCount(20L), node);
    assertEquals(1, result.size());
    result = validator.validate(new DvCount(100L), node);
    assertEquals(1, result.size());
  }

  @Test
  void testValidate_Range_Unbounded() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_count_range_unbounded.json");

    var result = validator.validate(new DvCount(0L), node);
    assertTrue(result.isEmpty());
    result = validator.validate(new DvCount(10L), node);
    assertTrue(result.isEmpty());
    result = validator.validate(new DvCount(10000L), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvCount(-1L), node);
    assertEquals(1, result.size());
  }
}
