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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.versorgungsfall.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:11.180498+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("EpisodeOfCare")
public class EpisodeOfCareComposition implements CompositionEntity {
    /**
     * Path: EpisodeOfCare/Episodeofcare
     * Description: unknown
     */
    @Path("/content[openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0]")
    private List<EpisodeofcareAdminEntry> episodeofcare;

    /**
     * Path: EpisodeOfCare/composer
     */
    @Path("/composer")
    private PartyProxy composer;

    /**
     * Path: EpisodeOfCare/language
     */
    @Path("/language")
    private Language language;

    /**
     * Path: EpisodeOfCare/context/start_time
     */
    @Path("/context/start_time|value")
    private TemporalAccessor startTimeValue;

    /**
     * Path: EpisodeOfCare/context/participations
     */
    @Path("/context/participations")
    private List<Participation> participations;

    /**
     * Path: EpisodeOfCare/context/end_time
     */
    @Path("/context/end_time|value")
    private TemporalAccessor endTimeValue;

    /**
     * Path: EpisodeOfCare/context/location
     */
    @Path("/context/location")
    private String location;

    /**
     * Path: EpisodeOfCare/context/health_care_facility
     */
    @Path("/context/health_care_facility")
    private PartyIdentified healthCareFacility;

    /**
     * Path: EpisodeOfCare/context/setting
     */
    @Path("/context/setting|defining_code")
    private Setting settingDefiningCode;

    /**
     * Path: EpisodeOfCare/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    /**
     * Path: EpisodeOfCare/category
     */
    @Path("/category|defining_code")
    private Category categoryDefiningCode;

    /**
     * Path: EpisodeOfCare/territory
     */
    @Path("/territory")
    private Territory territory;

    @Id
    private ObjectVersionId versionUid;

    public void setEpisodeofcare(List<EpisodeofcareAdminEntry> episodeofcare) {
        this.episodeofcare = episodeofcare;
    }

    public List<EpisodeofcareAdminEntry> getEpisodeofcare() {
        return this.episodeofcare;
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
