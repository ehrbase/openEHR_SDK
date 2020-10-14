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

package org.ehrbase.client.std.umarshal;

import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datavalues.quantity.DvQuantity;
import com.nedap.archie.rm.generic.PartySelf;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.ehrbase.validation.Validator;
import org.ehrbase.webtemplate.filter.Filter;
import org.ehrbase.webtemplate.model.WebTemplate;
import org.ehrbase.webtemplate.parser.OPTParser;
import org.junit.Assert;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


public class FlatJsonUnmarshallerTest {

    @Test
    public void unmarshal() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMNESE.getStream()).getTemplate();
        WebTemplate webTemplate = new Filter().filter(new OPTParser(template).parse());

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate, template);

        assertThat(actual).isNotNull();

        Observation observation = (Observation) actual.itemAtPath("/content[openEHR-EHR-OBSERVATION.story.v1]");
        assertThat(observation.getData().getOrigin().getValue().toString()).isEqualTo("2020-05-11T22:53:12.039139+02:00");
        assertThat(observation.getSubject()).isNotNull();
        assertThat(observation.getSubject().getClass()).isEqualTo(PartySelf.class);
        assertThat(cut.getUnconsumed()).containsExactlyInAnyOrder();

        try {
            new Validator(template).check(actual);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void unmarshalAllTypes() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.ALL_TYPES.getStream()).getTemplate();
        WebTemplate webTemplate = new Filter().filter(new OPTParser(template).parse());

        FlatJsonUnmarshaller cut = new FlatJsonUnmarshaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.ALL_TYPES.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.unmarshal(flat, webTemplate, template);

        assertThat(actual).isNotNull();

        //Choice Node was correctly parsed
        Object choice = actual.itemAtPath("/content[openEHR-EHR-EVALUATION.test_all_types.v1]/data[at0001]/items[at0009]/value");
        assertThat(choice).isNotNull();
        assertThat(choice.getClass()).isEqualTo(DvQuantity.class);
        assertThat(((DvQuantity) choice).getMagnitude()).isEqualTo(148.01210165023804d);
        assertThat(((DvQuantity) choice).getUnits()).isEqualTo("mm[H20]");

        assertThat(cut.getUnconsumed()).containsExactlyInAnyOrder(
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing|formalism",
                "test_all_types/test_all_types:0/ordinal|ordinal",
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|terminology",
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/narrative",
                "test_all_types/test_all_types:0/ordinal|value",
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types:0/current_activity/timing",
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|value",
                "test_all_types/test_all_types3:0/section_2/section_3/test_all_types2:0/ism_transition/current_state|code"
        );

        try {
            new Validator(template).check(actual);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}