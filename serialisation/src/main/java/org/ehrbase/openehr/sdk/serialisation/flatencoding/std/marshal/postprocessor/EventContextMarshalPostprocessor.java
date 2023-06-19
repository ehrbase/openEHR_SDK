/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.postprocessor;

import static org.ehrbase.openehr.sdk.webtemplate.parser.OPTParser.PATH_DIVIDER;

import com.nedap.archie.rm.composition.EventContext;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class EventContextMarshalPostprocessor extends AbstractMarshalPostprocessor<EventContext> {

    /** {@inheritDoc} */
    @Override
    public void process(
            String term, EventContext rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        addValue(
                values,
                term + PATH_DIVIDER + "_end_time",
                null,
                Optional.ofNullable(rmObject.getEndTime())
                        .map(DvDateTime::getValue)
                        .orElse(null));

        addValue(
                values,
                term + PATH_DIVIDER + "_location",
                null,
                Optional.ofNullable(rmObject.getLocation()).orElse(null));

        if (rmObject.getHealthCareFacility() != null) {
            handleRmAttribute(term, rmObject.getHealthCareFacility(), values, context, "health_care_facility");
        }

        if (rmObject.getParticipations() != null) {
            IntStream.range(0, rmObject.getParticipations().size()).forEach(i -> {
                callMarshal(
                        term,
                        "_participation:" + i,
                        rmObject.getParticipations().get(i),
                        values,
                        context,
                        context.getNodeDeque()
                                .peek()
                                .findChildById("participation")
                                .orElse(null));
                callPostprocess(
                        term,
                        "_participation:" + i,
                        rmObject.getParticipations().get(i),
                        values,
                        context,
                        context.getNodeDeque()
                                .peek()
                                .findChildById("participation")
                                .orElse(null));
            });
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<EventContext> getAssociatedClass() {
        return EventContext.class;
    }
}
