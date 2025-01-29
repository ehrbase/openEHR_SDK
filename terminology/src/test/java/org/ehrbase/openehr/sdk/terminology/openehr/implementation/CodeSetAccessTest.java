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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import com.nedap.archie.rm.datatypes.CodePhrase;
import com.nedap.archie.rm.support.identification.TerminologyId;
import org.ehrbase.openehr.sdk.terminology.openehr.CodeSetAccess;
import org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifiers;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.junit.Test;

public class CodeSetAccessTest {

    @Test
    public void testCodeSetAccess() throws Exception {
        TerminologyInterface simpleTerminologyInterface = new SimpleTerminologyInterface("en");

        CodeSetAccess codeSetAccess =
                simpleTerminologyInterface.codeSetForId(OpenEHRCodeSetIdentifiers.INTEGRITY_CHECK_ALGORITHMS);

        assertEquals("openehr_integrity_check_algorithms", codeSetAccess.id());

        assertEquals(7, codeSetAccess.allCodes().size());

        assertTrue(codeSetAccess.hasCode(
                new CodePhrase(new TerminologyId("openehr_integrity_check_algorithms"), "SHA-1")));
    }
}
