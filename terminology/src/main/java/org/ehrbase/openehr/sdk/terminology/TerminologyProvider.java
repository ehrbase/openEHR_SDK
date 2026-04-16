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
import org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyAccess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminologyProvider {

    public static final String OPENEHR = "openehr";
    private static final OpenEHRTerminologyAccess ARCHIE = OpenEHRTerminologyAccess.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(TerminologyProvider.class);

    public static ValueSet findOpenEhrValueSet(String id, String group, String language) {
        try {
            if (StringUtils.isNotBlank(group)) {
                List<TermCode> terms = ARCHIE.getTermsByOpenEHRGroup(group, language);
                return new ValueSet(
                        id,
                        group,
                        terms.stream()
                                .map(tc -> new TermDefinition(
                                        tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                                .collect(Collectors.toSet()));
            } else {
                List<TermCode> terms = ARCHIE.getTerms(id, language);
                return new ValueSet(
                        id,
                        "all",
                        terms.stream()
                                .map(tc -> new TermDefinition(
                                        tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                                .collect(Collectors.toSet()));
            }
        } catch (RuntimeException e) {
            LOGGER.warn("Unknown  group {} in Terminology {}", group, id);
            return ValueSet.EMPTY_VALUE_SET;
        }
    }

    public static ValueSet findOpenEhrValueSet(String id, String[] values, String language) {
        try {
            if (ArrayUtils.isNotEmpty(values)) {
                return new ValueSet(
                        id,
                        "local",
                        Arrays.stream(values).map(v -> convert(id, v, language)).collect(Collectors.toSet()));
            } else {
                List<TermCode> terms = ARCHIE.getTerms(id, language);
                return new ValueSet(
                        id,
                        "all",
                        terms.stream()
                                .map(tc -> new TermDefinition(
                                        tc.getCodeString(), tc.getDescription(), tc.getDescription()))
                                .collect(Collectors.toSet()));
            }
        } catch (RuntimeException e) {
            LOGGER.warn("Unknown  value {} in Terminology {}", values, id);
            return ValueSet.EMPTY_VALUE_SET;
        }
    }

    public static ValueSet findOpenEhrValueSet(String id, String[] values, String language, String rmAttributeName) {
        AttributeCodesets.Entry entry = rmAttributeName != null ? AttributeCodesets.get(rmAttributeName) : null;
        if (entry != null && entry.container() == ContainerType.GROUP) {
            // Resolves rubrics per group (fixes SPECPR-51 for code 532)
            try {
                if (ArrayUtils.isNotEmpty(values)) {
                    return new ValueSet(
                            id,
                            entry.id(),
                            Arrays.stream(values)
                                    .map(v -> convertWithGroup(entry.id(), v, language))
                                    .collect(Collectors.toSet()));
                } else {
                    return findOpenEhrValueSet(id, entry.id(), language);
                }
            } catch (RuntimeException e) {
                LOGGER.warn("Lookup with group failed for {} in {}, falling back", values, entry.id());
                return findOpenEhrValueSet(id, values, language);
            }
        }
        return findOpenEhrValueSet(id, values, language);
    }

    private static TermDefinition convertWithGroup(String groupId, String code, String language) {
        String value;
        try {
            TermCode term = ARCHIE.getTermByOpenEHRGroup(groupId, language, code);
            if (term == null && !ARCHIE.supportsLanguage(language)) {
                term = ARCHIE.getTermByOpenEHRGroup(groupId, "en", code);
            }
            value = term != null ? term.getDescription() : code;
        } catch (RuntimeException e) {
            value = code;
        }
        return new TermDefinition(code, value, value);
    }

    private static TermDefinition convert(String id, String code, String language) {
        String value;
        try {
            TermCode term = ARCHIE.getTermByOpenEhrId(id, code, language);
            if (term == null && !ARCHIE.supportsLanguage(language)) {
                term = ARCHIE.getTermByOpenEhrId(id, code, "en");
            }
            value = term != null ? term.getDescription() : code;
        } catch (RuntimeException e) {
            value = code;
        }
        return new TermDefinition(code, value, value);
    }
}
