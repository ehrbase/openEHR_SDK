/*
 *
 * Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 * This file is part of Project EHRbase
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.ehrbase.aql.dto;

import java.util.List;
import java.util.Objects;
import org.ehrbase.aql.dto.condition.ConditionDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.orderby.OrderByExpressionDto;
import org.ehrbase.aql.dto.select.SelectDto;

public class AqlDto {

  private SelectDto select;
  private EhrDto ehr;
  private ContainmentExpresionDto contains;
  private ConditionDto where;
  private List<OrderByExpressionDto> orderBy;
  private Integer limit;
  private Integer offset;

  public SelectDto getSelect() {
    return this.select;
  }

  public EhrDto getEhr() {
    return this.ehr;
  }

  public ContainmentExpresionDto getContains() {
    return this.contains;
  }

  public ConditionDto getWhere() {
    return this.where;
  }

  public List<OrderByExpressionDto> getOrderBy() {
    return this.orderBy;
  }

  public void setSelect(SelectDto select) {
    this.select = select;
  }

  public void setEhr(EhrDto ehr) {
    this.ehr = ehr;
  }

  public void setContains(ContainmentExpresionDto contains) {
    this.contains = contains;
  }

  public void setWhere(ConditionDto where) {
    this.where = where;
  }

  public void setOrderBy(List<OrderByExpressionDto> orderBy) {
    this.orderBy = orderBy;
  }

  public Integer getLimit() {
    return limit;
  }

  public void setLimit(Integer limit) {
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AqlDto aqlDto = (AqlDto) o;
    return Objects.equals(select, aqlDto.select)
        && Objects.equals(ehr, aqlDto.ehr)
        && Objects.equals(contains, aqlDto.contains)
        && Objects.equals(where, aqlDto.where)
        && Objects.equals(orderBy, aqlDto.orderBy)
        && Objects.equals(limit, aqlDto.limit)
        && Objects.equals(offset, aqlDto.offset);
  }

  @Override
  public int hashCode() {
    return Objects.hash(select, ehr, contains, where, orderBy, limit, offset);
  }

  @Override
  public String toString() {
    return "AqlDto{"
        + "select="
        + select
        + ", ehr="
        + ehr
        + ", contains="
        + contains
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
}
