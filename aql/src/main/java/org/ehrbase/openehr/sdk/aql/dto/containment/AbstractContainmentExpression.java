/*
 * Copyright (c) 2023 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.aql.dto.containment;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.serializer.PredicateSerializer;

/**
 * @author Stefan Spiska
 */
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "identifier")
public abstract sealed class AbstractContainmentExpression implements Containment
        permits ContainmentClassExpression, ContainmentVersionExpression {
    private Containment contains;

    private String identifier;

    public void setContains(Containment contains) {
        this.contains = contains;
    }

    public Containment getContains() {
        return this.contains;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @JsonSerialize(using = PredicateSerializer.class)
    public abstract List<AndOperatorPredicate> getPredicates();

    public abstract boolean hasPredicates();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractContainmentExpression that = (AbstractContainmentExpression) o;
        return Objects.equals(contains, that.contains) && Objects.equals(identifier, that.identifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contains, identifier);
    }

    @Override
    public String toString() {
        return "AbstractContainmentExpression{contains=%s, identifier='%s'".formatted(contains, identifier);
    }
}
