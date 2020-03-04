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

import org.ehrbase.client.terminology.ValueSet;

import java.util.Objects;

import static org.ehrbase.client.terminology.ValueSet.EMPTY_VALUE_SET;

public class EndNode implements Node {

    private final Class clazz;
    private final String name;
    private final ValueSet valueSet;
    private final boolean multi;

    public EndNode(Class clazz, String name) {
        this.clazz = clazz;
        this.name = name;
        this.valueSet = EMPTY_VALUE_SET;
        this.multi = false;
    }

    public EndNode(Class clazz, String name, ValueSet valuset) {
        this.clazz = clazz;
        this.name = name;
        this.valueSet = valuset;
        this.multi = false;
    }

    public EndNode(Class clazz, String name, ValueSet valueSet, boolean multi) {
        this.clazz = clazz;
        this.name = name;
        this.valueSet = valueSet;
        this.multi = multi;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public ValueSet getValuset() {
        return valueSet;
    }

    public boolean isMulti() {
        return multi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndNode endNode = (EndNode) o;
        return multi == endNode.multi &&
                Objects.equals(clazz, endNode.clazz) &&
                Objects.equals(name, endNode.name) &&
                Objects.equals(valueSet, endNode.valueSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clazz, name, valueSet, multi);
    }
}
