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

import com.nedap.archie.rm.composition.Activity;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import org.ehrbase.openehr.sdk.serialisation.walker.RMHelper;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValuePath;
import org.ehrbase.openehr.sdk.serialisation.walker.defaultvalues.DefaultValues;
import org.ehrbase.openehr.sdk.webtemplate.model.WebTemplateNode;

public class ActivityValueInserter extends AbstractValueInserter<Activity> {

    @Override
    public void insert(Activity rmObject, DefaultValues defaultValues, WebTemplateNode node) {

        if (RMHelper.isEmpty(rmObject.getTiming())
                && defaultValues.containsDefaultValue(DefaultValuePath.ACTIVITY_TIMING)) {

            if (rmObject.getTiming() == null) {
                rmObject.setTiming(new DvParsable());
            }

            rmObject.getTiming().setValue(defaultValues.getDefaultValue(DefaultValuePath.ACTIVITY_TIMING));
            rmObject.getTiming().setFormalism("timing");
        }

        if (rmObject.getTiming() != null && rmObject.getTiming().getFormalism() == null) {
            // default
            rmObject.getTiming().setFormalism("timing");
        }

        if (rmObject.getActionArchetypeId() == null) {
            rmObject.setActionArchetypeId("/.*/");
        }
    }

    @Override
    public Class<Activity> getAssociatedClass() {
        return Activity.class;
    }
}
