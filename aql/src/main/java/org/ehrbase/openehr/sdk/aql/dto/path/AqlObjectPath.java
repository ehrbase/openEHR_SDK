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

import java.util.ArrayList;
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

public class AqlObjectPath implements PathPredicateOperand {

    public static class PathNode {
        private final String attribute;
        private final List<AndOperatorPredicate> predicateOrOperands;

        public PathNode(String attribute) {
            this(attribute, null);
        }

        public PathNode(String attribute, List<AndOperatorPredicate> predicateOrOperands) {
            if (StringUtils.isBlank(attribute)) {
                throw new IllegalArgumentException("attribute must not be blank/empty/null");
            }
            this.attribute = attribute;
            this.predicateOrOperands =
                    Optional.ofNullable(predicateOrOperands).map(ArrayList::new).orElseGet(ArrayList::new);
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
    }

    private final List<PathNode> pathNodes;

    public AqlObjectPath(List<PathNode> pathNodes) {
        this.pathNodes = new ArrayList<>(pathNodes);
    }

    public AqlObjectPath(PathNode... pathNodes) {
        this(Arrays.asList(pathNodes));
    }

    public List<PathNode> getPathParts() {
        return pathNodes;
    }

    public static AqlObjectPath parse(String path) {
        return AqlQueryParser.parsePath(path);
    }

    public String render() {
        return AqlRenderer.renderPath(this);
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
}
