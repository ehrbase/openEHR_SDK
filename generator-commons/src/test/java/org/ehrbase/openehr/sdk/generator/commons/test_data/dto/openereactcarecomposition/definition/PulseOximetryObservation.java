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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvProportion;
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
@Archetype("openEHR-EHR-OBSERVATION.pulse_oximetry.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.739779400+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class PulseOximetryObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/SpO₂
     * Description: The saturation of oxygen in the peripheral blood, measured via pulse oximetry.
     * Comment: SpO₂ is defined as the percentage of oxyhaemoglobin (HbO₂) to the total concentration of haemoglobin (HbO₂ + deoxyhaemoglobin) in peripheral blood.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0006]/value")
    private DvProportion spo;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Waveform
     * Description: A waveform reading associated with the oximetry measurement.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0054]")
    private List<Cluster> waveform;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Multimedia image
     * Description: Details of a series of oximetry readings, other than waveforms, expressed as a multimedia image or series of images. Waveforms should be recorded using the Waveform slot and associated cluster archetype.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0060]")
    private List<Cluster> multimediaImage;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Exertion
     * Description: Details about physical activity undertaken at the time of measurement.
     */
    @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0034]")
    private Cluster exertion;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/Inspired oxygen
     * Description: Details of the amount of oxygen available to the subject at the time of observation.
     * Comment: Assumed values of 21% oxygen concentration, Fi0₂ of 0.21 and oxygen flow rate of 0 l/min or 0 ml/min.
     */
    @Path("/data[at0001]/events[at0002]/state[at0014]/items[at0015]")
    private Cluster inspiredOxygen;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Any event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Oximetry device
     * Description: Details of the non-invasive oximetry device used.
     */
    @Path("/protocol[at0007]/items[at0018]")
    private Cluster oximetryDevice;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/Extension
     * Description: Additional information required to capture local context or to align with other reference models/formalisms.
     * Comment: e.g. Local hospital departmental infomation or additional metadata to align with HL7 or CDISC equivalents.
     */
    @Path("/protocol[at0007]/items[at0059]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Assessment/NEWS2/Pulse oximetry/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setSpo(DvProportion spo) {
        this.spo = spo;
    }

    public DvProportion getSpo() {
        return this.spo;
    }

    public void setWaveform(List<Cluster> waveform) {
        this.waveform = waveform;
    }

    public List<Cluster> getWaveform() {
        return this.waveform;
    }

    public void setMultimediaImage(List<Cluster> multimediaImage) {
        this.multimediaImage = multimediaImage;
    }

    public List<Cluster> getMultimediaImage() {
        return this.multimediaImage;
    }

    public void setExertion(Cluster exertion) {
        this.exertion = exertion;
    }

    public Cluster getExertion() {
        return this.exertion;
    }

    public void setInspiredOxygen(Cluster inspiredOxygen) {
        this.inspiredOxygen = inspiredOxygen;
    }

    public Cluster getInspiredOxygen() {
        return this.inspiredOxygen;
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

    public void setOximetryDevice(Cluster oximetryDevice) {
        this.oximetryDevice = oximetryDevice;
    }

    public Cluster getOximetryDevice() {
        return this.oximetryDevice;
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
