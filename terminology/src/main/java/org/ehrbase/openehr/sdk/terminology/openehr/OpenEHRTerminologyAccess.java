/*
 * Copyright (c) 2026 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr;

import com.nedap.archie.terminology.TermCode;
import com.nedap.archie.terminology.TermCodeImpl;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Wrapper for archie's {@link com.nedap.archie.terminology.OpenEHRTerminologyAccess}
 * that patches SPECPR-51 for methods where the group is known.
 */
public class OpenEHRTerminologyAccess {

    private static final String INSTRUCTION_STATES = "instruction states";
    private static final String CODE_532 = "532";

    private static final Map<String, String> CODE_532_INSTRUCTION_RUBRICS = Map.of(
            "en", "completed",
            "es", "completado",
            "ja", "完了",
            "pt", "concluído");

    private static volatile OpenEHRTerminologyAccess instance;

    private final com.nedap.archie.terminology.OpenEHRTerminologyAccess delegate;

    private OpenEHRTerminologyAccess(com.nedap.archie.terminology.OpenEHRTerminologyAccess delegate) {
        this.delegate = delegate;
    }

    public static OpenEHRTerminologyAccess getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (OpenEHRTerminologyAccess.class) {
            if (instance == null) {
                instance = new OpenEHRTerminologyAccess(
                        com.nedap.archie.terminology.OpenEHRTerminologyAccess.getInstance());
            }
        }
        return instance;
    }

    public TermCode getTerm(String terminologyId, String code, String language) {
        return delegate.getTerm(terminologyId, code, language);
    }

    public List<TermCode> getTerms(String terminologyId, String language) {
        return delegate.getTerms(terminologyId, language);
    }

    public TermCode getTermByOpenEhrId(String terminologyId, String code, String language) {
        return delegate.getTermByOpenEhrId(terminologyId, code, language);
    }

    public List<TermCode> getTermsByOpenEhrId(String terminologyId, String language) {
        return delegate.getTermsByOpenEhrId(terminologyId, language);
    }

    public TermCode getTermByTerminologyURI(String uri, String language) {
        return delegate.getTermByTerminologyURI(uri, language);
    }

    public boolean supportsLanguage(String language) {
        if (language == null) {
            return false;
        }
        return !delegate.getTermsByOpenEHRGroup("setting", language).isEmpty();
    }

    public TermCode getTermByOpenEHRGroup(String groupId, String language, String code) {
        TermCode term = delegate.getTermByOpenEHRGroup(groupId, language, code);
        return patchedGroupRubric(term, groupId, language);
    }

    public List<TermCode> getTermsByOpenEHRGroup(String groupId, String language) {
        List<TermCode> terms = delegate.getTermsByOpenEHRGroup(groupId, language);
        if (!INSTRUCTION_STATES.equals(groupId)) {
            return terms;
        }
        return terms.stream().map(t -> patchedGroupRubric(t, groupId, language)).collect(Collectors.toList());
    }

    /**
     * <p>Code 532 appears in both "version lifecycle state" (complete) and "instruction states"
     * (completed). Archie merges them keeping only the first rubric.
     */
    private static TermCode patchedGroupRubric(TermCode term, String groupId, String language) {
        if (term == null || !INSTRUCTION_STATES.equals(groupId) || !CODE_532.equals(term.getCodeString())) {
            return term;
        }
        String correctRubric = CODE_532_INSTRUCTION_RUBRICS.get(language);
        if (correctRubric == null || correctRubric.equals(term.getDescription())) {
            return term;
        }
        return new TermCodeImpl(
                term.getTerminologyId(),
                term.getLanguage(),
                term.getCodeString(),
                correctRubric,
                term.getGroupName(),
                groupId);
    }
}
