package org.ehrbase.openehr.sdk.aql.dto.path;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate.PredicateComparisonOperator;

public class AndOperatorPredicate {
    private final List<ComparisonOperatorPredicate> operands;

    public AndOperatorPredicate(List<ComparisonOperatorPredicate> operands) {
        this.operands = Optional.ofNullable(operands).map(ArrayList::new).orElseGet(ArrayList::new);
    }

    public List<ComparisonOperatorPredicate> getOperands() {
        return operands;
    }

    public ComparisonOperatorPredicate add(AqlObjectPath path, PredicateComparisonOperator operator, PathPredicateOperand value) {
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
        return "PredicateAndOperator{" +
                "operands=" + operands +
                '}';
    }
}
