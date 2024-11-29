/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.dto.path;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ehrbase.openehr.sdk.util.Freezable;

public final class AdlRegex implements Freezable<AdlRegex> {
    private String escapedRegex;

    private boolean frozen = false;

    public AdlRegex(String escapedRegex) {
        setEscapedRegex(escapedRegex);
    }

    public String getEscapedRegex() {
        return escapedRegex;
    }

    public void setEscapedRegex(String escapedRegex) {
        if (frozen) {
            throw new IllegalStateException(
                    "%s is immutable".formatted(getClass().getSimpleName()));
        }
        if (!escapedRegex.matches("\\{\\s*/.*/\\s*(;\\s*[\"'].*[\"']\\s*)?}")) {
            throw new IllegalArgumentException("invalid ADL regex");
        }
        this.escapedRegex = escapedRegex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AdlRegex adlRegex = (AdlRegex) o;

        return new EqualsBuilder().append(escapedRegex, adlRegex.escapedRegex).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(escapedRegex).toHashCode();
    }

    @Override
    public String toString() {
        return "AdlRegex{" + "escapedRegex='" + escapedRegex + '\'' + '}';
    }

    @Override
    public boolean isFrozen() {
        return frozen;
    }

    @Override
    public AdlRegex frozen() {
        return Freezable.frozen(this, t -> {
            AdlRegex clone = t.clone();
            clone.frozen = true;
            return clone;
        });
    }

    @Override
    public AdlRegex clone() {
        return Freezable.clone(this, AdlRegex::thawed);
    }

    @Override
    public AdlRegex thawed() {
        return new AdlRegex(getEscapedRegex());
    }
}
