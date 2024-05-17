/*
 * Copyright (c) 2020 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.postprocessor;

import static org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper.*;
import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class EventContextUnmarshalPostprocessor extends AbstractUnmarshalPostprocessor<EventContext> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term,
            EventContext rmObject,
            Map<FlatPathDto, String> values,
            Set<String> consumedPaths,
            Context<Map<FlatPathDto, String>> context) {

        setValue(term + PATH_DIVIDER + "_location", null, values, rmObject::setLocation, String.class, consumedPaths);
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

        setParty(
                term,
                p -> rmObject.setHealthCareFacility((PartyIdentified) p),
                values,
                consumedPaths,
                context,
                "_health_care_facility",
                false);

        Map<Integer, Map<FlatPathDto, String>> other = extractMultiValued(term, "_participation", values);

        other.replaceAll(
                (k, v) -> convertAttributeToFlat(v, term + "/_participation:" + k, "identifiers", "_identifier"));
        rmObject.getParticipations()
                .addAll(other.entrySet().stream()
                        .map(e ->
                                DefaultValues.buildParticipation(e.getValue(), term + "/_participation:" + e.getKey()))
                        .collect(Collectors.toList()));

        consumeAllMatching(term + PATH_DIVIDER + "_participation", values, consumedPaths, false);

        // Strange Path with value true if setting = other care (238)
        consumedPaths.add(term + "/" + "setting|238");
        // Strange Path with value true if setting != other care (238)
        consumedPaths.add(term + "/" + "setting|");

        if (rmObject.getSetting() != null
                && (rmObject.getSetting().getDefiningCode().getTerminologyId() == null
                        || rmObject.getSetting()
                                        .getDefiningCode()
                                        .getTerminologyId()
                                        .getValue()
                                == null)) {
            rmObject.getSetting().getDefiningCode().setTerminologyId(new TerminologyId("openehr"));
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<EventContext> getAssociatedClass() {
        return EventContext.class;
    }
}
