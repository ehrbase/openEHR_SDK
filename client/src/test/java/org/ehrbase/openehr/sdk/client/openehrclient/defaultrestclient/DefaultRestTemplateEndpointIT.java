/*
 * Copyright (c) 2019 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.openehr.sdk.response.dto.TemplatesResponseData;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.TemplateMetaDataDto;
import org.ehrbase.openehr.sdk.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class DefaultRestTemplateEndpointIT extends SdkClientTestIT {

    private static DefaultRestClient restClient;
    private static DefaultRestClient restClientWithDefaultTemplateProvider;

    private static final String TEMPLATE_NAME_PREFIX = "ehrbase_blood_pressure_simple.de.v";

    private String templateId = null; // global used for teardown

    @BeforeAll
    public static void setup() throws URISyntaxException {
        restClient = SdkClientTestIT.setupDefaultRestClient();
        restClientWithDefaultTemplateProvider = SdkClientTestIT.setupRestClientWithDefaultTemplateProvider();
    }

    @AfterEach
    public void tearDown() {
        // delete template with random version id (mostly)
        restClient.adminTemplateEndpoint().delete(templateId);
    }

    @Test
    void testFindTemplate() {
        Optional<OPERATIONALTEMPLATE> operationalTemplate =
                restClient.templateEndpoint().findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "99999"));

        assertFalse(operationalTemplate.isPresent());

        Optional<OPERATIONALTEMPLATE> operationalTemplateFound =
                restClient.templateEndpoint().findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "0"));

        assertTrue(operationalTemplateFound.isPresent());
    }

    @Test
    void testFindTemplateWithDefaultTemplateProvider() {
        Optional<OPERATIONALTEMPLATE> operationalTemplate = restClientWithDefaultTemplateProvider
                .templateEndpoint()
                .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "3456245"));

        assertFalse(operationalTemplate.isPresent());

        Optional<OPERATIONALTEMPLATE> operationalTemplateFound =
                restClient.templateEndpoint().findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "0"));

        assertTrue(operationalTemplateFound.isPresent());
    }

    @Test
    void testCreateTemplate() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
                .getTemplate();

        templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        template.getTemplateId().setValue(templateId);
        template.getUid().setValue(UUID.randomUUID().toString());

        String actual = new DefaultRestTemplateEndpoint(restClient).upload(template);
        assertThat(actual).isEqualTo(templateId);
    }

    @Test
    void testCreateTemplateWithDefaultTemplateProvider() throws IOException, XmlException {

        OPERATIONALTEMPLATE template = TemplateDocument.Factory.parse(
                        OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
                .getTemplate();

        templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        template.getTemplateId().setValue(templateId);
        template.getUid().setValue(UUID.randomUUID().toString());

        String actual = new DefaultRestTemplateEndpoint(restClientWithDefaultTemplateProvider).upload(template);
        assertThat(actual).isEqualTo(templateId);
    }

    @Test
    void testFindAllTemplates() throws IOException, XmlException {

        templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        String savedTemplateId =
                uploadTemplate(restClient, OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE, templateId);

        assertThat(savedTemplateId).isEqualTo(templateId);

        TemplatesResponseData templatesResponseData =
                restClient.templateEndpoint().findAllTemplates();
        assertThat(templatesResponseData).isNotNull();

        List<TemplateMetaDataDto> templateMetaDataDtos = templatesResponseData.get();
        assertThat(templateMetaDataDtos).isNotEmpty();
        assertThat(templateMetaDataDtos.stream().anyMatch(t -> t.getTemplateId().equals(templateId)))
                .isTrue();
    }

    @Test
    void testFindAllTemplatesWithDefaultTemplateProvider() throws IOException, XmlException {
        templateId = String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
        String savedTemplateId = uploadTemplate(
                restClientWithDefaultTemplateProvider, OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE, templateId);

        assertThat(savedTemplateId).isEqualTo(templateId);

        TemplatesResponseData templatesResponseData =
                restClientWithDefaultTemplateProvider.templateEndpoint().findAllTemplates();
        assertThat(templatesResponseData).isNotNull();

        List<TemplateMetaDataDto> templateMetaDataDtos = templatesResponseData.get();
        assertThat(templateMetaDataDtos).isNotEmpty();
        assertThat(templateMetaDataDtos.stream().anyMatch(t -> t.getTemplateId().equals(templateId)))
                .isTrue();
    }

    private String uploadTemplate(
            DefaultRestClient client, OperationalTemplateTestData testTemplate, String testTemplateId)
            throws IOException, XmlException {
        DefaultRestTemplateEndpoint templateEndpoint = new DefaultRestTemplateEndpoint(client);

        OPERATIONALTEMPLATE template =
                TemplateDocument.Factory.parse(testTemplate.getStream()).getTemplate();

        template.getTemplateId().setValue(testTemplateId);
        template.getUid().setValue(UUID.randomUUID().toString());

        return templateEndpoint.upload(template);
    }
}
