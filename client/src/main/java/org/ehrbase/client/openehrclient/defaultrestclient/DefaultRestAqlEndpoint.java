/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.openehrclient.defaultrestclient;

import static org.ehrbase.client.openehrclient.defaultrestclient.DefaultRestClient.OBJECT_MAPPER;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.io.IOException;
import java.net.URI;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ContentType;
import org.apache.http.util.EntityUtils;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.parameter.ParameterValue;
import org.ehrbase.client.aql.query.Query;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.client.aql.record.RecordImp;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.exception.ClientException;
import org.ehrbase.client.flattener.Flattener;
import org.ehrbase.client.openehrclient.AqlEndpoint;
import org.ehrbase.client.openehrclient.VersionUid;
import org.ehrbase.response.openehr.QueryResponseData;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;

public class DefaultRestAqlEndpoint implements AqlEndpoint {

  public static final String AQL_PATH = "rest/openehr/v1/query/aql/";
  public static final String QUERY_MAP_KEY = "q";
  public static final ObjectMapper AQL_OBJECT_MAPPER = buildAqlObjectMapper();

  private final DefaultRestClient defaultRestClient;

  public DefaultRestAqlEndpoint(DefaultRestClient defaultRestClient) {
    this.defaultRestClient = defaultRestClient;
  }

  private static ObjectMapper buildAqlObjectMapper() {
    ObjectMapper objectMapper = ArchieObjectMapperProvider.getObjectMapper().copy();
    SimpleModule module = new SimpleModule("openEHR", new Version(1, 0, 0, null, null, null));
    module.addDeserializer(VersionUid.class, new VersionUidDeSerializer());
    module.addDeserializer(TemporalAccessor.class, new TemporalAccessorDeSerializer());
    objectMapper.registerModule(module);
    return objectMapper;
  }

  @Override
  public <T extends Record> List<T> execute(Query<T> query, ParameterValue... parameterValues) {
    List<T> result = new ArrayList<>();
    Map<String, String> qMap = new HashMap<>();
    String aql = query.buildAql();
    for (ParameterValue v : parameterValues) {
      aql = aql.replace(v.getParameter().getAqlParameter(), v.buildAql());
    }

    qMap.put(QUERY_MAP_KEY, aql);
    URI uri = defaultRestClient.getConfig().getBaseUri().resolve(AQL_PATH);
    try {

      HttpResponse response =
          defaultRestClient.internalPost(
              uri,
              null,
              OBJECT_MAPPER.writeValueAsString(qMap),
              ContentType.APPLICATION_JSON,
              ContentType.APPLICATION_JSON.getMimeType());
      String value = EntityUtils.toString(response.getEntity());
      JsonObject asJsonObject = JsonParser.parseString(value).getAsJsonObject();
      JsonArray rows = asJsonObject.get("rows").getAsJsonArray();
      for (JsonElement jresult : rows) {
        RecordImp record = new RecordImp(query.fields());
        int i = 0;
        for (AqlField<?> aqlField : query.fields()) {
          String valueAsString = ((JsonArray) jresult).get(i).toString();

          final Object object;

          Class<?> aClass = aqlField.getValueClass();
          if (ListSelectAqlField.class.isAssignableFrom(aqlField.getClass())) {
            List list = new ArrayList();
            object = list;
            // @TODO how to handle list values results like
            // content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]
            //      for (JsonElement element :
            // JsonParser.parseString(valueAsString).getAsJsonObject().get("items").getAsJsonArray()) {
            list.add(extractValue(valueAsString, ((ListSelectAqlField) aqlField).getInnerClass()));
            //    }

          } else {
            object = extractValue(valueAsString, aClass);
          }

          record.putValue(i, object);
          i++;
        }
        result.add((T) record);
      }

    } catch (IOException e) {
      throw new ClientException(e.getMessage(), e);
    }
    return result;
  }

  @Override
  public QueryResponseData executeRaw(Query query, ParameterValue... parameters) {

    if (query == null) {
      throw new ClientException("Invalid query");
    }

    if (parameters == null) {
      throw new ClientException("Invalid parameters");
    }

    String queryString = query.buildAql();

    if (StringUtils.isEmpty(queryString)) {
      throw new ClientException("Invalid query");
    }

    for (ParameterValue v : parameters) {
      queryString = queryString.replace(v.getParameter().getAqlParameter(), v.buildAql());
    }

    URI uri = defaultRestClient.getConfig().getBaseUri().resolve(AQL_PATH);

    try {
      String body = OBJECT_MAPPER.writeValueAsString(Map.of(QUERY_MAP_KEY, queryString));
      HttpResponse response =
          defaultRestClient
              .internalPost(uri, Collections.emptyMap(), body, ContentType.APPLICATION_JSON,
                  ContentType.APPLICATION_JSON.getMimeType());

      String responseJson = EntityUtils.toString(response.getEntity());

      return DefaultRestClient.OBJECT_MAPPER.readValue(responseJson, QueryResponseData.class);

    } catch (IOException e) {
      throw new ClientException(e.getMessage(), e);
    }
  }

  private Object extractValue(String valueAsString, Class<?> aClass)
      throws com.fasterxml.jackson.core.JsonProcessingException {
    Object object;

    if (StringUtils.isBlank(valueAsString) || "null".equals(valueAsString)) {
      object = null;
    } else if (aClass.isAnnotationPresent(Entity.class)) {
      RMObject locatable = AQL_OBJECT_MAPPER.readValue(valueAsString, RMObject.class);
      object = new Flattener(defaultRestClient.getTemplateProvider()).flatten(locatable, aClass);
      if (locatable instanceof Composition) {
        Flattener.addVersion(object, new VersionUid(((Composition) locatable).getUid().getValue()));
      }
    } else if (EnumValueSet.class.isAssignableFrom(aClass)) {
      RMObject rmObject = AQL_OBJECT_MAPPER.readValue(valueAsString, RMObject.class);
      final String codeString;
      if (CodePhrase.class.isAssignableFrom(rmObject.getClass())) {
        codeString = ((CodePhrase) rmObject).getCodeString();
      } else {
        codeString = ((DvCodedText) rmObject).getDefiningCode().getCodeString();
      }
      object =
          Arrays.stream(aClass.getEnumConstants())
              .map(e -> (EnumValueSet) e)
              .filter(e -> e.getCode().equals(codeString))
              .findAny()
              .orElseThrow(
                  () ->
                      new ClientException(
                          String.format(
                              "Unknown code %s for %s", codeString, aClass.getSimpleName())));
    } else {
      object = AQL_OBJECT_MAPPER.readValue(valueAsString, aClass);
    }
    return object;
  }
}
