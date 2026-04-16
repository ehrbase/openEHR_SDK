/*
 * Copyright (c) 2019 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.validation.terminology.validator;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyAccess;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.ehrbase.openehr.sdk.terminology.openehr.utils.AttributeCodesets;

public class TerminologyCheck implements I_TerminologyCheck {

    protected Class RM_CLASS;

    public static void validate(
            TerminologyInterface terminologyInterface, String context, CodePhrase codePhrase, String language) {
        String terminologyId = codePhrase.getTerminologyId().getValue();

        // if terminology id is not supported, skip the validation
        if (terminologyId.equals("local")
                || !terminologyInterface.codeSetIdentifiers().contains(terminologyId)) return;

        AttributeCodesets.Entry entry = AttributeCodesets.get(context);
        if (entry == null) {
            // log
            return;
        }

        switch (entry.container()) {
            case GROUP -> {
                if (!terminologyInterface.terminology(terminologyId).hasCodeForGroupId(entry.id(), codePhrase)) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in group:" + entry.id());
                }
            }
            case CODESET -> {
                if (!terminologyInterface.codeSet(terminologyId).hasCode(codePhrase)) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in codeset:" + entry.id());
                }
            }
            default -> throw new IllegalArgumentException("undefined container type");
        }
    }

    public static void validate(TerminologyInterface terminologyInterface, String context, CodePhrase codePhrase)
            throws IllegalArgumentException {
        validate(terminologyInterface, context, codePhrase, "en");
    }

    public static void validate(
            TerminologyInterface terminologyInterface, String context, DvCodedText dvCodedText, String language)
            throws IllegalArgumentException {
        CodePhrase definingCode = dvCodedText.getDefiningCode();
        validate(terminologyInterface, context, definingCode, language);

        String terminologyId = definingCode.getTerminologyId().getValue();

        TerminologyAccess terminology = terminologyInterface.terminology(terminologyId);
        if (terminology == null) {
            // terminology is NOT defined
            return;
        }

        String rubric = terminology.rubricForCode(definingCode.getCodeString(), language);

        boolean valid = rubric.equals(dvCodedText.getValue());
        if (!valid) {
            throw new IllegalArgumentException("supplied value ["
                    + dvCodedText.getValue()
                    + "] doesn't match code string:"
                    + definingCode.getCodeString()
                    + " (language:" + language + ", terminology:"
                    + terminologyId + "), expected:" + rubric);
        }
    }

    public static void validate(TerminologyInterface terminologyInterface, String context, DvCodedText dvCodedText)
            throws IllegalArgumentException {
        validate(terminologyInterface, context, dvCodedText, "en");
    }

    public Class rmClass() {
        return RM_CLASS;
    }
}
