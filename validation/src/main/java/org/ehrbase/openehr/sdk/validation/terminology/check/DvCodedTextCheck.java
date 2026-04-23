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
package org.ehrbase.openehr.sdk.validation.terminology.check;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.datavalues.DvCodedText;
import com.nedap.archie.terminology.TermCode;
import org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets;
import org.ehrbase.openehr.sdk.terminology.openehr.ContainerType;
import org.ehrbase.openehr.sdk.terminology.openehr.SimpleTerminologyAccess;

public class DvCodedTextCheck implements TerminologyCheck<DvCodedText> {

    @Override
    public Class<DvCodedText> rmClass() {
        return DvCodedText.class;
    }

    @Override
    public void check(String context, DvCodedText dvCodedText, String language) {
        DvTextCheck.checkDvText(dvCodedText, language);
        validate(context, dvCodedText, language);
    }

    public static void validate(String context, DvCodedText dvCodedText, String language)
            throws IllegalArgumentException {
        if (dvCodedText == null) {
            return;
        }

        CodePhrase definingCode = dvCodedText.getDefiningCode();
        TermCode term = CodePhraseCheck.validate(context, definingCode, language);
        if (term == null) {
            return;
        }

        String rubric = term.getDescription();
        if (!rubric.equals(dvCodedText.getValue())) {

            AttributeCodesets.TerminologyContainer terminologyContainer = AttributeCodesets.get(context);

            // fallback: check if the value matches the English rubric
            TermCode englishTerm = terminologyContainer.container() == ContainerType.GROUP
                    ? SimpleTerminologyAccess.getInstance()
                            .getTermByOpenEHRGroup(terminologyContainer.id(), "en", definingCode.getCodeString())
                    : SimpleTerminologyAccess.getInstance()
                            .getTerm(terminologyContainer.terminology(), definingCode.getCodeString(), "en");

            if (englishTerm == null || !englishTerm.getDescription().equals(dvCodedText.getValue())) {
                throw new IllegalArgumentException("supplied value [" + dvCodedText.getValue()
                        + "] doesn't match code string:" + definingCode.getCodeString()
                        + " (language:" + language + ", terminology:"
                        + definingCode.getTerminologyId().getValue()
                        + "), expected:" + rubric);
            }
        }
    }
}
