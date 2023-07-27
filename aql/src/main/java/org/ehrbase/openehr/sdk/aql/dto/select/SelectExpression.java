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
package org.ehrbase.openehr.sdk.aql.dto.select;

import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.operand.ColumnExpression;

public class SelectExpression {

    private String alias;
    private ColumnExpression columnExpression;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ColumnExpression getColumnExpression() {
        return columnExpression;
    }

    public void setColumnExpression(ColumnExpression columnExpression) {
        this.columnExpression = columnExpression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectExpression that = (SelectExpression) o;
        return Objects.equals(alias, that.alias) && Objects.equals(columnExpression, that.columnExpression);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, columnExpression);
    }

    @Override
    public String toString() {
        return "SelectExpression{" + "alias='" + alias + '\'' + ", columnExpression=" + columnExpression + '}';
    }
}
