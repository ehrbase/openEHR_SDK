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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.shareddefinition.Language;

public class ProblemDiagnoseEvaluationContainment extends Containment {
    public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE_EVALUATION = new AqlFieldImp<
            ProblemDiagnoseEvaluation>(
            ProblemDiagnoseEvaluation.class, "", "ProblemDiagnoseEvaluation", ProblemDiagnoseEvaluation.class, this);

    public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(
            ProblemDiagnoseEvaluation.class,
            "/data[at0001]/items[at0002]/value|value",
            "nameDesProblemsDerDiagnoseValue",
            String.class,
            this);

    public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(
            ProblemDiagnoseEvaluation.class,
            "/data[at0001]/items[at0039]",
            "anatomischeStelleStrukturiert",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(
            ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0043]", "spezifischeAngaben", Cluster.class, this);

    public ListSelectAqlField<Cluster> STATUS = new ListAqlFieldImp<Cluster>(
            ProblemDiagnoseEvaluation.class, "/data[at0001]/items[at0046]", "status", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            ProblemDiagnoseEvaluation.class, "/protocol[at0032]/items[at0071]", "erweiterung", Cluster.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(ProblemDiagnoseEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(ProblemDiagnoseEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ProblemDiagnoseEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private ProblemDiagnoseEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
    }

    public static ProblemDiagnoseEvaluationContainment getInstance() {
        return new ProblemDiagnoseEvaluationContainment();
    }
}
