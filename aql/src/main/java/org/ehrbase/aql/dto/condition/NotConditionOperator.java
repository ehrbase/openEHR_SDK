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
package org.ehrbase.aql.dto.condition;

import java.util.Objects;

/**
 * @author Stefan Spiska
 */
public class NotConditionOperator implements ConditionDto {

    private ConditionDto conditionDto;

    public NotConditionOperator() {}

    public NotConditionOperator(ConditionDto conditionDto) {
        this.conditionDto = conditionDto;
    }

    public ConditionDto getConditionDto() {
        return conditionDto;
    }

    public void setConditionDto(ConditionDto conditionDto) {
        this.conditionDto = conditionDto;
    }

    @Override
    public String toString() {
        return "NotConditionOperator{" + "conditionDto=" + conditionDto + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NotConditionOperator that = (NotConditionOperator) o;
        return Objects.equals(conditionDto, that.conditionDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionDto);
    }
}
