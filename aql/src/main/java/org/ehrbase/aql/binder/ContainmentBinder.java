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

package org.ehrbase.aql.binder;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperatorSymbol;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;

import java.util.HashMap;
import java.util.Map;

public class ContainmentBinder {

  public Pair<ContainmentExpression, Map<Integer, Containment>> buildContainment(
      ContainmentExpresionDto dto) {
    ContainmentExpression containmentExpression;
    Map<Integer, Containment> containmentMap = new HashMap<>();
    if (dto instanceof ContainmentDto) {
      Containment containment = new Containment(((ContainmentDto) dto).getArchetypeId());
      containmentExpression = containment;
      containmentMap.put(((ContainmentDto) dto).getId(), containment);
      if (((ContainmentDto) dto).getContains() != null) {
        Pair<ContainmentExpression, Map<Integer, Containment>> pair =
            buildContainment(((ContainmentDto) dto).getContains());
        containment.setContains(pair.getLeft());
        containmentMap.putAll(pair.getRight());
      }
    } else if (dto instanceof ContainmentLogicalOperator) {
      Pair<ContainmentExpression, Map<Integer, Containment>> pair =
          buildLogicalOperator(
              ((ContainmentLogicalOperator) dto).getSymbol(),
              buildContainment(((ContainmentLogicalOperator) dto).getValues().get(0)),
              buildContainment(((ContainmentLogicalOperator) dto).getValues().get(1)));
      containmentExpression = pair.getLeft();
      containmentMap = pair.getRight();
      for (int i = 2; i < ((ContainmentLogicalOperator) dto).getValues().size(); i++) {
        Pair<ContainmentExpression, Map<Integer, Containment>> subPair =
            buildLogicalOperator(
                ((ContainmentLogicalOperator) dto).getSymbol(),
                pair,
                buildContainment(((ContainmentLogicalOperator) dto).getValues().get(i)));
        containmentExpression = subPair.getLeft();
        containmentMap.putAll(subPair.getRight());
      }
    } else {
      throw new RuntimeException("Unknown Type" + dto.getClass().getSimpleName());
    }
    return new ImmutablePair<>(containmentExpression, containmentMap);
  }

  private Pair<ContainmentExpression, Map<Integer, Containment>> buildLogicalOperator(
      ContainmentLogicalOperatorSymbol symbol,
      Pair<ContainmentExpression, Map<Integer, Containment>> pair1,
      Pair<ContainmentExpression, Map<Integer, Containment>> pair2) {
    final ContainmentExpression containmentExpression;
    switch (symbol) {
      case OR:
        containmentExpression = pair1.getLeft().or(pair2.getLeft());
        break;
      case AND:
        containmentExpression = pair1.getLeft().and(pair2.getLeft());
        break;
      default:
        throw new RuntimeException();
    }
    pair1.getRight().putAll(pair2.getRight());
    return new ImmutablePair<>(containmentExpression, pair1.getRight());
  }
}
