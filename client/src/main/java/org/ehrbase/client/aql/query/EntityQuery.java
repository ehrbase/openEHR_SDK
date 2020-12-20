/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.query;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.client.aql.field.AqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
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
  private Map<Containment, String> variablesMap = new HashMap<>();
  private Condition where;
  private OrderByExpression orderByExpression;
  private TopExpresion topExpresion;

  protected EntityQuery(ContainmentExpression containmentExpression, SelectAqlField<?>... fields) {
    this.fields = (SelectAqlField<Object>[]) fields;
    this.containmentExpression = containmentExpression;
    containmentExpression.bindQuery(this);
  }

  @Override
  public String buildAql() {
    StringBuilder sb = new StringBuilder();
    sb.append("Select ");
    if (topExpresion != null) {
      sb.append(topExpresion.buildAql()).append(" ");
    }

    sb.append(Arrays.stream(fields).map(this::buildFieldAql).collect(Collectors.joining(", ")))
        .append(" from EHR e ");
    if (containmentExpression != null) {
      sb.append(" contains ").append(containmentExpression.buildAQL());
    }
    if (where != null) {
      sb.append(" where ").append(where.buildAql());
    }
    if (orderByExpression != null) {
      sb.append(" order by ").append(orderByExpression.buildAql());
    }
    return sb.toString();
  }

  private String buildFieldAql(SelectAqlField<?> field) {
    selectCount++;

    return field.buildAQL()
        + " as "
        + (StringUtils.isNotBlank(field.getName())
            ? field.getName().replaceAll("[^A-Za-z0-9]", "_")
            : "F" + selectCount);
  }

  @Override
  public AqlField<Object>[] fields() {
    return fields;
  }

  public String buildVariabelName(Containment containment) {

    return variablesMap.computeIfAbsent(containment, this::buildVariablesNameIntern);
  }

  private String buildVariablesNameIntern(Containment containment) {
    String name =
        containment.getType().getSimpleName().substring(0, 1).toLowerCase() + variabelCount;
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

  public String buildParameterName() {
    String name = "parm" + parameterCount;
    parameterCount++;
    return name;
  }

  public <V> Parameter<V> buildParameter() {
    return new Parameter<>(this);
  }
}
