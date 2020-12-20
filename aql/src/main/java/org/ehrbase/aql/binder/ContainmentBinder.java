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

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ehrbase.aql.dto.containment.ContainmentDto;
import org.ehrbase.aql.dto.containment.ContainmentExpresionDto;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperator;
import org.ehrbase.aql.dto.containment.ContainmentLogicalOperatorSymbol;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.containment.ContainmentExpression;
import org.ehrbase.util.exception.SdkException;

public class ContainmentBinder {

  public Pair<ContainmentExpression, Map<Integer, Containment>> buildContainment(
      ContainmentExpresionDto dto) {
    MutablePair<ContainmentExpression, Map<Integer, Containment>> result = new MutablePair<>();

    if (dto instanceof ContainmentDto) {
      handleContainmentDto((ContainmentDto) dto, result);
    } else if (dto instanceof ContainmentLogicalOperator) {
      handleContainmentLogicalOperator((ContainmentLogicalOperator) dto, result);
    } else {
      throw new SdkException("Unknown Type" + dto.getClass().getSimpleName());
    }

    return result;
  }

  public void handleContainmentLogicalOperator(
      ContainmentLogicalOperator dto,
      MutablePair<ContainmentExpression, Map<Integer, Containment>> result) {
    ContainmentExpression containmentExpression;
    Map<Integer, Containment> containmentMap;
    Pair<ContainmentExpression, Map<Integer, Containment>> pair =
        buildLogicalOperator(
            dto.getSymbol(),
            buildContainment(dto.getValues().get(0)),
            buildContainment(dto.getValues().get(1)));
    containmentExpression = pair.getLeft();
    containmentMap = pair.getRight();
    for (int i = 2; i < dto.getValues().size(); i++) {
      Pair<ContainmentExpression, Map<Integer, Containment>> subPair =
          buildLogicalOperator(dto.getSymbol(), pair, buildContainment(dto.getValues().get(i)));
      containmentExpression = subPair.getLeft();
      containmentMap.putAll(subPair.getRight());
    }
    result.setLeft(containmentExpression);
    result.setRight(containmentMap);
  }

  public void handleContainmentDto(
      ContainmentDto dto, MutablePair<ContainmentExpression, Map<Integer, Containment>> result) {
    ContainmentExpression containmentExpression;
    Map<Integer, Containment> containmentMap = new HashMap<>();
    ContainmentExpression containmentExpression1;
    Containment containment = new Containment(dto.getArchetypeId());
    containmentExpression1 = containment;
    containmentMap.put(dto.getId(), containment);
    if (dto.getContains() != null) {
      Pair<ContainmentExpression, Map<Integer, Containment>> pair =
          buildContainment(dto.getContains());
      containment.setContains(pair.getLeft());
      containmentMap.putAll(pair.getRight());
    }
    containmentExpression = containmentExpression1;
    result.setLeft(containmentExpression);
    result.setRight(containmentMap);
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
        throw new SdkException(String.format("Unknown Symbol %s", symbol));
    }
    pair1.getRight().putAll(pair2.getRight());
    return new ImmutablePair<>(containmentExpression, pair1.getRight());
  }
}
