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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Category;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Setting;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Territory;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.episodeofcarecomposition.definition.EpisodeofcareAdminEntry;

public class EpisodeOfCareCompositionContainment extends Containment {
    public SelectAqlField<EpisodeOfCareComposition> EPISODE_OF_CARE_COMPOSITION = new AqlFieldImp<
            EpisodeOfCareComposition>(
            EpisodeOfCareComposition.class, "", "EpisodeOfCareComposition", EpisodeOfCareComposition.class, this);

    public ListSelectAqlField<EpisodeofcareAdminEntry> EPISODEOFCARE = new ListAqlFieldImp<EpisodeofcareAdminEntry>(
            EpisodeOfCareComposition.class,
            "/content[openEHR-EHR-ADMIN_ENTRY.episodeofcare.v0]",
            "episodeofcare",
            EpisodeofcareAdminEntry.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            EpisodeOfCareComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(EpisodeOfCareComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            EpisodeOfCareComposition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            EpisodeOfCareComposition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            EpisodeOfCareComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            EpisodeOfCareComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            EpisodeOfCareComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            EpisodeOfCareComposition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            EpisodeOfCareComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            EpisodeOfCareComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            EpisodeOfCareComposition.class, "/territory", "territory", Territory.class, this);

    private EpisodeOfCareCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.versorgungsfall.v0");
    }

    public static EpisodeOfCareCompositionContainment getInstance() {
        return new EpisodeOfCareCompositionContainment();
    }
}
