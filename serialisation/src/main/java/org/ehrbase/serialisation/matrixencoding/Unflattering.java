/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.serialisation.matrixencoding;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class Unflattering {

    private static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final CollectionType VALUE_TYPE = OBJECT_MAPPER
            .getTypeFactory()
            .constructCollectionType(
                    ArrayList.class,
                    OBJECT_MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class));

    private final Encoder encoder;

    public Unflattering(Encoder encoder) {
        this.encoder = encoder;
    }

    private Map<List<Integer>, Map<AqlPath, Object>> decode(Map<String, Object> map, AqlPath parse) {
        AqlPath entityPath = AqlPath.parse(map.getOrDefault("entity_path", "/").toString());
        map.remove("entity_path");

        ArrayList<Integer> index = new ArrayList<>();

        index.addAll((Collection<Integer>) map.getOrDefault("entity_idx", Collections.emptyList()));
        map.remove("entity_idx");
        index.addAll((Collection<Integer>) map.getOrDefault("field_idx", Collections.emptyList()));
        map.remove("field_idx");

        var collect = map.entrySet().stream()
                .map(e -> Pair.of(AqlPath.parse(e.getKey()), e.getValue()))
                .map(p -> Pair.of(encoder != null ? encoder.decode(p.getKey()) : p.getKey(), p.getValue()))
                .map(p -> Pair.of(entityPath.addEnd(p.getKey()), p.getValue()))
                .filter(p -> p.getKey().startsWith(parse))
                .map(p -> Pair.of(p.getKey().removeStart(parse), p.getValue()))
                .collect(Collectors.toMap(Pair::getKey, Pair::getValue));
        LinkedHashMap<List<Integer>, Map<AqlPath, Object>> listMapLinkedHashMap = new LinkedHashMap<>();
        listMapLinkedHashMap.put(index, collect);
        return listMapLinkedHashMap;
    }

    public Object toRmObject(String json, AqlPath aqlPath) {
        Object value;
        try {
            List<Map<String, Object>> list = OBJECT_MAPPER.readValue(json, VALUE_TYPE);

            List<ToWalkerDto> collect = new ArrayList<>();
            list.stream()
                    .map(map -> decode(map, aqlPath))
                    .forEach(m -> m.forEach((k, v) -> v.forEach((p, o) -> {
                        ToWalkerDto walkerDto = new ToWalkerDto();
                        walkerDto.index = k;
                        walkerDto.path = p;
                        walkerDto.value = o;
                        collect.add(walkerDto);
                    })));

            value = Unflattering.unflatten(collect);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static Object unflatten(List<ToWalkerDto> collect) {
        if (collect.isEmpty()) {
            return null;
        }
        if (collect.size() == 1) {
            return collect.get(0).value;
        }

        List<ToWalkerDto> type = findType(collect);
        LinkedHashMap<String, Object> map;
        if (type.get(0).index.isEmpty()) {

            map = handleChildren(collect, type.get(0).value.toString());
        } else {
            map = new LinkedHashMap<>();

            int max = type.stream().mapToInt(w -> w.index.get(0)).max().orElse(0);

            for (int i = 0; i <= max; i++) {

                List<ToWalkerDto> collectIndex = filterAndRemoveIndex(collect, null, true, i);
                map.putAll(handleChildren(collectIndex, type.get(0).value.toString()));
            }
        }

        return map;
    }

    private static LinkedHashMap<String, Object> handleChildren(List<ToWalkerDto> collect, String type) {
        RMTypeInfo typeInfo = ARCHIE_RM_INFO_LOOKUP.getTypeInfo(type);

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        map.put("_type", type);

        typeInfo.getAttributes().values().forEach(a -> {
            AqlPath path = AqlPath.parse(a.getRmName());

            if (!a.isMultipleValued()) {
                List<ToWalkerDto> nextCollect = filterAndRemovePath(collect, path, false, null);
                if (!nextCollect.isEmpty()) {
                    map.put(a.getRmName(), unflatten(nextCollect));
                }

            } else {

                var nextCollect2 = collect.stream()
                        .filter(e -> matches(e, path, false, null))
                        .collect(Collectors.groupingBy(
                                e -> Optional.ofNullable(e.path.getBaseNode().getAtCode())
                                        .orElse(""),
                                Collectors.groupingBy(
                                        e2 -> Optional.ofNullable(
                                                        e2.path.getBaseNode().findNameValue())
                                                .orElse(""),
                                        Collectors.mapping(Unflattering::removePath, Collectors.toList()))));
                if (!nextCollect2.isEmpty()) {
                    List<Object> content = new ArrayList<>();

                    nextCollect2.values().stream()
                            .flatMap(m -> m.values().stream())
                            .forEach(l -> content.addAll(handleMultiValued(l)));

                    if (!content.isEmpty()) {
                        map.put(a.getRmName(), content);
                    }
                }
            }
        });
        return map;
    }

    private static ToWalkerDto removePath(ToWalkerDto w) {
        ToWalkerDto next = new ToWalkerDto(w);
        next.path = w.path.removeStart(1);
        return next;
    }

    private static List<Object> handleMultiValued(List<ToWalkerDto> nextCollect) {
        int max = nextCollect.stream()
                .filter(w -> !w.index.isEmpty())
                .mapToInt(w -> w.index.get(0))
                .max()
                .orElse(0);
        List<Object> objectList = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            List<ToWalkerDto> collectIndex = filterAndRemoveIndex(nextCollect, null, true, i);
            objectList.add(unflatten(collectIndex));
        }
        return objectList;
    }

    static List<ToWalkerDto> findType(List<ToWalkerDto> collect) {

        return collect.stream().filter(w -> w.path.toString().equals("/_type")).collect(Collectors.toList());
    }

    static List<ToWalkerDto> filter(List<ToWalkerDto> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream().filter(e -> matches(e, path, checkIndex, index)).collect(Collectors.toList());
    }

    static List<ToWalkerDto> filterAndRemovePath(
            List<ToWalkerDto> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream()
                .filter(e -> matches(e, path, checkIndex, index))
                .map(Unflattering::removePath)
                .collect(Collectors.toList());
    }

    static List<ToWalkerDto> filterAndRemoveIndex(
            List<ToWalkerDto> list, AqlPath path, boolean checkIndex, Integer index) {

        return list.stream()
                .filter(e -> matches(e, path, checkIndex, index))
                .map(Unflattering::removeIndex)
                .collect(Collectors.toList());
    }

    private static boolean matches(ToWalkerDto toWalkerDto, AqlPath path, boolean checkIndex, Integer index) {

        if (path != null
                && !toWalkerDto
                        .path
                        .getBaseNode()
                        .getName()
                        .equals(path.getBaseNode().getName())) {
            return false;
        }

        if (path != null
                && path.getBaseNode().getAtCode() != null
                && !path.getBaseNode()
                        .getAtCode()
                        .equals(toWalkerDto.path.getBaseNode().getAtCode())) {
            return false;
        }

        if ((path != null
                && path.getBaseNode().findNameValue() != null
                && toWalkerDto.path.getBaseNode().findNameValue() != null
                && !path.getBaseNode()
                        .findNameValue()
                        .equals(toWalkerDto.path.getBaseNode().findNameValue()))) {
            return false;
        }

        if (checkIndex && toWalkerDto.index.isEmpty()) {
            return false;
        }

        return index == null || toWalkerDto.index.get(0).equals(index);
    }

    private static ToWalkerDto removeIndex(ToWalkerDto current) {

        ToWalkerDto toWalkerDto = new ToWalkerDto(current);
        toWalkerDto.index.remove(0);
        return toWalkerDto;
    }
}
