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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition.AssessmentSection;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition.BackgroundSection;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition.ResponseSection;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition.SituationSection;

public class OpenEREACTCareCompositionContainment extends Containment {
    public SelectAqlField<OpenEREACTCareComposition> OPEN_E_R_E_A_C_T_CARE_COMPOSITION = new AqlFieldImp<
            OpenEREACTCareComposition>(
            OpenEREACTCareComposition.class, "", "OpenEREACTCareComposition", OpenEREACTCareComposition.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            OpenEREACTCareComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            OpenEREACTCareComposition.class,
            "/context/other_context[at0001]/items[at0002]",
            "extension",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            OpenEREACTCareComposition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            OpenEREACTCareComposition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            OpenEREACTCareComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            OpenEREACTCareComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            OpenEREACTCareComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            OpenEREACTCareComposition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public SelectAqlField<SituationSection> SITUATION = new AqlFieldImp<SituationSection>(
            OpenEREACTCareComposition.class,
            "/content[openEHR-EHR-SECTION.adhoc.v1]",
            "situation",
            SituationSection.class,
            this);

    public SelectAqlField<BackgroundSection> BACKGROUND = new AqlFieldImp<BackgroundSection>(
            OpenEREACTCareComposition.class,
            "/content[openEHR-EHR-SECTION.adhoc.v1]",
            "background",
            BackgroundSection.class,
            this);

    public SelectAqlField<AssessmentSection> ASSESSMENT = new AqlFieldImp<AssessmentSection>(
            OpenEREACTCareComposition.class,
            "/content[openEHR-EHR-SECTION.adhoc.v1]",
            "assessment",
            AssessmentSection.class,
            this);

    public SelectAqlField<ResponseSection> RESPONSE = new AqlFieldImp<ResponseSection>(
            OpenEREACTCareComposition.class,
            "/content[openEHR-EHR-SECTION.adhoc.v1]",
            "response",
            ResponseSection.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            OpenEREACTCareComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(OpenEREACTCareComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            OpenEREACTCareComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            OpenEREACTCareComposition.class, "/territory", "territory", Territory.class, this);

    private OpenEREACTCareCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.encounter.v1");
    }

    public static OpenEREACTCareCompositionContainment getInstance() {
        return new OpenEREACTCareCompositionContainment();
    }
}
