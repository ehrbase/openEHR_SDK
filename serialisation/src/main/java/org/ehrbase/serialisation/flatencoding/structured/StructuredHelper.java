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
package org.ehrbase.serialisation.flatencoding.structured;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.jsonencoding.ArchieObjectMapperProvider;
import org.ehrbase.util.exception.SdkException;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

public class StructuredHelper {
    private static final ObjectMapper OBJECT_MAPPER = ArchieObjectMapperProvider.getObjectMapper();

    private StructuredHelper() {
        // NOP
    }

    /**
     * Convert Structured JSON into Flat JSON
     *
     * @param structuredString
     * @return
     */
    public static String convertStructuredToFlat(String structuredString) {

        try {
            JsonNode jsonNode = OBJECT_MAPPER.readTree(structuredString);
            Map<String, JsonNode> convert = convertStructuredToFlat("", jsonNode);

            return OBJECT_MAPPER.writeValueAsString(convert);
        } catch (JsonProcessingException e) {

            throw new SdkException(e.getMessage(), e);
        }
    }

    /**
     * Convert Flat JSON into Structured JSON
     *
     * @param flatString
     * @return
     */
    public static String convertFlatToStructured(String flatString) {

        try {

            JsonNode jsonNode = null;
            jsonNode = OBJECT_MAPPER.readTree(flatString);

            Map<FlatPathDto, JsonNode> flatMap = new LinkedHashMap<>();

            for (Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> next = it.next();

                flatMap.put(new FlatPathDto(next.getKey()), next.getValue());
            }

            Map<String, Object> structuredMap = convertFlatToStructured(flatMap);

            // the first List is removed
            Map.Entry<String, Object> root =
                    structuredMap.entrySet().stream().findAny().orElseThrow();
            structuredMap.replace(root.getKey(), ((List) root.getValue()).get(0));

            return OBJECT_MAPPER.writeValueAsString(structuredMap);
        } catch (JsonProcessingException e) {

            throw new SdkException(e.getMessage(), e);
        }
    }

    private static Map<String, Object> convertFlatToStructured(Map<FlatPathDto, JsonNode> flatMap) {

        Map<FlatPathDto, Map<FlatPathDto, JsonNode>> sharedStartMap = flatMap.entrySet().stream()
                .collect(Collectors.groupingBy(
                        e -> {
                            FlatPathDto startFlatPathDto = new FlatPathDto();
                            startFlatPathDto.setName(e.getKey().getName());
                            startFlatPathDto.setCount(e.getKey().getCount());
                            return startFlatPathDto;
                        },
                        LinkedHashMap::new,
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (u, v) -> u, LinkedHashMap::new)));

        Map<String, Object> structured = new LinkedHashMap<>();

        sharedStartMap.forEach((k, v) -> {
            if (!structured.containsKey(k.getName())) {
                structured.put(k.getName(), new ArrayList<>());
            }

            List<Object> values = (List<Object>) structured.get(k.getName());

            LinkedHashMap<String, Object> subMap = new LinkedHashMap<>();

            // find sub entries
            Map<String, Object> subEntriesMap =
                    convertFlatToStructured((Map<FlatPathDto, JsonNode>) v.entrySet().stream()
                            .filter(e -> e.getKey().getChild() != null)
                            .collect(Collectors.toMap(
                                    e -> FlatPathDto.removeStart(e.getKey(), new FlatPathDto(k)),
                                    Map.Entry::getValue,
                                    (u, t) -> u,
                                    LinkedHashMap::new)));

            if (!subEntriesMap.isEmpty()) {
                subMap.putAll(subEntriesMap);
            }

            // find attributes
            Map<String, Object> collect = v.entrySet().stream()
                    .filter(e -> e.getKey().getChild() == null)
                    .collect(Collectors.toMap(
                            e -> Optional.ofNullable(e.getKey().getAttributeName())
                                    .map(s -> "|" + s)
                                    .orElse(""),
                            Map.Entry::getValue,
                            (u, j) -> u,
                            LinkedHashMap::new));

            // singe valued Attributes have no name
            if (collect.size() == 1 && collect.containsKey("") && subMap.isEmpty()) {
                values.add(collect.entrySet().stream()
                        .findAny()
                        .map(Map.Entry::getValue)
                        .orElse(""));
            } else if (!collect.isEmpty()) {

                if (collect.containsKey("")) {
                    collect.put("|value", collect.get(""));
                    collect.remove("");
                }
                subMap.putAll(collect);
            }

            if (!subMap.isEmpty()) {
                values.add(subMap);
            }
        });

        return structured;
    }

    private static Map<String, JsonNode> convertStructuredToFlat(String path, JsonNode jsonNode) {

        if (jsonNode instanceof ValueNode) {

            return Map.of(path, jsonNode);

        } else if (jsonNode instanceof ObjectNode) {

            Map<String, JsonNode> map = new HashMap<>();
            for (Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields(); it.hasNext(); ) {
                Map.Entry<String, JsonNode> field = it.next();
                final String newPath;
                if (StringUtils.startsWith(field.getKey(), "|") || StringUtils.isBlank(path)) {
                    newPath = path + field.getKey();
                } else if (StringUtils.isEmpty(field.getKey())) {
                    newPath = path;
                } else {
                    newPath = path + PATH_DIVIDER + field.getKey();
                }

                map.putAll(convertStructuredToFlat(newPath, field.getValue()));
            }
            return map;

        } else if (jsonNode instanceof ArrayNode) {

            Map<String, JsonNode> map = new LinkedHashMap<>();

            boolean needsIndex = IntStream.range(0, jsonNode.size())
                            .mapToObj(jsonNode::get)
                            .map(n -> {
                                if (n instanceof ObjectNode) {
                                    return StreamSupport.stream(
                                                    Spliterators.spliteratorUnknownSize(
                                                            n.fields(), Spliterator.ORDERED),
                                                    false)
                                            .map(Map.Entry::getKey)
                                            .anyMatch(s -> !StringUtils.startsWith(s, "_"));
                                }
                                return true;
                            })
                            .filter(BooleanUtils::isTrue)
                            .count()
                    > 1;

            for (int i = 0; i < jsonNode.size(); i++) {
                JsonNode child = jsonNode.get(i);
                final String newPath;

                if (i > 0 && needsIndex) {
                    newPath = path + ":" + i;
                } else {
                    // To save a look-up if at this path we have a single value or the first value of a multi
                    // Node,
                    // we can always leave the 0 since the Flat Parser can handle missing 0.
                    newPath = path;
                }
                map.putAll(convertStructuredToFlat(newPath, child));
            }

            return map;
        }

        throw new SdkException(
                String.format("Unknown Structure %s", jsonNode.getClass().getSimpleName()));
    }
}
