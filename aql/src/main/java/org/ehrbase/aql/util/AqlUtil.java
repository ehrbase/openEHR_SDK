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
package org.ehrbase.aql.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.ComparisonOperatorCondition;
import org.ehrbase.aql.dto.condition.LogicalOperatorCondition;
import org.ehrbase.aql.dto.condition.WhereCondition;
import org.ehrbase.aql.dto.operand.QueryParameter;
import org.ehrbase.aql.dto.operand.Terminal;
import org.ehrbase.aql.parser.AqlToDtoParser;
import org.ehrbase.aql.render.AqlRender;

public class AqlUtil {

    private AqlUtil() {}

    private static final AqlToDtoParser PARSER = new AqlToDtoParser();

    /**
     * Removes a parameter from the aql
     *
     * @param aql source aql string
     * @param parameterName name of the parameter to be removed
     * @return the aql with the parameter removed
     */
    public static String removeParameter(String aql, String parameterName) {

        AqlDto dto = PARSER.parse(aql);

        dto.setWhere(removeParameter(dto.getWhere(), parameterName));
        return new AqlRender(dto).render();
    }

    private static WhereCondition removeParameter(WhereCondition condition, String parameterName) {
        if (condition instanceof ComparisonOperatorCondition) {
            Terminal value = ((ComparisonOperatorCondition) condition).getValue();
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
}
