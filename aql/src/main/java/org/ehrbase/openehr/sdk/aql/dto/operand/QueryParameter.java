/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import org.ehrbase.openehr.sdk.util.Freezable;

/**
 * @author Stefan Spiska
 */
public final class QueryParameter
        implements MatchesOperand, Operand, LikeOperand, PathPredicateOperand<QueryParameter> {

    private String name;

    private boolean frozen = false;

    public QueryParameter() {}

    public QueryParameter(QueryParameter other) {
        this.name = other.name;
    }

    public QueryParameter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (frozen) {
            throw new IllegalStateException(
                    "%s is immutable".formatted(getClass().getSimpleName()));
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @JsonIgnore
    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public QueryParameter clone() {
        return Freezable.clone(this, Freezable::thawed);
    }

    public QueryParameter frozen() {
        return Freezable.frozen(this, t -> {
            QueryParameter clone = clone();
            clone.frozen = true;
            return clone;
        });
    }

    public QueryParameter thawed() {
        return new QueryParameter(getName());
    }

    @Override
    public void render(final StringBuilder sb) {
        sb.append("$").append(getName());
    }
}
