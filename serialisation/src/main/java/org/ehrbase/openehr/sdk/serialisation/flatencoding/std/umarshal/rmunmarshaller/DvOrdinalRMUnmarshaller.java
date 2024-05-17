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

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.serialisation.walker.Context;
import org.ehrbase.openehr.sdk.serialisation.walker.FlatHelper;
import org.ehrbase.openehr.sdk.webtemplate.path.flat.FlatPathDto;

@SuppressWarnings("unused")
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
    public void handle(
            String currentTerm,
            DvOrdinal rmObject,
            Map<FlatPathDto, String> currentValues,
            Context<Map<FlatPathDto, String>> context,
            Set<String> consumedPaths) {

        rmObject.setSymbol(new DvCodedText());
        rmObject.getSymbol().setDefiningCode(new CodePhrase());

        // TerminologyId is fixed local for DvOrdinal
        rmObject.getSymbol().getDefiningCode().setTerminologyId(new TerminologyId("local"));
        FlatHelper.consumeAllMatching(currentTerm + "|terminology", currentValues, consumedPaths, true);

        setValue(
                currentTerm,
                "code",
                currentValues,
                rmObject.getSymbol().getDefiningCode()::setCodeString,
                String.class,
                consumedPaths);

        if (rmObject.getSymbol().getDefiningCode().getCodeString() == null) {
            setValue(
                    currentTerm,
                    null,
                    currentValues,
                    rmObject.getSymbol().getDefiningCode()::setCodeString,
                    String.class,
                    consumedPaths);
        }

        var codeString = rmObject.getSymbol().getDefiningCode().getCodeString();

        var matchingInput = context.getNodeDeque().peek().getInputs().get(0).getList().stream()
                .filter(inputValue -> inputValue.getValue().equals(codeString))
                .findFirst();

        if (matchingInput.isPresent()) {
            rmObject.setValue(Long.valueOf(matchingInput.get().getOrdinal()));
            consumedPaths.add(currentTerm + "|ordinal");
            rmObject.getSymbol().setValue(matchingInput.get().getLabel());
            consumedPaths.add(currentTerm + "|value");
        } else {
            setValue(currentTerm, "ordinal", currentValues, rmObject::setValue, Long.class, consumedPaths);

            setValue(currentTerm, "value", currentValues, rmObject.getSymbol()::setValue, String.class, consumedPaths);
        }
    }
}
