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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDate;
import java.time.LocalDate;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.ehrbase.openehr.sdk.webtemplate.parser.TemporalPatternMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void validatesRangeAndPatternIndependently() throws Exception {
        var node = parseNode("/webtemplate_nodes/dv_date_pattern_range.json");

        // in range, pattern violation, days not allowed
        var result = validator.validate(new DvDate(LocalDate.of(2022, 1, 10)), node);
        assertThat(result)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly("The value 2022-01-10 does not match the pattern yyyy-mm-XX");

        result = validator.validate(new DvDate(LocalDate.of(2021, 12, 31)), node);
        assertThat(result)
                .extracting(ConstraintViolation::getMessage)
                .containsExactly(
                        "The value 2021-12-31 does not match the pattern yyyy-mm-XX",
                        "The value 2021-12-31 must be at least 2022-01-01 and at most 2022-01-31");
    }

    @Test
    void disabledModeSkipsThePattern() {
        var node = nodeWithValidationPattern("DV_DATE", "DATE", "yyyy-mm-dd");
        var disabled = new DvDateValidator(TemporalPatternMode.DISABLED);

        var result = disabled.validate(new DvDate(OpenEHRDateTimeParseUtils.parseDate("2022")), node);
        assertThat(result).isEmpty();
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "yyyy-mm-dd, 2022-10-24",
        "yyyy-mm-??, 2022-10",
        "yyyy-mm-??, 2022-10-24",
        "yyyy-mm-XX, 2022-10",
        "yyyy-??-??, 2022",
        "yyyy-??-??, 2022-10",
        "yyyy-??-??, 2022-10-24",
        "yyyy-??-XX, 2022",
        "yyyy-??-XX, 2022-10",
        "yyyy-XX-XX, 2022",
        "yyyy-mm,    2022",
        "yyyy-mm-dd, 20221024",
    })
    void validateAcceptedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_DATE", "DATE", pattern);
        var result = validator.validate(new DvDate(OpenEHRDateTimeParseUtils.parseDate(value)), node);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "yyyy-mm-dd, 2022",
        "yyyy-mm-dd, 2022-10",
        "yyyy-mm-??, 2022",
        "yyyy-mm-XX, 2022",
        "yyyy-mm-XX, 2022-10-24",
        "yyyy-??-XX, 2022-10-24",
        "yyyy-XX-XX, 2022-10",
        "yyyy-XX-XX, 2022-10-24",
        "yyyy-??-XX, 20221024",
    })
    void validateRejectedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_DATE", "DATE", pattern);
        var result = validator.validate(new DvDate(OpenEHRDateTimeParseUtils.parseDate(value)), node);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMessage()).contains(pattern);
    }
}
