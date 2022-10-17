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
package org.ehrbase.aql.dto.path.predicate;

import static org.ehrbase.aql.parser.AqlToDtoVisitor.buildLogicalOperator;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.condition.Value;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class PredicateHelper {

    public static final String NAME_VALUE = "name/value";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";

    private static final String[] OPERATOR_SYMBOLS = Arrays.stream(ConditionComparisonOperatorSymbol.values())
            .map(ConditionComparisonOperatorSymbol::getSymbole)
            .toArray(String[]::new);

    private static int comparisonKey(PredicateDto dto) {
        if (dto instanceof PredicateComparisonOperatorDto) {
            switch (((PredicateComparisonOperatorDto) dto).getStatement()) {
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

    static final Comparator<PredicateDto> PREDICATE_DTO_COMPARATOR =
            Comparator.comparingInt(PredicateHelper::comparisonKey);

    private PredicateHelper() {
        // NOP
    }

    public static PredicateDto buildPredicate(CharSequence predicate) {

        Object[] boolList = parsePredicate(predicate);

        if (boolList.length == 1) {
            return (PredicateDto) boolList[0];
        } else {

            LogicalOperatorDto predicateLogicalOperatorSymbolPredicateDtoLogicalOperatorDto =
                    buildLogicalOperator(Arrays.asList(boolList), (Function<
                                    PredicateLogicalOperatorSymbol,
                                    LogicalOperatorDto<PredicateLogicalOperatorSymbol, SimplePredicateDto>>)
                            s -> {
                                if (PredicateLogicalOperatorSymbol.AND.equals(s)) {
                                    return new PredicateLogicalAndOperation();
                                } else {
                                    return new PredicateLogicalOrOperation();
                                }
                            });
            return (PredicateDto) predicateLogicalOperatorSymbolPredicateDtoLogicalOperatorDto;
        }
    }

    static Object[] parsePredicate(CharSequence predicate) {

        List<CharSequence> split = AqlPath.split(predicate, -1, true, " and ", " AND ", " or ", " OR ", ",");

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
        switch (sequence.toString().trim()) {
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

    private static PredicateComparisonOperatorDto handleOperator(CharSequence sequence, int i) {
        List<CharSequence> split = AqlPath.split(sequence, 3, true, OPERATOR_SYMBOLS);
        PredicateComparisonOperatorDto comparisonOperatorDto = new PredicateComparisonOperatorDto();

        if (split.size() == 1) {
            if (i == 0) {

                comparisonOperatorDto.setStatement(ARCHETYPE_NODE_ID);
                comparisonOperatorDto.setValue(
                        parseValue(ARCHETYPE_NODE_ID, split.get(0).toString().trim()));
                comparisonOperatorDto.setSymbol(ConditionComparisonOperatorSymbol.EQ);
            } else if (i == 2) {

                comparisonOperatorDto.setStatement(NAME_VALUE);
                comparisonOperatorDto.setValue(
                        parseValue(NAME_VALUE, split.get(0).toString().trim()));
                comparisonOperatorDto.setSymbol(ConditionComparisonOperatorSymbol.EQ);
            }
        } else {

            comparisonOperatorDto.setStatement(split.get(0).toString().trim());

            comparisonOperatorDto.setValue(parseValue(
                    comparisonOperatorDto.getStatement(),
                    split.get(2).toString().trim()));
            comparisonOperatorDto.setSymbol(
                    ConditionComparisonOperatorSymbol.fromSymbol(split.get(1).toString()));
        }

        return comparisonOperatorDto;
    }

    private static Value parseValue(String statement, String s) {

        if (s.startsWith("$")) {
            ParameterValue parameterValue = new ParameterValue();
            parameterValue.setName(s.substring(1));
            return parameterValue;
        }

        Object value;
        if (ARCHETYPE_NODE_ID.equals(statement)) {
            value = s;
        } else if (s.startsWith("'")) {
            value = StringUtils.unwrap(s, "'");
        } else if (StringUtils.contains(s, '.')) {
            value = Double.parseDouble(s);
        } else {
            value = Long.parseLong(s);
        }
        return new SimpleValue(value);
    }

    private static void format(String statement, StringBuilder sb, Value value) {
        if (value instanceof SimpleValue) {
            Object o = ((SimpleValue) value).getValue();

            if (o instanceof String && !ARCHETYPE_NODE_ID.equals(statement)) {
                sb.append(StringUtils.wrap(o.toString(), "'"));
            } else {
                sb.append(o);
            }
        } else if (value instanceof ParameterValue) {

            sb.append('$').append(((ParameterValue) value).getName());
        }
    }

    public static String format(PredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        StringBuilder sb = new StringBuilder();
        format(sb, predicateDto, otherPredicatesFormat);
        return sb.toString();
    }

    public static void format(
            StringBuilder sb, PredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (predicateDto instanceof ParameterValue) {
            sb.append('$').append(((ParameterValue) predicateDto).getName());
        } else if (predicateDto instanceof PredicateComparisonOperatorDto) {
            formatPredicateComparisonOperatorDto(
                    sb, (PredicateComparisonOperatorDto) predicateDto, otherPredicatesFormat);
        } else if (predicateDto instanceof PredicateLogicalAndOperation) {
            formatPredicateLogicalAndOperation(sb, (PredicateLogicalAndOperation) predicateDto, otherPredicatesFormat);
        } else if (predicateDto instanceof PredicateLogicalOrOperation) {
            formatPredicateLogicalOrOperation(sb, (PredicateLogicalOrOperation) predicateDto, otherPredicatesFormat);
        }
    }

    private static void formatPredicateLogicalOrOperation(
            StringBuilder sb,
            PredicateLogicalOrOperation predicateDto,
            AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        if (otherPredicatesFormat.equals(AqlPath.OtherPredicatesFormat.SHORTED)) {
            otherPredicatesFormat = AqlPath.OtherPredicatesFormat.FULL;
        }
        List<SimplePredicateDto> values = predicateDto.getValues();
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
        SimplePredicateDto[] values = predicateDto.getValues().toArray(SimplePredicateDto[]::new);
        Arrays.sort(values, PREDICATE_DTO_COMPARATOR);

        for (int i = 0; i < values.length; i++) {
            SimplePredicateDto value = values[i];
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
            PredicateComparisonOperatorDto predicateDto,
            AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        if (isShorten(predicateDto, otherPredicatesFormat)) {
            format(predicateDto.getStatement(), sb, predicateDto.getValue());
        } else if (!isNone(predicateDto, otherPredicatesFormat)) {
            sb.append(predicateDto.getStatement())
                    .append(predicateDto.getSymbol().getSymbole());
            format(predicateDto.getStatement(), sb, predicateDto.getValue());
        }
    }

    private static boolean isNone(
            SimplePredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {
        return AqlPath.OtherPredicatesFormat.NONE == otherPredicatesFormat
                && predicateDto instanceof PredicateComparisonOperatorDto
                && !ARCHETYPE_NODE_ID.equals(((PredicateComparisonOperatorDto) predicateDto).getStatement());
    }

    private static boolean isShorten(
            SimplePredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        boolean isCompOp = predicateDto instanceof PredicateComparisonOperatorDto;

        if (isCompOp
                && ((PredicateComparisonOperatorDto) predicateDto)
                        .getStatement()
                        .equals(ARCHETYPE_NODE_ID)) {
            return true;
        }
        if (otherPredicatesFormat == AqlPath.OtherPredicatesFormat.FULL) {
            return false;
        }

        if (isCompOp) {
            PredicateComparisonOperatorDto cmpOpDto = (PredicateComparisonOperatorDto) predicateDto;
            return ConditionComparisonOperatorSymbol.EQ == cmpOpDto.getSymbol()
                    && List.of(NAME_VALUE, ARCHETYPE_NODE_ID).contains(cmpOpDto.getStatement());

        } else if (predicateDto instanceof PredicateLogicalOrOperation) {
            return false;

        } else if (predicateDto instanceof PredicateLogicalAndOperation) {
            List<SimplePredicateDto> andOpVals = ((PredicateLogicalAndOperation) predicateDto).getValues();
            return CollectionUtils.isNotEmpty(andOpVals)
                    && isShorten(andOpVals.get(andOpVals.size() - 1), otherPredicatesFormat);
        } else {
            return false;
        }
    }

    public static Optional<PredicateComparisonOperatorDto> find(PredicateDto predicateDto, String statement) {

        if (predicateDto instanceof PredicateComparisonOperatorDto) {
            if (statement.equals(((PredicateComparisonOperatorDto) predicateDto).getStatement())) {
                return Optional.of((PredicateComparisonOperatorDto) predicateDto);
            } else {
                return Optional.empty();
            }
        }

        Deque<PredicateDto> deque;
        {
            List<SimplePredicateDto> values;
            if (predicateDto instanceof PredicateLogicalOrOperation) {
                values = ((PredicateLogicalOrOperation) predicateDto).getValues();
            } else if (predicateDto instanceof PredicateLogicalAndOperation) {
                values = ((PredicateLogicalAndOperation) predicateDto).getValues();
            } else {
                return Optional.empty();
            }

            for (SimplePredicateDto v : values) {
                if (v instanceof PredicateComparisonOperatorDto) {
                    PredicateComparisonOperatorDto cmpOp = (PredicateComparisonOperatorDto) v;
                    if (statement.equals(cmpOp.getStatement())) {
                        return Optional.of(cmpOp);
                    }
                }
            }
            deque = new ArrayDeque<>(values);
        }

        deque.add(predicateDto);

        while (!deque.isEmpty()) {
            var pred = deque.pop();

            List<SimplePredicateDto> values;
            if (pred instanceof PredicateLogicalOrOperation) {
                values = ((PredicateLogicalOrOperation) pred).getValues();
            } else if (pred instanceof PredicateLogicalAndOperation) {
                values = ((PredicateLogicalAndOperation) pred).getValues();
            } else {
                continue;
            }
            for (SimplePredicateDto v : values) {
                if (v instanceof PredicateComparisonOperatorDto) {
                    PredicateComparisonOperatorDto cmpOp = (PredicateComparisonOperatorDto) v;
                    if (statement.equals(cmpOp.getStatement())) {
                        return Optional.of(cmpOp);
                    }
                }
            }
            deque.addAll(values);
        }

        return Optional.empty();
    }

    public static <P extends PredicateDto> P clone(P predicateDto) {

        if (predicateDto instanceof PredicateLogicalAndOperation) {

            PredicateLogicalAndOperation clone = new PredicateLogicalAndOperation();
            clone.setValues(new ArrayList<>(((PredicateLogicalAndOperation) predicateDto).getValues())
                    .stream().map(PredicateHelper::clone).collect(Collectors.toList()));
            return (P) clone;
        }

        if (predicateDto instanceof PredicateComparisonOperatorDto) {

            return (P) new PredicateComparisonOperatorDto((PredicateComparisonOperatorDto) predicateDto);
        }

        return predicateDto;
    }

    public static SimplePredicateDto add(SimplePredicateDto simplePredicateDto, SimplePredicateDto add) {
        if (simplePredicateDto instanceof PredicateLogicalAndOperation) {
            ((PredicateLogicalAndOperation) simplePredicateDto).getValues().add(add);
            return simplePredicateDto;
        } else {
            return new PredicateLogicalAndOperation(simplePredicateDto, add);
        }
    }

    /**
     * Return a clone of <code>and</code> with all {@link PredicateComparisonOperatorDto} removed where {@link PredicateComparisonOperatorDto#getStatement()} is in <code>remove</code>
     * @param and
     * @param remove
     * @return
     */
    public static PredicateLogicalAndOperation remove(PredicateLogicalAndOperation and, String... remove) {

        PredicateLogicalAndOperation clone = new PredicateLogicalAndOperation();

        List<SimplePredicateDto> values = and.getValues().stream()
                .map(v -> {
                    if (v instanceof PredicateLogicalAndOperation) {
                        return remove((PredicateLogicalAndOperation) v, remove);
                    } else if (v instanceof PredicateComparisonOperatorDto
                            && !ArrayUtils.contains(remove, ((PredicateComparisonOperatorDto) v).getStatement())) {
                        return clone(v);
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        clone.setValues(values);

        return clone;
    }
}
