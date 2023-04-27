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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.binder.AqlBinder;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.MatchesOperatorDto;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class AqlToDtoParserTest {

    @Test
    void parse() {
        String aql =
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    @Disabled
    void parseEhrPredicate() {
        String aql =
                "Select c/name/value, d/ehr_id/value as ehr_id from EHR d[some_key='some_value'] contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(aql, aql);
    }

    @Test
    @Disabled
    void parseFromComposition() {
        String aql = "Select e/name/value as name from COMPOSITION e";
        String expected = aql; // .replace(" from ", " from EHR e contains ");

        testAql(aql, expected);
    }

    @Test
    void parseDoubleAlias() {
        String aql =
                "Select e/ehr_id/value ,c0 as F1 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(
                aql,
                "Select e/ehr_id/value as F1, c0 as F1_F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    @Disabled
    void parseEhrAliasSwap() {
        String aql = "Select c/name/value as e from EHR[ehr_id/value != 'anything'] contains COMPOSITION c";
        String expected =
                "Select c/name/value as e from EHR e1 contains COMPOSITION c where e1/ehr_id/value != 'anything'";

        testAql(aql, expected);
    }

    @Test
    void parsePlainEhr() {
        String aql = "Select c/name/value from EHR CONTAINS COMPOSITION c";

        testAql(aql, "Select c/name/value as F1 from EHR e contains COMPOSITION c");
    }

    @Test
    void parseDefaultEhrAliasCollision() {
        String aql = "Select e/name/value as F1 from EHR[ehr_id/value != 'anything'] contains COMPOSITION e";

        String expected =
                "Select e/name/value as F1 from EHR e1 contains COMPOSITION e where e1/ehr_id/value != 'anything'";

        testAql(aql, expected);
    }

    @Test
    void parseDoubleAlias2() {
        String aql =
                "Select c0 as F1, e/ehr_id/value from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

        testAql(
                aql,
                "Select c0 as F1, e/ehr_id/value as F2 from EHR e contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]");
    }

    @Test
    void parseObservation() {
        String aql = "SELECT o FROM EHR e CONTAINS OBSERVATION o";

        testAql(aql, "Select o as F1 from EHR e contains OBSERVATION o");
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
                "Select c/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value, d/ehr_id/value as ehr_id from EHR d contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] where d/ehr_id/value matches {'f4da8646-8e36-4d9d-869c-af9dce5935c7','61861e76-1606-48c9-adcf-49ebbb2c6bbd'}";

        testAql(aql, aql);
    }

    @Test
    void addMatches() {
        String aql =
                "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(aql);

        ConditionDto contains = actual.getWhere();

        MatchesOperatorDto matchesOperatorDto = new MatchesOperatorDto();

        SelectFieldDto selectFieldDto = new SelectFieldDto();
        selectFieldDto.setAqlPath("/ehr_id/value");
        selectFieldDto.setContainmentId(actual.getEhr().getContainmentId());
        matchesOperatorDto.setStatement(selectFieldDto);
        matchesOperatorDto.setValues(
                Stream.of("f4da8646-8e36-4d9d-869c-af9dce5935c7", "61861e76-1606-48c9-adcf-49ebbb2c6bbd")
                        .map(SimpleValue::new)
                        .collect(Collectors.toList()));

        ConditionLogicalOperatorDto and = new ConditionLogicalOperatorDto();

        and.setSymbol(ConditionLogicalOperatorSymbol.AND);
        and.setValues(new ArrayList<>());
        and.getValues().add(contains);
        and.getValues().add(matchesOperatorDto);

        actual.setWhere(and);

        String actualAql = new AqlBinder().bind(actual).getLeft().buildAql();

        assertThat(actualAql)
                .isEqualTo(
                        "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1 and e/ehr_id/value matches {'f4da8646-8e36-4d9d-869c-af9dce5935c7','61861e76-1606-48c9-adcf-49ebbb2c6bbd'})");
    }

    @Test
    void parseWithoutContains() {
        String aql = "SELECT e/ehr_id/value FROM EHR e";

        testAql(aql, "Select e/ehr_id/value as F1 from EHR e");
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
            assertThat(e.getMessage())
                    .isEqualTo("Parse exception: line 1: char 113 mismatched input 'EHR' expecting FROM");
        }
    }

    @Test
    void parseWhere() {
        String aql =
                "Select o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        testAql(aql, aql);
    }

    @Test
    void parseTop() {
        String aql =
                "Select TOP 10 FORWARD o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        testAql(aql, aql);

        String aqlTopWithoutDirection =
                "Select TOP 10 o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude as Systolic__magnitude, e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1] where (o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude >= $magnitude and o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude < 1.1)";

        testAql(aqlTopWithoutDirection, aql);
    }

    @Test
    void parseOrderBy() {

        String aqlTwoOrderBy =
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASCENDING, e/ehr_id/value DESCENDING";

        testAql(aqlTwoOrderBy, aqlTwoOrderBy);

        String aqlShortenedSymbols =
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASC, e/ehr_id/value DESC";

        testAql(aqlShortenedSymbols, aqlTwoOrderBy);

        var sortDefault1 =
                "select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude, e/ehr_id/value";
        testAql(
                sortDefault1,
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude ASCENDING, e/ehr_id/value ASCENDING");

        var sortDefault2 =
                "select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESC, e/ehr_id/value";
        testAql(
                sortDefault2,
                "Select e/ehr_id/value as ehr_id from EHR e contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"
                        + " order by o0/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value/magnitude DESCENDING, e/ehr_id/value ASCENDING");
    }

    void testAql(String aql, String expected) {
        AqlToDtoParser cut = new AqlToDtoParser();
        AqlDto actual = cut.parse(aql);

        assertThat(actual).isNotNull();

        String actualAql = new AqlBinder().bind(actual).getLeft().buildAql();

        assertThat(actualAql).isEqualTo(expected);

        String roundtripAql =
                new AqlBinder().bind(cut.parse(expected)).getLeft().buildAql();

        assertThat(roundtripAql).isEqualTo(expected);
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
        assertThat(actual.getContains()).isNotNull();

        assertThat(render(actual.getContains())).isEqualTo(s);
    }

    @Test
    void parseContainsLogical4() {
        String aql =
                "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] contains CLUSTER) or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

        testContains(
                aql,
                "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 --> CLUSTER OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
    }

    String render(ContainmentExpresionDto containmentExpresion) {
        StringBuilder sb = new StringBuilder();
        if (containmentExpresion instanceof ContainmentDto) {
            sb.append(((ContainmentDto) containmentExpresion).getContainment().getArchetypeId());
            ContainmentExpresionDto contains = ((ContainmentDto) containmentExpresion).getContains();
            if (contains != null) {
                sb.append(" --> ").append(render(contains));
            }
        } else if (containmentExpresion instanceof ContainmentLogicalOperator) {
            sb.append("(")
                    .append(((ContainmentLogicalOperator) containmentExpresion)
                            .getValues().stream()
                                    .map(this::render)
                                    .collect(Collectors.joining((StringUtils.wrap(
                                            ((ContainmentLogicalOperator) containmentExpresion)
                                                    .getSymbol()
                                                    .toString(),
                                            " ")))))
                    .append(")");
            return sb.toString();
        }
        return sb.toString();
    }

    @Test
    void parseAqlLimitOffset() {
        var parser = new AqlToDtoParser();

        var query1 = "select e/ehr_id/value "
                + "from EHR e "
                + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                + "contains OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                + "order by e/ehr_id/value ASC "
                + "limit 10";

        var aql = parser.parse(query1);
        assertEquals(10, aql.getLimit());

        var query2 = "select e/ehr_id/value "
                + "from EHR e "
                + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                + "contains OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                + "limit 50 "
                + "offset 100";

        aql = parser.parse(query2);
        assertEquals(50, aql.getLimit());
        assertEquals(100, aql.getOffset());

        var query3 = "select e/ehr_id/value "
                + "from EHR e "
                + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                + "contains OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                + "limit 50 "
                + "order by e/ehr_id/value ASC ";

        aql = parser.parse(query3);
        assertEquals(50, aql.getLimit());

        var query4 = "select e/ehr_id/value "
                + "from EHR e "
                + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                + "contains OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                + "offset 100 "
                + "order by e/ehr_id/value ASC ";

        var query5 = "select e/ehr_id/value "
                + "from EHR e "
                + "contains COMPOSITION c[openEHR-EHR-COMPOSITION.report.v1] "
                + "contains OBSERVATION o [openEHR-EHR-OBSERVATION.body_temperature.v2] "
                + "offset 100";

        assertThrows(AqlParseException.class, () -> parser.parse(query5));
    }

    @Test
    void parseWhereClauseWithBoolean() {
        String aql;

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = true";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = true");

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value is true";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = true");

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value is not true";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value != true");

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = false";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = false");

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value is false";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value = false");

        aql =
                "select e/ehr_id/value from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value is not false";
        testAql(
                aql,
                "Select e/ehr_id/value as F1 from EHR e contains OBSERVATION o where o/data[at0001]/items[at0024]/items[at0025]/value/value != false");
    }

    @Test
    void orderByAndLimitOrder() {
        var aql1 = "Select "
                + "c/name/value as Name, c/context/start_time as date_time, c/composer/name as Composer "
                + "from EHR e contains COMPOSITION c "
                + "order by c/context/start_time ASCENDING "
                + "LIMIT 10 OFFSET 10";

        var aql2 = "Select "
                + "c/name/value as Name, c/context/start_time as date_time, c/composer/name as Composer "
                + "from EHR e contains COMPOSITION c "
                + "LIMIT 10 OFFSET 10 "
                + "order by c/context/start_time ASCENDING";
        testAql(aql1, aql2);

        testAql(aql2, aql2);

        var aql3 = "Select "
                + "c/name/value as Name, c/context/start_time as date_time, c/composer/name as Composer "
                + "from EHR e contains COMPOSITION c "
                + "LIMIT 10 OFFSET 10";
        testAql(aql3, aql3);

        var aql4 = "Select "
                + "c/name/value as Name, c/context/start_time as date_time, c/composer/name as Composer "
                + "from EHR e contains COMPOSITION c "
                + "order by c/context/start_time ASCENDING";
        testAql(aql4, aql4);
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
