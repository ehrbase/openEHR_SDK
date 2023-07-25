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

import java.util.Objects;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class NodeId {

    private final String aql;
    private final String name;
    private final String rmClass;

    public NodeId(WebTemplateNode node) {
        this.aql = node.getAqlPath(true);
        this.name = node.getName();
        this.rmClass = node.getRmType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NodeId nodeId = (NodeId) o;
        return Objects.equals(aql, nodeId.aql)
                && Objects.equals(name, nodeId.name)
                && Objects.equals(rmClass, nodeId.rmClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aql, name, rmClass);
    }
}
