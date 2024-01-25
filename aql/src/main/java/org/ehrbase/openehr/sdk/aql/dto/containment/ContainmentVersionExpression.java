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
package org.ehrbase.openehr.sdk.aql.dto.containment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.serializer.VersionPredicateDeserializer;
import org.ehrbase.openehr.sdk.aql.serializer.VersionPredicateSerializer;

/**
 * @author Stefan Spiska
 */
@JsonPropertyOrder({"versionPredicateType", "predicate", "contains"})
public final class ContainmentVersionExpression extends AbstractContainmentExpression {

    public enum VersionPredicateType {
        NONE,
        LATEST_VERSION,
        ALL_VERSIONS,
        STANDARD_PREDICATE;
    }

    private VersionPredicateType versionPredicateType = VersionPredicateType.NONE;

    protected ComparisonOperatorPredicate predicate;

    public VersionPredicateType getVersionPredicateType() {
        return versionPredicateType;
    }

    public void setVersionPredicateType(VersionPredicateType versionPredicateType) {
        this.versionPredicateType = versionPredicateType;
        if (versionPredicateType != VersionPredicateType.STANDARD_PREDICATE) {
            setPredicate(null);
        }
    }

    @JsonSerialize(using = VersionPredicateSerializer.class)
    public ComparisonOperatorPredicate getPredicate() {
        return predicate;
    }

    @JsonDeserialize(using = VersionPredicateDeserializer.class)
    public void setPredicate(ComparisonOperatorPredicate predicate) {
        this.predicate = predicate;

        if (predicate != null) {
            versionPredicateType = VersionPredicateType.STANDARD_PREDICATE;
        } else if (versionPredicateType == VersionPredicateType.STANDARD_PREDICATE) {
            versionPredicateType = VersionPredicateType.NONE;
        }
    }

    @JsonIgnore
    @Override
    public List<AndOperatorPredicate> getPredicates() {
        if (versionPredicateType == VersionPredicateType.STANDARD_PREDICATE) {
            return List.of(new AndOperatorPredicate(List.of(predicate)));
        } else {
            throw new IllegalStateException("Wrong VersionPredicateType: " + versionPredicateType);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContainmentVersionExpression that = (ContainmentVersionExpression) o;
        return versionPredicateType == that.versionPredicateType && Objects.equals(predicate, that.predicate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionPredicateType, predicate);
    }

    @Override
    public String toString() {
        return "ContainmentVersionExpression{versionPredicateType=%s, predicate=%s, %s}"
                .formatted(versionPredicateType, predicate, super.toString());
    }
}
