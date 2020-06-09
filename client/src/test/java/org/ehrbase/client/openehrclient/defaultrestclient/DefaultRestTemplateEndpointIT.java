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

package org.ehrbase.client.openehrclient.defaultrestclient;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DefaultRestTemplateEndpointIT {

    private static OpenEhrClient openEhrClient;

    @BeforeClass
    public static void setup() throws URISyntaxException {
        openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    }


    @Test
    public void testFindTemplate() {

        Optional<OPERATIONALTEMPLATE> operationaltemplate = openEhrClient.templateEndpoint().findTemplate("ehrbase_blood_pressure_simple.de.v99999");
        assertFalse(operationaltemplate.isPresent());

        Optional<OPERATIONALTEMPLATE> operationaltemplateFound = openEhrClient.templateEndpoint().findTemplate("ehrbase_blood_pressure_simple.de.v0");
        assertTrue(operationaltemplateFound.isPresent());
    }

    @Test
    public void testCreate() throws URISyntaxException, IOException, XmlException {
        DefaultRestClient cut = (DefaultRestClient) DefaultRestClientTestHelper.setupDefaultRestClient();
        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();
        String templateId = "ehrbase_blood_pressure_simple.de.v" + RandomStringUtils.randomNumeric(10);
        template.getTemplateId().setValue(templateId);
        template.getUid().setValue(UUID.randomUUID().toString());
        String actual = new DefaultRestTemplateEndpoint(cut).upload(template);
        assertThat(actual).isEqualTo(templateId);
    }
}