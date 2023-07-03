/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.openehr.sdk.aql.dto.operand.AggregateFunction.AggregateFunctionName;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.predicate.PredicateHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

class AqlQueryParserTest {

    @Test
    void parse() {
        String aql =
                "SELECT c/context/other_context[at0001]/items[at0002]/value/value AS Bericht_ID__value, d/ehr_id/value AS ehr_id FROM EHR d CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseEhrPredicate() {
        String aql =
                "SELECT c/name/value, d/ehr_id/value AS ehr_id FROM EHR d[some_key='some_value'] CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseCompositionPredicate() {
        String aql =
                "SELECT c/name/value, d/ehr_id/value AS ehr_id FROM EHR d CONTAINS COMPOSITION c[some_key='some_value']";

        testAql(aql, aql);
    }

    @Test
    void parseFromComposition() {
        String aql = "SELECT e/name/value AS name FROM COMPOSITION e";
        String expected = aql; // .replace(" FROM ", " FROM EHR e CONTAINS ");

        testAql(aql, expected);
    }

    @Test
    void parseDoubleAlias() {
        String aql =
                "SELECT e/ehr_id/value, c0 AS F1 FROM EHR e CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseEhrAliasSwap() {
        String aql = "SELECT c/name/value AS e FROM EHR[ehr_id/value!='anything'] CONTAINS COMPOSITION c";

        testAql(aql, aql);
    }

    @Test
    void parsePlainEhr() {
        String aql = "SELECT c/name/value FROM EHR CONTAINS COMPOSITION c";

        testAql(aql, aql);
    }

    @Test
    void parseDefaultEhrAliasCollision() {
        String aql = "SELECT e/name/value AS F1 FROM EHR[ehr_id/value!='anything'] CONTAINS COMPOSITION e";

        testAql(aql, aql);
    }

    @Test
    void parseDoubleAlias2() {
        String aql =
                "SELECT c0 AS F1, e/ehr_id/value FROM EHR e CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseObservation() {
        String aql = "SELECT o FROM EHR e CONTAINS OBSERVATION o";

        testAql(aql, aql);
    }

    @Test
    void parseObservation2() {
        String aql =
                "SELECT e/ehr_id/value AS F1, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value AS F2, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value AS F3 FROM EHR e CONTAINS (COMPOSITION c0 and SECTION s4[openEHR-EHR-SECTION.adhoc.v1] CONTAINS OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) WHERE (e/ehr_id/value MATCHES {'47dc21a2-7076-4a57-89dc-bd83729ed52f'} and c0/archetype_details/template_id/value MATCHES {'Corona_Anamnese'})";

        testAql(aql, aql);
    }

    @Test
    void parseMultiWhere() {
        String aql =
                "SELECT c0 AS openEHR_EHR_COMPOSITION_self_monitoring_v0, c1 AS openEHR_EHR_COMPOSITION_report_v1 FROM EHR e CONTAINS (COMPOSITION c0[openEHR-EHR-COMPOSITION.self_monitoring.v0] and COMPOSITION c1[openEHR-EHR-COMPOSITION.report.v1]) WHERE (e/ehr_id/value MATCHES {'b3a40b41-36e1-4802-8748-062d4000aaae'} and c0/archetype_details/template_id/value MATCHES {'Corona_Anamnese'} and c1/archetype_details/template_id/value MATCHES {'Corona_Anamnese'})";

        testAql(aql, aql);
    }

    @Test
    void parseMultiMixed() {
        String aql =
                "SELECT c0 AS F1, e/ehr_id/value AS F2 FROM EHR e CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] WHERE (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

        testAql(aql, aql);
    }

    @Test
    void parseMatches() {
        String aql =
                "SELECT c/context/other_context[at0001]/items[at0002]/value/value AS Bericht_ID__value, d/ehr_id/value AS ehr_id FROM EHR d CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] WHERE d/ehr_id/value MATCHES {'f4da8646-8e36-4d9d-869c-af9dce5935c7', '61861e76-1606-48c9-adcf-49ebbb2c6bbd'}";

        testAql(aql, aql);
    }

    @Test
    void parseWithoutContains() {
        String aql = "SELECT e/ehr_id/value FROM EHR e";

        testAql(aql, aql);
    }

    @Test
    void parseLimitOffset() {
        String aql =
                "SELECT c/context/other_context[at0001]/items[at0002]/value/value AS Bericht_ID__value, d/ehr_id/value AS ehr_id FROM EHR d CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] LIMIT 5 OFFSET 1";

        testAql(aql, aql);
    }

    @Test
    void parseError() {
        String aql =
                "SELECT c/context/other_context[at0001]/items[at0002]/value/value AS Bericht_ID__value, d/ehr_id/value AS ehr_id  EHR d CONTAINS COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        AqlQueryParser cut = new AqlQueryParser();
        try {
            cut.parse(aql);
            fail("Expected AqlParseException");
        } catch (AqlParseException e) {
            assertThat(e.getMessage()).isEqualTo("Parse exception: line 1: char 113 missing FROM at 'EHR'");
        }
    }

    @Test
    void parseWhere() {
        String aql =
                "SELECT o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude AS Systolic__magnitude, e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] WHERE (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        testAql(aql, aql);
    }

    @Test
    void parseOrderBy() {

        String aqlTwoOrderBy =
                "SELECT e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASCENDING, e/ehr_id/value DESCENDING";
        String aqlShortenedSymbols =
                "SELECT e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC, e/ehr_id/value DESC";

        testAql(aqlTwoOrderBy, aqlShortenedSymbols);

        testAql(aqlShortenedSymbols, aqlShortenedSymbols);

        var sortDefault1 =
                "select e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude, e/ehr_id/value";
        testAql(
                sortDefault1,
                "SELECT e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC, e/ehr_id/value ASC");

        var sortDefault2 =
                "select e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESC, e/ehr_id/value";
        testAql(
                sortDefault2,
                "SELECT e/ehr_id/value AS ehr_id FROM EHR e CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESC, e/ehr_id/value ASC");
    }

    void testAql(String aql, String expected) {
        AqlQueryParser cut = new AqlQueryParser();
        AqlQuery actual = cut.parse(aql);

        assertThat(actual).isNotNull();

        String actualAql = AqlRenderer.render(actual);

        assertThat(actualAql).isEqualToIgnoringCase(expected);

        String roundtripAql = AqlRenderer.render(cut.parse(expected));

        assertThat(roundtripAql).isEqualToIgnoringCase(expected);
    }

    @Test
    void parseContains() {
        String aql =
                "SELECT c0/context/other_context[at0001]/items[at0002]/value/value AS Bericht_ID__value FROM EHR e  CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] CONTAINS OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]";

        testContains(aql, "openEHR-EHR-COMPOSITION.report.v1 --> openEHR-EHR-OBSERVATION.sample_blood_pressure.v1");
    }

    @Test
    void parseContainsLogical() {
        String aql =
                "SELECT c0/context/other_context[at0001]/items[at0002]/value/value AS Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value AS Kommentar__value FROM EHR e  CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] CONTAINS (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] and OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] or OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 AND openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) OR openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    @Test
    void parseContainsLogical2() {
        String aql =
                "SELECT c0/context/other_context[at0001]/items[at0002]/value/value AS Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value AS Kommentar__value FROM EHR e  CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] CONTAINS (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> (openEHR-EHR-OBSERVATION.story.v1 OR (openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 AND openEHR-EHR-OBSERVATION.exposure_assessment.v0))");
    }

    @Test
    void parseContainsLogical3() {
        String aql =
                "SELECT c0/context/other_context[at0001]/items[at0002]/value/value AS Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value AS Kommentar__value FROM EHR e  CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] CONTAINS ((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    void testContains(String aql, String s) {
        AqlQueryParser cut = new AqlQueryParser();
        AqlQuery actual = cut.parse(aql);

        assertThat(actual).isNotNull();
        assertThat(actual.getFrom()).isNotNull();

        assertThat(render(actual.getFrom())).isEqualTo(s);
    }

    @Test
    void parseContainsLogical4() {
        String aql =
                "SELECT c0/context/other_context[at0001]/items[at0002]/value/value AS Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value AS Kommentar__value FROM EHR e  CONTAINS COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] CONTAINS (((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] CONTAINS CLUSTER) or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 --> CLUSTER OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }



    private static Stream<ComparisonOperatorPredicate> streamPredicates(List<AndOperatorPredicate> condition) {
        if (condition == null) {
            return Stream.empty();
        }
        return condition.stream().map(AndOperatorPredicate::getOperands).flatMap(List::stream);
    }

    String render(Containment containmentExpresion) {
        StringBuilder sb = new StringBuilder();

        if (containmentExpresion instanceof ContainmentClassExpression classExpressionDto) {

            if (classExpressionDto.getType().equals("EHR")) {
                sb.append(render(classExpressionDto.getContains()));
            } else {
                List<AndOperatorPredicate> otherPredicates = ((ContainmentClassExpression) containmentExpresion).getPredicates();
                sb.append(streamPredicates(otherPredicates).filter(p -> p.getPath().getPathParts().size()==1)
                        .filter(p -> PredicateHelper.ARCHETYPE_NODE_ID.equals(p.getPath().getPathParts().get(0).getAttribute()))
                        .map(ComparisonOperatorPredicate::getValue)
                        .map(StringPrimitive.class::cast)
                        .map(StringPrimitive::getValue)
                        .findFirst()
                        .orElse(classExpressionDto.getType()));
                Containment CONTAINS = ((AbstractContainmentExpression) containmentExpresion).getContains();
                if (CONTAINS != null) {
                    sb.append(" --> ").append(render(CONTAINS));
                }
            }
        } else if (containmentExpresion instanceof ContainmentSetOperator) {
            sb.append("(")
                    .append(((ContainmentSetOperator) containmentExpresion)
                            .getValues().stream()
                                    .map(this::render)
                                    .collect(Collectors.joining((StringUtils.wrap(
                                            ((ContainmentSetOperator) containmentExpresion)
                                                    .getSymbol()
                                                    .toString(),
                                            " ")))))
                    .append(")");
            return sb.toString();
        }

        return sb.toString();
    }

    @Test
    void parseLike() {
        String aql = "SELECT c0 AS openEHR_EHR_COMPOSITION_self_monitoring_v0, c1 AS openEHR_EHR_COMPOSITION_report_v1 "
                + "FROM EHR e CONTAINS (COMPOSITION c0[openEHR-EHR-COMPOSITION.self_monitoring.v0] AND COMPOSITION c1[openEHR-EHR-COMPOSITION.report.v1]) "
                + "WHERE (e/ehr_id/value MATCHES {'b3a40b41-36e1-4802-8748-062d4000aaae'} "
                + "AND c1/archetype_details/template_id/value LIKE '%test%' "
                + "AND c1/archetype_details/archetype_id/value LIKE $archetype)";

        testAql(aql, aql);
    }

    @ParameterizedTest
    @EnumSource(AggregateFunctionName.class)
    void testAggregateFunctions(AggregateFunctionName name) {
        String aql = "SELECT " + name.name() + "(d/ehr_id/value) FROM EHR d";
        testAql(aql, aql);
    }

    @Test
    void testCountDistinct() {
        String aql = "SELECT COUNT(DISTINCT d/ehr_id/value) FROM EHR d";
        testAql(aql, aql);
    }

    @Test
    void testCountAsterisk() {
        String aql = "SELECT COUNT(*) FROM EHR d";
        testAql(aql, aql);
    }

    @ParameterizedTest
    @ValueSource(
            strings = {
                "1",
                "-1",
                "1.1",
                "-1.1",
                "1e5",
                "-1e5",
                "1e-5",
                "-1e-5",
                "1.1e5",
                "-1.1e5",
                "1.1e-5",
                "-1.1e-5",
                "9007199254740992001.0"
            })
    void testSelectNumericPrimitive(String number) {
        String aql = "SELECT " + number + " FROM EHR";
        testAql(aql, aql);
    }

    @Test
    void parseStringLiterals() {
        String aql =
                "SELECT '\\\\☮\\'\\\"', '\\u20AC120 \\1001o1 \\53230€', d/ehr_id/value AS ehr_id FROM EHR d[some_key='𝄞']";

        testAql(aql, "SELECT '\\\\☮\\'\"', '€120 @1o1 +230€', d/ehr_id/value AS ehr_id FROM EHR d[some_key='𝄞']");
    }
}
