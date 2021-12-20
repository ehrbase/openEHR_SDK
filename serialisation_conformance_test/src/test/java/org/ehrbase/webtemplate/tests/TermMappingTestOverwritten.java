/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *
 *  This file is part of Project EHRbase
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.ehrbase.webtemplate.tests;

import care.better.platform.web.template.TermMappingTest;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.TermMapping;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.assertj.core.groups.Tuple;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.webtemplate.Helper.getFlatJson;

public class TermMappingTestOverwritten extends TermMappingTest {

  @Override
  /*
   *  Test error handling which is not specified
   */
  public void testTermMappingFromJsonNoPurposeValue() throws Exception {}

  @Override
  @Test
  public void testTermMappingFromJson() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.xml");
    String rawJson = this.getFileContent("/res/TmComposition.json");

    Composition unmarshal =
        new CanonicalJson()
            .unmarshal(rawJson.replace("\"@class\"", "\"_type\""), Composition.class);

    String structuredJson = getFlatJson(template, FlatFormat.STRUCTURED).marshal(unmarshal);

    Composition actualComposition =
        getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(structuredJson);
    List<TermMapping> termMappings =
        ((DvText)
                actualComposition
                    .itemsAtPath(
                        "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.body_temperature-zn.v1]/data[at0002]/events/data/items[at0.63]/value")
                    .get(0))
            .getMappings();

    assertThat(termMappings)
        .extracting(
            TermMapping::getMatch,
            t -> t.getTarget().getCodeString(),
            t -> t.getTarget().getTerminologyId().getValue(),
            TermMapping::getPurpose)
        .containsExactlyInAnyOrder(
            new Tuple('=', "21794005", "SNOMED-CT", null), new Tuple('=', "W.11.7", "RTX", null));

    List<TermMapping> termMappings2 =
        ((DvText)
                actualComposition
                    .itemsAtPath(
                        "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.body_temperature-zn.v1]/data[at0002]/events/state/items[at0041]/value")
                    .get(0))
            .getMappings();

    assertThat(termMappings2)
        .extracting(
            TermMapping::getMatch,
            t -> t.getTarget().getCodeString(),
            t -> t.getTarget().getTerminologyId().getValue(),
            TermMapping::getPurpose)
        .containsExactlyInAnyOrder(
            new Tuple(
                '=',
                "99.1",
                "IAXA",
                new DvCodedText(
                    "Purpose 1", new CodePhrase(new TerminologyId("Purposes"), "p.0.63.1"))));
  }

  @Override
  @Test
  public void testTermMappingFromMap() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.xml");
    String rawJson = this.getFileContent("/res/TmComposition.json");

    Composition unmarshal =
        new CanonicalJson()
            .unmarshal(rawJson.replace("\"@class\"", "\"_type\""), Composition.class);

    String flatJson = getFlatJson(template, FlatFormat.SIM_SDT).marshal(unmarshal);

    Composition actualComposition = getFlatJson(template, FlatFormat.SIM_SDT).unmarshal(flatJson);
    List<TermMapping> termMappings =
        ((DvText)
                actualComposition
                    .itemsAtPath(
                        "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.body_temperature-zn.v1]/data[at0002]/events/data/items[at0.63]/value")
                    .get(0))
            .getMappings();

    assertThat(termMappings)
        .extracting(
            TermMapping::getMatch,
            t -> t.getTarget().getCodeString(),
            t -> t.getTarget().getTerminologyId().getValue(),
            TermMapping::getPurpose)
        .containsExactlyInAnyOrder(
            new Tuple('=', "21794005", "SNOMED-CT", null), new Tuple('=', "W.11.7", "RTX", null));

    List<TermMapping> termMappings2 =
        ((DvText)
                actualComposition
                    .itemsAtPath(
                        "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.body_temperature-zn.v1]/data[at0002]/events/state/items[at0041]/value")
                    .get(0))
            .getMappings();

    assertThat(termMappings2)
        .extracting(
            TermMapping::getMatch,
            t -> t.getTarget().getCodeString(),
            t -> t.getTarget().getTerminologyId().getValue(),
            TermMapping::getPurpose)
        .containsExactlyInAnyOrder(
            new Tuple(
                '=',
                "99.1",
                "IAXA",
                new DvCodedText(
                    "Purpose 1", new CodePhrase(new TerminologyId("Purposes"), "p.0.63.1"))));
  }


}
