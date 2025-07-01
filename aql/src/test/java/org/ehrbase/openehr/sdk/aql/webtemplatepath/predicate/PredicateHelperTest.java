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
package org.ehrbase.openehr.sdk.aql.webtemplatepath.predicate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/**
 * @author Stefan Spiska
 */
class PredicateHelperTest {

    private enum TestCase {
        // T1_FULL("archetype_node_id=at001 and name/value='name1' or archetype_node_id=at001 and name/value='name2'",
        // AqlPath.OtherPredicatesFormat.FULL,"archetype_node_id=at001 and name/value='name1' or archetype_node_id=at001
        // and name/value='name2'"),
        // T1_SHORT("archetype_node_id=at001 and name/value='name1' or archetype_node_id=at001 and name/value='name2'",
        // AqlPath.OtherPredicatesFormat.SHORTED,"archetype_node_id=at001 and name/value='name1' or
        // archetype_node_id=at001 and name/value='name2'"),
        // T1_NODE("archetype_node_id=at001 and name/value='name1' or archetype_node_id=at001 and name/value='name2'",
        // AqlPath.OtherPredicatesFormat.NONE,"at001, 'name1' or at001, 'name2'"),
        T2_FULL(
                "at001,'name1' and path/value='abc'",
                AqlPath.OtherPredicatesFormat.FULL,
                "at001 and name/value='name1' and path/value='abc'"),
        T2_SHORT(
                "at001,'name1' and path/value='abc'",
                AqlPath.OtherPredicatesFormat.SHORTED,
                "at001,'name1' and path/value='abc'"),
        T2_NONE("at001,'name1' and path/value='abc'", AqlPath.OtherPredicatesFormat.NONE, "at001"),
        T3_FULL(
                "name/value='name1' and archetype_node_id=at001",
                AqlPath.OtherPredicatesFormat.FULL,
                "at001 and name/value='name1'"),
        T3_SHORT(
                "name/value='name1' and archetype_node_id=at001",
                AqlPath.OtherPredicatesFormat.SHORTED,
                "at001,'name1'"),
        T3_NONE("name/value='name1' and archetype_node_id=at001", AqlPath.OtherPredicatesFormat.NONE, "at001");

        final String input;
        final AqlPath.OtherPredicatesFormat format;
        final String expected;

        TestCase(String input, AqlPath.OtherPredicatesFormat format, String expected) {
            this.input = input;
            this.format = format;
            this.expected = expected;
        }
    }

    @ParameterizedTest
    @EnumSource(TestCase.class)
    void roundTrip(TestCase testCase) {

        Predicate predicate = PredicateHelper.buildPredicate(testCase.input);

        StringBuilder sb = new StringBuilder();
        PredicateHelper.format(sb, predicate, testCase.format);

        assertThat(sb).hasToString(testCase.expected);
    }

    @Test
    void remove() {
        Predicate predicate = PredicateHelper.buildPredicate("name/value='name1' and archetype_node_id=at001");

        {
            PredicateLogicalAndOperation actual = PredicateHelper.remove(
                    (PredicateLogicalAndOperation) predicate, ComparisonOperatorSymbol.EQ, PredicateHelper.NAME_VALUE);
            StringBuilder sb = new StringBuilder();
            PredicateHelper.format(sb, actual, AqlPath.OtherPredicatesFormat.SHORTED);
            assertThat(sb).hasToString("at001");
        }
        {
            PredicateLogicalAndOperation actual = PredicateHelper.remove(
                    (PredicateLogicalAndOperation) predicate,
                    ComparisonOperatorSymbol.GT_EQ,
                    PredicateHelper.NAME_VALUE);
            StringBuilder sb = new StringBuilder();
            PredicateHelper.format(sb, actual, AqlPath.OtherPredicatesFormat.SHORTED);
            assertThat(sb).hasToString("at001,'name1'");
        }
        {
            PredicateLogicalAndOperation actual =
                    PredicateHelper.remove((PredicateLogicalAndOperation) predicate, null, PredicateHelper.NAME_VALUE);
            StringBuilder sb = new StringBuilder();
            PredicateHelper.format(sb, actual, AqlPath.OtherPredicatesFormat.SHORTED);
            assertThat(sb).hasToString("at001");
        }
    }
}
