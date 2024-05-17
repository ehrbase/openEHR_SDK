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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition.GrosseLangeObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.korpergrossecomposition.definition.StatusDefiningCode;

public class KorpergrosseCompositionContainment extends Containment {
    public SelectAqlField<KorpergrosseComposition> KORPERGROSSE_COMPOSITION = new AqlFieldImp<KorpergrosseComposition>(
            KorpergrosseComposition.class, "", "KorpergrosseComposition", KorpergrosseComposition.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            KorpergrosseComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            KorpergrosseComposition.class,
            "/context/other_context[at0001]/items[at0002]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<StatusDefiningCode> STATUS_DEFINING_CODE = new AqlFieldImp<StatusDefiningCode>(
            KorpergrosseComposition.class,
            "/context/other_context[at0001]/items[at0004]/value|defining_code",
            "statusDefiningCode",
            StatusDefiningCode.class,
            this);

    public SelectAqlField<String> KATEGORIE_VALUE = new AqlFieldImp<String>(
            KorpergrosseComposition.class,
            "/context/other_context[at0001]/items[at0005]/value|value",
            "kategorieValue",
            String.class,
            this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            KorpergrosseComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            KorpergrosseComposition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            KorpergrosseComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION =
            new AqlFieldImp<String>(KorpergrosseComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            KorpergrosseComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            KorpergrosseComposition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public SelectAqlField<GrosseLangeObservation> GROSSE_LANGE = new AqlFieldImp<GrosseLangeObservation>(
            KorpergrosseComposition.class,
            "/content[openEHR-EHR-OBSERVATION.height.v2]",
            "grosseLange",
            GrosseLangeObservation.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER =
            new AqlFieldImp<PartyProxy>(KorpergrosseComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(KorpergrosseComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            KorpergrosseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY =
            new AqlFieldImp<Territory>(KorpergrosseComposition.class, "/territory", "territory", Territory.class, this);

    private KorpergrosseCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.registereintrag.v1");
    }

    public static KorpergrosseCompositionContainment getInstance() {
        return new KorpergrosseCompositionContainment();
    }
}
