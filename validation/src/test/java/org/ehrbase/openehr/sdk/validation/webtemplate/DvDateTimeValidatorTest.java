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

import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeParseUtils;
import org.ehrbase.openehr.sdk.validation.ConstraintViolation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
    void testValidate_Range() {
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

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "yyyy-mm-ddThh:mm:ss, 2022-10-24T10:30:47",
        "yyyy-mm-ddThh:mm:ss, 2022-10-24T10:30:47.5Z",
        "yyyy-XX-XXTXX:XX:XX, 2022",
        "yyyy-??-??T??:??:??, 2022",
        "yyyy-??-??T??:??:??, 2022-10-24",
        "yyyy-??-??T??:??:??, 2022-10-24T10:30:47",
        "yyyy-mm-??TXX:XX:XX, 2022-10",
        "yyyy-mm-ddTXX:XX:XX, 2022-10-24",
        "yyyy-mm-ddT??:XX:XX, 2022-10-24",
        "yyyy-mm-ddT??:XX:XX, 2022-10-24T10",
        "yyyy-??-??T??:??:XX, 2022",
        "yyyy-??-??T??:??:XX, 2022-10-24T10:30",
        "yyyy-mm-ddThh:??:??, 2022-10-24T10",
        "yyyy-mm-ddThh:??:??, 2022-10-24T10:30:47.5Z",
        "yyyy-mm-ddThh:mm:XX, 2022-10-24T10:30",
        "YYYY-MM-DDT??:??:??, 2022-10-24T10",
    })
    void validateAcceptedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_DATE_TIME", "DATETIME", pattern);
        var result = validator.validate(new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime(value)), node);

        assertThat(result).isEmpty();
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "yyyy-mm-ddThh:mm:ss, 2022",
        "yyyy-mm-ddThh:mm:ss, 2022-10-24",
        "yyyy-mm-ddThh:mm:ss, 2022-10-24T10:30",
        "yyyy-XX-XXTXX:XX:XX, 2022-10",
        "yyyy-XX-XXTXX:XX:XX, 2022-10-24T10",
        "yyyy-mm-??TXX:XX:XX, 2022",
        "yyyy-mm-??TXX:XX:XX, 2022-10-24T10",
        "yyyy-mm-ddTXX:XX:XX, 2022-10",
        "yyyy-mm-ddTXX:XX:XX, 2022-10-24T10",
        "yyyy-mm-ddT??:XX:XX, 2022-10-24T10:30",
        "yyyy-??-??T??:??:XX, 2022-10-24T10:30:47",
        "yyyy-mm-ddThh:??:??, 2022-10-24",
        "yyyy-mm-ddThh:mm:XX, 2022-10-24T10",
        "yyyy-mm-ddThh:mm:XX, 2022-10-24T10:30:47",
        "yyyy-mm-ddTHH:MM:SS, 2022-10-24T10:30",
    })
    void validateRejectedPatternValues(String pattern, String value) {
        var node = nodeWithValidationPattern("DV_DATE_TIME", "DATETIME", pattern);
        var result = validator.validate(new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime(value)), node);

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getMessage()).contains(pattern);
    }

    @Test
    void checkCompleteErrorMessageOnRejectedPatternValidation() {
        var node = nodeWithValidationPattern("DV_DATE_TIME", "DATETIME", "yyyy-mm-ddThh:mm:ss");
        var result = validator.validate(new DvDateTime(OpenEHRDateTimeParseUtils.parseDateTime("2022-10")), node);

        assertThat(result)
                .singleElement()
                .extracting(ConstraintViolation::getMessage)
                .isEqualTo("""
                    The value 2022-10 does not conform to the pattern yyyy-mm-ddThh:mm:ss \
                    (day is mandatory, hour is mandatory, minute is mandatory, second is mandatory)""");
    }
}
