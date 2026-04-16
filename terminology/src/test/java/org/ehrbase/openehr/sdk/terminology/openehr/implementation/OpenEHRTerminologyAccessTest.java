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
package org.ehrbase.openehr.sdk.terminology.openehr.implementation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.nedap.archie.terminology.TermCode;
import java.util.List;
import org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyAccess;
import org.junit.Test;

public class OpenEHRTerminologyAccessTest {

    private final OpenEHRTerminologyAccess archie = OpenEHRTerminologyAccess.getInstance();

    @Test
    public void groupLookupReturnsTerms() {
        List<TermCode> terms = archie.getTermsByOpenEHRGroup("setting", "en");
        assertEquals(14, terms.size());
    }

    @Test
    public void groupLookupByCodeReturnsCorrectRubric() {
        TermCode term = archie.getTermByOpenEHRGroup("setting", "en", "237");
        assertNotNull(term);
        assertEquals("nursing home care", term.getDescription());
    }

    @Test
    public void groupLookupUnknownCodeReturnsNull() {
        TermCode term = archie.getTermByOpenEHRGroup("setting", "en", "240");
        assertNull(term);
    }

    @Test
    public void codesetLookupReturnsTerms() {
        List<TermCode> terms = archie.getTerms("ISO_3166-1", "en");
        assertTrue(terms.size() > 200);
    }

    @Test
    public void codesetLookupByCodeReturnsCorrectValue() {
        TermCode term = archie.getTerm("ISO_3166-1", "AU", "en");
        assertNotNull(term);
    }

    @Test
    public void codesetLookupUnknownCodeReturnsNull() {
        TermCode term = archie.getTerm("ISO_3166-1", "ZZ", "en");
        assertNull(term);
    }

    @Test
    public void rubricInSpanish() {
        TermCode term = archie.getTermByOpenEHRGroup("composition category", "es", "433");
        assertNotNull(term);
        assertEquals("evento", term.getDescription());
    }

    @Test
    public void rubricInPortuguese() {
        TermCode term = archie.getTermByOpenEHRGroup("composition category", "pt", "433");
        assertNotNull(term);
        assertEquals("evento", term.getDescription());
    }

    @Test
    public void rubricInJapanese() {
        TermCode term = archie.getTermByOpenEHRGroup("composition category", "ja", "433");
        assertNotNull(term);
        assertEquals("イベント", term.getDescription());
    }

    @Test
    public void code532VersionLifecycleStateReturnsComplete() {
        TermCode term = archie.getTermByOpenEHRGroup("version lifecycle state", "en", "532");
        assertNotNull(term);
        assertEquals("complete", term.getDescription());
    }

    @Test
    public void code532InstructionStatesReturnsCompleted() {
        TermCode term = archie.getTermByOpenEHRGroup("instruction states", "en", "532");
        assertNotNull(term);
        assertEquals("completed", term.getDescription());
    }

    @Test
    public void code532InstructionStatesSpanish() {
        TermCode term = archie.getTermByOpenEHRGroup("instruction states", "es", "532");
        assertNotNull(term);
        assertEquals("completado", term.getDescription());
    }

    @Test
    public void code532InstructionStatesPortuguese() {
        TermCode term = archie.getTermByOpenEHRGroup("instruction states", "pt", "532");
        assertNotNull(term);
        assertEquals("concluído", term.getDescription());
    }

    @Test
    public void code532InstructionStatesJapanese() {
        TermCode term = archie.getTermByOpenEHRGroup("instruction states", "ja", "532");
        assertNotNull(term);
        assertEquals("完了", term.getDescription());
    }

    @Test
    public void code532PatchedInGroupList() {
        List<TermCode> terms = archie.getTermsByOpenEHRGroup("instruction states", "en");
        TermCode code532 = terms.stream()
                .filter(t -> "532".equals(t.getCodeString()))
                .findFirst()
                .orElse(null);
        assertNotNull(code532);
        assertEquals("completed", code532.getDescription());
    }

    @Test
    public void supportedLanguages() {
        assertTrue(archie.supportsLanguage("en"));
        assertTrue(archie.supportsLanguage("es"));
        assertTrue(archie.supportsLanguage("ja"));
        assertTrue(archie.supportsLanguage("pt"));
        assertFalse(archie.supportsLanguage("de"));
        assertFalse(archie.supportsLanguage("fr"));
        assertFalse(archie.supportsLanguage("ro"));
        assertFalse(archie.supportsLanguage(null));
    }
}
