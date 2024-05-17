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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v2")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:11.010497300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BodyTemperatureObservation implements EntryEntity {
    /**
     * Path: Encounter/Body temperature/origin
     */
    @Path("/data[at0002]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: Encounter/Body temperature/Structured measurement location
     * Description: Structured anatomical location of where the measurement was taken.
     */
    @Path("/protocol[at0020]/items[at0064]")
    private List<Cluster> structuredMeasurementLocation;

    /**
     * Path: Encounter/Body temperature/Device
     * Description: Details about the device use to measure body temperature.
     */
    @Path("/protocol[at0020]/items[at0059]")
    private Cluster device;

    /**
     * Path: Encounter/Body temperature/Extension
     * Description: Additional information required to capture local content or to align with
     *                         other reference models/formalisms.
     *
     * Comment: e.g. Local information requirements or additional metadata to align with FHIR or
     *                         CIMI equivalents.
     *
     */
    @Path("/protocol[at0020]/items[at0062]")
    private List<Cluster> extension;

    /**
     * Path: Encounter/Body temperature/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Encounter/Body temperature/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Encounter/Body temperature/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Encounter/Body temperature/value
     */
    @Path("/protocol[at0020]/items[at0021]/value")
    @Choice
    private BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement;

    /**
     * Path: Encounter/Body temperature/Any event
     * Description: Default, unspecified point in time or interval event which may be explicitly
     *                         defined in a template or at run-time.
     *
     */
    @Path("/data[at0002]/events[at0003]")
    @Choice
    private List<BodyTemperatureAnyEventChoice> anyEvent;

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setStructuredMeasurementLocation(List<Cluster> structuredMeasurementLocation) {
        this.structuredMeasurementLocation = structuredMeasurementLocation;
    }

    public List<Cluster> getStructuredMeasurementLocation() {
        return this.structuredMeasurementLocation;
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

    public void setLocationOfMeasurement(BodyTemperatureLocationOfMeasurementChoice locationOfMeasurement) {
        this.locationOfMeasurement = locationOfMeasurement;
    }

    public BodyTemperatureLocationOfMeasurementChoice getLocationOfMeasurement() {
        return this.locationOfMeasurement;
    }

    public void setAnyEvent(List<BodyTemperatureAnyEventChoice> anyEvent) {
        this.anyEvent = anyEvent;
    }

    public List<BodyTemperatureAnyEventChoice> getAnyEvent() {
        return this.anyEvent;
    }
}
