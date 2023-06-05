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
package org.ehrbase.aql.parser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.aql.dto.operand.StringPrimitive;
import org.ehrbase.aql.dto.path.predicate.AqlPredicate;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperator;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.render.AqlRender;
import org.junit.jupiter.api.Test;

class AqlToDtoParserTest {

    @Test
    void parse() {
        String aql =
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseEhrPredicate() {
        String aql =
                "Select c/name/value, d/ehr_id/value as ehr_id from EHR d[some_key='some_value'] contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseCompositionPredicate() {
        String aql =
                "Select c/name/value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[some_key='some_value']";

        testAql(aql, aql);
    }

    @Test
    void parseFromComposition() {
        String aql = "Select e/name/value as name from COMPOSITION e";
        String expected = aql; // .replace(" from ", " from EHR e contains ");

        testAql(aql, expected);
    }

    @Test
    void parseDoubleAlias() {
        String aql =
                "Select e/ehr_id/value, c0 as F1 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    void parseEhrAliasSwap() {
        String aql = "Select c/name/value as e from EHR[ehr_id/value!='anything'] contains COMPOSITION c";

        testAql(aql, aql);
    }

    @Test
    void parsePlainEhr() {
        String aql = "Select c/name/value from EHR CONTAINS COMPOSITION c";

        testAql(aql, aql);
    }

    @Test
    void parseDefaultEhrAliasCollision() {
        String aql = "Select e/name/value as F1 from EHR[ehr_id/value!='anything'] contains COMPOSITION e";

        testAql(aql, aql);
    }

    @Test
    void parseDoubleAlias2() {
        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

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
                "Select e/ehr_id/value as F1, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0005]/value/value as F2, o/data[at0001]/events[at0002]/data[at0003]/items[at0022]/items[at0004]/value/value as F3 from EHR e contains (COMPOSITION c0 and SECTION s4[openEHR-EHR-SECTION.adhoc.v1] contains OBSERVATION o[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) where (e/ehr_id/value matches {'47dc21a2-7076-4a57-89dc-bd83729ed52f'} and c0/archetype_details/template_id/value matches {'Corona_Anamnese'})";

        testAql(aql, aql);
    }

    @Test
    void parseMultiWhere() {
        String aql =
                "Select c0 as openEHR_EHR_COMPOSITION_self_monitoring_v0, c1 as openEHR_EHR_COMPOSITION_report_v1 from EHR e contains (COMPOSITION c0[openEHR-EHR-COMPOSITION.self_monitoring.v0] and COMPOSITION c1[openEHR-EHR-COMPOSITION.report.v1]) where (e/ehr_id/value matches {'b3a40b41-36e1-4802-8748-062d4000aaae'} and c0/archetype_details/template_id/value matches {'Corona_Anamnese'} and c1/archetype_details/template_id/value matches {'Corona_Anamnese'})";

        testAql(aql, aql);
    }

    @Test
    void parseMultiMixed() {
        String aql =
                "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] where (e/ehr_id/value = $ehrid or (e/ehr_id/value = $ehrid2 and e/ehr_id/value = $ehrid3))";

        testAql(aql, aql);
    }

    @Test
    void parseMatches() {
        String aql =
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] where d/ehr_id/value matches {'f4da8646-8e36-4d9d-869c-af9dce5935c7', '61861e76-1606-48c9-adcf-49ebbb2c6bbd'}";

        testAql(aql, aql);
    }

    /*
    @Test
    void addMatches() {
        String aql =
                "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(aql);

        WhereCondition contains = actual.getWhere();

        MatchesCondition matchesOperatorDto = new MatchesCondition();

        SelectFieldDto selectFieldDto = new SelectFieldDto();
        selectFieldDto.setAqlPath("/ehr_id/value");
        selectFieldDto.setContainmentId(actual.getEhr().getContainmentId());
        matchesOperatorDto.setStatement(selectFieldDto);
        matchesOperatorDto.setValues(
                Stream.of("f4da8646-8e36-4d9d-869c-af9dce5935c7", "61861e76-1606-48c9-adcf-49ebbb2c6bbd")
                        .map(SimpleValue::new)
                        .collect(Collectors.toList()));

        LogicalOperatorCondition and = new LogicalOperatorCondition();

        and.setSymbol(ConditionLogicalOperatorSymbol.AND);
        and.setValues(new ArrayList<>());
        and.getValues().add(contains);
        and.getValues().add(matchesOperatorDto);

        actual.setWhere(and);

        String actualAql = new AqlRender(actual).render();

        assertThat(actualAql)
                .isEqualTo(
                        "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1 and e/ehr_id/value matches {'f4da8646-8e36-4d9d-869c-af9dce5935c7','61861e76-1606-48c9-adcf-49ebbb2c6bbd'})");
    }
     */

    @Test
    void parseWithoutContains() {
        String aql = "SELECT e/ehr_id/value FROM EHR e";

        testAql(aql, aql);
    }

    @Test
    void parseLimitOffset() {
        String aql =
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] LIMIT 5 OFFSET 1";

        testAql(aql, aql);
    }

    @Test
    void parseError() {
        String aql =
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id  EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        AqlToDtoParser cut = new AqlToDtoParser();
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
                "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        testAql(aql, aql);
    }

    @Test
    void parseOrderBy() {

        String aqlTwoOrderBy =
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASCENDING, e/ehr_id/value DESCENDING";
        String aqlShortenedSymbols =
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC, e/ehr_id/value DESC";

        testAql(aqlTwoOrderBy, aqlShortenedSymbols);

        testAql(aqlShortenedSymbols, aqlShortenedSymbols);

        var sortDefault1 =
                "select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude, e/ehr_id/value";
        testAql(
                sortDefault1,
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC, e/ehr_id/value ASC");

        var sortDefault2 =
                "select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESC, e/ehr_id/value";
        testAql(
                sortDefault2,
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESC, e/ehr_id/value ASC");
    }

    void testAql(String aql, String expected) {
        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(aql);

        assertThat(actual).isNotNull();

        String actualAql = new AqlRender(actual).render();

        assertThat(actualAql.toUpperCase()).isEqualTo(expected.toUpperCase());

        String roundtripAql = new AqlRender(cut.parse(expected)).render();

        assertThat(roundtripAql.toUpperCase()).isEqualTo(expected.toUpperCase());
    }

    @Test
    void parseContains() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]";

        testContains(aql, "openEHR-EHR-COMPOSITION.report.v1 --> openEHR-EHR-OBSERVATION.sample_blood_pressure.v1");
    }

    @Test
    void parseContainsLogical() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] and OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] or OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 AND openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) OR openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    @Test
    void parseContainsLogical2() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> (openEHR-EHR-OBSERVATION.story.v1 OR (openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 AND openEHR-EHR-OBSERVATION.exposure_assessment.v0))");
    }

    @Test
    void parseContainsLogical3() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains ((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    void testContains(String aql, String s) {
        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(aql);

        assertThat(actual).isNotNull();
        assertThat(actual.getFrom()).isNotNull();

        assertThat(render(actual.getFrom())).isEqualTo(s);
    }

    @Test
    void parseContainsLogical4() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] contains CLUSTER) or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 --> CLUSTER OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    String render(Containment containmentExpresion) {
        StringBuilder sb = new StringBuilder();

        if (containmentExpresion instanceof ContainmentClassExpression classExpressionDto) {

            if (classExpressionDto.getType().equals("EHR")) {
                sb.append(render(classExpressionDto.getContains()));
            } else {
                AqlPredicate otherPredicates = ((ContainmentClassExpression) containmentExpresion).getPredicates();
                sb.append(PredicateHelper.find(otherPredicates, PredicateHelper.ARCHETYPE_NODE_ID)
                        .map(PredicateComparisonOperator::getValue)
                        .map(StringPrimitive.class::cast)
                        .map(StringPrimitive::getValue)
                        .orElse(classExpressionDto.getType()));
                Containment contains = ((AbstractContainmentExpression) containmentExpresion).getContains();
                if (contains != null) {
                    sb.append(" --> ").append(render(contains));
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
        String aql = "Select c0 as openEHR_EHR_COMPOSITION_self_monitoring_v0, c1 as openEHR_EHR_COMPOSITION_report_v1 "
                + "from EHR e contains (COMPOSITION c0[openEHR-EHR-COMPOSITION.self_monitoring.v0] and COMPOSITION c1[openEHR-EHR-COMPOSITION.report.v1]) "
                + "where (e/ehr_id/value matches {'b3a40b41-36e1-4802-8748-062d4000aaae'} "
                + "and c1/archetype_details/template_id/value like '%test%' "
                + "and c1/archetype_details/archetype_id/value like $archetype)";

        testAql(aql, aql);
    }
}
