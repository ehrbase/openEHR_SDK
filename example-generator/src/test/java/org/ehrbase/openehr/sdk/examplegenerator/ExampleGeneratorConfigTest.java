/*
 * Copyright (c) 2022. vitasystems GmbH and Hannover Medical School.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.ehrbase.openehr.sdk.examplegenerator;

import com.nedap.archie.rminfo.ArchieRMInfoLookup;
import com.nedap.archie.rminfo.RMTypeInfo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
@Disabled
class ExampleGeneratorConfigTest {

    private static ArchieRMInfoLookup ARCHIE_RM_INFO_LOOKUP = ArchieRMInfoLookup.getInstance();

    @Test
    void testUnhandledDataValues() {

        Set<String> handled = ExampleGeneratorConfig.Handlers.getHandledRmTypes();

        RMTypeInfo dataValue = ARCHIE_RM_INFO_LOOKUP.getTypeInfo("DATA_VALUE");

        Set<String> missing = ARCHIE_RM_INFO_LOOKUP.getAllTypes().stream()
                .filter(t -> t.isDescendantOrEqual(dataValue))
                .filter(t -> ! handled.contains(t.getRmName()))
                .map(RMTypeInfo::getRmName)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<String> knownMissing = Set.of(
                "DATA_VALUE",
                "DV_ABSOLUTE_QUANTITY",
                "DV_AMOUNT",
                "DV_ENCAPSULATED",
                "DV_GENERAL_TIME_SPECIFICATION",
                "DV_INTERVAL",
                "DV_ORDERED",
                "DV_PARAGRAPH",
                "DV_PERIODIC_TIME_SPECIFICATION",
                "DV_QUANTIFIED",
                "DV_SCALE",
                "DV_STATE",
                "DV_TEMPORAL",
                "DV_TIME_SPECIFICATION");

        assertThat(missing).isEqualTo(knownMissing);
    }

    @Test
    void testUnhandledDataValuesCascaded() {

        Set<RMTypeInfo> handled = ExampleGeneratorConfig.Handlers.getObjectHandlers().keySet().stream()
                .map(ARCHIE_RM_INFO_LOOKUP::getTypeInfo)
                .collect(Collectors.toSet());

        RMTypeInfo dataValue = ARCHIE_RM_INFO_LOOKUP.getTypeInfo("DATA_VALUE");

        Set<String> missing = ARCHIE_RM_INFO_LOOKUP.getAllTypes().stream()
                .filter(t -> t.isDescendantOrEqual(dataValue))
                .filter(t -> {
                    if (Modifier.isAbstract(t.getJavaClass().getModifiers())) {
                        return handled.stream().noneMatch(h -> h.isDescendantOrEqual(t));
                    } else {
                        return !handled.contains(t);
                    }
                })
                .map(RMTypeInfo::getRmName)
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));

        Set<String> knownMissing = Set.of(
                //not yet supported by ehrbase
                "DV_GENERAL_TIME_SPECIFICATION",
                //handled in the walker
                "DV_INTERVAL",
                //deprecated
                "DV_PARAGRAPH",
                //not yet supported by ehrbase
                "DV_PERIODIC_TIME_SPECIFICATION",
                "DV_SCALE",
                //not yet supported by ehrbase
                "DV_STATE",
                //not yet supported by ehrbase
                "DV_TIME_SPECIFICATION");

        assertThat(missing).isEqualTo(knownMissing);
    }
}