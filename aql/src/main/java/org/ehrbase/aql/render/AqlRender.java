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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nedap.archie.json.JacksonUtil;
import java.time.temporal.TemporalAccessor;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ExistsConditionOperatorDto;
import org.ehrbase.aql.dto.condition.LikeOperatorDto;
import org.ehrbase.aql.dto.condition.MatchesOperatorDto;
import org.ehrbase.aql.dto.condition.NotConditionOperatorDto;
import org.ehrbase.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.aql.dto.containment.Containment;
import org.ehrbase.aql.dto.containment.ContainmentClassExpression;
import org.ehrbase.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.aql.dto.operand.AggregateFunctionDto;
import org.ehrbase.aql.dto.operand.ColumnExpression;
import org.ehrbase.aql.dto.operand.ComparisonLeftOperator;
import org.ehrbase.aql.dto.operand.IdentifiedPath;
import org.ehrbase.aql.dto.operand.LikeOperant;
import org.ehrbase.aql.dto.operand.MatchesOperant;
import org.ehrbase.aql.dto.operand.ParameterDto;
import org.ehrbase.aql.dto.operand.Primitive;
import org.ehrbase.aql.dto.operand.SingleRowFunktion;
import org.ehrbase.aql.dto.operand.StringPrimitiveDto;
import org.ehrbase.aql.dto.operand.Terminal;
import org.ehrbase.aql.dto.orderby.OrderByExpression;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.Select;
import org.ehrbase.aql.dto.select.SelectExpression;
import org.ehrbase.util.exception.SdkException;

/**
 * @author Stefan Spiska
 */
public class AqlRender {

    private final AqlDto dto;

    public AqlRender(AqlDto dto) {
        this.dto = dto;
    }

    public String render() {

        StringBuilder sb = new StringBuilder();

        renderSelect(sb, dto.getSelect());
        sb.append(buildFrom());

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

    private void renderOrderByClause(StringBuilder sb, List<OrderByExpression> orderBy) {
        sb.append(" ORDER BY ");

        Iterator<OrderByExpression> iterator = orderBy.iterator();

        while (iterator.hasNext()) {

            OrderByExpression next = iterator.next();
            renderIdentifiedPath(sb, next.getStatement());
            sb.append(" ").append(next.getSymbol());

            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
    }

    private void renderWhere(StringBuilder sb, ConditionDto where) {
        if (where instanceof ConditionComparisonOperatorDto conditionComparisonOperatorDto) {
            renderConditionComparisonOperatorDto(sb, conditionComparisonOperatorDto);
        } else if (where instanceof NotConditionOperatorDto notConditionOperatorDto) {
            renderNotConditionOperatorDto(sb, notConditionOperatorDto);
        } else if (where instanceof ExistsConditionOperatorDto existsConditionOperatorDto) {
            renderExistsCondition(sb, existsConditionOperatorDto);
        } else if (where instanceof ConditionLogicalOperatorDto conditionLogicalOperatorDto) {
            renderConditionLogical(sb, conditionLogicalOperatorDto);
        } else if (where instanceof LikeOperatorDto likeOperatorDto) {
            renderLike(sb, likeOperatorDto);
        } else if (where instanceof MatchesOperatorDto matchesOperatorDto) {
            renderMatches(sb, matchesOperatorDto);
        } else {
            throw new SdkException(
                    "Can not handle %s".formatted(where.getClass().getName()));
        }
    }

    private void renderMatches(StringBuilder sb, MatchesOperatorDto matchesOperatorDto) {

        renderIdentifiedPath(sb, matchesOperatorDto.getStatement());
        sb.append(" ").append("MATCHES").append(" ");
        sb.append("{");
        Iterator<MatchesOperant> iterator = matchesOperatorDto.getValues().iterator();
        while (iterator.hasNext()) {
            MatchesOperant next = iterator.next();
            renderMatchesOperant(sb, next);
            if (iterator.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("}");
    }

    private void renderMatchesOperant(StringBuilder sb, MatchesOperant next) {

        if (next instanceof ParameterDto parameterDto) {
            renderParameterDto(sb, parameterDto);
        } else if (next instanceof Primitive<?> primitive) {
            sb.append(renderValue(primitive.getValue()));
        } else {
            throw new SdkException("Can not handle %s".formatted(next.getClass().getName()));
        }
    }

    private void renderLike(StringBuilder sb, LikeOperatorDto likeOperatorDto) {
        renderIdentifiedPath(sb, likeOperatorDto.getStatement());
        sb.append(" ").append("LIKE").append(" ");
        renderLikeOperant(sb, likeOperatorDto.getValue());
    }

    private void renderLikeOperant(StringBuilder sb, LikeOperant value) {

        if (value instanceof ParameterDto parameterDto) {
            renderParameterDto(sb, parameterDto);
        } else {
            sb.append(renderValue(((StringPrimitiveDto) value).getValue()));
        }
    }

    private void renderConditionLogical(StringBuilder sb, ConditionLogicalOperatorDto conditionLogicalOperatorDto) {

        Iterator<ConditionDto> iterator =
                conditionLogicalOperatorDto.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {

            renderWhere(sb, iterator.next());
            if (iterator.hasNext()) {
                sb.append(" ").append(conditionLogicalOperatorDto.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private void renderExistsCondition(StringBuilder sb, ExistsConditionOperatorDto existsConditionOperatorDto) {
        sb.append("EXISTS ");
        renderIdentifiedPath(sb, existsConditionOperatorDto.getValue());
    }

    private void renderNotConditionOperatorDto(StringBuilder sb, NotConditionOperatorDto notConditionOperatorDto) {
        sb.append("NOT ");
        renderWhere(sb, notConditionOperatorDto.getConditionDto());
    }

    private void renderConditionComparisonOperatorDto(
            StringBuilder sb, ConditionComparisonOperatorDto conditionComparisonOperatorDto) {
        ComparisonLeftOperator statement = conditionComparisonOperatorDto.getStatement();

        renderComparisonLeftOperator(sb, statement);
        sb.append(" ")
                .append(conditionComparisonOperatorDto.getSymbol().getSymbol())
                .append(" ");
        sb.append(renderTerminal(conditionComparisonOperatorDto.getValue()));
    }

    private void renderComparisonLeftOperator(StringBuilder sb, ComparisonLeftOperator statement) {
        if (statement instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (statement instanceof SingleRowFunktion singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else {
            throw new SdkException(
                    "Can not handle %s".formatted(statement.getClass().getName()));
        }
    }

    private void renderSelect(StringBuilder sb, Select dto) {

        sb.append("SELECT ");

        if (dto.isDistinct()) {
            sb.append("DISTINCT ");
        }

        sb.append(dto.getStatement().stream()
                .map(s -> {
                    StringBuilder sbInner = new StringBuilder();
                    renderSelectStatementDto(sbInner, s);
                    return sbInner.toString();
                })
                .collect(Collectors.joining(", ")));

        sb.append(" ");
    }

    private void renderSelectStatementDto(StringBuilder sb, SelectExpression dto) {
        ColumnExpression columnExpression = dto.getColumnExpression();

        if (columnExpression instanceof IdentifiedPath) {
            renderIdentifiedPath(sb, (IdentifiedPath) columnExpression);
        } else if (columnExpression instanceof Primitive) {
            renderSelectPrimitiveDto(sb, (Primitive) columnExpression);
        } else if (columnExpression instanceof AggregateFunctionDto) {

            renderAggregateFunctionDto(sb, (AggregateFunctionDto) columnExpression);
        } else if (columnExpression instanceof SingleRowFunktion singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else {
            throw new UnsupportedOperationException(
                    "Can not handle %s".formatted(dto.getClass().getName()));
        }

        if (dto.getAlias() != null) {
            sb.append(" AS ").append(dto.getAlias());
        }
    }

    private void renderAggregateFunctionDto(StringBuilder sb, AggregateFunctionDto aggregateFunctionDto) {

        sb.append(aggregateFunctionDto.getFunctionName().name()).append("(");

        renderIdentifiedPath(sb, aggregateFunctionDto.getIdentifiedPath());
        sb.append(")");
    }

    private void renderSingleRowFunctionDto(StringBuilder sb, SingleRowFunktion singleRowFunktion) {

        sb.append(singleRowFunktion.getFunctionName().name()).append("(");
        sb.append(singleRowFunktion.getOperantList().stream()
                .map(this::renderTerminal)
                .collect(Collectors.joining(", ")));
        sb.append(")");
    }

    private String renderTerminal(Terminal terminal) {

        StringBuilder sb = new StringBuilder();
        if (terminal instanceof SingleRowFunktion singleRowFunktion) {
            renderSingleRowFunctionDto(sb, singleRowFunktion);
        } else if (terminal instanceof IdentifiedPath identifiedPath) {
            renderIdentifiedPath(sb, identifiedPath);
        } else if (terminal instanceof Primitive<?> primitive) {
            sb.append(renderValue(primitive.getValue()));
        } else if (terminal instanceof ParameterDto parameterDto) {
            renderParameterDto(sb, parameterDto);
        } else {
            throw new UnsupportedOperationException(
                    "Can not handle %s".formatted(terminal.getClass().getName()));
        }
        return sb.toString();
    }

    private void renderParameterDto(StringBuilder sb, ParameterDto parameterDto) {

        sb.append("$").append(parameterDto.getName());
    }

    private void renderIdentifiedPath(StringBuilder sb, IdentifiedPath dto) {

        AbstractContainmentExpression containmentDto = dto.getFrom();

        if (containmentDto == null) {
            throw new SdkException("Select without corresponding contains");
        }

        sb.append(containmentDto.getIdentifier());

        sb.append(dto.getPath().format(AqlPath.OtherPredicatesFormat.SHORTED, false));
    }

    private void renderSelectPrimitiveDto(StringBuilder sb, Primitive dto) {

        sb.append(renderValue(dto.getValue()));
    }

    public String renderValue(Object value) {

        if (value == null) {

            return "NULL";
        }
        if (Long.class.isAssignableFrom(value.getClass()) || Integer.class.isAssignableFrom(value.getClass())) {
            return value.toString();
        } else if (Double.class.isAssignableFrom(value.getClass()) || Float.class.isAssignableFrom(value.getClass())) {
            return value.toString();
        }
        if (Boolean.class.isAssignableFrom(value.getClass())) {
            return value.toString();
        } else if (String.class.isAssignableFrom(value.getClass()) || UUID.class.isAssignableFrom(value.getClass())) {
            return StringUtils.wrap(value.toString(), "'");
        } else if (TemporalAccessor.class.isAssignableFrom(value.getClass())) {
            String valueAsString;
            try {
                valueAsString = JacksonUtil.getObjectMapper().writeValueAsString(value);
            } catch (JsonProcessingException e) {
                throw new SdkException(e.getMessage(), e);
            }
            return StringUtils.wrap(valueAsString.replace("\"", ""), "'");
        } else {
            throw new SdkException(
                    "%s is not an valid AQL Value".formatted(value.getClass().getName()));
        }
    }

    private String buildFrom() {
        StringBuilder sb = new StringBuilder();

        sb.append("FROM ");

        renderContainmentExpresionDto(sb, dto.getFrom());

        return sb.toString();
    }

    private void renderContainmentExpresionDto(StringBuilder sb, Containment dto) {

        if (dto instanceof ContainmentClassExpression classExpressionDto) {
            renderContainmentDto(sb, classExpressionDto);
        } else if (dto instanceof ContainmentSetOperator containmentSetOperator) {
            renderContainmentLogicalOperator(sb, containmentSetOperator);
        } else {
            throw new UnsupportedOperationException(
                    "Can not handle %s".formatted(dto.getClass().getName()));
        }
    }

    private void renderContainmentLogicalOperator(StringBuilder sb, ContainmentSetOperator containmentSetOperator) {

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

    private void renderContainmentDto(StringBuilder sb, ContainmentClassExpression dto) {

        sb.append(dto.getType());

        if (dto.getIdentifier() != null) {
            sb.append(" ").append(dto.getIdentifier());
        }

        if (dto.getOtherPredicates() != null) {

            sb.append("[")
                    .append(PredicateHelper.format(dto.getOtherPredicates(), AqlPath.OtherPredicatesFormat.SHORTED))
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
