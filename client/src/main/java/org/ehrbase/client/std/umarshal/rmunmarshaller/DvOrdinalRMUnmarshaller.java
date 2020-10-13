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

package org.ehrbase.client.std.umarshal.rmunmarshaller;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;

import java.util.Map;

public class DvOrdinalRMUnmarshaller extends AbstractRMUnmarshaller<DvOrdinal> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<DvOrdinal> getAssociatedClass() {
        return DvOrdinal.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handle(String currentTerm, DvOrdinal rmObject, Map<String, String> currentValues) {

        rmObject.setSymbol(new DvCodedText());
        rmObject.getSymbol().setDefiningCode(new CodePhrase());
        rmObject.getSymbol().getDefiningCode().setTerminologyId(new TerminologyId("local"));
        setValue(currentTerm, "code", currentValues, rmObject.getSymbol().getDefiningCode()::setCodeString, String.class);

        //@TODO set correct value
        rmObject.getSymbol().setValue("ord1");
        rmObject.setValue(1l);
    }
}
