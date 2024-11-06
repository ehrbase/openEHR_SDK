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

import com.nedap.archie.rm.composition.Action;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class ActionMarshalPostprocessor extends AbstractMarshalPostprocessor<Action> {

    /** {@inheritDoc} Adds the encoding information */
    @Override
    public void process(
            String term, Action rmObject, Map<String, Object> values, Context<Map<String, Object>> context) {

        if (rmObject.getInstructionDetails() != null) {
            handleRmAttribute(term, rmObject.getInstructionDetails(), values, context, "instruction_details");
        }
        // contents handled via InstructionDetailsConfig
        // Note: the paths are unexpectedly missing the _instruction_details node
        values.remove(term + "/instruction_id");
        values.remove(term + "/activity_id");
    }

    /** {@inheritDoc} */
    @Override
    public Class<Action> getAssociatedClass() {
        return Action.class;
    }
}
