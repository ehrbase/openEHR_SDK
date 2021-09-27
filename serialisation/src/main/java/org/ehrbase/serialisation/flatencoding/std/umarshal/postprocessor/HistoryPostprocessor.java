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

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.time.temporal.TemporalAccessor;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class HistoryPostprocessor extends AbstractUnmarshalPostprocessor<History> {

  /** {@inheritDoc} */
  @Override
  public void process(String term, History rmObject, Map<FlatPathDto, String> values, Set<String> consumedPaths) {

    setValue(
        term + PATH_DIVIDER + "history_origin",
        null,
        values,
        s -> {
          if (s != null) {
            rmObject.setOrigin(new DvDateTime(s));
          }
        },
        String.class, consumedPaths );

    if (rmObject.getOrigin() == null || rmObject.getOrigin().getValue() == null) {
      Optional<TemporalAccessor> first =
          ((History<ItemStructure>) rmObject)
              .getEvents().stream()
                  .map(Event::getTime)
                  .filter(Objects::nonNull)
                  .map(DvDateTime::getValue)
                  .filter(Objects::nonNull)
                  .sorted()
                  .findFirst();
      first.ifPresent(
          temporalAccessor ->
              ((History<ItemStructure>) rmObject).setOrigin(new DvDateTime(temporalAccessor)));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<History> getAssociatedClass() {
    return History.class;
  }
}
