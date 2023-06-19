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

import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.util.OpenEHRDateTimeSerializationUtils;

public class HistoryPostprocessor extends AbstractMarshalPostprocessor<History> {

    /**
     * {@inheritDoc} Removes the {@link History#getOrigin} if it is equal to fist {@link
     * Event#getTime()}
     */
    @Override
    public void process(
            String term, History rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        if (rmObject.getOrigin() != null && rmObject.getOrigin().getValue() != null) {

            // Add history origin only if different from time of first event.
            Optional<DvDateTime> first = ((History<ItemStructure>) rmObject)
                    .getEvents().stream()
                            .map(Event::getTime)
                            .filter(Objects::nonNull)
                            .filter(t -> t.getValue() != null)
                            .sorted()
                            .findFirst();
            if (first.map(OpenEHRDateTimeSerializationUtils::toMagnitude)
                    .filter(t -> Objects.equals(t, OpenEHRDateTimeSerializationUtils.toMagnitude(rmObject.getOrigin())))
                    .isEmpty()) {
                addValue(
                        values,
                        term + "/history_origin",
                        null,
                        rmObject.getOrigin().getValue());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Class<History> getAssociatedClass() {
        return History.class;
    }
}
