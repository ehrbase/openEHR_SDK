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

package org.ehrbase.conformance_test.extern.tests;

import care.better.platform.web.template.NullFlavorTest;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.Element;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.conformance_test.extern.Helper.getFlatJson;

public class NullFlavorTestOverwritten extends NullFlavorTest {

  @Override
  @Test
  public void nullFlavourJsonBuild() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.xml");
    String json = this.getFileContent("/res/NullFlavor3.json");

    Composition unmarshal = getFlatJson(template, FlatFormat.STRUCTURED).unmarshal(json);

    assertThat(unmarshal).isNotNull();

    Element element1 =
        (Element)
            unmarshal
                .itemsAtPath(
                    "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.lab_test-hba1c.v1,1]/data/events[at0002]/data/items[at0005]")
                .get(0);

    assertThat(element1.getValue()).isNull();
    assertThat(element1.getNullFlavour()).isEqualTo(NullFlavour.NOT_APPLICABLE.toCodedText());

    Element element2 =
        (Element)
            unmarshal
                .itemsAtPath(
                    "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.lab_test-hba1c.v1,2]/data/events[at0003]/data/items[at0004]")
                .get(0);

    assertThat(element2.getValue()).isNull();
    assertThat(element2.getNullFlavour()).isEqualTo(NullFlavour.MASKED.toCodedText());
  }

  @Test
  public void nullFlavourMapBuild() throws Exception {
    String template = this.getFileContent("/res/Demo Vitals.xml");
    String json = this.getFileContent("/res/NullFlavor4.json");

    Composition unmarshal = getFlatJson(template, FlatFormat.SIM_SDT).unmarshal(json);

    assertThat(unmarshal).isNotNull();

    Element element1 =
        (Element)
            unmarshal
                .itemsAtPath(
                    "/content[openEHR-EHR-SECTION.ispek_dialog.v1]/items[openEHR-EHR-OBSERVATION.lab_test-hba1c.v1,2]/data/events[at0003]/data/items[at0.63]")
                .get(0);

    assertThat(element1.getValue()).isNull();
    assertThat(element1.getNullFlavour()).isEqualTo(NullFlavour.NO_INFORMATION.toCodedText());
  }


}
