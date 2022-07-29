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
package org.ehrbase.aql.dto.containment;

import java.io.Serializable;
import java.util.Objects;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;

public class Containment implements Serializable {
    private String archetypeId;
    private String type;

    private PredicateDto otherPredicates;

    public Containment() {}

    public Containment(String type, String archetypeId, PredicateDto otherPredicates) {
        this.archetypeId = archetypeId;
        this.type = type;
        this.otherPredicates = otherPredicates;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArchetypeId() {
        return this.archetypeId;
    }

    public void setArchetypeId(String archetypeId) {
        this.archetypeId = archetypeId;
    }

    public PredicateDto getOtherPredicates() {
        return otherPredicates;
    }

    public void setOtherPredicates(PredicateDto otherPredicates) {
        this.otherPredicates = otherPredicates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Containment that = (Containment) o;
        return Objects.equals(archetypeId, that.archetypeId)
                && Objects.equals(type, that.type)
                && Objects.equals(otherPredicates, that.otherPredicates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archetypeId, type, otherPredicates);
    }

    @Override
    public String toString() {
        return "Containment{" + "archetypeId='"
                + archetypeId + '\'' + ", type='"
                + type + '\'' + ", otherPredicates="
                + otherPredicates + '}';
    }
}
