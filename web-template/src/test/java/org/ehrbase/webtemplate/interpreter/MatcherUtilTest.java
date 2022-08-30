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
package org.ehrbase.webtemplate.interpreter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.NAME_VALUE;

import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class MatcherUtilTest {

    @Test
    void matchesNodeInput() {

        WebTemplateInput value = new WebTemplateInput();
        WebTemplateInput magnitude = new WebTemplateInput();
        magnitude.setSuffix("magnitude");

        AqlPath valuePath = AqlPath.parse("/value");
        AqlPath magnitudePath = AqlPath.parse("/magnitude");

        assertThat(MatcherUtil.matches(valuePath.getBaseNode(), value)).isTrue();
        assertThat(MatcherUtil.matches(magnitudePath.getBaseNode(), value)).isFalse();
        assertThat(MatcherUtil.matches(valuePath.getBaseNode(), magnitude)).isFalse();
        assertThat(MatcherUtil.matches(magnitudePath.getBaseNode(), magnitude)).isTrue();
    }

    @Test
    void testMatchesAqlNodeNode() {

        WebTemplateNode webTemplateNode = new WebTemplateNode();
        webTemplateNode.setNodeId("openEHR-EHR-OBSERVATION.demo_observation.v0");
        webTemplateNode.setName("first_observation");
        webTemplateNode.setAqlPath(
                "/content[openEHR-EHR-SECTION.adhoc.v1, 'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0, 'first_observation']");

        assertThat(MatcherUtil.matches(AqlPath.parse("/items").getBaseNode(), webTemplateNode))
                .isTrue();
        assertThat(MatcherUtil.matches(AqlPath.parse("/value").getBaseNode(), webTemplateNode))
                .isFalse();
        assertThat(MatcherUtil.matches(
                        AqlPath.parse("/items[openEHR-EHR-OBSERVATION.demo_observation.v0]")
                                .getBaseNode(),
                        webTemplateNode))
                .isTrue();
        assertThat(MatcherUtil.matches(AqlPath.parse("/items[at0001]").getBaseNode(), webTemplateNode))
                .isFalse();
        assertThat(MatcherUtil.matches(
                        AqlPath.parse("/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation']")
                                .getBaseNode(),
                        webTemplateNode))
                .isTrue();
        assertThat(MatcherUtil.matches(
                        AqlPath.parse("/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation']")
                                .getBaseNode(),
                        webTemplateNode))
                .isFalse();
    }

    @Test
    void testMatchesContainmentNode() {

        WebTemplateNode webTemplateNode = new WebTemplateNode();
        webTemplateNode.setNodeId("openEHR-EHR-OBSERVATION.demo_observation.v0");
        webTemplateNode.setName("first_observation");
        webTemplateNode.setAqlPath(
                "/content[openEHR-EHR-SECTION.adhoc.v1, 'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0, 'first_observation']");

        Containment justType = new Containment();
        justType.setOtherPredicates(new PredicateLogicalAndOperation());
        justType.setType("OBSERVATION");
        assertThat(MatcherUtil.matches(justType, webTemplateNode)).isTrue();

        Containment containment = new Containment();
        containment.setOtherPredicates(new PredicateLogicalAndOperation());
        containment.setType("OBSERVATION");
        containment.setArchetypeId("openEHR-EHR-OBSERVATION.demo_observation.v0");
        assertThat(MatcherUtil.matches(containment, webTemplateNode)).isTrue();

        Containment containmentWithName = new Containment();
        containmentWithName.setOtherPredicates(new PredicateLogicalAndOperation(new PredicateComparisonOperatorDto(
                NAME_VALUE, ConditionComparisonOperatorSymbol.EQ, new SimpleValue("first_observation"))));
        containmentWithName.setType("OBSERVATION");
        containmentWithName.setArchetypeId("openEHR-EHR-OBSERVATION.demo_observation.v0");
        assertThat(MatcherUtil.matches(containmentWithName, webTemplateNode)).isTrue();
    }

    @Test
    void findTypeName() {

        assertThat(MatcherUtil.findTypeName("at0001")).isNull();
        assertThat(MatcherUtil.findTypeName("openEHR-EHR-OBSERVATION.demo_observation.v0"))
                .isEqualTo("OBSERVATION");
        assertThat(MatcherUtil.findTypeName("OBSERVATION")).isEqualTo("OBSERVATION");
    }
}
