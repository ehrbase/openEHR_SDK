/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.path;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;
import org.ehrbase.openehr.sdk.aql.serializer.ObjectPathDeserializer;
import org.ehrbase.openehr.sdk.aql.serializer.ObjectPathSerializer;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.util.Freezable;

@JsonSerialize(using = ObjectPathSerializer.class)
@JsonDeserialize(using = ObjectPathDeserializer.class)
public final class AqlObjectPath implements PathPredicateOperand<AqlObjectPath> {

    public static class PathNode implements Freezable<PathNode> {
        private final String attribute;
        private final List<AndOperatorPredicate> predicateOrOperands;
        private boolean frozen = false;

        private PathNode(String attribute, List<AndOperatorPredicate> predicateOrOperands, boolean frozen) {
            if (StringUtils.isBlank(attribute)) {
                throw new IllegalArgumentException("attribute must not be blank/empty/null");
            }
            this.frozen = frozen;
            this.attribute = attribute;
            this.predicateOrOperands = predicateOrOperands;
        }

        public PathNode(String attribute) {
            this(attribute, List.of());
        }

        public PathNode(String attribute, List<AndOperatorPredicate> predicateOrOperands) {
            this(attribute, Freezable.clone(predicateOrOperands), false);
        }

        public String getAttribute() {
            return attribute;
        }

        public List<AndOperatorPredicate> getPredicateOrOperands() {
            return predicateOrOperands;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            PathNode pathNode = (PathNode) o;

            return new EqualsBuilder()
                    .append(attribute, pathNode.attribute)
                    .append(predicateOrOperands, pathNode.predicateOrOperands)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(attribute)
                    .append(predicateOrOperands)
                    .toHashCode();
        }

        @Override
        public String toString() {
            return "PathPart{" + "attribute='" + attribute + '\'' + ", predicate=" + predicateOrOperands + '}';
        }

        @Override
        public PathNode thawed() {
            return new PathNode(this.getAttribute(), Freezable.thawed(getPredicateOrOperands()), false);
        }

        @Override
        public boolean isFrozen() {
            return frozen;
        }

        @Override
        public PathNode frozen() {
            return Freezable.frozen(
                    this, t -> new PathNode(t.attribute, Freezable.frozen(t.predicateOrOperands), true));
        }

        @Override
        public PathNode clone() {
            return Freezable.clone(this, t -> new PathNode(t.attribute, t.predicateOrOperands));
        }
    }

    private final List<PathNode> pathNodes;
    private boolean frozen = false;

    private AqlObjectPath(List<PathNode> pathNodes, boolean frozen) {
        this.frozen = frozen;
        this.pathNodes = pathNodes;
    }

    public AqlObjectPath(List<PathNode> pathNodes) {
        this(Freezable.clone(pathNodes), false);
    }

    public AqlObjectPath(PathNode... pathNodes) {
        this(Arrays.asList(pathNodes));
    }

    public List<PathNode> getPathNodes() {
        return pathNodes;
    }

    public static AqlObjectPath parse(String path) {
        return AqlQueryParser.parsePath(path);
    }

    public String render() {
        return AqlRenderer.render(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AqlObjectPath that = (AqlObjectPath) o;

        return new EqualsBuilder().append(pathNodes, that.pathNodes).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(pathNodes).toHashCode();
    }

    @Override
    public String toString() {
        return "ObjectPath{" + "pathParts=" + pathNodes + '}';
    }

    public static AqlObjectPathBuilder builder() {
        return new AqlObjectPathBuilder();
    }

    public static final class AqlObjectPathBuilder {
        private final Builder<PathNode> nodes;

        private AqlObjectPathBuilder() {
            this.nodes = Stream.builder();
        }

        public AqlObjectPath build() {
            return new AqlObjectPath(nodes.build().toList());
        }

        public AqlObjectPathBuilder nodes(AqlObjectPath path) {
            path.getPathNodes().stream().map(PathNode::clone).forEach(nodes::add);
            return this;
        }

        public AqlObjectPathBuilder node(String attribute) {
            nodes.add(new PathNode(attribute));
            return this;
        }

        public AqlObjectPathBuilder node(String attribute, List<AndOperatorPredicate> predicates) {
            nodes.add(new PathNode(attribute, predicates));
            return this;
        }

        public AqlObjectPathBuilder node(String attribute, ComparisonOperatorPredicate... predicates) {
            nodes.add(new PathNode(attribute, List.of(new AndOperatorPredicate(Arrays.asList(predicates)))));
            return this;
        }
    }

    @Override
    public AqlObjectPath thawed() {
        return new AqlObjectPath(Freezable.thawed(pathNodes), false);
    }

    @Override
    public boolean isFrozen() {
        return this.frozen;
    }

    @Override
    public AqlObjectPath frozen() {
        return Freezable.frozen(this, o -> new AqlObjectPath(Freezable.frozen(o.getPathNodes()), true));
    }

    @Override
    public AqlObjectPath clone() {
        return Freezable.clone(this, o -> new AqlObjectPath(o.getPathNodes()));
    }

    public static AqlObjectPath fromAqlPath(AqlPath aqlPath) {
        return Optional.of(aqlPath)
                .filter(AqlPath::hasPath)
                .map(p -> p.format(true))
                .map(s -> StringUtils.removeStart(s, "/"))
                .map(AqlObjectPath::parse)
                .orElse(null);
    }

    public static AqlPath toAqlPath(AqlObjectPath aqlObjectPath) {
        return Optional.ofNullable(aqlObjectPath)
                .map(AqlObjectPath::render)
                .map(AqlPath::parse)
                .orElse(AqlPath.EMPTY_PATH);
    }

    public boolean endsWith(AqlObjectPath path) {
        List<PathNode> myNodes = this.getPathNodes();
        List<PathNode> otherNodes =
                Optional.ofNullable(path).map(AqlObjectPath::getPathNodes).orElse(List.of());
        int mySize = myNodes.size();
        int start = mySize - otherNodes.size();
        if (start < 0) {
            return false;
        }
        return otherNodes.equals(myNodes.subList(start, mySize));
    }
}
