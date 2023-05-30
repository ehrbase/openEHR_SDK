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

import com.nedap.archie.datetime.DateTimeParsers;
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
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.containment.*;
import org.ehrbase.aql.dto.operant.*;
import org.ehrbase.aql.dto.orderby.OrderByExpressionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpressionSymbol;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.*;

public class AqlToDtoVisitor extends AqlParserBaseVisitor<Object> {

    private int containmentId = 0;
    private final Map<String, Integer> identifierMap = new HashMap<>();
    private final MultiValuedMap<String, IdentifiedPath> selectFieldDtoMultiMap = new ArrayListValuedHashMap<>();

    private List<String> errors;

    @Override
    public AqlDto visitSelectQuery(AqlParser.SelectQueryContext ctx) {

        errors = new ArrayList<>();
        AqlDto aqlDto = new AqlDto();

        // select
        aqlDto.setSelect(visitSelectClause(ctx.selectClause()));
        // from
        aqlDto.setFrom(visitFromClause(ctx.fromClause()));
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
                e.getValue().setFromId(identifierMap.get(e.getKey()));
            }
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
    public SelectExpressionDto visitSelectExpr(AqlParser.SelectExprContext ctx) {

        SelectExpressionDto selectExpressionDto = new SelectExpressionDto();

        selectExpressionDto.setColumnExpression(visitColumnExpr(ctx.columnExpr()));

        if (ctx.IDENTIFIER() != null) {
            selectExpressionDto.setAlias(ctx.IDENTIFIER().getText());
        }

        return selectExpressionDto;
    }

    @Override
    public ColumnExpression visitColumnExpr(AqlParser.ColumnExprContext ctx) {

        if (ctx.identifiedPath() != null) {
            return visitIdentifiedPath(ctx.identifiedPath());

        } else if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        } else if (ctx.aggregateFunctionCall() != null) {

            errors.add("aggregate function not yet implemented");
            return new AggregateFunctionDto();
        } else if (ctx.primitive() != null) {

            return visitPrimitive(ctx.primitive());
        } else {

            throw new AqlParseException("Invalid ColumnExpr");
        }
    }

    @Override
    public Primitive visitPrimitive(AqlParser.PrimitiveContext ctx) {

        final Primitive selectPrimitiveDto;

        if (ctx.BOOLEAN() != null) {
            selectPrimitiveDto = new BooleanPrimitiveDto(Boolean.parseBoolean(ctx.getText()));
        } else if (ctx.DATE() != null) {
            String unwrap = unwrapText(ctx);
            selectPrimitiveDto = new TemporalPrimitiveDto(DateTimeParsers.parseTimeValue(unwrap));
        } else if (ctx.numericPrimitive() != null) {
            AqlParser.NumericPrimitiveContext numericPrimitiveContext = ctx.numericPrimitive();
            selectPrimitiveDto = visitNumericPrimitive(numericPrimitiveContext);
        } else if (ctx.STRING() != null) {
            selectPrimitiveDto = new StringPrimitiveDto(unwrapText(ctx));
        } else {
            throw new AqlParseException("Can not handle value " + ctx.getText());
        }

        return selectPrimitiveDto;
    }

    @Override
    public Primitive visitNumericPrimitive(AqlParser.NumericPrimitiveContext ctx) {

        Primitive value;

        if (ctx.REAL() != null) {
            value = new DoublePrimitiveDto(Double.valueOf(ctx.getText()));
        } else if (ctx.INTEGER() != null) {
            value = new LongPrimitiveDto(Long.valueOf(ctx.getText()));
        } else {
            throw new AqlParseException("Can not handle value " + ctx.getText());
        }

        return value;
    }

    @Override
    public IdentifiedPath visitIdentifiedPath(AqlParser.IdentifiedPathContext ctx) {

        IdentifiedPath selectFieldDto = new IdentifiedPath();

        selectFieldDtoMultiMap.put(ctx.IDENTIFIER().getText(), selectFieldDto);
        selectFieldDto.setPath(AqlPath.parse(
                StringUtils.removeStart(getFullText(ctx), ctx.IDENTIFIER().getText())));

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

            final ContainmentDto containmentDto;

            if (classExprOperandContext instanceof AqlParser.ClassExpressionContext) {

                containmentDto = visitClassExpression((AqlParser.ClassExpressionContext) classExprOperandContext);
            } else {

                containmentDto = visitVersionClassExpr((AqlParser.VersionClassExprContext) classExprOperandContext);
            }
            if (ctx.CONTAINS() != null) {

                ContainmentExpresionDto contains = visitContainsExpr(ctx.containsExpr(0));
                if (ctx.NOT() != null) {

                    errors.add("NOT contains not yet implemented");
                } else {
                    containmentDto.setContains(contains);
                }
            }
            return containmentDto;
        }
    }

    @Override
    public ContainmentClassExpressionDto visitClassExpression(AqlParser.ClassExpressionContext ctx) {

        ContainmentClassExpressionDto containmentDto = new ContainmentClassExpressionDto();
        containmentDto.setId(buildContainmentId());
        containmentDto.setType(ctx.IDENTIFIER(0).getText());

        if (ctx.IDENTIFIER().size() == 2) {
            containmentDto.setIdentifier(ctx.IDENTIFIER(1).getText());
            identifierMap.put(containmentDto.getIdentifier(), containmentDto.getId());
        }

        if (ctx.pathPredicate() != null) {
            PredicateDto predicateDto = PredicateHelper.buildPredicate(getFullText(ctx.pathPredicate()));

            containmentDto.setOtherPredicates(predicateDto);
        }

        return containmentDto;
    }

    @Override
    public VersionExpressionDto visitVersionClassExpr(AqlParser.VersionClassExprContext ctx) {

        errors.add("version not yet implemented");
        return new VersionExpressionDto() {};
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
}
