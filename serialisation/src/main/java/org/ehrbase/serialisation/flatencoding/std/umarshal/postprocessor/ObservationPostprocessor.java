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

package org.ehrbase.serialisation.flatencoding.std.umarshal.postprocessor;

import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ObservationPostprocessor extends AbstractUnmarshalPostprocessor<Observation> {

  /** {@inheritDoc} */
  @Override
  public void process(String term, Observation rmObject, Map<String, String> values) {
    if (rmObject.getData() != null && rmObject.getData().getOrigin().getValue() == null) {
      setOrigin(rmObject.getData());
    }
    if (rmObject.getState() != null && rmObject.getState().getOrigin().getValue() == null) {
      setOrigin(rmObject.getState());
    }
  }

  public void setOrigin(History<ItemStructure> history) {
    Optional<TemporalAccessor> first =
        history.getEvents().stream()
            .map(Event::getTime)
            .map(DvDateTime::getValue)
            .filter(Objects::nonNull)
            .sorted()
            .findFirst();
    first.ifPresent(temporalAccessor -> history.setOrigin(new DvDateTime(temporalAccessor)));
  }

  /** {@inheritDoc} */
  @Override
  public Class<Observation> getAssociatedClass() {
    return Observation.class;
  }
}
