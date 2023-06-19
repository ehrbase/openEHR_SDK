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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;

/**
 * @author Stefan Spiska
 */
public class ExistsCondition implements WhereCondition {

    private IdentifiedPath value;

    public ExistsCondition() {}

    public ExistsCondition(IdentifiedPath value) {
        this.value = value;
    }

    public IdentifiedPath getValue() {
        return value;
    }

    public void setValue(IdentifiedPath value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExistsCondition{" + "value=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExistsCondition that = (ExistsCondition) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
