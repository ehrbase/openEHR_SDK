/*
 *
 *  *  Copyright (c) 2021  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
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

package org.ehrbase.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.RMHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.model.WebTemplateInput;
import org.ehrbase.webtemplate.model.WebTemplateInterval;
import org.ehrbase.webtemplate.model.WebTemplateNode;
import org.ehrbase.webtemplate.model.WebTemplateValidation;
import org.threeten.extra.PeriodDuration;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObservationValueInserter extends AbstractValueInserter<Observation> {

  public void insert(Observation rmObject, DefaultValues defaultValues, WebTemplateNode node) {

    if (rmObject.getData() != null) {
      insert(
          rmObject.getData(),
          defaultValues,
          node
              .findMatching(
                  n -> Objects.equals(n.getNodeId(), rmObject.getData().getArchetypeNodeId()))
              .stream()
              .findFirst()
              .orElse(FlatHelper.buildDummyChild("history", node)));
    }

    if (rmObject.getState() != null) {
      insert(
          rmObject.getState(),
          defaultValues,
          node
              .findMatching(
                  n -> Objects.equals(n.getNodeId(), rmObject.getState().getArchetypeNodeId()))
              .stream()
              .findFirst()
              .orElse(FlatHelper.buildDummyChild("history", node)));
    }
  }

  private void insert(History<?> rmObject, DefaultValues defaultValues, WebTemplateNode node) {

    if (RMHelper.isEmpty(rmObject.getOrigin())
        && (defaultValues.containsDefaultValue(DefaultValuePath.TIME)
            || defaultValues.containsDefaultValue(DefaultValuePath.HISTORY_ORIGIN))) {
      TemporalAccessor defaultTemporalAccessor =
          Stream.of(DefaultValuePath.HISTORY_ORIGIN, DefaultValuePath.TIME)
              .map(defaultValues::getDefaultValue)
              .filter(Objects::nonNull)
              .findFirst()
              .orElseThrow();
      rmObject.setOrigin(new DvDateTime(defaultTemporalAccessor));
    }

    if (rmObject.getEvents() != null && rmObject.getOrigin() != null) {

      rmObject
          .getEvents()
          .forEach(
              e ->
                  insert(
                      e,
                      rmObject.getOrigin().getValue(),
                      node
                          .findMatching(n -> Objects.equals(n.getNodeId(), e.getArchetypeNodeId()))
                          .stream()
                          .findFirst()
                          .orElse(FlatHelper.buildDummyChild("event", node))));
    }
  }

  private void insert(Event<?> rmObject, TemporalAccessor origin, WebTemplateNode node) {
    if (RMHelper.isEmpty(rmObject.getTime())) {

      TemporalAccessor defaultValue = origin;
      if (defaultValue instanceof Temporal) {
        defaultValue =
            ((Temporal) defaultValue).plus(buildOffset(node.findChildById("offset").orElse(null)));
      }
      rmObject.setTime(new DvDateTime(defaultValue));
    }
  }

  private TemporalAmount buildOffset(WebTemplateNode offsetNode) {

    PeriodDuration offset = PeriodDuration.ZERO;

    List<TemporalAmount> temporalAmounts =
        Optional.ofNullable(offsetNode).map(WebTemplateNode::getInputs).stream()
            .flatMap(List::stream)
            .filter(
                i ->
                    Optional.of(i)
                        .map(WebTemplateInput::getValidation)
                        .map(WebTemplateValidation::getRange)
                        .map(WebTemplateInterval::getMin)
                        .map(Object::getClass)
                        .filter(Integer.class::isAssignableFrom)
                        .isPresent())
            .map(this::build)
            .collect(Collectors.toList());

    for (TemporalAmount amount : temporalAmounts) {

      offset = offset.plus(amount);
    }
    return offset;
  }

  private TemporalAmount build(WebTemplateInput input) {

    String suffix = input.getSuffix();
    WebTemplateInterval<Integer> validation = input.getValidation().getRange();

    switch (suffix) {
      case "year":
        return Period.ofYears(validation.getMin());
      case "month":
        return Period.ofMonths(validation.getMin());
      case "day":
        return Period.ofDays(validation.getMin());
      case "week":
        return Period.ofWeeks(validation.getMin());
      case "hour":
        return Duration.ofHours(validation.getMin());
      case "minute":
        return Duration.ofMinutes(validation.getMin());
      case "second":
        return Duration.ofSeconds(validation.getMin());
      default:
        throw new IllegalArgumentException("Unsupported suffix: " + suffix);
    }

  }

  @Override
  public Class<Observation> getAssociatedClass() {
    return Observation.class;
  }
}
