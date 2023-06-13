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
package org.ehrbase.aql.roundtriptest;

import static org.assertj.core.api.Assertions.assertThat;

import org.ehrbase.aql.dto.AqlQuery;
import org.ehrbase.aql.parser.AqlQueryParser;
import org.ehrbase.aql.render.AqlRenderer;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author Stefan Spiska
 */
@Disabled
class AqlRoundTripTest {

    @ParameterizedTest
    @EnumSource(AqlExpressionTestCase.class)
    void testAqlExpressionTest(AqlExpressionTestCase testData) {

        assertRoundTrip(testData);
    }

    @ParameterizedTest
    @EnumSource(AqlExpressionWithParameterTestCase.class)
    void testAqlExpressionWithParameterTest(AqlExpressionWithParameterTestCase testData) {

        assertRoundTrip(testData);
    }

    @ParameterizedTest
    @EnumSource(UCTestData.class)
    void testUC(UCTestData testData) {

        assertRoundTrip(testData);
    }

    @ParameterizedTest
    @EnumSource(PerformanceTestCase.class)
    void testPerformanceQuery(PerformanceTestCase testData) {

        assertRoundTrip(testData);
    }

    void assertRoundTrip(AqlTestCase testData) {
        AqlQueryParser cut = new AqlQueryParser();
        AqlQuery actual = cut.parse(testData.getTestAql());

        assertThat(actual).isNotNull();

        String actualAql = AqlRenderer.render(actual);

        assertThat(actualAql).isEqualTo(testData.getExpectedAql());
    }
}
