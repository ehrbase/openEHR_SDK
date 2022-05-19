/*
 *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.building.webtemplateskeletnbuilder;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datatypes.CodePhrase;
import org.apache.xmlbeans.XmlException;
import org.assertj.core.groups.Tuple;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;

import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

public class WebTemplateSkeletonBuilderTest {

    @Test
    public void build() throws XmlException, IOException {

        org.openehr.schemas.v1.TemplateDocument document = org.openehr.schemas.v1.TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream());
        OPERATIONALTEMPLATE operationaltemplate = document.getTemplate();

        WebTemplate webTemplate = new OPTParser(operationaltemplate).parse();

    Composition generate = WebTemplateSkeletonBuilder.build(webTemplate, true);
    assertThat(generate).isNotNull();
    assertThat(generate.getCategory().getDefiningCode())
            .extracting(CodePhrase::getCodeString, c -> c.getTerminologyId().getValue())
            .containsExactly("433", "openehr");
    assertThat(generate.itemAtPath("/composer")).isNotNull();
    assertThat(generate.itemAtPath("/context/end_time")).isNotNull();
    assertThat(generate.itemAtPath("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"))
        .isNotNull();
    assertThat(
            ((Observation)
                    generate.itemAtPath(
                        "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]"))
                .getEncoding())
        .extracting(CodePhrase::getCodeString, c -> c.getTerminologyId().getValue())
        .containsExactly("UTF-8", "IANA_character-sets");
    assertThat(
            generate.itemAtPath(
                "/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value"))
        .isNotNull();
    }
}