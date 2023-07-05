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
package org.ehrbase.openehr.sdk.aql.dto.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate.PredicateComparisonOperator;
import org.ehrbase.openehr.sdk.util.Freezable;

public class AndOperatorPredicate implements Freezable<AndOperatorPredicate> {
    private final List<ComparisonOperatorPredicate> operands;
    private boolean immutable;

    private AndOperatorPredicate(List<ComparisonOperatorPredicate> operands, boolean immutable) {
        this.immutable = immutable;
        this.operands = operands;
    }

    public AndOperatorPredicate(List<ComparisonOperatorPredicate> operands) {
        this(Optional.ofNullable(operands).map(Freezable::clone).orElseGet(ArrayList::new), false);
    }

    public List<ComparisonOperatorPredicate> getOperands() {
        return operands;
    }

    public ComparisonOperatorPredicate add(
            AqlObjectPath path, PredicateComparisonOperator operator, PathPredicateOperand<?> value) {
        ComparisonOperatorPredicate toAdd = new ComparisonOperatorPredicate(path, operator, value);
        this.operands.add(toAdd);
        return toAdd;
    }

    public boolean isEmpty() {
        return getOperands().isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AndOperatorPredicate that = (AndOperatorPredicate) o;

        return new EqualsBuilder().append(operands, that.operands).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(operands).toHashCode();
    }

    @Override
    public String toString() {
        return "PredicateAndOperator{" + "operands=" + operands + '}';
    }


    @Override
    public AndOperatorPredicate thawed() {
        return new AndOperatorPredicate(Freezable.thawed(getOperands()),false);
    }

    @Override
    public boolean isFrozen() {
        return this.immutable;
    }

    @Override
    public AndOperatorPredicate frozen() {
        return Freezable.frozen(this, o -> new AndOperatorPredicate(Freezable.frozen(o.getOperands()), true));
    }

    @Override
    public AndOperatorPredicate clone() {
        return Freezable.clone(this, o -> new AndOperatorPredicate(o.getOperands()));
    }
}
