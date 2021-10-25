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
import org.ehrbase.response.ehrscape.TemplateMetaDataDto;
import org.ehrbase.response.openehr.TemplatesResponseData;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class DefaultRestTemplateEndpointIT {

    private static DefaultRestClient restClient;
    private static DefaultRestClient restClientWithDefaultTemplateProvider;

    private static final String TEMPLATE_NAME_PREFIX = "ehrbase_blood_pressure_simple.de.v";

    @BeforeClass
    public static void setup() throws URISyntaxException {
        restClient = DefaultRestClientTestHelper.setupDefaultRestClient();
        restClientWithDefaultTemplateProvider = DefaultRestClientTestHelper.setupRestClientWithDefaultTemplateProvider();
    }

    @Test
    public void testFindTemplate() {
        Optional<OPERATIONALTEMPLATE> operationalTemplate = restClient.templateEndpoint()
                .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "99999"));

        assertFalse(operationalTemplate.isPresent());

        Optional<OPERATIONALTEMPLATE> operationalTemplateFound = restClient.templateEndpoint()
                .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "0"));

        assertTrue(operationalTemplateFound.isPresent());
    }

    @Test
    public void testFindTemplateWithDefaultTemplateProvider() {
        Optional<OPERATIONALTEMPLATE> operationalTemplate = restClientWithDefaultTemplateProvider.templateEndpoint()
            .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "3456245"));

        assertFalse(operationalTemplate.isPresent());

        Optional<OPERATIONALTEMPLATE> operationalTemplateFound = restClient.templateEndpoint()
            .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "0"));

        assertTrue(operationalTemplateFound.isPresent());
    }

    @Test
    public void testCreateTemplate() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();

        String templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        template.getTemplateId().setValue(templateId);
        template.getUid().setValue(UUID.randomUUID().toString());

        Optional<String> actual = new DefaultRestTemplateEndpoint(restClient).upload(template);

        assertTrue(actual.isPresent());
        assertThat(actual.get()).isEqualTo(templateId);
    }

    @Test
    public void testCreateTemplateWithDefaultTemplateProvider() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream()).getTemplate();

        String templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        template.getTemplateId().setValue(templateId);
        template.getUid().setValue(UUID.randomUUID().toString());

        Optional<String> actual = new DefaultRestTemplateEndpoint(restClientWithDefaultTemplateProvider).upload(template);

        assertTrue(actual.isPresent());
        assertThat(actual.get()).isEqualTo(templateId);
    }

    @Test
    public void testFindAllTemplates() throws IOException, XmlException {

        String templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        Optional<String> savedTemplateId = uploadTemplate(restClient,OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE, templateId);

        assertTrue(savedTemplateId.isPresent());
        assertThat(savedTemplateId.get()).isEqualTo(templateId);

        TemplatesResponseData templatesResponseData = restClient.templateEndpoint().findAllTemplates();
        assertThat(templatesResponseData).isNotNull();

        List<TemplateMetaDataDto> templateMetaDataDtos = templatesResponseData.get();
        assertThat(templateMetaDataDtos).isNotEmpty();
        assertThat(templateMetaDataDtos.stream().anyMatch(t -> t.getTemplateId().equals(templateId))).isTrue();
    }

    @Test
    public void testFindAllTemplatesWithDefaultTemplateProvider() throws IOException, XmlException {
        String templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        Optional<String> savedTemplateId = uploadTemplate(restClientWithDefaultTemplateProvider,OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE, templateId);

        assertTrue(savedTemplateId.isPresent());
        assertThat(savedTemplateId.get()).isEqualTo(templateId);

        TemplatesResponseData templatesResponseData = restClientWithDefaultTemplateProvider.templateEndpoint().findAllTemplates();
        assertThat(templatesResponseData).isNotNull();

        List<TemplateMetaDataDto> templateMetaDataDtos = templatesResponseData.get();
        assertThat(templateMetaDataDtos).isNotEmpty();
        assertThat(templateMetaDataDtos.stream().anyMatch(t -> t.getTemplateId().equals(templateId))).isTrue();
    }

    private Optional<String> uploadTemplate(DefaultRestClient client, OperationalTemplateTestData testTemplate, String testTemplateId) throws IOException, XmlException{
        DefaultRestTemplateEndpoint templateEndpoint = new DefaultRestTemplateEndpoint(client);

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(testTemplate.getStream()).getTemplate();

        template.getTemplateId().setValue(testTemplateId);
        template.getUid().setValue(UUID.randomUUID().toString());

       return templateEndpoint.upload(template);
    }

}