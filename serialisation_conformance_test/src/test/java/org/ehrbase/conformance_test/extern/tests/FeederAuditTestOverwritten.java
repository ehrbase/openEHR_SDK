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

import care.better.platform.web.template.FeederAuditTest;
import care.better.platform.web.template.extension.WebTemplateTestExtension;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.composition.Composition;
import org.assertj.core.api.Assertions;
import org.ehrbase.conformance_test.extern.CompositionValidatorImp;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.serialisation.flatencoding.FlatFormat;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ehrbase.conformance_test.extern.Helper.getFlatJson;

@ExtendWith({WebTemplateTestExtension.class})
public class FeederAuditTestOverwritten extends FeederAuditTest {

  @Override
  /* test proprietary stuff from better */
  public void structuredWithGenericFields() throws Exception {}

  @Override
  @Test
  public void feederAuditBroken() throws Exception {

    String flatComposition =
        this.getFileContent("/res/gel_data.json")
            // topography is single  valued  in the template
            .replace(
                "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography:78",
                "gel_cancer_diagnosis/problem_diagnosis:3/cancer_diagnosis/topography")
            .replace(
                "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography:137",
                "gel_cancer_diagnosis/problem_diagnosis:5/cancer_diagnosis/topography")
            .replace(
                "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography:19",
                "gel_cancer_diagnosis/problem_diagnosis:1/cancer_diagnosis/topography");

    String template = this.getFileContent("/res/GEL Cancer diagnosis input.opt");

    RMDataFormat flatJson = getFlatJson(template, FlatFormat.SIM_SDT);
    Composition composition = flatJson.unmarshal(flatComposition);

    Assertions.assertThat(
            new CompositionValidatorImp()
                .validate(template, new CanonicalJson().marshal(composition)))
        .isEmpty();

    FeederAudit feederAudit = composition.getContent().get(0).getFeederAudit();

    assertThat(feederAudit).isNotNull();

    assertThat(feederAudit.getOriginatingSystemAudit().getSystemId()).isEqualTo("infoflex");

    assertThat(feederAudit.getOriginatingSystemAudit().getTime().getValue())
        .hasToString("2018-01-01T03:00Z");
  }
}
