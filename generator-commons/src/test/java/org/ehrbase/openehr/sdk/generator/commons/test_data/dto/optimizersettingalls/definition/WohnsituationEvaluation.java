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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.EntryEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.living_arrangement.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.109062700+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class WohnsituationEvaluation implements EntryEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Beschreibung
     * Description: Beschreibung der Wohnsituation.
     */
    @Path("/data[at0001]/items[at0003]/value|value")
    private String beschreibungValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Anzahl der Haushaltsmitglieder
     * Description: Die Anzahl der Individuen, die dem Haushalt angehören.
     */
    @Path("/data[at0001]/items[at0007]/value|magnitude")
    private Long anzahlDerHaushaltsmitgliederMagnitude;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte
     * Description: Ein Überblick über die Eigenschaften eines einzelnen Bauwerks oder eines separaten Bereichs innerhalb eines Bauwerks und dazugehörige Bereiche, in denen eine Person lebt.
     * Comment: Der vorgesehene Umfang einer Wohnstätte beinhaltet, aber ist nicht beschränkt auf: ein Gebäude; Teil eines Gebäudes; Haus; Wohnung; Gefängnis; Hausboot; Wohnwagen; Fahrzeug. Es kann auch weitere dem Bauwerk oder dem Grundstück zugehörige Bereiche beinhalten, inklusive dem Untergeschoss oder Hof.
     */
    @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.dwelling.v0]")
    private List<WohnstatteCluster> wohnstatte;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/Erweiterung
     * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere Referenzmodelle/Formalismen.
     * Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusäztliche Metadaten zur Anpassung an FHIR.
     */
    @Path("/protocol[at0002]/items[at0011]")
    private List<Cluster> erweiterung;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/subject
     */
    @Path("/subject")
    private PartyProxy subject;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/Allgemeine Angaben/Wohnsituation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setBeschreibungValue(String beschreibungValue) {
        this.beschreibungValue = beschreibungValue;
    }

    public String getBeschreibungValue() {
        return this.beschreibungValue;
    }

    public void setAnzahlDerHaushaltsmitgliederMagnitude(Long anzahlDerHaushaltsmitgliederMagnitude) {
        this.anzahlDerHaushaltsmitgliederMagnitude = anzahlDerHaushaltsmitgliederMagnitude;
    }

    public Long getAnzahlDerHaushaltsmitgliederMagnitude() {
        return this.anzahlDerHaushaltsmitgliederMagnitude;
    }

    public void setWohnstatte(List<WohnstatteCluster> wohnstatte) {
        this.wohnstatte = wohnstatte;
    }

    public List<WohnstatteCluster> getWohnstatte() {
        return this.wohnstatte;
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
