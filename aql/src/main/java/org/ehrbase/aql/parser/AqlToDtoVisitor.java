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
import java.util.Collections;
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
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.CollectionUtils;
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
import org.ehrbase.aql.dto.condition.ExistsConditionOperatorDto;
import org.ehrbase.aql.dto.condition.LikeOperatorDto;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.condition.MatchesOperatorDto;
import org.ehrbase.aql.dto.condition.NotConditionOperatorDto;
import org.ehrbase.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.aql.dto.containment.ContainmentSetOperatorSymbol;
import org.ehrbase.aql.dto.containment.ContainmentVersionExpression;
import org.ehrbase.aql.dto.operand.AQLFunction;
import org.ehrbase.aql.dto.operand.AggregateFunctionDto;
import org.ehrbase.aql.dto.operand.BooleanPrimitiveDto;
import org.ehrbase.aql.dto.operand.ColumnExpression;
import org.ehrbase.aql.dto.operand.ComparisonLeftOperator;
import org.ehrbase.aql.dto.operand.DoublePrimitiveDto;
import org.ehrbase.aql.dto.operand.IdentifiedPath;
import org.ehrbase.aql.dto.operand.LikeOperant;
import org.ehrbase.aql.dto.operand.LongPrimitiveDto;
import org.ehrbase.aql.dto.operand.MatchesOperant;
import org.ehrbase.aql.dto.operand.ParameterDto;
import org.ehrbase.aql.dto.operand.Primitive;
import org.ehrbase.aql.dto.operand.SingleRowFunktion;
import org.ehrbase.aql.dto.operand.StringPrimitiveDto;
import org.ehrbase.aql.dto.operand.TemporalPrimitiveDto;
import org.ehrbase.aql.dto.operand.Terminal;
import org.ehrbase.aql.dto.operand.TerminologyFunctionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpression;
import org.ehrbase.aql.dto.orderby.OrderByExpression.OrderByDirection;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.Select;
import org.ehrbase.aql.dto.select.SelectExpression;
import org.ehrbase.util.exception.SdkException;

public class AqlToDtoVisitor extends AqlParserBaseVisitor<Object> {

    private final Map<String, AbstractContainmentExpression> identifierMap = new HashMap<>();
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

            AqlParser.LimitClauseContext limitClauseContext = ctx.limitClause();

            aqlDto.setLimit(Integer.parseInt(limitClauseContext.limit.getText()));

            if (limitClauseContext.offset != null) {
                aqlDto.setOffset(Integer.parseInt(limitClauseContext.offset.getText()));
            }
        }

        selectFieldDtoMultiMap.entries().forEach(e -> {
            if (identifierMap.containsKey(e.getKey())) {
                e.getValue().setFrom(identifierMap.get(e.getKey()));
            }
        });

        return aqlDto;
    }

    @Override
    public Select visitSelectClause(AqlParser.SelectClauseContext ctx) {

        Select select = new Select();

        select.setDistinct(ctx.DISTINCT() != null);

        if (ctx.top() != null) {

            errors.add("top not yet implemented");
        }

        select.setStatement(ctx.selectExpr().stream().map(this::visitSelectExpr).collect(Collectors.toList()));

        return select;
    }

    @Override
    public SelectExpression visitSelectExpr(AqlParser.SelectExprContext ctx) {

        SelectExpression selectExpression = new SelectExpression();

        selectExpression.setColumnExpression(visitColumnExpr(ctx.columnExpr()));

        if (ctx.IDENTIFIER() != null) {
            selectExpression.setAlias(ctx.IDENTIFIER().getText());
        }

        return selectExpression;
    }

    @Override
    public ColumnExpression visitColumnExpr(AqlParser.ColumnExprContext ctx) {

        if (ctx.identifiedPath() != null) {
            return visitIdentifiedPath(ctx.identifiedPath());

        } else if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        } else if (ctx.aggregateFunctionCall() != null) {

            return visitAggregateFunctionCall(ctx.aggregateFunctionCall());
        } else if (ctx.primitive() != null) {

            return visitPrimitive(ctx.primitive());
        } else {

            throw new AqlParseException("Invalid ColumnExpr");
        }
    }

    @Override
    public AggregateFunctionDto visitAggregateFunctionCall(AqlParser.AggregateFunctionCallContext ctx) {

        AggregateFunctionDto dto = new AggregateFunctionDto();

        final AQLFunction aqlFunction = findFunctionName(ctx.name);

        dto.setFunctionName(aqlFunction);
        dto.setIdentifiedPath(visitIdentifiedPath(ctx.identifiedPath()));

        return dto;
    }

    private static AQLFunction findFunctionName(Token name) {
        final AQLFunction aqlFunction;
        try {
            aqlFunction = AQLFunction.valueOf(name.getText().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new SdkException(String.format("Unknown function %s ", name.getText()));
        }
        return aqlFunction;
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
    public SingleRowFunktion visitFunctionCall(AqlParser.FunctionCallContext ctx) {

        SingleRowFunktion dto = new SingleRowFunktion();
        dto.setFunctionName(findFunctionName(ctx.name));

        dto.setOperantList(ctx.terminal().stream().map(this::visitTerminal).toList());

        return dto;
    }

    @Override
    public Terminal visitTerminal(AqlParser.TerminalContext ctx) {

        if (ctx.identifiedPath() != null) {
            return visitIdentifiedPath(ctx.identifiedPath());
        }
        if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        }
        if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        }
        if (ctx.primitive() != null) {
            return visitPrimitive(ctx.primitive());
        }
        throw new UnsupportedOperationException("Can not parse %s".formatted(ctx.getText()));
    }

    private ParameterDto createParameter(TerminalNode node) {

        ParameterDto parameterDto = new ParameterDto();
        parameterDto.setName(StringUtils.removeStart(node.getText(), "$"));
        return parameterDto;
    }

    @Override
    public Containment visitFromClause(AqlParser.FromClauseContext ctx) {

        return visitFromExpr(ctx.fromExpr());
    }

    @Override
    public Containment visitFromExpr(AqlParser.FromExprContext ctx) {

        return visitContainsExpr(ctx.containsExpr());
    }

    @Override
    public Containment visitContainsExpr(AqlParser.ContainsExprContext ctx) {

        ContainmentSetOperatorSymbol symbol = extractSymbol(ctx);

        if (symbol != null) {

            AqlParser.ContainsExprContext current = ctx;

            List<Object> boolList = new ArrayList<>();

            while (current != null) {

                if (current.containsExpr().size() == 2) {
                    boolList.add(visitContainsExpr(current.containsExpr(0)));
                    Optional.ofNullable(extractSymbol(current)).ifPresent(boolList::add);
                    current = current.containsExpr(1);
                } else {
                    boolList.add(visitContainsExpr(current));
                    current = null;
                }
            }
            return buildContainmentLogicalOperator(boolList);

        } else if (ctx.SYM_LEFT_PAREN() != null) {

            return visitContainsExpr(ctx.containsExpr(0));
        } else {

            AqlParser.ClassExprOperandContext classExprOperandContext = ctx.classExprOperand();

            final AbstractContainmentExpression containmentDto;

            if (classExprOperandContext instanceof AqlParser.ClassExpressionContext) {

                containmentDto = visitClassExpression((AqlParser.ClassExpressionContext) classExprOperandContext);
            } else {

                containmentDto = visitVersionClassExpr((AqlParser.VersionClassExprContext) classExprOperandContext);
            }
            if (ctx.CONTAINS() != null) {

                Containment contains = visitContainsExpr(ctx.containsExpr(0));
                if (ctx.NOT() != null) {

                    ContainmentNotOperator not = new ContainmentNotOperator();
                    not.setContainmentExpression(contains);
                    containmentDto.setContains(not);
                } else {
                    containmentDto.setContains(contains);
                }
            }
            return containmentDto;
        }
    }

    @Override
    public ContainmentClassExpression visitClassExpression(AqlParser.ClassExpressionContext ctx) {

        ContainmentClassExpression containmentDto = new ContainmentClassExpression();
        containmentDto.setType(ctx.IDENTIFIER(0).getText());

        if (ctx.IDENTIFIER().size() == 2) {
            containmentDto.setIdentifier(ctx.IDENTIFIER(1).getText());
            identifierMap.put(containmentDto.getIdentifier(), containmentDto);
        }

        if (ctx.pathPredicate() != null) {
            PredicateDto predicateDto = PredicateHelper.buildPredicate(
                    StringUtils.removeEnd(StringUtils.removeStart(getFullText(ctx.pathPredicate()), "["), "]"));

            containmentDto.setOtherPredicates(predicateDto);
        }

        return containmentDto;
    }

    @Override
    public ContainmentVersionExpression visitVersionClassExpr(AqlParser.VersionClassExprContext ctx) {

        errors.add("version not yet implemented");
        return new ContainmentVersionExpression() {};
    }

    public ContainmentSetOperator buildContainmentLogicalOperator(List<Object> boolList) {

        return (ContainmentSetOperator) buildLogicalOperator(boolList, (Function<
                        ContainmentSetOperatorSymbol, LogicalOperatorDto<ContainmentSetOperatorSymbol, Containment>>)
                s -> {
                    ContainmentSetOperator conditionLogicalOperatorDto = new ContainmentSetOperator();
                    conditionLogicalOperatorDto.setSymbol(s);
                    conditionLogicalOperatorDto.setValues(new ArrayList<>());

                    return conditionLogicalOperatorDto;
                });
    }

    public ConditionLogicalOperatorDto buildConditionLogicalOperatorDto(List<Object> boolList) {

        return (ConditionLogicalOperatorDto) buildLogicalOperator(boolList, (Function<
                        ConditionLogicalOperatorSymbol,
                        LogicalOperatorDto<ConditionLogicalOperatorSymbol, ConditionDto>>)
                s -> {
                    ConditionLogicalOperatorDto conditionLogicalOperatorDto = new ConditionLogicalOperatorDto();
                    conditionLogicalOperatorDto.setSymbol(s);
                    conditionLogicalOperatorDto.setValues(new ArrayList<>());

                    return conditionLogicalOperatorDto;
                });
    }

    @Override
    public ConditionDto visitWhereClause(AqlParser.WhereClauseContext ctx) {

        return visitWhereExpr(ctx.whereExpr());
    }

    @Override
    public ConditionDto visitWhereExpr(AqlParser.WhereExprContext ctx) {

        if (ctx.identifiedExpr() != null) {
            return visitIdentifiedExpr(ctx.identifiedExpr());
        } else if (ctx.NOT() != null) {

            NotConditionOperatorDto notConditionOperatorDto = new NotConditionOperatorDto();
            notConditionOperatorDto.setConditionDto(visitWhereExpr(ctx.whereExpr(0)));
            return notConditionOperatorDto;
        } else if (ctx.SYM_RIGHT_PAREN() != null) {
            return visitWhereExpr(ctx.whereExpr(0));
        }
        // AND /  OR
        else {
            List<Object> boollist = new ArrayList<>();
            var current = ctx;
            ConditionLogicalOperatorSymbol symbol = extractSymbol(current);

            while (current != null) {
                if (symbol != null) {
                    boollist.add(0, visitWhereExpr(current.whereExpr(1)));
                    boollist.add(0, symbol);
                    current = current.whereExpr(0);
                    symbol = extractSymbol(current);
                } else {
                    boollist.add(0, visitWhereExpr(current));
                    current = null;
                }
            }

            return buildConditionLogicalOperatorDto(boollist);
        }
    }

    @Override
    public ConditionDto visitIdentifiedExpr(AqlParser.IdentifiedExprContext ctx) {

        if (ctx.COMPARISON_OPERATOR() != null) {

            ConditionComparisonOperatorDto conditionComparisonOperatorDto = new ConditionComparisonOperatorDto();
            conditionComparisonOperatorDto.setSymbol(extractSymbol(ctx.COMPARISON_OPERATOR()));
            conditionComparisonOperatorDto.setValue(visitTerminal(ctx.terminal()));
            ComparisonLeftOperator statement;

            if (ctx.functionCall() != null) {
                statement = visitFunctionCall(ctx.functionCall());
            } else {
                statement = visitIdentifiedPath(ctx.identifiedPath());
            }
            conditionComparisonOperatorDto.setStatement(statement);
            conditionComparisonOperatorDto.setValue(visitTerminal(ctx.terminal()));
            return conditionComparisonOperatorDto;
        } else if (ctx.likeOperand() != null) {

            LikeOperatorDto likeOperatorDto = new LikeOperatorDto();
            likeOperatorDto.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
            likeOperatorDto.setValue(visitLikeOperand(ctx.likeOperand()));
            return likeOperatorDto;
        } else if (ctx.SYM_LEFT_PAREN() != null) {

            return visitIdentifiedExpr(ctx.identifiedExpr());
        } else if (ctx.EXISTS() != null) {

            ExistsConditionOperatorDto existsConditionOperatorDto = new ExistsConditionOperatorDto();
            existsConditionOperatorDto.setValue(visitIdentifiedPath(ctx.identifiedPath()));
            return existsConditionOperatorDto;
        } else if (ctx.MATCHES() != null) {

            MatchesOperatorDto matchesOperatorDto = new MatchesOperatorDto();
            matchesOperatorDto.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
            matchesOperatorDto.setValues(visitMatchesOperand(ctx.matchesOperand()));
            return matchesOperatorDto;
        } else {

            errors.add("Can not handle %s".formatted(ctx.getText()));
            return null;
        }
    }

    @Override
    public List<MatchesOperant> visitMatchesOperand(AqlParser.MatchesOperandContext ctx) {

        if (CollectionUtils.isNotEmpty(ctx.valueListItem())) {
            return ctx.valueListItem().stream().map(this::visitValueListItem).toList();
        }

        return Collections.emptyList();
    }

    @Override
    public MatchesOperant visitValueListItem(AqlParser.ValueListItemContext ctx) {

        if (ctx.primitive() != null) {
            return visitPrimitive(ctx.primitive());
        } else if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        } else {
            errors.add("Terminology not yet implemented");
            return new TerminologyFunctionDto();
        }
    }

    @Override
    public LikeOperant visitLikeOperand(AqlParser.LikeOperandContext ctx) {

        if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        } else {
            return new StringPrimitiveDto(unwrapText(ctx));
        }
    }

    @Override
    public List<OrderByExpression> visitOrderByClause(AqlParser.OrderByClauseContext ctx) {

        return ctx.orderByExpr().stream().map(this::visitOrderByExpr).toList();
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

    @Override
    public OrderByExpression visitOrderByExpr(AqlParser.OrderByExprContext ctx) {
        OrderByExpression orderByExpression = new OrderByExpression();
        orderByExpression.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
        orderByExpression.setSymbol(extractSymbol(ctx));
        return orderByExpression;
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

    private OrderByDirection extractSymbol(AqlParser.OrderByExprContext ctx) {
        if (ctx.DESC() != null || ctx.DESCENDING() != null) {
            return OrderByDirection.DESC;
        } else {
            return OrderByDirection.ASC;
        }
    }

    private ConditionComparisonOperatorSymbol extractSymbol(TerminalNode comparisonOperator) {
        switch (comparisonOperator.getText()) {
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
                throw new AqlParseException("Unknown Token " + comparisonOperator.getText());
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

    private ContainmentSetOperatorSymbol extractSymbol(AqlParser.ContainsExprContext ctx) {
        if (ctx == null) {
            return null;
        }
        if (ctx.OR() != null) {
            return ContainmentSetOperatorSymbol.OR;
        } else if (ctx.AND() != null) {
            return ContainmentSetOperatorSymbol.AND;
        } else {
            return null;
        }
    }

    private ConditionLogicalOperatorSymbol extractSymbol(AqlParser.WhereExprContext ctx) {
        if (ctx == null) {
            return null;
        }
        if (ctx.OR() != null) {
            return ConditionLogicalOperatorSymbol.OR;
        } else if (ctx.AND() != null) {
            return ConditionLogicalOperatorSymbol.AND;
        } else {
            return null;
        }
    }
}
