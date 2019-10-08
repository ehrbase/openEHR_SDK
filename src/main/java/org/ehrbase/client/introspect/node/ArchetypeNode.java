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

public class ArchetypeNode implements Node {

    private final String name;
    private final String archetypeId;
    private final Map<String, Node> children;
    private final boolean multi;

    public ArchetypeNode(String name, String archetypeId, Map<String, Node> children, boolean multi) {
        this.name = name;
        this.archetypeId = archetypeId;
        this.children = children;
        this.multi = multi;
    }

    @Override
    public String getName() {
        return name;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public String getArchetypeId() {
        return archetypeId;
    }

    public boolean isMulti() {
        return multi;
    }
}
