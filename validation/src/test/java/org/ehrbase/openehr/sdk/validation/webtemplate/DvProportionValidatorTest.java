/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.DvProportion;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvProportionValidatorTest extends AbstractRMObjectValidatorTest {

    private final DvProportionValidator validator = new DvProportionValidator();

    @Test
    void testValidate() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_proportion.json");

        var result = validator.validate(new DvProportion(50.0, 100.0, 3L), node);
        assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_Range() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_proportion_range.json");

        var result = validator.validate(new DvProportion(50.0, 100.0, 3L), node);
        assertTrue(result.isEmpty());

        result = validator.validate(new DvProportion(200.0, 100.0, 3L), node);
        assertEquals(1, result.size());
        result = validator.validate(new DvProportion(50.0, 120.0, 3L), node);
        assertEquals(1, result.size());
    }
}
