/*
 * Copyright (c) 2023 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.aql.render;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.ehrbase.aql.dto.AqlQuery;
import org.ehrbase.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.aql.dto.condition.ExistsCondition;
import org.ehrbase.aql.dto.condition.LikeCondition;
import org.ehrbase.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.aql.dto.condition.MatchesCondition;
import org.ehrbase.aql.dto.condition.NotCondition;
import org.ehrbase.aql.dto.condition.WhereCondition;
import org.ehrbase.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.aql.dto.operand.AggregateFunction;
import org.ehrbase.aql.dto.operand.AggregateFunction.AggregateFunctionName;
import org.ehrbase.aql.dto.operand.ColumnExpression;
import org.ehrbase.aql.dto.operand.ComparisonLeftOperand;
import org.ehrbase.aql.dto.operand.CountDistinctAggregateFunction;
import org.ehrbase.aql.dto.operand.DoublePrimitive;
import org.ehrbase.aql.dto.operand.IdentifiedPath;
import org.ehrbase.aql.dto.operand.LikeOperand;
import org.ehrbase.aql.dto.operand.MatchesOperand;
import org.ehrbase.aql.dto.operand.Operand;
import org.ehrbase.aql.dto.operand.Primitive;
import org.ehrbase.aql.dto.operand.QueryParameter;
import org.ehrbase.aql.dto.operand.SingleRowFunction;
import org.ehrbase.aql.dto.operand.StringPrimitive;
import org.ehrbase.aql.dto.orderby.OrderByExpression;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.AqlPath.OtherPredicatesFormat;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.SelectClause;
import org.ehrbase.aql.dto.select.SelectExpression;
import org.ehrbase.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public final class AqlRenderer {

    private AqlRenderer() {}

    public static String render(AqlQuery dto) {
        StringBuilder sb = new StringBuilder();

        renderSelect(sb, dto.getSelect());
        renderFromClause(sb, dto.getFrom());

        if (dto.getWhere() != null) {
            sb.append(" WHERE ");
            renderWhere(sb, dto.getWhere());
        }

        if (CollectionUtils.isNotEmpty(dto.getOrderBy())) {

            renderOrderByClause(sb, dto.getOrderBy());
        }

        if (dto.getLimit() != null) {
            sb.append(" LIMIT ").append(dto.getLimit());
            if (dto.getOffset() != null) {
                sb.append(" OFFSET ").append(dto.getOffset());
            }
        }

        return sb.toString();
    }

    private static void renderOrderByClause(StringBuilder sb, List<OrderByExpression> orderBy) {
        sb.append(" ORDER BY ");

        var it = orderBy.iterator();
        while (it.hasNext()) {
            OrderByExpression next = it.next();
            renderIdentifiedPath(sb, next.getStatement());
            sb.append(" ").append(next.getSymbol());

            if (it.hasNext()) {
                sb.append(", ");
            }
        }
    }

    private static void renderWhere(StringBuilder sb, WhereCondition where) {
        if (where instanceof ComparisonOperatorCondition comparisonOperatorCondition) {
            renderConditionComparisonOperatorDto(sb, comparisonOperatorCondition);
        } else if (where instanceof NotCondition notCondition) {
            renderNotConditionOperatorDto(sb, notCondition);
        } else if (where instanceof ExistsCondition existsCondition) {
            renderExistsCondition(sb, existsCondition);
        } else if (where instanceof LogicalOperatorCondition logicalOperatorCondition) {
            renderConditionLogical(sb, logicalOperatorCondition);
        } else if (where instanceof LikeCondition likeCondition) {
            renderLike(sb, likeCondition);
        } else if (where instanceof MatchesCondition matchesCondition) {
            renderMatches(sb, matchesCondition);
        } else {
            throw new SdkException("Cannot handle %s".formatted(where.getClass().getName()));
        }
    }

    private static void renderMatches(StringBuilder sb, MatchesCondition matchesCondition) {

        renderIdentifiedPath(sb, matchesCondition.getStatement());
        sb.append(" ").append("MATCHES").append(" ");
        sb.append("{");
        Iterator<MatchesOperand> iterator = matchesCondition.getValues().iterator();
        while (iterator.hasNext()) {
            MatchesOperand next = iterator.next();
            renderMatchesOperand(sb, next);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
    }

    private static void renderMatchesOperand(StringBuilder sb, MatchesOperand next) {

        if (next instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else if (next instanceof Primitive<?> primitive) {
            sb.append(renderPrimitive(primitive));
        } else {
            throw new SdkException("Cannot handle %s".formatted(next.getClass().getName()));
        }
    }

    private static void renderLike(StringBuilder sb, LikeCondition likeCondition) {
        renderIdentifiedPath(sb, likeCondition.getStatement());
        sb.append(" LIKE ");
        renderLikeOperand(sb, likeCondition.getValue());
    }

    private static void renderLikeOperand(StringBuilder sb, LikeOperand value) {

        if (value instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else {
            sb.append(renderPrimitive((StringPrimitive) value));
        }
    }

    private static void renderConditionLogical(StringBuilder sb, LogicalOperatorCondition logicalOperatorCondition) {

        Iterator<WhereCondition> iterator = logicalOperatorCondition.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {

            renderWhere(sb, iterator.next());
            if (iterator.hasNext()) {
                sb.append(" ").append(logicalOperatorCondition.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private static void renderExistsCondition(StringBuilder sb, ExistsCondition existsCondition) {
        sb.append("EXISTS ");
        renderIdentifiedPath(sb, existsCondition.getValue());
    }

    private static void renderNotConditionOperatorDto(StringBuilder sb, NotCondition notCondition) {
        sb.append("NOT ");
        renderWhere(sb, notCondition.getConditionDto());
    }

    private static void renderConditionComparisonOperatorDto(
            StringBuilder sb, ComparisonOperatorCondition comparisonOperatorCondition) {
        ComparisonLeftOperand statement = comparisonOperatorCondition.getStatement();

        renderComparisonLeftOperator(sb, statement);
        sb.append(" ")
                .append(comparisonOperatorCondition.getSymbol().getSymbol())
                .append(" ");
        sb.append(renderTerminal(comparisonOperatorCondition.getValue()));
    }

    private static void renderComparisonLeftOperator(StringBuilder sb, ComparisonLeftOperand statement) {
        if (statement instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (statement instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else {
            throw new SdkException(
                    "Cannot handle %s".formatted(statement.getClass().getName()));
        }
    }

    private static void renderSelect(StringBuilder sb, SelectClause dto) {
        sb.append("SELECT ");

        if (dto.isDistinct()) {
            sb.append("DISTINCT ");
        }

        var it = dto.getStatement().stream().iterator();
        while (it.hasNext()) {
            renderSelectStatementDto(sb, it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append(" ");
    }

    private static void renderSelectStatementDto(StringBuilder sb, SelectExpression dto) {
        ColumnExpression columnExpression = dto.getColumnExpression();

        if (columnExpression instanceof IdentifiedPath) {
            renderIdentifiedPath(sb, (IdentifiedPath) columnExpression);
        } else if (columnExpression instanceof Primitive) {
            renderSelectPrimitiveDto(sb, (Primitive) columnExpression);
        } else if (columnExpression instanceof AggregateFunction) {
            renderAggregateFunctionDto(sb, (AggregateFunction) columnExpression);
        } else if (columnExpression instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else {
            throw new UnsupportedOperationException(
                    ("Cannot handle %s").formatted(dto.getClass().getName()));
        }

        if (dto.getAlias() != null) {
            sb.append(" AS ").append(dto.getAlias());
        }
    }

    private static void renderAggregateFunctionDto(StringBuilder sb, AggregateFunction aggregateFunction) {

        sb.append(aggregateFunction.getFunctionName().name()).append("(");

        if (aggregateFunction instanceof CountDistinctAggregateFunction) {
            if (aggregateFunction.getIdentifiedPath() == null) {
                throw new IllegalStateException("COUNT(DISTINCT *) is not allowed");
            }
            sb.append("DISTINCT ");
        }

        if (AggregateFunctionName.COUNT.equals(aggregateFunction.getFunctionName())
                && aggregateFunction.getIdentifiedPath() == null) {
            sb.append('*');
        } else {
            renderIdentifiedPath(sb, aggregateFunction.getIdentifiedPath());
        }
        sb.append(")");
    }

    private static void renderSingleRowFunctionDto(StringBuilder sb, SingleRowFunction singleRowFunktion) {

        sb.append(singleRowFunktion.getFunctionName().getName()).append("(");
        sb.append(singleRowFunktion.getOperandList().stream()
                .map(AqlRenderer::renderTerminal)
                .collect(Collectors.joining(", ")));
        sb.append(")");
    }

    private static String renderTerminal(Operand operand) {

        StringBuilder sb = new StringBuilder();
        if (operand instanceof SingleRowFunction singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else if (operand instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (operand instanceof Primitive<?> primitive) {
            sb.append(renderPrimitive(primitive));
        } else if (operand instanceof QueryParameter queryParameter) {
            renderParameterDto(sb, queryParameter);
        } else {
            throw new UnsupportedOperationException(
                    "Cannot handle %s".formatted(operand.getClass().getName()));
        }
        return sb.toString();
    }

    private static void renderParameterDto(StringBuilder sb, QueryParameter queryParameter) {

        sb.append("$").append(queryParameter.getName());
    }

    private static void renderIdentifiedPath(StringBuilder sb, IdentifiedPath dto) {

        AbstractContainmentExpression containmentDto = dto.getFrom();

        if (containmentDto == null) {
            throw new SdkException("SelectClause without corresponding contains");
        }

        sb.append(containmentDto.getIdentifier());
        if (dto.getRootPredicate() != null) {
            sb.append('[');
            PredicateHelper.format(sb, dto.getRootPredicate(), OtherPredicatesFormat.SHORTED);
            sb.append(']');
        }

        sb.append(dto.getPath().format(OtherPredicatesFormat.SHORTED, false));
    }

    private static void renderSelectPrimitiveDto(StringBuilder sb, Primitive dto) {
        sb.append(renderPrimitive(dto));
    }

    private static String renderPrimitive(Primitive primitive) {

        if (primitive.getValue() == null) {
            return "NULL";
        } else if (primitive instanceof DoublePrimitive d) {
            return d.getStringRepresentation();
        } else if (primitive instanceof StringPrimitive s) {
            return encodeString(s.getValue());
        } else {
            return primitive.getValue().toString();
        }
    }

    /**
     * @see org.ehrbase.aql.parser.AqlQueryVisitor::unwrapText
     *
     * @param value
     * @return
     */
    static String encodeString(String value) {
        if (value == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(value.length() + 5);
        sb.append("'");

        // ' \ abfnrtv

        for (int i = 0, l = value.length(); i < l; i++) {
            char ch = value.charAt(i);
            switch (ch) {
                case '\'':
                case '\\':
                    sb.append('\\').append(ch);
                    break;

                case 0x00007:
                    sb.append('\\').append('a');
                    break;
                case 0x00008:
                    sb.append('\\').append('b');
                    break;
                case 0x0000c:
                    sb.append('\\').append('f');
                    break;
                case 0x0000a:
                    sb.append('\\').append('n');
                    break;
                case 0x0000d:
                    sb.append('\\').append('r');
                    break;
                case 0x00009:
                    sb.append('\\').append('t');
                    break;
                case 0x0000b:
                    sb.append('\\').append('v');
                    break;

                default:
                    sb.append(ch);
            }
        }

        sb.append("'");

        return sb.toString();
    }

    private static void renderFromClause(StringBuilder sb, Containment from) {
        sb.append("FROM ");
        renderContainmentExpresionDto(sb, from);
    }

    private static void renderContainmentExpresionDto(StringBuilder sb, Containment dto) {

        if (dto instanceof ContainmentClassExpression classExpressionDto) {
            renderContainmentDto(sb, classExpressionDto);
        } else if (dto instanceof ContainmentSetOperator containmentSetOperator) {
            renderContainmentLogicalOperator(sb, containmentSetOperator);
        } else {
            throw new UnsupportedOperationException(
                    "Cannot handle %s".formatted(dto.getClass().getName()));
        }
    }

    private static void renderContainmentLogicalOperator(
            StringBuilder sb, ContainmentSetOperator containmentSetOperator) {

        Iterator<Containment> iterator = containmentSetOperator.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {
            Containment next = iterator.next();
            renderContainmentExpresionDto(sb, next);
            if (iterator.hasNext()) {
                sb.append(" ").append(containmentSetOperator.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private static void renderContainmentDto(StringBuilder sb, ContainmentClassExpression dto) {

        sb.append(dto.getType());

        if (dto.getIdentifier() != null) {
            sb.append(" ").append(dto.getIdentifier());
        }

        if (dto.getPredicates() != null) {

            sb.append("[")
                    .append(PredicateHelper.format(dto.getPredicates(), AqlPath.OtherPredicatesFormat.SHORTED))
                    .append("]");
        }

        if (dto.getContains() != null) {

            if (dto.getContains() instanceof ContainmentNotOperator containmentNotOperator) {
                sb.append(" NOT CONTAINS ");
                renderContainmentExpresionDto(sb, containmentNotOperator.getContainmentExpression());
            } else {
                sb.append(" CONTAINS ");
                renderContainmentExpresionDto(sb, dto.getContains());
            }
        }
    }
}
