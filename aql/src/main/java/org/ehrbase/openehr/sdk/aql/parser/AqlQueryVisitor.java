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
package org.ehrbase.openehr.sdk.aql.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.condition.ExistsCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LikeCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition.ConditionLogicalOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.condition.MatchesCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.NotCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperatorSymbol;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentVersionExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.AggregateFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.AggregateFunction.AggregateFunctionName;
import org.ehrbase.openehr.sdk.aql.dto.operand.BooleanPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.ColumnExpression;
import org.ehrbase.openehr.sdk.aql.dto.operand.ComparisonLeftOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.CountDistinctAggregateFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.DoublePrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.LikeOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.LongPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.MatchesOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.NullPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.Operand;
import org.ehrbase.openehr.sdk.aql.dto.operand.PathPredicateOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Primitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.aql.dto.operand.SingleRowFunction;
import org.ehrbase.openehr.sdk.aql.dto.operand.StringPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.TemporalPrimitive;
import org.ehrbase.openehr.sdk.aql.dto.operand.TerminologyFunction;
import org.ehrbase.openehr.sdk.aql.dto.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.aql.dto.orderby.OrderByExpression.OrderByDirection;
import org.ehrbase.openehr.sdk.aql.dto.path.AdlRegex;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPath.PathNode;
import org.ehrbase.openehr.sdk.aql.dto.path.AqlObjectPathUtil;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate.PredicateComparisonOperator;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectExpression;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.AggregateFunctionCallContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ClassExprOperandContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ClassExpressionContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ColumnExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ContainsExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.FromClauseContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.FromExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.FunctionCallContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.IdentifiedExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.IdentifiedPathContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.LikeOperandContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.LimitClauseContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.MatchesOperandContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.NameConstraintContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.NodeConstraintContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.NumericPrimitiveContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ObjectPathContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.OrderByClauseContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.OrderByExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.PathPartContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.PathPredicateAndContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.PathPredicateContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.PathPredicateOperandContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.PrimitiveContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.SelectClauseContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.SelectExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.SelectQueryContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.StandardPredicateContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.TerminalContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.TerminalPredicateContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.ValueListItemContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.VersionClassExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParser.WhereExprContext;
import org.ehrbase.openehr.sdk.aql.parser.antlr.AqlParserBaseVisitor;
import org.ehrbase.openehr.sdk.util.exception.SdkException;

class AqlQueryVisitor extends AqlParserBaseVisitor<Object> {

    private final Map<String, AbstractContainmentExpression> containmentByAlias = new HashMap<>();
    private final MultiValuedMap<String, IdentifiedPath> identifiedPathByContainmentAlias =
            new ArrayListValuedHashMap<>();
    private final List<String> errors = new ArrayList<>();

    @Override
    public AqlQuery visitSelectQuery(SelectQueryContext ctx) {

        AqlQuery aqlQuery = new AqlQuery();

        // select
        aqlQuery.setSelect(visitSelectClause(ctx.selectClause()));
        // from
        aqlQuery.setFrom(visitFromClause(ctx.fromClause()));
        // where
        if (ctx.whereClause() != null) {
            aqlQuery.setWhere((WhereCondition) visitWhereClause(ctx.whereClause()));
        }
        // oder by
        if (ctx.orderByClause() != null) {
            aqlQuery.setOrderBy(visitOrderByClause(ctx.orderByClause()));
        }

        if (ctx.limitClause() != null) {

            LimitClauseContext limitClauseContext = ctx.limitClause();

            aqlQuery.setLimit(Long.parseLong(limitClauseContext.limit.getText()));

            if (limitClauseContext.offset != null) {
                aqlQuery.setOffset(Long.parseLong(limitClauseContext.offset.getText()));
            }
        }

        identifiedPathByContainmentAlias.entries().forEach(e -> {
            if (containmentByAlias.containsKey(e.getKey())) {
                e.getValue().setRoot(containmentByAlias.get(e.getKey()));
            } else {
                errors.add("unknown FROM alias '%s'".formatted(e.getKey()));
            }
        });

        return aqlQuery;
    }

    @Override
    public SelectClause visitSelectClause(SelectClauseContext ctx) {

        SelectClause selectClause = new SelectClause();

        selectClause.setDistinct(ctx.DISTINCT() != null);

        if (ctx.top() != null) {
            errors.add("Deprecated keyword 'TOP' not implemented. Use 'LIMIT'.");
        }

        selectClause.setStatement(
                ctx.selectExpr().stream().map(this::visitSelectExpr).collect(Collectors.toList()));

        return selectClause;
    }

    @Override
    public SelectExpression visitSelectExpr(SelectExprContext ctx) {

        SelectExpression selectExpression = new SelectExpression();

        selectExpression.setColumnExpression(visitColumnExpr(ctx.columnExpr()));

        if (ctx.aliasName != null) {
            selectExpression.setAlias(ctx.aliasName.getText());
        }

        return selectExpression;
    }

    @Override
    public ColumnExpression visitColumnExpr(ColumnExprContext ctx) {
        return (ColumnExpression) super.visitColumnExpr(ctx);
    }

    @Override
    public AggregateFunction visitAggregateFunctionCall(AggregateFunctionCallContext ctx) {

        final AggregateFunctionName aqlFunction = findFunctionName(ctx.name, AggregateFunctionName::valueOf);

        final AggregateFunction dto;
        if (AggregateFunctionName.COUNT.equals(aqlFunction) && ctx.DISTINCT() != null) {
            dto = new CountDistinctAggregateFunction();
        } else {
            dto = new AggregateFunction();
            dto.setFunctionName(aqlFunction);
        }

        IdentifiedPathContext identifiedPath = ctx.identifiedPath();
        if (identifiedPath != null) {
            dto.setIdentifiedPath(visitIdentifiedPath(identifiedPath));
        }

        return dto;
    }

    private static <F> F findFunctionName(Token name, Function<String, F> toNameNumFunc) {
        try {
            return toNameNumFunc.apply(name.getText().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new SdkException(String.format("Unknown function %s ", name.getText()));
        }
    }

    @Override
    public Primitive visitPrimitive(PrimitiveContext ctx) {

        final Primitive selectPrimitiveDto;

        if (ctx.BOOLEAN() != null) {
            selectPrimitiveDto = new BooleanPrimitive(Boolean.parseBoolean(ctx.getText()));
        } else if (ctx.DATE() != null || ctx.DATETIME() != null || ctx.TIME() != null) {
            selectPrimitiveDto = new TemporalPrimitive(unescapeText(ctx));
        } else if (ctx.numericPrimitive() != null) {
            NumericPrimitiveContext numericPrimitiveContext = ctx.numericPrimitive();
            selectPrimitiveDto = visitNumericPrimitive(numericPrimitiveContext);
        } else if (ctx.STRING() != null) {
            selectPrimitiveDto = new StringPrimitive(unescapeText(ctx));
        } else if (ctx.NULL() != null) {
            selectPrimitiveDto = NullPrimitive.INSTANCE;
        } else {
            throw new AqlParseException("Cannot handle value " + ctx.getText());
        }

        return selectPrimitiveDto;
    }

    @Override
    public Primitive visitNumericPrimitive(NumericPrimitiveContext ctx) {

        if (ctx.REAL() != null || ctx.SCI_REAL() != null || ctx.SCI_INTEGER() != null) {
            try {
                return new DoublePrimitive(ctx.getText());
            } catch (NumberFormatException e) {
                throw new AqlParseException("Precision of %s not supported".formatted(ctx.getText()), e);
            }
        } else if (ctx.INTEGER() != null) {
            try {
                return new LongPrimitive(Long.valueOf(ctx.getText()));
            } catch (NumberFormatException e) {
                throw new AqlParseException("Precision of %s not supported".formatted(ctx.getText()), e);
            }
        } else {
            throw new AqlParseException("Cannot handle value " + ctx.getText());
        }
    }

    @Override
    public IdentifiedPath visitIdentifiedPath(IdentifiedPathContext ctx) {

        IdentifiedPath selectFieldDto = new IdentifiedPath();

        String containsAlias = ctx.IDENTIFIER().getText();
        identifiedPathByContainmentAlias.put(containsAlias, selectFieldDto);

        Optional.of(ctx)
                .map(IdentifiedPathContext::pathPredicate)
                .map(this::visitPathPredicate)
                .ifPresent(selectFieldDto::setRootPredicate);

        selectFieldDto.setPath(Optional.of(ctx)
                .map(IdentifiedPathContext::objectPath)
                .map(this::visitObjectPath)
                .orElse(null));

        return selectFieldDto;
    }

    @Override
    public SingleRowFunction visitFunctionCall(FunctionCallContext ctx) {

        SingleRowFunction dto = new SingleRowFunction();

        if (ctx.IDENTIFIER() == null) {
            dto.setFunctionName(findFunctionName(ctx.name, SingleRowFunction.KnownFunctionName::valueOf));
        } else {
            dto.setFunctionName(
                    new SingleRowFunction.CustomFunctionName(ctx.IDENTIFIER().getText()));
        }

        dto.setOperandList(ctx.terminal().stream().map(this::visitTerminal).collect(Collectors.toList()));

        return dto;
    }

    @Override
    public Operand visitTerminal(TerminalContext ctx) {

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
        throw new UnsupportedOperationException("Cannot parse %s".formatted(ctx.getText()));
    }

    private QueryParameter createParameter(TerminalNode node) {

        QueryParameter queryParameter = new QueryParameter();
        queryParameter.setName(StringUtils.removeStart(node.getText(), "$"));
        return queryParameter;
    }

    @Override
    public Containment visitFromClause(FromClauseContext ctx) {

        return visitFromExpr(ctx.fromExpr());
    }

    @Override
    public Containment visitFromExpr(FromExprContext ctx) {

        return visitContainsExpr(ctx.containsExpr());
    }

    @Override
    public Containment visitContainsExpr(ContainsExprContext ctx) {

        ContainmentSetOperatorSymbol setOperatorSymbol = extractSymbol(ctx);
        if (setOperatorSymbol != null) {
            ContainmentSetOperator result = new ContainmentSetOperator();
            result.setSymbol(setOperatorSymbol);
            result.setValues(getOperands(ctx, setOperatorSymbol));
            return result;
        } else if (ctx.SYM_LEFT_PAREN() != null) {

            return visitContainsExpr(ctx.containsExpr(0));
        } else {

            ClassExprOperandContext classExprOperandContext = ctx.classExprOperand();

            final AbstractContainmentExpression containmentDto;

            if (classExprOperandContext instanceof ClassExpressionContext) {

                containmentDto = visitClassExpression((ClassExpressionContext) classExprOperandContext);
            } else {

                containmentDto = visitVersionClassExpr((VersionClassExprContext) classExprOperandContext);
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

    private List<Containment> getOperands(ContainsExprContext ctx, ContainmentSetOperatorSymbol symbol) {
        return IntStream.of(0, 1)
                .mapToObj(ctx::containsExpr)
                .map(this::visitContainsExpr)
                .flatMap(e -> (e instanceof ContainmentSetOperator s && s.getSymbol() == symbol)
                        ? s.getValues().stream()
                        : Stream.of(e))
                .collect(Collectors.toList());
    }

    @Override
    public ContainmentClassExpression visitClassExpression(ClassExpressionContext ctx) {

        ContainmentClassExpression containmentDto = new ContainmentClassExpression();
        containmentDto.setType(ctx.IDENTIFIER(0).getText());

        if (ctx.variable != null) {
            containmentDto.setIdentifier(ctx.variable.getText());
            containmentByAlias.put(containmentDto.getIdentifier(), containmentDto);
        }

        if (ctx.pathPredicate() != null) {
            containmentDto.setPredicates(visitPathPredicate(ctx.pathPredicate()));
        }

        return containmentDto;
    }

    @Override
    public List<AndOperatorPredicate> visitPathPredicate(PathPredicateContext ctx) {
        if (ctx == null) {
            return new ArrayList<>();
        }
        return ctx.pathPredicateAnd().stream().map(this::visitPathPredicateAnd).collect(Collectors.toList());
    }

    @Override
    public AndOperatorPredicate visitPathPredicateAnd(PathPredicateAndContext ctx) {
        Stream<ComparisonOperatorPredicate> predicates;
        if (ctx.PARAMETER() != null) {
            predicates = Stream.of(new ComparisonOperatorPredicate(
                    AqlObjectPathUtil.ARCHETYPE_NODE_ID,
                    PredicateComparisonOperator.EQ,
                    createParameter(ctx.PARAMETER())));

        } else if (ctx.nodeConstraint() != null) {
            predicates = Stream.concat(
                    Stream.of(visitNodeConstraint(ctx.nodeConstraint())),
                    visitNameConstraint(ctx.nameConstraint()).stream());

        } else {
            predicates = Stream.empty();
        }

        predicates = Stream.concat(predicates, ctx.terminalPredicate().stream().map(this::visitTerminalPredicate));
        return new AndOperatorPredicate(predicates.collect(Collectors.toList()));
    }

    @Override
    public ComparisonOperatorPredicate visitStandardPredicate(StandardPredicateContext ctx) {
        String operatorSymbol = ctx.COMPARISON_OPERATOR().getText();
        PredicateComparisonOperator operator = PredicateComparisonOperator.findBySymbol(operatorSymbol)
                .orElseThrow(() -> new AqlParseException("Unknown comparison operator %s".formatted(operatorSymbol)));
        return new ComparisonOperatorPredicate(
                visitObjectPath(ctx.objectPath()), operator, visitPathPredicateOperand(ctx.pathPredicateOperand()));
    }

    @Override
    public ComparisonOperatorPredicate visitTerminalPredicate(TerminalPredicateContext ctx) {

        if (ctx.standardPredicate() != null) {
            return visitStandardPredicate(ctx.standardPredicate());
        }
        if (ctx.MATCHES() != null) {
            return ComparisonOperatorPredicate.matches(
                    visitObjectPath(ctx.objectPath()),
                    new AdlRegex(getFullText(ctx.CONTAINED_REGEX().getSymbol())));
        }

        throw new AqlParseException("Cannot parse nodePredicate %s".formatted(getFullText(ctx)));
    }

    @Override
    public ComparisonOperatorPredicate visitNodeConstraint(NodeConstraintContext ctx) {
        return new ComparisonOperatorPredicate(
                AqlObjectPathUtil.ARCHETYPE_NODE_ID,
                PredicateComparisonOperator.EQ,
                new StringPrimitive(ctx.getText()));
    }

    @Override
    public List<ComparisonOperatorPredicate> visitNameConstraint(NameConstraintContext ctx) {
        if (ctx == null) {
            return List.of();
        }

        // name/value cases
        if (ctx.PARAMETER() != null) {
            return List.of(new ComparisonOperatorPredicate(
                    AqlObjectPathUtil.NAME_VALUE, PredicateComparisonOperator.EQ, createParameter(ctx.PARAMETER())));
        }
        if (ctx.STRING() != null) {
            return List.of(new ComparisonOperatorPredicate(
                    AqlObjectPathUtil.NAME_VALUE,
                    PredicateComparisonOperator.EQ,
                    new StringPrimitive(unescapeText(ctx))));
        }

        String text = getFullText(ctx);
        // Terminology cases
        final String termId;
        final String code;
        if (ObjectUtils.anyNotNull(ctx.ID_CODE(), ctx.AT_CODE())) {
            termId = "local";
            code = text;
        } else if (ctx.TERM_CODE() != null) {
            int termIdEnd = text.indexOf("::");
            int commentStart = text.indexOf('|', termIdEnd + 2);
            termId = text.substring(0, termIdEnd);
            code = text.substring(termIdEnd + 2, commentStart > 0 ? commentStart : text.length());
        } else {
            throw new AqlParseException("Cannot parse name constraint %s".formatted(text));
        }
        return List.of(
                new ComparisonOperatorPredicate(
                        AqlObjectPathUtil.NAME_CODE_STRING, PredicateComparisonOperator.EQ, new StringPrimitive(code)),
                new ComparisonOperatorPredicate(
                        AqlObjectPathUtil.NAME_TERMINOLOGY,
                        PredicateComparisonOperator.EQ,
                        new StringPrimitive(termId)));
    }

    @Override
    public PathPredicateOperand visitPathPredicateOperand(PathPredicateOperandContext ctx) {
        if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        }
        if (ctx.primitive() != null) {
            return visitPrimitive(ctx.primitive());
        }
        if (ctx.AT_CODE() != null || ctx.ID_CODE() != null) {
            return new StringPrimitive(ctx.getText());
        }
        if (ctx.objectPath() != null) {
            return visitObjectPath(ctx.objectPath());
        }

        throw new AqlParseException("Failed to parse PathPredicateOperand: %s".formatted(getFullText(ctx)));
    }

    @Override
    public AqlObjectPath visitObjectPath(ObjectPathContext ctx) {
        return new AqlObjectPath(
                ctx.pathPart().stream().map(this::visitPathPart).toList());
    }

    @Override
    public PathNode visitPathPart(PathPartContext ctx) {
        return new PathNode(ctx.IDENTIFIER().getText(), visitPathPredicate(ctx.pathPredicate()));
    }

    @Override
    public ContainmentVersionExpression visitVersionClassExpr(VersionClassExprContext ctx) {

        errors.add("Not implemented: VERSION is not yet supported");
        return new ContainmentVersionExpression() {};
    }

    @Override
    public WhereCondition visitWhereExpr(WhereExprContext ctx) {

        if (ctx.identifiedExpr() != null) {
            return visitIdentifiedExpr(ctx.identifiedExpr());
        } else if (ctx.NOT() != null) {
            NotCondition notCondition = new NotCondition();
            notCondition.setConditionDto(visitWhereExpr(ctx.whereExpr(0)));
            return notCondition;
        } else if (ctx.SYM_RIGHT_PAREN() != null) {
            return visitWhereExpr(ctx.whereExpr(0));
        }
        // AND /  OR
        else {
            LogicalOperatorCondition result = new LogicalOperatorCondition();
            ConditionLogicalOperatorSymbol symbol = extractSymbol(ctx);
            result.setSymbol(symbol);
            result.setValues(getOperands(ctx, symbol));
            return result;
        }
    }

    private List<WhereCondition> getOperands(WhereExprContext ctx, ConditionLogicalOperatorSymbol symbol) {
        return IntStream.of(0, 1)
                .mapToObj(ctx::whereExpr)
                .map(this::visitWhereExpr)
                .flatMap(e -> (e instanceof LogicalOperatorCondition s && s.getSymbol() == symbol)
                        ? s.getValues().stream()
                        : Stream.of(e))
                .collect(Collectors.toList());
    }

    @Override
    public WhereCondition visitIdentifiedExpr(IdentifiedExprContext ctx) {

        if (ctx.COMPARISON_OPERATOR() != null) {
            ComparisonOperatorCondition comparisonOperatorCondition = new ComparisonOperatorCondition();
            comparisonOperatorCondition.setSymbol(ComparisonOperatorSymbol.fromSymbol(
                    ctx.COMPARISON_OPERATOR().getText()));

            final ComparisonLeftOperand statement;
            if (ctx.functionCall() != null) {
                statement = visitFunctionCall(ctx.functionCall());
            } else {
                statement = visitIdentifiedPath(ctx.identifiedPath());
            }
            comparisonOperatorCondition.setStatement(statement);
            comparisonOperatorCondition.setValue(visitTerminal(ctx.terminal()));
            return comparisonOperatorCondition;

        } else if (ctx.likeOperand() != null) {
            LikeCondition likeCondition = new LikeCondition();
            likeCondition.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
            likeCondition.setValue(visitLikeOperand(ctx.likeOperand()));
            return likeCondition;

        } else if (ctx.SYM_LEFT_PAREN() != null) {
            return visitIdentifiedExpr(ctx.identifiedExpr());
        } else if (ctx.EXISTS() != null) {
            ExistsCondition existsCondition = new ExistsCondition();
            existsCondition.setValue(visitIdentifiedPath(ctx.identifiedPath()));
            return existsCondition;

        } else if (ctx.MATCHES() != null) {
            MatchesCondition matchesCondition = new MatchesCondition();
            matchesCondition.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
            matchesCondition.setValues(visitMatchesOperand(ctx.matchesOperand()));
            return matchesCondition;
        } else {
            errors.add("Cannot handle %s".formatted(ctx.getText()));
            return null;
        }
    }

    @Override
    public List<MatchesOperand> visitMatchesOperand(MatchesOperandContext ctx) {

        if (CollectionUtils.isNotEmpty(ctx.valueListItem())) {
            return ctx.valueListItem().stream().map(this::visitValueListItem).collect(Collectors.toList());
        } else if (ctx.terminologyFunction() != null) {
            errors.add("Not implemented: Terminology is not yet supported");
            return Stream.of(new TerminologyFunction()).collect(Collectors.toList());
        } else {
            errors.add("Not implemented: MATCHES URI not yet supported");
            return new ArrayList<>();
        }
    }

    @Override
    public MatchesOperand visitValueListItem(ValueListItemContext ctx) {

        if (ctx.primitive() != null) {
            return visitPrimitive(ctx.primitive());
        } else if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        } else {
            errors.add("Not implemented: Terminology not yet supported");
            return new TerminologyFunction();
        }
    }

    @Override
    public LikeOperand visitLikeOperand(LikeOperandContext ctx) {

        if (ctx.PARAMETER() != null) {
            return createParameter(ctx.PARAMETER());
        } else {
            return new StringPrimitive(unescapeText(ctx));
        }
    }

    @Override
    public List<OrderByExpression> visitOrderByClause(OrderByClauseContext ctx) {

        return ctx.orderByExpr().stream().map(this::visitOrderByExpr).collect(Collectors.toList());
    }

    public List<String> getErrors() {
        return errors;
    }

    private static String getFullText(ParserRuleContext context) {

        if (context.start == null
                || context.stop == null
                || context.start.getStartIndex() < 0
                || context.stop.getStopIndex() < 0) return context.getText(); // Fallback

        return context.start
                .getInputStream()
                .getText(Interval.of(context.start.getStartIndex(), context.stop.getStopIndex()));
    }

    private static String getFullText(Token token) {
        return token.getInputStream().getText(Interval.of(token.getStartIndex(), token.getStopIndex()));
    }

    @Override
    public OrderByExpression visitOrderByExpr(OrderByExprContext ctx) {
        OrderByExpression orderByExpression = new OrderByExpression();
        orderByExpression.setStatement(visitIdentifiedPath(ctx.identifiedPath()));
        orderByExpression.setSymbol(extractSymbol(ctx));
        return orderByExpression;
    }

    private static String unescapeText(RuleContext ctx) {

        /*
         *     STRING
         *     : SYM_SINGLE_QUOTE ( ESCAPE_SEQ | UTF8CHAR | OCTAL_ESC | ~('\\'|'\'') )* SYM_SINGLE_QUOTE
         *     | SYM_DOUBLE_QUOTE ( ESCAPE_SEQ | UTF8CHAR | OCTAL_ESC | ~('\\'|'"') )* SYM_DOUBLE_QUOTE
         *
         *     fragment ESCAPE_SEQ: '\\' ['"?abfnrtv\\] ;
         *     fragment UTF8CHAR: '\\u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT ;
         *     fragment DIGIT: [0-9];
         *     fragment HEX_DIGIT: [0-9a-fA-F];
         *
         *     fragment OCTAL_ESC: '\\' [0-3] OCTAL_DIGIT OCTAL_DIGIT | '\\' OCTAL_DIGIT OCTAL_DIGIT | '\\' OCTAL_DIGIT;
         *     fragment OCTAL_DIGIT: [0-7];
         */
        String text = ctx.getText();
        StringBuilder sb = new StringBuilder(text.length() - 2);

        // remove trailing quote
        int end = text.length() - 1;

        for (int pos = 1; pos < end; ) {
            char ch = text.charAt(pos);

            if (ch == '\\') {
                ch = text.charAt(++pos);
                switch (ch) {
                    case '\\':
                    case '"':
                    case '\'':
                        sb.append(ch);
                        break;
                    case 'a':
                        sb.append((char) 0x0007);
                        break;
                    case 'b':
                        sb.append((char) 0x0008);
                        break;
                    case 'f':
                        sb.append((char) 0x000c);
                        break;
                    case 'n':
                        sb.append((char) 0x000a);
                        break;
                    case 'r':
                        sb.append((char) 0x000d);
                        break;
                    case 't':
                        sb.append((char) 0x0009);
                        break;
                    case 'v':
                        sb.append((char) 0x000b);
                        break;
                    case 'u':
                        // UTF8CHAR
                        sb.append(decodeUtf(16, text.substring(pos + 1, pos + 5)));
                        pos += 4;
                        break;
                    default:
                        if (isOctal(ch)) {
                            // OCTAL_ESC
                            // UTF8CHAR
                            int oCount = countOctals(text, pos + 1, (ch <= '3') ? 2 : 1);
                            sb.append(decodeUtf(8, text.substring(pos, pos + 1 + oCount)));
                            pos += oCount;
                            break;
                        }
                        throw new IllegalArgumentException(
                                "Unsupported escaped character %s in %s".formatted(ch, text));
                }
                pos++;

            } else {
                pos++;
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    static boolean isOctal(char ch) {
        return switch (ch) {
            case '0', '1', '2', '3', '4', '5', '6', '7' -> true;
            default -> false;
        };
    }

    static int countOctals(String text, int pos, int max) {
        int maxPos = Math.min(text.length(), pos + max);
        int count = 0;
        for (int p = pos; p < maxPos; p++) {
            if (isOctal(text.charAt(p))) {
                count++;
            } else {
                return count;
            }
        }
        return maxPos - pos;
    }

    static String decodeUtf(int radix, String hex) {
        int codePoint = Integer.parseInt(hex, radix);
        if (Character.isISOControl(codePoint) || !Character.isValidCodePoint(codePoint)) {
            throw new IllegalArgumentException("Unsupported unicode character u%s".formatted(hex));
        }
        String string = Character.toString(codePoint);
        if (string.length() != 1 || string.codePointAt(0) != codePoint) {
            throw new IllegalArgumentException("Unsupported unicode character u%s".formatted(hex));
        }
        return string;
    }

    private OrderByDirection extractSymbol(OrderByExprContext ctx) {
        if (ctx.DESC() != null || ctx.DESCENDING() != null) {
            return OrderByDirection.DESC;
        } else {
            return OrderByDirection.ASC;
        }
    }

    private ContainmentSetOperatorSymbol extractSymbol(ContainsExprContext ctx) {
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

    private ConditionLogicalOperatorSymbol extractSymbol(WhereExprContext ctx) {

        if (ctx.OR() != null) {
            return ConditionLogicalOperatorSymbol.OR;
        } else if (ctx.AND() != null) {
            return ConditionLogicalOperatorSymbol.AND;
        } else {
            return null;
        }
    }
}
