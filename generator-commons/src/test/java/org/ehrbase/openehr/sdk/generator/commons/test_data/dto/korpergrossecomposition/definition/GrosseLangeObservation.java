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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition;

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
        date = "2021-02-18T12:17:33.262322500+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class GrosseLangeObservation implements EntryEntity {
    /**
     * Path: Körpergröße/Größe/Länge/Beliebiges Ereignis/Größe/Länge
     * Description: Die Länge des Körpers vom Scheitel bis zur Fußsohle.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
    private Double grosseLangeMagnitude;

    /**
     * Path: Körpergröße/Größe/Länge/Beliebiges Ereignis/Größe/Länge
     * Description: Die Länge des Körpers vom Scheitel bis zur Fußsohle.
     */
    @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
    private String grosseLangeUnits;

    /**
     * Path: Körpergröße/Größe/Länge/Beliebiges Ereignis/Tree
     * Description: @ internal @
     */
    @Path("/data[at0001]/events[at0002]/state[at0013]")
    private ItemTree tree;

    /**
     * Path: Körpergröße/Größe/Länge/Beliebiges Ereignis/time
     */
    @Path("/data[at0001]/events[at0002]/time|value")
    private TemporalAccessor timeValue;

    /**
     * Path: Körpergröße/Größe/Länge/origin
     */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: Körpergröße/Größe/Länge/Gerät
     * Description: Beschreibung des Geräts, das zur Messung der Größe oder Länge verwendet wurde.
     */
    @Path("/protocol[at0007]/items[at0011]")
    private Cluster gerat;

    /**
     * Path: Körpergröße/Größe/Länge/Erweiterung
     * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
     * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
     */
    @Path("/protocol[at0007]/items[at0022]")
    private List<Cluster> erweiterung;

    /**
     * Path: Körpergröße/Größe/Länge/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Körpergröße/Größe/Länge/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Körpergröße/Größe/Länge/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setGrosseLangeMagnitude(Double grosseLangeMagnitude) {
        this.grosseLangeMagnitude = grosseLangeMagnitude;
    }

    public Double getGrosseLangeMagnitude() {
        return this.grosseLangeMagnitude;
    }

    public void setGrosseLangeUnits(String grosseLangeUnits) {
        this.grosseLangeUnits = grosseLangeUnits;
    }

    public String getGrosseLangeUnits() {
        return this.grosseLangeUnits;
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

    public void setGerat(Cluster gerat) {
        this.gerat = gerat;
    }

    public Cluster getGerat() {
        return this.gerat;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
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
