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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition;

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
@Archetype("openEHR-EHR-OBSERVATION.laboratory_test_result.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:30.040760700+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BefundObservation implements EntryEntity {
    /** Path: GECCO_Serologischer Befund/Befund/origin */
    @Path("/data[at0001]/origin|value")
    private TemporalAccessor originValue;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Labor, welches den Untersuchungsauftrag annimmt
     * Description: Angaben zu dem Labor, das die Anfrage erhalten hat und die Hauptverantwortung für
     * die Verwaltung der Berichterstattung über den Test trägt, auch wenn andere Labore bestimmte
     * Aspekte ausführen. Comment: Dieser Slot gibt die Details des Labors an, dass die Anforderung
     * erhalten hat und die Gesamtverantwortung für die Berichterstellung des Tests trägt, selbst wenn
     * andere Labore bestimmte Aspekte ausführen.
     *
     * <p>Das Empfangslabor kann den Test entweder durchführen oder an ein anderes Labor verweisen.
     * Wenn ein anderes Labor für die Durchführung der Tests mit bestimmten Analyten zuständig ist,
     * ist zu erwarten, dass diese Details im SLOT 'Analyte result detail' innerhalb des Archetyps
     * CLUSTER.laboratory_test_analyte enthalten sind.
     */
    @Path("/protocol[at0004]/items[at0017]")
    private Cluster laborWelchesDenUntersuchungsauftragAnnimmt;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Details der Testanforderung/Anforderung Description:
     * Name des ursprünglich angeforderten Tests. Comment: Dieses Datenelement ist zu verwenden, wenn
     * die angeforderte Testung von der tatsächlich vom Labor durchgeführten Testung abweicht.
     */
    @Path("/protocol[at0004]/items[at0094]/items[at0106 and name/value='Anforderung']/value|defining_code")
    private AnforderungDefiningCode anforderungDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Details der Testanforderung/Einsender Description:
     * Details über den Kliniker oder die Abteilung, die das Labortestergebnis angefordert hat.
     */
    @Path("/protocol[at0004]/items[at0094]/items[at0090]")
    private Cluster einsender;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Details der Testanforderung/Verteilerliste Description:
     * Details über weitere Kliniker oder Organisationen, die eine Kopie der Analyseergebnisse
     * benötigen. Comment: Die "Verteilerliste" dient nur zu Informationszwecken. Der Hauptempfänger
     * des Berichts ist die Person, die dazu bestimmt ist, auf die Information zu reagieren.
     */
    @Path("/protocol[at0004]/items[at0094]/items[at0035]")
    private List<Cluster> verteilerliste;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Test Details Description: Strukturierte Details über
     * die beim Labortest verwendete Methodik, das Gerät oder die Auswertung. Comment: Zum Beispiel:
     * "Details der ELISA/Nephelometrie".
     */
    @Path("/protocol[at0004]/items[at0110]")
    private List<Cluster> testDetails;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Erweiterung Description: Weitere Informationen, die
     * erforderlich sind, um lokale Inhalte abzubilden oder das Modell an andere Referenzmodelle
     * anzupassen. Comment: Zum Beispiel: Lokaler Informationsbedarf oder zusätzliche Metadaten, um
     * ein Mapping auf FHIR oder CIMI Modelle zu ermöglichen.
     */
    @Path("/protocol[at0004]/items[at0117]")
    private List<Cluster> erweiterung;

    /** Path: GECCO_Serologischer Befund/Befund/subject */
    @Path("/subject")
    private PartyProxy subject;

    /** Path: GECCO_Serologischer Befund/Befund/language */
    @Path("/language")
    private Language language;

    /** Path: GECCO_Serologischer Befund/Befund/feeder_audit */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis Description: Jeder Zeitpunkt oder jedes
     * Intervall, das in einem Template oder zur Laufzeit definiert werden kann.
     */
    @Path("/data[at0001]/events[at0002]")
    @Choice
    private List<BefundJedesEreignisChoice> jedesEreignis;

    public void setOriginValue(TemporalAccessor originValue) {
        this.originValue = originValue;
    }

    public TemporalAccessor getOriginValue() {
        return this.originValue;
    }

    public void setLaborWelchesDenUntersuchungsauftragAnnimmt(Cluster laborWelchesDenUntersuchungsauftragAnnimmt) {
        this.laborWelchesDenUntersuchungsauftragAnnimmt = laborWelchesDenUntersuchungsauftragAnnimmt;
    }

    public Cluster getLaborWelchesDenUntersuchungsauftragAnnimmt() {
        return this.laborWelchesDenUntersuchungsauftragAnnimmt;
    }

    public void setAnforderungDefiningCode(AnforderungDefiningCode anforderungDefiningCode) {
        this.anforderungDefiningCode = anforderungDefiningCode;
    }

    public AnforderungDefiningCode getAnforderungDefiningCode() {
        return this.anforderungDefiningCode;
    }

    public void setEinsender(Cluster einsender) {
        this.einsender = einsender;
    }

    public Cluster getEinsender() {
        return this.einsender;
    }

    public void setVerteilerliste(List<Cluster> verteilerliste) {
        this.verteilerliste = verteilerliste;
    }

    public List<Cluster> getVerteilerliste() {
        return this.verteilerliste;
    }

    public void setTestDetails(List<Cluster> testDetails) {
        this.testDetails = testDetails;
    }

    public List<Cluster> getTestDetails() {
        return this.testDetails;
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

    public void setJedesEreignis(List<BefundJedesEreignisChoice> jedesEreignis) {
        this.jedesEreignis = jedesEreignis;
    }

    public List<BefundJedesEreignisChoice> getJedesEreignis() {
        return this.jedesEreignis;
    }
}
