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
import org.assertj.core.groups.Tuple;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.templateprovider.TestDataTemplateProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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
        Set<Pair<Containment[], Containment>> expected1 = cut.findContainment(composition.getId(), parse.getContains());
        assertThat(expected1.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected1)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("COMPOSITION");

        Set<Pair<Containment[], Containment>> expected2 = cut.findContainment(observation.getId(), parse.getContains());
        assertThat(expected2.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected2)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("openEHR-EHR-OBSERVATION.demo_observation.v0");

        Set<Pair<Containment[], Containment>> expected3 = cut.findContainment(cluster.getId(), parse.getContains());
        assertThat(expected3.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(composition.getArchetypeId(), observation.getArchetypeId(), cluster.getArchetypeId());
        assertThat(expected3)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("openEHR-EHR-CLUSTER.lab_demo.v0");
    }

    private enum InterpreterTestCase {
        CASE_1(
                "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                2,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value"),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value")
                }),
    /*
          CASE_2(
              "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
              1,
              new Tuple[] {
                  new Tuple(
                      1,
                      "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value"),
                  new Tuple(
                      1,
                      "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value")
              }),

          CASE_3(
              "select c/uid/value, o/data[at0001]/events[at0002.1]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
              0,
              new Tuple[] {
                  new Tuple(
                      1,
                      "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value"),
                  new Tuple(
                      1,
                      "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                      "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value")
              })

    */
    ;
        final String aql;
        final int selectField;
        final Tuple[] expected;

        InterpreterTestCase(String aql, int selectField, Tuple[] expected) {
            this.aql = aql;
            this.selectField = selectField;
            this.expected = expected;
        }
    }

    @ParameterizedTest
    @EnumSource(InterpreterTestCase.class)
    void interpret(InterpreterTestCase test) {

        String aql = test.aql;
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(test.selectField);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider());

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto, parse.getContains(), OperationalTemplateTestData.AQL_TEST.getTemplateId());

        assertOutput(interpreterOutputSet, test.expected);
    }

    private static void assertOutput(Set<InterpreterOutput> interpreterOutputSet, Tuple... tuples) {
        assertThat(interpreterOutputSet)
                .map(
                        InterpreterOutput::getRootContainment,
                        interpreterOutput -> interpreterOutput.getContain().stream()
                                .map(c -> {
                                    StringBuilder sb = new StringBuilder();
                                    PredicateHelper.format(
                                            sb, c.getOtherPredicates(), AqlPath.OtherPredicatesFormat.SHORTED);
                                    return sb.toString();
                                })
                                .collect(Collectors.joining(";")),
                        o -> o.getPathFromRootToValue().buildNormalisedAql())
                .contains(tuples);
    }
}
