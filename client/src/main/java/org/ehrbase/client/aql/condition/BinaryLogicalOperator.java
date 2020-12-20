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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BinaryLogicalOperator implements Condition {

  protected final List<Condition> conditionList = new ArrayList<>();

  BinaryLogicalOperator(Condition condition1, Condition condition2) {
    conditionList.add(condition1);
    conditionList.add(condition2);
  }

  @Override
  public String buildAql() {

    return "("
        + conditionList.stream()
            .map(Condition::buildAql)
            .collect(Collectors.joining(" " + getSymbol() + " "))
        + ")";
  }

  protected abstract String getSymbol();
}
