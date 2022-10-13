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

import static org.ehrbase.serialisation.jsonencoding.CanonicalJson.MARSHAL_OM;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.lang3.ArrayUtils;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class Flatter {

    private enum HEADERS {
        NUM,
        ENTITY_CONCEPT,
        RM_ENTITY,
        ENTITY_PATH,
        ENTITY_IDX,
        FIELD_IDX,
        FIELD_IDX_LEN,
        FIELDS
    }

    public static final CSVFormat CSV_FORMAT =
            CSVFormat.DEFAULT.builder().setHeader(HEADERS.class).build();
    public static final ObjectMapper MAPPER;

    static {
        MAPPER = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addKeySerializer(AqlPath.class, new AqlPathKeyStdSerializer());

        MAPPER.registerModule(module);
    }

    private static final List<String> resolveTo =
            List.of("OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION", "ADMIN_ENTRY", "SECTION");

    private static void printRow(CSVPrinter printer, Row r) {
        try {
            printer.printRecord(
                    r.getNum(),
                    r.getArchetypeId(),
                    r.getRmType(),
                    r.getEntityPath().format(AqlPath.OtherPredicatesFormat.SHORTED, true),
                    printArray(r.getEntityIdx()),
                    printArray(r.getFieldIdx()),
                    ArrayUtils.isEmpty(r.getFieldIdx()) ? 0 : r.getFieldIdx().length,
                    MAPPER.writerFor(MAPPER.getTypeFactory().constructMapType(Map.class, AqlPath.class, Object.class))
                            .writeValueAsString(r.getFields()));
        } catch (IOException e) {
            throw new SdkException(e.getMessage());
        }
    }

    private static String printArray(Integer[] index) {
        return ArrayUtils.isEmpty(index)
                ? "{}"
                : "{" + Arrays.stream(index).map(Objects::toString).collect(Collectors.joining(",")) + "}";
    }

    public static String flatten(Composition composition) {

        List<Row> flatten = toTable(composition);

        StringBuilder sb = new StringBuilder();
        try (CSVPrinter printer = new CSVPrinter(sb, CSV_FORMAT)) {

            for (Row r : flatten) {
                printRow(printer, r);
            }
        } catch (IOException e) {
            throw new SdkException(e.getMessage(), e);
        }

        return sb.toString();
    }

    public static List<Row> toTable(Composition composition) {
        Map<String, Object> map = MARSHAL_OM.convertValue(composition, Map.class);

        String templateId = composition.getArchetypeDetails().getTemplateId().getValue();
        Entity currentEntity = new Entity();
        currentEntity.setPathFromRoot(AqlPath.ROOT_PATH);
        currentEntity.setArchetypeId(composition.getArchetypeNodeId());
        currentEntity.setRmType("COMPOSITION");
        FromWalkerDto fromWalkerDto = new FromWalkerDto();
        fromWalkerDto.updateEntity(currentEntity);
        fromWalkerDto.setRootFound(true);

        convert(fromWalkerDto, map, AqlPath.ROOT_PATH);

        Map<Entity, Map<Index, Map<AqlPath, Object>>> matrix = fromWalkerDto.getMatrix();
        List<Row> flatten = flatten(matrix);
        flatten.forEach(r -> r.setTemplateId(templateId));
        return flatten;
    }

    private static List<Row> flatten(Map<Entity, Map<Index, Map<AqlPath, Object>>> map) {

        List<Row> collect = map.entrySet().stream()
                .map(e -> toRows(e.getKey(), e.getValue()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        IntStream.range(0, collect.size()).forEach(i -> collect.get(i).setNum(i));
        return collect;
    }

    private static List<Row> toRows(Entity entity, Map<Index, Map<AqlPath, Object>> map) {

        // Make the row unique on archetype + entity_path + entity_index + field_idx
        Map<String, Map<AqlPath, Map<List<Integer>, Map<List<Integer>, Optional<Row>>>>> collect =
                map.entrySet().stream()
                        .map(e -> toRow(entity, e))
                        .collect(Collectors.groupingBy(
                                Row::getArchetypeId,
                                Collectors.groupingBy(
                                        Row::getEntityPath,
                                        Collectors.groupingBy(
                                                r -> Arrays.asList(r.getEntityIdx()),
                                                Collectors.groupingBy(
                                                        r -> Arrays.asList(r.getFieldIdx()),
                                                        Collectors.reducing((r1, r2) -> {
                                                            r1.getFields().putAll(r2.getFields());
                                                            return r1;
                                                        }))))));

        return collect.values().stream()
                .flatMap(m -> m.values().stream())
                .flatMap(m -> m.values().stream())
                .flatMap(m -> m.values().stream())
                .flatMap(Optional::stream)
                .collect(Collectors.toList());
    }

    private static Row toRow(Entity entity, Map.Entry<Index, Map<AqlPath, Object>> e) {
        Row row = new Row();
        row.setEntityIdx(entity.getEntityIdx().getRepetitions());
        row.setArchetypeId(entity.getArchetypeId());
        row.setEntityPath(entity.getPathFromRoot());
        row.setRmType(entity.getRmType());
        row.setFields(e.getValue());
        row.setFieldIdx(e.getKey().getRepetitions());
        return row;
    }

    private static void convert(FromWalkerDto fromWalkerDto, Map<String, Object> map, AqlPath path) {

        map.forEach((k, v) -> {
            if (v instanceof Map) {

                FromWalkerDto next = new FromWalkerDto(fromWalkerDto);

                Map<String, Object> m = (Map<String, Object>) v;

                NodeInfo nodeInfo = extractNodeInfo(m);

                AqlPath pathFromRoot = buildPathFromRoot(path, k, nodeInfo);

                if (!pathFromRoot.isEmpty()
                        && (pathFromRoot.getBaseNode().getAtCode() != null
                                && pathFromRoot.getBaseNode().getAtCode().contains("SECTION"))) {
                    pathFromRoot = pathFromRoot.removeStart(1);
                }
                convert(next, m, pathFromRoot);

            } else {

                if (v instanceof List) {

                    Map<NodeInfo, ? extends List<?>> collect = ((List<?>) v)
                            .stream()
                                    .collect(Collectors.groupingBy(
                                            m -> extractNodeInfo((Map<String, Object>) m),
                                            LinkedHashMap::new,
                                            Collectors.toList()));
                    collect.forEach((info, l) -> IntStream.range(0, l.size()).forEach(i -> {
                        Map<String, Object> m = (Map<String, Object>) ((List<?>) l).get(i);
                        FromWalkerDto next = new FromWalkerDto(fromWalkerDto);

                        if (k.equals("content")) {
                            next.setRootFound(false);
                        }

                        boolean contains = resolveTo.contains(info.type);

                        AqlPath pathFromRoot = buildPathFromRoot(path, k, info);

                        if (contains) {

                            Entity nextEntity = new Entity(next.getCurrentEntity());

                            nextEntity.setArchetypeId(info.nodeId);
                            nextEntity.setRmType(info.type);
                            nextEntity.setPathFromRoot(pathFromRoot);

                            nextEntity.getEntityIdx().add(pathFromRoot.getPath(), i);

                            next.updateEntity(nextEntity);
                            next.setRootFound(true);
                        }

                        if (next.isRootFound()) {
                            next.getCurrentFieldIndex().add(pathFromRoot.getPath(), i);
                            next.getMatrix()
                                    .get(next.getCurrentEntity())
                                    .put(next.getCurrentFieldIndex(), new LinkedHashMap<>());
                        } else {
                            next.getCurrentEntity().getEntityIdx().add(pathFromRoot.getPath(), i);
                            if (!next.getMatrix().containsKey(next.getCurrentEntity())) {
                                next.getMatrix().put(next.getCurrentEntity(), new LinkedHashMap<>());
                            }
                            next.getMatrix()
                                    .get(next.getCurrentEntity())
                                    .put(next.getCurrentFieldIndex(), new LinkedHashMap<>());
                        }

                        convert(next, m, contains && !info.type.equals("SECTION") ? AqlPath.ROOT_PATH : pathFromRoot);
                    }));

                } else {

                    AqlPath pathFromRoot = path.addEnd(k);

                    if (!pathFromRoot.isEmpty()
                            && (pathFromRoot.getBaseNode().getAtCode() != null
                                    && pathFromRoot.getBaseNode().getAtCode().contains("SECTION"))) {

                        pathFromRoot = pathFromRoot.removeStart(1);
                    }

                    fromWalkerDto
                            .getMatrix()
                            .get(fromWalkerDto.getCurrentEntity())
                            .get(fromWalkerDto.getCurrentFieldIndex())
                            .put(pathFromRoot, v);
                }
            }
        });
    }

    private static AqlPath buildPathFromRoot(AqlPath path, String k, NodeInfo info) {
        AqlPath pathFromRoot;

        if (info.nodeId.equals("")) {
            pathFromRoot = path.addEnd(k);
        } else {
            if (List.of("OBSERVATION", "EVALUATION", "INSTRUCTION", "ACTION", "ADMIN_ENTRY", "SECTION", "CLUSTER")
                    .contains(info.type)) {
                pathFromRoot = path.addEnd(k + "[" + info.nodeId + ",'" + info.nameValue + "'" + "]");
            } else {
                pathFromRoot = path.addEnd(k + "[" + info.nodeId + "]");
            }
        }
        return pathFromRoot;
    }

    private static class NodeInfo {
        String type;
        String nodeId;
        String nameValue;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            NodeInfo nodeInfo = (NodeInfo) o;
            return Objects.equals(type, nodeInfo.type)
                    && Objects.equals(nodeId, nodeInfo.nodeId)
                    && Objects.equals(nameValue, nodeInfo.nameValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(type, nodeId, nameValue);
        }
    }

    private static NodeInfo extractNodeInfo(Map<String, Object> map) {

        NodeInfo nodeInfo = new NodeInfo();
        nodeInfo.type = map.getOrDefault("_type", "").toString();
        nodeInfo.nodeId = map.getOrDefault("archetype_node_id", "").toString();
        Object name = map.getOrDefault("name", Collections.emptyMap());
        if (name instanceof Map) {
            nodeInfo.nameValue =
                    ((Map<String, Object>) name).getOrDefault("value", "").toString();
        } else {
            nodeInfo.nameValue = "";
        }

        if (nodeInfo.nodeId.contains(
                "." + nodeInfo.nameValue.toLowerCase(Locale.ENGLISH).replace(" ", "_") + ".")) {

            nodeInfo.nameValue = "";
        }
        return nodeInfo;
    }

    static class AqlPathKeyStdSerializer extends StdSerializer<AqlPath> {

        public AqlPathKeyStdSerializer() {
            super((Class<AqlPath>) null);
        }

        @Override
        public void serialize(AqlPath value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeFieldName(value.format(AqlPath.OtherPredicatesFormat.SHORTED, true));
        }
    }
}
