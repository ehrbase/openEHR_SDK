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
import com.nedap.archie.terminology.TermCode;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.ContainerType;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.OpenEHRTerminologyAccess;
import org.ehrbase.openehr.sdk.terminology.openehr.utils.AttributeCodesets;

public class TerminologyCheck implements I_TerminologyCheck {

    protected Class RM_CLASS;
    private static final OpenEHRTerminologyAccess ARCHIE = OpenEHRTerminologyAccess.getInstance();

    public static void validate(String context, CodePhrase codePhrase, String language) {
        String terminologyId = codePhrase.getTerminologyId().getValue();

        if ("local".equals(terminologyId)) {
            return;
        }

        AttributeCodesets.Entry entry = AttributeCodesets.get(context);
        if (entry == null || !entry.terminology().equals(terminologyId)) {
            return;
        }

        switch (entry.container()) {
            case GROUP -> {
                TermCode term = ARCHIE.getTermByOpenEHRGroup(entry.id(), language, codePhrase.getCodeString());
                if (term == null) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in group:" + entry.id());
                }
            }
            case CODESET -> {
                TermCode term = ARCHIE.getTerm(entry.terminology(), codePhrase.getCodeString(), language);
                if (term == null) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in codeset:" + entry.id());
                }
            }
        }
    }

    public static void validate(String context, CodePhrase codePhrase) throws IllegalArgumentException {
        validate(context, codePhrase, "en");
    }

    public static void validate(String context, DvCodedText dvCodedText, String language)
            throws IllegalArgumentException {
        CodePhrase definingCode = dvCodedText.getDefiningCode();
        validate(context, definingCode, language);

        AttributeCodesets.Entry entry = AttributeCodesets.get(context);
        if (entry == null) {
            return;
        }

        // Group-aware rubric lookup — fixes SPECPR-51 for code 532
        TermCode term = entry.container() == ContainerType.GROUP
                ? ARCHIE.getTermByOpenEHRGroup(entry.id(), language, definingCode.getCodeString())
                : ARCHIE.getTerm(entry.terminology(), definingCode.getCodeString(), language);
        if (term == null) {
            return;
        }

        String rubric = term.getDescription();
        if (!rubric.equals(dvCodedText.getValue())) {
            // fallback: check if the value matches the English rubric
            TermCode englishTerm = entry.container() == ContainerType.GROUP
                    ? ARCHIE.getTermByOpenEHRGroup(entry.id(), "en", definingCode.getCodeString())
                    : ARCHIE.getTerm(entry.terminology(), definingCode.getCodeString(), "en");
            if (englishTerm == null || !englishTerm.getDescription().equals(dvCodedText.getValue())) {
                throw new IllegalArgumentException("supplied value [" + dvCodedText.getValue()
                        + "] doesn't match code string:" + definingCode.getCodeString()
                        + " (language:" + language + ", terminology:"
                        + definingCode.getTerminologyId().getValue()
                        + "), expected:" + rubric);
            }
        }
    }

    public static void validate(String context, DvCodedText dvCodedText) throws IllegalArgumentException {
        validate(context, dvCodedText, "en");
    }

    public Class rmClass() {
        return RM_CLASS;
    }
}
