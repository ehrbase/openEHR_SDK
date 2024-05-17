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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class DvParsableConfig extends AbstractsStdConfig<DvParsable> {

    /** {@inheritDoc} */
    @Override
    public Class<DvParsable> getAssociatedClass() {
        return DvParsable.class;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, DvParsable rmObject, Context<Map<String, Object>> context) {
        Map<String, Object> result = new HashMap<>();

        // wf_definition adds the value attribute name ,according to
        // care.better.platform.web.template.InstructionActionTest
        if (List.of("wf_definition").contains(context.getNodeDeque().peek().getId())) {
            addValue(result, currentTerm, "value", rmObject.getValue());
        } else {
            addValue(result, currentTerm, null, rmObject.getValue());
        }
        addValue(result, currentTerm, "formalism", rmObject.getFormalism());
        return result;
    }
}
