/*
 * Copyright (c) 2021 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Observation;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.stream.Stream;
import org.ehrbase.openehr.sdk.serialisation.walker.DurationHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.RMHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;
import org.threeten.extra.PeriodDuration;

public class ObservationValueInserter extends AbstractValueInserter<Observation> {

    public void insert(Observation rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (rmObject.getData() != null) {
            insert(
                    rmObject.getData(),
                    defaultValues,
                    node
                            .findMatching(n -> Objects.equals(
                                    n.getNodeId(), rmObject.getData().getArchetypeNodeId()))
                            .stream()
                            .findFirst()
                            .orElse(FlatHelper.buildDummyChild("history", node)));
        }

        if (rmObject.getState() != null) {
            insert(
                    rmObject.getState(),
                    defaultValues,
                    node
                            .findMatching(n -> Objects.equals(
                                    n.getNodeId(), rmObject.getState().getArchetypeNodeId()))
                            .stream()
                            .findFirst()
                            .orElse(FlatHelper.buildDummyChild("history", node)));
        }
    }

    private void insert(History<?> rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (RMHelper.isEmpty(rmObject.getOrigin())
                && (defaultValues.containsDefaultValue(DefaultValuePath.TIME)
                        || defaultValues.containsDefaultValue(DefaultValuePath.HISTORY_ORIGIN))) {
            TemporalAccessor defaultTemporalAccessor = Stream.of(DefaultValuePath.HISTORY_ORIGIN, DefaultValuePath.TIME)
                    .map(defaultValues::getDefaultValue)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow();
            rmObject.setOrigin(new DvDateTime(defaultTemporalAccessor));
        }

        if (rmObject.getEvents() != null && rmObject.getOrigin() != null) {

            rmObject.getEvents()
                    .forEach(e -> insert(
                            e,
                            rmObject.getOrigin().getValue(),
                            node.findMatching(n -> Objects.equals(n.getNodeId(), e.getArchetypeNodeId())).stream()
                                    .findFirst()
                                    .orElse(FlatHelper.buildDummyChild("event", node))));
        }
    }

    private void insert(Event<?> rmObject, TemporalAccessor origin, WebTemplateNode node) {
        if (RMHelper.isEmpty(rmObject.getTime())) {

            TemporalAccessor defaultValue = origin;
            if (defaultValue instanceof Temporal) {
                defaultValue = ((Temporal) defaultValue)
                        .plus(DurationHelper.buildTotalRange(
                                        node.findChildById("offset").orElse(null), DurationHelper.MIN_MAX.MIN)
                                .orElse(PeriodDuration.ZERO));
            }
            rmObject.setTime(new DvDateTime(defaultValue));
        }
    }

    @Override
    public Class<Observation> getAssociatedClass() {
        return Observation.class;
    }
}
