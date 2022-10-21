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
package org.ehrbase.aql.dto.path;

import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.ARCHETYPE_NODE_ID;
import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.NAME_VALUE;
import static org.ehrbase.aql.dto.path.predicate.PredicateHelper.find;
import static org.ehrbase.aql.util.CharSequenceHelper.subSequence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.condition.Value;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalOrOperation;
import org.ehrbase.aql.dto.path.predicate.SimplePredicateDto;
import org.ehrbase.aql.util.CharSequenceHelper;
import org.ehrbase.util.exception.SdkException;

public final class AqlPath implements Serializable {

    public static final AqlPath EMPTY_PATH = new AqlPath(true, new AqlNode[0], 0, null);
    public static final AqlPath ROOT_PATH = new AqlPath(false, new AqlNode[0], 0, null);

    private static final AqlNode NO_NODE = new AqlNode("", null, new PredicateLogicalAndOperation());
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

        var lastPath = AqlPath.parse(pathExp[pathExp.length - 1]);
        String newAttributeName = lastPath.attributeName;

        // do not parse pathExp twice
        AqlNode[] nodesToAdd = Stream.concat(
                        Arrays.stream(pathExp, 0, pathExp.length - 1).map(AqlPath::parse), Stream.of(lastPath))
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

        final List<CharSequence> nodeStrings = split(CharSequenceHelper.removeStart(pathExp, "/"), -1, false, "/");

        int nodeCount = nodeStrings.size();
        List<AqlNode> nodes = new ArrayList<>(nodeCount);

        for (int i = 0; i < nodeCount; i++) {

            var currentNode = nodeStrings.get(i);
            boolean isLastNode = nodeCount == i + 1;

            // remove attribute
            if (isLastNode) {
                List<CharSequence> attributeSplit = split(currentNode, 2, false, "|");
                if (attributeSplit.size() == 2) {
                    attributeName = attributeSplit.get(1).toString();
                }
                currentNode = attributeSplit.get(0);
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
            predicatesExp = subSequence(currentNode, fist + 1, currentNode.length() - 1);
        } else {
            nodeName = currentNode.toString();
            predicatesExp = null;
        }

        if (StringUtils.isEmpty(nodeName)) {
            return Optional.empty();
        }

        String atCode;
        PredicateLogicalAndOperation otherPredicates;

        if (predicatesExp != null) {
            PredicateDto predicateDto = PredicateHelper.buildPredicate(predicatesExp);

            if (predicateDto instanceof PredicateLogicalOrOperation) {
                throw new SdkException("Or in predicate not supported");
            } else if (predicateDto instanceof PredicateLogicalAndOperation) {
                otherPredicates = (PredicateLogicalAndOperation) predicateDto;
            } else {
                otherPredicates = new PredicateLogicalAndOperation((SimplePredicateDto) predicateDto);
            }

            atCode = find(otherPredicates, ARCHETYPE_NODE_ID)
                    .map(PredicateComparisonOperatorDto::getValue)
                    .map(SimpleValue.class::cast)
                    .map(SimpleValue::getValue)
                    .map(Object::toString)
                    .orElse(null);
        } else {
            atCode = null;
            otherPredicates = new PredicateLogicalAndOperation();
        }
        AqlNode node = new AqlNode(nodeName, atCode, otherPredicates);
        if (nameValue != null && isLastNode) {
            node = node.withNameValue(nameValue);
        }
        return Optional.of(node);
    }

    public static List<CharSequence> split(CharSequence path, int max, boolean addSearch, String... search) {
        List<CharSequence> strings = new ArrayList<>(max > 0 && max < 10 ? max : 10);

        boolean inBrackets = false;
        boolean inQuotes = false;
        boolean escape = false;

        int last = 0;
        for (int i = 0, l = path.length(); i < l; i++) {
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
                String prefix = findPrefix(path, i, search);
                if (prefix == null) {
                    escape = false;
                } else {
                    strings.add(subSequence(path, last, i));
                    if (addSearch) {
                        strings.add(prefix);
                    }
                    last = prefix.length() + i;
                    if (max > 0 && strings.size() == max - 1) {
                        strings.add(subSequence(path, last, path.length()));
                        break;
                    }
                }
            }
        }

        if (strings.isEmpty()) {
            strings.add(path);
        } else if (max <= 0 && last < path.length()) {
            strings.add(subSequence(path, last, path.length()));
        }
        return strings;
    }

    private static String findPrefix(CharSequence fullPath, int startPos, String[] search) {
        CharSequence pathAfter = subSequence(fullPath, startPos, fullPath.length());
        int pathAfterLength = pathAfter.length();
        for (String s : search) {
            int len = s.length();

            if (len <= pathAfterLength && 0 == CharSequenceHelper.compareSubsequence(s, 0, len, pathAfter, 0, len)) {
                return s;
            }
        }
        return null;
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
        private final PredicateLogicalAndOperation otherPredicate;

        private transient Integer hashCode;

        public AqlNode(String name, String atCode, PredicateLogicalAndOperation otherPredicates) {
            this.name = name;
            this.atCode = StringUtils.isBlank(atCode) ? null : atCode;
            this.otherPredicate = otherPredicates;
        }

        public String getName() {
            return name;
        }

        public String getAtCode() {
            return atCode;
        }

        public AqlNode withAtCode(String atCode) {
            return withStatement(ARCHETYPE_NODE_ID, atCode);
        }

        /**
         *
         * @param cmpOp
         * @param statement
         * @param newValue
         * @return new value; null means not found
         */
        private static PredicateComparisonOperatorDto replaceValue(
                PredicateComparisonOperatorDto cmpOp, String statement, String newValue) {
            if (cmpOp.getSymbol() == ConditionComparisonOperatorSymbol.EQ
                    && cmpOp.getStatement().equals(statement)) {
                Value v = cmpOp.getValue();
                if (v instanceof SimpleValue) {
                    Object oldValue = ((SimpleValue) v).getValue();
                    if (Objects.equals(newValue, oldValue)) {
                        // value unchanged
                        return cmpOp;
                    } else {
                        // value changed
                        return new PredicateComparisonOperatorDto(
                                statement, ConditionComparisonOperatorSymbol.EQ, new SimpleValue(newValue));
                    }
                }
            }
            // statement not found
            return null;
        }

        private static <P extends SimplePredicateDto> P replaceInternal(
                P predicate, String statement, String newValue) {
            if (predicate instanceof PredicateComparisonOperatorDto) {
                return (P) replaceValue((PredicateComparisonOperatorDto) predicate, statement, newValue);

            } else if (predicate instanceof PredicateLogicalAndOperation) {
                for (SimplePredicateDto child : ((PredicateLogicalAndOperation) predicate).getValues()) {
                    SimplePredicateDto newChild = replaceInternal(child, statement, newValue);
                    if (newChild == child) {
                        // value unchanged
                        return predicate;
                    } else if (newChild != null) {
                        // value changed
                        SimplePredicateDto[] newValues = ((PredicateLogicalAndOperation) predicate)
                                .getValues().stream()
                                        .map(p -> p == child ? newChild : p)
                                        .toArray(SimplePredicateDto[]::new);
                        return (P) new PredicateLogicalAndOperation(newValues);
                    }
                }
            }
            // statement not found
            return null;
        }

        private static final PredicateComparisonOperatorDto NO_PREDICATE =
                new PredicateComparisonOperatorDto(null, null, null);

        private static <P extends SimplePredicateDto> P remove(P predicate, String statement) {
            if (predicate instanceof PredicateComparisonOperatorDto) {
                PredicateComparisonOperatorDto cmpOp = (PredicateComparisonOperatorDto) predicate;
                if (cmpOp.getSymbol() == ConditionComparisonOperatorSymbol.EQ
                        && cmpOp.getStatement().equals(statement)) {
                    return (P) NO_PREDICATE;
                }
                // statement not found
                return predicate;

            } else if (predicate instanceof PredicateLogicalAndOperation) {
                for (SimplePredicateDto child : ((PredicateLogicalAndOperation) predicate).getValues()) {
                    SimplePredicateDto newChild = remove(child, statement);
                    if (newChild != child) {
                        // statement removed
                        SimplePredicateDto[] newValues = ((PredicateLogicalAndOperation) predicate)
                                .getValues().stream()
                                        .filter(p -> p != NO_PREDICATE)
                                        .map(p -> p == child ? newChild : p)
                                        .toArray(SimplePredicateDto[]::new);
                        return (P) new PredicateLogicalAndOperation(newValues);
                    }
                }
            }
            // statement not found
            return predicate;
        }

        private PredicateLogicalAndOperation replace(String statement, String newValue) {
            PredicateLogicalAndOperation newPredicateDto = replaceInternal(otherPredicate, statement, newValue);

            if (newPredicateDto == null) {
                // statement not found
                SimplePredicateDto[] newValues = Stream.concat(
                                otherPredicate.getValues().stream(),
                                Stream.of(new PredicateComparisonOperatorDto(
                                        statement, ConditionComparisonOperatorSymbol.EQ, new SimpleValue(newValue))))
                        .toArray(SimplePredicateDto[]::new);
                return new PredicateLogicalAndOperation(newValues);

            } else {
                return newPredicateDto;
            }
        }

        public AqlNode withNameValue(String nameValue) {
            return withStatement(NAME_VALUE, nameValue);
        }

        private AqlNode withStatement(String statement, String value) {
            PredicateLogicalAndOperation newOtherPredicate;
            if (value == null) {
                newOtherPredicate = PredicateHelper.remove(otherPredicate, statement);
            } else {
                newOtherPredicate = replace(statement, value);
            }
            if (newOtherPredicate == otherPredicate) {
                return this;
            }
            String newAtCode = ARCHETYPE_NODE_ID.equals(statement) ? value : atCode;
            return new AqlNode(name, newAtCode, newOtherPredicate);
        }

        private PredicateLogicalAndOperation remove(String statement) {
            return PredicateHelper.remove(otherPredicate, statement);
        }

        public PredicateLogicalAndOperation getOtherPredicate() {
            return otherPredicate;
        }

        public String findOtherPredicate(String name) {
            return find(otherPredicate, name)
                    .map(PredicateComparisonOperatorDto::getValue)
                    .filter(SimpleValue.class::isInstance)
                    .map(SimpleValue.class::cast)
                    .map(SimpleValue::getValue)
                    .map(Object::toString)
                    .orElse(null);
        }

        public AqlNode clearOtherPredicates() {
            final PredicateLogicalAndOperation otherPredicates;
            if (atCode == null) {
                otherPredicates = new PredicateLogicalAndOperation();
            } else {
                otherPredicates = new PredicateLogicalAndOperation(new PredicateComparisonOperatorDto(
                        ARCHETYPE_NODE_ID, ConditionComparisonOperatorSymbol.EQ, new SimpleValue(atCode)));
            }
            return new AqlNode(name, atCode, otherPredicates);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (this.hashCode() != o.hashCode()) return false;
            AqlNode aqlNode = (AqlNode) o;
            return Objects.equals(name, aqlNode.name)
                    && Objects.equals(atCode, aqlNode.atCode)
                    && Objects.equals(otherPredicate, aqlNode.otherPredicate);
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
                hashCode = Objects.hash(name, atCode, otherPredicate);
            }
            return hashCode;
        }

        public void appendFormat(StringBuilder sb, OtherPredicatesFormat otherPredicatesFormat) {
            sb.append(this.name);
            if (!otherPredicate.getValues().isEmpty()) {
                sb.append("[");
                PredicateHelper.format(sb, otherPredicate, otherPredicatesFormat);
                sb.append("]");
            }
        }
    }
}
