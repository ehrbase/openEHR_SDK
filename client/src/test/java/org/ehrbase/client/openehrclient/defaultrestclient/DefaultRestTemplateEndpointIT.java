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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.xmlbeans.XmlException;
import org.ehrbase.client.openehrclient.OpenEhrClient;
import org.ehrbase.response.ehrscape.TemplateMetaDataDto;
import org.ehrbase.response.openehr.TemplatesResponseData;
import org.ehrbase.test_data.operationaltemplate.OperationalTemplateTestData;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;

public class DefaultRestTemplateEndpointIT {

  private static OpenEhrClient openEhrClient;
  private static DefaultRestClient defaultRestClient;

  private static final String TEMPLATE_NAME_PREFIX = "ehrbase_blood_pressure_simple.de.v";

  @BeforeClass
  public static void setup() throws URISyntaxException {
    openEhrClient = DefaultRestClientTestHelper.setupDefaultRestClient();
    defaultRestClient = DefaultRestClientTestHelper.setupDefaultRestClient();
  }

  @Test
  public void testFindTemplate() {

    Optional<OPERATIONALTEMPLATE> operationalTemplate =
        openEhrClient
            .templateEndpoint()
            .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "99999"));

    assertFalse(operationalTemplate.isPresent());

    Optional<OPERATIONALTEMPLATE> operationalTemplateFound =
        openEhrClient
            .templateEndpoint()
            .findTemplate(String.format("%s%s", TEMPLATE_NAME_PREFIX, "0"));

    assertTrue(operationalTemplateFound.isPresent());
  }

  @Test
  public void testCreateTemplate() throws IOException, XmlException {

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
            .getTemplate();

    String templateId =
        String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));
    template.getTemplateId().setValue(templateId);
    template.getUid().setValue(UUID.randomUUID().toString());

    String actual = new DefaultRestTemplateEndpoint(defaultRestClient).upload(template);
    assertThat(actual).isEqualTo(templateId);
  }

  @Test
  public void testFindAllTemplates() throws IOException, XmlException {

    DefaultRestTemplateEndpoint templateEndpoint =
        new DefaultRestTemplateEndpoint(defaultRestClient);

    OPERATIONALTEMPLATE template =
        TemplateDocument.Factory.parse(
                OperationalTemplateTestData.BLOOD_PRESSURE_SIMPLE.getStream())
            .getTemplate();

    String templateId =
        String.format("%s%s", TEMPLATE_NAME_PREFIX, RandomStringUtils.randomNumeric(10));

    template.getTemplateId().setValue(templateId);
    template.getUid().setValue(UUID.randomUUID().toString());

    String savedTemplateId = templateEndpoint.upload(template);
    assertThat(savedTemplateId).isEqualTo(templateId);

    TemplatesResponseData templatesResponseData = templateEndpoint.findAllTemplates();
    assertThat(templatesResponseData).isNotNull();

    List<TemplateMetaDataDto> templateMetaDataDtos = templatesResponseData.get();
    assertThat(templateMetaDataDtos).isNotEmpty();
    assertThat(templateMetaDataDtos.stream().anyMatch(t -> t.getTemplateId().equals(templateId)))
        .isTrue();
  }
}
