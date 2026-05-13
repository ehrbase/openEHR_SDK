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

import static org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets.TerminologyContainer.codeset;
import static org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets.TerminologyContainer.group;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.CHARACTER_SETS;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.COMPRESSION_ALGORITHMS;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.COUNTRIES;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.INTEGRITY_CHECK_ALGORITHMS;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.LANGUAGES;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.MEDIA_TYPES;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifier.NORMAL_STATUSES;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.COMPOSITION_CATEGORY;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.EVENT_MATH_FUNCTION;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.INSTRUCTION_STATES;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.INSTRUCTION_TRANSITIONS;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.NULL_FLAVOURS;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.PARTICIPATION_FUNCTION;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.PARTICIPATION_MODE;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.PROPERTY;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.SETTING;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.SUBJECT_RELATIONSHIP;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.TERM_MAPPING_PURPOSE;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifier.VERSION_LIFECYCLE_STATE;

import java.util.Map;
import org.ehrbase.openehr.sdk.util.SnakeCase;

/**
 * Maps RM object attributes to their corresponding openEHR terminology group or codeset entry.
 * All ids are English, matching archie's terminology structure where group ids are always English
 * regardless of language.
 */
public class AttributeCodesets {

    private static final String OPENEHR = "openehr";

    private static final Map<String, TerminologyContainer> MAPPINGS = Map.ofEntries(
            group("lifecycle_state", VERSION_LIFECYCLE_STATE),
            group("category", COMPOSITION_CATEGORY),
            group("setting", SETTING),
            group("current_state", INSTRUCTION_STATES),
            group("transition", INSTRUCTION_TRANSITIONS),
            group("null_flavour", NULL_FLAVOURS),
            group("mode", PARTICIPATION_MODE),
            group("function", PARTICIPATION_FUNCTION),
            group("relationship", SUBJECT_RELATIONSHIP),
            group("property", PROPERTY),
            group("math_function", EVENT_MATH_FUNCTION),
            group("purpose", TERM_MAPPING_PURPOSE),
            codeset("language", LANGUAGES, "ISO_639-1"),
            codeset("normal_status", NORMAL_STATUSES, "openehr_normal_status"),
            codeset("media_type", MEDIA_TYPES, "IANA_media-types"),
            codeset("character_set", CHARACTER_SETS, "IANA_character-sets"),
            codeset("compression_algorithm", COMPRESSION_ALGORITHMS, "openehr_compression_algorithm"),
            codeset("territory", COUNTRIES, "ISO_3166-1"),
            codeset("integrity_check_algorithm", INTEGRITY_CHECK_ALGORITHMS, "openehr_integrity_check_algorithm"));

    /**
     * Returns the mapping entry for the given RM attribute name (snake_case).
     *
     * @param attribute the RM attribute name, e.g. "category", "lifecycle_state"
     * @return the entry, or null if no mapping exists
     */
    public static TerminologyContainer get(String attribute) {
        if (attribute == null) {
            return null;
        }
        var updatedAttribute = new SnakeCase(attribute).camelToSnake();
        return MAPPINGS.get(updatedAttribute);
    }

    public record TerminologyContainer(String terminology, ContainerType container, String id) {
        static Map.Entry<String, TerminologyContainer> group(String attribute, OpenEHRTerminologyGroupIdentifier id) {
            return Map.entry(attribute, new TerminologyContainer(OPENEHR, ContainerType.GROUP, id.getValue()));
        }

        static Map.Entry<String, TerminologyContainer> codeset(
                String attribute, OpenEHRCodeSetIdentifier id, String terminology) {
            return Map.entry(attribute, new TerminologyContainer(terminology, ContainerType.CODESET, id.toString()));
        }
    }
}
