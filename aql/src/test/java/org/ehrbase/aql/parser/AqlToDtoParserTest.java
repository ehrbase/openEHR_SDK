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

package org.ehrbase.aql.parser;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.binder.AqlBinder;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.junit.Test;

public class AqlToDtoParserTest {

  @Test
  public void parse() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1]";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();

    String actualAql = new AqlBinder().bind(actual).getLeft().buildAql();

    assertThat(actualAql).isEqualTo(aql);
  }

  @Test
  public void parseContains() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bericht_ID__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains OBSERVATION o0[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();
    assertThat(actual.getContains()).isNotNull();

    assertThat(render(actual.getContains()))
        .isEqualTo(
            "openEHR-EHR-COMPOSITION.report.v1 --> openEHR-EHR-OBSERVATION.sample_blood_pressure.v1");
  }

  @Test
  public void parseContainsLogical() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] and OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] or OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();
    assertThat(actual.getContains()).isNotNull();

    assertThat(render(actual.getContains()))
        .isEqualTo(
            "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 AND openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) OR openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
  }

  @Test
  public void parseContainsLogical2() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0] and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();
    assertThat(actual.getContains()).isNotNull();

    assertThat(render(actual.getContains()))
        .isEqualTo(
            "openEHR-EHR-COMPOSITION.report.v1 --> (openEHR-EHR-OBSERVATION.story.v1 OR (openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 AND openEHR-EHR-OBSERVATION.exposure_assessment.v0))");
  }

  @Test
  public void parseContainsLogical3() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains ((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();
    assertThat(actual.getContains()).isNotNull();

    assertThat(render(actual.getContains()))
        .isEqualTo(
            "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
  }

  @Test
  public void parseContainsLogical4() {
    String aql =
        "Select c0/context/other_context[at0001]/items[at0002]/value/value as Bezeichnung_des_Symptoms_oder_Anzeichens___value, o3/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value/value as Kommentar__value from EHR e  contains COMPOSITION c0[openEHR-EHR-COMPOSITION.report.v1] contains (((OBSERVATION o1[openEHR-EHR-OBSERVATION.story.v1] contains CLUSTER) or OBSERVATION o2[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]) and OBSERVATION o3[openEHR-EHR-OBSERVATION.exposure_assessment.v0])";

    AqlToDtoParser cut = new AqlToDtoParser();
    AqlDto actual = cut.parse(aql);

    assertThat(actual).isNotNull();
    assertThat(actual.getContains()).isNotNull();

    assertThat(render(actual.getContains()))
        .isEqualTo(
            "openEHR-EHR-COMPOSITION.report.v1 --> ((openEHR-EHR-OBSERVATION.story.v1 --> CLUSTER OR openEHR-EHR-OBSERVATION.symptom_sign_screening.v0) AND openEHR-EHR-OBSERVATION.exposure_assessment.v0)");
  }

  private String render(ContainmentExpresionDto containmentExpresion) {
    StringBuilder sb = new StringBuilder();
    if (containmentExpresion instanceof ContainmentDto) {
      sb.append(((ContainmentDto) containmentExpresion).getArchetypeId());
      ContainmentExpresionDto contains = ((ContainmentDto) containmentExpresion).getContains();
      if (contains != null) {
        sb.append(" --> ").append(render(contains));
      }
    } else if (containmentExpresion instanceof ContainmentLogicalOperator) {
      sb.append("(")
          .append(
              ((ContainmentLogicalOperator) containmentExpresion)
                  .getValues().stream()
                      .map(this::render)
                      .collect(
                          Collectors.joining(
                              (StringUtils.wrap(
                                  ((ContainmentLogicalOperator) containmentExpresion)
                                      .getSymbol()
                                      .toString(),
                                  " ")))))
          .append(")");
      return sb.toString();
    }
    return sb.toString();
  }
}
