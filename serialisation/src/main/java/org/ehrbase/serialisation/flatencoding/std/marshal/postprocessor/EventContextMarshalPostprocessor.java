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

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.ParticipationConfig;
import org.ehrbase.serialisation.flatencoding.std.marshal.config.PartyIdentifiedStdConfig;
import org.ehrbase.serialisation.walker.Context;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class EventContextMarshalPostprocessor implements MarshalPostprocessor<EventContext> {

  private static final PartyIdentifiedStdConfig PARTY_IDENTIFIED_STD_CONFIG =
      new PartyIdentifiedStdConfig();

  private static final ParticipationConfig PARTICIPATION_CONFIG = new ParticipationConfig();

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      EventContext rmObject,
      Map<String, Object> values,
      Context<Map<String, Object>> context) {

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

    if (rmObject.getParticipations() != null) {
      IntStream.range(0, rmObject.getParticipations().size())
          .forEach(
              i ->
                  values.putAll(
                      PARTICIPATION_CONFIG.buildChildValues(
                          term + PATH_DIVIDER + "_participation:" + i,
                          rmObject.getParticipations().get(i),
                          null)));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<EventContext> getAssociatedClass() {
    return EventContext.class;
  }
}
