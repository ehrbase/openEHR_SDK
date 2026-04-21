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
import java.util.Map;

import org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifiers;
import org.ehrbase.openehr.sdk.terminology.openehr.SimpleTerminologyAccess;
import org.junit.Test;

public class SimpleTerminologyAccessTest {

    private final SimpleTerminologyAccess TERMINOLOGY_ACCESS = SimpleTerminologyAccess.getInstance();

    @Test
    public void groupLookupReturnsTerms() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTermsByOpenEHRGroup("setting", "en");
        assertEquals(14, terms.size());
    }

    @Test
    public void groupLookupByCodeReturnsCorrectRubric() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("setting", "en", "237");
        assertNotNull(term);
        assertEquals("nursing home care", term.getDescription());
    }

    @Test
    public void groupLookupUnknownCodeReturnsNull() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("setting", "en", "240");
        assertNull(term);
    }

    @Test
    public void codesetLookupReturnsTerms() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTerms("ISO_3166-1", "en");
        assertTrue(terms.size() > 200);
    }

    @Test
    public void codesetLookupByCodeReturnsCorrectValue() {
        TermCode term = TERMINOLOGY_ACCESS.getTerm("ISO_3166-1", "AU", "en");
        assertNotNull(term);
    }

    @Test
    public void codesetLookupUnknownCodeReturnsNull() {
        TermCode term = TERMINOLOGY_ACCESS.getTerm("ISO_3166-1", "ZZ", "en");
        assertNull(term);
    }

    @Test
    public void rubricInSpanish() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "es", "433");
        assertNotNull(term);
        assertEquals("evento", term.getDescription());
    }

    @Test
    public void rubricInPortuguese() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "pt", "433");
        assertNotNull(term);
        assertEquals("evento", term.getDescription());
    }

    @Test
    public void rubricInJapanese() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "ja", "433");
        assertNotNull(term);
        assertEquals("イベント", term.getDescription());
    }

    @Test
    public void code532VersionLifecycleStateIsSupported() {
        List<String> unsupportedLanguages = List.of("de", "fr", "ro", "nl");
        Map<String, String> expectedValues = Map.of(
            "en", "complete",
            "es", "completo",
            "pt", "completo",
            "ja", "完了"
        );

        expectedValues.forEach((lang, rubric) -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                OpenEHRTerminologyGroupIdentifiers.VERSION_LIFECYCLE_STATE.toString(),
                lang,
                "532"
            );
            assertNotNull("Support for code is unavailable in language: " + lang, term);
            assertEquals(rubric, term.getDescription());
        });

        unsupportedLanguages.forEach(lang -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                OpenEHRTerminologyGroupIdentifiers.VERSION_LIFECYCLE_STATE.toString(),
                lang,
                "532");

            assertNull("Support was added for language: " + lang, term);
        });
    }

    @Test
    public void code532InstructionStatesIsSupported() {
        List<String> unsupportedLanguages = List.of("de", "fr", "ro", "nl");
        Map<String, String> expectedValues = Map.of(
            "en", "completed",
            "es", "completado",
            "pt", "concluído",
            "ja", "完了"
        );

        expectedValues.forEach((lang, rubric) -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                OpenEHRTerminologyGroupIdentifiers.INSTRUCTION_STATES.toString(),
                lang,
                "532"
            );
            assertNotNull("Support for code is unavailable in language: " + lang, term);
            assertEquals(rubric, term.getDescription());
        });

        unsupportedLanguages.forEach(lang -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                OpenEHRTerminologyGroupIdentifiers.INSTRUCTION_STATES.toString(),
                lang,
                "532");

            assertNull("Support was added for language: " + lang, term);
        });
    }

    @Test
    public void code532PatchedInGroupList() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTermsByOpenEHRGroup("instruction states", "en");
        TermCode code532 = terms.stream()
                .filter(t -> "532".equals(t.getCodeString()))
                .findFirst()
                .orElse(null);
        assertNotNull(code532);
        assertEquals("completed", code532.getDescription());
    }

    @Test
    public void supportedLanguages() {
        assertTrue(TERMINOLOGY_ACCESS.supportsLanguage("en"));
        assertTrue(TERMINOLOGY_ACCESS.supportsLanguage("es"));
        assertTrue(TERMINOLOGY_ACCESS.supportsLanguage("ja"));
        assertTrue(TERMINOLOGY_ACCESS.supportsLanguage("pt"));
        assertFalse(TERMINOLOGY_ACCESS.supportsLanguage("de"));
        assertFalse(TERMINOLOGY_ACCESS.supportsLanguage("fr"));
        assertFalse(TERMINOLOGY_ACCESS.supportsLanguage("ro"));
        assertFalse(TERMINOLOGY_ACCESS.supportsLanguage(null));
    }
}
