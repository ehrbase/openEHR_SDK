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

import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class AggregateFunction implements ColumnExpression {

    private AggregateFunctionName functionName;
    private IdentifiedPath identifiedPath;

    public IdentifiedPath getIdentifiedPath() {
        return identifiedPath;
    }

    public void setIdentifiedPath(IdentifiedPath identifiedPath) {
        this.identifiedPath = identifiedPath;
    }

    public void setFunctionName(AggregateFunctionName functionName) {
        this.functionName = functionName;
    }

    public AggregateFunctionName getFunctionName() {
        return functionName;
    }

    public enum AggregateFunctionName {
        COUNT,
        MIN,
        MAX,
        AVG
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AggregateFunction that = (AggregateFunction) o;
        return functionName == that.functionName && Objects.equals(identifiedPath, that.identifiedPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(functionName, identifiedPath);
    }

    @Override
    public String toString() {
        return "AggregateFunction{" + "functionName=" + functionName + ", identifiedPath=" + identifiedPath + '}';
    }
}
