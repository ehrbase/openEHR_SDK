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

import com.nedap.archie.rm.datavalues.DvBoolean;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvBooleanValidatorTest extends AbstractRMObjectValidatorTest {

  private final DvBooleanValidator validator = new DvBooleanValidator();

  @Test
  void testValidate() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_boolean.json");
    var dvBoolean = new DvBoolean();

    dvBoolean.setValue(true);
    var result = validator.validate(dvBoolean, node);
    assertTrue(result.isEmpty());
    dvBoolean.setValue(false);
    result = validator.validate(dvBoolean, node);
    assertTrue(result.isEmpty());
  }

  @Test
  void testValidate_TrueAllowed() throws Exception {
    var node = parseNode("/webtemplate_nodes/dv_boolean_true_allowed.json");
    var dvBoolean = new DvBoolean();

    dvBoolean.setValue(true);
    var result = validator.validate(dvBoolean, node);
    assertTrue(result.isEmpty());

    dvBoolean.setValue(false);
    result = validator.validate(dvBoolean, node);
    assertEquals(1, result.size());
  }
}
