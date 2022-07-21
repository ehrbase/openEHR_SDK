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

import org.ehrbase.aql.binder.AqlBinder;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author Stefan Spiska
 */
class AqlRoundTripTest {

    @ParameterizedTest
    @EnumSource(AqlExpressionTestData.class)
    void testAqlExpressionTest(AqlExpressionTestData testData) {

        testAql(testData);
    }

    @ParameterizedTest
    @EnumSource(AqlExpressionWithParameterTestData.class)
    void testAqlExpressionWithParameterTest(AqlExpressionWithParameterTestData testData) {

        testAql(testData);
    }

    @ParameterizedTest
    @EnumSource(UCTestData.class)
    void testUC(UCTestData testData) {

        testAql(testData);
    }

    @ParameterizedTest
    @EnumSource(PerformanceTestCase.class)
    void testPerformanceQuery(PerformanceTestCase testData) {

        testAql(testData);
    }

    void testAql(AqlTestDto testData) {
        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(testData.getTestAql());

        assertThat(actual).isNotNull();

        String actualAql = new AqlBinder().bind(actual).getLeft().buildAql();

        assertThat(actualAql).isEqualTo(testData.getExpectedAql());
    }
}
