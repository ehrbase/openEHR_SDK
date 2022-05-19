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
package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class ProblemDiganoseCoronovirusEvaluationContainment extends Containment {
    public SelectAqlField<ProblemDiganoseCoronovirusEvaluation> PROBLEM_DIGANOSE_CORONOVIRUS_EVALUATION =
            new AqlFieldImp<ProblemDiganoseCoronovirusEvaluation>(
                    ProblemDiganoseCoronovirusEvaluation.class,
                    "",
                    "ProblemDiganoseCoronovirusEvaluation",
                    ProblemDiganoseCoronovirusEvaluation.class,
                    this);

    public SelectAqlField<String> NAME_DES_PROBLEMS_DER_DIAGNOSE_VALUE = new AqlFieldImp<String>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/data[at0001]/items[at0002]/value|value",
            "nameDesProblemsDerDiagnoseValue",
            String.class,
            this);

    public ListSelectAqlField<Cluster> ANATOMISCHE_STELLE_STRUKTURIERT = new ListAqlFieldImp<Cluster>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/data[at0001]/items[at0039]",
            "anatomischeStelleStrukturiert",
            Cluster.class,
            this);

    public ListSelectAqlField<Cluster> SPEZIFISCHE_ANGABEN = new ListAqlFieldImp<Cluster>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/data[at0001]/items[at0043]",
            "spezifischeAngaben",
            Cluster.class,
            this);

    public SelectAqlField<StatusCluster> STATUS = new AqlFieldImp<StatusCluster>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]",
            "status",
            StatusCluster.class,
            this);

    public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/data[at0001]/items[at0069]/value|value",
            "kommentarValue",
            String.class,
            this);

    public SelectAqlField<TemporalAccessor> ZULETZT_AKTUALISIERT_VALUE = new AqlFieldImp<TemporalAccessor>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/protocol[at0032]/items[at0070]/value|value",
            "zuletztAktualisiertValue",
            TemporalAccessor.class,
            this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            ProblemDiganoseCoronovirusEvaluation.class,
            "/protocol[at0032]/items[at0071]",
            "erweiterung",
            Cluster.class,
            this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(
            ProblemDiganoseCoronovirusEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(
            ProblemDiganoseCoronovirusEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            ProblemDiganoseCoronovirusEvaluation.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice> DIAGNOSTISCHE_SICHERHEIT =
            new AqlFieldImp<ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice>(
                    ProblemDiganoseCoronovirusEvaluation.class,
                    "/data[at0001]/items[at0073]/value",
                    "diagnostischeSicherheit",
                    ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice.class,
                    this);

    private ProblemDiganoseCoronovirusEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1");
    }

    public static ProblemDiganoseCoronovirusEvaluationContainment getInstance() {
        return new ProblemDiganoseCoronovirusEvaluationContainment();
    }
}
