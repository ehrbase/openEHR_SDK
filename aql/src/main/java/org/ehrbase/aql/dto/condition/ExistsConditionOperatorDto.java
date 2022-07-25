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
import org.ehrbase.aql.dto.select.SelectFieldDto;

/**
 * @author Stefan Spiska
 */
public class ExistsConditionOperatorDto implements ConditionDto {

    private SelectFieldDto value;

    public ExistsConditionOperatorDto() {}

    public ExistsConditionOperatorDto(SelectFieldDto value) {
        this.value = value;
    }

    public SelectFieldDto getValue() {
        return value;
    }

    public void setValue(SelectFieldDto value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExistsConditionOperator{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExistsConditionOperatorDto that = (ExistsConditionOperatorDto) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
