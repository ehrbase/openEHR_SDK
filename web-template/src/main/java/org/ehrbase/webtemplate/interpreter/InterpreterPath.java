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
package org.ehrbase.webtemplate.interpreter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class InterpreterPath implements Serializable {

    private List<InterpreterPathNode> nodeList;

    public List<InterpreterPathNode> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<InterpreterPathNode> nodeList) {
        this.nodeList = nodeList;
    }

    public AqlPath buildNormalisedAql() {
        if (CollectionUtils.isNotEmpty(nodeList)) {
            AqlPath.AqlNode[] nodes =
                    nodeList.stream().map(n -> n.getNormalisedNode()).toArray(AqlPath.AqlNode[]::new);
            return AqlPath.ROOT_PATH.addEnd(nodes);
        } else return AqlPath.EMPTY_PATH;
    }

    public Long extractDepth() {

        return nodeList.stream().filter(n -> n.getTemplateNode().isMulti()).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InterpreterPath that = (InterpreterPath) o;
        return Objects.equals(nodeList, that.nodeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nodeList);
    }

    @Override
    public String toString() {
        return "InterpreterPath{" + "nodeList=" + nodeList + '}';
    }
}
