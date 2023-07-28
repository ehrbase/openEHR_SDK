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
package org.ehrbase.openehr.sdk.aql.dto;

import java.util.List;
import java.util.Objects;
import org.ehrbase.openehr.sdk.aql.dto.condition.WhereCondition;
import org.ehrbase.openehr.sdk.aql.dto.containment.Containment;
import org.ehrbase.openehr.sdk.aql.dto.orderby.OrderByExpression;
import org.ehrbase.openehr.sdk.aql.dto.select.SelectClause;
import org.ehrbase.openehr.sdk.aql.parser.AqlQueryParser;
import org.ehrbase.openehr.sdk.aql.render.AqlRenderer;

public class AqlQuery {

    private SelectClause select;

    private Containment from;
    private WhereCondition where;
    private List<OrderByExpression> orderBy;
    private Long limit;
    private Long offset;

    public SelectClause getSelect() {
        return this.select;
    }

    public Containment getFrom() {
        return this.from;
    }

    public WhereCondition getWhere() {
        return this.where;
    }

    public List<OrderByExpression> getOrderBy() {
        return this.orderBy;
    }

    public void setSelect(SelectClause select) {
        this.select = select;
    }

    public void setFrom(Containment from) {
        this.from = from;
    }

    public void setWhere(WhereCondition where) {
        this.where = where;
    }

    public void setOrderBy(List<OrderByExpression> orderBy) {
        this.orderBy = orderBy;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public Long getOffset() {
        return offset;
    }

    public void setOffset(Long offset) {
        this.offset = offset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AqlQuery aqlQuery = (AqlQuery) o;
        return Objects.equals(select, aqlQuery.select)
                && Objects.equals(from, aqlQuery.from)
                && Objects.equals(where, aqlQuery.where)
                && Objects.equals(orderBy, aqlQuery.orderBy)
                && Objects.equals(limit, aqlQuery.limit)
                && Objects.equals(offset, aqlQuery.offset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(select, from, where, orderBy, limit, offset);
    }

    @Override
    public String toString() {
        return "AqlQuery{"
                + "select="
                + select
                + ", from="
                + from
                + ", where="
                + where
                + ", orderBy="
                + orderBy
                + ", limit="
                + limit
                + ", offset="
                + offset
                + '}';
    }

    public static AqlQuery parse(String aqlStr) {
        return AqlQueryParser.parse(aqlStr);
    }

    public String render() {
        return AqlRenderer.render(this);
    }
}
