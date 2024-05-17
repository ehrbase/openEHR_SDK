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
package org.ehrbase.openehr.sdk.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.support.identification.HierObjectId;
import com.nedap.archie.rm.support.identification.LocatableRef;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.util.exception.SdkException;
import org.ehrbase.openehr.sdk.util.rmconstants.RmConstants;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

public class InstructionDetailsRMUnmarshaller extends AbstractRMUnmarshaller<InstructionDetails> {

    /** {@inheritDoc} */
    @Override
    public Class<InstructionDetails> getAssociatedClass() {
        return InstructionDetails.class;
    }

    /** {@inheritDoc} */
    @Override
    public void handle(
            String currentTerm,
            InstructionDetails rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        if (currentValues.keySet().stream().anyMatch(k -> List.of("instruction_uid", "wt_path", "instruction_index")
                .contains(k.getLast().getAttributeName()))) {
            throw new SdkException(String.format(
                    "Calculation of Path from instruction_uid, wt_path or instruction_index is not supported. Provide |path at %s ",
                    currentTerm));
        }
        if (currentValues.keySet().stream()
                .anyMatch(k -> List.of("activity_index").contains(k.getLast().getAttributeName()))) {
            throw new SdkException(String.format(
                    "Calculation of activity_id from activity_index is not supported. Provide |activity_id at %s ",
                    currentTerm));
        }

        setValue(currentTerm, "activity_id", currentValues, rmObject::setActivityId, String.class, consumedPaths);

        rmObject.setInstructionId(new LocatableRef());
        rmObject.getInstructionId().setNamespace("local");
        rmObject.getInstructionId().setType(RmConstants.INSTRUCTION);

        setValue(
                currentTerm,
                "composition_uid",
                currentValues,
                s -> Optional.ofNullable(s)
                        .ifPresent(p -> rmObject.getInstructionId().setId(new HierObjectId(p))),
                String.class,
                consumedPaths);

        setValue(
                currentTerm,
                "path",
                currentValues,
                s -> rmObject.getInstructionId().setPath(s),
                String.class,
                consumedPaths);
    }
}
