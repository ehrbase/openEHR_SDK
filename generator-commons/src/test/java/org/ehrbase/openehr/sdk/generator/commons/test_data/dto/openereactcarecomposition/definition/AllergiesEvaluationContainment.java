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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class AllergiesEvaluationContainment extends Containment {
    public SelectAqlField<AllergiesEvaluation> ALLERGIES_EVALUATION = new AqlFieldImp<AllergiesEvaluation>(
            AllergiesEvaluation.class, "", "AllergiesEvaluation", AllergiesEvaluation.class, this);

    public SelectAqlField<String> SYNOPSIS_VALUE = new AqlFieldImp<String>(
            AllergiesEvaluation.class, "/data[at0001]/items[at0002]/value|value", "synopsisValue", String.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(
            AllergiesEvaluation.class, "/protocol[at0003]/items[at0004]", "extension", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(AllergiesEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(AllergiesEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AllergiesEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AllergiesEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.clinical_synopsis.v1");
    }

    public static AllergiesEvaluationContainment getInstance() {
        return new AllergiesEvaluationContainment();
    }
}
