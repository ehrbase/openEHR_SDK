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

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.PartyIdentifiedStdConfig;

public class EventContextMarshalPostprocessor implements MarshalPostprocessor<EventContext> {

  private static final PartyIdentifiedStdConfig PARTY_IDENTIFIED_STD_CONFIG =
      new PartyIdentifiedStdConfig();

  /** {@inheritDoc} */
  @Override
  public void process(String term, EventContext rmObject, Map<String, Object> values) {

    MarshalPostprocessor.addValue(
        values,
        term + PATH_DIVIDER + "_end_time",
        null,
        Optional.ofNullable(rmObject.getEndTime()).map(DvDateTime::getValue).orElse(null));

    MarshalPostprocessor.addValue(
            values,
            term + PATH_DIVIDER + "_location",
            null,
            Optional.ofNullable(rmObject.getLocation()).orElse(null));

    if (rmObject.getHealthCareFacility() != null) {
      values.putAll(
          PARTY_IDENTIFIED_STD_CONFIG.buildChildValues(
              term + "/" + "_health_care_facility", rmObject.getHealthCareFacility(), null));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<EventContext> getAssociatedClass() {
    return EventContext.class;
  }
}
