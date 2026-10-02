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
package org.ehrbase.openehr.sdk.webtemplate.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternField.*;
import static org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity.*;

import java.util.Map;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern;
import org.ehrbase.openehr.sdk.webtemplate.model.TemporalPattern.TemporalPatternValidity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TemporalPatternParserTest {

    @Test
    void parsesTheValidityOfEachField() {
        assertThat(TemporalPatternParser.parse("yyyy-mm-XX"))
                .isEqualTo(new TemporalPattern(Map.of(MONTH, MANDATORY, DAY, PROHIBITED)));
        assertThat(TemporalPatternParser.parse("HH:??:xxZ"))
                .isEqualTo(new TemporalPattern(Map.of(MINUTE, OPTIONAL, SECOND, PROHIBITED, OFFSET, MANDATORY_UTC)));
        assertThat(TemporalPatternParser.parse(" yyyy-mm-ddThh:mm:ss+hh "))
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
        "HH:MM:SS+hh, MANDATORY_HOURS",
        "HH:MM:SS+hh:mm, MANDATORY",
        "hh:mm:ss-hhmm, MANDATORY",
        "HH:MM:SS,",
    })
    void parsesTheTimezoneSuffix(String pattern, TemporalPatternValidity offset) {
        assertThat(TemporalPatternParser.parse(pattern).validities().get(OFFSET))
                .isEqualTo(offset);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "dd/mm/yyyy", "yyyy-mm-ddZ", "1995-??-XX", "yyyy-mm-dd-XX", "??:??:??"})
    void ignoresUnsupportedPatterns(String pattern) {
        assertThat(TemporalPatternParser.parse(pattern)).isNull();
    }
}
