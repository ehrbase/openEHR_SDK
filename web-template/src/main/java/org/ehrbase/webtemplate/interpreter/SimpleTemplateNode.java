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
import java.util.Objects;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateNode;

/**
 * @author Stefan Spiska
 */
public class SimpleTemplateNode implements Serializable {

    private String name;
    private String nodeId;
    private String type;
    private boolean isMulti;

    public SimpleTemplateNode() {}

    public SimpleTemplateNode(WebTemplateNode webTemplateNode) {
        this.name = webTemplateNode.getName();
        this.nodeId = webTemplateNode.getNodeId();
        this.type = webTemplateNode.getRmType();
        this.isMulti = webTemplateNode.isMulti();
    }

    public SimpleTemplateNode(WebTemplateInput input) {

        this.name = input.getSuffix();
        this.nodeId = input.getSuffix();
        this.type = input.getType();
        this.isMulti = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isMulti() {
        return isMulti;
    }

    public void setMulti(boolean multi) {
        isMulti = multi;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleTemplateNode that = (SimpleTemplateNode) o;
        return isMulti == that.isMulti
                && Objects.equals(name, that.name)
                && Objects.equals(nodeId, that.nodeId)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, nodeId, type, isMulti);
    }

    @Override
    public String toString() {
        return "SimpleTemplateNode{" + "name='"
                + name + '\'' + ", nodeId='"
                + nodeId + '\'' + ", type='"
                + type + '\'' + ", isMulti="
                + isMulti + '}';
    }
}
