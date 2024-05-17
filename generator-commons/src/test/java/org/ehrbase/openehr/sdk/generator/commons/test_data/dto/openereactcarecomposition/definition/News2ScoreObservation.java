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
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
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
@Archetype("openEHR-EHR-OBSERVATION.news2.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.767780200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class News2ScoreObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Respiration rate
     * Description: Category for the respiratory rate measurement.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
    private DvOrdinal respirationRate;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/SpO₂ Scale 1
     * Description: Category for the oxygen saturation measurement.
     * Comment: 'SpO₂ Scale 1' and 'SpO₂ Scale 2' are variations of the same physiological variable to be used in different clinical scenarios. Scale 1 will be used for most patients, with the exception of patients with an oxygen saturation target range of 88–92%, such as patients with hypercapnic respiratory failure.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0029]/value")
    private DvOrdinal spoScale1;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/SpO₂ Scale 2
     * Description: Category for the oxygen saturation measurement in patients with a target oxygen saturation range of 88–92%.
     * Comment: 'SpO₂ Scale 1' and 'SpO₂ Scale 2' are variations of the same physiological variable to be used in different clinical scenarios. Scale 2 will be used only in patients with an oxygen saturation target range of 88–92%, such as patients with hypercapnic respiratory failure. Otherwise use Scale 1.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0047]/value")
    private DvOrdinal spoScale2;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Air or oxygen?
     * Description: Is the patient receiving supplemental oxygen?
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0034]/value")
    private DvOrdinal airOrOxygen;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Systolic blood pressure
     * Description: Category for the systolic blood pressure measurement.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value")
    private DvOrdinal systolicBloodPressure;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Pulse
     * Description: Category for the pulse measurement.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value")
    private DvOrdinal pulse;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Consciousness
     * Description: Category for the observed conscious state, using the ACVPU scale.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0008]/value")
    private DvOrdinal consciousness;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Temperature
     * Description: Range category for the body temperature measurement.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0007]/value")
    private DvOrdinal temperature;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Total score
     * Description: The sum of points assigned for each of the component variables.
     * Comment: The total score may be generated at run-time.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0028]/value|magnitude")
    private Long totalScoreMagnitude;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/Clinical risk category
     * Description: Overall category representing the urgency and scale of the clinical response required in response to the physiological variables.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0056]/value|defining_code")
    private ClinicalRiskCategoryDefiningCode clinicalRiskCategoryDefiningCode;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Any point in time event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/Extension
     * Description: Additional information required to extend the model with local content or to align with other reference models/formalisms.
     * Comment: For example: local information requirements; or additional metadata to align with FHIR.
     */
    @Path("/protocol[at0045]/items[at0046]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/NEWS2 Score/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setRespirationRate(DvOrdinal respirationRate) {
        this.respirationRate = respirationRate;
    }

    public DvOrdinal getRespirationRate() {
        return this.respirationRate;
    }

    public void setSpoScale1(DvOrdinal spoScale1) {
        this.spoScale1 = spoScale1;
    }

    public DvOrdinal getSpoScale1() {
        return this.spoScale1;
    }

    public void setSpoScale2(DvOrdinal spoScale2) {
        this.spoScale2 = spoScale2;
    }

    public DvOrdinal getSpoScale2() {
        return this.spoScale2;
    }

    public void setAirOrOxygen(DvOrdinal airOrOxygen) {
        this.airOrOxygen = airOrOxygen;
    }

    public DvOrdinal getAirOrOxygen() {
        return this.airOrOxygen;
    }

    public void setSystolicBloodPressure(DvOrdinal systolicBloodPressure) {
        this.systolicBloodPressure = systolicBloodPressure;
    }

    public DvOrdinal getSystolicBloodPressure() {
        return this.systolicBloodPressure;
    }

    public void setPulse(DvOrdinal pulse) {
        this.pulse = pulse;
    }

    public DvOrdinal getPulse() {
        return this.pulse;
    }

    public void setConsciousness(DvOrdinal consciousness) {
        this.consciousness = consciousness;
    }

    public DvOrdinal getConsciousness() {
        return this.consciousness;
    }

    public void setTemperature(DvOrdinal temperature) {
        this.temperature = temperature;
    }

    public DvOrdinal getTemperature() {
        return this.temperature;
    }

    public void setTotalScoreMagnitude(Long totalScoreMagnitude) {
        this.totalScoreMagnitude = totalScoreMagnitude;
    }

    public Long getTotalScoreMagnitude() {
        return this.totalScoreMagnitude;
    }

    public void setClinicalRiskCategoryDefiningCode(ClinicalRiskCategoryDefiningCode clinicalRiskCategoryDefiningCode) {
        this.clinicalRiskCategoryDefiningCode = clinicalRiskCategoryDefiningCode;
    }

    public ClinicalRiskCategoryDefiningCode getClinicalRiskCategoryDefiningCode() {
        return this.clinicalRiskCategoryDefiningCode;
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
