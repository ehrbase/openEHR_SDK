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
@Archetype("openEHR-EHR-OBSERVATION.sepsis_screening_tool.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.587778900+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class SepsisScreeningObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Risk factors for sepsis
     * Description: Used to record details of suspicion of any serious illness from the sepsis screening tool.
     * Comment: Q1: ARE THERE CLUES THAT THE PATIENT MAY BE SERIOUSLY ILL?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]")
    private List<SepsisScreeningRiskFactorsForSepsisElement> riskFactorsForSepsis;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Likely source of infection
     * Description: Used to record details of source of any infection from the sepsis screening tool.
     * Comment: Q2: COULD THIS BE DUE TO AN INFECTION?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0011]")
    private List<SepsisScreeningLikelySourceOfInfectionElement> likelySourceOfInfection;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Red flag (acute)
     * Description: Used to record details of any red flag indicators from the sepsis screening tool.
     * Comment: Q3: ANY RED FLAG PRESENT?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0058]")
    private List<SepsisScreeningRedFlagAcuteElement> redFlagAcute;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Amber flag (acute)
     * Description: Used to record details of any amber flag indicators from the sepsis screening tool.
     * Comment: Q4: ANY AMBER FLAG PRESENT?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0083]")
    private List<SepsisScreeningAmberFlagAcuteElement> amberFlagAcute;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/999 Flag
     * Description: *
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0036]")
    private List<SepsisScreening999FlagElement> N999Flag;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Extension
     * Description: *
     */
    @Path("/protocol[at0004]/items[at0005]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setRiskFactorsForSepsis(List<SepsisScreeningRiskFactorsForSepsisElement> riskFactorsForSepsis) {
        this.riskFactorsForSepsis = riskFactorsForSepsis;
    }

    public List<SepsisScreeningRiskFactorsForSepsisElement> getRiskFactorsForSepsis() {
        return this.riskFactorsForSepsis;
    }

    public void setLikelySourceOfInfection(
            List<SepsisScreeningLikelySourceOfInfectionElement> likelySourceOfInfection) {
        this.likelySourceOfInfection = likelySourceOfInfection;
    }

    public List<SepsisScreeningLikelySourceOfInfectionElement> getLikelySourceOfInfection() {
        return this.likelySourceOfInfection;
    }

    public void setRedFlagAcute(List<SepsisScreeningRedFlagAcuteElement> redFlagAcute) {
        this.redFlagAcute = redFlagAcute;
    }

    public List<SepsisScreeningRedFlagAcuteElement> getRedFlagAcute() {
        return this.redFlagAcute;
    }

    public void setAmberFlagAcute(List<SepsisScreeningAmberFlagAcuteElement> amberFlagAcute) {
        this.amberFlagAcute = amberFlagAcute;
    }

    public List<SepsisScreeningAmberFlagAcuteElement> getAmberFlagAcute() {
        return this.amberFlagAcute;
    }

    public void setN999Flag(List<SepsisScreening999FlagElement> N999Flag) {
        this.N999Flag = N999Flag;
    }

    public List<SepsisScreening999FlagElement> getN999Flag() {
        return this.N999Flag;
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
