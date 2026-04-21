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
import org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets;
import org.ehrbase.openehr.sdk.terminology.openehr.ContainerType;
import org.ehrbase.openehr.sdk.terminology.openehr.SimpleTerminologyAccess;

public class TerminologyCheck implements I_TerminologyCheck {

    protected Class RM_CLASS;
    private static final SimpleTerminologyAccess TERMINOLOGY_ACCESS = SimpleTerminologyAccess.getInstance();

    public static void validate(String context, CodePhrase codePhrase, String language) {
        String terminologyId = codePhrase.getTerminologyId().getValue();

        if ("local".equals(terminologyId)) {
            return;
        }

        AttributeCodesets.TerminologyContainer terminologyContainer = AttributeCodesets.get(context);
        if (terminologyContainer == null || !terminologyContainer.terminology().equals(terminologyId)) {
            return;
        }

        switch (terminologyContainer.container()) {
            case GROUP -> {
                TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                        terminologyContainer.id(), language, codePhrase.getCodeString());
                if (term == null && !TERMINOLOGY_ACCESS.supportsLanguage(language)) {
                    term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                            terminologyContainer.id(), "en", codePhrase.getCodeString());
                }
                if (term == null) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in group:" + terminologyContainer.id());
                }
            }
            case CODESET -> {
                // External codesets (ISO_639-1, ISO_3166-1...) are only loaded in en
                TermCode term = TERMINOLOGY_ACCESS.getTerm(
                        terminologyContainer.terminology(), codePhrase.getCodeString(), "en");
                if (term == null) {
                    throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                            + "] is not found in codeset:" + terminologyContainer.id());
                }
            }

            default -> throw new IllegalArgumentException("undefined container type");
        }
    }

    public static void validate(String context, DvCodedText dvCodedText, String language)
            throws IllegalArgumentException {
        CodePhrase definingCode = dvCodedText.getDefiningCode();
        validate(context, definingCode, language);

        AttributeCodesets.TerminologyContainer terminologyContainer = AttributeCodesets.get(context);
        if (terminologyContainer == null) {
            return;
        }

        TermCode term = terminologyContainer.container() == ContainerType.GROUP
                ? TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                        terminologyContainer.id(), language, definingCode.getCodeString())
                : TERMINOLOGY_ACCESS.getTerm(
                        terminologyContainer.terminology(), definingCode.getCodeString(), language);
        if (term == null) {
            return;
        }

        String rubric = term.getDescription();
        if (!rubric.equals(dvCodedText.getValue())) {
            // fallback: check if the value matches the English rubric
            TermCode englishTerm = terminologyContainer.container() == ContainerType.GROUP
                    ? TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                            terminologyContainer.id(), "en", definingCode.getCodeString())
                    : TERMINOLOGY_ACCESS.getTerm(
                            terminologyContainer.terminology(), definingCode.getCodeString(), "en");
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
