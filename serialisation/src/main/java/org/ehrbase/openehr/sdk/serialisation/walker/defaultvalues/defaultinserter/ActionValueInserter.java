/*
 * Copyright (c) 2021 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.defaultinserter;

import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Objects;
import java.util.stream.Stream;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.RMHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class ActionValueInserter extends AbstractValueInserter<Action> {
    @Override
    public void insert(Action rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (RMHelper.isEmpty(rmObject.getTime())
                && (defaultValues.containsDefaultValue(DefaultValuePath.TIME)
                        || defaultValues.containsDefaultValue(DefaultValuePath.ACTION_TIME))) {
            TemporalAccessor defaultTemporalAccessor = Stream.of(DefaultValuePath.ACTION_TIME, DefaultValuePath.TIME)
                    .map(defaultValues::getDefaultValue)
                    .filter(Objects::nonNull)
                    .findFirst()
                    .orElseThrow();
            rmObject.setTime(new DvDateTime(defaultTemporalAccessor));
        }

        if (rmObject.getIsmTransition() == null) {
            rmObject.setIsmTransition(new IsmTransition());
        }
        new IsmTransitionValueInserter()
                .insert(rmObject.getIsmTransition(), defaultValues, FlatHelper.buildDummyChild("ism_transition", node));
        if (RMHelper.isEmpty(rmObject.getIsmTransition())) {
            rmObject.setIsmTransition(null);
        }
    }

    @Override
    public Class<Action> getAssociatedClass() {
        return Action.class;
    }
}
