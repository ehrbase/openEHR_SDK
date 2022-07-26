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
package org.ehrbase.aql.dto.select;

import java.util.List;
import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class FunctionDto implements SelectStatementDto {

    private AQLFunction aqlFunction;

    private List<SelectStatementDto> parameters;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AQLFunction getAqlFunction() {
        return aqlFunction;
    }

    public void setAqlFunction(AQLFunction aqlFunction) {
        this.aqlFunction = aqlFunction;
    }

    public List<SelectStatementDto> getParameters() {
        return parameters;
    }

    public void setParameters(List<SelectStatementDto> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FunctionDto that = (FunctionDto) o;
        return aqlFunction == that.aqlFunction
                && Objects.equals(parameters, that.parameters)
                && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aqlFunction, parameters, name);
    }

    @Override
    public String toString() {
        return "FunctionDto{" + "aqlFunction="
                + aqlFunction + ", parameters="
                + parameters + ", name='"
                + name + '\'' + '}';
    }
}
