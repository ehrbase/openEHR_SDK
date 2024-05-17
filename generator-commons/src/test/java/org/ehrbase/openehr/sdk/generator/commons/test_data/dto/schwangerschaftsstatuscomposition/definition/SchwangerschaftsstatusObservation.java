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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.schwangerschaftsstatuscomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.pregnancy_status.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.301026600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SchwangerschaftsstatusObservation implements EntryEntity {
    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/Beliebiges Ereignis/Status
     * Description: Liegt eine Schwangerschaft vor?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]/value|defining_code")
    private StatusDefiningCode2 statusDefiningCode;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/Beliebiges Ereignis/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/Erweiterungen
     * Description: Zusätzliche Informationen, die zur Erfassung lokaler Inhalte oder zur Anpassung an andere Referenzmodelle/Formalismen erforderlich sind.
     * Comment: Zum Beispiel: Lokale Informationsanforderungen oder zusätzliche Metadaten, um Verknüpfungen mit FHIR herzustellen.
     */
    @Path("/protocol[at0021]/items[at0022]")
    private List<Cluster> erweiterungen;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Schwangerschaftsstatus/Schwangerschaftsstatus/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode) {
        this.statusDefiningCode = statusDefiningCode;
    }

    public StatusDefiningCode2 getStatusDefiningCode() {
        return this.statusDefiningCode;
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

    public void setErweiterungen(List<Cluster> erweiterungen) {
        this.erweiterungen = erweiterungen;
    }

    public List<Cluster> getErweiterungen() {
        return this.erweiterungen;
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
