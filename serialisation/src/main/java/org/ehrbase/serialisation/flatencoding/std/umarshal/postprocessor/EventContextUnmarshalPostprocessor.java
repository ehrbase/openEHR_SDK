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

import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller.PartyIdentifiedRMUnmarshaller;

public class EventContextUnmarshalPostprocessor
    extends AbstractUnmarshalPostprocessor<EventContext> {

  private static final PartyIdentifiedRMUnmarshaller PARTY_IDENTIFIED_RM_UNMARSHALLER =
      new PartyIdentifiedRMUnmarshaller();

  /** {@inheritDoc} */
  @Override
  public void process(String term, EventContext rmObject, Map<String, String> values) {
    setValue(
        term + PATH_DIVIDER + "_end_time",
        null,
        values,
        s -> {
          if (s != null) {
            rmObject.setEndTime(new DvDateTime(s));
          }
        },
        String.class);

    Map<String, String> health_care_facilityValues =
        values.entrySet().stream()
            .filter(
                e ->
                    StringUtils.substringAfterLast(e.getKey(), "/")
                        .contains("_health_care_facility"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    if (!health_care_facilityValues.isEmpty()) {
      rmObject.setHealthCareFacility(new PartyIdentified());

      PARTY_IDENTIFIED_RM_UNMARSHALLER.handle(
          term + "/" + "_health_care_facility",
          rmObject.getHealthCareFacility(),
          health_care_facilityValues,
          null);
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<EventContext> getAssociatedClass() {
    return EventContext.class;
  }
}
