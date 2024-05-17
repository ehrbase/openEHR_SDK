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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.coronaanamnesecomposition.definition;

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
@Archetype("openEHR-EHR-EVALUATION.health_risk.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:12.619026200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BewertungDesGesundheitsrisikosEvaluation implements EntryEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Gesundheitsrisiko
     * Description: Identifizierung der potenziellen zukünftigen Krankheit, des Zustands oder des Gesundheitsproblems, für die das Risiko bewertet wird.
     */
    @Path("/data[at0001]/items[at0002]/value|value")
    private String gesundheitsrisikoValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Risikofaktor
     * Description: Identifizierung des Risikofaktors über einen Namen.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0013]/value|value")
    private String spezifischerRisikofaktorRisikofaktorValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Vorhandensein
     * Description: Vorhandensein des Risikofaktors.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0017]/value|defining_code")
    private VorhandenseinDefiningCode2 spezifischerRisikofaktorVorhandenseinDefiningCode;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Spezifischer Risikofaktor/Details
     * Description: Strukturierte Informationen zu anderen Aspekten der Risikofaktorenbewertung.
     * Comment: Zum Beispiel: Prävalenz des Risikofaktors bei Familienmitgliedern.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Spezifischer Risikofaktor']/items[at0027]")
    private List<Cluster> spezifischerRisikofaktorDetails;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Risikofaktor
     * Description: Identifizierung des Risikofaktors über einen Namen.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0013]/value|value")
    private String andereRisikofaktorenRisikofaktorValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Vorhandensein
     * Description: Vorhandensein des Risikofaktors.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0017]/value|defining_code")
    private VorhandenseinDefiningCode2 andereRisikofaktorenVorhandenseinDefiningCode;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Andere Risikofaktoren/Details
     * Description: Strukturierte Informationen zu anderen Aspekten der Risikofaktorenbewertung.
     * Comment: Zum Beispiel: Prävalenz des Risikofaktors bei Familienmitgliedern.
     */
    @Path("/data[at0001]/items[at0016 and name/value='Andere Risikofaktoren']/items[at0027]")
    private List<Cluster> andereRisikofaktorenDetails;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Risikobewertung
     * Description: Bewertung des Gesundheitsrisikos.
     */
    @Path("/data[at0001]/items[at0003]/value|value")
    private String risikobewertungValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Letzte Aktualisierung
     * Description: Das Datum an dem die Bewertung des Gesundheitsrisikos zuletzt aktualisiert wurde.
     */
    @Path("/protocol[at0010]/items[at0024]/value|value")
    private TemporalAccessor letzteAktualisierungValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Bewertungsmethode
     * Description: Identifikation des Algorithmus oder der Leitlinie, welche für die Risikobewertung verwendet wurde.
     */
    @Path("/protocol[at0010]/items[at0025]/value|value")
    private String bewertungsmethodeValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/Erweiterung
     * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
     * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder CIMI-Modelle.
     */
    @Path("/protocol[at0010]/items[at0011]")
    private List<Cluster> erweiterung;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setGesundheitsrisikoValue(String gesundheitsrisikoValue) {
        this.gesundheitsrisikoValue = gesundheitsrisikoValue;
    }

    public String getGesundheitsrisikoValue() {
        return this.gesundheitsrisikoValue;
    }

    public void setSpezifischerRisikofaktorRisikofaktorValue(String spezifischerRisikofaktorRisikofaktorValue) {
        this.spezifischerRisikofaktorRisikofaktorValue = spezifischerRisikofaktorRisikofaktorValue;
    }

    public String getSpezifischerRisikofaktorRisikofaktorValue() {
        return this.spezifischerRisikofaktorRisikofaktorValue;
    }

    public void setSpezifischerRisikofaktorVorhandenseinDefiningCode(
            VorhandenseinDefiningCode2 spezifischerRisikofaktorVorhandenseinDefiningCode) {
        this.spezifischerRisikofaktorVorhandenseinDefiningCode = spezifischerRisikofaktorVorhandenseinDefiningCode;
    }

    public VorhandenseinDefiningCode2 getSpezifischerRisikofaktorVorhandenseinDefiningCode() {
        return this.spezifischerRisikofaktorVorhandenseinDefiningCode;
    }

    public void setSpezifischerRisikofaktorDetails(List<Cluster> spezifischerRisikofaktorDetails) {
        this.spezifischerRisikofaktorDetails = spezifischerRisikofaktorDetails;
    }

    public List<Cluster> getSpezifischerRisikofaktorDetails() {
        return this.spezifischerRisikofaktorDetails;
    }

    public void setAndereRisikofaktorenRisikofaktorValue(String andereRisikofaktorenRisikofaktorValue) {
        this.andereRisikofaktorenRisikofaktorValue = andereRisikofaktorenRisikofaktorValue;
    }

    public String getAndereRisikofaktorenRisikofaktorValue() {
        return this.andereRisikofaktorenRisikofaktorValue;
    }

    public void setAndereRisikofaktorenVorhandenseinDefiningCode(
            VorhandenseinDefiningCode2 andereRisikofaktorenVorhandenseinDefiningCode) {
        this.andereRisikofaktorenVorhandenseinDefiningCode = andereRisikofaktorenVorhandenseinDefiningCode;
    }

    public VorhandenseinDefiningCode2 getAndereRisikofaktorenVorhandenseinDefiningCode() {
        return this.andereRisikofaktorenVorhandenseinDefiningCode;
    }

    public void setAndereRisikofaktorenDetails(List<Cluster> andereRisikofaktorenDetails) {
        this.andereRisikofaktorenDetails = andereRisikofaktorenDetails;
    }

    public List<Cluster> getAndereRisikofaktorenDetails() {
        return this.andereRisikofaktorenDetails;
    }

    public void setRisikobewertungValue(String risikobewertungValue) {
        this.risikobewertungValue = risikobewertungValue;
    }

    public String getRisikobewertungValue() {
        return this.risikobewertungValue;
    }

    public void setLetzteAktualisierungValue(TemporalAccessor letzteAktualisierungValue) {
        this.letzteAktualisierungValue = letzteAktualisierungValue;
    }

    public TemporalAccessor getLetzteAktualisierungValue() {
        return this.letzteAktualisierungValue;
    }

    public void setBewertungsmethodeValue(String bewertungsmethodeValue) {
        this.bewertungsmethodeValue = bewertungsmethodeValue;
    }

    public String getBewertungsmethodeValue() {
        return this.bewertungsmethodeValue;
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
