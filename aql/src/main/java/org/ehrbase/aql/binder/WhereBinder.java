/*
 *
 * Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 * This file is part of Project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.ehrbase.aql.binder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.condition.ConditionComparisonOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorDto;
import org.ehrbase.aql.dto.condition.ConditionLogicalOperatorSymbol;
import org.ehrbase.aql.dto.condition.ParameterValue;
import org.ehrbase.aql.dto.condition.SimpleValue;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.util.exception.SdkException;

public class WhereBinder {
  private final SelectBinder selectBinder = new SelectBinder();

  public Pair<Condition, List<ParameterValue>> bind(
      ConditionDto dto, Map<Integer, Containment> containmentMap) {
    Condition condition;
    List<ParameterValue> parameterList = new ArrayList<>();
    if (dto instanceof ConditionComparisonOperatorDto) {

      Pair<Condition, List<ParameterValue>> pair =
          handleComparisonOperator((ConditionComparisonOperatorDto) dto, containmentMap);
      condition = pair.getLeft();
      parameterList.addAll(pair.getRight());
    } else if (dto instanceof ConditionLogicalOperatorDto) {

      Pair<Condition, List<ParameterValue>> pair =
          handleConditionLogicalOperator((ConditionLogicalOperatorDto) dto, containmentMap);
      condition = pair.getLeft();
      parameterList.addAll(pair.getRight());
    } else {
      throw new SdkException(String.format("Unexpected class: %s", dto.getClass().getSimpleName()));
    }

    return new ImmutablePair<>(condition, parameterList);
  }

  private Pair<Condition, List<ParameterValue>> handleConditionLogicalOperator(
      ConditionLogicalOperatorDto dto, Map<Integer, Containment> containmentMap) {
    Pair<Condition, List<ParameterValue>> pair = bind(dto.getValues().get(0), containmentMap);
    Condition condition = pair.getLeft();
    List<ParameterValue> parameterList = pair.getRight();

    for (int i = 1; i < dto.getValues().size(); i++) {
      Pair<Condition, List<ParameterValue>> subPair =
          buildLogicalOperator(dto.getSymbol(), pair, bind(dto.getValues().get(i), containmentMap));
      condition = subPair.getLeft();
      parameterList = subPair.getRight();
    }

    return new ImmutablePair<>(condition, parameterList);
  }

  public Pair<Condition, List<ParameterValue>> handleComparisonOperator(
      ConditionComparisonOperatorDto dto, Map<Integer, Containment> containmentMap) {

    Condition condition;
    final Class<?> valueClass;
    final Object value;
    List<ParameterValue> parameterList = new ArrayList<>();
    if (dto.getValue() instanceof SimpleValue) {
      valueClass = Object.class;
      value = ((SimpleValue) dto.getValue()).getValue();
    } else if (dto.getValue() instanceof ParameterValue) {
      valueClass = Parameter.class;
      value = new Parameter<>(((ParameterValue) dto.getValue()).getName());
      parameterList.add(((ParameterValue) dto.getValue()));
    } else {
      throw new SdkException(String.format("Unexpected class %s", dto.getClass().getSimpleName()));
    }
    Method method = null;
    try {
      method =
          Condition.class.getMethod(
              dto.getSymbol().getJavaName(), SelectAqlField.class, valueClass);
    } catch (NoSuchMethodException e) {
      throw new SdkException(e.getMessage(), e);
    }
    try {
      condition =
          (Condition)
              method.invoke(null, selectBinder.bind(dto.getStatement(), containmentMap), value);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw new SdkException(e.getMessage(), e);
    }
    return new ImmutablePair<>(condition, parameterList);
  }

  private Pair<Condition, List<ParameterValue>> buildLogicalOperator(
      ConditionLogicalOperatorSymbol symbol,
      Pair<Condition, List<ParameterValue>> pair1,
      Pair<Condition, List<ParameterValue>> pair2) {
    final Condition containmentExpression;
    switch (symbol) {
      case OR:
        containmentExpression = pair1.getLeft().or(pair2.getLeft());
        break;
      case AND:
        containmentExpression = pair1.getLeft().and(pair2.getLeft());
        break;
      default:
        throw new SdkException(String.format("Unknown Symbol %s", symbol));
    }
    pair1.getRight().addAll(pair2.getRight());
    return new ImmutablePair<>(containmentExpression, pair1.getRight());
  }
}
