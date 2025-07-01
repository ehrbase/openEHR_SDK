/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.aql.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.*;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.openehr.sdk.aql.dto.operand.LikeOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.MatchesOperand;
import org.ehrbase.openehr.sdk.aql.dto.operand.Operand;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.aql.dto.path.AndOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.dto.path.ComparisonOperatorPredicate;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;

public final class AqlUtil {

    private AqlUtil() {}

    /**
     * Removes a parameter from the aql
     *
     * @param aql source aql string
     * @param parameterName name of the parameter to be removed
     * @return the aql with the parameter removed
     */
    public static String removeParameter(String aql, String parameterName) {

        AqlQuery dto = AqlQueryParser.parse(aql);

        dto.setWhere(removeParameter(dto.getWhere(), parameterName));
        return AqlRenderer.render(dto);
    }

    private static WhereCondition removeParameter(WhereCondition condition, String parameterName) {
        if (condition instanceof ComparisonOperatorCondition comparisonOperatorCondition) {
            Operand value = comparisonOperatorCondition.getValue();
            if (value instanceof QueryParameter queryParameter
                    && Objects.equals(queryParameter.getName(), parameterName)) {
                return null;
            }
        } else if (condition instanceof LogicalOperatorCondition logicalOperatorCondition) {
            List<WhereCondition> values = logicalOperatorCondition.getValues();

            for (WhereCondition value : new ArrayList<>(values)) {
                values.remove(value);

                WhereCondition newValue = removeParameter(value, parameterName);

                if (newValue != null) {
                    values.add(newValue);
                }
            }

            if (values.isEmpty()) {
                return null;
            } else if (values.size() == 1) {
                return values.get(0);
            }
        } else if (condition instanceof NotCondition notCondition) {
            var value = removeParameter(notCondition.getConditionDto(), parameterName);
            if (value != null) {
                notCondition.setConditionDto(value);
            } else {
                return null;
            }
        } else if (condition instanceof MatchesCondition matchesCondition) {
            List<MatchesOperand> values = matchesCondition.getValues();
            values.removeIf(value -> value instanceof QueryParameter queryParameter
                    && Objects.equals(queryParameter.getName(), parameterName));
            if (values.isEmpty()) {
                return null;
            }
        } else if (condition instanceof LikeCondition likeCondition) {
            LikeOperand value = likeCondition.getValue();
            if (value instanceof QueryParameter queryParameter
                    && Objects.equals(queryParameter.getName(), parameterName)) {
                return null;
            }
        } else if (condition instanceof ExistsCondition) {
            // doesn't have parameter
        } else {
            if (condition != null) {
                throw new IllegalStateException("Unexpected condition: " + condition);
            }
        }

        return condition;
    }

    public static Map<String, AbstractContainmentExpression> containmentExpressionsByIdentifier(
            Containment containment) {
        return streamContainments(containment)
                .filter(e1 -> e1.getIdentifier() != null)
                .collect(Collectors.toMap(AbstractContainmentExpression::getIdentifier, e -> e));
    }

    public static Stream<AbstractContainmentExpression> streamContainments(Containment containment) {
        if (containment == null) {
            return Stream.empty();
        } else if (containment instanceof AbstractContainmentExpression containmentExp) {
            return containmentExp.getContains() == null
                    ? Stream.of(containmentExp)
                    : Stream.concat(Stream.of(containmentExp), streamContainments(containmentExp.getContains()));
        } else if (containment instanceof ContainmentSetOperator containmentSetOp) {
            return containmentSetOp.getValues().stream().flatMap(AqlUtil::streamContainments);
        } else if (containment instanceof ContainmentNotOperator containmentNotOp) {
            return streamContainments(containmentNotOp.getContainmentExpression());
        } else {
            throw new IllegalArgumentException(
                    "Unsupported class %s".formatted(containment.getClass().getSimpleName()));
        }
    }

    public static Stream<ComparisonOperatorPredicate> streamPredicates(List<AndOperatorPredicate> condition) {
        if (condition == null) {
            return Stream.empty();
        }
        return condition.stream().map(AndOperatorPredicate::getOperands).flatMap(List::stream);
    }
}
