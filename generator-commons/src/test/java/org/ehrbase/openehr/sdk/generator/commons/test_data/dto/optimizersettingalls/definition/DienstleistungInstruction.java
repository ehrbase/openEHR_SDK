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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

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
@Archetype("openEHR-EHR-INSTRUCTION.service_request.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.123030700+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DienstleistungInstruction implements EntryEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/Aktuelle Aktivität
     * Description: Aktuelle Aktivität
     */
    @Path("/activities[at0001]")
    private List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/Einsender
     * Description: Details über den Kliniker oder die Abteilung, die die Dienstleistung anfordert.
     */
    @Path("/protocol[at0008]/items[at0141]")
    private Cluster einsender;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/Empfänger
     * Description: Details über den Kliniker oder die Abteilung, die die Dienstleistung erhält.
     */
    @Path("/protocol[at0008]/items[at0142]")
    private Cluster empfanger;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/Verteilerliste
     * Description: Details über weitere Kliniker, Organisationen oder Einrichtungen, die Kopien aller Mitteilungen benötigen.
     */
    @Path("/protocol[at0008]/items[at0128]")
    private List<Cluster> verteilerliste;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/Erweiterung
     * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
     * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
     */
    @Path("/protocol[at0008]/items[at0112]")
    private List<Cluster> erweiterung;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/narrative
     */
    @Path("/narrative|value")
    private String narrativeValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/Allgemeine Angaben/Dienstleistung/expiry_time
     */
    @Path("/expiry_time|value")
    private TemporalAccessor expiryTimeValue;

    public void setAktuelleAktivitat(List<DienstleistungAktuelleAktivitatActivity> aktuelleAktivitat) {
        this.aktuelleAktivitat = aktuelleAktivitat;
    }

    public List<DienstleistungAktuelleAktivitatActivity> getAktuelleAktivitat() {
        return this.aktuelleAktivitat;
    }

    public void setEinsender(Cluster einsender) {
        this.einsender = einsender;
    }

    public Cluster getEinsender() {
        return this.einsender;
    }

    public void setEmpfanger(Cluster empfanger) {
        this.empfanger = empfanger;
    }

    public Cluster getEmpfanger() {
        return this.empfanger;
    }

    public void setVerteilerliste(List<Cluster> verteilerliste) {
        this.verteilerliste = verteilerliste;
    }

    public List<Cluster> getVerteilerliste() {
        return this.verteilerliste;
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

    public void setNarrativeValue(String narrativeValue) {
        this.narrativeValue = narrativeValue;
    }

    public String getNarrativeValue() {
        return this.narrativeValue;
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

    public void setExpiryTimeValue(TemporalAccessor expiryTimeValue) {
        this.expiryTimeValue = expiryTimeValue;
    }

    public TemporalAccessor getExpiryTimeValue() {
        return this.expiryTimeValue;
    }
}
