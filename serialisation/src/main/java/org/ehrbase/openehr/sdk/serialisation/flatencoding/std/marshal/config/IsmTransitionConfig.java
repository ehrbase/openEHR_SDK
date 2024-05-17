/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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

import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class IsmTransitionConfig extends AbstractsStdConfig<IsmTransition> {

    /** {@inheritDoc} */
    @Override
    public Class<IsmTransition> getAssociatedClass() {
        return IsmTransition.class;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, IsmTransition rmObject, Context<Map<String, Object>> context) {
        Map<String, Object> result = new HashMap<>();
        addValue(
                result,
                currentTerm + "/current_state",
                "code",
                Optional.of(rmObject)
                        .map(IsmTransition::getCurrentState)
                        .map(DvCodedText::getDefiningCode)
                        .map(CodePhrase::getCodeString)
                        .orElse(null));
        addValue(
                result,
                currentTerm + "/current_state",
                "value",
                Optional.of(rmObject)
                        .map(IsmTransition::getCurrentState)
                        .map(DvText::getValue)
                        .orElse(null));
        addValue(
                result,
                currentTerm + "/current_state",
                "terminology",
                Optional.of(rmObject)
                        .map(IsmTransition::getCurrentState)
                        .map(DvCodedText::getDefiningCode)
                        .map(CodePhrase::getTerminologyId)
                        .map(ObjectId::getValue)
                        .orElse(null));
        return result;
    }
}
