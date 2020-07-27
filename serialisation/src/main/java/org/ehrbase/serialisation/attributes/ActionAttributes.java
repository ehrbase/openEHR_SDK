package org.ehrbase.serialisation.attributes;

import com.nedap.archie.rm.composition.*;
import com.nedap.archie.rm.datastructures.Event;
import com.nedap.archie.rm.datastructures.History;
import com.nedap.archie.rm.datastructures.IntervalEvent;
import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.encapsulated.DvParsable;
import com.nedap.archie.rm.datavalues.quantity.datetime.DvDateTime;
import org.ehrbase.serialisation.dbencoding.CompositionSerializer;
import org.ehrbase.serialisation.dbencoding.ItemStack;

import java.util.Map;

import static org.ehrbase.serialisation.dbencoding.CompositionSerializer.*;

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

        if (action.getTime() != null) {
            if (!action.getTime().equals(new DvDateTime())) {
//                    if (allElements || !action.getTime().equals(new DvDateTime(RmBinding.DEFAULT_DATE_TIME))) {
                map = toMap(TAG_TIME, action.getTime(), action.getName());
//					hasActiveContent = true;
            }
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
