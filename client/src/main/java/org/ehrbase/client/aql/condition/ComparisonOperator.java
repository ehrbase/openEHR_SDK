/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.aql.condition;

import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.AqlValue;
import org.ehrbase.client.aql.parameter.Parameter;

public abstract class ComparisonOperator<T> implements Condition {
  protected final SelectAqlField<T> field;
  protected final AqlValue value;
  protected final Parameter<T> parameter;
  protected final SelectAqlField<T> compereField;

  protected ComparisonOperator(SelectAqlField<T> field, T value) {
    this.field = field;
    this.value = new AqlValue(value);
    this.parameter = null;
    this.compereField = null;
  }

  protected ComparisonOperator(SelectAqlField<T> field, Parameter<T> parameter) {
    this.field = field;
    this.parameter = parameter;
    this.value = null;
    this.compereField = null;
  }

  protected ComparisonOperator(SelectAqlField<T> field, SelectAqlField<T> compereField) {
    this.field = field;
    this.value = null;
    this.parameter = null;
    this.compereField = compereField;
  }

  @Override
  public String buildAql() {
    StringBuilder sb = new StringBuilder();
    sb.append(field.buildAQL()).append(" ").append(getSymbol()).append(" ");
    if (value != null) {
      sb.append(value.buildAql());
    } else if (parameter != null) {
      sb.append(parameter.getAqlParameter());
    } else {
      sb.append(compereField.buildAQL());
    }
    return sb.toString();
  }

  protected abstract String getSymbol();
}
