/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class BackgroundSectionContainment extends Containment {
    public SelectAqlField<BackgroundSection> BACKGROUND_SECTION = new AqlFieldImp<BackgroundSection>(
            BackgroundSection.class, "", "BackgroundSection", BackgroundSection.class, this);

    public SelectAqlField<HeightObservation> HEIGHT = new AqlFieldImp<HeightObservation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-OBSERVATION.height.v2]",
            "height",
            HeightObservation.class,
            this);

    public SelectAqlField<WeightObservation> WEIGHT = new AqlFieldImp<WeightObservation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-OBSERVATION.body_weight.v2]",
            "weight",
            WeightObservation.class,
            this);

    public SelectAqlField<FrailtyObservation> FRAILTY = new AqlFieldImp<FrailtyObservation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1]",
            "frailty",
            FrailtyObservation.class,
            this);

    public SelectAqlField<PastHistoryEvaluation> PAST_HISTORY = new AqlFieldImp<PastHistoryEvaluation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]",
            "pastHistory",
            PastHistoryEvaluation.class,
            this);

    public SelectAqlField<MedicationEvaluation> MEDICATION = new AqlFieldImp<MedicationEvaluation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]",
            "medication",
            MedicationEvaluation.class,
            this);

    public SelectAqlField<AllergiesEvaluation> ALLERGIES = new AqlFieldImp<AllergiesEvaluation>(
            BackgroundSection.class,
            "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1]",
            "allergies",
            AllergiesEvaluation.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            BackgroundSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private BackgroundSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static BackgroundSectionContainment getInstance() {
        return new BackgroundSectionContainment();
    }
}
