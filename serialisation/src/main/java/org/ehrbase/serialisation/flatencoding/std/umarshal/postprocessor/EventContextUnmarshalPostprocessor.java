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

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.serialisation.walker.FlatHelper;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.ehrbase.serialisation.walker.FlatHelper.consumeAllMatching;
import static org.ehrbase.serialisation.walker.FlatHelper.extractMultiValued;
import static org.ehrbase.webtemplate.parser.OPTParser.PATH_DIVIDER;

public class EventContextUnmarshalPostprocessor
    extends AbstractUnmarshalPostprocessor<EventContext> {

  /** {@inheritDoc} */
  @Override
  public void process(
      String term,
      EventContext rmObject,
      Map<FlatPathDto, String> values,
      Set<String> consumedPaths,
      Context<Map<FlatPathDto, String>> context) {

    setValue(
        term + PATH_DIVIDER + "_location",
        null,
        values,
        rmObject::setLocation,
        String.class,
        consumedPaths);
    setValue(
        term + PATH_DIVIDER + "_end_time",
        null,
        values,
        s -> {
          if (s != null) {
            rmObject.setEndTime(new DvDateTime(s));
          }
        },
        String.class,
        consumedPaths);

    Map<FlatPathDto, String> health_care_facilityValues =
        FlatHelper.filter(values, term + "/_health_care_facility", false);

    if (!health_care_facilityValues.isEmpty()) {
      rmObject.setHealthCareFacility(new PartyIdentified());

      handleRmAttribute(
          term,
          rmObject.getHealthCareFacility(),
          health_care_facilityValues,
          consumedPaths,
          context,
          "health_care_facility");
    }

    Map<Integer, Map<String, String>> other = extractMultiValued(term, "_participation", values);

    other.values().stream()
        .map(Map::entrySet)
        .map(
            s ->
                s.stream()
                    .collect(
                        Collectors.toMap(
                            e ->
                                "ctx/"
                                    + DefaultValuePath.PARTICIPATION.getPath()
                                    + "_"
                                    + e.getKey().replace("identifiers_", "identifiers|"),
                            e -> StringUtils.wrap(e.getValue(), '"')))
                    .entrySet())
        .map(DefaultValues::buildParticipation)
        .forEach(rmObject::addParticipation);
    consumeAllMatching(term + PATH_DIVIDER + "_participation", values, consumedPaths);

    // Strange Path with value true if setting = other care (238)
    consumedPaths.add(term + "/" + "setting|238");
    // Strange Path with value true if setting != other care (238)
    consumedPaths.add(term + "/" + "setting|");

    if (rmObject.getSetting() != null
        && (rmObject.getSetting().getDefiningCode().getTerminologyId() == null
            || rmObject.getSetting().getDefiningCode().getTerminologyId().getValue() == null)) {
      rmObject.getSetting().getDefiningCode().setTerminologyId(new TerminologyId("openehr"));
    }
  }

  /** {@inheritDoc} */
  @Override
  public Class<EventContext> getAssociatedClass() {
    return EventContext.class;
  }
}
