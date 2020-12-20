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

  public AqlDto() {}

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

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof AqlDto)) return false;
    final AqlDto other = (AqlDto) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$select = this.getSelect();
    final Object other$select = other.getSelect();
    if (this$select == null ? other$select != null : !this$select.equals(other$select))
      return false;
    final Object this$ehr = this.getEhr();
    final Object other$ehr = other.getEhr();
    if (this$ehr == null ? other$ehr != null : !this$ehr.equals(other$ehr)) return false;
    final Object this$contains = this.getContains();
    final Object other$contains = other.getContains();
    if (this$contains == null ? other$contains != null : !this$contains.equals(other$contains))
      return false;
    final Object this$where = this.getWhere();
    final Object other$where = other.getWhere();
    if (this$where == null ? other$where != null : !this$where.equals(other$where)) return false;
    final Object this$orderBy = this.getOrderBy();
    final Object other$orderBy = other.getOrderBy();
    if (this$orderBy == null ? other$orderBy != null : !this$orderBy.equals(other$orderBy))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof AqlDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $select = this.getSelect();
    result = result * PRIME + ($select == null ? 43 : $select.hashCode());
    final Object $ehr = this.getEhr();
    result = result * PRIME + ($ehr == null ? 43 : $ehr.hashCode());
    final Object $contains = this.getContains();
    result = result * PRIME + ($contains == null ? 43 : $contains.hashCode());
    final Object $where = this.getWhere();
    result = result * PRIME + ($where == null ? 43 : $where.hashCode());
    final Object $orderBy = this.getOrderBy();
    result = result * PRIME + ($orderBy == null ? 43 : $orderBy.hashCode());
    return result;
  }

  public String toString() {
    return "AqlDto(select="
        + this.getSelect()
        + ", ehr="
        + this.getEhr()
        + ", contains="
        + this.getContains()
        + ", where="
        + this.getWhere()
        + ", orderBy="
        + this.getOrderBy()
        + ")";
  }
}
