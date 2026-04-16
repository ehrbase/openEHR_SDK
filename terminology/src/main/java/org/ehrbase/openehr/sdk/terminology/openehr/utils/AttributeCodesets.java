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
package org.ehrbase.openehr.sdk.terminology.openehr.utils;

import static org.ehrbase.openehr.sdk.terminology.openehr.utils.AttributeCodesets.Entry.codesetEntry;
import static org.ehrbase.openehr.sdk.terminology.openehr.utils.AttributeCodesets.Entry.groupEntry;

import java.util.Map;
import org.ehrbase.openehr.sdk.terminology.openehr.implementation.ContainerType;
import org.ehrbase.openehr.sdk.util.SnakeCase;

/**
 * Maps RM object attributes to their corresponding openEHR terminology group or codeset entry.
 * All ids are English, matching archie's terminology structure where group ids are always English
 * regardless of language.
 */
public class AttributeCodesets {

    private static final Map<String, Entry> MAPPINGS = Map.ofEntries(
            Map.entry("lifecycle_state", groupEntry("openehr", "version lifecycle state")),
            Map.entry("category", groupEntry("openehr", "composition category")),
            Map.entry("setting", groupEntry("openehr", "setting")),
            Map.entry("current_state", groupEntry("openehr", "instruction states")),
            Map.entry("transition", groupEntry("openehr", "instruction transitions")),
            Map.entry("null_flavour", groupEntry("openehr", "null flavours")),
            Map.entry("mode", groupEntry("openehr", "participation mode")),
            Map.entry("function", groupEntry("openehr", "participation function")),
            Map.entry("relationship", groupEntry("openehr", "subject relationship")),
            Map.entry("property", groupEntry("openehr", "property")),
            Map.entry("math_function", groupEntry("openehr", "event math function")),
            Map.entry("purpose", groupEntry("openehr", "term mapping purpose")),
            Map.entry("normal_status", codesetEntry("openehr_normal_status", "normal statuses")),
            Map.entry("language", codesetEntry("ISO_639-1", "languages")),
            Map.entry("media_type", codesetEntry("IANA_media-types", "media types")),
            Map.entry("character_set", codesetEntry("IANA_character-sets", "character sets")),
            Map.entry("compression_algorithm", codesetEntry("openehr_compression_algorithm", "compression algorithms")),
            Map.entry(
                    "integrity_check_algorithm", codesetEntry("openehr_integrity_check_algorithm", "integrity check")),
            Map.entry("territory", codesetEntry("ISO_3166-1", "countries")));

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
