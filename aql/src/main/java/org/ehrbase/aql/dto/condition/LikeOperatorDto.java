/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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

import org.ehrbase.aql.dto.select.SelectStatementDto;

public class LikeOperatorDto implements ConditionDto {

    public static final String SYMBOL = "like";

    private SelectStatementDto statement;

    private Value value;

    public SelectStatementDto getStatement() {
        return this.statement;
    }

    public Value getValue() {
        return this.value;
    }

    public void setStatement(SelectStatementDto statement) {
        this.statement = statement;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof LikeOperatorDto)) return false;
        final LikeOperatorDto other = (LikeOperatorDto) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$statement = this.getStatement();
        final Object other$statement = other.getStatement();
        if (this$statement == null ? other$statement != null : !this$statement.equals(other$statement)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof LikeOperatorDto;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $statement = this.getStatement();
        result = result * PRIME + ($statement == null ? 43 : $statement.hashCode());
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        return result;
    }

    public String toString() {
        return "LikeOperatorDto(statement=" + this.getStatement() + ", value=" + this.getValue() + ")";
    }
}
