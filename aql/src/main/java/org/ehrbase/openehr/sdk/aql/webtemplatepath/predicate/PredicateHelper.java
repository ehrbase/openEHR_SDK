/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.webtemplatepath.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.aql.dto.LogicalOperator;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.operand.DoublePrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.LongPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Primitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPath;
import org.ehrbase.openehr.sdk.aql.webtemplatepath.AqlPathHelper;
import org.ehrbase.openehr.sdk.util.CharSequenceHelper;
import org.ehrbase.openehr.sdk.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class PredicateHelper {

    public static final String NAME_VALUE = "name/value";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";

    private static final AqlPathHelper.PrefixMatcher OPERATOR_SYMBOLS =
            AqlPathHelper.PrefixMatcher.forStrings(Arrays.stream(ComparisonOperatorSymbol.values())
                    .map(ComparisonOperatorSymbol::getSymbol)
                    .toArray(String[]::new));

    private static final PredicateComparisonOperator NO_PREDICATE = new PredicateComparisonOperator(null, null, null);
    private static final AqlPathHelper.PrefixMatcher PREDICATES_MATCHER =
            AqlPathHelper.PrefixMatcher.forStrings(" and ", " AND ", " or ", " OR ", ",");

    private static int comparisonKey(Predicate dto) {
        if (dto instanceof PredicateComparisonOperator) {
            switch (((PredicateComparisonOperator) dto).getStatement()) {
                case ARCHETYPE_NODE_ID:
                    return 1;
                case NAME_VALUE:
                    return 2;
                default:
                    return 3;
            }
        } else {
            return 9;
        }
    }

    static final Comparator<Predicate> PREDICATE_DTO_COMPARATOR =
            Comparator.comparingInt(PredicateHelper::comparisonKey);

    private PredicateHelper() {
        // NOP
    }

    public static Predicate buildPredicate(CharSequence predicate) {

        Object[] boolList = parsePredicate(predicate);

        if (boolList.length == 1) {
            return (Predicate) boolList[0];
        } else {

            LogicalOperator predicateLogicalOperator = buildLogicalOperator(
                    Arrays.asList(boolList),
                    s -> {
                        final LogicalOperator ret;
                        if (PredicateLogicalOperatorSymbol.AND.equals(s)) {
                            ret = new PredicateLogicalAndOperation();
                        } else {
                            ret = new PredicateLogicalOrOperation();
                        }
                        return ret;
                    },
                    PredicateLogicalOperatorSymbol::getPrecedence);
            return (Predicate) predicateLogicalOperator;
        }
    }

    static Object[] parsePredicate(CharSequence predicate) {

        List<CharSequence> split = AqlPathHelper.split(predicate, 0, -1, true, PREDICATES_MATCHER);

        Object[] ret = new Object[split.size()];
        for (int i = 0, l = split.size(); i < l; i++) {
            if (i % 2 == 0) {
                ret[i] = handleOperator(split.get(i), i);
            } else {
                ret[i] = handleSymbol(split.get(i));
            }
        }
        return ret;
    }

    private static PredicateLogicalOperatorSymbol handleSymbol(CharSequence sequence) {
        switch (CharSequenceHelper.trim(sequence).toString()) {
            case ",":
            case "and":
            case "AND":
                return PredicateLogicalOperatorSymbol.AND;
            case "or":
            case "OR":
                return PredicateLogicalOperatorSymbol.OR;
            default:
                throw new SdkException(String.format("Unknown symbol %s", sequence));
        }
    }

    private static PredicateComparisonOperator handleOperator(CharSequence sequence, int i) {
        List<CharSequence> split = AqlPathHelper.split(sequence, 0, 3, true, OPERATOR_SYMBOLS);

        String statement;
        ComparisonOperatorSymbol operator;
        PathPredicateOperand value;
        if (split.size() == 1) {
            if (i == 0) {
                statement = ARCHETYPE_NODE_ID;
                operator = ComparisonOperatorSymbol.EQ;
                value = parseValue(ARCHETYPE_NODE_ID, CharSequenceHelper.trim(split.get(0)));
            } else if (i == 2) {
                statement = NAME_VALUE;
                operator = ComparisonOperatorSymbol.EQ;
                value = parseValue(NAME_VALUE, CharSequenceHelper.trim(split.get(0)));
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            statement = CharSequenceHelper.trim(split.get(0)).toString();
            operator = ComparisonOperatorSymbol.fromSymbol(split.get(1));
            value = parseValue(statement, CharSequenceHelper.trim(split.get(2)));
        }

        return new PredicateComparisonOperator(statement, operator, value);
    }

    private static PathPredicateOperand parseValue(String statement, CharSequence s) {

        if (s.length() > 0 && s.charAt(0) == '$') {
            QueryParameter parameterValue = new QueryParameter();
            parameterValue.setName(CharSequenceHelper.subSequence(s, 1).toString());
            return parameterValue;
        }

        if (ARCHETYPE_NODE_ID.equals(statement)) {
            return new StringPrimitive(s.toString());
        } else if (s.length() > 1 && s.charAt(0) == '\'') {
            return new StringPrimitive(
                    CharSequenceHelper.subSequence(s, 1, s.length() - 1).toString());
        } else if (representsPlainInteger(s)) {
            return new LongPrimitive(Long.parseLong(s.toString()));
        } else {
            return new DoublePrimitive(Double.parseDouble(s.toString()));
        }
    }

    static boolean representsPlainInteger(CharSequence s) {
        for (int i = 0, l = s.length(); i < l; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c) || (i == 0 && c == '-')) {
                return false;
            }
        }
        return true;
    }

    private static void format(String statement, StringBuilder sb, PathPredicateOperand value) {
        if (value instanceof Primitive) {
            Object o = ((Primitive) value).getValue();

            if (o instanceof String && !ARCHETYPE_NODE_ID.equals(statement)) {
                sb.append(StringUtils.wrap(o.toString(), "'"));
            } else {
                sb.append(o);
            }
        } else if (value instanceof QueryParameter) {

            sb.append('$').append(((QueryParameter) value).getName());
        }
    }

    public static String format(Predicate predicate, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        StringBuilder sb = new StringBuilder();
        format(sb, predicate, otherPredicatesFormat);
        return sb.toString();
    }

    public static void format(
            StringBuilder sb, Predicate predicate, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (predicate instanceof PredicateComparisonOperator) {
            formatPredicateComparisonOperatorDto(sb, (PredicateComparisonOperator) predicate, otherPredicatesFormat);
        } else if (predicate instanceof PredicateLogicalAndOperation) {
            formatPredicateLogicalAndOperation(sb, (PredicateLogicalAndOperation) predicate, otherPredicatesFormat);
        } else if (predicate instanceof PredicateLogicalOrOperation) {
            formatPredicateLogicalOrOperation(sb, (PredicateLogicalOrOperation) predicate, otherPredicatesFormat);
        } else {
            throw new IllegalArgumentException(
                    "Unexpected predicate type: " + predicate.getClass().getName());
        }
    }

    private static void formatPredicateLogicalOrOperation(
            StringBuilder sb,
            PredicateLogicalOrOperation predicateDto,
            AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        if (otherPredicatesFormat.equals(AqlPath.OtherPredicatesFormat.SHORTED)) {
            otherPredicatesFormat = AqlPath.OtherPredicatesFormat.FULL;
        }
        List<DisjunctablePredicate> values = predicateDto.getValues();
        for (int i = 0; i < values.size(); i++) {

            if (i > 0 && !isNone(values.get(i), otherPredicatesFormat)) {
                sb.append(" or ");
            }
            format(sb, values.get(i), otherPredicatesFormat);
        }
    }

    private static void formatPredicateLogicalAndOperation(
            StringBuilder sb,
            PredicateLogicalAndOperation predicateDto,
            AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        DisjunctablePredicate[] values = predicateDto.getValues().toArray(DisjunctablePredicate[]::new);
        Arrays.sort(values, PREDICATE_DTO_COMPARATOR);

        for (int i = 0; i < values.length; i++) {
            DisjunctablePredicate value = values[i];
            if (isNone(value, otherPredicatesFormat)) {
                continue;
            }
            if (i > 0) {
                if (isShorten(value, otherPredicatesFormat)) {
                    sb.append(",");
                } else {
                    sb.append(" and ");
                }
            }
            format(sb, value, otherPredicatesFormat);
        }
    }

    private static void formatPredicateComparisonOperatorDto(
            StringBuilder sb,
            PredicateComparisonOperator predicateDto,
            AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        if (isShorten(predicateDto, otherPredicatesFormat)) {
            format(predicateDto.getStatement(), sb, predicateDto.getValue());
        } else if (!isNone(predicateDto, otherPredicatesFormat)) {
            sb.append(predicateDto.getStatement())
                    .append(predicateDto.getSymbol().getSymbol());
            format(predicateDto.getStatement(), sb, predicateDto.getValue());
        }
    }

    private static boolean isNone(
            DisjunctablePredicate predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        return AqlPath.OtherPredicatesFormat.NONE == otherPredicatesFormat
                && predicateDto instanceof PredicateComparisonOperator
                && !ARCHETYPE_NODE_ID.equals(((PredicateComparisonOperator) predicateDto).getStatement());
    }

    private static boolean isShorten(
            DisjunctablePredicate predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (predicateDto instanceof PredicateComparisonOperator cmpOpDto
                && cmpOpDto.getStatement().equals(ARCHETYPE_NODE_ID)) {
            return true;
        }
        if (otherPredicatesFormat == AqlPath.OtherPredicatesFormat.FULL) {
            return false;
        }

        if (predicateDto instanceof PredicateComparisonOperator cmpOpDto) {
            return ComparisonOperatorSymbol.EQ == cmpOpDto.getSymbol()
                    && List.of(NAME_VALUE, ARCHETYPE_NODE_ID).contains(cmpOpDto.getStatement());

        } else if (predicateDto instanceof PredicateLogicalAndOperation) {
            List<PredicateComparisonOperator> andOpVals = ((PredicateLogicalAndOperation) predicateDto).getValues();
            return CollectionUtils.isNotEmpty(andOpVals)
                    && isShorten(andOpVals.get(andOpVals.size() - 1), otherPredicatesFormat);
        } else {
            throw new IllegalArgumentException(
                    "Unexpected predicate type: " + predicateDto.getClass().getName());
        }
    }

    public static Optional<PredicateComparisonOperator> find(Predicate predicate, String statement) {

        if (predicate instanceof PredicateComparisonOperator cmpOp) {
            return Optional.of(cmpOp).filter(op -> statement.equals(op.getStatement()));
        }

        List<? extends Predicate> currentList;
        {
            List<? extends Predicate> values;
            if (predicate instanceof PredicateLogicalOrOperation orOp) {
                values = orOp.getValues();
            } else if (predicate instanceof PredicateLogicalAndOperation andOp) {
                values = andOp.getValues();
            } else {
                return Optional.empty();
            }

            if (values.isEmpty()) {
                return Optional.empty();
            }

            for (Predicate v : values) {
                if (v instanceof PredicateComparisonOperator cmpOp && statement.equals(cmpOp.getStatement())) {
                    return Optional.of(cmpOp);
                }
            }

            currentList = values;
        }

        Deque<List<? extends Predicate>> remainingLists = new LinkedList<>();

        do {
            for (Predicate pred : currentList) {
                List<? extends Predicate> values;
                if (pred instanceof PredicateLogicalOrOperation orOp) {
                    values = orOp.getValues();
                } else if (pred instanceof PredicateLogicalAndOperation andOp) {
                    values = andOp.getValues();
                } else {
                    continue;
                }
                for (Predicate v : values) {
                    if (v instanceof PredicateComparisonOperator cmpOp && statement.equals(cmpOp.getStatement())) {
                        return Optional.of(cmpOp);
                    }
                }
                if (!values.isEmpty()) {
                    remainingLists.add(values);
                }
            }
            currentList = remainingLists.pollFirst();

        } while (currentList != null);

        return Optional.empty();
    }

    //    public static DisjunctableAqlPredicate add(DisjunctableAqlPredicate simplePredicateDto,
    // DisjunctableAqlPredicate add) {
    //        if (simplePredicateDto instanceof PredicateLogicalAndOperation) {
    //            return ((PredicateLogicalAndOperation) simplePredicateDto).addValues(Stream.of(add));
    //        } else {
    //            return new PredicateLogicalAndOperation(simplePredicateDto, add);
    //        }
    //    }

    /**
     * Return a clone of <code>and</code> with all {@link PredicateComparisonOperator} removed where
     * the operator <code>symbol</code> matches
     * and {@link PredicateComparisonOperator#getStatement()} is in <code>remove</code>
     * @param and
     * @param symbol the operator to be matched, or null
     * @param remove names of statements to be removed
     * @return
     */
    public static PredicateLogicalAndOperation remove(
            PredicateLogicalAndOperation and, ComparisonOperatorSymbol symbol, String... remove) {
        return removeInternal(
                and,
                cmpOp -> (symbol == null || symbol == cmpOp.getSymbol())
                        && ArrayUtils.contains(remove, cmpOp.getStatement()));
    }

    private static <P extends DisjunctablePredicate> P removeInternal(
            P predicate, java.util.function.Predicate<PredicateComparisonOperator> filter) {
        if (predicate instanceof PredicateComparisonOperator) {
            PredicateComparisonOperator cmpOp = (PredicateComparisonOperator) predicate;
            if (filter.test(cmpOp)) {
                return (P) NO_PREDICATE;
            }
            // statement not found
            return predicate;

        } else if (predicate instanceof PredicateLogicalAndOperation) {
            List<DisjunctablePredicate> newValues = null;
            List<PredicateComparisonOperator> values = ((PredicateLogicalAndOperation) predicate).getValues();
            int newPos = 0;
            int s = values.size();
            for (int i = 0; i < s; i++) {
                PredicateComparisonOperator child = values.get(i);
                PredicateComparisonOperator newChild = removeInternal(child, filter);
                if (newChild != child) {
                    boolean removeNode = newChild == NO_PREDICATE;
                    if (newValues == null) {
                        newValues = new ArrayList<>(removeNode ? (s - 1) : s);
                    }
                    if (i > newPos) {
                        newValues.addAll(values.subList(newPos, i));
                    }
                    if (!removeNode) {
                        newValues.add(newChild);
                    }
                    newPos = i + 1;
                }
            }

            if (newPos == 0) {
                return predicate;
            }

            if (newPos < s) {
                newValues.addAll(values.subList(newPos, s));
            }
            return (P) new PredicateLogicalAndOperation(newValues.toArray(PredicateComparisonOperator[]::new));
        }
        // statement not found
        return predicate;
    }

    private static <S, T> LogicalOperator<S, T> buildLogicalOperator(
            List<Object> boolList, Function<S, LogicalOperator<S, T>> creator, ToIntFunction<S> precedenceFunction) {
        OperatorStructure<S> structure = buildLogicalOperatorStructure(boolList, precedenceFunction);
        return buildLogicalOperator(structure, creator);
    }

    private static <S, T> LogicalOperator<S, T> buildLogicalOperator(
            OperatorStructure<S> structure, Function<S, LogicalOperator<S, T>> creator) {

        LogicalOperator<S, T> operator = creator.apply(structure.getSymbol());

        Stream<T> stream = structure.getChildren().stream().map(v -> {
            if (v instanceof OperatorStructure) {
                return (T) buildLogicalOperator((OperatorStructure<S>) v, creator);
            } else {
                return (T) v;
            }
        });
        return operator.addValues(stream);
    }

    private static <S> OperatorStructure<S> buildLogicalOperatorStructure(
            List<Object> boolList, ToIntFunction<S> precedenceFunction) {

        S currentSymbol = (S) boolList.get(1);
        OperatorStructure<S> currentOperator = new OperatorStructure(currentSymbol, boolList.get(0));

        OperatorStructure<S> lowestOperator = currentOperator;
        for (int i = 2, l = boolList.size(); i < l; i += 2) {
            S nextSymbol = i + 1 < l ? (S) boolList.get(i + 1) : null;
            Object currentOpValue = boolList.get(i);
            if (nextSymbol == null || Objects.equals(currentSymbol, nextSymbol)) {
                currentOperator.addChild(currentOpValue);

            } else {
                OperatorStructure<S> nextOperator = new OperatorStructure<>(nextSymbol);

                if (hasHigherPrecedence(currentSymbol, nextSymbol, precedenceFunction)) {
                    currentOperator.addChild(currentOpValue);
                    nextOperator.addChild(currentOperator);
                    lowestOperator = nextOperator;
                } else {
                    nextOperator.addChild(currentOpValue);
                    currentOperator.addChild(nextOperator);
                    lowestOperator = currentOperator;
                }

                currentOperator = nextOperator;
            }
            currentSymbol = nextSymbol;
        }
        return lowestOperator;
    }

    private static <S> boolean hasHigherPrecedence(
            S operatorSymbol, S nextOperatorSymbol, ToIntFunction<S> precedenceFunction) {
        if (nextOperatorSymbol == null) {
            return true;
        } else {
            return precedenceFunction.applyAsInt(operatorSymbol) <= precedenceFunction.applyAsInt(nextOperatorSymbol);
        }
    }

    private static final class OperatorStructure<S> {
        private final S symbol;
        private final List<Object> children;

        private OperatorStructure(S symbol, Object... children) {
            this.symbol = symbol;
            this.children = Arrays.stream(children).collect(Collectors.toList());
        }

        public S getSymbol() {
            return symbol;
        }

        public List<Object> getChildren() {
            return children;
        }

        public void addChild(Object child) {
            children.add(child);
        }
    }
}
