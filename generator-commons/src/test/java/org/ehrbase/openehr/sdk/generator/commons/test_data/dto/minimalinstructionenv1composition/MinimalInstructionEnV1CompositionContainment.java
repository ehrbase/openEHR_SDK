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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalinstructionenv1composition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalinstructionenv1composition.definition.MinimalInstruction;

public class MinimalInstructionEnV1CompositionContainment extends Containment {
    public SelectAqlField<MinimalInstructionEnV1Composition> MINIMAL_INSTRUCTION_EN_V1_COMPOSITION =
            new AqlFieldImp<MinimalInstructionEnV1Composition>(
                    MinimalInstructionEnV1Composition.class,
                    "",
                    "MinimalInstructionEnV1Composition",
                    MinimalInstructionEnV1Composition.class,
                    this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            MinimalInstructionEnV1Composition.class,
            "/category|defining_code",
            "categoryDefiningCode",
            Category.class,
            this);

    public ListSelectAqlField<MinimalInstruction> MINIMAL = new ListAqlFieldImp<MinimalInstruction>(
            MinimalInstructionEnV1Composition.class,
            "/content[openEHR-EHR-INSTRUCTION.minimal.v1]",
            "minimal",
            MinimalInstruction.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(
            MinimalInstructionEnV1Composition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            MinimalInstructionEnV1Composition.class, "/language", "language", Language.class, this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            MinimalInstructionEnV1Composition.class,
            "/context/start_time|value",
            "startTimeValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            MinimalInstructionEnV1Composition.class,
            "/context/participations",
            "participations",
            Participation.class,
            this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            MinimalInstructionEnV1Composition.class,
            "/context/end_time|value",
            "endTimeValue",
            TemporalAccessor.class,
            this);

    public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(
            MinimalInstructionEnV1Composition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            MinimalInstructionEnV1Composition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            MinimalInstructionEnV1Composition.class,
            "/context/setting|defining_code",
            "settingDefiningCode",
            Setting.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            MinimalInstructionEnV1Composition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(
            MinimalInstructionEnV1Composition.class, "/territory", "territory", Territory.class, this);

    private MinimalInstructionEnV1CompositionContainment() {
        super("openEHR-EHR-COMPOSITION.minimal.v1");
    }

    public static MinimalInstructionEnV1CompositionContainment getInstance() {
        return new MinimalInstructionEnV1CompositionContainment();
    }
}
