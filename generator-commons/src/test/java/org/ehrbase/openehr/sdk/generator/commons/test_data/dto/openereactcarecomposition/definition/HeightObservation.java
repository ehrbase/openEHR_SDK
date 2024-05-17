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
@Archetype("openEHR-EHR-OBSERVATION.height.v2")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:59:53.437779100+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class HeightObservation implements EntryEntity {
    /**
     * Path: open_eREACT-Care/Background/Height/Any event/Height/Length
     * Description: The length of the body from crown of head to sole of foot.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double heightLengthMagnitude;

    /**
     * Path: open_eREACT-Care/Background/Height/Any event/Height/Length
     * Description: The length of the body from crown of head to sole of foot.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
    private String heightLengthUnits;

    /**
     * Path: open_eREACT-Care/Background/Height/Any event/Tree
     * Description: @ internal @
     */
    @Path("/data[at0001]/events[at0002]/state[at0013]")
    private ItemTree tree;

    /**
     * Path: open_eREACT-Care/Background/Height/Any event/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: open_eREACT-Care/Background/Height/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: open_eREACT-Care/Background/Height/Device
     * Description: Description of the device used to measure height or body length.
     */
    @Path("/protocol[at0007]/items[at0011]")
    private Cluster device;

    /**
     * Path: open_eREACT-Care/Background/Height/Extension
     * Description: Additional information required to capture local content or to align with other reference models/formalisms.
     * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
     */
    @Path("/protocol[at0007]/items[at0022]")
    private List<Cluster> extension;

    /**
     * Path: open_eREACT-Care/Background/Height/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: open_eREACT-Care/Background/Height/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: open_eREACT-Care/Background/Height/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setHeightLengthMagnitude(Double heightLengthMagnitude) {
        this.heightLengthMagnitude = heightLengthMagnitude;
    }

    public Double getHeightLengthMagnitude() {
        return this.heightLengthMagnitude;
    }

    public void setHeightLengthUnits(String heightLengthUnits) {
        this.heightLengthUnits = heightLengthUnits;
    }

    public String getHeightLengthUnits() {
        return this.heightLengthUnits;
    }

    public void setTree(ItemTree tree) {
        this.tree = tree;
    }

    public ItemTree getTree() {
        return this.tree;
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
