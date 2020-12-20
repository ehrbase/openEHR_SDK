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

package org.ehrbase.aql.dto.orderby;

import org.ehrbase.aql.dto.select.SelectStatementDto;

public class OrderByExpressionDto {

  private SelectStatementDto statement;
  private OrderByExpressionSymbol symbol;

  public OrderByExpressionDto() {}

  public SelectStatementDto getStatement() {
    return this.statement;
  }

  public OrderByExpressionSymbol getSymbol() {
    return this.symbol;
  }

  public void setStatement(SelectStatementDto statement) {
    this.statement = statement;
  }

  public void setSymbol(OrderByExpressionSymbol symbol) {
    this.symbol = symbol;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof OrderByExpressionDto)) return false;
    final OrderByExpressionDto other = (OrderByExpressionDto) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$statement = this.getStatement();
    final Object other$statement = other.getStatement();
    if (this$statement == null ? other$statement != null : !this$statement.equals(other$statement))
      return false;
    final Object this$symbol = this.getSymbol();
    final Object other$symbol = other.getSymbol();
    if (this$symbol == null ? other$symbol != null : !this$symbol.equals(other$symbol))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof OrderByExpressionDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $statement = this.getStatement();
    result = result * PRIME + ($statement == null ? 43 : $statement.hashCode());
    final Object $symbol = this.getSymbol();
    result = result * PRIME + ($symbol == null ? 43 : $symbol.hashCode());
    return result;
  }

  public String toString() {
    return "OrderByExpressionDto(statement="
        + this.getStatement()
        + ", symbol="
        + this.getSymbol()
        + ")";
  }
}
