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

import java.util.Arrays;
import java.util.Optional;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;

public class ComparisonOperatorPredicate {

    public enum PredicateComparisonOperator {
        EQ("="),
        NEQ("!="),
        GT_EQ(">="),
        GT(">"),
        LT_EQ("<="),
        LT("<"),
        MATCHES(" matches ");

        private final String symbol;

        PredicateComparisonOperator(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static Optional<PredicateComparisonOperator> findBySymbol(String symbol) {
            return Arrays.stream(values())
                    .filter(o -> o.getSymbol().trim().equalsIgnoreCase(symbol))
                    .findFirst();
        }
    }

    private AqlObjectPath path;
    private PredicateComparisonOperator operator;
    private PathPredicateOperand value;
    private AdlRegex matchesOperand;

    public ComparisonOperatorPredicate() {}

    public ComparisonOperatorPredicate(
            AqlObjectPath path, PredicateComparisonOperator operator, PathPredicateOperand value) {
        if (ObjectUtils.anyNull(operator, path, value)) {
            throw new IllegalArgumentException("All constructor arguments are required to not be null");
        }
        if (operator == PredicateComparisonOperator.MATCHES) {
            throw new IllegalArgumentException("Cannot combine MATCHES with PathPredicateOperand");
        }
        this.operator = operator;
        this.path = path;
        this.value = value;
    }

    public static ComparisonOperatorPredicate matches(AqlObjectPath path, AdlRegex value) {
        ComparisonOperatorPredicate p = new ComparisonOperatorPredicate();
        p.setOperator(PredicateComparisonOperator.MATCHES);
        p.setPath(path);
        p.setMatchesOperand(value);
        return p;
    }

    public AqlObjectPath getPath() {
        return path;
    }

    public void setPath(AqlObjectPath path) {
        this.path = path;
    }

    public PredicateComparisonOperator getOperator() {
        return operator;
    }

    public void setOperator(PredicateComparisonOperator operator) {
        if (operator == PredicateComparisonOperator.MATCHES) {
            this.value = null;
        } else {
            this.matchesOperand = null;
        }
        this.operator = operator;
    }

    public PathPredicateOperand getValue() {
        return value;
    }

    public void setValue(PathPredicateOperand value) {
        if (operator == PredicateComparisonOperator.MATCHES) {
            throw new IllegalStateException("value cannot be set for a matches predicate");
        }
        this.value = value;
    }

    public AdlRegex getMatchesOperand() {
        return matchesOperand;
    }

    public void setMatchesOperand(AdlRegex matchesOperand) {
        if (operator != PredicateComparisonOperator.MATCHES) {
            throw new IllegalStateException("matchesOperand can only be set for a matches predicate");
        }
        this.matchesOperand = matchesOperand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ComparisonOperatorPredicate that = (ComparisonOperatorPredicate) o;

        return new EqualsBuilder()
                .append(operator, that.operator)
                .append(path, that.path)
                .append(value, that.value)
                .append(matchesOperand, that.matchesOperand)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(operator)
                .append(path)
                .append(value)
                .append(matchesOperand)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ComparisonOperatorPredicate{" + "path="
                + path + ", operator="
                + operator + ", value="
                + value + ", matchesOperand="
                + matchesOperand + '}';
    }
}
