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

package org.ehrbase.aql.dto.condition;

import org.ehrbase.aql.dto.select.SelectStatementDto;

public class ConditionComparisonOperatorDto implements ConditionDto {

  private SelectStatementDto statement;
  private ConditionComparisonOperatorSymbol symbol;
  private Value value;

  public ConditionComparisonOperatorDto() {}

  public SelectStatementDto getStatement() {
    return this.statement;
  }

  public ConditionComparisonOperatorSymbol getSymbol() {
    return this.symbol;
  }

  public Value getValue() {
    return this.value;
  }

  public void setStatement(SelectStatementDto statement) {
    this.statement = statement;
  }

  public void setSymbol(ConditionComparisonOperatorSymbol symbol) {
    this.symbol = symbol;
  }

  public void setValue(Value value) {
    this.value = value;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof ConditionComparisonOperatorDto)) return false;
    final ConditionComparisonOperatorDto other = (ConditionComparisonOperatorDto) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$statement = this.getStatement();
    final Object other$statement = other.getStatement();
    if (this$statement == null ? other$statement != null : !this$statement.equals(other$statement))
      return false;
    final Object this$symbol = this.getSymbol();
    final Object other$symbol = other.getSymbol();
    if (this$symbol == null ? other$symbol != null : !this$symbol.equals(other$symbol))
      return false;
    final Object this$value = this.getValue();
    final Object other$value = other.getValue();
    if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ConditionComparisonOperatorDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $statement = this.getStatement();
    result = result * PRIME + ($statement == null ? 43 : $statement.hashCode());
    final Object $symbol = this.getSymbol();
    result = result * PRIME + ($symbol == null ? 43 : $symbol.hashCode());
    final Object $value = this.getValue();
    result = result * PRIME + ($value == null ? 43 : $value.hashCode());
    return result;
  }

  public String toString() {
    return "ConditionComparisonOperatorDto(statement="
        + this.getStatement()
        + ", symbol="
        + this.getSymbol()
        + ", value="
        + this.getValue()
        + ")";
  }
}
