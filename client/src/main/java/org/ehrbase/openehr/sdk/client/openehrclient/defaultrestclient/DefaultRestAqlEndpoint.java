/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.ehrbase.openehr.sdk.client.openehrclient.AqlEndpoint;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.ParameterValue;
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.StoredQueryParameter;
import org.ehrbase.openehr.sdk.generator.commons.aql.query.Query;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.Record;
import org.ehrbase.openehr.sdk.generator.commons.aql.record.RecordImp;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;
import org.ehrbase.openehr.sdk.response.dto.QueryResponseData;
import org.ehrbase.openehr.sdk.response.dto.StoredQueryResponseData;
import org.ehrbase.openehr.sdk.serialisation.dto.RmToGeneratedDtoConverter;
import org.ehrbase.openehr.sdk.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.openehr.sdk.util.exception.ClientException;
import org.ehrbase.openehr.sdk.webtemplate.templateprovider.TemplateProvider;

public class DefaultRestAqlEndpoint implements AqlEndpoint {

    public static final String AQL_PATH = "rest/openehr/v1/query/aql/";
    public static final String AQL_STORED_QUERY_PATH = "rest/openehr/v1/query/";
    public static final String STORE_AQL_QUERY_PATH = "rest/openehr/v1/definition/query/";
    public static final String QUERY_MAP_KEY = "q";
    public static final ObjectMapper AQL_OBJECT_MAPPER = buildAqlObjectMapper();

    private final DefaultRestClient defaultRestClient;

    private static final String INVALID_QUERY_ERROR_STRING = "Invalid query";
    private static final String INVALID_PARAMETERS_ERROR_STRING = "Invalid parameters";

    public DefaultRestAqlEndpoint(DefaultRestClient defaultRestClient) {
        this.defaultRestClient = defaultRestClient;
    }

    private static ObjectMapper buildAqlObjectMapper() {
        ObjectMapper objectMapper = ArchieObjectMapperProvider.getObjectMapper().copy();
        SimpleModule module = new SimpleModule("openEHR", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(TemporalAccessor.class, new TemporalAccessorDeSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }

    @Override
    public <T extends Record> List<T> execute(Query<T> query, ParameterValue... parameterValues) {

        QueryResponseData queryResponseData = executeRaw(query, parameterValues);

        List<List<Object>> dataRows = queryResponseData.getRows();
        if (CollectionUtils.isEmpty(dataRows)) {
            return new ArrayList<>();
        }

        return dataRows.stream()
                .map(row -> {
                    AqlField<Object>[] fields = query.fields();
                    RecordImp record = new RecordImp(fields);
                    try {
                        for (int i = 0; i < fields.length; i++) {
                            AqlField<?> aqlField = fields[i];
                            Object cell = row.get(i);
                            var valueAsString = AQL_OBJECT_MAPPER.writeValueAsString(cell);
                            final Object object;

                            if (aqlField instanceof ListSelectAqlField<?> listField) {
                                List<Object> list = new ArrayList<>();
                                list.add(extractValue(valueAsString, listField.getInnerClass()));
                                object = list;

                            } else {
                                object = extractValue(valueAsString, aqlField.getValueClass());
                            }
                            record.putValue(i, object);
                        }
                    } catch (JacksonException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                    return (T) record;
                })
                .collect(Collectors.toList());
    }

    @Override
    public QueryResponseData executeRaw(Query query, ParameterValue... parameterValues) {

        if (query == null) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        if (parameterValues == null) {
            throw new ClientException(INVALID_PARAMETERS_ERROR_STRING);
        }

        String aql = query.buildAql();

        if (StringUtils.isEmpty(aql)) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        for (ParameterValue<?> v : parameterValues) {
            aql = aql.replace(v.getParameter().getAqlParameter(), v.buildAql());
        }

        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(AQL_PATH);

        Map<String, String> qMap = new LinkedHashMap<>();
        qMap.put(QUERY_MAP_KEY, aql);

        try {
            HttpResponse response = defaultRestClient.internalPost(
                    uri,
                    Collections.emptyMap(),
                    DefaultRestClient.OBJECT_MAPPER.writeValueAsString(qMap),
                    ContentType.APPLICATION_JSON,
                    ContentType.APPLICATION_JSON.getMimeType());

            String responseJson = EntityUtils.toString(response.getEntity());

            return DefaultRestClient.OBJECT_MAPPER.readValue(responseJson, QueryResponseData.class);

        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public QueryResponseData executeStoredQuery(StoredQueryParameter queryParameter) {

        if (queryParameter == null || !queryParameter.isValid()) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        URIBuilder uriBuilder = getBaseUriBuilder()
                .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                        + AQL_STORED_QUERY_PATH
                        + queryParameter.getPath());

        queryParameter.getOffset().ifPresent(value -> uriBuilder.addParameter("offset", value.toString()));

        queryParameter.getFetch().ifPresent(value -> uriBuilder.addParameter("fetch", value.toString()));

        for (Map.Entry<String, String> param : queryParameter.getQueryParams().entrySet()) {
            uriBuilder.addParameter(param.getKey(), param.getValue());
        }

        try {
            HttpResponse response = defaultRestClient.internalGet(
                    uriBuilder.build(), Collections.emptyMap(), ContentType.APPLICATION_JSON.getMimeType());

            String responseJson = EntityUtils.toString(response.getEntity());

            return DefaultRestClient.OBJECT_MAPPER.readValue(responseJson, QueryResponseData.class);
        } catch (IOException | URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public StoredQueryResponseData getStoredAqlQuery(StoredQueryParameter queryParameter) {
        if (queryParameter == null || !queryParameter.isValid()) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        URIBuilder uriBuilder = getBaseUriBuilder()
                .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                        + AQL_STORED_QUERY_PATH
                        + queryParameter.getPath());

        try {
            HttpResponse response = defaultRestClient.internalGet(
                    uriBuilder.build(), Collections.emptyMap(), ContentType.APPLICATION_JSON.getMimeType());

            String responseJson = EntityUtils.toString(response.getEntity());

            return DefaultRestClient.OBJECT_MAPPER.readValue(responseJson, StoredQueryResponseData.class);
        } catch (IOException | URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    @Override
    public void storeAqlQuery(Query query, StoredQueryParameter queryParameter) {

        if (query == null) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        if (queryParameter == null || !queryParameter.isValid()) {
            throw new ClientException(INVALID_PARAMETERS_ERROR_STRING);
        }

        String body;
        try {
            body = AQL_OBJECT_MAPPER.writeValueAsString(Map.of("q", query.buildAql()));
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
        URIBuilder uriBuilder = getBaseUriBuilder()
                .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                        + STORE_AQL_QUERY_PATH
                        + queryParameter.getPath());

        queryParameter.getType().ifPresent(type -> uriBuilder.addParameter("type", type));

        try {
            defaultRestClient.internalPut(
                    uriBuilder.build(),
                    Collections.emptyMap(),
                    body,
                    ContentType.APPLICATION_JSON,
                    ContentType.APPLICATION_JSON.getMimeType());

        } catch (URISyntaxException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    private URIBuilder getBaseUriBuilder() {
        URI baseUri = defaultRestClient.getConfig().getBaseUri();

        return new URIBuilder()
                .setScheme(baseUri.getScheme())
                .setHost(baseUri.getHost())
                .setPort(baseUri.getPort());
    }

    private Object extractValue(String valueAsString, Class<?> aClass) throws JsonProcessingException {
        Object object;

        if (StringUtils.isBlank(valueAsString) || "null".equals(valueAsString)) {
            object = null;
        } else if (aClass.isAnnotationPresent(Entity.class)) {
            RMObject locatable = AQL_OBJECT_MAPPER.readValue(valueAsString, RMObject.class);
            object = createFlattener(defaultRestClient.getTemplateProvider()).toGeneratedDto(locatable, aClass);
            if (locatable instanceof Composition comp) {
                RmToGeneratedDtoConverter.addVersion(
                        object, new ObjectVersionId(comp.getUid().getValue()));
            }
        } else if (EnumValueSet.class.isAssignableFrom(aClass)) {
            RMObject rmObject = AQL_OBJECT_MAPPER.readValue(valueAsString, RMObject.class);
            final String codeString;
            if (rmObject instanceof CodePhrase codePhrase) {
                codeString = codePhrase.getCodeString();
            } else {
                codeString = ((DvCodedText) rmObject).getDefiningCode().getCodeString();
            }
            object = Arrays.stream(aClass.getEnumConstants())
                    .map(e -> (EnumValueSet) e)
                    .filter(e -> e.getCode().equals(codeString))
                    .findAny()
                    .orElseThrow(() -> new ClientException(
                            String.format("Unknown code %s for %s", codeString, aClass.getSimpleName())));
        } else {
            object = AQL_OBJECT_MAPPER.readValue(valueAsString, aClass);
        }
        return object;
    }

    protected RmToGeneratedDtoConverter createFlattener(TemplateProvider templateProvider) {
        return new RmToGeneratedDtoConverter(templateProvider);
    }
}
