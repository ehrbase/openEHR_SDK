/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.webtemplate.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternField.*;
import static org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity.*;

import java.util.Map;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TemporalPatternTest {

    @Test
    void parsesTheValidityOfEachField() {
        assertThat(TemporalPattern.parse("yyyy-mm-XX"))
                .isEqualTo(new TemporalPattern(Map.of(MONTH, MANDATORY, DAY, PROHIBITED)));
        assertThat(TemporalPattern.parse("HH:??:xxZ"))
                .isEqualTo(new TemporalPattern(Map.of(MINUTE, OPTIONAL, SECOND, PROHIBITED, OFFSET, MANDATORY_UTC)));
        assertThat(TemporalPattern.parse("HH:MM:SS"))
                .isEqualTo(new TemporalPattern(Map.of(MINUTE, MANDATORY, SECOND, MANDATORY)));
        assertThat(TemporalPattern.parse(" yyyy-mm-ddThh:mm:ss+hh "))
                .isEqualTo(new TemporalPattern(Map.of(
                        MONTH, MANDATORY,
                        DAY, MANDATORY,
                        HOUR, MANDATORY,
                        MINUTE, MANDATORY,
                        SECOND, MANDATORY,
                        OFFSET, MANDATORY_HOURS)));
    }

    @ParameterizedTest
    @CsvSource({
        "HH:MM:SSZ, MANDATORY_UTC",
        "HH:MM:SSz, MANDATORY_UTC",
        "HH:MM:SS+hh, MANDATORY_HOURS",
        "HH:MM:SS+hh:mm, MANDATORY",
        "hh:mm:ss-hhmm, MANDATORY",
        "HH:XX:XXZ, MANDATORY_UTC",
        "yyyy-??-??T??:??:??Z, MANDATORY_UTC",
    })
    void parsesTheTimezoneSuffix(String pattern, TemporalPatternValidity offset) {
        assertThat(TemporalPattern.parse(pattern).validities()).containsEntry(OFFSET, offset);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(
            strings = {
                "  ",
                "dd/mm/yyyy",
                "yyyy-mm-ddZ",
                "1995-??-XX",
                "yyyy-mm-dd-XX",
                "??:??:??",
                "HH:XX:??",
                "HH:XX:SS",
                "HH:??:SS",
                "yyyy-XX-dd",
                "yyyy-??-dd",
                "yyyy-mm-XXTHH:MM:SS",
                "yyyy-??-XXT??:??:??",
                "yyyy-??-??THH:??:??",
                "yyyy-mm-ddTXX:XX:XX+hh"
            })
    void unsupportedPatternReturnsNull(String pattern) {
        assertThat(TemporalPattern.parse(pattern)).isNull();
    }

    @ParameterizedTest(name = "{0} / {1}")
    @CsvSource({
        "yyyy-mm-dd, DATE, true",
        "yyyy-mm-dd, TIME, false",
        "yyyy-mm-dd, DATETIME, false",
        "HH:MM:SS, DATE, false",
        "HH:MM:SS, TIME, true",
        "HH:MM:SS, DATETIME, false",
        "yyyy-mm-ddTHH:MM:SS, DATE, false",
        "yyyy-mm-ddTHH:MM:SS, TIME, false",
        "yyyy-mm-ddTHH:MM:SS, DATETIME, true",
    })
    void isOfTheInputTypeOfItsKind(String pattern, String inputType, boolean ofInputType) {
        assertThat(TemporalPattern.parse(pattern).isOfInputType(inputType)).isEqualTo(ofInputType);
    }
}
