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

package org.ehrbase.client.std;

import com.nedap.archie.rm.composition.Composition;
import org.apache.commons.io.IOUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.introspect.TemplateIntrospect;
import org.ehrbase.test_data.composition.CompositionTestDataSimSDTJson;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Ignore;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;


public class FlatJsonUnmarschallerTest {

    @Test
    @Ignore
    public void unmarschall() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.CORONA_ANAMMNESE.getStream()).getTemplate();
        TemplateIntrospect introspect = new TemplateIntrospect(template);

        FlatJsonUnmarschaller cut = new FlatJsonUnmarschaller();

        String flat = IOUtils.toString(CompositionTestDataSimSDTJson.CORONA.getStream(), StandardCharsets.UTF_8);

        Composition actual = cut.Unmarschall(flat, introspect, template);

        assertThat(actual).isNotNull();

        assertThat(cut.getUnconsumed()).containsExactlyInAnyOrder();
    }
}