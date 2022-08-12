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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ARCHETYPE_ID,
        TYPE,
        PATH,
        COUNT,
        INDEX,
        DEPTH,
        JSON;
    }

    public static final CSVFormat CSV_FORMAT =
            CSVFormat.DEFAULT.builder().setHeader(HEADERS.class).build();

    public static final ObjectMapper MAPPER = new ObjectMapper();
    private final TemplateProvider templateProvider;

    public MatrixFormat(TemplateProvider templateProvider) {
        this.templateProvider = templateProvider;
    }

    Map<Resolve, Map<Index, Map<AqlPath, Object>>> toMatrix(Composition composition) {
        String templateId = composition.getArchetypeDetails().getTemplateId().getValue();
        Resolve currentResolve = new Resolve();
        currentResolve.setPathFromRoot(AqlPath.ROOT_PATH);
        currentResolve.setArchetypeId(composition.getArchetypeNodeId());
        FromWalkerDto fromWalkerDto = new FromWalkerDto();
        fromWalkerDto.updateResolve(currentResolve);
        new CompositionToMatrixWalker()
                .walk(
                        composition,
                        fromWalkerDto,
                        templateProvider.buildIntrospect(templateId).get().getTree(),
                        templateId);

        return fromWalkerDto.getMatrix();
    }

    public List<Row> toTable(Composition composition) {

        return flatten(toMatrix(composition));
    }

    private List<Row> flatten(Map<Resolve, Map<Index, Map<AqlPath, Object>>> map) {

        return map.entrySet().stream()
                .map(e -> toRows(e.getKey(), e.getValue()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Row> toRows(Resolve resolve, Map<Index, Map<AqlPath, Object>> map) {

        return map.entrySet().stream().map(e -> toRow(resolve, e)).collect(Collectors.toList());
    }

    private static Row toRow(Resolve resolve, Map.Entry<Index, Map<AqlPath, Object>> e) {
        Row row = new Row();
        row.setCount(resolve.getCount().getRepetitions());
        row.setArchetypeId(resolve.getArchetypeId());
        row.setPathFromRoot(resolve.getPathFromRoot());
        row.setOther(e.getValue());
        row.setIndex(e.getKey().getRepetitions());
        return row;
    }

    private static List<Entry> toEntryList(String csv) {

        try {
            return CSV_FORMAT.parse(new StringReader(csv)).stream()
                    .skip(1)
                    .map(MatrixFormat::toRow)
                    .map(MatrixFormat::toEntryList)
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new SdkException(e.getMessage());
        }
    }

    private static List<Entry> toEntryList(Row row) {

        return row.getOther().entrySet().stream()
                .map(e -> {
                    Entry entry = new Entry();

                    entry.path = row.getPathFromRoot().addEnd(e.getKey());
                    entry.index = new ArrayList<>(Arrays.asList(row.getCount()));
                    entry.index.addAll(Arrays.asList(row.getIndex()));
                    entry.value = e.getValue();
                    return entry;
                })
                .collect(Collectors.toList());
    }

    private static Row toRow(CSVRecord record) {

        Row row = new Row();
        row.setCount(buildArray(record.get(HEADERS.COUNT)));
        row.setIndex(buildArray(record.get(HEADERS.INDEX)));
        row.setArchetypeId(record.get(HEADERS.ARCHETYPE_ID));
        row.setPathFromRoot(AqlPath.parse(record.get(HEADERS.PATH)));
        try {
            Map<String, Object> map = MAPPER.readValue(
                    record.get(HEADERS.JSON),
                    MAPPER.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class));
            row.setOther(map.entrySet().stream()
                    .collect(Collectors.toMap(e -> AqlPath.parse(e.getKey()), Map.Entry::getValue)));
        } catch (JsonProcessingException e) {
            throw new SdkException(e.getMessage());
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
                toTable((Composition) rmObject).forEach(r -> getPrintRecord(printer, r));
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
                    r.getArchetypeId(),
                    findTypeName(r.getArchetypeId()),
                    r.getPathFromRoot().getPath(),
                    printArray(r.getCount()),
                    printArray(r.getIndex()),
                    ArrayUtils.isEmpty(r.getIndex()) ? 0 : r.getIndex().length,
                    MAPPER.writeValueAsString(r.getOther()));
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

        List<Entry> entries = MatrixFormat.toEntryList(value);

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
}
