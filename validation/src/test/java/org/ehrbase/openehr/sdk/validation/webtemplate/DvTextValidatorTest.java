/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.datavalues.DvText;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvTextValidatorTest extends AbstractRMObjectValidatorTest {

    private final DvTextValidator validator = new DvTextValidator();

    @Test
    void testValidate() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_text.json");

        var result = validator.validate(new DvText("Test value"), node);
        assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_ListOpen() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_text_list_open.json");

        var result = validator.validate(new DvText("No known allergies"), node);
        assertTrue(result.isEmpty());
        result = validator.validate(new DvText("Test value"), node);
        assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_ListClose() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_text_list_close.json");

        var result = validator.validate(new DvText("No known allergies"), node);
        assertTrue(result.isEmpty());

        result = validator.validate(new DvText("Test value"), node);
        assertEquals(1, result.size());
    }

    @Test
    void testValidate_Pattern() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_text_pattern.json");

        var result = validator.validate(new DvText("No known allergies"), node);
        assertTrue(result.isEmpty());
        result = validator.validate(new DvText("No known medication allergies"), node);
        assertTrue(result.isEmpty());

        result = validator.validate(new DvText("Test value"), node);
        assertEquals(1, result.size());
    }
}
