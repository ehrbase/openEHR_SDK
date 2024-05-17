/*
 * Copyright (c) 2021 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.conformance_test.extern.tests;

import static org.assertj.core.api.Assertions.assertThat;

import care.better.platform.web.template.FeederAuditTest;
import care.better.platform.web.template.extension.WebTemplateTestExtension;
import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.composition.Composition;
import org.assertj.core.api.Assertions;
import org.ehrbase.openehr.sdk.conformance_test.extern.CompositionValidatorImp;
import org.ehrbase.openehr.sdk.conformance_test.extern.Helper;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.flatencoding.FlatFormat;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.CanonicalJson;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({WebTemplateTestExtension.class})
public class FeederAuditTestOverwritten extends FeederAuditTest {

    @Override
    /* test proprietary stuff from better */
    public void structuredWithGenericFields() throws Exception {}

    @Override
    @Test
    public void feederAuditBroken() throws Exception {

        String flatComposition = this.getFileContent("/res/gel_data.json")
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

        RMDataFormat flatJson = Helper.getFlatJson(template, FlatFormat.SIM_SDT);
        Composition composition = flatJson.unmarshal(flatComposition);

        Assertions.assertThat(
                        new CompositionValidatorImp().validate(template, new CanonicalJson().marshal(composition)))
                .isEmpty();

        FeederAudit feederAudit = composition.getContent().get(0).getFeederAudit();

        assertThat(feederAudit).isNotNull();

        assertThat(feederAudit.getOriginatingSystemAudit().getSystemId()).isEqualTo("infoflex");

        assertThat(feederAudit.getOriginatingSystemAudit().getTime().getValue()).hasToString("2018-01-01T03:00Z");
    }
}
