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

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.TemplateEndpoint;
import org.openehr.schemas.v1.OPERATIONALTEMPLATE;
import org.openehr.schemas.v1.TemplateDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.ACCEPT_APPLICATION_JSON;
import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.ACCEPT_APPLICATION_XML;

public class DefaultRestTemplateEndpoint implements TemplateEndpoint {

    public static final String DEFINITION_TEMPLATE_ADL_1_4_PATH = "definition/template/adl1.4/";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DefaultRestClient defaultRestClient;

    public DefaultRestTemplateEndpoint(DefaultRestClient defaultRestClient) {
        this.defaultRestClient = defaultRestClient;
    }

    @Override
    public Optional<OPERATIONALTEMPLATE> findTemplate(String templateId) {
        final TemplateDocument templateDocument;
        try {

            URI uri = defaultRestClient.getConfig().getBaseUri().resolve(new URIBuilder().setPath(defaultRestClient.getConfig().getBaseUri().getPath() + DEFINITION_TEMPLATE_ADL_1_4_PATH + templateId).build());
            logger.debug("Calling Get {}", uri);
            HttpResponse httpResponse = Request.Get(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_XML)
                    .execute()
                    .returnResponse();

            DefaultRestClient.checkStatus(httpResponse, HttpStatus.SC_OK, HttpStatus.SC_NOT_FOUND);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                return Optional.empty();
            }
            templateDocument = TemplateDocument.Factory.parse(httpResponse.getEntity().getContent());
        } catch (IOException | XmlException | URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }

        return Optional.of(templateDocument.getTemplate());
    }

    @Override
    public void ensureExistence(String templateId) {
        Optional<OPERATIONALTEMPLATE> operationaltemplate = defaultRestClient.getTemplateProvider().find(templateId);
        if (!operationaltemplate.isPresent()) {
            throw new ClientException(String.format("Unknown Template with Id %s", templateId));
        }
        if (!findTemplate(templateId).isPresent()) {
            upload(operationaltemplate.get());
        }
    }


    /**
     * Upload a template to the remote system.
     *
     * @param operationaltemplate
     * @return The templateId
     * @throws ClientException
     * @throws WrongStatusCodeException
     */
    String upload(OPERATIONALTEMPLATE operationaltemplate) {
        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(DEFINITION_TEMPLATE_ADL_1_4_PATH);
        XmlOptions opts = new XmlOptions();
        opts.setSaveSyntheticDocumentElement(new QName("http://schemas.openehr.org/v1", "template"));
        try {
            HttpResponse response = Request.Post(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_JSON).bodyString(
                            operationaltemplate.xmlText(opts), ContentType.APPLICATION_XML)
                    .execute().returnResponse();
            DefaultRestClient.checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT);
            Header location = response.getFirstHeader(HttpHeaders.LOCATION);
            return location.getValue().substring(location.getValue().lastIndexOf('/') + 1);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }
}
