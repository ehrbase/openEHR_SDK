/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Choice;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:10.076492400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class KorpergewichtObservation implements EntryEntity {
    /**
     * Path: Bericht/Körpergewicht/*Birth(en)
     * Description: *Usually the first weight, measured soon after birth. This event will only
     *                         be used once per health record
     *                         .(en)
     *
     */
    @Path("/data[at0002]/events[at0026]")
    private KorpergewichtBirthEnPointEvent birthEn;

    /**
     * Path: Bericht/Körpergewicht/origin
     */
    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: Bericht/Körpergewicht/Gerät
     * Description: Details über die benutzte Waage.
     */
    @Path("/protocol[at0015]/items[at0020]")
    private Cluster gerat;

    /**
     * Path: Bericht/Körpergewicht/*Extension(en)
     * Description: *Additional information required to capture local content or to align with
     *                         other reference models/formalisms.(en)
     *
     * Comment: *For example: local information requirements or additional metadata to align
     *                         with FHIR or CIMI equivalents.(en)
     *
     */
    @Path("/protocol[at0015]/items[at0027]")
    private List<Cluster> extensionEn;

    /**
     * Path: Bericht/Körpergewicht/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Bericht/Körpergewicht/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/Körpergewicht/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/Körpergewicht/*Any event(en)
     * Description: *Default, unspecified point in time or interval event which may be
     *                         explicitly defined in a template or at run-time.(en)
     *
     */
    @Path("/data[at0002]/events[at0003]")
    @Choice
    private List<KorpergewichtAnyEventEnChoice> anyEventEn;

    public void setBirthEn(KorpergewichtBirthEnPointEvent birthEn) {
        this.birthEn = birthEn;
    }

    public KorpergewichtBirthEnPointEvent getBirthEn() {
        return this.birthEn;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setGerat(Cluster gerat) {
        this.gerat = gerat;
    }

    public Cluster getGerat() {
        return this.gerat;
    }

    public void setExtensionEn(List<Cluster> extensionEn) {
        this.extensionEn = extensionEn;
    }

    public List<Cluster> getExtensionEn() {
        return this.extensionEn;
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

    public void setAnyEventEn(List<KorpergewichtAnyEventEnChoice> anyEventEn) {
        this.anyEventEn = anyEventEn;
    }

    public List<KorpergewichtAnyEventEnChoice> getAnyEventEn() {
        return this.anyEventEn;
    }
}
