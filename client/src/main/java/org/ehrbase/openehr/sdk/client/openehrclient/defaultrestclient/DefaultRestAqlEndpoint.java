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
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
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
import org.ehrbase.openehr.sdk.generator.commons.aql.parameter.StoredQueryDefinition;
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
import org.jspecify.annotations.NonNull;

public class DefaultRestAqlEndpoint implements AqlEndpoint {

    public static final String AQL_PATH = "rest/openehr/v1/query/aql/";
    public static final String AQL_STORED_QUERY_PATH = "rest/openehr/v1/query/";
    public static final String STORE_AQL_QUERY_PATH = "rest/openehr/v1/definition/query/";
    public static final String QUERY_KEY = "q";
    public static final String PARAMETERS_KEY = "query_parameters";
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
    public <T extends Record> List<T> execute(Query<T> query, ParameterValue<?>... parameterValues) {
        return toRecords(query, executeRaw(query, parameterValues));
    }

    @Override
    public QueryResponseData executeRaw(Query<?> query, ParameterValue<?>... parameterValues) {
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

        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(AQL_PATH);

        ObjectNode reqBody = DefaultRestClient.OBJECT_MAPPER.createObjectNode();
        reqBody.put(QUERY_KEY, aql);

        if (ArrayUtils.isNotEmpty(parameterValues)) {
            addQueryParameters(reqBody, parameterValues);
        }

        return postQuery(uri, reqBody);
    }

    @Override
    public QueryResponseData executeStoredQuery(StoredQueryParameter queryParameter) {
        if (queryParameter == null || !queryParameter.isValid()) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        URI uri = defaultRestClient.getConfig().getBaseUri().resolve(AQL_STORED_QUERY_PATH + queryParameter.getPath());

        ObjectNode reqBody = DefaultRestClient.OBJECT_MAPPER.createObjectNode();

        queryParameter.getOffset().ifPresent(value -> reqBody.put("offset", value));
        queryParameter.getFetch().ifPresent(value -> reqBody.put("fetch", value));

        if (CollectionUtils.isNotEmpty(queryParameter.getQueryParams())) {
            addQueryParameters(reqBody, queryParameter.getQueryParams().toArray(ParameterValue[]::new));
        }

        return postQuery(uri, reqBody);
    }

    private QueryResponseData postQuery(URI uri, ObjectNode body) {
        try {
            HttpResponse response = defaultRestClient.internalPost(
                    uri,
                    Collections.emptyMap(),
                    DefaultRestClient.OBJECT_MAPPER.writeValueAsString(body),
                    ContentType.APPLICATION_JSON,
                    ContentType.APPLICATION_JSON.getMimeType());

            String responseJson = EntityUtils.toString(response.getEntity());

            return DefaultRestClient.OBJECT_MAPPER.readValue(responseJson, QueryResponseData.class);
        } catch (IOException e) {
            throw new ClientException(e.getMessage(), e);
        }
    }

    public <T extends Record> @NonNull List<T> toRecords(Query<T> query, QueryResponseData queryResponseData) {
        List<List<Object>> dataRows = queryResponseData.getRows();
        if (CollectionUtils.isEmpty(dataRows)) {
            return new ArrayList<>();
        }

        return dataRows.stream()
                .map(row -> {
                    AqlField<Object>[] fields = query.fields();
                    RecordImp rec = new RecordImp(fields);
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
                            rec.putValue(i, object);
                        }
                    } catch (JacksonException e) {
                        throw new ClientException(e.getMessage(), e);
                    }
                    return (T) rec;
                })
                .collect(Collectors.toList());
    }

    private static void addQueryParameters(ObjectNode reqBody, ParameterValue<?>... parameterValues) {
        ObjectNode params = (ObjectNode) reqBody.get(PARAMETERS_KEY);
        if (params == null) {
            params = reqBody.objectNode();
            reqBody.set(PARAMETERS_KEY, params);
        }

        JsonNode valueNode;
        for (ParameterValue<?> parameterValue : parameterValues) {
            Object rawValue = parameterValue.getValue();

            valueNode = toValueNode(rawValue, params);

            String name = parameterValue.getParameter().getAqlParameter().substring(1);
            JsonNode existingParamValue = params.get(name);
            if (existingParamValue == null) {
                params.set(name, valueNode);
            } else {
                // duplicate param: add as list
                ArrayNode list;
                if (existingParamValue.isArray()) {
                    list = (ArrayNode) existingParamValue;
                } else {
                    list = params.arrayNode();
                    list.add(existingParamValue);
                    params.set(name, list);
                }
                list.add(valueNode);
            }
        }
    }

    private static JsonNode toValueNode(Object rawValue, JsonNodeCreator creator) {
        JsonNode valueNode;
        if (rawValue instanceof BigInteger value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof BigDecimal value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Byte value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Short value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Integer value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Long value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Float value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof Double value) {
            valueNode = creator.numberNode(value);
        } else if (rawValue instanceof String value) {
            valueNode = creator.textNode(value);
        } else if (rawValue instanceof Collection<?> values) {
            ArrayNode list = creator.arrayNode();
            for (Object value : values) {
                JsonNode node = toValueNode(value, creator);
                if (node.isArray()) {
                    list.addAll((ArrayNode) node);
                } else {
                    list.add(node);
                }
            }
            valueNode = list;
        } else if (rawValue.getClass().isArray()) {
            valueNode = toValueNode(Arrays.asList((Object[]) rawValue), creator);
        } else {
            valueNode = creator.textNode(rawValue.toString());
        }
        return valueNode;
    }

    @Override
    public StoredQueryResponseData getStoredAqlQuery(StoredQueryDefinition queryParameter) {
        if (queryParameter == null || !queryParameter.isValid()) {
            throw new ClientException(INVALID_QUERY_ERROR_STRING);
        }

        URIBuilder uriBuilder = getBaseUriBuilder()
                .setPath(defaultRestClient.getConfig().getBaseUri().getPath()
                        + STORE_AQL_QUERY_PATH
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
    public void storeAqlQuery(Query<?> query, StoredQueryDefinition queryParameter) {

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
