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

package org.ehrbase.serialisation.flatencoding.std.marshal.postprocessor;

import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ObservationPostprocessor implements MarshalPostprocessor<Observation> {

  /**
   * {@inheritDoc} Removes the {@link History#getOrigin} if it is equal to fist {@link
   * Event#getTime()}
   */
  @Override
  public void process(String term, Observation rmObject, Map<String, Object> values) {
    if (rmObject.getData() != null
        && rmObject.getData().getOrigin() != null
        && rmObject.getData().getOrigin().getValue() != null) {
      removeOrigin(term, rmObject.getData(), values);
    }
    if (rmObject.getState() != null
        && rmObject.getState().getOrigin() != null
        && rmObject.getState().getOrigin().getValue() != null) {
      removeOrigin(term, rmObject.getState(), values);
    }
  }

  public void removeOrigin(
      String term, History<ItemStructure> history, Map<String, Object> values) {
    Optional<TemporalAccessor> first =
        history.getEvents().stream()
            .map(Event::getTime)
            .filter(Objects::nonNull)
            .map(DvDateTime::getValue)
            .filter(Objects::nonNull)
            .sorted()
            .findFirst();
    first
        .filter(t -> t.equals(history.getOrigin().getValue()))
        .ifPresent(t -> values.remove(term + "/" + "origin"));
  }

  /** {@inheritDoc} */
  @Override
  public Class<Observation> getAssociatedClass() {
    return Observation.class;
  }
}
