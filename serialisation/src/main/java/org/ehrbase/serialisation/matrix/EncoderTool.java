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

import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.NAME_VALUE;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class EncoderTool {

    private final EncoderHelper totalHelper = new EncoderHelper();
    private final EncoderHelper patHelper = new EncoderHelper();

    Map<String, String> buildTotalEncodingMap(List<Row> seed) {

        Map<String, String> encodingMap = new LinkedHashMap<>();

        Comparator<Map.Entry<AqlPath, Long>> comparator = Map.Entry.comparingByValue();

        seed.stream()
                .flatMap(r -> Stream.concat(Stream.of(r.getEntityPath()), r.getFields().keySet().stream()))
                .filter(p -> isSimple(p.getNodes()))
                .filter(p -> !p.getPath().equals("/"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(comparator.reversed())
                .map(Map.Entry::getKey)
                .forEach(p -> encodingMap.computeIfAbsent(p.getPath(), k -> "!" + totalHelper.next()));

        return encodingMap;
    }

    Map<String, String> buildPathEncodingMap(List<Row> seed) {

        Map<String, String> encodingMap = new LinkedHashMap<>();

        Comparator<Map.Entry<String, Long>> comparator = Map.Entry.comparingByValue();

        seed.stream()
                .flatMap(r -> Stream.concat(Stream.of(r.getEntityPath()), r.getFields().keySet().stream()))
                .filter(p -> !p.getPath().equals("/"))
                .filter(p -> !isSimple(p.getNodes()))
                .flatMap(p -> p.getNodes().stream())
                .map(AqlPath.AqlNode::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(comparator.reversed())
                .map(Map.Entry::getKey)
                .forEach(n -> encodingMap.computeIfAbsent(n, k -> totalHelper.next()));

        return encodingMap;
    }

    private boolean isSimple(List<AqlPath.AqlNode> nodes) {
        return nodes.stream().allMatch(this::isSimple);
    }

    private boolean isSimple(AqlPath.AqlNode node) {

        return node.getAtCode() == null && node.findOtherPredicate(NAME_VALUE) == null;
    }
}
