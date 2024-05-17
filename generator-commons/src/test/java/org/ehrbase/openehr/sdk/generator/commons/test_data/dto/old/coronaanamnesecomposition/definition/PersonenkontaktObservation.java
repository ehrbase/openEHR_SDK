/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.exposure_assessment.v0")
public class PersonenkontaktObservation {
    @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0055]/value|value")
    private String kommentarValue;

    @Path("/protocol[at0004]/items[at0056]")
    private List<Cluster> erweiterung;

    @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0045]/value|value")
    private String exposureEnValue;

    @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0044]/items[at0046]/value|defining_code")
    private VorhandenseinDefiningcodeSpecificExposureEn vorhandenseinDefiningcode;

    @Path("/language")
    private Language language;

    @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0043]/value|value")
    private String agentEnValue;

    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    @Path("/subject")
    private PartyProxy subject;

    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    @Path("/data[at0001]/events[at0002]/data[at0042]/items[at0057]/value|defining_code")
    private PresenceOfAnyExposureEnDefiningcode presenceOfAnyExposureEnDefiningcode;

    public void setKommentarValue(String kommentarValue) {
        this.kommentarValue = kommentarValue;
    }

    public String getKommentarValue() {
        return this.kommentarValue;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setExposureEnValue(String exposureEnValue) {
        this.exposureEnValue = exposureEnValue;
    }

    public String getExposureEnValue() {
        return this.exposureEnValue;
    }

    public void setVorhandenseinDefiningcode(VorhandenseinDefiningcodeSpecificExposureEn vorhandenseinDefiningcode) {
        this.vorhandenseinDefiningcode = vorhandenseinDefiningcode;
    }

    public VorhandenseinDefiningcodeSpecificExposureEn getVorhandenseinDefiningcode() {
        return this.vorhandenseinDefiningcode;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Language getLanguage() {
        return this.language;
    }

    public void setAgentEnValue(String agentEnValue) {
        this.agentEnValue = agentEnValue;
    }

    public String getAgentEnValue() {
        return this.agentEnValue;
    }

    public void setTimeValue(TemporalAccessor timeValue) {
        this.timeValue = timeValue;
    }

    public TemporalAccessor getTimeValue() {
        return this.timeValue;
    }

    public void setSubject(PartyProxy subject) {
        this.subject = subject;
    }

    public PartyProxy getSubject() {
        return this.subject;
    }

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setPresenceOfAnyExposureEnDefiningcode(
            PresenceOfAnyExposureEnDefiningcode presenceOfAnyExposureEnDefiningcode) {
        this.presenceOfAnyExposureEnDefiningcode = presenceOfAnyExposureEnDefiningcode;
    }

    public PresenceOfAnyExposureEnDefiningcode getPresenceOfAnyExposureEnDefiningcode() {
        return this.presenceOfAnyExposureEnDefiningcode;
    }
}
