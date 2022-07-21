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
package org.ehrbase.client.aql.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.funtion.Function;
import org.ehrbase.client.aql.orderby.OrderByExpression;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.client.aql.record.Record;
import org.ehrbase.client.aql.top.TopExpresion;

public class EntityQuery<T extends Record> implements Query<T> {
    private final SelectAqlField<Object>[] fields;
    private final ContainmentExpression containmentExpression;
    private int variabelCount = 0;
    private int parameterCount = 0;
    private int selectCount = 0;
    private Map<Containment, String> variablesMap;
    private Condition where;
    private OrderByExpression orderByExpression;
    private TopExpresion topExpresion;
    private final Containment ehrContainment;
    private Integer limit;
    private Integer offset;

    private boolean isDistinct = false;

    protected EntityQuery(ContainmentExpression containmentExpression, SelectAqlField<?>... fields) {
        this(containmentExpression, new HashMap<>(), fields);
    }

    protected EntityQuery(
            ContainmentExpression containmentExpression,
            Map<Containment, String> variablesMap,
            SelectAqlField<?>... fields) {
        this.variablesMap = variablesMap;
        ehrContainment = new Containment("EHR");
        String ehrVariableName = variablesMap.entrySet().stream()
                .filter(e -> e.getKey().getTypeName().equals("EHR"))
                .map(Map.Entry::getValue)
                .findAny()
                .orElse("e");
        variablesMap.put(ehrContainment, ehrVariableName);
        ehrContainment.bindQuery(this);
        this.fields = Arrays.stream(fields).map(this::replace).toArray(SelectAqlField[]::new);

        this.containmentExpression = containmentExpression;

        if (containmentExpression != null) {
            containmentExpression.bindQuery(this);
        }
    }

    private SelectAqlField<Object> replace(SelectAqlField<?> selectAqlField) {

        if (selectAqlField instanceof Function) {

            List<SelectAqlField<?>> parameters = ((Function) selectAqlField).getParameters();
            List<SelectAqlField<Object>> replaceList =
                    parameters.stream().map(this::replace).collect(Collectors.toList());

            parameters.clear();
            parameters.addAll(replaceList);

            return (SelectAqlField<Object>) selectAqlField;
        }

        if (selectAqlField.getContainment().getTypeName().equals("EHR")) {
            return new AqlFieldImp(
                    selectAqlField.getEntityClass(),
                    selectAqlField.getPath(),
                    selectAqlField.getName(),
                    selectAqlField.getValueClass(),
                    ehrContainment);
        } else {
            return (SelectAqlField<Object>) selectAqlField;
        }
    }

    @Override
    public String buildAql() {
        StringBuilder sb = new StringBuilder();
        sb.append("Select ");

        if (isDistinct) {
            sb.append("DISTINCT").append(" ");
        }

        if (topExpresion != null) {
            sb.append(topExpresion.buildAql()).append(" ");
        }
        List<String> usedNames = new ArrayList<>();
        sb.append(Arrays.stream(fields)
                        .map(field -> buildFieldAql(field, usedNames))
                        .collect(Collectors.joining(", ")))
                .append(" from EHR ")
                .append(buildVariabelName(ehrContainment));
        if (containmentExpression != null) {
            sb.append(" contains ").append(containmentExpression.buildAQL());
        }
        if (where != null) {
            sb.append(" where ").append(where.buildAql(ehrContainment));
        }
        if (limit != null) {
            sb.append(" LIMIT ").append(limit);
        }

        if (offset != null) {
            sb.append(" OFFSET ").append(offset);
        }

        if (orderByExpression != null) {
            sb.append(" order by ").append(orderByExpression.buildAql(ehrContainment));
        }

        return sb.toString();
    }

    private String buildFieldAql(SelectAqlField<?> field, List<String> usedNames) {
        selectCount++;

        String name = StringUtils.isNotBlank(field.getName())
                ? field.getName().replaceAll("[^A-Za-z0-9]", "_")
                : "F" + selectCount;
        while (usedNames.contains(name)) {
            name = name + "_F" + selectCount;
        }
        usedNames.add(name);
        return field.buildAQL(ehrContainment) + " as " + name;
    }

    @Override
    public AqlField<Object>[] fields() {
        return fields;
    }

    public String buildVariabelName(Containment containment) {
        return variablesMap.computeIfAbsent(containment, this::buildVariablesNameIntern);
    }

    private String buildVariablesNameIntern(Containment containment) {
        String name = containment.getType().getSimpleName().substring(0, 1).toLowerCase() + variabelCount;
        variabelCount++;
        return name;
    }

    public EntityQuery<T> where(Condition where) {
        this.where = where;
        return this;
    }

    public EntityQuery<T> orderBy(OrderByExpression orderByExpression) {
        this.orderByExpression = orderByExpression;
        return this;
    }

    public EntityQuery<T> top(TopExpresion topExpresion) {
        this.topExpresion = topExpresion;
        return this;
    }

    public EntityQuery<T> distinct(boolean isDistinct) {
        this.isDistinct = isDistinct;
        return this;
    }

    public EntityQuery<T> limit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public EntityQuery<T> offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    public String buildParameterName() {
        String name = "parm" + parameterCount;
        parameterCount++;
        return name;
    }

    public <V> Parameter<V> buildParameter() {
        return new Parameter<>(this);
    }
}
