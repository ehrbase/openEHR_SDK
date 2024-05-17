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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.story.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.422779+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StoryHistoryObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Situation/Story/History/Any event/Soft signs
     * Description: Narrative description of the story or clinical history for the subject of care.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Soft signs']")
    private List<StoryHistorySoftSignsElement> softSigns;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/Any event/Notes
     * Description: Narrative description of the story or clinical history for the subject of care.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004 and name/value='Notes']/value|value")
    private String notesValue;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/Any event/Structured detail
     * Description: Structured detail about the individual's story or patient's history.
     * Comment: For example: a specific symptom such as nausea or pain; an event such as a fall off a bicycle; or an issue such as a desire to quit using tobacco.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
    private List<Cluster> structuredDetail;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/Any event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/Extension
     * Description: Additional information required to capture local content or to align with other reference models/formalisms.
     * Comment: For example: Local information requirements or additional metadata to align with FHIR or CIMI equivalents.
     */
    @Path("/protocol[at0007]/items[at0008]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Situation/Story/History/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setSoftSigns(List<StoryHistorySoftSignsElement> softSigns) {
        this.softSigns = softSigns;
    }

    public List<StoryHistorySoftSignsElement> getSoftSigns() {
        return this.softSigns;
    }

    public void setNotesValue(String notesValue) {
        this.notesValue = notesValue;
    }

    public String getNotesValue() {
        return this.notesValue;
    }

    public void setStructuredDetail(List<Cluster> structuredDetail) {
        this.structuredDetail = structuredDetail;
    }

    public List<Cluster> getStructuredDetail() {
        return this.structuredDetail;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setExtension(List<Cluster> extension) {
        this.extension = extension;
    }

    public List<Cluster> getExtension() {
        return this.extension;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
