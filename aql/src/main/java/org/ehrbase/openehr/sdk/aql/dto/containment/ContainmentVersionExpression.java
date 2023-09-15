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

import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;

/**
 * @author Stefan Spiska
 */
public class ContainmentVersionExpression extends AbstractContainmentExpression {

    public enum VersionPredicateType {
        LATEST_VERSION,
        ALL_VERSIONS,
        STANDARD_PREDICATE;
    }

    private VersionPredicateType versionPredicateType;

    public VersionPredicateType getVersionPredicateType() {
        return versionPredicateType;
    }

    public void setVersionPredicateType(VersionPredicateType versionPredicateType) {
        this.versionPredicateType = versionPredicateType;

        if (versionPredicateType.equals(VersionPredicateType.ALL_VERSIONS)
                || versionPredicateType.equals(VersionPredicateType.LATEST_VERSION)) {
            setPredicates(null);
        }
    }

    @Override
    public void setPredicates(List<AndOperatorPredicate> predicates) {
        super.setPredicates(predicates);

        if (predicates != null) {
            versionPredicateType = VersionPredicateType.STANDARD_PREDICATE;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContainmentVersionExpression that = (ContainmentVersionExpression) o;
        return versionPredicateType == that.versionPredicateType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), versionPredicateType);
    }

    @Override
    public String toString() {
        return "ContainmentVersionExpression{" + "versionPredicateType="
                + versionPredicateType + "} "
                + super.toString();
    }
}
