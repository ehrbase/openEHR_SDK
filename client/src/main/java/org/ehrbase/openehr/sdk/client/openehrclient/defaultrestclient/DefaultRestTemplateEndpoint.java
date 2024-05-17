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

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.ehrbase.openehr.sdk.client.openehrclient.TemplateEndpoint;
import org.ehrbase.openehr.sdk.response.dto.TemplatesResponseData;
import org.ehrbase.openehr.sdk.response.dto.ehrscape.TemplateMetaDataDto;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.WrongStatusCodeException;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultRestTemplateEndpoint implements TemplateEndpoint {

    public static final String DEFINITION_TEMPLATE_ADL_1_4_PATH = "rest/openehr/v1/definition/template/adl1.4/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DefaultRestClient defaultRestClient;

    public DefaultRestTemplateEndpoint(DefaultRestClient defaultRestClient) {
        this.defaultRestClient = defaultRestClient;
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> findTemplate(String templateId) {
        final TemplateDocument templateDocument;

        try {
            URI uri = defaultRestClient
                    .getConfig()
                    .getBaseUri()
                    .resolve(new URIBuilder()
                            .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                                    + DEFINITION_TEMPLATE_ADL_1_4_PATH
                                    + templateId)
                            .build());

            HttpResponse httpResponse =
                    defaultRestClient.internalGet(uri, null, ContentType.APPLICATION_XML.getMimeType());

            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                return Optional.empty();
            }

            templateDocument =
                    TemplateDocument.Factory.parse(httpResponse.getEntity().getContent());
        } catch (IOException | XmlException | URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }

        return Optional.of(templateDocument.getTemplate());
    }

    @Override
    public TemplatesResponseData findAllTemplates() {
        try {
            URI uri = defaultRestClient
                    .getConfig()
                    .getBaseUri()
                    .resolve(new URIBuilder()
                            .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                                    + DEFINITION_TEMPLATE_ADL_1_4_PATH)
                            .build());

            HttpResponse response =
                    defaultRestClient.internalGet(uri, null, ContentType.APPLICATION_JSON.getMimeType());
            List<TemplateMetaDataDto> templateResponseData = DefaultRestClient.OBJECT_MAPPER.readValue(
                    response.getEntity().getContent(), new TypeReference<>() {});

            return new TemplatesResponseData(templateResponseData);
        } catch (URISyntaxException | IOException e) {
            logger.error(e.getMessage(), e);
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public void ensureExistence(String templateId) {
        Optional<OPERATIONALTEMPLATE> operationalTemplate =
                defaultRestClient.getTemplateProvider().find(templateId);
        if (!operationalTemplate.isPresent()) {
            throw new ClientException(String.format("Unknown Template with Id %s", templateId));
        }
        if (!findTemplate(templateId).isPresent()) {
            upload(operationalTemplate.get());
        }
    }

    /**
     * Upload a template to the remote system.
     *
     * @param operationaltemplate The operational template to be uploaded on the server
     * @return The templateId
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    String upload(OPERATIONALTEMPLATE operationaltemplate) {
        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(DEFINITION_TEMPLATE_ADL_1_4_PATH);
        XmlOptions opts = new XmlOptions();
        opts.setSaveSyntheticDocumentElement(new QName("http://schemas.openehr.org/v1", "template"));

        HttpResponse response = defaultRestClient.internalPost(
                uri,
                null,
                operationaltemplate.xmlText(opts),
                ContentType.APPLICATION_XML,
                ContentType.APPLICATION_XML.getMimeType());

        return Optional.ofNullable(response.getFirstHeader(HttpHeaders.ETAG))
                .map(header -> StringUtils.unwrap(StringUtils.removeStart(header.getValue(), "W/"), '"'))
                .orElse(operationaltemplate.getTemplateId().getValue());
    }
}
