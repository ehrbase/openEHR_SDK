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
package org.ehrbase.aql.dto.containment;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class ContainmentDto implements ContainmentExpresionDto {
    private int id;
    private String identifier;
    private Containment containment = new Containment();
    private ContainmentExpresionDto contains;

    @JsonProperty(index = 10)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    /**
     * @deprecated use  {@link ContainmentDto#getContainment()} and {@link Containment#getArchetypeId()}
     */
    @Deprecated
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getArchetypeId() {
        return getContainment().getArchetypeId();
    }
    /**
     * @deprecated  use {@link ContainmentDto#getContainment()} and {@link Containment#setArchetypeId(String)}
     */
    @Deprecated
    public void setArchetypeId(String archetypeId) {
        getContainment().setArchetypeId(archetypeId);
    }

    @JsonProperty(index = 20)
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JsonProperty(index = 30)
    public Containment getContainment() {
        return containment;
    }

    public void setContainment(Containment containment) {
        this.containment = containment;
    }

    @JsonProperty(index = 40)
    public ContainmentExpresionDto getContains() {
        return this.contains;
    }

    public void setContains(ContainmentExpresionDto contains) {
        this.contains = contains;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ContainmentDto that = (ContainmentDto) o;
        return id == that.id
                && Objects.equals(containment, that.containment)
                && Objects.equals(contains, that.contains)
                && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(containment, id, contains, identifier);
    }

    @Override
    public String toString() {
        return "ContainmentDto{" + "containment="
                + containment + ", id="
                + id + ", contains="
                + contains + ", identifier='"
                + identifier + '\'' + '}';
    }
}
