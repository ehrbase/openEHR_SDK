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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.EhrDto;
import org.ehrbase.aql.dto.LogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorSymbol;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.LogicalOperatorDto;
import org.ehrbase.aql.dto.condition.MatchesOperatorDto;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.aql.dto.condition.Value;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperatorSymbol;
import org.ehrbase.aql.dto.orderby.OrderByExpressionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpressionSymbol;
import org.ehrbase.aql.dto.path.predicate.PredicateComparisonOperatorDto;
import org.ehrbase.aql.dto.path.predicate.PredicateDto;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalAndOperation;
import org.ehrbase.aql.dto.path.predicate.PredicateLogicalOrOperation;
import org.ehrbase.aql.dto.select.AQLFunction;
import org.ehrbase.aql.dto.select.FunctionDto;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectFieldDto;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.aql.parser.AqlParser.OperandContext;
import org.ehrbase.client.aql.top.Direction;

public class AqlToDtoVisitor extends AqlBaseVisitor<Object> {

    private int containmentId = 0;
    private final Map<String, Integer> identifierMap = new HashMap<>();
    private final MultiValuedMap<String, SelectFieldDto> selectFieldDtoMultiMap = new ArrayListValuedHashMap<>();

    @Override
    public AqlDto visitQuery(AqlParser.QueryContext ctx) {
        AqlDto aqlDto = new AqlDto();

        Pair<EhrDto, ConditionDto> visitFromEHR =
                visitFromEHR(ctx.queryExpr().from().fromEHR());
        aqlDto.setEhr(visitFromEHR.getLeft());

        if (ctx.queryExpr().from().containsExpression() != null) {
            aqlDto.setContains(visitContainsExpression(ctx.queryExpr().from().containsExpression()));
        }
        aqlDto.setSelect(visitSelect(ctx.queryExpr().select()));
        if (ctx.queryExpr().where() != null) {
            aqlDto.setWhere(visitIdentifiedExpr(ctx.queryExpr().where().identifiedExpr()));
        }
        if (visitFromEHR.getRight() != null) {
            if (aqlDto.getWhere() == null) {
                aqlDto.setWhere(visitFromEHR.getRight());
            } else {
                ConditionLogicalOperatorDto and = new ConditionLogicalOperatorDto();
                and.setSymbol(ConditionLogicalOperatorSymbol.AND);
                and.setValues(new ArrayList<>());
                and.getValues().add(aqlDto.getWhere());
                and.getValues().add(visitFromEHR.getRight());
                aqlDto.setWhere(and);
            }
        }

        if (ctx.queryExpr().orderBy() != null) {
            aqlDto.setOrderBy(visitOrderBySeq(ctx.queryExpr().orderBy().orderBySeq()));
        }

        if (ctx.queryExpr().limit() != null) {
            AqlParser.LimitContext limitExpr = ctx.queryExpr().limit();
            aqlDto.setLimit(Integer.parseInt(limitExpr.INTEGER().getText()));

            if (ctx.queryExpr().offset() != null) {
                aqlDto.setOffset(
                        Integer.parseInt(ctx.queryExpr().offset().INTEGER().getText()));
            }
        }

        selectFieldDtoMultiMap.entries().forEach(e -> e.getValue()
                .setContainmentId(
                        Optional.ofNullable(identifierMap.get(e.getKey())).orElseThrow()));
        return aqlDto;
    }

    @Override
    public Pair<EhrDto, ConditionDto> visitFromEHR(AqlParser.FromEHRContext ctx) {
        EhrDto ehrDto = new EhrDto();
        ehrDto.setContainmentId(buildContainmentId());
        if (ctx.IDENTIFIER() != null) {
            identifierMap.put(ctx.IDENTIFIER().getText(), ehrDto.getContainmentId());
            ehrDto.setIdentifier(ctx.IDENTIFIER().getText());
        }
        return Pair.of(
                ehrDto,
                Optional.ofNullable(ctx.standardPredicate())
                        .map(AqlParser.StandardPredicateContext::predicateExpr)
                        .map(p -> buildConditionDtoFromPredicate(p, ehrDto.getContainmentId()))
                        .orElse(null));
    }

    private ConditionDto buildConditionDtoFromPredicate(AqlParser.PredicateExprContext p, int containmentId) {
        PredicateDto predicateDto = PredicateHelper.buildPredicate(getFullText(p));
        return to(predicateDto, containmentId);
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
            statement.setAqlPath(
                    StringUtils.prependIfMissing(((PredicateComparisonOperatorDto) predicateDto).getStatement(), "/"));
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

    @Override
    public SelectDto visitSelect(AqlParser.SelectContext ctx) {
        SelectDto selectDto = new SelectDto();
        selectDto.setStatement(visitSelectExpr(ctx.selectExpr()));
        if (ctx.topExpr() != null) {
            selectDto.setTopDirection(extractSymbol(ctx.topExpr()));
            selectDto.setTopCount(Integer.parseInt(ctx.topExpr().INTEGER().getText()));
        }
        return selectDto;
    }

    private Direction extractSymbol(AqlParser.TopExprContext topExpr) {
        if (topExpr.BACKWARD() != null) {
            return Direction.BACKWARD;
        } else {
            return Direction.FORWARD;
        }
    }

    @Override
    public List<SelectStatementDto> visitSelectExpr(AqlParser.SelectExprContext ctx) {
        List<SelectStatementDto> selectStatementDtos = new ArrayList<>();

        if (ctx.identifiedPath() != null) {
            SelectFieldDto selectFieldDto = visitIdentifiedPath(ctx.identifiedPath());
            if (ctx.IDENTIFIER() != null) {
                selectFieldDto.setName(ctx.IDENTIFIER().getText());
            }
            selectStatementDtos.add(selectFieldDto);
        } else if (ctx.stdExpression() != null) {
            if (ctx.stdExpression().function() != null) {
                FunctionDto functionDto = visitFunction(ctx.stdExpression().function());
                selectStatementDtos.add(functionDto);
                if (ctx.IDENTIFIER() != null) {
                    functionDto.setName(ctx.IDENTIFIER().getText());
                }
            }
        }

        if (ctx.selectExpr() != null) {
            selectStatementDtos.addAll(visitSelectExpr(ctx.selectExpr()));
        }
        return selectStatementDtos;
    }

    @Override
    public FunctionDto visitFunction(AqlParser.FunctionContext ctx) {

        FunctionDto functionDto = new FunctionDto();

        AQLFunction aqlFunction =
                AQLFunction.valueOf(ctx.FUNCTION_IDENTIFIER().toString().toUpperCase(Locale.ROOT));

        functionDto.setAqlFunction(aqlFunction);

        if (ctx.identifiedPath() != null) {
            functionDto.setParameters(
                    ctx.identifiedPath().stream().map(this::visitIdentifiedPath).collect(Collectors.toList()));
        }

        return functionDto;
    }

    @Override
    public SelectFieldDto visitIdentifiedPath(AqlParser.IdentifiedPathContext ctx) {
        SelectFieldDto selectStatementDto = new SelectFieldDto();
        selectFieldDtoMultiMap.put(ctx.IDENTIFIER().getText(), selectStatementDto);
        selectStatementDto.setAqlPath(
                StringUtils.removeStart(ctx.getText(), ctx.IDENTIFIER().getText()));

        return selectStatementDto;
    }

    @Override
    public ContainmentExpresionDto visitContainsExpression(AqlParser.ContainsExpressionContext ctx) {

        List<Object> boolList = new ArrayList<>();

        AqlParser.ContainsExpressionContext currentContext = ctx;
        while (currentContext != null) {
            ContainmentLogicalOperatorSymbol symbol = extractSymbol(currentContext);
            boolList.add(visitContainExpressionBool(currentContext.containExpressionBool()));

            if (symbol != null) {
                boolList.add(symbol);
            }
            currentContext = currentContext.containsExpression();
        }

        if (boolList.size() == 1) {
            return (ContainmentExpresionDto) boolList.get(0);
        } else {
            return buildContainmentLogicalOperator(boolList);
        }
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
    public ContainmentExpresionDto visitContainExpressionBool(AqlParser.ContainExpressionBoolContext ctx) {

        if (ctx.contains() != null) {
            return visitContains(ctx.contains());
        } else {
            return visitContainsExpression(ctx.containsExpression());
        }
    }

    @Override
    public ContainmentDto visitContains(AqlParser.ContainsContext ctx) {

        ContainmentDto containmentDto = visitSimpleClassExpr(ctx.simpleClassExpr());
        if (ctx.containsExpression() != null) {
            containmentDto.setContains(visitContainsExpression(ctx.containsExpression()));
        }
        return containmentDto;
    }

    @Override
    public ContainmentDto visitSimpleClassExpr(AqlParser.SimpleClassExprContext ctx) {
        ContainmentDto currentContainment = new ContainmentDto();
        currentContainment.setId(buildContainmentId());
        AqlParser.ArchetypedClassExprContext archetypedClassExprContext =
                ctx.getChild(AqlParser.ArchetypedClassExprContext.class, 0);
        if (archetypedClassExprContext != null) {
            if (archetypedClassExprContext.IDENTIFIER().size() == 2) {
                currentContainment.setIdentifier(
                        archetypedClassExprContext.IDENTIFIER(1).getText());
                identifierMap.put(currentContainment.getIdentifier(), currentContainment.getId());
            }
            currentContainment.setArchetypeId(
                    archetypedClassExprContext.ARCHETYPEID().getText());

        } else {
            currentContainment.setArchetypeId(ctx.IDENTIFIER(0).getText());
            if (ctx.IDENTIFIER().size() == 2) {
                currentContainment.setIdentifier(ctx.IDENTIFIER(1).getText());
                identifierMap.put(currentContainment.getIdentifier(), currentContainment.getId());
            }
        }

        return currentContainment;
    }

    @Override
    public ConditionDto visitIdentifiedExpr(AqlParser.IdentifiedExprContext ctx) {

        List<Object> boolList = new ArrayList<>();

        for (int i = 0; i < ctx.getChildCount(); i++) {
            ParseTree child = ctx.getChild(i);
            if (child instanceof AqlParser.IdentifiedEqualityContext) {
                boolList.add(visitIdentifiedEquality((AqlParser.IdentifiedEqualityContext) child));
            } else if (child instanceof TerminalNode) {
                ConditionLogicalOperatorSymbol symbol = extractSymbolTerminal((TerminalNode) child);
                if (symbol != null) {
                    boolList.add(symbol);
                }
            }
        }
        if (boolList.size() == 1) {
            return (ConditionDto) boolList.get(0);
        } else {
            return buildConditionLogicalOperator(boolList);
        }
    }

    private ConditionLogicalOperatorDto buildConditionLogicalOperator(List<Object> boolList) {

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

    public static <S extends LogicalOperatorSymbol, T> LogicalOperatorDto<S, T> buildLogicalOperator(
            List<Object> boolList, Function<S, LogicalOperatorDto<S, T>> creator) {

        S currentSymbol = (S) boolList.get(1);
        LogicalOperatorDto<S, T> currentOperator = creator.apply(currentSymbol);
        currentOperator.getValues().add((T) boolList.get(0));
        LogicalOperatorDto<S, T> lowestOperator = currentOperator;
        for (int i = 2; i < boolList.size(); i = i + 2) {
            S nextSymbol = i + 1 < boolList.size() ? (S) boolList.get(i + 1) : null;
            if (nextSymbol == null || Objects.equals(currentSymbol, nextSymbol)) {
                currentOperator.getValues().add((T) boolList.get(i));
                currentSymbol = nextSymbol;
            } else {
                LogicalOperatorDto<S, T> nextOperator = creator.apply(nextSymbol);

                if (hasHigherPrecedence(currentSymbol, nextSymbol)) {
                    currentOperator.getValues().add((T) boolList.get(i));
                    nextOperator.getValues().add((T) currentOperator);
                    lowestOperator = nextOperator;
                } else {
                    nextOperator.getValues().add((T) boolList.get(i));
                    currentOperator.getValues().add((T) nextOperator);
                    lowestOperator = currentOperator;
                }

                currentOperator = nextOperator;
                currentSymbol = nextSymbol;
            }
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

    @Override
    public ConditionDto visitIdentifiedEquality(AqlParser.IdentifiedEqualityContext ctx) {
        ConditionDto conditionDto = null;

        if (ctx.identifiedExpr() != null) {
            conditionDto = visitIdentifiedExpr(ctx.identifiedExpr());
        } else if (ctx.IS() != null) {
            ConditionComparisonOperatorDto operatorDto = new ConditionComparisonOperatorDto();
            operatorDto.setSymbol(
                    ctx.NOT() == null ? ConditionComparisonOperatorSymbol.EQ : ConditionComparisonOperatorSymbol.NEQ);
            operatorDto.setStatement(
                    visitIdentifiedPath(ctx.identifiedOperand(0).identifiedPath()));
            if (ctx.TRUE() != null) {
                operatorDto.setValue(new SimpleValue(true));
            } else if (ctx.FALSE() != null) {
                operatorDto.setValue(new SimpleValue(false));
            } else {
                throw new AqlParseException("Not supported, only IS (NOT) TRUE/FALSE is supported");
            }
            conditionDto = operatorDto;
        } else if (ctx.COMPARABLEOPERATOR() != null) {
            ConditionComparisonOperatorSymbol comparisonOperatorSymbol = extractSymbol(ctx);
            ConditionComparisonOperatorDto operatorDto = new ConditionComparisonOperatorDto();
            operatorDto.setSymbol(comparisonOperatorSymbol);
            operatorDto.setStatement(
                    visitIdentifiedPath(ctx.identifiedOperand(0).identifiedPath()));
            operatorDto.setValue(visitOperand(ctx.identifiedOperand(1).operand()));

            conditionDto = operatorDto;
        } else if (ctx.MATCHES() != null) {
            MatchesOperatorDto matchesOperatorDto = new MatchesOperatorDto();
            matchesOperatorDto.setStatement(
                    visitIdentifiedPath(ctx.identifiedOperand(0).identifiedPath()));
            matchesOperatorDto.setValues(new ArrayList<>());
            AqlParser.ValueListItemsContext valueListItemsContext =
                    ctx.matchesOperand().valueListItems();
            while (valueListItemsContext != null) {
                matchesOperatorDto.getValues().add(visitOperand(valueListItemsContext.operand()));
                valueListItemsContext = valueListItemsContext.valueListItems();
            }

            conditionDto = matchesOperatorDto;
        }
        return conditionDto;
    }

    @Override
    public Value visitOperand(AqlParser.OperandContext ctx) {
        final Value value;

        if (isBooleanOperand(ctx)) {
            SimpleValue simpleValue = new SimpleValue();
            simpleValue.setValue(Boolean.parseBoolean(ctx.getText()));
            value = simpleValue;
        } else if (ctx.DATE() != null) {
            SimpleValue simpleValue = new SimpleValue();
            String unwrap = StringUtils.unwrap(StringUtils.unwrap(ctx.getText(), "'"), "\"");
            simpleValue.setValue(DateTimeParsers.parseTimeValue(unwrap));
            value = simpleValue;
        } else if (ctx.FLOAT() != null) {
            SimpleValue simpleValue = new SimpleValue();
            simpleValue.setValue(Double.valueOf(ctx.getText()));
            value = simpleValue;
        } else if (ctx.INTEGER() != null) {
            SimpleValue simpleValue = new SimpleValue();
            simpleValue.setValue(Integer.valueOf(ctx.getText()));
            value = simpleValue;
        } else if (ctx.STRING() != null) {
            SimpleValue simpleValue = new SimpleValue();
            String unwrap = StringUtils.unwrap(StringUtils.unwrap(ctx.getText(), "'"), "\"");
            simpleValue.setValue(unwrap);
            value = simpleValue;
        } else if (ctx.PARAMETER() != null) {
            ParameterValue simpleValue = new ParameterValue();
            simpleValue.setName(StringUtils.removeStart(ctx.getText(), "$"));
            simpleValue.setType("?");
            value = simpleValue;
        } else {
            throw new AqlParseException("Can not handle value " + ctx.getText());
        }
        return value;
    }

    @Override
    public List<OrderByExpressionDto> visitOrderBySeq(AqlParser.OrderBySeqContext ctx) {
        List<OrderByExpressionDto> orderByExpressionDtoList = new ArrayList<>();

        orderByExpressionDtoList.add(visitOrderByExpr(ctx.orderByExpr()));
        if (ctx.orderBySeq() != null) {
            orderByExpressionDtoList.addAll(visitOrderBySeq(ctx.orderBySeq()));
        }
        return orderByExpressionDtoList;
    }

    @Override
    public OrderByExpressionDto visitOrderByExpr(AqlParser.OrderByExprContext ctx) {
        OrderByExpressionDto orderByExpressionDto = new OrderByExpressionDto();
        orderByExpressionDto.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
        orderByExpressionDto.setSymbol(extractSymbol(ctx));
        return orderByExpressionDto;
    }

    private OrderByExpressionSymbol extractSymbol(AqlParser.OrderByExprContext ctx) {
        if (ctx.DESC() != null || ctx.DESCENDING() != null) {
            return OrderByExpressionSymbol.DESC;
        } else {
            return OrderByExpressionSymbol.ASC;
        }
    }

    private ConditionComparisonOperatorSymbol extractSymbol(AqlParser.IdentifiedEqualityContext ctx) {
        switch (ctx.COMPARABLEOPERATOR().getText()) {
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
                        "Unknown Token " + ctx.COMPARABLEOPERATOR().getText());
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

    private ContainmentLogicalOperatorSymbol extractSymbol(AqlParser.ContainsExpressionContext ctx) {
        if (ctx == null) {
            return null;
        }
        if (ctx.OR() != null) {
            return ContainmentLogicalOperatorSymbol.OR;
        } else if (ctx.AND() != null) {
            return ContainmentLogicalOperatorSymbol.AND;
        } else if (ctx.XOR() != null) {
            throw new AqlParseException("XOR not supported");
        } else {
            return null;
        }
    }

    private boolean isBooleanOperand(OperandContext ctx) {
        return ctx.BOOLEAN() != null || ctx.TRUE() != null || ctx.FALSE() != null;
    }
}
