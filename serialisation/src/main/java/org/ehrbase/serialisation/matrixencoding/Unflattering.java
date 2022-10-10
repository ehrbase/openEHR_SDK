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

import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class Unflattering {

    private static final ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

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
                List<ToWalkerDto> nextCollect = filter(collect, path, false, null);
                if (!nextCollect.isEmpty()) {
                    List<Object> content = new ArrayList<>();

                    List<ToWalkerDto> archtype = nextCollect.stream()
                            .filter(w -> w.path.getNodeCount() == 2
                                    && w.path.getNode(1).getName().equals("archetype_node_id"))
                            .collect(Collectors.toList());

                    archtype.stream().parallel().forEach(at -> {
                        AqlPath aqlPath = AqlPath.parse("/" + a.getRmName() + "[" + at.value.toString() + "]");
                        List<ToWalkerDto> filter = filter(nextCollect, aqlPath, false, null);

                        List<ToWalkerDto> names = filter.stream()
                                .filter(w -> w.path.removeStart(1).equals(AqlPath.parse("name/value")))
                                .collect(Collectors.toList());

                        names.stream()
                                .parallel()
                                .forEach(n -> content.addAll(handleMultiValued(filterAndRemovePath(
                                        filter,
                                        AqlPath.parse("/" + a.getRmName() + "[" + at.value.toString() + ",'"
                                                + n.value.toString() + "']"),
                                        false,
                                        null))));
                    });

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
                && path.getBaseNode().findOtherPredicate("name/value") != null
                && toWalkerDto.path.getBaseNode().findOtherPredicate("name/value") != null
                && !path.getBaseNode()
                        .findOtherPredicate("name/value")
                        .equals(toWalkerDto.path.getBaseNode().findOtherPredicate("name/value")))) {
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
