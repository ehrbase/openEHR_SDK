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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.nedap.archie.rm.datavalues.quantity.datetime.DvTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "HH:MM:SS, 10:30:47",
        "HH:MM:??, 10:30",
        "HH:MM:??, 10:30:47",
        "HH:MM:XX, 10:30",
        "HH:??:??, 10",
        "HH:??:??, 10:30",
        "HH:??:??, 10:30:47",
        "HH:??:XX, 10",
        "HH:??:XX, 10:30",
        "HH:XX:XX, 10",
        "HH:MM:SS, 10:30:47.5Z",
        "HH:??:XX, 10:30+02:00",
        "hh:mm:ss, 10:30:47",
        "HH:MM:SS+HH:MM, 10:30:47",
        "HH:MM,    10",
    })
    void validateAcceptedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_TIME", "TIME", pattern);
        var result = validator.validate(new DvTime(OpenEHRDateTimeParseUtils.parseTime(value)), node);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "HH:MM:SS, 10",
        "HH:MM:SS, 10:30",
        "HH:MM:??, 10",
        "HH:MM:XX, 10",
        "HH:MM:XX, 10:30:47",
        "HH:??:XX, 10:30:47",
        "HH:XX:XX, 10:30",
        "HH:XX:XX, 10:30:47",
        "HH:MM:XX, 10:30:47.5",
        "HH:??:xx, 10:30:47",
        "HH:MM:SSZ, 10:30",
    })
    void validateRejectedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_TIME", "TIME", pattern);
        var result = validator.validate(new DvTime(OpenEHRDateTimeParseUtils.parseTime(value)), node);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMessage()).contains(pattern);
    }
}
