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
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.path.AqlPath;
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
        assertThat(cut.findContainment(composition.getId(), parse.getContains()).stream()
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId());

        assertThat(cut.findContainment(observation.getId(), parse.getContains()).stream()
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId());

        assertThat(cut.findContainment(cluster.getId(), parse.getContains()).stream()
                        .flatMap(Arrays::stream)
                        .collect(Collectors.toList()))
                .map(AqlPath.AqlNode::getAtCode)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
    }

    @Test
    void resolve() {

        String aql =
                "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";

        AqlDto parse = new AqlToDtoParser().parse(aql);

        ContainmentDto composition = (ContainmentDto) parse.getContains();
        ContainmentDto observation = (ContainmentDto) composition.getContains();
        ContainmentDto cluster = (ContainmentDto) observation.getContains();

        Interpreter cut = new Interpreter(new TestDataTemplateProvider());

        assertThat(cut.resolve(
                        cut.findContainment(composition.getId(), composition),
                        OperationalTemplateTestData.AQL_TEST.getTemplateId()))
                .map(a -> Arrays.stream(a)
                        .map(n -> n.getNodeId() + ":" + n.getName())
                        .collect(Collectors.joining(",")))
                .containsExactly("openEHR-EHR-COMPOSITION.report.v1:aql_demo.hip.de.v0");

        assertThat(cut.resolve(
                        cut.findContainment(observation.getId(), composition),
                        OperationalTemplateTestData.AQL_TEST.getTemplateId()))
                .map(a -> Arrays.stream(a)
                        .map(n -> n.getNodeId() + ":" + n.getName())
                        .collect(Collectors.joining(",")))
                .containsExactly(
                        "openEHR-EHR-OBSERVATION.demo_observation.v0:demo_observation",
                        "openEHR-EHR-OBSERVATION.demo_observation.v0:first_observation",
                        "openEHR-EHR-OBSERVATION.demo_observation.v0:root_observation");

        assertThat(cut.resolve(
                        cut.findContainment(cluster.getId(), composition),
                        OperationalTemplateTestData.AQL_TEST.getTemplateId()))
                .map(a -> Arrays.stream(a)
                        .map(n -> n.getNodeId() + ":" + n.getName())
                        .collect(Collectors.joining(",")))
                .containsExactly(
                        "openEHR-EHR-OBSERVATION.demo_observation.v0:first_observation,openEHR-EHR-CLUSTER.lab_demo.v0:lab_demo",
                        "openEHR-EHR-OBSERVATION.demo_observation.v0:root_observation,openEHR-EHR-CLUSTER.lab_demo.v0:lab_demo");
    }
}
