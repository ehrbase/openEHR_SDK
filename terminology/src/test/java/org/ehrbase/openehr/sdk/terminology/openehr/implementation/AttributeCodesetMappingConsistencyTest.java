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

import static java.util.Map.entry;
import static org.junit.Assert.*;

import com.nedap.archie.rm.datatypes.CodePhrase;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyAccess;
import org.ehrbase.openehr.sdk.terminology.openehr.TerminologyInterface;
import org.junit.Before;
import org.junit.Test;

public class AttributeCodesetMappingConsistencyTest {

    private AttributeCodesetMapping attributeCodesetMapping;
    private TerminologyInterface terminologyEn;
    private TerminologyInterface terminologyEs;
    private TerminologyInterface terminologyJa;
    private TerminologyInterface terminologyPt;

    private static final List<String> OPENEHR_GROUP_ATTRIBUTES = List.of(
            "lifecycle_state",
            "category",
            "setting",
            "current_state",
            "transition",
            "null_flavour",
            "mode",
            "function",
            "relationship",
            "property",
            "math_function",
            "purpose");

    private static final Map<String, String> EXPECTED_EN_GROUP_NAMES = Map.ofEntries(
            entry("lifecycle_state", "version lifecycle state"),
            entry("category", "composition category"),
            entry("setting", "setting"),
            entry("current_state", "instruction states"),
            entry("transition", "instruction transitions"),
            entry("null_flavour", "null flavours"),
            entry("mode", "participation mode"),
            entry("function", "participation function"),
            entry("relationship", "subject relationship"),
            entry("property", "property"),
            entry("math_function", "event math function"),
            entry("purpose", "term mapping purpose"));

    private static final Map<String, String> EXPECTED_JA_GROUP_NAMES = Map.ofEntries(
            entry("lifecycle_state", "version lifecycle state"),
            entry("category", "コンポジションカテゴリ"),
            entry("setting", "setting"),
            entry("current_state", "instruction states"),
            entry("transition", "instruction transitions"),
            entry("null_flavour", "null flavours"),
            entry("mode", "participation mode"),
            entry("function", "participation function"),
            entry("relationship", "subject relationship"),
            entry("property", "プロパティ"),
            entry("math_function", "event math function"),
            entry("purpose", "term mapping purpose"));

    private static final Map<String, String> EXPECTED_PT_GROUP_NAMES = Map.ofEntries(
            entry("lifecycle_state", "estado de ciclo de vida de versão"),
            entry("category", "categoria de composição"),
            entry("setting", "configuração"),
            entry("current_state", "estados de instrução"),
            entry("transition", "transições de instrução"),
            entry("null_flavour", "null flavours"), // not translated in archie
            entry("mode", "modo de participação"),
            entry("function", "função de participação"),
            entry("relationship", "relacionamento de sujeito"),
            entry("property", "propriedade"),
            entry("math_function", "função matemática de evento"),
            entry("purpose", "propósito de mapeamento de termo"));

    @Before
    public void setUp() {
        attributeCodesetMapping = AttributeCodesetMapping.getInstance();
        terminologyEn = new SimpleTerminologyInterface("en");
        terminologyEs = new SimpleTerminologyInterface("es");
        terminologyJa = new SimpleTerminologyInterface("ja");
        terminologyPt = new SimpleTerminologyInterface("pt");
    }

    /**
     * English: archie uses EN group names. SDK must match exactly.
     */
    @Test
    public void testEnglishGroupNames() {
        TerminologyAccess terminology = terminologyEn.terminology("openehr");

        for (String attribute : OPENEHR_GROUP_ATTRIBUTES) {
            String sdkName = attributeCodesetMapping.actualAttributeId("openehr", attribute, "en");
            String expected = EXPECTED_EN_GROUP_NAMES.get(attribute);
            Set<CodePhrase> codes = terminology.codesForGroupId(sdkName);

            assertEquals("Mismatch for " + attribute, expected, sdkName);
            assertNotNull("Group not found: " + sdkName, codes);
            assertFalse("Group empty: " + sdkName, codes.isEmpty());
        }
    }

    /**
     * Spanish: archie -> EN group names, ES rubrics
     * SDK must have ES entries pointing to EN group names
     */
    @Test
    public void testSpanishGroupNames() {
        TerminologyAccess terminology = terminologyEs.terminology("openehr");

        for (String attribute : OPENEHR_GROUP_ATTRIBUTES) {
            assertTrue(
                    "Spanish not localized: " + attribute,
                    attributeCodesetMapping.isLocalizedAttribute("openehr", attribute, "es"));

            String sdkName = attributeCodesetMapping.actualAttributeId("openehr", attribute, "es");
            String expected = EXPECTED_EN_GROUP_NAMES.get(attribute);
            Set<CodePhrase> codes = terminology.codesForGroupId(sdkName);

            assertEquals("Spanish should use English name for " + attribute, expected, sdkName);
            assertNotNull("Group not found: " + sdkName, codes);
        }

        assertEquals("evento", terminology.rubricForCode("433", "es"));
    }

    /**
     * Japanese: archie -> mixed (JA+EN) group names
     * SDK must match archie's mixes
     */
    @Test
    public void testJapaneseGroupNames() {
        TerminologyAccess terminology = terminologyJa.terminology("openehr");

        for (String attribute : OPENEHR_GROUP_ATTRIBUTES) {
            assertTrue(
                    "Japanese not localized: " + attribute,
                    attributeCodesetMapping.isLocalizedAttribute("openehr", attribute, "ja"));

            String sdkName = attributeCodesetMapping.actualAttributeId("openehr", attribute, "ja");
            String expected = EXPECTED_JA_GROUP_NAMES.get(attribute);
            Set<CodePhrase> codes = terminology.codesForGroupName(sdkName, "ja");

            assertEquals("Mismatch for " + attribute, expected, sdkName);
            assertNotNull("Group not found: " + sdkName, codes);
        }
    }

    /**
     * Portuguese: archie -> PT group names.
     * SDK has PT entries with localized names.
     */
    @Test
    public void testPortugueseGroupNames() {
        TerminologyAccess terminology = terminologyPt.terminology("openehr");

        for (String attribute : OPENEHR_GROUP_ATTRIBUTES) {
            assertTrue(
                    "Portuguese not localized: " + attribute,
                    attributeCodesetMapping.isLocalizedAttribute("openehr", attribute, "pt"));

            String sdkName = attributeCodesetMapping.actualAttributeId("openehr", attribute, "pt");
            String expected = EXPECTED_PT_GROUP_NAMES.get(attribute);
            Set<CodePhrase> codes = terminology.codesForGroupName(sdkName, "pt");

            assertEquals("Mismatch for " + attribute, expected, sdkName);
            assertNotNull("Group not found: " + sdkName, codes);
        }
    }
}
