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

package org.ehrbase.client.introspect;

import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.introspect.node.Node;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateIntrospectTest {

    @Test
    public void introspect() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();
        TemplateIntrospect cut = new TemplateIntrospect(template);

        Map<String, Node> actual = cut.getRoot().getChildren();

        assertThat(actual).isNotEmpty();

    }

    @Test
    public void introspectMultiOccurrence() throws IOException, XmlException {
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.MULTI_OCCURRENCE.getStream()).getTemplate();
        TemplateIntrospect cut = new TemplateIntrospect(template);

        Map<String, Node> actual = cut.getRoot().getChildren();

        assertThat(actual).isNotEmpty();

    }
}