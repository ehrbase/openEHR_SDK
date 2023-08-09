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
package org.ehrbase.openehr.sdk.aql.dto.orderby;

import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.IdentifiedPath;

public class OrderByExpression {
    public enum OrderByDirection {
        DESC,
        ASC;
    }

    private IdentifiedPath statement;
    private OrderByDirection symbol;

    public IdentifiedPath getStatement() {
        return this.statement;
    }

    public OrderByDirection getSymbol() {
        return this.symbol;
    }

    public void setStatement(IdentifiedPath statement) {
        this.statement = statement;
    }

    public void setSymbol(OrderByDirection symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderByExpression that = (OrderByExpression) o;
        return Objects.equals(statement, that.statement) && symbol == that.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement, symbol);
    }

    @Override
    public String toString() {
        return "OrderByExpression{" + "statement=" + statement + ", symbol=" + symbol + '}';
    }
}
