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
package org.ehrbase.serialisation.matrix;

import static org.ehrbase.serialisation.matrix.CompositionToMatrixWalker.findTypeName;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.nedap.archie.rm.RMObject;
import com.nedap.archie.rm.composition.Composition;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.serialisation.RMDataFormat;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.templateprovider.TemplateProvider;

/**
 * @author Stefan Spiska
 */
public class MatrixFormat implements RMDataFormat {

    private enum HEADERS {
        NUM,
        ENTITY_CONCEPT,
        RM_ENTITY,
        ENTITY_PATH,
        ENTITY_IDX,
        FIELD_IDX,
        FIELD_IDX_LEN,
        FIELDS;
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

    private static final Encoder encoder = new Encoder();
    private final TemplateProvider templateProvider;

    private final boolean encode;

    public MatrixFormat(TemplateProvider templateProvider, boolean encode) {
        this.templateProvider = templateProvider;
        this.encode = encode;
    }

    Map<Entity, Map<Index, Map<AqlPath, Object>>> toMatrix(Composition composition) {
        String templateId = composition.getArchetypeDetails().getTemplateId().getValue();
        Entity currentEntity = new Entity();
        currentEntity.setPathFromRoot(AqlPath.ROOT_PATH);
        currentEntity.setArchetypeId(composition.getArchetypeNodeId());
        FromWalkerDto fromWalkerDto = new FromWalkerDto();
        fromWalkerDto.updateEntity(currentEntity);
        new CompositionToMatrixWalker()
                .walk(
                        composition,
                        fromWalkerDto,
                        templateProvider
                                .buildIntrospect(templateId)
                                .orElseThrow(() -> new SdkException(String.format("Unknown template %s", templateId)))
                                .getTree(),
                        templateId);

        return fromWalkerDto.getMatrix();
    }

    public List<Row> toTable(Composition composition) {

        List<Row> flatten = flatten(toMatrix(composition));

        if (encode) {
            flatten.forEach(encoder::encode);
        }

        return flatten;
    }

    private List<Row> flatten(Map<Entity, Map<Index, Map<AqlPath, Object>>> map) {

        List<Row> collect = map.entrySet().stream()
                .map(e -> toRows(e.getKey(), e.getValue()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        IntStream.range(0, collect.size()).forEach(i -> collect.get(i).setNum(i));
        return collect;
    }

    private List<Row> toRows(Entity entity, Map<Index, Map<AqlPath, Object>> map) {

        return map.entrySet().stream().map(e -> toRow(entity, e)).collect(Collectors.toList());
    }

    private Row toRow(Entity entity, Map.Entry<Index, Map<AqlPath, Object>> e) {
        Row row = new Row();
        row.setEntityIdx(entity.getEntityIdx().getRepetitions());
        row.setArchetypeId(entity.getArchetypeId());
        row.setEntityPath(entity.getPathFromRoot());
        row.setFields(e.getValue());
        row.setFieldIdx(e.getKey().getRepetitions());
        return row;
    }

    private List<Entry> toEntryList(String csv) {

        try {
            return CSV_FORMAT.parse(new StringReader(csv)).stream()
                    .skip(1)
                    .map(this::toRow)
                    .map(this::toEntryList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new SdkException(e.getMessage());
        }
    }

    private List<Entry> toEntryList(Row row) {

        return row.getFields().entrySet().stream()
                .map(e -> {
                    Entry entry = new Entry();

                    entry.path = row.getEntityPath().addEnd(e.getKey());
                    entry.index = new ArrayList<>(Arrays.asList(row.getEntityIdx()));
                    entry.index.addAll(Arrays.asList(row.getFieldIdx()));
                    entry.value = e.getValue();
                    return entry;
                })
                .collect(Collectors.toList());
    }

    private Row toRow(CSVRecord record) {

        Row row = new Row();
        row.setNum(Integer.parseInt(record.get(HEADERS.NUM)));
        row.setEntityIdx(buildArray(record.get(HEADERS.ENTITY_IDX)));
        row.setFieldIdx(buildArray(record.get(HEADERS.FIELD_IDX)));
        row.setArchetypeId(record.get(HEADERS.ENTITY_CONCEPT));
        row.setEntityPath(AqlPath.parse(record.get(HEADERS.ENTITY_PATH)));
        try {
            Map<String, Object> map = MAPPER.readValue(
                    record.get(HEADERS.FIELDS),
                    MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class));
            row.setFields(map.entrySet().stream()
                    .collect(Collectors.toMap(e -> AqlPath.parse(e.getKey()), Map.Entry::getValue)));
        } catch (JsonProcessingException e) {
            throw new SdkException(e.getMessage());
        }

        if (encode) {
            row = encoder.decode(row);
        }

        return row;
    }

    private static Integer[] buildArray(String s) {

        String s1 = StringUtils.substringBetween(s, "{", "}");

        if (StringUtils.isEmpty(s1)) {
            return new Integer[] {};
        }

        return Arrays.stream(s1.split(",")).map(Integer::parseInt).toArray(Integer[]::new);
    }

    @Override
    public String marshal(RMObject rmObject) {
        if (rmObject instanceof Composition) {

            StringBuilder sb = new StringBuilder();
            try (CSVPrinter printer = new CSVPrinter(sb, CSV_FORMAT)) {
                for (Row r : toTable((Composition) rmObject)) {
                    getPrintRecord(printer, r);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return sb.toString();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static void getPrintRecord(CSVPrinter printer, Row r) {
        try {
            printer.printRecord(
                    r.getNum(),
                    r.getArchetypeId(),
                    findTypeName(r.getArchetypeId()),
                    r.getEntityPath().format(AqlPath.OtherPredicatesFormat.SHORTED, true),
                    printArray(r.getEntityIdx()),
                    printArray(r.getFieldIdx()),
                    ArrayUtils.isEmpty(r.getFieldIdx()) ? 0 : r.getFieldIdx().length,
                    MAPPER.writerFor(MAPPER.getTypeFactory().constructMapType(Map.class, AqlPath.class, Object.class))
                            .writeValueAsString(r.getFields()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String printArray(Integer[] index) {
        return ArrayUtils.isEmpty(index)
                ? "{}"
                : "{" + Arrays.stream(index).map(Objects::toString).collect(Collectors.joining(",")) + "}";
    }

    @Override
    public <T extends RMObject> T unmarshal(String value, Class<T> clazz) {

        List<Entry> entries = toEntryList(value);

        String templateId =
                MatrixToCompositionWalker.filter(
                                entries, AqlPath.parse("/archetype_details/template_id/value"), false, null)
                        .stream()
                        .findAny()
                        .orElseThrow()
                        .value
                        .toString();

        MatrixToCompositionWalker matrixToCompositionWalker = new MatrixToCompositionWalker();

        Composition composition = new Composition();
        matrixToCompositionWalker.walk(
                composition,
                entries,
                templateProvider.buildIntrospect(templateId).orElseThrow(),
                null,
                templateId);

        return (T) composition;
    }

    private static class AqlPathKeyStdSerializer extends StdSerializer<AqlPath> {

        public AqlPathKeyStdSerializer() {
            super((Class<AqlPath>) null);
        }

        @Override
        public void serialize(AqlPath value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeFieldName(value.format(AqlPath.OtherPredicatesFormat.SHORTED, true));
        }
    }
}
