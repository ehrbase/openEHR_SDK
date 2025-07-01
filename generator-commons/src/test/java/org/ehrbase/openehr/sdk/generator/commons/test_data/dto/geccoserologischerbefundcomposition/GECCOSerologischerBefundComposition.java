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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import com.nedap.archie.rm.support.identification.ObjectVersionId;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Id;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Template;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.CompositionEntity;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Category;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Setting;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Territory;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieElement;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition.GeccoSerologischerBefundKategorieLoincElement;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.geccoserologischerbefundcomposition.definition.StatusDefiningCode;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.registereintrag.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-05-19T16:20:29.973165500+02:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("GECCO_Serologischer Befund")
public class GECCOSerologischerBefundComposition implements CompositionEntity {
    /** Path: GECCO_Serologischer Befund/category */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/context/Erweiterung Description: Ergänzende Angaben zum
     * Registereintrag.
     */
    @Path("/context/other_context[at0001 and name/value='Tree']/items[at0002]")
    private List<Cluster> erweiterung;

    /**
     * Path: GECCO_Serologischer Befund/context/Status Description: Status der gelieferten Daten für
     * den Registereintrag. Hinweis: Dies ist nicht der Status einzelner Komponenten.
     */
    @Path("/context/other_context[at0001 and name/value='Tree']/items[at0004]/value|defining_code")
    private StatusDefiningCode statusDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/context/Kategorie Description: Die Klassifikation des
     * Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/context/other_context[at0001 and name/value='Tree']/items[at0005 and name/value='Kategorie']")
    private List<GeccoSerologischerBefundKategorieElement> kategorie;

    /**
     * Path: GECCO_Serologischer Befund/context/Kategorie (LOINC) Description: Die Klassifikation des
     * Registereintrags (z.B. Typ der Observation des FHIR-Profils).
     */
    @Path("/context/other_context[at0001 and name/value='Tree']/items[at0005 and name/value='Kategorie (LOINC)']")
    private List<GeccoSerologischerBefundKategorieLoincElement> kategorieLoinc;

    /** Path: GECCO_Serologischer Befund/context/start_time */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /** Path: GECCO_Serologischer Befund/context/participations */
    @Path("/context/participations")
    private List<Participation> participations;

    /** Path: GECCO_Serologischer Befund/context/end_time */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /** Path: GECCO_Serologischer Befund/context/location */
    @Path("/context/location")
    private String location;

    /** Path: GECCO_Serologischer Befund/context/health_care_facility */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /** Path: GECCO_Serologischer Befund/context/setting */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: GECCO_Serologischer Befund/Befund Description: Das Ergebnis - einschließlich der Befunde
     * und der Interpretation des Labors - einer Untersuchung, die an Proben durchgeführt wurde, die
     * von einer Einzelperson stammen oder mit dieser Person zusammenhängen.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Befund']")
    private List<BefundObservation> befund;

    /** Path: GECCO_Serologischer Befund/composer */
    @Path("/composer")
    private PartyProxy composer;

    /** Path: GECCO_Serologischer Befund/language */
    @Path("/language")
    private Language language;

    /** Path: GECCO_Serologischer Befund/feeder_audit */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /** Path: GECCO_Serologischer Befund/territory */
    @Path("/territory")
    private Territory territory;

    @Id
    private ObjectVersionId versionUid;

    public void setCategoryDefiningCode(Category categoryDefiningCode) {
        this.categoryDefiningCode = categoryDefiningCode;
    }

    public Category getCategoryDefiningCode() {
        return this.categoryDefiningCode;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
    }

    public void setStatusDefiningCode(StatusDefiningCode statusDefiningCode) {
        this.statusDefiningCode = statusDefiningCode;
    }

    public StatusDefiningCode getStatusDefiningCode() {
        return this.statusDefiningCode;
    }

    public void setKategorie(List<GeccoSerologischerBefundKategorieElement> kategorie) {
        this.kategorie = kategorie;
    }

    public List<GeccoSerologischerBefundKategorieElement> getKategorie() {
        return this.kategorie;
    }

    public void setKategorieLoinc(List<GeccoSerologischerBefundKategorieLoincElement> kategorieLoinc) {
        this.kategorieLoinc = kategorieLoinc;
    }

    public List<GeccoSerologischerBefundKategorieLoincElement> getKategorieLoinc() {
        return this.kategorieLoinc;
    }

    public void setStartTimeValue(TemporalAccessor startTimeValue) {
        this.startTimeValue = startTimeValue;
    }

    public TemporalAccessor getStartTimeValue() {
        return this.startTimeValue;
    }

    public void setParticipations(List<Participation> participations) {
        this.participations = participations;
    }

    public List<Participation> getParticipations() {
        return this.participations;
    }

    public void setEndTimeValue(TemporalAccessor endTimeValue) {
        this.endTimeValue = endTimeValue;
    }

    public TemporalAccessor getEndTimeValue() {
        return this.endTimeValue;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setHealthCareFacility(PartyIdentified healthCareFacility) {
        this.healthCareFacility = healthCareFacility;
    }

    public PartyIdentified getHealthCareFacility() {
        return this.healthCareFacility;
    }

    public void setSettingDefiningCode(Setting settingDefiningCode) {
        this.settingDefiningCode = settingDefiningCode;
    }

    public Setting getSettingDefiningCode() {
        return this.settingDefiningCode;
    }

    public void setBefund(List<BefundObservation> befund) {
        this.befund = befund;
    }

    public List<BefundObservation> getBefund() {
        return this.befund;
    }

    public void setComposer(PartyProxy composer) {
        this.composer = composer;
    }

    public PartyProxy getComposer() {
        return this.composer;
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

    public void setTerritory(Territory territory) {
        this.territory = territory;
    }

    public Territory getTerritory() {
        return this.territory;
    }

    public ObjectVersionId getVersionUid() {
        return this.versionUid;
    }

    public void setVersionUid(ObjectVersionId versionUid) {
        this.versionUid = versionUid;
    }
}
