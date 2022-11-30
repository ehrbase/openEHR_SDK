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
package org.ehrbase.aql.binder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.condition.*;
import org.ehrbase.aql.dto.select.SelectStatementDto;
import org.ehrbase.client.aql.condition.Condition;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.Parameter;
import org.ehrbase.util.exception.SdkException;

public class WhereBinder {
    private final SelectBinder selectBinder = new SelectBinder();

    public Pair<Condition, List<ParameterValue>> bind(ConditionDto dto, Map<Integer, Containment> containmentMap) {
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
        } else if (dto instanceof MatchesOperatorDto) {
            Object[] value = ((MatchesOperatorDto) dto)
                    .getValues().stream()
                            .filter(f -> f.getClass().equals(SimpleValue.class))
                            .map(v -> (SimpleValue) v)
                            .map(SimpleValue::getValue)
                            .toArray();
            if (value.length != ((MatchesOperatorDto) dto).getValues().size()) {
                throw new SdkException("Only simple values are supported Matches");
            }
            condition = Condition.matches(
                    selectBinder.bind(((MatchesOperatorDto) dto).getStatement(), containmentMap), value);

        } else if (dto instanceof ExistsConditionOperatorDto) {
            condition =
                    Condition.exists(selectBinder.bind(((ExistsConditionOperatorDto) dto).getValue(), containmentMap));
        } else if (dto instanceof NotConditionOperatorDto) {
            condition = Condition.not(bind(((NotConditionOperatorDto) dto).getConditionDto(), containmentMap)
                    .getLeft());
        } else if (dto instanceof LikeOperatorDto) {
            Pair<Condition, List<ParameterValue>> pair = handleLikeOperator((LikeOperatorDto) dto, containmentMap);
            condition = pair.getLeft();
            parameterList.addAll(pair.getRight());
        } else {
            throw new SdkException(
                    String.format("Unexpected class: %s", dto.getClass().getSimpleName()));
        }

        return new ImmutablePair<>(condition, parameterList);
    }

    private Pair<Condition, List<ParameterValue>> handleConditionLogicalOperator(
            ConditionLogicalOperatorDto dto, Map<Integer, Containment> containmentMap) {
        Pair<Condition, List<ParameterValue>> pair = bind(dto.getValues().get(0), containmentMap);
        Condition condition = pair.getLeft();
        List<ParameterValue> parameterList = pair.getRight();
        Pair<Condition, List<ParameterValue>> subPair = pair;
        for (int i = 1; i < dto.getValues().size(); i++) {
            subPair = buildLogicalOperator(
                    dto.getSymbol(), subPair, bind(dto.getValues().get(i), containmentMap));
            condition = subPair.getLeft();
            parameterList = subPair.getRight();
        }

        return new ImmutablePair<>(condition, parameterList);
    }

    public Pair<Condition, List<ParameterValue>> handleComparisonOperator(
            ConditionComparisonOperatorDto dto, Map<Integer, Containment> containmentMap) {

        return handleComparisonOperator(
                dto.getValue(),
                dto.getSymbol().getJavaName(),
                dto.getStatement(),
                dto.getClass().getSimpleName(),
                containmentMap);
    }

    public Pair<Condition, List<ParameterValue>> handleLikeOperator(
            LikeOperatorDto dto, Map<Integer, Containment> containmentMap) {

        return handleComparisonOperator(
                dto.getValue(),
                dto.getSymbol().getJavaName(),
                dto.getStatement(),
                dto.getClass().getSimpleName(),
                containmentMap);
    }

    public Pair<Condition, List<ParameterValue>> handleComparisonOperator(
            Object value,
            String symbolJavaName,
            SelectStatementDto statement,
            String dtoClassName,
            Map<Integer, Containment> containmentMap) {

        Condition condition;
        final Class<?> valueClass;
        final Object conditionValue;
        List<ParameterValue> parameterList = new ArrayList<>();
        if (value instanceof SimpleValue) {
            valueClass = Object.class;
            conditionValue = ((SimpleValue) value).getValue();
        } else if (value instanceof ParameterValue) {
            valueClass = Parameter.class;
            conditionValue = new Parameter<>(((ParameterValue) value).getName());
            parameterList.add(((ParameterValue) value));
        } else {
            throw new SdkException(String.format("Unexpected class %s", dtoClassName));
        }
        Method method;
        try {
            method = Condition.class.getMethod(symbolJavaName, SelectAqlField.class, valueClass);
        } catch (NoSuchMethodException e) {
            throw new SdkException(e.getMessage(), e);
        }
        try {
            condition = (Condition) method.invoke(null, selectBinder.bind(statement, containmentMap), conditionValue);
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
