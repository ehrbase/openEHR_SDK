/*
 * Copyright (c) 2020 Christian Chevalley (Hannover Medical School) and Vitasystems GmbH
 *
 * This file is part of project EHRbase
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 */

package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.Action;
import com.nedap.archie.rm.composition.InstructionDetails;
import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

/**
 * populate the attributes for RM Action
 */
public class ActionAttributes extends CareEntryAttributes {

    public ActionAttributes(CompositionSerializer compositionSerializer, ItemStack itemStack, Map<String, Object> map) {
        super(compositionSerializer, itemStack, map);
    }

    public Map<String, Object> toMap(Action action){

        if (action.getWorkflowId() != null) {
            map = toMap(TAG_WORKFLOW_ID, action.getWorkflowId(), action.getName());
        }

        if (action.getGuidelineId() != null) {
            map = toMap(TAG_GUIDELINE_ID, action.getGuidelineId(), action.getName());
        }

        if (action.getTime() != null && !action.getTime().equals(new DvDateTime())) {
                map = toMap(TAG_TIME, action.getTime(), action.getName());
        }

        if (action.getInstructionDetails() != null) {
            InstructionDetails instructionDetails = action.getInstructionDetails();
            map = toMap(TAG_INSTRUCTION_DETAILS + TAG_ACTIVITY_ID, instructionDetails.getActivityId(), action.getName());
            map = toMap(TAG_INSTRUCTION_DETAILS + TAG_INSTRUCTION_ID, instructionDetails.getInstructionId(), action.getName());
        }


        if (action.getIsmTransition() != null) {
            IsmTransition ismTransition = action.getIsmTransition();
            if (ismTransition != null && ismTransition.getCareflowStep() != null) {
                if (ismTransition.getCurrentState() != null)
                    map = toMap(TAG_ISM_TRANSITION + TAG_CURRENT_STATE, ismTransition.getCurrentState(), action.getName());
                if (ismTransition.getTransition() != null)
                    map = toMap(TAG_ISM_TRANSITION + TAG_TRANSITION, ismTransition.getTransition(), action.getName());
                if (ismTransition.getCareflowStep() != null)
                    map = toMap(TAG_ISM_TRANSITION + TAG_CAREFLOW_STEP, ismTransition.getCareflowStep(), action.getName());
            }
        }

        map =  super.toMap(action);

        return map;
    }

}
