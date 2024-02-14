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
package org.ehrbase.openehr.sdk.aql.dto.containment;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.serializer.PredicateDeserializer;
import org.ehrbase.openehr.sdk.aql.serializer.PredicateSerializer;

@JsonPropertyOrder({"type", "predicates", "contains"})
public final class ContainmentClassExpression extends AbstractContainmentExpression {

    private String type;

    protected List<AndOperatorPredicate> predicates;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonSerialize(using = PredicateSerializer.class)
    @Override
    public List<AndOperatorPredicate> getPredicates() {
        return predicates;
    }

    @JsonDeserialize(using = PredicateDeserializer.class)
    public void setPredicates(List<AndOperatorPredicate> predicates) {
        if (predicates != null) {
            this.predicates = new ArrayList<>(predicates);
        } else {
            this.predicates = null;
        }
    }

    @Override
    public boolean hasPredicates() {
        return CollectionUtils.isNotEmpty(this.predicates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContainmentClassExpression that = (ContainmentClassExpression) o;
        return Objects.equals(type, that.type) && Objects.equals(predicates, that.predicates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, predicates);
    }

    @Override
    public String toString() {
        return "ContainmentClassExpression{type='%s', predicates=%s, %s}".formatted(type, predicates, super.toString());
    }
}
