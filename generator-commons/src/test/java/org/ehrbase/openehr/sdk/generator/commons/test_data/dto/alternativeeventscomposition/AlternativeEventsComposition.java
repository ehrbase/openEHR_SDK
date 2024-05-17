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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.alternativeeventscomposition.definition.KorpergewichtObservation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:10.025486800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("AlternativeEvents")
public class AlternativeEventsComposition implements CompositionEntity {
    /**
     * Path: Bericht/context/Bericht ID
     * Description: Identifizierungsmerkmal des Berichts.
     */
    @Path("/context/other_context[at0001]/items[at0002]/value|value")
    private String berichtIdValue;

    /**
     * Path: Bericht/context/Status
     * Description: Der Status des gesamten Berichts. Hinweis: Dies ist nicht der Status einer
     *                 Berichtskomponente.
     *
     */
    @Path("/context/other_context[at0001]/items[at0005]/value|value")
    private String statusValue;

    /**
     * Path: Bericht/context/Erweiterung
     * Description: Zusätzliche Informationen zur Erfassung lokaler Inhalte oder Anpassung an andere
     *                 Referenzmodelle/Formalismen.
     *
     * Comment: Zum Beispiel: lokaler Informationsbedarf oder zusätzliche Metadaten zur Anpassung an
     *                 FHIR-Ressourcen oder CIMI-Modelle.
     *
     */
    @Path("/context/other_context[at0001]/items[at0006]")
    private List<Cluster> erweiterung;

    /**
     * Path: Bericht/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: Bericht/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: Bericht/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: Bericht/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: Bericht/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: Bericht/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: Bericht/Körpergewicht
     * Description: Messung des Körpergewichts eines Individuums.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.body_weight.v2]")
    private List<KorpergewichtObservation> korpergewicht;

    /**
     * Path: Bericht/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: Bericht/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: Bericht/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: Bericht/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: Bericht/territory
     */
    @Path("/territory")
    private Territory territory;

    @Id
    private ObjectVersionId versionUid;

    public void setBerichtIdValue(String berichtIdValue) {
        this.berichtIdValue = berichtIdValue;
    }

    public String getBerichtIdValue() {
        return this.berichtIdValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getStatusValue() {
        return this.statusValue;
    }

    public void setErweiterung(List<Cluster> erweiterung) {
        this.erweiterung = erweiterung;
    }

    public List<Cluster> getErweiterung() {
        return this.erweiterung;
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

    public void setKorpergewicht(List<KorpergewichtObservation> korpergewicht) {
        this.korpergewicht = korpergewicht;
    }

    public List<KorpergewichtObservation> getKorpergewicht() {
        return this.korpergewicht;
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

    public void setCategoryDefiningCode(Category categoryDefiningCode) {
        this.categoryDefiningCode = categoryDefiningCode;
    }

    public Category getCategoryDefiningCode() {
        return this.categoryDefiningCode;
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
