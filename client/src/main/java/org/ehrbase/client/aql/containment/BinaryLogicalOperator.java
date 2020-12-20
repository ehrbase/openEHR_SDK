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

package org.ehrbase.client.aql.containment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.ehrbase.client.aql.query.EntityQuery;

public abstract class BinaryLogicalOperator implements ContainmentExpression {

  protected final List<ContainmentExpression> containmentExpressionList = new ArrayList<>();

  protected BinaryLogicalOperator(
      ContainmentExpression containmentExpression1, ContainmentExpression containmentExpression2) {
    containmentExpressionList.add(containmentExpression1);
    containmentExpressionList.add(containmentExpression2);
  }

  @Override
  public String buildAQL() {

    return "("
        + containmentExpressionList.stream()
            .map(containmentExpression -> containmentExpression.buildAQL())
            .collect(Collectors.joining(" " + getSymbol() + " "))
        + ")";
  }

  @Override
  public void bindQuery(EntityQuery<?> query) {
    containmentExpressionList.forEach(c -> c.bindQuery(query));
  }

  protected abstract String getSymbol();
}
