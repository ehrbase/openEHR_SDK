/*
 * Copyright (c) 2020 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology;

import com.nedap.archie.terminology.TermCode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets;
import org.ehrbase.openehr.sdk.terminology.openehr.ContainerType;
import org.ehrbase.openehr.sdk.terminology.openehr.SimpleTerminologyAccess;

public class TerminologyProvider {

    public static final String OPENEHR = "openehr";
    private static final SimpleTerminologyAccess TERMINOLOGY_ACCESS = SimpleTerminologyAccess.getInstance();

    public static ValueSet findOpenEhrValueSet(String id, String group, String language) {
        if (StringUtils.isNotBlank(group)) {
            List<TermCode> terms = TERMINOLOGY_ACCESS.getTermsByOpenEHRGroup(group, language);
            return new ValueSet(
                    id,
                    group,
                    terms.stream()
                            .map(tc -> new TermDefinition(tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                            .collect(Collectors.toSet()));
        } else {
            List<TermCode> terms = TERMINOLOGY_ACCESS.getTerms(id, language);
            return new ValueSet(
                    id,
                    "all",
                    terms.stream()
                            .map(tc -> new TermDefinition(tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                            .collect(Collectors.toSet()));
        }
    }

    public static ValueSet findOpenEhrValueSet(String id, String[] values, String language) {
        if (ArrayUtils.isNotEmpty(values)) {
            return new ValueSet(
                    id,
                    "local",
                    Arrays.stream(values).map(v -> convert(id, v, language)).collect(Collectors.toSet()));
        } else {
            List<TermCode> terms = TERMINOLOGY_ACCESS.getTerms(id, language);
            return new ValueSet(
                    id,
                    "all",
                    terms.stream()
                            .map(tc -> new TermDefinition(tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                            .collect(Collectors.toSet()));
        }
    }

    public static ValueSet findOpenEhrValueSet(String id, String[] values, String language, String rmAttributeName) {
        AttributeCodesets.TerminologyContainer terminologyContainer =
                rmAttributeName != null ? AttributeCodesets.get(rmAttributeName) : null;
        if (terminologyContainer != null && terminologyContainer.container() == ContainerType.GROUP) {
            // Resolves rubrics per group (fixes SPECPR-51 for code 532)
            if (ArrayUtils.isNotEmpty(values)) {
                return new ValueSet(
                        id,
                        terminologyContainer.id(),
                        Arrays.stream(values)
                                .map(v -> convertWithGroup(terminologyContainer.id(), v, language))
                                .collect(Collectors.toSet()));
            } else {
                return findOpenEhrValueSet(id, terminologyContainer.id(), language);
            }
        }
        return findOpenEhrValueSet(id, values, language);
    }

    private static TermDefinition convertWithGroup(String groupId, String code, String language) {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(groupId, language, code);
        if (term == null && !TERMINOLOGY_ACCESS.supportsLanguage(language)) {
            term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(groupId, "en", code);
        }
        String value = term != null ? term.getDescription() : code;
        return new TermDefinition(code, value, value);
    }

    private static TermDefinition convert(String id, String code, String language) {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEhrId(id, code, language);
        if (term == null && !TERMINOLOGY_ACCESS.supportsLanguage(language)) {
            term = TERMINOLOGY_ACCESS.getTermByOpenEhrId(id, code, "en");
        }
        String value = term != null ? term.getDescription() : code;
        return new TermDefinition(code, value, value);
    }
}
