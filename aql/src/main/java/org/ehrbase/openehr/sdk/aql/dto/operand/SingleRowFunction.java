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
package org.ehrbase.openehr.sdk.aql.dto.operand;

import java.util.List;

/**
 * @author Stefan Spiska
 */
public class SingleRowFunction implements ColumnExpression, Operand, ComparisonLeftOperand {

    private SingleRowFunctionName functionName;
    private List<Operand> operandList;

    public List<Operand> getOperandList() {
        return operandList;
    }

    public void setOperandList(List<Operand> operandList) {
        this.operandList = operandList;
    }

    public void setFunctionName(SingleRowFunctionName functionName) {
        this.functionName = functionName;
    }

    public SingleRowFunctionName getFunctionName() {
        return functionName;
    }

    public interface SingleRowFunctionName {
        String getName();
    }

    public static class CustomFunctionName implements SingleRowFunctionName {
        private final String name;

        public CustomFunctionName(String name) {
            this.name = name.toUpperCase();
        }

        @Override
        public String getName() {
            return name;
        }
    }

    public enum KnownFunctionName implements SingleRowFunctionName {
        // STRING_FUNCTION_ID
        LENGTH,
        CONTAINS,
        POSITION,
        SUBSTRING,
        CONCAT_WS,
        CONCAT,
        // NUMERIC_FUNCTION_ID
        ABS,
        MOD,
        CEIL,
        FLOOR,
        ROUND,
        // DATE_TIME_FUNCTION_ID
        NOW,
        CURRENT_DATE_TIME,
        CURRENT_DATE,
        CURRENT_TIMEZONE,
        CURRENT_TIME;

        @Override
        public String getName() {
            return name();
        }
    }
}
