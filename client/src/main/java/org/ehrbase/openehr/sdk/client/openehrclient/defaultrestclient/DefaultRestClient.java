/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.client.openehrclient.defaultrestclient;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.net.HttpHeaders;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.changecontrol.Contribution;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.ehr.VersionedComposition;
import com.nedap.archie.rm.generic.AuditDetails;
import com.nedap.archie.rm.generic.RevisionHistoryItem;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import com.nedap.archie.rm.support.identification.UIDBasedId;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.AdminEhrEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.AdminTemplateEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.AqlEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.CompositionEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.ContributionEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.DirectoryCrudEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClient;
import org.ehrbase.openehr.sdk.client.openehrclient.OpenEhrClientConfig;
import org.ehrbase.openehr.sdk.client.openehrclient.TemplateEndpoint;
import org.ehrbase.openehr.sdk.client.openehrclient.VersionedCompositionEndpoint;
import org.ehrbase.openehr.sdk.client.templateprovider.ClientTemplateProvider;
import org.ehrbase.openehr.sdk.serialisation.RMDataFormat;
import org.ehrbase.openehr.sdk.serialisation.dto.DefaultValuesProvider;
import org.ehrbase.openehr.sdk.serialisation.mapper.RmObjectJsonDeSerializer;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.util.exception.OptimisticLockException;
import org.ehrbase.openehr.sdk.util.exception.WrongStatusCodeException;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

public class DefaultRestClient implements OpenEhrClient {

    static final ObjectMapper OBJECT_MAPPER = createObjectMapper();
    private final OpenEhrClientConfig config;
    private final TemplateProvider templateProvider;
    private final Executor executor;
    private final DefaultRestEhrEndpoint defaultRestEhrEndpoint;
    private final DefaultValuesProvider defaultValuesProvider;

    public DefaultRestClient(OpenEhrClientConfig config, TemplateProvider templateProvider) {
        this(config, templateProvider, null);
    }

    public DefaultRestClient(OpenEhrClientConfig config) {
        this(config, null, null);
    }

    public DefaultRestClient(OpenEhrClientConfig config, TemplateProvider templateProvider, HttpClient httpClient) {
        this.config = config;

        if (templateProvider != null) {
            this.templateProvider = templateProvider;
        } else {
            this.templateProvider = new ClientTemplateProvider(this);
        }

        executor = Executor.newInstance(httpClient);
        defaultRestEhrEndpoint = new DefaultRestEhrEndpoint(this);

        if (config.getDefaultValuesProvider() != null) {
            defaultValuesProvider = config.getDefaultValuesProvider();
        } else {
            defaultValuesProvider = o -> new DefaultValues();
        }
    }

    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule("openEHR", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(AuditDetails.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(Composition.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(Contribution.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(DvCodedText.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(DvText.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(EhrStatus.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(Folder.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(HierObjectId.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(ItemStructure.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(ObjectRef.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(ObjectVersionId.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(UIDBasedId.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(RevisionHistoryItem.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(VersionedComposition.class, new RmObjectJsonDeSerializer());

        objectMapper.registerModule(module);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    protected ObjectVersionId httpPost(URI uri, RMObject body) {
        return httpPost(uri, body, null);
    }

    protected ObjectVersionId httpPost(URI uri, RMObject body, Map<String, String> headers) {
        String bodyString = RMDataFormat.canonicalJSON().marshal(body);
        HttpResponse response = internalPost(
                uri, headers, bodyString, ContentType.APPLICATION_JSON, ContentType.APPLICATION_JSON.getMimeType());
        Header eTag = response.getFirstHeader(HttpHeaders.ETAG);
        return buildVersionUidFromETag(eTag);
    }

    private ObjectVersionId buildVersionUidFromETag(Header eTag) {
        return new ObjectVersionId(StringUtils.unwrap(StringUtils.removeStart(eTag.getValue(), "W/"), '"'));
    }

    protected HttpResponse internalPost(
            URI uri, Map<String, String> headers, String bodyString, ContentType contentType, String accept) {
        HttpResponse response;
        try {

            Request request =
                    Request.Post(uri).addHeader(HttpHeaders.ACCEPT, accept).bodyString(bodyString, contentType);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }
            response = executor.execute(request).returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
        return response;
    }

    protected HttpResponse internalPut(
            URI uri, Map<String, String> headers, String bodyString, ContentType contentType, String accept) {
        HttpResponse response;
        try {

            Request request =
                    Request.Put(uri).addHeader(HttpHeaders.ACCEPT, accept).bodyString(bodyString, contentType);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }
            response = executor.execute(request).returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
        return response;
    }

    protected ObjectVersionId httpPut(URI uri, Locatable body, ObjectVersionId versionUid) {
        return httpPut(uri, body, versionUid, null);
    }

    protected ObjectVersionId httpPut(
            URI uri, Locatable body, ObjectVersionId versionUid, Map<String, String> headers) {
        try {
            Request request = Request.Put(uri)
                    .addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType())
                    .addHeader(HttpHeaders.IF_MATCH, versionUid.getValue())
                    .bodyString(RMDataFormat.canonicalJSON().marshal(body), ContentType.APPLICATION_JSON);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }
            HttpResponse response = executor.execute(request).returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_PRECONDITION_FAILED);
            if (HttpStatus.SC_PRECONDITION_FAILED == response.getStatusLine().getStatusCode()) {
                throw new OptimisticLockException("Entity outdated");
            }
            Header eTag = response.getFirstHeader(HttpHeaders.ETAG);
            return buildVersionUidFromETag(eTag);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    protected <T> Optional<T> httpGet(URI uri, Class<T> valueType) {
        return httpGet(uri, valueType, null);
    }

    protected <T> Optional<T> httpGet(URI uri, Class<T> valueType, Map<String, String> headers) {
        HttpResponse response = internalGet(uri, headers, ContentType.APPLICATION_JSON.getMimeType());

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT
                || response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
            return Optional.empty();
        }
        try {
            String value = EntityUtils.toString(response.getEntity());
            return Optional.of(OBJECT_MAPPER.readValue(value, valueType));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    protected HttpResponse internalGet(URI uri, Map<String, String> headers, String accept) {
        HttpResponse response;
        try {
            Request request = Request.Get(uri).addHeader(HttpHeaders.ACCEPT, accept);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }

            response = executor.execute(request).returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_NOT_FOUND);

        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
        return response;
    }

    /**
     * used with the admin endpoint for now
     *
     * @param uri
     * @param headers
     * @return
     */
    protected HttpResponse internalDelete(URI uri, Map<String, String> headers) {
        HttpResponse response;
        try {
            Request request = Request.Delete(uri);
            if (headers != null) {
                headers.forEach(request::addHeader);
            }

            response = executor.execute(request).returnResponse();
            checkStatus(
                    response,
                    HttpStatus.SC_OK,
                    HttpStatus.SC_NO_CONTENT,
                    HttpStatus.SC_ACCEPTED,
                    HttpStatus.SC_NOT_FOUND);

        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
        return response;
    }

    void checkStatus(HttpResponse httpResponse, int... expected) {
        if (!ArrayUtils.contains(expected, httpResponse.getStatusLine().getStatusCode())) {
            String message = Optional.of(httpResponse)
                    .map(HttpResponse::getEntity)
                    .map(e -> {
                        try {
                            return EntityUtils.toString(e);
                        } catch (IOException ex) {
                            return null;
                        }
                    })
                    .orElse("");

            throw new WrongStatusCodeException(
                    message, httpResponse.getStatusLine().getStatusCode(), expected);
        }
    }

    protected OpenEhrClientConfig getConfig() {
        return config;
    }

    protected DefaultValuesProvider getDefaultValuesProvider() {
        return defaultValuesProvider;
    }

    public Executor getExecutor() {
        return executor;
    }

    @Override
    public DefaultRestEhrEndpoint ehrEndpoint() {
        return defaultRestEhrEndpoint;
    }

    TemplateProvider getTemplateProvider() {
        return templateProvider;
    }

    @Override
    public CompositionEndpoint compositionEndpoint(UUID ehrId) {
        return new DefaultRestCompositionEndpoint(this, ehrId);
    }

    @Override
    public ContributionEndpoint contributionEndpoint(UUID ehrId) {
        return new DefaultRestContributionEndpoint(this, ehrId);
    }

    @Override
    public DirectoryCrudEndpoint directoryCrudEndpoint(UUID ehrId) {
        return new DefaultCrudEndpoint(this, ehrId);
    }

    @Override
    public TemplateEndpoint templateEndpoint() {
        return new DefaultRestTemplateEndpoint(this);
    }

    @Override
    public AqlEndpoint aqlEndpoint() {
        return new DefaultRestAqlEndpoint(this);
    }

    @Override
    public AdminEhrEndpoint adminEhrEndpoint() {
        return new DefaultRestAdminEhrEndpoint(this);
    }

    @Override
    public AdminTemplateEndpoint adminTemplateEndpoint() {
        return new DefaultRestAdminTemplateEndpoint(this);
    }

    @Override
    public VersionedCompositionEndpoint versionedCompositionEndpoint(UUID ehrId) {
        return new DefaultRestVersionedCompositionEndpoint(this, ehrId);
    }
}
