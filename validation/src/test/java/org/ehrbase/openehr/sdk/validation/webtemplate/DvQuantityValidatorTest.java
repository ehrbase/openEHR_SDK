/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvQuantityValidatorTest extends AbstractRMObjectValidatorTest {

    private final DvQuantityValidator validator = new DvQuantityValidator();

    @Test
    void testValidate() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_quantity.json");

        var result = validator.validate(new DvQuantity("kg", 100.0, 1L), node);
        assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_List() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_quantity_list.json");

        var result = validator.validate(new DvQuantity("kg", 100.0, 1L), node);
        assertTrue(result.isEmpty());

        result = validator.validate(new DvQuantity("g", 100.0, 1L), node);
        assertEquals(1, result.size());
    }

    @Test
    void testValidate_ListValidation() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_quantity_list_validation.json");

        var result = validator.validate(new DvQuantity("kg", 100.0, 1L), node);
        assertTrue(result.isEmpty());
        result = validator.validate(new DvQuantity("lb", 300.0, 1L), node);
        assertTrue(result.isEmpty());

        result = validator.validate(new DvQuantity("kg", 300.0, 1L), node);
        assertEquals(1, result.size());
        result = validator.validate(new DvQuantity("lb", 500.0, 1L), node);
        assertEquals(1, result.size());
        result = validator.validate(new DvQuantity("lb", 300.00, 2L), node);
        assertEquals(1, result.size());
    }
}
