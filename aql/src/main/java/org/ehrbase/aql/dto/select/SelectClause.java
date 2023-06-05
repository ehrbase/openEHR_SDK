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
package org.ehrbase.aql.dto.select;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import org.ehrbase.client.aql.top.Direction;

public class SelectClause {

    private Integer topCount;
    private Direction topDirection;

    private boolean isDistinct = false;

    private List<SelectExpression> statement;

    public Integer getTopCount() {
        return this.topCount;
    }

    public Direction getTopDirection() {
        return this.topDirection;
    }

    public List<SelectExpression> getStatement() {
        return this.statement;
    }

    public void setTopCount(Integer topCount) {
        this.topCount = topCount;
    }

    public void setTopDirection(Direction topDirection) {
        this.topDirection = topDirection;
    }

    public void setStatement(List<SelectExpression> statement) {
        this.statement = statement;
    }

    @JsonProperty(index = 10)
    public boolean isDistinct() {
        return isDistinct;
    }

    public void setDistinct(boolean distinct) {
        isDistinct = distinct;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SelectClause)) return false;
        final SelectClause other = (SelectClause) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$topCount = this.getTopCount();
        final Object other$topCount = other.getTopCount();
        if (this$topCount == null ? other$topCount != null : !this$topCount.equals(other$topCount)) return false;
        final Object this$topDirection = this.getTopDirection();
        final Object other$topDirection = other.getTopDirection();
        if (this$topDirection == null ? other$topDirection != null : !this$topDirection.equals(other$topDirection))
            return false;
        final Object this$statement = this.getStatement();
        final Object other$statement = other.getStatement();
        if (this$statement == null ? other$statement != null : !this$statement.equals(other$statement)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SelectClause;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $topCount = this.getTopCount();
        result = result * PRIME + ($topCount == null ? 43 : $topCount.hashCode());
        final Object $topDirection = this.getTopDirection();
        result = result * PRIME + ($topDirection == null ? 43 : $topDirection.hashCode());
        final Object $statement = this.getStatement();
        result = result * PRIME + ($statement == null ? 43 : $statement.hashCode());
        return result;
    }

    public String toString() {
        return "SelectClause(topCount="
                + this.getTopCount()
                + ", topDirection="
                + this.getTopDirection()
                + ", statement="
                + this.getStatement()
                + ")";
    }
}
