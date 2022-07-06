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

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.condition.Value;
import org.ehrbase.aql.dto.path.AqlPath;

/**
 * @author Stefan Spiska
 */
public class PredicateHelper {

    public static final String[] PREDICATE_DIVIDERS = {" and ", " AND ", " or ", " OR ", ","};
    public static final String NAME_VALUE = "name/value";
    public static final String ARCHETYPE_NODE_ID = "archetype_node_id";

    public static final Comparator<PredicateDto> PREDICATE_DTO_COMPARATOR = new Comparator<PredicateDto>() {
        @Override
        public int compare(PredicateDto o1, PredicateDto o2) {

            if (o1 instanceof PredicateComparisonOperatorDto && o2 instanceof PredicateComparisonOperatorDto) {

                int x = toInt((PredicateComparisonOperatorDto) o1);
                return Integer.compare(x, toInt((PredicateComparisonOperatorDto) o2));
            }

            return 0;
        }

        int toInt(PredicateComparisonOperatorDto dto) {
            switch (dto.getStatement()) {
                case ARCHETYPE_NODE_ID:
                    return 1;
                case NAME_VALUE:
                    return 2;
                default:
                    return 3;
            }
        }
    };

    private PredicateHelper() {
        // NOP
    }

    public static PredicateDto buildPredicate(String predicate) {

        List<Object> boolList = parsePredicate(predicate);

        if (boolList.size() == 1) {
            return (PredicateDto) boolList.get(0);
        } else {

            LogicalOperatorDto predicateLogicalOperatorSymbolPredicateDtoLogicalOperatorDto =
                    buildLogicalOperator(boolList, (Function<
                                    PredicateLogicalOperatorSymbol,
                                    LogicalOperatorDto<PredicateLogicalOperatorSymbol, SimplePredicateDto>>)
                            s -> {
                                if (PredicateLogicalOperatorSymbol.AND.equals(s)) {
                                    return (LogicalOperatorDto<PredicateLogicalOperatorSymbol, SimplePredicateDto>)
                                            new PredicateLogicalAndOperation();
                                } else {

                                    return new PredicateLogicalOrOperation();
                                }
                            });
            return (PredicateDto) predicateLogicalOperatorSymbolPredicateDtoLogicalOperatorDto;
        }
    }

    static List<Object> parsePredicate(String predicate) {

        CharSequence[] split = AqlPath.split2(predicate, null, PREDICATE_DIVIDERS);

        return IntStream.range(0, split.length)
                .mapToObj(i -> {
                    if (i % 2 == 0) {
                        return handleOperator(split[i], i);
                    } else {
                        return handleSymbol(split[i]);
                    }
                })
                .collect(Collectors.toList());
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
                throw new RuntimeException();
        }
    }

    private static PredicateComparisonOperatorDto handleOperator(CharSequence sequence, int i) {
        CharSequence[] split = AqlPath.split2(
                sequence,
                3,
                Arrays.stream(ConditionComparisonOperatorSymbol.values())
                        .map(ConditionComparisonOperatorSymbol::getSymbole)
                        .toArray(String[]::new));
        PredicateComparisonOperatorDto comparisonOperatorDto = new PredicateComparisonOperatorDto();

        if (split.length == 1) {
            if (i == 0) {

                comparisonOperatorDto.setStatement(ARCHETYPE_NODE_ID);
                SimpleValue value = new SimpleValue();
                value.setValue(split[0].toString().trim());
                comparisonOperatorDto.setValue(value);
                comparisonOperatorDto.setSymbol(ConditionComparisonOperatorSymbol.EQ);
            } else if (i == 2) {

                comparisonOperatorDto.setStatement(NAME_VALUE);
                SimpleValue value = new SimpleValue();
                value.setValue(StringUtils.unwrap(split[0].toString().trim(), "'"));
                comparisonOperatorDto.setValue(value);
            }
        } else {

            comparisonOperatorDto.setStatement(split[0].toString().trim());

            comparisonOperatorDto.setValue(parseValue(split[2].toString().trim()));
            comparisonOperatorDto.setSymbol(ConditionComparisonOperatorSymbol.fromSymbol(split[1].toString()));
        }

        return comparisonOperatorDto;
    }

    private static Value parseValue(String s) {

        if (s.startsWith("$")) {
            ParameterValue parameterValue = new ParameterValue();
            parameterValue.setName(StringUtils.removeStart(s, "$"));
            return parameterValue;
        }
        if (s.startsWith("'")) {
            SimpleValue simpleValue = new SimpleValue();

            simpleValue.setValue(StringUtils.unwrap(s, "'"));
            return simpleValue;
        }

        if (StringUtils.contains(s, '.')) {
            SimpleValue simpleValue = new SimpleValue();
            simpleValue.setValue(Double.parseDouble(s));
            return simpleValue;
        } else {
            SimpleValue simpleValue = new SimpleValue();
            simpleValue.setValue(Long.parseLong(s));
            return simpleValue;
        }
    }

    private static void format(String statement, StringBuilder sb, Value value) {
        if (value instanceof SimpleValue) {
            Object o = ((SimpleValue) value).getValue();

            if (NAME_VALUE.equals(statement)) {
                sb.append(StringUtils.wrap(o.toString(), "'"));
            } else {
                sb.append(o);
            }
        }
    }

    public static void format(
            StringBuilder sb, PredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (predicateDto instanceof ParameterValue) {
            sb.append("$").append(((ParameterValue) predicateDto).getName());
        } else if (predicateDto instanceof PredicateComparisonOperatorDto) {
            if (isShorten((SimplePredicateDto) predicateDto, otherPredicatesFormat)) {
                format(
                        ((PredicateComparisonOperatorDto) predicateDto).getStatement(),
                        sb,
                        ((PredicateComparisonOperatorDto) predicateDto).getValue());
            } else if (!isNone((SimplePredicateDto) predicateDto, otherPredicatesFormat)) {
                sb.append(((PredicateComparisonOperatorDto) predicateDto).getStatement())
                        .append(((PredicateComparisonOperatorDto) predicateDto)
                                .getSymbol()
                                .getSymbole());
                format(
                        ((PredicateComparisonOperatorDto) predicateDto).getStatement(),
                        sb,
                        ((PredicateComparisonOperatorDto) predicateDto).getValue());
            }
        } else if (predicateDto instanceof PredicateLogicalAndOperation) {
            List<SimplePredicateDto> values = ((PredicateLogicalAndOperation) predicateDto).getValues();
            values.sort(PREDICATE_DTO_COMPARATOR);
            for (int i = 0; i < values.size(); i++) {
                if (i > 0 && !isNone(values.get(i), otherPredicatesFormat)) {
                    if (isShorten(values.get(i), otherPredicatesFormat)) {
                        sb.append(",");
                    } else {
                        sb.append(" ").append("and").append(" ");
                    }
                }
                if (!isNone(values.get(i), otherPredicatesFormat)) {
                    format(sb, values.get(i), otherPredicatesFormat);
                }
            }
        } else if (predicateDto instanceof PredicateLogicalOrOperation) {
            if (otherPredicatesFormat.equals(AqlPath.OtherPredicatesFormat.SHORTED)) {
                otherPredicatesFormat = AqlPath.OtherPredicatesFormat.FULL;
            }
            List<SimplePredicateDto> values = ((PredicateLogicalOrOperation) predicateDto).getValues();
            for (int i = 0; i < values.size(); i++) {

                if (i > 0 && !isNone(values.get(i), otherPredicatesFormat)) {
                    sb.append(" ").append("or").append(" ");
                }
                format(sb, values.get(i), otherPredicatesFormat);
            }
        }
    }

    private static boolean isNone(
            SimplePredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (!otherPredicatesFormat.equals(AqlPath.OtherPredicatesFormat.NONE)) {
            return false;
        }

        if (predicateDto instanceof PredicateComparisonOperatorDto) {
            return !Objects.equals(ARCHETYPE_NODE_ID, ((PredicateComparisonOperatorDto) predicateDto).getStatement());
        }

        return false;
    }

    private static boolean isShorten(
            SimplePredicateDto predicateDto, AqlPath.OtherPredicatesFormat otherPredicatesFormat) {

        if (predicateDto instanceof PredicateComparisonOperatorDto
                && ((PredicateComparisonOperatorDto) predicateDto)
                        .getStatement()
                        .equals(ARCHETYPE_NODE_ID)) {
            return true;
        }
        if (otherPredicatesFormat.equals(AqlPath.OtherPredicatesFormat.FULL)) {
            return false;
        }
        if (predicateDto instanceof PredicateLogicalOrOperation) {
            return false;
        }
        if (predicateDto instanceof PredicateLogicalAndOperation) {
            return CollectionUtils.isNotEmpty(((PredicateLogicalAndOperation) predicateDto).getValues())
                    && isShorten(
                            ((PredicateLogicalAndOperation) predicateDto)
                                    .getValues()
                                    .get(((PredicateLogicalAndOperation) predicateDto)
                                            .getValues()
                                            .size()),
                            otherPredicatesFormat);
        }
        if (predicateDto instanceof PredicateComparisonOperatorDto) {

            return List.of(NAME_VALUE, ARCHETYPE_NODE_ID)
                            .contains(((PredicateComparisonOperatorDto) predicateDto).getStatement())
                    && ((PredicateComparisonOperatorDto) predicateDto)
                            .getSymbol()
                            .equals(ConditionComparisonOperatorSymbol.EQ);
        }
        return false;
    }

    public static Optional<PredicateComparisonOperatorDto> find(PredicateDto predicateDto, String statement) {

        if (predicateDto instanceof PredicateLogicalOrOperation) {

            return ((PredicateLogicalOrOperation) predicateDto)
                    .getValues().stream()
                            .map(p -> find(p, statement))
                            .flatMap(Optional::stream)
                            .findAny();
        }

        if (predicateDto instanceof PredicateLogicalAndOperation) {

            return ((PredicateLogicalAndOperation) predicateDto)
                    .getValues().stream()
                            .map(p -> find(p, statement))
                            .flatMap(Optional::stream)
                            .findAny();
        }

        if (predicateDto instanceof PredicateComparisonOperatorDto) {
            return Optional.of((PredicateComparisonOperatorDto) predicateDto)
                    .filter(p -> p.getStatement().equals(statement));
        }

        return Optional.empty();
    }

    public static <P extends PredicateDto> P clone(P predicateDto) {

        if (predicateDto instanceof PredicateLogicalAndOperation) {

            PredicateLogicalAndOperation clone = new PredicateLogicalAndOperation();
            clone.setValues(((PredicateLogicalAndOperation) predicateDto)
                    .getValues().stream().map(PredicateHelper::clone).collect(Collectors.toList()));
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
            PredicateLogicalAndOperation and = new PredicateLogicalAndOperation();
            and.getValues().add(simplePredicateDto);
            and.getValues().add(add);

            return and;
        }
    }
}
