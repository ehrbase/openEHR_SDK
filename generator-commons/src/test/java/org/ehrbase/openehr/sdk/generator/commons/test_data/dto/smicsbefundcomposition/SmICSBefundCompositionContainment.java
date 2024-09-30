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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition.EventsummaryCluster;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition.SmicsErgebnisObservation;

public class SmICSBefundCompositionContainment extends Containment {
    public SelectAqlField<SmICSBefundComposition> SM_I_C_S_BEFUND_COMPOSITION = new AqlFieldImp<SmICSBefundComposition>(
            SmICSBefundComposition.class, "", "SmICSBefundComposition", SmICSBefundComposition.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            SmICSBefundComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(
            SmICSBefundComposition.class,
            "/context/other_context[at0001]/items[at0002]/value|value",
            "berichtIdValue",
            String.class,
            this);

    public SelectAqlField<String> STATUS_VALUE = new AqlFieldImp<String>(
            SmICSBefundComposition.class,
            "/context/other_context[at0001]/items[at0005]/value|value",
            "statusValue",
            String.class,
            this);

    public ListSelectAqlField<EventsummaryCluster> EVENTSUMMARY = new ListAqlFieldImp<EventsummaryCluster>(
            SmICSBefundComposition.class,
            "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.eventsummary.v0]",
            "eventsummary",
            EventsummaryCluster.class,
            this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            SmICSBefundComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            SmICSBefundComposition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            SmICSBefundComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION =
            new AqlFieldImp<String>(SmICSBefundComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            SmICSBefundComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            SmICSBefundComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

    public SelectAqlField<SmicsErgebnisObservation> SMICS_ERGEBNIS = new AqlFieldImp<SmicsErgebnisObservation>(
            SmICSBefundComposition.class,
            "/content[openEHR-EHR-OBSERVATION.smics_befund.v1]",
            "smicsErgebnis",
            SmicsErgebnisObservation.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER =
            new AqlFieldImp<PartyProxy>(SmICSBefundComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(SmICSBefundComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            SmICSBefundComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY =
            new AqlFieldImp<Territory>(SmICSBefundComposition.class, "/territory", "territory", Territory.class, this);

    private SmICSBefundCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.report.v1");
    }

    public static SmICSBefundCompositionContainment getInstance() {
        return new SmICSBefundCompositionContainment();
    }
}
