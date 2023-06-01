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
import java.util.UUID;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.containment.*;
import org.ehrbase.aql.dto.operant.*;
import org.ehrbase.aql.dto.path.AqlPath;
import org.ehrbase.aql.dto.path.predicate.PredicateHelper;
import org.ehrbase.aql.dto.select.SelectDto;
import org.ehrbase.aql.dto.select.SelectExpressionDto;
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

        String from = buildFrom();

        renderSelect(sb, dto.getSelect());

        sb.append(from);

        return sb.toString();
    }

    private void renderSelect(StringBuilder sb, SelectDto dto) {

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

    private void renderSelectStatementDto(StringBuilder sb, SelectExpressionDto dto) {
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

        ContainmentDto containmentDto = dto.getFrom();

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

    private void renderContainmentExpresionDto(StringBuilder sb, ContainmentExpresionDto dto) {

        if (dto instanceof ContainmentClassExpressionDto classExpressionDto) {
            renderContainmentDto(sb, classExpressionDto);
        } else if (dto instanceof ContainmentLogicalOperator containmentLogicalOperator) {
            renderContainmentLogicalOperator(sb, containmentLogicalOperator);
        } else {
            throw new UnsupportedOperationException(
                    "Can not handle %s".formatted(dto.getClass().getName()));
        }
    }

    private void renderContainmentLogicalOperator(
            StringBuilder sb, ContainmentLogicalOperator containmentLogicalOperator) {

        Iterator<ContainmentExpresionDto> iterator =
                containmentLogicalOperator.getValues().iterator();
        sb.append("(");
        while (iterator.hasNext()) {
            ContainmentExpresionDto next = iterator.next();
            renderContainmentExpresionDto(sb, next);
            if (iterator.hasNext()) {
                sb.append(" ").append(containmentLogicalOperator.getSymbol()).append(" ");
            }
        }
        sb.append(")");
    }

    private void renderContainmentDto(StringBuilder sb, ContainmentClassExpressionDto dto) {

        sb.append(dto.getType()).append(" ");

        if (dto.getIdentifier() != null) {
            sb.append(dto.getIdentifier());
        }

        if (dto.getOtherPredicates() != null) {

            sb.append(PredicateHelper.format(dto.getOtherPredicates(), AqlPath.OtherPredicatesFormat.SHORTED));
        }

        if (dto.getContains() != null) {

            if (dto.getContains() instanceof ContainmentLogicalNotOperator containmentLogicalNotOperator) {
                sb.append(" NOT CONTAINS ");
                renderContainmentExpresionDto(sb, containmentLogicalNotOperator.getContainmentExpression());
            } else {
                sb.append(" CONTAINS ");
                renderContainmentExpresionDto(sb, dto.getContains());
            }
        }
    }
}
