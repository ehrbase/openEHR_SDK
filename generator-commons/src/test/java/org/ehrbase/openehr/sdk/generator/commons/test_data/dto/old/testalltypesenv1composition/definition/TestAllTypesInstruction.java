/*
 * Copyright (c) 2022 vitasystems GmbH.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datastructures.ItemStructure;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-INSTRUCTION.test_all_types.v1")
public class TestAllTypesInstruction {
    @Path("/narrative|value")
    private String narrativeValue;

    @Path("/language")
    private Language language;

    @Path("/activities[at0001]/description")
    private ItemStructure description;

    @Path("/activities[at0001]/description[at0002]/items[at0003]/value|value")
    private Temporal partialDateValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/activities[at0001]/action_archetype_id")
    private String currentActivity;

    @Path("/activities[at0001]/description[at0002]/items[at0004]/value|value")
    private TemporalAccessor partialDatetimeValue;

    public void setNarrativeValue(String narrativeValue) {
        this.narrativeValue = narrativeValue;
    }

    public String getNarrativeValue() {
        return this.narrativeValue;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setDescription(ItemStructure description) {
        this.description = description;
    }

    public ItemStructure getDescription() {
        return this.description;
    }

    public void setPartialDateValue(Temporal partialDateValue) {
        this.partialDateValue = partialDateValue;
    }

    public Temporal getPartialDateValue() {
        return this.partialDateValue;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getCurrentActivity() {
        return this.currentActivity;
    }

    public void setPartialDatetimeValue(TemporalAccessor partialDatetimeValue) {
        this.partialDatetimeValue = partialDatetimeValue;
    }

    public TemporalAccessor getPartialDatetimeValue() {
        return this.partialDatetimeValue;
    }
}
