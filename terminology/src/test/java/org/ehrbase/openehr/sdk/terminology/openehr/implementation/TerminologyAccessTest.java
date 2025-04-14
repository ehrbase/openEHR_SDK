/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import java.util.Set;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyAccess;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.junit.Before;
import org.junit.Test;

public class TerminologyAccessTest {

    TerminologyAccess terminologyAccess;

    @Before
    public void setUp() {
        TerminologyInterface simpleTerminologyInterface = new SimpleTerminologyInterface("en");

        // get openehr terminology
        terminologyAccess = simpleTerminologyInterface.terminology("openehr");
    }

    @Test
    public void testTerminologyAccess() throws Exception {
        assertEquals("openehr", terminologyAccess.id());

        Set<CodePhrase> codePhrases = terminologyAccess.allCodes();
        assertEquals(280, codePhrases.size());

        codePhrases = terminologyAccess.codesForGroupId("setting");
        assertEquals(14, codePhrases.size());

        assertTrue(terminologyAccess.hasCodeForGroupId(
                "setting", new CodePhrase(new TerminologyId("openehr"), "237"))); // nursing home care

        assertFalse(terminologyAccess.hasCodeForGroupId(
                "setting", new CodePhrase(new TerminologyId("openehr"), "240"))); // bad code string
    }

    @Test
    public void labelForCodeTest() {
        assertEquals("nursing home care", terminologyAccess.rubricForCode("237", "en"));
    }
}
