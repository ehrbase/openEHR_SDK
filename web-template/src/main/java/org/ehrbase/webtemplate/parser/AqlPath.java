/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.webtemplate.parser;

import static org.ehrbase.webtemplate.util.CharSequenceHelper.subSequence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.webtemplate.util.CharSequenceHelper;

public final class AqlPath implements Serializable {

    public static final AqlPath EMPTY_PATH = new AqlPath(true, new AqlNode[0], 0, null);
    public static final AqlPath ROOT_PATH = new AqlPath(false, new AqlNode[0], 0, null);

    private static final AqlNode NO_NODE = new AqlNode("", null, Collections.emptyMap());
    public static final String NAME_VALUE_KEY = "name/value";

    private final boolean isEmpty;
    private final AqlNode[] nodes;
    private final int firstNode;
    private final String attributeName;

    private transient Integer hashCode;

    private AqlPath(boolean isEmpty, AqlNode[] nodes, int firstNode, String attributeName) {

        this.isEmpty = isEmpty;
        if (isEmpty) {
            this.nodes = new AqlNode[0];
            this.firstNode = 0;
        } else {
            this.nodes = nodes;
            this.firstNode = firstNode;
        }
        this.attributeName = attributeName;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public AqlPath withAttributeName(String attributeName) {
        return new AqlPath(isEmpty, nodes, firstNode, attributeName);
    }

    public int getNodeCount() {
        return nodes.length - firstNode;
    }

    public AqlNode getNode(int pos) {
        return nodes[firstNode + pos];
    }

    public List<AqlNode> getNodes() {
        return Collections.unmodifiableList(Arrays.asList(nodes).subList(firstNode, nodes.length));
    }

    public AqlNode getBaseNode() {
        return firstNode == nodes.length ? NO_NODE : nodes[firstNode];
    }

    public AqlNode getLastNode() {
        return firstNode == nodes.length ? NO_NODE : nodes[nodes.length - 1];
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public boolean hasPath() {
        return getNodeCount() > 0;
    }

    public boolean startsWith(AqlPath aqlPath) {
        if (getNodeCount() < aqlPath.getNodeCount()) {
            return false;
        }
        for (int i = 0; i < aqlPath.getNodeCount(); i++) {
            if (!aqlPath.getNode(i).equals(getNode(i))) {
                return false;
            }
        }
        return true;
    }

    public AqlPath removeStart(AqlPath remove) {
        if (remove.hasPath() && this.startsWith(remove)) {
            return removeStart(remove.getNodeCount());
        } else {
            return this;
        }
    }

    public AqlPath removeStart(int nodeCount) {
        if (isEmpty || nodeCount == 0) {
            return this;
        }
        if (nodeCount == this.getNodeCount()) {
            if (getAttributeName() != null) {
                return EMPTY_PATH.withAttributeName(attributeName);
            } else {
                return EMPTY_PATH;
            }
        }
        if (nodeCount < 1 || nodeCount > this.getNodeCount()) {
            throw new IllegalArgumentException();
        }
        return new AqlPath(false, nodes, firstNode + nodeCount, attributeName);
    }

    public AqlPath getEnd(int nodeCount) {
        if (isEmpty) {
            return this;
        }
        if (nodeCount == this.getNodeCount()) {
            return this;
        }
        if (nodeCount < 0 || nodeCount >= this.getNodeCount()) {
            throw new IllegalArgumentException();
        }
        return new AqlPath(nodeCount == 0, nodes, nodes.length - nodeCount, this.attributeName);
    }

    public AqlPath addEnd(AqlPath add) {
        if (isEmpty) {
            return add;
        } else if (add.isEmpty) {
            return this;
        }
        AqlNode[] mergedNodes = new AqlNode[getNodeCount() + add.getNodeCount()];
        System.arraycopy(nodes, firstNode, mergedNodes, 0, getNodeCount());
        System.arraycopy(add.nodes, add.firstNode, mergedNodes, getNodeCount(), add.getNodeCount());

        return new AqlPath(false, mergedNodes, 0, add.getAttributeName());
    }

    public AqlPath addEnd(AqlNode... nodesToAdd) {
        int nodeCount = getNodeCount();
        AqlNode[] mergedNodes = new AqlNode[nodeCount + nodesToAdd.length];
        System.arraycopy(nodes, firstNode, mergedNodes, 0, nodeCount);
        System.arraycopy(nodesToAdd, 0, mergedNodes, nodeCount, nodesToAdd.length);

        return new AqlPath(false, mergedNodes, 0, null);
    }

    public AqlPath addEnd(String... pathExp) {
        if (pathExp.length == 0) {
            return this;
        }
        AqlPath[] paths = Arrays.stream(pathExp).map(AqlPath::parse).toArray(AqlPath[]::new);

        String newAttributeName = paths[paths.length - 1].attributeName;

        AqlNode[] nodesToAdd = Arrays.stream(pathExp)
                .map(AqlPath::parse)
                .map(AqlPath::getNodes)
                .flatMap(Collection::stream)
                .toArray(AqlNode[]::new);

        if (nodesToAdd.length == 0) {
            if (Objects.equals(attributeName, newAttributeName)) {
                return this;
            } else {
                return new AqlPath(isEmpty, nodes, firstNode, newAttributeName);
            }
        }

        int nodeCount = getNodeCount();
        AqlNode[] mergedNodes = new AqlNode[nodeCount + nodesToAdd.length];
        System.arraycopy(nodes, firstNode, mergedNodes, 0, nodeCount);
        System.arraycopy(nodesToAdd, 0, mergedNodes, nodeCount, nodesToAdd.length);

        return new AqlPath(false, mergedNodes, 0, newAttributeName);
    }

    public AqlPath removeEnd(AqlPath remove) {
        if (!StringUtils.equals(this.attributeName, remove.attributeName)) {
            return this;
        }
        int remainingNodes = this.getNodeCount() - remove.getNodeCount();
        if (remainingNodes < 1) {
            return this;
        } else {
            for (int i = 0; i < remove.getNodeCount(); i++) {
                if (!this.getNode(remainingNodes + i).equals(remove.getNode(i))) {
                    return this;
                }
            }
            AqlNode[] subNodes = Arrays.copyOfRange(nodes, firstNode, firstNode + remainingNodes);
            return new AqlPath(false, subNodes, 0, null);
        }
    }

    public AqlPath removeEnd(int nodeCount) {
        if (isEmpty || nodeCount == 0) {
            return this;
        }
        if (nodeCount == this.getNodeCount()) {
            return ROOT_PATH;
        }
        if (nodeCount < 1 || nodeCount > this.getNodeCount()) {
            throw new IllegalArgumentException();
        }
        AqlNode[] subNodes = Arrays.copyOfRange(nodes, firstNode, nodes.length - nodeCount);
        return new AqlPath(false, subNodes, 0, null);
    }

    public AqlPath replaceNode(int pos, AqlNode newNode) {
        AqlNode[] newNodes = Arrays.copyOfRange(nodes, firstNode, nodes.length);
        newNodes[pos] = newNode;
        return new AqlPath(isEmpty, newNodes, 0, attributeName);
    }

    public AqlPath replaceLastNode(UnaryOperator<AqlNode> op) {
        if (getNodeCount() == 0) {
            return this;
        }
        int pos = nodes.length - 1;
        AqlNode oldNode = nodes[pos];
        AqlNode newNode = op.apply(oldNode);
        if (newNode == null) {
            return removeEnd(1);
        } else if (oldNode.equals(newNode)) {
            return this;
        } else {
            return replaceNode(getNodeCount() - 1, newNode);
        }
    }

    public String getPath() {
        return format(OtherPredicatesFormat.FULL, false);
    }

    public String format(boolean withOtherPredicates) {
        if (withOtherPredicates) {
            return format(OtherPredicatesFormat.FULL, true);
        } else {
            return format(OtherPredicatesFormat.NONE, true);
        }
    }

    public String format(OtherPredicatesFormat otherPredicatesFormat, boolean includeAttributeName) {
        StringBuilder sb = new StringBuilder();
        appendFormat(sb, otherPredicatesFormat, includeAttributeName);
        return sb.toString();
    }

    private void appendFormat(
            StringBuilder sb, OtherPredicatesFormat otherPredicatesFormat, boolean includeAttributeName) {
        if (isEmpty) {
            // NOOP
        } else if (getNodeCount() == 0) {
            sb.append('/');
        } else {
            for (int i = firstNode; i < nodes.length; i++) {
                sb.append('/');
                AqlNode node = nodes[i];
                node.appendFormat(sb, otherPredicatesFormat);
            }
        }

        if (includeAttributeName && attributeName != null) {
            sb.append("|").append(attributeName);
        }
    }

    public static AqlPath parse(String pathExp) {
        return parse(pathExp, null);
    }

    public static AqlPath parse(String pathExp, String nameValue) {

        if (StringUtils.isBlank(pathExp)) {
            return EMPTY_PATH;
        }
        String attributeName = null;

        final CharSequence[] nodeStrings = split(CharSequenceHelper.removeStart(pathExp, "/"), null, "/");

        List<AqlNode> nodes = new ArrayList<>(nodeStrings.length);

        for (int i = 0; i < nodeStrings.length; i++) {

            var currentNode = nodeStrings[i];
            boolean isLastNode = nodeStrings.length == i + 1;

            // remove attribute
            if (isLastNode) {
                CharSequence[] attributeSplit = split(currentNode, 2, "|");
                if (attributeSplit.length == 2) {
                    attributeName = attributeSplit[1].toString();
                }
                currentNode = attributeSplit[0];
            }
            parseNode(currentNode, isLastNode, nameValue).ifPresent(nodes::add);
        }

        boolean isAttributeOnly = attributeName != null && pathExp.startsWith("|");

        return new AqlPath(isAttributeOnly, nodes.toArray(AqlNode[]::new), 0, attributeName);
    }

    private static Optional<AqlNode> parseNode(CharSequence currentNode, boolean isLastNode, String nameValue) {
        String nodeName;
        CharSequence predicatesExp;
        if (StringUtils.endsWith(currentNode, "]")) {
            int fist = StringUtils.indexOf(currentNode, '[');
            nodeName = subSequence(currentNode, 0, fist).toString();
            predicatesExp = subSequence(currentNode, fist, currentNode.length());
        } else {
            nodeName = currentNode.toString();
            predicatesExp = null;
        }

        if (StringUtils.isEmpty(nodeName)) {
            return Optional.empty();
        }

        String atCode;
        Map<String, String> otherPredicates;
        if (predicatesExp == null) {
            atCode = null;
            otherPredicates = Collections.emptyMap();

        } else {
            CharSequence node = CharSequenceHelper.removeEnd(CharSequenceHelper.removeStart(predicatesExp, "["), "]");

            CharSequence[] predicates = split(node, null, " and ", ",");
            atCode = predicates[0].toString().trim();

            if (predicates.length == 1) {
                if (isLastNode && StringUtils.isNotEmpty(nameValue)) {
                    otherPredicates = Collections.singletonMap(NAME_VALUE_KEY, nameValue);
                } else {
                    otherPredicates = Collections.emptyMap();
                }
            } else {
                otherPredicates = new LinkedHashMap<>();

                for (int j = 1; j < predicates.length; j++) {
                    CharSequence[] pair = split(predicates[j], 2, "=");
                    String key;
                    CharSequence value;
                    if (j == 1 && pair.length == 1) {
                        key = NAME_VALUE_KEY;
                        value = pair[0];
                    } else if (pair.length == 2) {
                        key = pair[0].toString();
                        value = pair[1];
                    } else {
                        throw new IllegalArgumentException("Illegal predicate format");
                    }
                    otherPredicates.put(
                            key.trim(), StringUtils.unwrap(value.toString().trim(), "'"));
                }

                if (isLastNode && StringUtils.isNotEmpty(nameValue)) {
                    otherPredicates.put(NAME_VALUE_KEY, nameValue);
                }
            }
        }

        return Optional.of(new AqlNode(nodeName, atCode, otherPredicates));
    }

    private static CharSequence[] split(CharSequence path, Integer max, String... search) {
        List<CharSequence> strings = new ArrayList<>();
        Arrays.sort(search, CharSequence::compare);

        boolean inBrackets = false;
        boolean inQuotes = false;
        boolean escape = false;

        int last = 0;
        for (int i = 0; i < path.length(); i++) {
            char ch = path.charAt(i);
            if (!inQuotes && ch == '[') {
                inBrackets = true;
                escape = false;
            } else if (!inQuotes && ch == ']') {
                inBrackets = false;
                escape = false;
            } else if (!escape && ch == '\'') {
                inQuotes = !inQuotes;
            } else if (!escape && ch == '\\') {
                escape = true;
            } else if (inBrackets || inQuotes) {
                escape = false;
            } else {
                CharSequence prefix = findPrefix(path, i, search);
                if (prefix == null) {
                    escape = false;
                } else {
                    strings.add(subSequence(path, last, i));
                    last = prefix.length() + i;
                    if (max != null && strings.size() == max - 1) {
                        strings.add(subSequence(path, last, path.length()));
                        break;
                    }
                }
            }
        }

        if (strings.isEmpty()) {
            strings.add(path);
        } else if (last < path.length() && max == null) {
            strings.add(subSequence(path, last, path.length()));
        }
        return strings.toArray(CharSequence[]::new);
    }

    private static CharSequence findPrefix(CharSequence fullPath, int startPos, String[] search) {
        CharSequence pathAfter = subSequence(fullPath, startPos, fullPath.length());
        int insertionPoint = Arrays.binarySearch(search, pathAfter, CharSequence::compare);
        if (insertionPoint >= 0) {
            return search[insertionPoint];
        }

        int prefixPos = -insertionPoint - 2;
        if (prefixPos < 0) {
            return null;
        }
        String candidate = search[prefixPos];
        if (candidate.length() <= pathAfter.length()
                && 0 == CharSequence.compare(candidate, pathAfter.subSequence(0, candidate.length()))) {
            return candidate;
        } else {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        return equals(o, true);
    }

    public boolean equals(Object o, boolean withOtherPredicates) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (withOtherPredicates && this.hashCode() != o.hashCode()) return false;
        AqlPath aqlPath = (AqlPath) o;

        if (isEmpty == aqlPath.isEmpty) {

            if (getNodeCount() != aqlPath.getNodeCount()) {
                return false;
            }
            for (int i = 0, c = getNodeCount(); i < c; i++) {
                if (!getNode(i).equals(aqlPath.getNode(i), withOtherPredicates)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        if (hashCode == null) {
            int result = Objects.hash(attributeName, isEmpty);

            for (int i = firstNode, c = nodes.length; i < c; i++) {
                result = 31 * result + nodes[i].hashCode();
            }
            hashCode = result;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return format(true);
    }

    public enum OtherPredicatesFormat {
        NONE,
        SHORTED,
        FULL
    }

    public static final class AqlNode implements Serializable {
        private final String name;
        private final String atCode;
        private final Map<String, String> otherPredicates;

        private transient Integer hashCode;

        private AqlNode(String name, String atCode, Map<String, String> otherPredicates) {
            this.name = name;
            this.atCode = StringUtils.isBlank(atCode) ? null : atCode;
            this.otherPredicates = otherPredicates;
        }

        public String getName() {
            return name;
        }

        public String getAtCode() {
            return atCode;
        }

        public AqlNode withAtCode(String atCode) {
            return new AqlNode(name, atCode, otherPredicates);
        }

        public AqlNode withNameValue(String nameValue) {

            if (Objects.equals(nameValue, otherPredicates.get(NAME_VALUE_KEY))) {
                return this;
            }

            Map<String, String> map;
            if (StringUtils.isEmpty(nameValue)) {
                if (otherPredicates.size() == 1) {
                    map = Map.of();
                } else {
                    map = new LinkedHashMap<>(otherPredicates);
                    map.remove(NAME_VALUE_KEY);
                }
            } else if (otherPredicates.containsKey(NAME_VALUE_KEY)) {
                if (otherPredicates.size() == 1) {
                    map = Map.of(NAME_VALUE_KEY, nameValue);
                } else {
                    map = new LinkedHashMap<>(otherPredicates);
                    map.put(NAME_VALUE_KEY, nameValue);
                }
            } else {
                if (otherPredicates.isEmpty()) {
                    map = Map.of(NAME_VALUE_KEY, nameValue);
                } else {
                    map = new LinkedHashMap<>(otherPredicates);
                    map.put(NAME_VALUE_KEY, nameValue);
                }
            }
            return new AqlNode(name, atCode, map);
        }

        public String findOtherPredicate(String name) {
            return otherPredicates.get(name);
        }

        public AqlNode clearOtherPredicates() {
            if (otherPredicates.isEmpty()) {
                return this;
            } else {
                return new AqlNode(name, atCode, Map.of());
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (this.hashCode() != o.hashCode()) return false;
            AqlNode aqlNode = (AqlNode) o;
            return Objects.equals(name, aqlNode.name)
                    && Objects.equals(atCode, aqlNode.atCode)
                    && Objects.equals(otherPredicates, aqlNode.otherPredicates);
        }

        public boolean equals(AqlNode o, boolean withOtherPredicates) {
            if (withOtherPredicates) {
                return equals(o);
            } else {
                return Objects.equals(name, o.name) && Objects.equals(atCode, o.atCode);
            }
        }

        @Override
        public int hashCode() {
            if (hashCode == null) {
                hashCode = Objects.hash(name, atCode, otherPredicates);
            }
            return hashCode;
        }

        public void appendFormat(StringBuilder sb, OtherPredicatesFormat otherPredicatesFormat) {
            sb.append(this.name);
            if (this.atCode != null) {
                sb.append("[").append(this.atCode);
                if (otherPredicatesFormat != OtherPredicatesFormat.NONE) {
                    this.otherPredicates.forEach((key, value) -> {
                        // XXX escape value?? ('\)
                        if (otherPredicatesFormat == OtherPredicatesFormat.SHORTED && key.equals(NAME_VALUE_KEY)) {
                            sb.append(",'").append(value).append("'");
                        } else {
                            sb.append(" and ")
                                    .append(key)
                                    .append("='")
                                    .append(value)
                                    .append("'");
                        }
                    });
                }
                sb.append("]");
            }
        }
    }
}
