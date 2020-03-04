/*
 *  Copyright (c) 2019  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  This file is part of Project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.ehrbase.client.introspect.node;

import java.util.Map;
import java.util.Objects;

public class EntityNode implements Node {
    protected final String name;
    protected final boolean multi;
    protected final String rmName;
    protected final Map<String, Node> children;

    public EntityNode(String name, boolean multi, String rmName, Map<String, Node> children) {
        this.name = name;
        this.multi = multi;
        this.rmName = rmName;
        this.children = children;
    }

    @Override
    public String getName() {
        return name;
    }

    public boolean isMulti() {
        return multi;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public String getRmName() {
        return rmName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityNode that = (EntityNode) o;
        return multi == that.multi &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(rmName, that.rmName) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), multi, rmName, children);
    }
}
