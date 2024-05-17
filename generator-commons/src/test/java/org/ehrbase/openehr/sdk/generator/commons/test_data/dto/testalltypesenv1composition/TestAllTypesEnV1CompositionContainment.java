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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition.ContextCodedTextDefiningCode;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition.TestAllTypesEvaluation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition.TestAllTypesObservation;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.testalltypesenv1composition.definition.TestAllTypesSection;

public class TestAllTypesEnV1CompositionContainment extends Containment {
    public SelectAqlField<TestAllTypesEnV1Composition> TEST_ALL_TYPES_EN_V1_COMPOSITION =
            new AqlFieldImp<TestAllTypesEnV1Composition>(
                    TestAllTypesEnV1Composition.class,
                    "",
                    "TestAllTypesEnV1Composition",
                    TestAllTypesEnV1Composition.class,
                    this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            TestAllTypesEnV1Composition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public SelectAqlField<ContextCodedTextDefiningCode> CONTEXT_CODED_TEXT_DEFINING_CODE =
            new AqlFieldImp<ContextCodedTextDefiningCode>(
                    TestAllTypesEnV1Composition.class,
                    "/context/other_context[at0004]/item[at0005]/value|defining_code",
                    "contextCodedTextDefiningCode",
                    ContextCodedTextDefiningCode.class,
                    this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesEnV1Composition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            TestAllTypesEnV1Composition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            TestAllTypesEnV1Composition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            TestAllTypesEnV1Composition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            TestAllTypesEnV1Composition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            TestAllTypesEnV1Composition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public ListSelectAqlField<TestAllTypesObservation> TEST_ALL_TYPES = new ListAqlFieldImp<TestAllTypesObservation>(
            TestAllTypesEnV1Composition.class,
            "/content[openEHR-EHR-OBSERVATION.test_all_types.v1]",
            "testAllTypes",
            TestAllTypesObservation.class,
            this);

    public ListSelectAqlField<TestAllTypesEvaluation> TEST_ALL_TYPES2 = new ListAqlFieldImp<TestAllTypesEvaluation>(
            TestAllTypesEnV1Composition.class,
            "/content[openEHR-EHR-EVALUATION.test_all_types.v1]",
            "testAllTypes2",
            TestAllTypesEvaluation.class,
            this);

    public ListSelectAqlField<TestAllTypesSection> TEST_ALL_TYPES3 = new ListAqlFieldImp<TestAllTypesSection>(
            TestAllTypesEnV1Composition.class,
            "/content[openEHR-EHR-SECTION.test_all_types.v1]",
            "testAllTypes3",
            TestAllTypesSection.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            TestAllTypesEnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(TestAllTypesEnV1Composition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            TestAllTypesEnV1Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            TestAllTypesEnV1Composition.class, "/territory", "territory", Territory.class, this);

    private TestAllTypesEnV1CompositionContainment() {
        super("openEHR-EHR-COMPOSITION.test_all_types.v1");
    }

    public static TestAllTypesEnV1CompositionContainment getInstance() {
        return new TestAllTypesEnV1CompositionContainment();
    }
}
