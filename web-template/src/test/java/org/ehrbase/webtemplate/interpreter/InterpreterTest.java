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

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.templateprovider.TestDataTemplateProvider;
import org.junit.jupiter.api.Test;

/**
 * @author Stefan Spiska
 */
class InterpreterTest {

    @Test
    void findContainment() {

        String aql =
                "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";

        AqlDto parse = new AqlToDtoParser().parse(aql);

        ContainmentDto composition = (ContainmentDto) parse.getContains();
        ContainmentDto observation = (ContainmentDto) composition.getContains();
        ContainmentDto cluster = (ContainmentDto) observation.getContains();

        Interpreter cut = new Interpreter(new TestDataTemplateProvider());
        Set<Pair<AqlPath.AqlNode[], AqlPath.AqlNode>> expected1 =
                cut.findContainment(composition.getId(), parse.getContains());
        assertThat(expected1.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected1)
                .map(Pair::getValue)
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly("COMPOSITION");

        Set<Pair<AqlPath.AqlNode[], AqlPath.AqlNode>> expected2 =
                cut.findContainment(observation.getId(), parse.getContains());
        assertThat(expected2.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected2)
                .map(Pair::getValue)
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly("openEHR-EHR-OBSERVATION.demo_observation.v0");

        Set<Pair<AqlPath.AqlNode[], AqlPath.AqlNode>> expected3 =
                cut.findContainment(cluster.getId(), parse.getContains());
        assertThat(expected3.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected3)
                .map(Pair::getValue)
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly("openEHR-EHR-CLUSTER.lab_demo.v0");
    }

    @Test
    void interpret() {

        String aql =
                "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";

        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(2);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider());

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto, parse.getContains(), OperationalTemplateTestData.AQL_TEST.getTemplateId());

        System.out.println(interpreterOutputSet);
    }
}
