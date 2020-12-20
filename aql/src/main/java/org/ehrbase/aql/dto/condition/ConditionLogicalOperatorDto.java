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

import java.util.List;

public class ConditionLogicalOperatorDto implements ConditionDto {

  private ConditionLogicalOperatorSymbol symbol;
  private List<ConditionDto> values;

  public ConditionLogicalOperatorDto() {}

  public ConditionLogicalOperatorSymbol getSymbol() {
    return this.symbol;
  }

  public List<ConditionDto> getValues() {
    return this.values;
  }

  public void setSymbol(ConditionLogicalOperatorSymbol symbol) {
    this.symbol = symbol;
  }

  public void setValues(List<ConditionDto> values) {
    this.values = values;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof ConditionLogicalOperatorDto)) return false;
    final ConditionLogicalOperatorDto other = (ConditionLogicalOperatorDto) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$symbol = this.getSymbol();
    final Object other$symbol = other.getSymbol();
    if (this$symbol == null ? other$symbol != null : !this$symbol.equals(other$symbol))
      return false;
    final Object this$values = this.getValues();
    final Object other$values = other.getValues();
    if (this$values == null ? other$values != null : !this$values.equals(other$values))
      return false;
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ConditionLogicalOperatorDto;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $symbol = this.getSymbol();
    result = result * PRIME + ($symbol == null ? 43 : $symbol.hashCode());
    final Object $values = this.getValues();
    result = result * PRIME + ($values == null ? 43 : $values.hashCode());
    return result;
  }

  public String toString() {
    return "ConditionLogicalOperatorDto(symbol="
        + this.getSymbol()
        + ", values="
        + this.getValues()
        + ")";
  }
}
