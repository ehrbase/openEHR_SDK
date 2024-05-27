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
package org.ehrbase.openehr.sdk.aql.dto.condition;

import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;
import org.ehrbase.openehr.sdk.aql.dto.operand.LikeOperand;

public final class LikeCondition implements WhereCondition {

    private IdentifiedPath statement;

    private LikeOperand value;

    public IdentifiedPath getStatement() {
        return this.statement;
    }

    public LikeOperand getValue() {
        return this.value;
    }

    public void setStatement(IdentifiedPath statement) {
        this.statement = statement;
    }

    public void setValue(LikeOperand value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeCondition that = (LikeCondition) o;
        return Objects.equals(statement, that.statement) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, value);
    }

    @Override
    public String toString() {
        return "LikeCondition{" + "statement=" + statement + ", value=" + value + '}';
    }
}
