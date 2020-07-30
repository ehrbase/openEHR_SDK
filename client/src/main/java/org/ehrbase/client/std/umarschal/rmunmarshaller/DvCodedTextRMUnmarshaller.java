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

package org.ehrbase.client.std.umarschal.rmunmarshaller;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.support.identification.TerminologyId;

import java.util.Map;

public class DvCodedTextRMUnmarshaller extends AbstractRMUnmarshaller<DvCodedText> {

    @Override
    public Class<DvCodedText> getRMClass() {
        return DvCodedText.class;
    }

    @Override
    public void handle(String termLoop, DvCodedText child, Map<String, String> values) {
        setValue(termLoop, "value", values, child::setValue, String.class);
        child.setDefiningCode(new CodePhrase());
        setValue(termLoop, "code", values, c -> child.getDefiningCode().setCodeString(c), String.class);
        child.getDefiningCode().setTerminologyId(new TerminologyId());
        setValue(termLoop, "terminology", values, t -> child.getDefiningCode().getTerminologyId().setValue(t), String.class);
    }
}
