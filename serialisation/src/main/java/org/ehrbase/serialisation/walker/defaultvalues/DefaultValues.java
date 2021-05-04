/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nedap.archie.datetime.DateTimeParsers;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.PartyRef;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.classgenerator.shareddefinition.ParticipationMode;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.util.exception.SdkException;

public class DefaultValues {

  private final Map<DefaultValuePath, Object> defaultValueMap;
  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  public DefaultValues() {
    defaultValueMap = new HashMap<>();
  }

  public DefaultValues(Map<String, String> flat) {
    defaultValueMap = new HashMap<>();

    Stream.of(
            DefaultValuePath.LANGUAGE,
            DefaultValuePath.TERRITORY,
            DefaultValuePath.COMPOSER_NAME,
            DefaultValuePath.COMPOSER_ID,
            DefaultValuePath.ID_SCHEME,
            DefaultValuePath.ID_NAMESPACE,
            DefaultValuePath.COMPOSER_SELF,
            DefaultValuePath.TIME,
            DefaultValuePath.END_TIME,
            DefaultValuePath.HISTORY_ORIGIN,
            DefaultValuePath.ACTION_TIME,
            DefaultValuePath.ACTIVITY_TIMING,
            DefaultValuePath.PROVIDER_ID,
            DefaultValuePath.PROVIDER_NAME,
            DefaultValuePath.HEALTHCARE_FACILITY_NAME,
            DefaultValuePath.HEALTHCARE_FACILITY_ID,
            DefaultValuePath.ACTION_ISM_TRANSITION_CURRENT_STATE,
            DefaultValuePath.INSTRUCTION_NARRATIVE,
            DefaultValuePath.LOCATION,
            DefaultValuePath.SETTING,
            DefaultValuePath.PARTICIPATION)
        .forEach(
            path -> {
              Map<String, String> subValues = filter(flat, path.getPath());
              if (!subValues.isEmpty()) {
                if (EnumValueSet.class.isAssignableFrom(path.getType())) {
                  String value =
                      subValues.values().stream().map(DefaultValues::read).findAny().orElseThrow();
                  defaultValueMap.put(
                      path, findEnumValue(value, (Class<? extends EnumValueSet>) path.getType()));

                } else if (String.class.isAssignableFrom(path.getType())) {
                  String value =
                      subValues.values().stream().map(DefaultValues::read).findAny().orElseThrow();
                  defaultValueMap.put(path, value);

                } else if (Boolean.class.isAssignableFrom(path.getType())) {
                  String value =
                      subValues.values().stream().map(DefaultValues::read).findAny().orElseThrow();
                  if (value.equals("true")) {
                    defaultValueMap.put(path, value);
                  }
                } else if (TemporalAccessor.class.isAssignableFrom(path.getType())) {
                  String value =
                      subValues.values().stream().map(DefaultValues::read).findAny().orElseThrow();
                  defaultValueMap.put(path, DateTimeParsers.parseDateTimeValue(value));
                } else if (List.class.isAssignableFrom(path.getType())) {
                  Map<Integer, List<Map.Entry<String, String>>> byIndex =
                      subValues.entrySet().stream()
                          .collect(
                              Collectors.groupingBy(
                                  e -> {
                                    String s =
                                        StringUtils.substringBefore(
                                            StringUtils.substringAfter(e.getKey(), ":"), "|");
                                    return StringUtils.isBlank(s) ? 0 : Integer.parseInt(s);
                                  }));
                  List<Participation> participations = new ArrayList<>();
                  byIndex.values().stream()
                      .map(this::buildParticipation)
                      .forEach(participations::add);

                  defaultValueMap.put(path, participations);
                }
              }

              if (defaultValueMap.containsKey(DefaultValuePath.PARTICIPATION)) {
                getDefaultValue(DefaultValuePath.PARTICIPATION).stream()
                    .map(Participation::getPerformer)
                    .map(PartyProxy::getExternalRef)
                    .filter(Objects::nonNull)
                    .filter(ref -> ref.getId() != null)
                    .forEach(
                        ref -> {
                          ref.setNamespace(getDefaultValue(DefaultValuePath.ID_NAMESPACE));
                          ((GenericId) ref.getId())
                              .setScheme(getDefaultValue(DefaultValuePath.ID_SCHEME));
                        });
              }
            });
  }

  private Map<String, String> filter(Map<String, String> flat, String path) {
    return flat.entrySet().stream()
        .filter(e -> StringUtils.startsWith(e.getKey(), "ctx/" + path))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private Participation buildParticipation(List<Map.Entry<String, String>> subValues) {
    Participation participation = new Participation();
    participation.setPerformer(new PartyIdentified());

    extract(
        subValues,
        "id",
        s -> {
          participation.getPerformer().setExternalRef(new PartyRef());
          GenericId id = new GenericId();
          id.setValue(s);
          participation.getPerformer().getExternalRef().setId(id);
        });

    extract(subValues, "name", ((PartyIdentified) participation.getPerformer())::setName);
    extract(subValues, "function", s -> participation.setFunction(new DvText(s)));
    extract(
        subValues,
        "mode",
        s -> {
          ParticipationMode participationMode = findEnumValue(s, ParticipationMode.class);
          participation.setMode(new DvCodedText());
          participation.getMode().setValue(participationMode.getValue());
          participation.getMode().setDefiningCode(participationMode.toCodePhrase());
        });
    ((PartyIdentified) participation.getPerformer())
        .setIdentifiers(
            splitByIndex(
                    filter(
                        subValues.stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
                        DefaultValuePath.PARTICIPATION.getPath() + "_" + "identifiers"))
                .values().stream()
                .map(this::toDvIdentifier)
                .collect(Collectors.toList()));
    return participation;
  }

  private DvIdentifier toDvIdentifier(Map<String, String> valueMap) {
    DvIdentifier dvIdentifier = new DvIdentifier();

    dvIdentifier.setId(valueMap.get("id"));
    dvIdentifier.setAssigner(valueMap.get("assigner"));
    dvIdentifier.setIssuer(valueMap.get("issuer"));
    dvIdentifier.setType(valueMap.get("type"));

    return dvIdentifier;
  }

  private Map<Integer, Map<String, String>> splitByIndex(Map<String, String> values) {
    Map<Integer, Map<String, String>> map;

    if (values.size() == 1) {
      map = new HashMap<>();
      String ids = StringUtils.unwrap(values.values().stream().findAny().orElseThrow(), '"');
      int i = 0;
      for (String sub : ids.split(";")) {
        map.put(i, new HashMap<>());
        String[] split = sub.split("::");

        map.get(i).put("issuer", split[0]);
        map.get(i).put("assigner", split[1]);
        map.get(i).put("id", split[2]);
        map.get(i).put("type", split[3]);

        i++;
      }
    } else if (values.size() > 1) {

      map =
          values.entrySet().stream()
              .collect(
                  Collectors.groupingBy(
                      e -> {
                        String s =
                            StringUtils.substringAfter(
                                StringUtils.substringAfter(e.getKey(), "|"), ":");
                        return StringUtils.isBlank(s) ? 0 : Integer.parseInt(s);
                      },
                      Collectors.toMap(
                          e ->
                              StringUtils.substringBefore(
                                  StringUtils.substringAfter(e.getKey(), "|"), ":"),
                          stringStringEntry ->
                              StringUtils.unwrap(stringStringEntry.getValue(), '"'))));

    } else {
      map = Collections.emptyMap();
    }
    return map;
  }

  private void extract(
      List<Map.Entry<String, String>> subValues, String subPath, Consumer<String> stringConsumer) {
    filter(
            subValues.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
            DefaultValuePath.PARTICIPATION.getPath() + "_" + subPath)
        .values().stream()
        .map(DefaultValues::read)
        .findAny()
        .ifPresent(stringConsumer);
  }

  private <E extends EnumValueSet> E findEnumValue(String value, Class<E> clazz) {

    return Arrays.stream(clazz.getEnumConstants())
        .filter(e -> e.getCode().equals(value) || e.getValue().equals(value))
        .findAny()
        .orElseThrow(
            () ->
                new SdkException(
                    String.format(
                        "Unknown Value %s in terminology %s",
                        value, clazz.getEnumConstants()[0].getTerminologyId())));
  }

  private static String read(String s) {
    try {
      return OBJECT_MAPPER.readValue(s, String.class);
    } catch (JsonProcessingException jsonProcessingException) {
      throw new SdkException(jsonProcessingException.getMessage());
    }
  }

  public <T> void addDefaultValue(DefaultValuePath<T> path, T value) {

    if (value == null) {

      defaultValueMap.remove(path);
    } else if (path.getType().isAssignableFrom(value.getClass())) {
      defaultValueMap.put(path, value);
    } else {
      throw new SdkException(
          String.format(
              "Can not set %s can not cast %s to %s",
              path, path.getType().getSimpleName(), value.getClass().getSimpleName()));
    }
  }

  public void removeDefaultValue(DefaultValuePath path) {
    defaultValueMap.remove(path);
  }

  public <T> T getDefaultValue(DefaultValuePath<T> path) {
    return (T) defaultValueMap.get(path);
  }

  public boolean containsDefaultValue(DefaultValuePath<?> path) {
    return defaultValueMap.containsKey(path);
  }
}
