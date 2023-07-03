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
        MATCHES("MATCHES");

        private final String symbol;

        PredicateComparisonOperator(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }

        public static Optional<PredicateComparisonOperator> findBySymbol(String symbol) {
            return Arrays.stream(values()).filter(o -> o.getSymbol().equals(symbol)).findFirst();
        }
    }

    private final AqlObjectPath path;
    private final PredicateComparisonOperator operator;
    private final PathPredicateOperand value;

    public ComparisonOperatorPredicate(AqlObjectPath path, PredicateComparisonOperator operator, PathPredicateOperand value) {
        if(ObjectUtils.anyNull(operator,path,value)){
            throw new IllegalArgumentException("All constructor arguments are required to not be null");
        }
        this.operator = operator;
        this.path = path;
        this.value = value;
    }

    public PredicateComparisonOperator getOperator() {
        return operator;
    }

    public AqlObjectPath getPath() {
        return path;
    }

    public PathPredicateOperand getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ComparisonOperatorPredicate that = (ComparisonOperatorPredicate) o;

        return new EqualsBuilder().append(operator, that.operator).append(path, that.path).append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(operator).append(path).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return "ComparisonPredicate{" +
                "operator=" + operator +
                ", leftOperand=" + path +
                ", rightOperand=" + value +
                '}';
    }
}
