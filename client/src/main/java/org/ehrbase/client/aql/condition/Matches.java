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

import java.util.Arrays;
import java.util.stream.Collectors;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.aql.parameter.AqlValue;
import org.ehrbase.client.aql.parameter.Parameter;

public class Matches<T> implements Condition {

  private final SelectAqlField<T> field;
  private final AqlValue[] values;
  private final Parameter<T>[] parameters;

  Matches(SelectAqlField<T> field, T... values) {
    this.field = field;
    this.values = Arrays.stream(values).map(AqlValue::new).toArray(AqlValue[]::new);
    this.parameters = null;
  }

  Matches(SelectAqlField<T> field, Parameter<T>... parameters) {
    this.field = field;
    this.values = null;
    this.parameters = parameters;
  }

  @Override
  public String buildAql() {
    StringBuilder sb = new StringBuilder();
    sb.append(field.buildAQL()).append(" matches {");

    if (values != null) {
      sb.append(Arrays.stream(values).map(AqlValue::buildAql).collect(Collectors.joining(",")));
    } else {
      sb.append(
          Arrays.stream(parameters)
              .map(Parameter::getAqlParameter)
              .collect(Collectors.joining(",")));
    }
    sb.append("}");

    return sb.toString();
  }
}
