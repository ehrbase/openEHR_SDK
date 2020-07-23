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

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.net.HttpHeaders;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.archetyped.Locatable;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.directory.Folder;
import com.nedap.archie.rm.ehr.EhrStatus;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.UIDBasedId;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.exception.OptimisticLockException;
import org.ehrbase.client.exception.WrongStatusCodeException;
import org.ehrbase.client.openehrclient.*;
import org.ehrbase.client.templateprovider.TemplateProvider;
import org.ehrbase.serialisation.jsonencoding.CanonicalJson;
import org.ehrbase.serialisation.mapper.RmObjectJsonDeSerializer;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

public class DefaultRestClient implements OpenEhrClient {

    static final String ACCEPT_APPLICATION_JSON = "application/json";
    static final String ACCEPT_APPLICATION_XML = "application/xml";
    static final ObjectMapper OBJECT_MAPPER = createObjectMapper();
    private final OpenEhrClientConfig config;
    private final TemplateProvider templateProvider;
    private final DefaultRestEhrEndpoint defaultRestEhrEndpoint;
    private final Map<UUID, DefaultRestDirectoryEndpoint> directoryEndpointMap = new WeakHashMap<>();


    public DefaultRestClient(OpenEhrClientConfig config, TemplateProvider templateProvider) {
        this.config = config;
        this.templateProvider = templateProvider;
        defaultRestEhrEndpoint = new DefaultRestEhrEndpoint(this);
    }


    private static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("openEHR", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(EhrStatus.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(HierObjectId.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(Composition.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(Folder.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(UIDBasedId.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(DvText.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(ObjectRef.class, new RmObjectJsonDeSerializer());
        module.addDeserializer(ItemStructure.class, new RmObjectJsonDeSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    static VersionUid httpPost(URI uri, RMObject body) {
        try {
            HttpResponse response = Request.Post(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_JSON)
                    .bodyString(new CanonicalJson().marshal(body), ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_CREATED, HttpStatus.SC_NO_CONTENT);
            Header eTag = response.getFirstHeader(HttpHeaders.ETAG);
            return new VersionUid(eTag.getValue().replace("\"", ""));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    static VersionUid httpPut(URI uri, Locatable body, VersionUid versionUid) {
        try {
            HttpResponse response = Request.Put(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_JSON)
                    .addHeader(HttpHeaders.IF_MATCH, versionUid.toString())
                    .bodyString(new CanonicalJson().marshal(body), ContentType.APPLICATION_JSON)
                    .execute().returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_NO_CONTENT, HttpStatus.SC_PRECONDITION_FAILED);
            if (HttpStatus.SC_PRECONDITION_FAILED == response.getStatusLine().getStatusCode()) {
                throw new OptimisticLockException("Entity outdated");
            }
            Header eTag = response.getFirstHeader(HttpHeaders.ETAG);
            return new VersionUid(eTag.getValue().replace("\"", ""));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    static <T> Optional<T> httpGet(URI uri, Class<T> valueType) {
        try {
            HttpResponse response = Request.Get(uri)
                    .addHeader(HttpHeaders.ACCEPT, ACCEPT_APPLICATION_JSON)
                    .execute().returnResponse();
            checkStatus(response, HttpStatus.SC_OK, HttpStatus.SC_NOT_FOUND);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_FOUND) {
                return Optional.empty();
            }
            String value = EntityUtils.toString(response.getEntity());
            return Optional.of(OBJECT_MAPPER.readValue(value, valueType));
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    static void checkStatus(HttpResponse httpResponse, int... expected) {
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

            throw new WrongStatusCodeException(message, httpResponse.getStatusLine().getStatusCode(), expected);
        }
    }

    OpenEhrClientConfig getConfig() {
        return config;
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
    public FolderDAO folder(UUID ehrId, String path) {
        return directoryEndpointMap.computeIfAbsent(ehrId, k -> new DefaultRestDirectoryEndpoint(this, k)).getFolder(path);
    }

    @Override
    public TemplateEndpoint templateEndpoint() {
        return new DefaultRestTemplateEndpoint(this);
    }

    @Override
    public AqlEndpoint aqlEndpoint() {
        return new DefaultRestAqlEndpoint(this);
    }
}
