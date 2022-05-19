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
package org.ehrbase.validation.webtemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 */
class DvDateTimeValidatorTest extends AbstractRMObjectValidatorTest {

    private final DvDateTimeValidator validator = new DvDateTimeValidator();

    @Test
    void testValidate() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_datetime.json");

        var result = validator.validate(new DvDateTime(OffsetDateTime.now()), node);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testValidate_Range() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_datetime_range.json");
        OffsetDateTime value;

        value = OffsetDateTime.of(2022, 1, 1, 12, 0, 0, 0, ZoneOffset.UTC);
        var result = validator.validate(new DvDateTime(value), node);
        assertTrue(result.isEmpty());
        value = OffsetDateTime.of(2022, 1, 1, 11, 15, 0, 0, ZoneOffset.of("+01:00"));
        result = validator.validate(new DvDateTime(value), node);
        assertTrue(result.isEmpty());

        value = OffsetDateTime.of(2022, 1, 10, 12, 0, 0, 0, ZoneOffset.UTC);
        result = validator.validate(new DvDateTime(value), node);
        assertEquals(1, result.size());
        value = OffsetDateTime.of(2022, 1, 1, 7, 0, 0, 0, ZoneOffset.UTC);
        result = validator.validate(new DvDateTime(value), node);
        assertEquals(1, result.size());
        value = OffsetDateTime.of(2022, 1, 10, 18, 30, 0, 0, ZoneOffset.of("+01:00"));
        result = validator.validate(new DvDateTime(value), node);
        assertEquals(1, result.size());
    }

    @Test
    void testValidate_Pattern() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_datetime_pattern.json");
        var value = OffsetDateTime.of(2022, 1, 10, 12, 0, 0, 0, ZoneOffset.UTC);

        var result = validator.validate(new DvDateTime(value), node);
        assertTrue(result.isEmpty());
    }
}
