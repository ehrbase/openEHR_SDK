/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.LogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.containment.*;
import org.ehrbase.aql.dto.orderby.OrderByExpressionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpressionSymbol;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalOrOperation;
import org.ehrbase.aql.dto.select.FunctionDto;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;

public class AqlToDtoVisitor extends AqlParserBaseVisitor<Object> {

    private int containmentId = 0;
    private final Map<String, Integer> identifierMap = new HashMap<>();
    private final MultiValuedMap<String, SelectFieldDto> selectFieldDtoMultiMap = new ArrayListValuedHashMap<>();

    private List<String> errors;

    @Override
    public AqlDto visitSelectQuery(AqlParser.SelectQueryContext ctx) {

        errors = new ArrayList<>();
        AqlDto aqlDto = new AqlDto();

        // select
        aqlDto.setSelect(visitSelectClause(ctx.selectClause()));
        // from
        aqlDto.setContains(visitFromClause(ctx.fromClause()));
        // where
        if (ctx.whereClause() != null) {
            aqlDto.setWhere(visitWhereClause(ctx.whereClause()));
        }
        // oder by
        if (ctx.orderByClause() != null) {
            aqlDto.setOrderBy(visitOrderByClause(ctx.orderByClause()));
        }

        if (ctx.limitClause() != null) {

            errors.add("limit not yet implemented");
        }

        selectFieldDtoMultiMap.entries().forEach(e -> {
            if (identifierMap.containsKey(e.getKey())) {
                e.getValue().setContainmentId(identifierMap.get(e.getKey()));
            }
        });

        // replace reference by name
        selectFieldDtoMultiMap.entries().stream()
                .filter(e -> !identifierMap.containsKey(e.getKey()))
                .forEach(e -> {
                    SelectFieldDto selectFieldDto = selectFieldDtoMultiMap.values().stream()
                            .filter(d -> e.getKey().equals(d.getName()))
                            .findAny()
                            .orElseThrow();

                    SelectFieldDto value = e.getValue();
                    value.setName(selectFieldDto.getName());
                    value.setAqlPath(selectFieldDto.getAqlPathDto());
                    value.setContainmentId(selectFieldDto.getContainmentId());
                });

        return aqlDto;
    }

    @Override
    public SelectDto visitSelectClause(AqlParser.SelectClauseContext ctx) {

        SelectDto selectDto = new SelectDto();

        selectDto.setDistinct(ctx.DISTINCT() != null);

        if (ctx.top() != null) {

            errors.add("top not yet implemented");
        }

        selectDto.setStatement(
                ctx.selectExpr().stream().map(this::visitSelectExpr).collect(Collectors.toList()));

        return selectDto;
    }

    @Override
    public SelectStatementDto visitSelectExpr(AqlParser.SelectExprContext ctx) {

        SelectStatementDto selectStatementDto = visitColumnExpr(ctx.columnExpr());

        if (ctx.IDENTIFIER() != null) {
            selectStatementDto.setName(ctx.IDENTIFIER().getText());
        }

        return selectStatementDto;
    }

    @Override
    public SelectStatementDto visitColumnExpr(AqlParser.ColumnExprContext ctx) {

        if (ctx.identifiedPath() != null) {
            return visitIdentifiedPath(ctx.identifiedPath());

        } else if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        } else if (ctx.aggregateFunctionCall() != null) {

            errors.add("aggregate function not yet implemented");
            return new DummySelectStatementDto();
        } else if (ctx.primitive() != null) {

            errors.add("primitive function not yet implemented");
            return new DummySelectStatementDto();
        } else {

            throw new AqlParseException("Invalid ColumnExpr");
        }
    }

    @Override
    public SelectFieldDto visitIdentifiedPath(AqlParser.IdentifiedPathContext ctx) {

        SelectFieldDto selectFieldDto = new SelectFieldDto();

        selectFieldDtoMultiMap.put(ctx.IDENTIFIER().getText(), selectFieldDto);
        selectFieldDto.setAqlPath(
                StringUtils.removeStart(getFullText(ctx), ctx.IDENTIFIER().getText()));

        return selectFieldDto;
    }

    @Override
    public FunctionDto visitFunctionCall(AqlParser.FunctionCallContext ctx) {

        errors.add("function not yet implemented");
        return new FunctionDto();
    }

    @Override
    public ContainmentExpresionDto visitFromClause(AqlParser.FromClauseContext ctx) {

        return visitFromExpr(ctx.fromExpr());
    }

    @Override
    public ContainmentExpresionDto visitFromExpr(AqlParser.FromExprContext ctx) {

        return visitContainsExpr(ctx.containsExpr());
    }

    @Override
    public ContainmentExpresionDto visitContainsExpr(AqlParser.ContainsExprContext ctx) {

        ContainmentLogicalOperatorSymbol symbol = extractSymbol(ctx);

        if (symbol != null) {

            AqlParser.ContainsExprContext current = ctx;

            List<Object> boolList = new ArrayList<>();

            while (current != null) {

                boolList.add(visitContainsExpr(ctx.containsExpr(0)));
                Optional.ofNullable(extractSymbol(current)).ifPresent(boolList::add);

                if (ctx.containsExpr().size() == 2) {
                    current = ctx.containsExpr(1);
                } else {
                    current = null;
                }
            }
            return buildContainmentLogicalOperator(boolList);

        } else if (ctx.SYM_LEFT_PAREN() != null) {

            return visitContainsExpr(ctx.containsExpr(0));
        } else {

            AqlParser.ClassExprOperandContext classExprOperandContext = ctx.classExprOperand();
            if (classExprOperandContext instanceof AqlParser.ClassExpressionContext) {

                ContainmentDto containmentDto =
                        visitClassExpression((AqlParser.ClassExpressionContext) classExprOperandContext);

                if (ctx.CONTAINS() != null) {

                    ContainmentExpresionDto contains = visitContainsExpr(ctx.containsExpr(0));
                    if (ctx.NOT() != null) {

                        errors.add("NOT contains not yet implemented");
                    } else {
                        containmentDto.setContains(contains);
                    }
                }
                return containmentDto;
            } else {
                return visitVersionClassExpr((AqlParser.VersionClassExprContext) classExprOperandContext);
            }
        }
    }

    @Override
    public ContainmentDto visitClassExpression(AqlParser.ClassExpressionContext ctx) {

        ContainmentDto containmentDto = new ContainmentDto();
        containmentDto.setId(buildContainmentId());
        containmentDto.getContainment().setType(ctx.IDENTIFIER(0).getText());

        if (ctx.IDENTIFIER().size() == 2) {
            containmentDto.setIdentifier(ctx.IDENTIFIER(1).getText());
            identifierMap.put(containmentDto.getIdentifier(), containmentDto.getId());
        }

        if (ctx.pathPredicate() != null) {
            PredicateDto predicateDto = PredicateHelper.buildPredicate(getFullText(ctx.pathPredicate()));

            containmentDto.getContainment().setOtherPredicates(predicateDto);
            PredicateHelper.find(predicateDto, PredicateHelper.ARCHETYPE_NODE_ID)
                    .ifPresent(s -> containmentDto
                            .getContainment()
                            .setArchetypeId(
                                    ((SimpleValue) s.getValue()).getValue().toString()));
        }

        return containmentDto;
    }

    @Override
    public ContainmentExpresionDto visitVersionClassExpr(AqlParser.VersionClassExprContext ctx) {

        errors.add("version not yet implemented");
        return new ContainmentExpresionDto() {};
    }

    public ContainmentLogicalOperator buildContainmentLogicalOperator(List<Object> boolList) {

        return (ContainmentLogicalOperator) buildLogicalOperator(boolList, (Function<
                        ContainmentLogicalOperatorSymbol,
                        LogicalOperatorDto<ContainmentLogicalOperatorSymbol, ContainmentExpresionDto>>)
                s -> {
                    ContainmentLogicalOperator conditionLogicalOperatorDto = new ContainmentLogicalOperator();
                    conditionLogicalOperatorDto.setSymbol(s);
                    conditionLogicalOperatorDto.setValues(new ArrayList<>());

                    return conditionLogicalOperatorDto;
                });
    }

    @Override
    public ConditionDto visitWhereClause(AqlParser.WhereClauseContext ctx) {

        errors.add("where not yet implemented");

        return null;
    }

    @Override
    public List<OrderByExpressionDto> visitOrderByClause(AqlParser.OrderByClauseContext ctx) {

        errors.add("OrderBy not yet implemented");

        return null;
    }

    public List<String> getErrors() {
        return errors;
    }

    public static String getFullText(ParserRuleContext context) {
        if (context.start == null
                || context.stop == null
                || context.start.getStartIndex() < 0
                || context.stop.getStopIndex() < 0) return context.getText(); // Fallback

        return context.start
                .getInputStream()
                .getText(Interval.of(context.start.getStartIndex(), context.stop.getStopIndex()));
    }

    private ConditionDto to(PredicateDto predicateDto, int containmentId) {
        if (predicateDto instanceof PredicateComparisonOperatorDto) {
            ConditionComparisonOperatorDto conditionComparisonOperatorDto = new ConditionComparisonOperatorDto();
            SelectFieldDto statement = new SelectFieldDto();
            statement.setContainmentId(containmentId);

            String aqlStr = ((PredicateComparisonOperatorDto) predicateDto).getStatement();
            if (StringUtils.isEmpty(aqlStr)) {
                statement.setAqlPath(AqlPath.ROOT_PATH);
            } else {
                statement.setAqlPath(AqlPath.parse(aqlStr));
            }
            conditionComparisonOperatorDto.setStatement(statement);
            conditionComparisonOperatorDto.setSymbol(((PredicateComparisonOperatorDto) predicateDto).getSymbol());
            conditionComparisonOperatorDto.setValue(((PredicateComparisonOperatorDto) predicateDto).getValue());
            return conditionComparisonOperatorDto;
        }

        if (predicateDto instanceof PredicateLogicalAndOperation) {
            ConditionLogicalOperatorDto and = new ConditionLogicalOperatorDto();
            and.setSymbol(ConditionLogicalOperatorSymbol.AND);
            and.setValues(((PredicateLogicalAndOperation) predicateDto)
                    .getValues().stream().map(p -> to(p, containmentId)).collect(Collectors.toList()));
            return and;
        }

        if (predicateDto instanceof PredicateLogicalOrOperation) {
            ConditionLogicalOperatorDto or = new ConditionLogicalOperatorDto();
            or.setSymbol(ConditionLogicalOperatorSymbol.OR);
            or.setValues(((PredicateLogicalOrOperation) predicateDto)
                    .getValues().stream().map(p -> to(p, containmentId)).collect(Collectors.toList()));
            return or;
        }

        return null;
    }

    private static <T, S extends LogicalOperatorSymbol> LogicalOperatorDto<S, T> buildLogicalOperator(
            OperatorStructure<S> structure, Function<S, LogicalOperatorDto<S, T>> creator) {

        LogicalOperatorDto<S, T> operator = creator.apply(structure.getSymbol());

        Stream<T> stream = structure.getChildren().stream().map(v -> {
            if (v instanceof OperatorStructure) {
                return (T) buildLogicalOperator((OperatorStructure<S>) v, creator);
            } else {
                return (T) v;
            }
        });
        return operator.addValues(stream);
    }

    public static <S extends LogicalOperatorSymbol, T> LogicalOperatorDto<S, T> buildLogicalOperator(
            List<Object> boolList, Function<S, LogicalOperatorDto<S, T>> creator) {
        OperatorStructure<S> structure = buildLogicalOperatorStructure(boolList);
        return buildLogicalOperator(structure, creator);
    }

    private static final class OperatorStructure<S extends LogicalOperatorSymbol> {
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

    private static <S extends LogicalOperatorSymbol> OperatorStructure<S> buildLogicalOperatorStructure(
            List<Object> boolList) {

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

                if (hasHigherPrecedence(currentSymbol, nextSymbol)) {
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

    private ConditionLogicalOperatorSymbol extractSymbolTerminal(TerminalNode child) {
        if (child == null) {
            return null;
        }

        switch (child.getSymbol().getText().toLowerCase(Locale.ROOT)) {
            case "or":
                return ConditionLogicalOperatorSymbol.OR;
            case "and":
                return ConditionLogicalOperatorSymbol.AND;
            case "xor":
                throw new AqlParseException("XOR not supported");
            default:
                return null;
        }
    }

    private static String unwrapText(RuleContext ctx) {
        return StringUtils.unwrap(StringUtils.unwrap(ctx.getText(), "'"), "\"");
    }

    private static ParameterValue parameter(RuleContext ctx) {
        return new ParameterValue(StringUtils.removeStart(ctx.getText(), "$"), "?");
    }

    private OrderByExpressionSymbol extractSymbol(AqlParser.OrderByExprContext ctx) {
        if (ctx.DESC() != null || ctx.DESCENDING() != null) {
            return OrderByExpressionSymbol.DESC;
        } else {
            return OrderByExpressionSymbol.ASC;
        }
    }

    private ConditionComparisonOperatorSymbol extractSymbol(AqlParser.IdentifiedExprContext ctx) {
        switch (ctx.COMPARISON_OPERATOR().getText()) {
            case "=":
                return ConditionComparisonOperatorSymbol.EQ;
            case "!=":
                return ConditionComparisonOperatorSymbol.NEQ;
            case ">":
                return ConditionComparisonOperatorSymbol.GT;
            case ">=":
                return ConditionComparisonOperatorSymbol.GT_EQ;
            case "<":
                return ConditionComparisonOperatorSymbol.LT;
            case "<=":
                return ConditionComparisonOperatorSymbol.LT_EQ;
            default:
                throw new AqlParseException(
                        "Unknown Token " + ctx.COMPARISON_OPERATOR().getText());
        }
    }

    private static boolean hasHigherPrecedence(
            LogicalOperatorSymbol operatorSymbol, LogicalOperatorSymbol nextOperatorSymbol) {
        if (nextOperatorSymbol == null) {
            return true;
        } else {
            return operatorSymbol.getPrecedence() <= nextOperatorSymbol.getPrecedence();
        }
    }

    private int buildContainmentId() {
        return containmentId++;
    }

    private ContainmentLogicalOperatorSymbol extractSymbol(AqlParser.ContainsExprContext ctx) {
        if (ctx == null) {
            return null;
        }
        if (ctx.OR() != null) {
            return ContainmentLogicalOperatorSymbol.OR;
        } else if (ctx.AND() != null) {
            return ContainmentLogicalOperatorSymbol.AND;
        } else {
            return null;
        }
    }

    private static class DummySelectStatementDto implements SelectStatementDto {

        private String name;

        @Override
        public String getName() {
            return name;
        }

        @Override
        public void setName(String name) {
            this.name = name;
        }
    }
}
