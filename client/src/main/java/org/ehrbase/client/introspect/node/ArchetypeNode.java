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

public class ArchetypeNode extends EntityNode {

    private final String archetypeId;

    public ArchetypeNode(String name, String archetypeId, Map<String, Node> children, boolean multi, String rmName) {
        super(name, multi, rmName, children);
        this.archetypeId = archetypeId;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ArchetypeNode that = (ArchetypeNode) o;
        return Objects.equals(archetypeId, that.archetypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), archetypeId);
    }
}
