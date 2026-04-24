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

import static com.nedap.archie.rmutil.InvariantUtil.ENGLISH;
import static org.assertj.core.api.Assertions.assertThat;

import com.nedap.archie.terminology.OpenEHRTerminologyAccess;
import com.nedap.archie.terminology.TermCode;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleTerminologyAccessTest {

    private static final SimpleTerminologyAccess TERMINOLOGY_ACCESS = SimpleTerminologyAccess.getInstance();

    @Test
    void groupLookupReturnsTerms() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTermsByOpenEHRGroup("setting", "en");
        assertThat(terms).hasSize(14);
    }

    @Test
    void groupLookupByCodeReturnsCorrectRubric() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("setting", "en", "237");
        assertThat(term).extracting(TermCode::getDescription).isEqualTo("nursing home care");
    }

    @Test
    void groupLookupUnknownCodeReturnsNull() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("setting", "en", "240");
        assertThat(term).isNull();
    }

    @Test
    void codesetLookupReturnsTerms() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTerms("ISO_3166-1", "en");
        assertThat(terms).hasSizeGreaterThan(200);
    }

    @Test
    void codesetLookupByCodeReturnsCorrectValue() {
        TermCode term = TERMINOLOGY_ACCESS.getTerm("ISO_3166-1", "AU", "en");
        assertThat(term).isNotNull();
    }

    @Test
    void codesetLookupUnknownCodeReturnsNull() {
        TermCode term = TERMINOLOGY_ACCESS.getTerm("ISO_3166-1", "ZZ", "en");
        assertThat(term).isNull();
    }

    @Test
    void rubricInSpanish() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "es", "433");
        assertThat(term).extracting(TermCode::getDescription).isEqualTo("evento");
    }

    @Test
    void rubricInPortuguese() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "pt", "433");
        assertThat(term).extracting(TermCode::getDescription).isEqualTo("evento");
    }

    @Test
    void rubricInJapanese() {
        TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup("composition category", "ja", "433");
        assertThat(term).extracting(TermCode::getDescription).isEqualTo("イベント");
    }

    @Test
    void code532VersionLifecycleStateIsSupported() {
        Map<String, String> expectedRubrics = Map.of(
                "en", "complete",
                "es", "completo",
                "pt", "completo",
                "ja", "完了");
        List<String> languageCodes = List.of("en", "es", "ja", "pt");

        languageCodes.forEach(lang -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                    OpenEHRTerminologyGroupIdentifiers.VERSION_LIFECYCLE_STATE.toString(), lang, "532");

            assertThat(expectedRubrics).containsKey(lang);
            assertThat(term).extracting(TermCode::getDescription).isEqualTo(expectedRubrics.get(lang));
        });
    }

    @Test
    void code532InstructionStatesIsSupported() {
        List<String> languageCodes = List.of("en", "es", "ja", "pt");
        assertThat(languageCodes).containsAll(SimpleTerminologyAccess.CODE_532_INSTRUCTION_RUBRICS.keySet());

        languageCodes.forEach(lang -> {
            TermCode term = TERMINOLOGY_ACCESS.getTermByOpenEHRGroup(
                    OpenEHRTerminologyGroupIdentifiers.INSTRUCTION_STATES.toString(), lang, "532");
            assertThat(term)
                    .withFailMessage("Support for code is unavailable in language: %s", lang)
                    .isNotNull()
                    .extracting(TermCode::getDescription)
                    .isEqualTo(SimpleTerminologyAccess.CODE_532_INSTRUCTION_RUBRICS.get(lang));
        });
    }

    @Test
    void code532PatchedInGroupList() {
        List<TermCode> terms = TERMINOLOGY_ACCESS.getTermsByOpenEHRGroup("instruction states", "en");
        var code532 =
                terms.stream().filter(t -> "532".equals(t.getCodeString())).findFirst();

        assertThat(code532).map(TermCode::getDescription).hasValue("completed");
    }

    @Test
    void supportsLanguage() {
        Stream.of("en", "es", "ja", "pt")
                .forEach(l -> Assertions.assertThat(TERMINOLOGY_ACCESS.supportsLanguage(l))
                        .isTrue());

        assertThat(SimpleTerminologyAccess.CODE_532_INSTRUCTION_RUBRICS).hasSize(4);

        Stream.of("de", "fr", "ro", null)
                .forEach(l -> Assertions.assertThat(TERMINOLOGY_ACCESS.supportsLanguage(l))
                        .isFalse());

        OpenEHRTerminologyAccess archieOpenehrTerminology = OpenEHRTerminologyAccess.getInstance();
        List<TermCode> languages = archieOpenehrTerminology.getTerms("ISO_639-1", ENGLISH);

        IntSummaryStatistics supportStats = languages.stream()
                .mapToInt(langTerm -> {
                    String langCode = langTerm.getCodeString();

                    boolean supported = TERMINOLOGY_ACCESS.supportsLanguage(langCode);

                    TermCode attestationReasonSigned = archieOpenehrTerminology.getTermByOpenEHRGroup(
                            OpenEHRTerminologyGroupIdentifiers.ATTESTATION_REASON.getValue(), langCode, "240");
                    boolean supportedByArchie = attestationReasonSigned != null;

                    assertThat(supported)
                            .withFailMessage(
                                    "Language %s is%s supported, but should%s",
                                    langTerm, supported ? " " : " not", supportedByArchie ? "" : " not")
                            .isEqualTo(supportedByArchie);
                    return supported ? 1 : 0;
                })
                .summaryStatistics();

        assertThat(supportStats.getSum()).isEqualTo(SimpleTerminologyAccess.CODE_532_INSTRUCTION_RUBRICS.size());
        assertThat(supportStats.getCount()).isGreaterThan(200);
    }
}
