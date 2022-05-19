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
import org.ehrbase.aql.binder.AqlBinder;
import org.ehrbase.aql.dto.AqlDto;
import org.ehrbase.aql.dto.condition.*;
import org.ehrbase.aql.parser.AqlToDtoParser;

public class AqlUtil {

    private AqlUtil() {}

    private static final AqlToDtoParser PARSER = new AqlToDtoParser();
    private static final AqlBinder BINDER = new AqlBinder();

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
        return BINDER.bind(dto).getLeft().buildAql();
    }

    private static ConditionDto removeParameter(ConditionDto conditionDto, String parameterName) {
        if (conditionDto instanceof ConditionComparisonOperatorDto) {
            Value value = ((ConditionComparisonOperatorDto) conditionDto).getValue();
            if (value instanceof ParameterValue && Objects.equals(((ParameterValue) value).getName(), parameterName)) {
                return null;
            }
        } else if (conditionDto instanceof ConditionLogicalOperatorDto) {
            List<ConditionDto> values = ((ConditionLogicalOperatorDto) conditionDto).getValues();

            for (ConditionDto value : new ArrayList<>(values)) {
                values.remove(value);

                ConditionDto newValue = removeParameter(value, parameterName);

                if (newValue != null) {
                    values.add(newValue);
                }
            }

            if (values.isEmpty()) {
                return null;
            } else if (values.size() == 1) {
                return values.get(0);
            } else {
                return conditionDto;
            }
        }

        return conditionDto;
    }
}
