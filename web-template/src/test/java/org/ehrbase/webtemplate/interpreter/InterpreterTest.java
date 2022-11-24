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
import java.util.Deque;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.groups.Tuple;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplateNode;
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
                "select c/uid/value, o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value, cl/items[at0001]/value/value from ehr e contains COMPOSITION c contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";

        AqlDto parse = new AqlToDtoParser().parse(aql);

        ContainmentDto composition = (ContainmentDto) parse.getContains();
        ContainmentDto observation = (ContainmentDto) composition.getContains();
        ContainmentDto cluster = (ContainmentDto) observation.getContains();

        Interpreter cut = new Interpreter(
                new TestDataTemplateProvider(),
                List.of("COMPOSITION", "OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION"));
        Set<Pair<Containment[], Containment>> expected1 = cut.findContainment(composition.getId(), parse.getContains());
        assertThat(expected1.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(
                        composition.getContainment().getArchetypeId(),
                        observation.getContainment().getArchetypeId(),
                        cluster.getContainment().getArchetypeId());
        assertThat(expected1)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("COMPOSITION");

        Set<Pair<Containment[], Containment>> expected2 = cut.findContainment(observation.getId(), parse.getContains());
        assertThat(expected2.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(
                        composition.getContainment().getArchetypeId(),
                        observation.getContainment().getArchetypeId(),
                        cluster.getContainment().getArchetypeId());
        assertThat(expected2)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("openEHR-EHR-OBSERVATION.demo_observation.v0");

        Set<Pair<Containment[], Containment>> expected3 = cut.findContainment(cluster.getId(), parse.getContains());
        assertThat(expected3.stream().map(Pair::getLeft).flatMap(Arrays::stream).collect(Collectors.toList()))
                .map(Containment::getArchetypeId)
                .containsExactly(
                        composition.getContainment().getArchetypeId(),
                        observation.getContainment().getArchetypeId(),
                        cluster.getContainment().getArchetypeId());
        assertThat(expected3)
                .map(Pair::getValue)
                .map(Containment::getArchetypeId)
                .containsExactly("openEHR-EHR-CLUSTER.lab_demo.v0");
    }

    private enum InterpreterTestCase {
        CASE_CLUSTER(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                        + " cl/items[at0001]/value/value "
                        + "from ehr e "
                        + "contains COMPOSITION c "
                        + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                        + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                2,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                            false),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                            false)
                }),

        CASE_CLUSTER_2(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                        + " cl/items[at0001]"
                        + "from ehr e "
                        + "contains COMPOSITION c "
                        + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                        + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                2,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]",
                            true),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]",
                            true)
                }),

        CASE_CLUSTER_3(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                        + " cl"
                        + " from ehr e "
                        + "contains COMPOSITION c "
                        + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                        + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                2,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]",
                            true),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]",
                            true)
                }),

        CASE_OBSERVATION(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                        + " cl/items[at0001]/value/value "
                        + "from ehr e "
                        + "contains COMPOSITION c "
                        + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                        + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                1,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                            false),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                            false)
                }),

        CASE_OBSERVATION_2(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value"
                        + " from ehr e"
                        + " contains COMPOSITION c"
                        + " contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0]",
                1,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'demo_observation'",
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                            false),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation'",
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                            false),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation'",
                            "/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                            false)
                }),

        CASE_OBSERVATION_3(
                "select c/uid/value," + " o"
                        + " from ehr e"
                        + " contains COMPOSITION c"
                        + " contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0]",
                1,
                new Tuple[] {
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'demo_observation'",
                            "",
                            true),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation'",
                            "",
                            true),
                    new Tuple(
                            1,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation'",
                            "",
                            true)
                }),

        CASE_COMPOSITION(
                "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                        + " cl/items[at0001]/value/value "
                        + "from ehr e "
                        + "contains COMPOSITION c "
                        + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                        + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]",
                0,
                new Tuple[] {
                    new Tuple(
                            0,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/uid/value",
                            false),
                    new Tuple(
                            0,
                            "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                            "/uid/value",
                            false)
                }),

        CASE_COMPOSITION_2("select c/uid/value " + "from ehr e " + "contains COMPOSITION c ", 0, new Tuple[] {
            new Tuple(0, "openEHR-EHR-COMPOSITION.report.v1", "/uid/value", false),
        });
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

        Interpreter cut = new Interpreter(
                new TestDataTemplateProvider(),
                List.of("COMPOSITION", "OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertOutput(interpreterOutputSet, test.expected);
    }

    @Test
    void interpretToComposition() {

        String aql = "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                + " cl/items[at0001]/value/value "
                + "from ehr e "
                + "contains COMPOSITION c "
                + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(2);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider(), List.of("COMPOSITION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertOutput(interpreterOutputSet, new Tuple[] {
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                    "/content[openEHR-EHR-SECTION.adhoc.v1,'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation']/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                    false),
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                    "/content[openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation']/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                    false)
        });
    }

    @Test
    void interpretOriginalContain() {

        String aql = "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                + " cl/items[at0001]/value/value "
                + "from ehr e "
                + "contains COMPOSITION c "
                + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(2);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider(), List.of("COMPOSITION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertThat(interpreterOutputSet)
                .map(InterpreterOutput::getOriginalContain)
                .map(l -> l.stream()
                        .map(c -> {
                            StringBuilder sb = new StringBuilder();
                            PredicateHelper.format(sb, c.getOtherPredicates(), AqlPath.OtherPredicatesFormat.SHORTED);
                            return c.getType() + "[" + sb + "]";
                        })
                        .collect(Collectors.joining(";")))
                .containsExactly(
                        "COMPOSITION[];OBSERVATION[openEHR-EHR-OBSERVATION.demo_observation.v0];CLUSTER[openEHR-EHR-CLUSTER.lab_demo.v0]",
                        "COMPOSITION[];OBSERVATION[openEHR-EHR-OBSERVATION.demo_observation.v0];CLUSTER[openEHR-EHR-CLUSTER.lab_demo.v0]");
    }

    @Test
    void interpretDepth() {

        String aql =
                "SELECT e/ehr_id/value, c/uid/value as composition_id, c/context/start_time/value as start_time, o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude as body_temperature"
                        + "  FROM EHR e "
                        + "    CONTAINS COMPOSITION c "
                        + "    CONTAINS OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                        + " WHERE e/ehr_id/value = '96940df3-c979-4d96-9a51-34b6c5222777' "
                        + "    AND o/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value/magnitude > 37.5"
                        + " LIMIT 10 OFFSET 10"
                        + " ORDER BY c/context/start_time/value DESC";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(3);

        Interpreter cut = new Interpreter(
                new TestDataTemplateProvider(),
                List.of("COMPOSITION", "OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION", "ADMIN_ENTRY"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.CORONA_ANAMNESE.getTemplateId());

        assertThat(interpreterOutputSet)
                .map(InterpreterOutput::getPathFromRootToValue)
                .map(InterpreterPath::extractDepth)
                .containsExactly(2L);
    }

    @Test
    void interpretWhere() {

        String aql = "select c/uid/value," + " o/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value,"
                + " cl/items[at0001]/value/value "
                + "from ehr e "
                + "contains COMPOSITION c "
                + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]"
                + "where cl/items[at0001]/value/value = 'abc' ";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) ((ConditionComparisonOperatorDto) parse.getWhere()).getStatement();

        Interpreter cut = new Interpreter(new TestDataTemplateProvider(), List.of("COMPOSITION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertOutput(interpreterOutputSet, new Tuple[] {
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                    "/content[openEHR-EHR-SECTION.adhoc.v1,'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation']/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                    false),
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1;openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation';openEHR-EHR-CLUSTER.lab_demo.v0",
                    "/content[openEHR-EHR-OBSERVATION.demo_observation.v0,'root_observation']/data[at0001]/events[at0002]/data[at0003]/items[openEHR-EHR-CLUSTER.lab_demo.v0]/items[at0001]/value/value",
                    false)
        });
    }

    @Test
    void interpretToCompositionWithOtherPredicate() {

        String aql = "select c/uid/value,"
                + " o/data[at0001]/events[at0002 and time/value < '2020-05-11T22:53:12.039139+02:00']/data[at0003]/items[at0004]/value/value,"
                + " cl/items[at0001]/value/value "
                + "from ehr e "
                + "contains COMPOSITION c "
                + "contains OBSERVATION o[openEHR-EHR-OBSERVATION.demo_observation.v0] "
                + "contains CLUSTER cl [openEHR-EHR-CLUSTER.lab_demo.v0]";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(1);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider(), List.of("COMPOSITION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertThat(interpreterOutputSet)
                .map(o -> o.getPathFromRootToValue().getNodeList().stream()
                        .filter(n -> n.getNormalisedNode().getName().equals("events"))
                        .map(n -> PredicateHelper.format(n.getOtherPredicate(), AqlPath.OtherPredicatesFormat.SHORTED))
                        .collect(Collectors.joining()))
                .containsExactly(
                        "time/value<'2020-05-11T22:53:12.039139+02:00'",
                        "time/value<'2020-05-11T22:53:12.039139+02:00'");
    }

    @Test
    void interpretToCompositionWithNotUniquePath() {

        String aql =
                "select c/content[openEHR-EHR-SECTION.adhoc.v1,'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0]/data[at0001]/events[at0002]/data[at0003]/items/value/value "
                        + "from ehr e "
                        + "contains COMPOSITION c";
        AqlDto parse = new AqlToDtoParser().parse(aql);

        SelectFieldDto clusterSelectStatementDto =
                (SelectFieldDto) parse.getSelect().getStatement().get(0);

        Interpreter cut = new Interpreter(new TestDataTemplateProvider(), List.of("COMPOSITION"));

        Set<InterpreterOutput> interpreterOutputSet = cut.interpret(
                clusterSelectStatementDto,
                parse.getContains(),
                OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId());

        assertOutput(interpreterOutputSet, new Tuple[] {
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1",
                    "/content[openEHR-EHR-SECTION.adhoc.v1,'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'demo_observation']/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                    false),
            new Tuple(
                    0,
                    "openEHR-EHR-COMPOSITION.report.v1",
                    "/content[openEHR-EHR-SECTION.adhoc.v1,'cause']/items[openEHR-EHR-OBSERVATION.demo_observation.v0,'first_observation']/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/value",
                    false)
        });
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
                        o -> o.getPathFromRootToValue()
                                .buildNormalisedAqlDto()
                                .format(AqlPath.OtherPredicatesFormat.SHORTED, true),
                        InterpreterOutput::isRepresentingObject)
                .containsExactly(tuples);
    }

    @Test
    void resolve() {

        WebTemplateNode root = new TestDataTemplateProvider()
                .buildIntrospect(OperationalTemplateTestData.REPEATING_ARCHETYPES.getTemplateId())
                .get()
                .getTree();

        Containment compositionContainment = new Containment("COMPOSITION", null, new PredicateLogicalAndOperation());
        Containment observationContainment = new Containment(
                "OBSERVATION", "openEHR-EHR-OBSERVATION.demo_observation.v0", new PredicateLogicalAndOperation());
        Containment clusterContainment =
                new Containment("CLUSTER", "openEHR-EHR-CLUSTER.lab_demo.v0", new PredicateLogicalAndOperation());

        Set<List<Pair<WebTemplateNode, Deque<WebTemplateNode>>>> actual =
                Interpreter.resolve(List.of(compositionContainment, observationContainment, clusterContainment), root);

        assertThat(actual)
                .map(s -> s.stream()
                        .map(p -> p.getLeft().getNodeId() + ":"
                                + p.getRight().stream()
                                        .map(WebTemplateNode::getId)
                                        .collect(Collectors.joining("->")))
                        .collect(Collectors.joining(";")))
                .containsExactly(
                        "openEHR-EHR-COMPOSITION.report.v1:aql_demo.hip.de.v0;"
                                + "openEHR-EHR-OBSERVATION.demo_observation.v0:cause->first_observation;"
                                + "openEHR-EHR-CLUSTER.lab_demo.v0:history->any_event->tree->lab_demo",
                        "openEHR-EHR-COMPOSITION.report.v1:aql_demo.hip.de.v0;"
                                + "openEHR-EHR-OBSERVATION.demo_observation.v0:root_observation;"
                                + "openEHR-EHR-CLUSTER.lab_demo.v0:history->any_event->tree->lab_demo");
    }
}
