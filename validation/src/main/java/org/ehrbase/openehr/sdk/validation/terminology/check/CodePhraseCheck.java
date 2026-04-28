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

import static com.nedap.archie.rmutil.InvariantUtil.ENGLISH;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.terminology.TermCode;
import org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets;
import org.ehrbase.openehr.sdk.terminology.openehr.SimpleTerminologyAccess;

public class CodePhraseCheck implements TerminologyCheck<CodePhrase> {
    @Override
    public Class<CodePhrase> rmClass() {
        return CodePhrase.class;
    }

    @Override
    public void check(String context, CodePhrase codePhrase, String language) {
        validate(context, codePhrase, language);
    }

    public static TermCode validate(String context, CodePhrase codePhrase, String language) {
        if (codePhrase == null) {
            return null;
        }

        String terminologyId = codePhrase.getTerminologyId().getValue();
        if ("local".equals(terminologyId)) {
            return null;
        }

        AttributeCodesets.TerminologyContainer terminologyContainer = AttributeCodesets.get(context);
        if (terminologyContainer == null || !terminologyContainer.terminology().equals(terminologyId)) {
            // XXX CDR-2273 Why tolerate the wrong terminology?
            return null;
        }

        TermCode term =
                switch (terminologyContainer.container()) {
                    case GROUP -> {
                        SimpleTerminologyAccess terminologyAccess = SimpleTerminologyAccess.getInstance();
                        TermCode t = terminologyAccess.getTermByOpenEHRGroup(
                                terminologyContainer.id(), language, codePhrase.getCodeString());
                        if (t == null && !terminologyAccess.supportsLanguage(language)) {
                            t = terminologyAccess.getTermByOpenEHRGroup(
                                    terminologyContainer.id(), ENGLISH, codePhrase.getCodeString());
                        }
                        yield t;
                    }
                    case CODESET -> // External codesets (ISO_639-1, ISO_3166-1...) are only loaded in en
                        SimpleTerminologyAccess.getInstance()
                                .getTerm(terminologyContainer.terminology(), codePhrase.getCodeString(), ENGLISH);
                };

        if (term == null) {
            throw new IllegalArgumentException("supplied code string [" + codePhrase.getCodeString()
                    + "] is not found in codeset:" + terminologyContainer.id());
        }
        return term;
    }
}
