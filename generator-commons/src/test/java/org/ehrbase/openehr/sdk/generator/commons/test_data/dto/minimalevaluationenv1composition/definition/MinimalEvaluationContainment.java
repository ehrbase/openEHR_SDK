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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.minimalevaluationenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.NullFlavour;

public class MinimalEvaluationContainment extends Containment {
    public SelectAqlField<MinimalEvaluation> MINIMAL_EVALUATION = new AqlFieldImp<MinimalEvaluation>(
            MinimalEvaluation.class, "", "MinimalEvaluation", MinimalEvaluation.class, this);

    public SelectAqlField<Double> QUANTITY_MAGNITUDE = new AqlFieldImp<Double>(
            MinimalEvaluation.class,
            "/data[at0001]/items[at0002]/value|magnitude",
            "quantityMagnitude",
            Double.class,
            this);

    public SelectAqlField<String> QUANTITY_UNITS = new AqlFieldImp<String>(
            MinimalEvaluation.class, "/data[at0001]/items[at0002]/value|units", "quantityUnits", String.class, this);

    public SelectAqlField<NullFlavour> QUANTITY_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(
            MinimalEvaluation.class,
            "/data[at0001]/items[at0002]/null_flavour|defining_code",
            "quantityNullFlavourDefiningCode",
            NullFlavour.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(MinimalEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(MinimalEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            MinimalEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private MinimalEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.minimal.v1");
    }

    public static MinimalEvaluationContainment getInstance() {
        return new MinimalEvaluationContainment();
    }
}
