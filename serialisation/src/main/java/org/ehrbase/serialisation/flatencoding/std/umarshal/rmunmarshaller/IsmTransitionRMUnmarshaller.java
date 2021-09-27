/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.serialisation.flatencoding.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.composition.IsmTransition;
import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.serialisation.walker.Context;
import org.ehrbase.webtemplate.path.flat.FlatPathDto;

import java.util.Map;
import java.util.Set;

public class IsmTransitionRMUnmarshaller extends AbstractRMUnmarshaller<IsmTransition> {

    @Override
    public Class<IsmTransition> getAssociatedClass() {
        return IsmTransition.class;
    }

    @Override
    public void handle(String currentTerm, IsmTransition rmObject, Map<FlatPathDto, String> currentValues, Context<Map<FlatPathDto, String>> context, Set<String> consumedPaths) {

        rmObject.setCurrentState(new DvCodedText());
        rmObject.getCurrentState().setDefiningCode(new CodePhrase());
        rmObject.getCurrentState().getDefiningCode().setTerminologyId(new TerminologyId());
        setValue(currentTerm + "/current_state", "code", currentValues, rmObject.getCurrentState().getDefiningCode()::setCodeString, String.class, consumedPaths);
        setValue(currentTerm + "/current_state", "value", currentValues, rmObject.getCurrentState()::setValue, String.class, consumedPaths);
        setValue(currentTerm + "/current_state", "terminology", currentValues, rmObject.getCurrentState().getDefiningCode().getTerminologyId()::setValue, String.class, consumedPaths);

    }
}
