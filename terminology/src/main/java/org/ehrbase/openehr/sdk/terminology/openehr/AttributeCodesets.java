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

import static org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets.Entry.codesetEntry;
import static org.ehrbase.openehr.sdk.terminology.openehr.AttributeCodesets.Entry.groupEntry;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRCodeSetIdentifiers.*;
import static org.ehrbase.openehr.sdk.terminology.openehr.OpenEHRTerminologyGroupIdentifiers.*;

import java.util.Map;
import org.ehrbase.openehr.sdk.util.SnakeCase;

/**
 * Maps RM object attributes to their corresponding openEHR terminology group or codeset entry.
 * All ids are English, matching archie's terminology structure where group ids are always English
 * regardless of language.
 */
public class AttributeCodesets {

    private static final String OPENEHR = "openehr";

    private static final Map<String, Entry> MAPPINGS = Map.ofEntries(
            Map.entry("lifecycle_state", groupEntry(OPENEHR, VERSION_LIFECYCLE_STATE.toString())),
            Map.entry("category", groupEntry(OPENEHR, COMPOSITION_CATEGORY.toString())),
            Map.entry("setting", groupEntry(OPENEHR, SETTING.toString())),
            Map.entry("current_state", groupEntry(OPENEHR, INSTRUCTION_STATES.toString())),
            Map.entry("transition", groupEntry(OPENEHR, INSTRUCTION_TRANSITIONS.toString())),
            Map.entry("null_flavour", groupEntry(OPENEHR, NULL_FLAVOURS.toString())),
            Map.entry("mode", groupEntry(OPENEHR, PARTICIPATION_MODE.toString())),
            Map.entry("function", groupEntry(OPENEHR, PARTICIPATION_FUNCTION.toString())),
            Map.entry("relationship", groupEntry(OPENEHR, SUBJECT_RELATIONSHIP.toString())),
            Map.entry("property", groupEntry(OPENEHR, PROPERTY.toString())),
            Map.entry("math_function", groupEntry(OPENEHR, EVENT_MATH_FUNCTION.toString())),
            Map.entry("purpose", groupEntry(OPENEHR, TERM_MAPPING_PURPOSE.toString())),
            Map.entry("normal_status", codesetEntry("openehr_normal_status", NORMAL_STATUSES.toString())),
            Map.entry("language", codesetEntry("ISO_639-1", LANGUAGES.toString())),
            Map.entry("media_type", codesetEntry("IANA_media-types", MEDIA_TYPES.toString())),
            Map.entry("character_set", codesetEntry("IANA_character-sets", CHARACTER_SETS.toString())),
            Map.entry(
                    "compression_algorithm",
                    codesetEntry("openehr_compression_algorithm", COMPRESSION_ALGORITHMS.toString())),
            Map.entry(
                    "integrity_check_algorithm",
                    codesetEntry("openehr_integrity_check_algorithm", INTEGRITY_CHECK_ALGORITHMS.toString())),
            Map.entry("territory", codesetEntry("ISO_3166-1", COUNTRIES.toString())));

    /**
     * Returns the mapping entry for the given RM attribute name (snake_case).
     *
     * @param attribute the RM attribute name, e.g. "category", "lifecycle_state"
     * @return the entry, or null if no mapping exists
     */
    public static Entry get(String attribute) {
        if (attribute == null) {
            return null;
        }
        var updatedAttribute = new SnakeCase(attribute).camelToSnake();
        return MAPPINGS.get(updatedAttribute);
    }

    public record Entry(String terminology, ContainerType container, String id) {
        static Entry groupEntry(String terminology, String id) {
            return new Entry(terminology, ContainerType.GROUP, id);
        }

        static Entry codesetEntry(String terminology, String id) {
            return new Entry(terminology, ContainerType.CODESET, id);
        }
    }
}
