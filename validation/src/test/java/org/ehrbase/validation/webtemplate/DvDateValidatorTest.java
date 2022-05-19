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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvDateValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvDateValidator validator = new DvDateValidator();

  @Test
  void testValidate() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_date.json");

    var result = validator.validate(new DvDate(LocalDate.now()), node);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidate_Range() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_date_range.json");

    var result = validator.validate(new DvDate(LocalDate.of(2022, 1, 10)), node);
    assertTrue(result.isEmpty());

    result = validator.validate(new DvDate(LocalDate.of(2021, 1, 22)), node);
    assertEquals(1, result.size());
    result = validator.validate(new DvDate(LocalDate.of(2022, 2, 1)), node);
    assertEquals(1, result.size());
  }

  @Test
  void testValidate_Pattern() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_date_pattern.json");

    var result = validator.validate(new DvDate(LocalDate.of(2022, 1, 10)), node);
    assertTrue(result.isEmpty());
  }
}
