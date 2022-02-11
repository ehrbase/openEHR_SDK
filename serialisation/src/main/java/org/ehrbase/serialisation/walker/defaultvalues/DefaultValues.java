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
import com.nedap.archie.rm.archetyped.Link;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvEHRURI;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.generic.*;
import com.nedap.archie.rm.support.identification.GenericId;
import com.nedap.archie.rm.support.identification.ObjectRef;
import com.nedap.archie.rm.support.identification.PartyRef;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.classgenerator.shareddefinition.ParticipationMode;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.State;
import org.ehrbase.serialisation.jsonencoding.JacksonUtil;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.ehrbase.serialisation.walker.FlatHelper.*;

public class DefaultValues {

  private final Map<DefaultValuePath, Object> defaultValueMap;
  private static final ObjectMapper OBJECT_MAPPER = JacksonUtil.getObjectMapper();

  private final Logger log = LoggerFactory.getLogger(getClass());

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
            DefaultValuePath.PARTICIPATION,
            DefaultValuePath.WORKFLOW_ID,
            DefaultValuePath.LINKS)
        .forEach(
            path -> {
              Map<String, String> subValues = filter(flat, path.getPath());

              if (!subValues.isEmpty()) {
                if (EnumValueSet.class.isAssignableFrom(path.getType())) {
                  String value =
                      subValues.values().stream().map(DefaultValues::read).findAny().orElseThrow();
                  defaultValueMap.put(
                      path,
                      findEnumValueOrThrow(value, (Class<? extends EnumValueSet>) path.getType()));

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
                  if ("now".equals(value)) {
                    defaultValueMap.put(path, OffsetDateTime.now());
                  } else {
                    defaultValueMap.put(path, DateTimeParsers.parseDateTimeValue(value));
                  }
                } else if (path.equals(DefaultValuePath.PARTICIPATION)) {
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
                      .map(DefaultValues::buildParticipation)
                      .forEach(participations::add);

                  defaultValueMap.put(path, participations);
                } else if (path.equals(DefaultValuePath.WORKFLOW_ID)) {
                  ObjectRef<GenericId> ref = new ObjectRef<>();

                  Map<String, String> attributes =
                      subValues.entrySet().stream()
                          .collect(
                              Collectors.toMap(
                                  e1 ->
                                      StringUtils.substringBefore(
                                          StringUtils.substringAfter(e1.getKey(), "|"), ":"),
                                  stringStringEntry ->
                                      StringUtils.unwrap(stringStringEntry.getValue(), '"')));

                  ref.setNamespace(attributes.get("namespace"));
                  ref.setType(attributes.get("type"));
                  ref.setId(new GenericId());
                  ref.getId().setValue(attributes.get("id"));
                  ref.getId().setScheme(attributes.get("id_scheme"));

                  defaultValueMap.put(path, ref);
                } else if (path.equals(DefaultValuePath.LINKS)) {

                  Map<Integer, Map<String, String>> links =
                      subValues.entrySet().stream()
                          .collect(
                              Collectors.groupingBy(
                                  e -> {
                                    String s =
                                        StringUtils.substringBefore(
                                            StringUtils.substringAfter(e.getKey(), ":"), "|");
                                    return StringUtils.isBlank(s) ? 0 : Integer.parseInt(s);
                                  },
                                  Collectors.toMap(
                                      e1 ->
                                          StringUtils.substringBefore(
                                              StringUtils.substringAfter(e1.getKey(), "|"), ":"),
                                      stringStringEntry ->
                                          StringUtils.unwrap(stringStringEntry.getValue(), '"'))));

                  defaultValueMap.put(
                      path,
                      links.values().stream()
                          .map(DefaultValues::createLink)
                          .collect(Collectors.toList()));
                }
              }

              setFlatDefaults();
            });
  }

  private void setFlatDefaults() {

    if (defaultValueMap.containsKey(DefaultValuePath.PARTICIPATION)) {
      getDefaultValue(DefaultValuePath.PARTICIPATION).stream()
          .map(Participation::getPerformer)
          .map(PartyProxy::getExternalRef)
          .filter(Objects::nonNull)
          .filter(ref -> ref.getId() != null)
          .forEach(
              ref -> {
                ref.setNamespace(getDefaultValue(DefaultValuePath.ID_NAMESPACE));
                ((GenericId) ref.getId()).setScheme(getDefaultValue(DefaultValuePath.ID_SCHEME));
              });
    }

    if (defaultValueMap.containsKey(DefaultValuePath.WORKFLOW_ID)) {
      if (((GenericId) getDefaultValue(DefaultValuePath.WORKFLOW_ID).getId()).getScheme() == null) {
        ((GenericId) getDefaultValue(DefaultValuePath.WORKFLOW_ID).getId())
            .setScheme(getDefaultValue(DefaultValuePath.ID_SCHEME));
      }
      if (getDefaultValue(DefaultValuePath.WORKFLOW_ID).getNamespace() == null) {
        getDefaultValue(DefaultValuePath.WORKFLOW_ID)
            .setNamespace(getDefaultValue(DefaultValuePath.ID_NAMESPACE));
      }
    }

    defaultValueMap.putIfAbsent(DefaultValuePath.TIME, OffsetDateTime.now());
    defaultValueMap.putIfAbsent(
        DefaultValuePath.ACTION_ISM_TRANSITION_CURRENT_STATE, State.COMPLETED);
    defaultValueMap.putIfAbsent(DefaultValuePath.SETTING, Setting.OTHER_CARE);
    defaultValueMap.putIfAbsent(DefaultValuePath.ACTIVITY_TIMING, "R1");
    defaultValueMap.putIfAbsent(DefaultValuePath.INSTRUCTION_NARRATIVE, "<none>");
  }

  public static Link createLink(Map<String, String> stringStringMap) {
    Link link = new Link();
    if (stringStringMap.containsKey("meaning")) {
      link.setMeaning(new DvText(stringStringMap.get("meaning")));
    }
    if (stringStringMap.containsKey("type")) {
      link.setType(new DvText(stringStringMap.get("type")));
    }
    if (stringStringMap.containsKey("target")) {
      link.setTarget(new DvEHRURI(stringStringMap.get("target")));
    }

    return link;
  }

  public static Link createLink(Map<FlatPathDto, String> valueMap, String path) {
    Link link = new Link();

    setValue(
        path,
        "meaning",
        valueMap,
        s -> {
          if (s != null) {
            link.setMeaning(new DvText(s));
          }
        },
        String.class);

    setValue(
        path,
        "type",
        valueMap,
        s -> {
          if (s != null) {
            link.setType(new DvText(s));
          }
        },
        String.class);

    setValue(
        path,
        "target",
        valueMap,
        s -> {
          if (s != null) {
            link.setTarget(new DvEHRURI(s));
          }
        },
        String.class);

    return link;
  }

  private static Map<String, String> filter(Map<String, String> flat, String path) {
    return flat.entrySet().stream()
        .filter(e -> StringUtils.startsWith(e.getKey(), "ctx/" + path))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private static Map<String, String> filterExact(Map<String, String> flat, String path) {
    return flat.entrySet().stream()
        .filter(e -> new FlatPathDto(e.getKey()).startsWith("ctx/" + path))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }



  public static Participation buildParticipation(Collection<Map.Entry<String, String>> subValues) {
    Participation participation = new Participation();
    participation.setPerformer(new PartyIdentified());

    extractExact(
        subValues,
        "id",
        s -> {
          participation.getPerformer().setExternalRef(new PartyRef());
          participation.getPerformer().getExternalRef().setType("PARTY");
          GenericId id = new GenericId();
          id.setValue(s);
          participation.getPerformer().getExternalRef().setId(id);
        });



    extract(subValues, "name", ((PartyIdentified) participation.getPerformer())::setName);

    if (participation.getPerformer().getExternalRef() != null) {
      extract(
          subValues,
          "id_namespace",
          n -> participation.getPerformer().getExternalRef().setNamespace(n));
      extract(
          subValues,
          "id_scheme",
          ((GenericId) participation.getPerformer().getExternalRef().getId())::setScheme);
    }

    extract(subValues, "function", s -> participation.setFunction(new DvText(s)));

    extract(
        subValues,
        "mode",
        s -> {
          ParticipationMode participationMode = findEnumValueOrThrow(s, ParticipationMode.class);
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
                .values()
                .stream()
                .map(DefaultValues::toDvIdentifier)
                .collect(Collectors.toList()));
    return participation;
  }

  public static Participation buildParticipation(Map<FlatPathDto, String> subValues, String path) {
    Participation participation = new Participation();

    if (isExactlyPartyIdentified(subValues, path)) {
      participation.setPerformer(new PartyIdentified());
    } else if (isExactlyPartyRelated(subValues, path)) {
      participation.setPerformer(new PartyRelated());
    } else {
      participation.setPerformer(new PartySelf());
    }

    setValue(
        path,
        "id",
        subValues,
        s -> {
          if (s != null) {
            participation.getPerformer().setExternalRef(new PartyRef());
            participation.getPerformer().getExternalRef().setType("PARTY");
            GenericId id = new GenericId();
            id.setValue(s);
            participation.getPerformer().getExternalRef().setId(id);
          }
        },
        String.class);

    setValue(
        path,
        "name",
        subValues,
        s -> {
          if (s != null) {

            ((PartyIdentified) participation.getPerformer()).setName(s);
          }
        },
        String.class);

    if (participation.getPerformer().getExternalRef() != null) {
      setValue(
          path,
          "id_namespace",
          subValues,
          s -> {
            if (s != null) {

              participation.getPerformer().getExternalRef().setNamespace(s);
            }
          },
          String.class);
      setValue(
          path,
          "id_scheme",
          subValues,
          s -> {
            if (s != null) {

              ((GenericId) participation.getPerformer().getExternalRef().getId()).setScheme(s);
            }
          },
          String.class);
    }

    setValue(
        path,
        "function",
        subValues,
        s -> {
          if (s != null) {

            participation.setFunction(new DvText(s));
          }
        },
        String.class);

    setValue(
        path,
        "mode",
        subValues,
        s -> {
          if (s != null) {

            ParticipationMode participationMode = findEnumValueOrThrow(s, ParticipationMode.class);
            participation.setMode(new DvCodedText());
            participation.getMode().setValue(participationMode.getValue());
            participation.getMode().setDefiningCode(participationMode.toCodePhrase());
          }
        },
        String.class);

    Map<Integer, Map<FlatPathDto, String>> feederSystemIds =
        extractMultiValued(path, "/_identifier", subValues);

    if (participation.getPerformer() instanceof PartyIdentified) {
      ((PartyIdentified) participation.getPerformer())
          .setIdentifiers(
              feederSystemIds.entrySet().stream()
                  .map(
                      e ->
                          DefaultValues.toDvIdentifier(
                              e.getValue(), path + "/_identifier:" + e.getKey()))
                  .collect(Collectors.toList()));
    }

    if (participation.getPerformer() instanceof PartyRelated) {
      DvCodedText relationship = new DvCodedText();
      relationship.setDefiningCode(new CodePhrase());
      ((PartyRelated) participation.getPerformer()).setRelationship(relationship);

      setValue(path + "/relationship", "value", subValues, relationship::setValue, String.class);
      setValue(
          path + "/relationship",
          "code",
          subValues,
          relationship.getDefiningCode()::setCodeString,
          String.class);
      setValue(
          path + "/relationship",
          "terminology",
          subValues,
          s -> {
            if (s != null) {
              relationship.getDefiningCode().setTerminologyId(new TerminologyId(s));
            }
          },
          String.class);
    }

    return participation;
  }

  public static DvIdentifier toDvIdentifier(Map<String, String> valueMap) {
    DvIdentifier dvIdentifier = new DvIdentifier();

    dvIdentifier.setId(valueMap.get("id"));

    if (StringUtils.isBlank(dvIdentifier.getId())) {
      dvIdentifier.setId(valueMap.get(""));
    }
    dvIdentifier.setAssigner(valueMap.get("assigner"));
    dvIdentifier.setIssuer(valueMap.get("issuer"));
    dvIdentifier.setType(valueMap.get("type"));

    return dvIdentifier;
  }

  public static DvIdentifier toDvIdentifier(Map<FlatPathDto, String> valueMap, String path) {
    DvIdentifier dvIdentifier = new DvIdentifier();

    setValue(path, "id", valueMap, dvIdentifier::setId, String.class);

    if (StringUtils.isBlank(dvIdentifier.getId())) {
      setValue(path, null, valueMap, dvIdentifier::setId, String.class);
    }

    setValue(path, "assigner", valueMap, dvIdentifier::setAssigner, String.class);
    setValue(path, "issuer", valueMap, dvIdentifier::setIssuer, String.class);
    setValue(path, "type", valueMap, dvIdentifier::setType, String.class);

    return dvIdentifier;
  }

  private static <S> void setValue(
      String term,
      String propertyName,
      Map<FlatPathDto, String> values,
      Consumer<S> consumer,
      Class<S> clazz) {
    String key = propertyName != null ? term + "|" + propertyName : term;
    Map.Entry<FlatPathDto, String> entry = FlatPathDto.get(values, key);
    String jasonValue = entry.getValue();
    if (StringUtils.isNotBlank(jasonValue)) {
      try {
        S value = OBJECT_MAPPER.readValue(jasonValue, clazz);
        consumer.accept(value);

      } catch (JsonProcessingException e) {
        throw new SdkException(e.getMessage());
      }
    } else {
      consumer.accept(null);
    }
  }

  private static String getRead(Map<FlatPathDto, String> valueMap, String path) {
    return Optional.ofNullable(FlatPathDto.get(valueMap, path))
        .map(Map.Entry::getValue)
        .map(DefaultValues::read)
        .orElse(null);
  }

  private static Map<Integer, Map<String, String>> splitByIndex(Map<String, String> values) {
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
                          e1 ->
                              StringUtils.substringBefore(
                                  StringUtils.substringAfter(e1.getKey(), "|"), ":"),
                          stringStringEntry ->
                              StringUtils.unwrap(stringStringEntry.getValue(), '"'))));

    } else {
      map = Collections.emptyMap();
    }
    return map;
  }

  private static void extract(
      Collection<Map.Entry<String, String>> subValues,
      String subPath,
      Consumer<String> stringConsumer) {
    filter(
            subValues.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
            DefaultValuePath.PARTICIPATION.getPath() + "_" + subPath)
        .values()
        .stream()
        .map(DefaultValues::read)
        .findAny()
        .ifPresent(stringConsumer);
  }

  private static void extractExact(
      Collection<Map.Entry<String, String>> subValues,
      String subPath,
      Consumer<String> stringConsumer) {
    filterExact(
            subValues.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)),
            DefaultValuePath.PARTICIPATION.getPath() + "_" + subPath)
        .values()
        .stream()
        .map(DefaultValues::read)
        .findAny()
        .ifPresent(stringConsumer);
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
