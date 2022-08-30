/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.walker.defaultvalues;

import static org.ehrbase.serialisation.walker.FlatHelper.*;

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
import java.time.OffsetDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.classgenerator.EnumValueSet;
import org.ehrbase.client.classgenerator.shareddefinition.ParticipationMode;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.State;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class DefaultValues {

    public static final String COMPACT = "compact";
    private final Map<DefaultValuePath, Object> defaultValueMap;
    private static final ObjectMapper OBJECT_MAPPER = ArchieObjectMapperProvider.getObjectMapper();

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
                .forEach(path -> {
                    Map<String, String> subValues = filter(flat, path.getPath());

                    if (!subValues.isEmpty()) {
                        if (EnumValueSet.class.isAssignableFrom(path.getType())) {
                            String value = subValues.values().stream()
                                    .map(DefaultValues::read)
                                    .findAny()
                                    .orElseThrow();
                            defaultValueMap.put(
                                    path, findEnumValueOrThrow(value, (Class<? extends EnumValueSet>) path.getType()));

                        } else if (String.class.isAssignableFrom(path.getType())) {
                            String value = subValues.values().stream()
                                    .map(DefaultValues::read)
                                    .findAny()
                                    .orElseThrow();
                            defaultValueMap.put(path, value);

                        } else if (Boolean.class.isAssignableFrom(path.getType())) {
                            String value = subValues.values().stream()
                                    .map(DefaultValues::read)
                                    .findAny()
                                    .orElseThrow();
                            if (value.equals("true")) {
                                defaultValueMap.put(path, true);
                            }
                        } else if (TemporalAccessor.class.isAssignableFrom(path.getType())) {
                            String value = subValues.values().stream()
                                    .map(DefaultValues::read)
                                    .findAny()
                                    .orElseThrow();
                            if ("now".equals(value)) {
                                defaultValueMap.put(path, OffsetDateTime.now());
                            } else {
                                defaultValueMap.put(path, DateTimeParsers.parseDateTimeValue(value));
                            }
                        } else if (path.equals(DefaultValuePath.PARTICIPATION)) {

                            Map<Integer, Map<FlatPathDto, String>> participationValues = extractMultiValued(
                                    "ctx",
                                    null,
                                    subValues.entrySet().stream()
                                            .collect(Collectors.toMap(
                                                    e -> new FlatPathDto(e.getKey()), Map.Entry::getValue)));
                            participationValues.replaceAll((k, v) -> {
                                Map<FlatPathDto, String> map;

                                map = v.entrySet().stream()
                                        .collect(Collectors.toMap(
                                                e1 -> {
                                                    if (StringUtils.equals(
                                                            e1.getKey()
                                                                    .getLast()
                                                                    .getName(),
                                                            "participation_identifiers")) {

                                                        Integer integer = Optional.ofNullable(e1.getKey()
                                                                        .getLast()
                                                                        .getAttributeName())
                                                                .map(a -> StringUtils.substringAfter(a, ':'))
                                                                .map(Integer::valueOf)
                                                                .orElse(0);

                                                        return new FlatPathDto(("ctx/"
                                                                        + DefaultValuePath.PARTICIPATION.getPath()
                                                                        + ":"
                                                                        + k)
                                                                + "/"
                                                                + "_identifier"
                                                                + ":"
                                                                + integer
                                                                + "|"
                                                                + Optional.ofNullable(StringUtils.substringBefore(
                                                                                e1.getKey()
                                                                                        .getLast()
                                                                                        .getAttributeName(),
                                                                                ":"))
                                                                        // Contains the identifiers in a compact
                                                                        // formate
                                                                        // "ctx/participation_identifiers:0":
                                                                        // "issuer1::assigner1::id1::PERSON;issuer2::assigner2::id2::PERSON"
                                                                        .orElse(COMPACT));

                                                    } else {
                                                        return new FlatPathDto(("ctx/"
                                                                        + DefaultValuePath.PARTICIPATION.getPath()
                                                                        + ":"
                                                                        + k)
                                                                + "|"
                                                                + StringUtils.substringAfter(
                                                                        e1.getKey()
                                                                                .getLast()
                                                                                .getName(),
                                                                        "_"));
                                                    }
                                                },
                                                Map.Entry::getValue));

                                return map;
                            });

                            defaultValueMap.put(
                                    path,
                                    participationValues.entrySet().stream()
                                            .map(e -> buildParticipation(
                                                    e.getValue(),
                                                    "ctx/"
                                                            + DefaultValuePath.PARTICIPATION.getPath()
                                                            + ":"
                                                            + e.getKey()))
                                            .collect(Collectors.toList()));

                        } else if (path.equals(DefaultValuePath.WORKFLOW_ID)) {
                            ObjectRef<GenericId> ref = new ObjectRef<>();

                            Map<String, String> attributes = subValues.entrySet().stream()
                                    .collect(Collectors.toMap(
                                            e1 -> StringUtils.substringBefore(
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

                            Map<Integer, Map<FlatPathDto, String>> linkValues = extractMultiValued(
                                    "ctx",
                                    null,
                                    subValues.entrySet().stream()
                                            .collect(Collectors.toMap(
                                                    e -> new FlatPathDto(e.getKey()), Map.Entry::getValue)));

                            defaultValueMap.put(
                                    path,
                                    linkValues.entrySet().stream()
                                            .map(e -> createLink(
                                                    e.getValue(),
                                                    "ctx/" + DefaultValuePath.LINKS.getPath() + ":" + e.getKey()))
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
                    .forEach(ref -> {
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
        defaultValueMap.putIfAbsent(DefaultValuePath.ACTION_ISM_TRANSITION_CURRENT_STATE, State.COMPLETED);
        defaultValueMap.putIfAbsent(DefaultValuePath.SETTING, Setting.OTHER_CARE);
        defaultValueMap.putIfAbsent(DefaultValuePath.ACTIVITY_TIMING, "R1");
        defaultValueMap.putIfAbsent(DefaultValuePath.INSTRUCTION_NARRATIVE, "<none>");
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

    public static Participation buildParticipation(Map<FlatPathDto, String> subValues, String path) {
        Participation participation = new Participation();

        if (isExactlyPartyIdentified(subValues, path, null)) {
            participation.setPerformer(new PartyIdentified());
        } else if (isExactlyPartyRelated(subValues, path, null)) {
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

                            ((GenericId) participation
                                            .getPerformer()
                                            .getExternalRef()
                                            .getId())
                                    .setScheme(s);
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

        Map<Integer, Map<FlatPathDto, String>> identifiers = extractMultiValued(path, "/_identifier", subValues);

        if (participation.getPerformer() instanceof PartyIdentified) {

            // Contains the identifiers in a compact formate "ctx/participation/_identifiers:0|compact":
            // "issuer1::assigner1::id1::PERSON;issuer2::assigner2::id2::PERSON"
            if (identifiers.size() == 1
                    && identifiers.get(0).size() == 1
                    && COMPACT.equals(identifiers.get(0).entrySet().stream()
                            .findAny()
                            .orElseThrow()
                            .getKey()
                            .getLast()
                            .getAttributeName())) {

                String ids = StringUtils.unwrap(
                        identifiers.get(0).entrySet().stream()
                                .findAny()
                                .orElseThrow()
                                .getValue(),
                        '"');

                for (String sub : ids.split(";")) {
                    DvIdentifier dvIdentifier = new DvIdentifier();
                    String[] split = sub.split("::");

                    dvIdentifier.setIssuer(split[0]);
                    dvIdentifier.setAssigner(split[1]);
                    dvIdentifier.setId(split[2]);
                    dvIdentifier.setType(split[3]);

                    ((PartyIdentified) participation.getPerformer()).addIdentifier(dvIdentifier);
                }
            } else {
                ((PartyIdentified) participation.getPerformer())
                        .setIdentifiers(identifiers.entrySet().stream()
                                .map(e ->
                                        DefaultValues.toDvIdentifier(e.getValue(), path + "/_identifier:" + e.getKey()))
                                .collect(Collectors.toList()));
            }
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
            String term, String propertyName, Map<FlatPathDto, String> values, Consumer<S> consumer, Class<S> clazz) {
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
            throw new SdkException(String.format(
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
