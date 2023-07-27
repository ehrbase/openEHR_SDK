/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.aql.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.AqlQuery;
import org.ehrbase.openehr.sdk.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.AbstractContainmentExpression;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentNotOperator;
import org.ehrbase.openehr.sdk.aql.dto.containment.ContainmentSetOperator;
import org.ehrbase.openehr.sdk.aql.dto.operand.Operand;
import org.ehrbase.openehr.sdk.aql.dto.operand.QueryParameter;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;

public class AqlUtil {

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
        if (condition instanceof ComparisonOperatorCondition) {
            Operand value = ((ComparisonOperatorCondition) condition).getValue();
            if (value instanceof QueryParameter && Objects.equals(((QueryParameter) value).getName(), parameterName)) {
                return null;
            }
        } else if (condition instanceof LogicalOperatorCondition) {
            List<WhereCondition> values = ((LogicalOperatorCondition) condition).getValues();

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
            } else {
                return condition;
            }
        }

        return condition;
    }

    public static Map<String, AbstractContainmentExpression> listById(Containment containment) {

        HashMap<String, AbstractContainmentExpression> map = new HashMap<>();
        if (containment instanceof AbstractContainmentExpression abstractContainmentExpression) {
            map.put(abstractContainmentExpression.getIdentifier(), abstractContainmentExpression);
            if (abstractContainmentExpression.getContains() != null) {
                map.putAll(listById(abstractContainmentExpression.getContains()));
            }
        } else if (containment instanceof ContainmentSetOperator containmentSetOperator) {

            containmentSetOperator.getValues().stream().map(AqlUtil::listById).forEach(map::putAll);
        } else if (containment instanceof ContainmentNotOperator containmentNotOperator) {

            map.putAll(listById(containmentNotOperator.getContainmentExpression()));
        } else {
            throw new UnsupportedOperationException(
                    "Unsupported class %s".formatted(containment.getClass().getSimpleName()));
        }
        return map;
    }
}
