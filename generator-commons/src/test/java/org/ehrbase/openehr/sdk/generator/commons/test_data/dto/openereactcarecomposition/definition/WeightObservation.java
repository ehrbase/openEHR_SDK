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
import com.nedap.archie.rm.datastructures.ItemTree;
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
@Archetype("openEHR-EHR-OBSERVATION.body_weight.v2")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.442785700+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class WeightObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Background/Weight/Any event/Weight
     * Description: The weight of the individual.
     */
    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
    private Double weightMagnitude;

    /**
     * Path: open_eREACT-Care/Background/Weight/Any event/Weight
     * Description: The weight of the individual.
     */
    @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
    private String weightUnits;

    /**
     * Path: open_eREACT-Care/Background/Weight/Any event/state structure
     * Description: @ internal @
     */
    @Path("/data[at0002]/events[at0003]/state[at0008]")
    private ItemTree stateStructure;

    /**
     * Path: open_eREACT-Care/Background/Weight/Any event/time
     */
    @Path("/data[at0002]/events[at0003]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Background/Weight/origin
     */
    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Background/Weight/Device
     * Description: Details about the weighing device.
     */
    @Path("/protocol[at0015]/items[at0020]")
    private Cluster device;

    /**
     * Path: open_eREACT-Care/Background/Weight/Extension
     * Description: Additional information required to capture local content or to align with other reference models/formalisms.
     * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
     */
    @Path("/protocol[at0015]/items[at0027]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Background/Weight/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Background/Weight/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Background/Weight/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setWeightMagnitude(Double weightMagnitude) {
        this.weightMagnitude = weightMagnitude;
    }

    public Double getWeightMagnitude() {
        return this.weightMagnitude;
    }

    public void setWeightUnits(String weightUnits) {
        this.weightUnits = weightUnits;
    }

    public String getWeightUnits() {
        return this.weightUnits;
    }

    public void setStateStructure(ItemTree stateStructure) {
        this.stateStructure = stateStructure;
    }

    public ItemTree getStateStructure() {
        return this.stateStructure;
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

    public void setDevice(Cluster device) {
        this.device = device;
    }

    public Cluster getDevice() {
        return this.device;
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
