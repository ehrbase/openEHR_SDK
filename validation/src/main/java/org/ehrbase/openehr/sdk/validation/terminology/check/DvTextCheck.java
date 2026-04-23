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

import com.nedap.archie.rm.datavalues.DvText;
import com.nedap.archie.rm.datavalues.TermMapping;
import org.apache.commons.collections4.CollectionUtils;

public class DvTextCheck implements TerminologyCheck<DvText> {
    @Override
    public Class<DvText> rmClass() {
        return DvText.class;
    }

    @Override
    public void check(String context, DvText dvText, String language) {
        checkDvText(dvText, language);
    }

    public static void checkDvText(DvText dvText, String language) {
        if (dvText != null && CollectionUtils.isNotEmpty(dvText.getMappings())) {
            for (TermMapping termMapping : dvText.getMappings()) {
                TermMappingCheck.checkTermMapping(termMapping, language);
            }
        }
    }
}
