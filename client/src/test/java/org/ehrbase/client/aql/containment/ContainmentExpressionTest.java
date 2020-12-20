/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.containment;

import static org.assertj.core.api.Assertions.assertThat;

import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.CoronaAnamneseCompositionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.AllgemeineAngabenSectionContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.GeschichteHistorieObservationContainment;
import org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition.SymptomeSectionContainment;
import org.junit.Test;

public class ContainmentExpressionTest {

  @Test
  public void buildAQLContains() {
    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();
    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();

    coronaAnamneseCompositionContainment.setContains(symptomeSectionContainment);

    Query.buildEntityQuery(coronaAnamneseCompositionContainment);

    assertThat(coronaAnamneseCompositionContainment.buildAQL())
        .isEqualTo(
            "COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains SECTION s1[openEHR-EHR-SECTION.adhoc.v1]");
  }

  @Test
  public void buildAQLOr() {
    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();

    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();
    AllgemeineAngabenSectionContainment allgemeineAngabenSectionContainment =
        AllgemeineAngabenSectionContainment.getInstance();
    coronaAnamneseCompositionContainment.setContains(
        ContainmentExpression.or(allgemeineAngabenSectionContainment, symptomeSectionContainment));

    Query.buildEntityQuery(coronaAnamneseCompositionContainment);

    assertThat(coronaAnamneseCompositionContainment.buildAQL())
        .isEqualTo(
            "COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (SECTION s1[openEHR-EHR-SECTION.adhoc.v1] or SECTION s2[openEHR-EHR-SECTION.adhoc.v1])");
  }

  @Test
  public void buildAQLOr2() {
    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();

    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();
    AllgemeineAngabenSectionContainment allgemeineAngabenSectionContainment =
        AllgemeineAngabenSectionContainment.getInstance();
    GeschichteHistorieObservationContainment geschichteHistorieObservationContainment =
        GeschichteHistorieObservationContainment.getInstance();

    coronaAnamneseCompositionContainment.setContains(
        allgemeineAngabenSectionContainment
            .or(symptomeSectionContainment)
            .or(geschichteHistorieObservationContainment));

    Query.buildEntityQuery(coronaAnamneseCompositionContainment);

    assertThat(coronaAnamneseCompositionContainment.buildAQL())
        .isEqualTo(
            "COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (SECTION s1[openEHR-EHR-SECTION.adhoc.v1] or SECTION s2[openEHR-EHR-SECTION.adhoc.v1] or OBSERVATION o3[openEHR-EHR-OBSERVATION.story.v1])");
  }

  @Test
  public void buildAQLOrAndAnd() {
    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();

    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();
    AllgemeineAngabenSectionContainment allgemeineAngabenSectionContainment =
        AllgemeineAngabenSectionContainment.getInstance();
    GeschichteHistorieObservationContainment geschichteHistorieObservationContainment =
        GeschichteHistorieObservationContainment.getInstance();

    coronaAnamneseCompositionContainment.setContains(
        allgemeineAngabenSectionContainment
            .or(symptomeSectionContainment)
            .and(geschichteHistorieObservationContainment));

    Query.buildEntityQuery(coronaAnamneseCompositionContainment);

    assertThat(coronaAnamneseCompositionContainment.buildAQL())
        .isEqualTo(
            "COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains ((SECTION s1[openEHR-EHR-SECTION.adhoc.v1] or SECTION s2[openEHR-EHR-SECTION.adhoc.v1]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.story.v1])");
  }

  @Test
  public void buildAQLAnd() {
    CoronaAnamneseCompositionContainment coronaAnamneseCompositionContainment =
        CoronaAnamneseCompositionContainment.getInstance();

    SymptomeSectionContainment symptomeSectionContainment =
        SymptomeSectionContainment.getInstance();
    AllgemeineAngabenSectionContainment allgemeineAngabenSectionContainment =
        AllgemeineAngabenSectionContainment.getInstance();
    coronaAnamneseCompositionContainment.setContains(
        ContainmentExpression.and(allgemeineAngabenSectionContainment, symptomeSectionContainment));

    Query.buildEntityQuery(coronaAnamneseCompositionContainment);

    assertThat(coronaAnamneseCompositionContainment.buildAQL())
        .isEqualTo(
            "COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (SECTION s1[openEHR-EHR-SECTION.adhoc.v1] and SECTION s2[openEHR-EHR-SECTION.adhoc.v1])");
  }
}
