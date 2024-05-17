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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition.EventsummaryCluster;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition.SmicsErgebnisObservation;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:57:29.061800+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("SmICS-Befund")
public class SmICSBefundComposition implements CompositionEntity {
    /**
     * Path: SmICS Befund/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: SmICS Befund/context/Bericht ID
     * Description: Identifizierungsmerkmal des Berichts.
     */
    @Path("/context/other_context[at0001]/items[at0002]/value|value")
    private String berichtIdValue;

    /**
     * Path: SmICS Befund/context/Status
     * Description: Der Status des gesamten Berichts. Hinweis: Dies ist nicht der Status einer Berichtskomponente.
     */
    @Path("/context/other_context[at0001]/items[at0005]/value|value")
    private String statusValue;

    /**
     * Path: SmICS Befund/context/Eventsummary
     * Description: Zur Erfassung von Details zur Identifikation eines Events im Gesundheitswesen.
     */
    @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.eventsummary.v0]")
    private List<EventsummaryCluster> eventsummary;

    /**
     * Path: SmICS Befund/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: SmICS Befund/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: SmICS Befund/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: SmICS Befund/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: SmICS Befund/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: SmICS Befund/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis
     * Description: Zur Repräsentation von Krankheiten und Erregern, welche sich durch die hohe Mobilität der Menschen über große Entfernungen und ein größeres Risikopotential für die schnelle Ausbreitung von Infektionskrankheiten und Erregern kennzeichnet.
     */
    @Path("/content[openEHR-EHR-OBSERVATION.smics_befund.v1]")
    private SmicsErgebnisObservation smicsErgebnis;

    /**
     * Path: SmICS Befund/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: SmICS Befund/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: SmICS Befund/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: SmICS Befund/territory
     */
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

    public void setEventsummary(List<EventsummaryCluster> eventsummary) {
        this.eventsummary = eventsummary;
    }

    public List<EventsummaryCluster> getEventsummary() {
        return this.eventsummary;
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

    public void setSmicsErgebnis(SmicsErgebnisObservation smicsErgebnis) {
        this.smicsErgebnis = smicsErgebnis;
    }

    public SmicsErgebnisObservation getSmicsErgebnis() {
        return this.smicsErgebnis;
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
