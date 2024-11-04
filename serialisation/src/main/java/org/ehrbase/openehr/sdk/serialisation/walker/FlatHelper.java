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
package org.ehrbase.openehr.sdk.serialisation.walker;

import static org.ehrbase.openehr.sdk.util.rmconstants.RmConstants.*;
import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.buildId;

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rminfo.RMTypeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EnumValueSet;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathParser;

public class FlatHelper<T> {

    private final Map<String, Map<List<String>, Integer>> pathCountMap = new HashMap<>();

    public String buildNamePath(Context<T> context, boolean addCount) {
        StringBuilder namePathBuilder = new StringBuilder();
        List<String> nodeIdPath = new ArrayList<>();

        WebTemplateNode child = null;
        for (Iterator<WebTemplateNode> iterator = context.getNodeDeque().descendingIterator(); iterator.hasNext(); ) {
            WebTemplateNode parent = child;
            child = iterator.next();
            boolean skip = skip(child, parent);

            boolean parentIsSkippedElement = parent != null && ELEMENT.equals(parent.getRmType()) && skip(parent, null);
            WebTemplateNode node = parentIsSkippedElement ? parent : child;

            nodeIdPath.add(node.getId(true));

            if (!skip) {
                if (!namePathBuilder.isEmpty()) {
                    namePathBuilder.append('/');
                }

                // Value inherits id of skipped Element, if applicable
                namePathBuilder.append(node.getId(false));

                // Note: the key is still without pathCount and count
                String key = namePathBuilder.toString();

                Map<List<String>, Integer> pathCounts = pathCountMap.computeIfAbsent(key, l -> new HashMap<>());

                List<String> nodeIdPathKey = List.copyOf(nodeIdPath);
                int pathCount = pathCounts.computeIfAbsent(nodeIdPathKey, k -> 1 + maxValue(pathCounts));
                if (pathCount != 1) {
                    namePathBuilder.append(pathCount);
                }

                appendCount(node, context, addCount, namePathBuilder);

                Map<List<String>, Integer> map =
                        pathCountMap.computeIfAbsent(namePathBuilder.toString(), l -> new HashMap<>());
                map.computeIfAbsent(nodeIdPath, k -> 1 + maxValue(map));
            }
        }

        return namePathBuilder.toString();
    }

    private static int maxValue(Map<?, Integer> map) {
        return map.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }

    /**
     * For {@code nodes} with {@code max} != 0 and {@code count} != 0 or {@code forceAppend}' the count from the {@code context}, prefixed with ':', is added to the {@code StringBuilder}
     *
     * @param node
     * @param context
     * @param forceAppend
     * @param sb
     */
    private static void appendCount(WebTemplateNode node, Context<?> context, boolean forceAppend, StringBuilder sb) {
        if (node.getMax() == 1) {
            return;
        }
        Optional.of(node)
                .map(NodeId::new)
                .map(context.getCountMap()::get)
                .filter(c -> forceAppend || c != 0)
                .ifPresent(c -> sb.append(":").append(c));
    }

    public static boolean isExactlyDvCodedText(Map<FlatPathDto, String> values, String path) {
        FlatPathDto codeAtt = FlatPathParser.parse(path).pathWithAttributeName("code");
        return values.keySet().stream().anyMatch(e -> e.isEqualTo(codeAtt));
    }

    public static boolean isExactlyPartySelf(Map<FlatPathDto, String> values, String path, WebTemplateNode node) {
        if (node != null && !rmTypeMatches(node, RM_OBJECT, PARTY_PROXY, PARTY_SELF)) {
            return false;
        }

        FlatPathDto pathDto = FlatPathParser.parse(path);
        FlatPathDto typePath = pathDto.pathWithAttributeName("_type");
        // attributes from PartyIdentified
        FlatPathDto namePath = pathDto.pathWithAttributeName("name");
        FlatPathDto idPath = pathDto.pathWithAttributeName("id");
        // has sub-path from PartyIdentified or PartyRelated?
        FlatPathDto relationshipPath = pathDto.pathWithChild(FlatPathParser.parse("relationship"));
        FlatPathDto identifierPath = pathDto.pathWithChild(FlatPathParser.parse("_identifier"));

        var valueIt = subEntries(values, path).iterator();
        boolean hasAttributeFromDifferentType = false;
        while (valueIt.hasNext()) {
            Map.Entry<FlatPathDto, String> e = valueIt.next();
            if (keyAndValueMatches(e, typePath, PARTY_SELF)) {
                return true;
            }
            FlatPathDto key = e.getKey();
            if (!hasAttributeFromDifferentType) {
                hasAttributeFromDifferentType = key.isEqualTo(namePath)
                        || key.isEqualTo(idPath)
                        || key.startsWith(relationshipPath)
                        || key.startsWith(identifierPath);
            }
        }
        return !hasAttributeFromDifferentType;
    }

    /**
     *
     * @param values
     * @param path
     * @param node if given, the rmType is also checked
     * @return
     */
    public static boolean isExactlyPartyRelated(Map<FlatPathDto, String> values, String path, WebTemplateNode node) {
        if (node != null && !rmTypeMatches(node, RM_OBJECT, PARTY_PROXY, PARTY_IDENTIFIED, PARTY_RELATED)) {
            return false;
        }

        FlatPathDto pathDto = FlatPathParser.parse(path);
        FlatPathDto relationshipPath = pathDto.pathWithChild(FlatPathParser.parse("relationship"));

        return subEntries(values, path).anyMatch(e -> e.getKey().startsWith(relationshipPath));
    }

    public static boolean isExactlyPartyIdentified(Map<FlatPathDto, String> values, String path, WebTemplateNode node) {
        if (node != null && !rmTypeMatches(node, RM_OBJECT, PARTY_PROXY, PARTY_IDENTIFIED)) {
            return false;
        }

        FlatPathDto pathDto = FlatPathParser.parse(path);
        FlatPathDto typePath = pathDto.pathWithAttributeName("_type");
        // is sub-path from subclass?
        FlatPathDto relationshipPath = pathDto.pathWithChild(new FlatPathDto("relationship", null, null, null));
        FlatPathDto namePath = pathDto.pathWithAttributeName("name");
        FlatPathDto idPath = pathDto.pathWithAttributeName("id");
        FlatPathDto identifierPath = pathDto.pathWithChild(new FlatPathDto("_identifier", null, null, null));

        var valueIt = subEntries(values, path).iterator();
        boolean hasAttributeFromType = false;
        while (valueIt.hasNext()) {
            Map.Entry<FlatPathDto, String> e = valueIt.next();
            FlatPathDto key = e.getKey();
            if (keyAndValueMatches(e, typePath, PARTY_SELF) || key.startsWith(relationshipPath)) {
                return false;
            }
            if (!hasAttributeFromType) {
                hasAttributeFromType =
                        key.isEqualTo(namePath) || key.isEqualTo(idPath) || key.startsWith(identifierPath);
            }
        }
        return hasAttributeFromType;
    }

    private static Stream<Map.Entry<FlatPathDto, String>> subEntries(Map<FlatPathDto, String> values, String path) {
        return values.entrySet().stream().filter(e -> e.getKey().startsWith(path));
    }

    private static boolean keyAndValueMatches(
            Map.Entry<FlatPathDto, String> entry, FlatPathDto typePath, String value) {
        return entry.getKey().isEqualTo(typePath)
                && Optional.of(entry)
                        .map(Map.Entry::getValue)
                        .map(v -> StringUtils.unwrap(v, '"'))
                        .filter(value::equals)
                        .isPresent();
    }

    private static boolean rmTypeMatches(WebTemplateNode node, String... rmTypNames) {
        return ArrayUtils.contains(rmTypNames, node.getRmType());
    }

    public static boolean isExactlyIntervalEvent(Map<FlatPathDto, String> values, String path) {
        FlatPathDto mathFunctionPath =
                FlatPathParser.parse(path).pathWithChild(new FlatPathDto("math_function", null, null, null));
        return values.keySet().stream().anyMatch(k -> k.startsWith(mathFunctionPath));
    }

    public boolean skip(Context<T> context) {
        Deque<WebTemplateNode> nodes = context.getNodeDeque();
        WebTemplateNode node = nodes.poll();
        WebTemplateNode parent = nodes.peek();
        nodes.push(node);
        return skip(node, parent);
    }

    public boolean skip(WebTemplateNode node, WebTemplateNode parent) {

        if (node.isArchetypeSlot()) {
            return true;
        }
        if (parent != null && isNonMandatoryRmAttribute(node, parent)) {
            return true;
        }

        if (parent != null
                && parent.getRmType().equals(ISM_TRANSITION)
                && !parent.getId().equals("ism_transition")) {
            return true;
        }

        if (rmTypeMatches(
                node,
                RmConstants.HISTORY,
                RmConstants.ITEM_TREE,
                RmConstants.ITEM_LIST,
                RmConstants.ITEM_SINGLE,
                RmConstants.ITEM_TABLE,
                RmConstants.ITEM_STRUCTURE)) {
            return true;
        } else if (parent != null && isEvent(node)) {
            // a corresponding  RM-tree would contain at maximum 1 event.
            return parent.getChildren().stream()
                                    .collect(Collectors.groupingBy(WebTemplateNode::getAqlPath))
                                    .entrySet()
                                    .stream()
                                    .filter(e -> e.getValue().stream().anyMatch(this::isEvent))
                                    .count()
                            == 1
                    && node.getMax() == 1;
        } else if (node.getRmType().equals(ELEMENT)) {
            List<String> trueChildren = node.getChildren().stream()
                    .filter(n ->
                            !List.of("name", "null_flavour", "feeder_audit").contains(n.getName()))
                    .map(WebTemplateNode::getRmType)
                    .toList();
            return node.getChildren().stream().anyMatch(n -> n.getId().equals("value"))
                    || (trueChildren.size() == 2 && trueChildren.containsAll(List.of(DV_TEXT, DV_CODED_TEXT)));
        } else if (node.getRmType().equals(CODE_PHRASE) && parent != null) {
            return parent.getRmType().equals(DV_CODED_TEXT);
        } else if (node.getRmType().equals(ISM_TRANSITION)) {
            return !node.getId().equals("ism_transition");
        }
        return false;
    }

    public boolean isEvent(WebTemplateNode node) {
        RMTypeInfo typeInfo = Walker.ARCHIE_RM_INFO_LOOKUP.getTypeInfo(node.getRmType());
        return typeInfo != null && Event.class.isAssignableFrom(typeInfo.getJavaClass());
    }

    public boolean isNonMandatoryRmAttribute(WebTemplateNode node, WebTemplateNode parent) {

        RMTypeInfo typeInfo = Walker.ARCHIE_RM_INFO_LOOKUP.getTypeInfo(parent.getRmType());
        String rmName = typeInfo.getRmName();
        String nodeName = node.getName();

        boolean mandatoryNotInWebTemplate = Set.of(
                        "name",
                        "archetype_node_id",
                        "origin",
                        "media_type",
                        "upper_included",
                        "lower_included",
                        "upper_unbounded",
                        "lower_unbounded")
                .contains(nodeName);
        if (mandatoryNotInWebTemplate) {
            return true;
        }

        boolean nonMandatoryInWebTemplate = (RmConstants.ACTIVITY.equals(rmName) && "timing".equals(nodeName))
                || (RmConstants.INSTRUCTION.equals(rmName) && "expiry_time".equals(nodeName))
                || (RmConstants.INTERVAL_EVENT.equals(rmName) && "width".equals(nodeName))
                || (RmConstants.INTERVAL_EVENT.equals(rmName) && "math_function".equals(nodeName))
                || (ISM_TRANSITION.equals(rmName) && "transition".equals(nodeName));
        if (nonMandatoryInWebTemplate) {
            return false;
        }

        boolean nonMandatoryRmAttribute =
                node.getMin() == 0 && typeInfo.getAttributes().containsKey(nodeName);
        if (nonMandatoryRmAttribute) {
            return true;
        }
        return false;
    }

    public static void consumeAllMatching(
            String term, Map<FlatPathDto, String> values, Set<String> consumedPaths, boolean exact) {
        consumedPaths.addAll(values.keySet().stream()
                .filter(s -> exact ? s.isEqualTo(term) : s.startsWith(term))
                .map(FlatPathDto::format)
                .collect(Collectors.toSet()));
    }

    /**
     * extract multi valued sub-values like
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|id": "id1",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|type": "PERSON",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|assigner": "assigner1",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:0|issuer": "issuer1",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|id": "id2",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|type": "PERSON",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|assigner": "assigner2",
     * "vitals/vitals/body_temperature:0/_feeder_audit/feeder_system_item_id:1|issuer": "issuer2",
     *
     * @param currentTerm
     * @param childTerm
     * @param values
     * @return
     */
    public static Map<Integer, Map<FlatPathDto, String>> extractMultiValued(
            String currentTerm, String childTerm, Map<FlatPathDto, String> values) {
        FlatPathDto currentTermDto = new FlatPathDto(currentTerm);
        final FlatPathDto otherPath;
        if (childTerm == null) {
            otherPath = currentTermDto;
        } else {
            otherPath = currentTermDto.pathWithChild(FlatPathParser.parse(childTerm));
        }
        return values.entrySet().stream()
                .filter(s -> s.getKey().startsWith(otherPath))
                .collect(Collectors.groupingBy(
                        e -> Optional.ofNullable(FlatPathDto.removeStart(e.getKey(), currentTermDto)
                                        .getCount())
                                .orElse(0),
                        Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    /**
     * Filter map by path prefix (string comparison)
     *
     * @param values
     * @param path filter path
     * @param includeRaw if raw nodes should be included
     * @return a new map with the filtered entries
     */
    public static Map<FlatPathDto, String> filter(Map<FlatPathDto, String> values, String path, boolean includeRaw) {

        return values.entrySet().stream()
                .filter(e -> e.getKey().startsWith(path))
                .filter(e -> includeRaw || !"raw".equals(e.getKey().getLast().getAttributeName()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static Map<FlatPathDto, String> convertAttributeToFlat(
            Map<FlatPathDto, String> values, String path, String attr, String node) {
        FlatPathDto pathDto = FlatPathParser.parse(path);
        String attrPostfix = attr + "_";

        return values.entrySet().stream()
                .collect(Collectors.toMap(
                        e1 -> {
                            String attributeName = e1.getKey().getLast().getAttributeName();
                            if (StringUtils.contains(attributeName, attrPostfix)) {
                                Integer count = Integer.valueOf(StringUtils.substringAfter(attributeName, ":"));
                                String attribute = StringUtils.substringBetween(attributeName, attrPostfix, ":");
                                return pathDto.pathWithChild(new FlatPathDto(node, null, count, attribute));
                            } else {
                                return e1.getKey();
                            }
                        },
                        Map.Entry::getValue));
    }

    public static <E extends EnumValueSet> E findEnumValueOrThrow(String value, Class<E> clazz) {

        return Arrays.stream(clazz.getEnumConstants())
                .filter(e -> e.getCode().equals(value) || e.getValue().equals(value))
                .findAny()
                .orElseThrow(() -> new SdkException(String.format(
                        "Unknown Value %s in terminology %s", value, clazz.getEnumConstants()[0].getTerminologyId())));
    }

    public static WebTemplateNode buildDummyChild(String attributeName, WebTemplateNode parent) {
        WebTemplateNode node = new WebTemplateNode();
        node.setId(buildId(attributeName));
        node.setName(attributeName);
        node.setAqlPath(parent.getAqlPathDto().addEnd(attributeName));
        node.setMax(0);
        node.setMax(1);

        return node;
    }

    public static WebTemplateNode findOrBuildSubNode(Context<?> context, String id) {
        return context.getNodeDeque()
                .peek()
                .findChildById(id)
                .orElse(buildDummyChild(id, context.getNodeDeque().peek()));
    }
}
