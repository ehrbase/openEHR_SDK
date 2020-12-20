/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.client.building;

import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.building.OptSkeletonBuilder;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

public class OptSkeletonBuilderTest {

  @Test
  public void testGenerate() throws Exception {

    org.openehr.schemas.v1.TemplateDocument document =
        org.openehr.schemas.v1.TemplateDocument.Factory.parse(
            OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream());
    OPERATIONALTEMPLATE operationaltemplate = document.getTemplate();
    OptSkeletonBuilder cut = new OptSkeletonBuilder();

    Composition generate = (Composition) cut.generate(operationaltemplate);
    assertThat(generate).isNotNull();
    assertThat(generate.itemAtPath("/composer")).isNotNull();
    assertThat(generate.itemAtPath("/context/end_time")).isNotNull();
    assertThat(
            generate.itemAtPath(
                "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value"))
        .isNotNull();
  }

  @Test
  public void testGenerateCorona() throws Exception {

    org.openehr.schemas.v1.TemplateDocument document =
        org.openehr.schemas.v1.TemplateDocument.Factory.parse(
            OperationalTemplateTestData.CORONA_ANAMNESE.getStream());
    OPERATIONALTEMPLATE operationaltemplate = document.getTemplate();
    OptSkeletonBuilder cut = new OptSkeletonBuilder();

    Composition generate = (Composition) cut.generate(operationaltemplate);
    assertThat(generate.getContent())
        .extracting(Locatable::getName)
        .extracting(DvText::getValue)
        .containsExactlyInAnyOrder(
            "Geschichte/Historie", "Symptome", "Kontakt", "Risikogebiet", "Allgemeine Angaben");
  }

  @Test
  public void testGenerateEpisodeOfCare() throws Exception {

    org.openehr.schemas.v1.TemplateDocument document =
        org.openehr.schemas.v1.TemplateDocument.Factory.parse(
            OperationalTemplateTestData.EPISODE_OF_CARE.getStream());
    OPERATIONALTEMPLATE operationaltemplate = document.getTemplate();
    OptSkeletonBuilder cut = new OptSkeletonBuilder();

    Composition generate = (Composition) cut.generate(operationaltemplate);
    assertThat(generate).isNotNull();
    assertThat(generate.itemAtPath("/composer")).isNotNull();
    assertThat(generate.itemAtPath("/context/end_time")).isNotNull();
    assertThat(generate.itemAtPath("/name"))
        .extracting(d -> ((DvText) d).getValue())
        .isEqualTo("EpisodeOfCare");
  }
}
