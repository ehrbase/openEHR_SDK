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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.ehrbasemultioccurrencedev1composition.definition.BodyTemperatureObservation;

public class EhrbaseMultiOccurrenceDeV1CompositionContainment extends Containment {
    public SelectAqlField<EhrbaseMultiOccurrenceDeV1Composition> EHRBASE_MULTI_OCCURRENCE_DE_V1_COMPOSITION =
            new AqlFieldImp<EhrbaseMultiOccurrenceDeV1Composition>(
                    EhrbaseMultiOccurrenceDeV1Composition.class,
                    "",
                    "EhrbaseMultiOccurrenceDeV1Composition",
                    EhrbaseMultiOccurrenceDeV1Composition.class,
                    this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/other_context[at0001]/items[at0002]",
            "extension",
            Cluster.class,
            this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/participations",
            "participations",
            Participation.class,
            this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/end_time|value",
            "endTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            EhrbaseMultiOccurrenceDeV1Composition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public ListSelectAqlField<BodyTemperatureObservation> BODY_TEMPERATURE =
            new ListAqlFieldImp<BodyTemperatureObservation>(
                    EhrbaseMultiOccurrenceDeV1Composition.class,
                    "/content[openEHR-EHR-OBSERVATION.body_temperature.v2]",
                    "bodyTemperature",
                    BodyTemperatureObservation.class,
                    this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            EhrbaseMultiOccurrenceDeV1Composition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            EhrbaseMultiOccurrenceDeV1Composition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            EhrbaseMultiOccurrenceDeV1Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            EhrbaseMultiOccurrenceDeV1Composition.class,
            "/category|defining_code",
            "categoryDefiningCode",
            Category.class,
            this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            EhrbaseMultiOccurrenceDeV1Composition.class, "/territory", "territory", Territory.class, this);

    private EhrbaseMultiOccurrenceDeV1CompositionContainment() {
        super("openEHR-EHR-COMPOSITION.encounter.v1");
    }

    public static EhrbaseMultiOccurrenceDeV1CompositionContainment getInstance() {
        return new EhrbaseMultiOccurrenceDeV1CompositionContainment();
    }
}
