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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.marshal.config;

import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.support.identification.LocatableRef;
import com.nedap.archie.rm.support.identification.ObjectId;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;

public class InstructionDetailsConfig extends AbstractsStdConfig<InstructionDetails> {

    /** {@inheritDoc} */
    @Override
    public Class<InstructionDetails> getAssociatedClass() {
        return InstructionDetails.class;
    }

    /** {@inheritDoc} */
    @Override
    public Map<String, Object> buildChildValues(
            String currentTerm, InstructionDetails rmObject, Context<Map<String, Object>> context) {
        Map<String, Object> result = new HashMap<>();

        addValue(
                result,
                currentTerm,
                "activity_id",
                Optional.of(rmObject).map(InstructionDetails::getActivityId).orElse(null));
        addValue(
                result,
                currentTerm,
                "composition_uid",
                Optional.of(rmObject)
                        .map(InstructionDetails::getInstructionId)
                        .map(LocatableRef::getId)
                        .map(ObjectId::getValue)
                        .orElse(null));
        addValue(
                result,
                currentTerm,
                "path",
                Optional.of(rmObject)
                        .map(InstructionDetails::getInstructionId)
                        .map(LocatableRef::getPath)
                        .orElse(null));

        return result;
    }
}
