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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition;

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
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition.FallidentifikationCluster;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.diagnosecomposition.definition.ProblemDiagnoseEvaluation;

public class DiagnoseCompositionContainment extends Containment {
    public SelectAqlField<DiagnoseComposition> DIAGNOSE_COMPOSITION = new AqlFieldImp<DiagnoseComposition>(
            DiagnoseComposition.class, "", "DiagnoseComposition", DiagnoseComposition.class, this);

    public SelectAqlField<String> BERICHT_ID_VALUE = new AqlFieldImp<String>(
            DiagnoseComposition.class,
            "/context/other_context[at0001]/items[at0002]/value|value",
            "berichtIdValue",
            String.class,
            this);

    public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION = new AqlFieldImp<FallidentifikationCluster>(
            DiagnoseComposition.class,
            "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]",
            "fallidentifikation",
            FallidentifikationCluster.class,
            this);

    public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            DiagnoseComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

    public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(
            DiagnoseComposition.class, "/context/participations", "participations", Participation.class, this);

    public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(
            DiagnoseComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

    public SelectAqlField<String> LOCATION =
            new AqlFieldImp<String>(DiagnoseComposition.class, "/context/location", "location", String.class, this);

    public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(
            DiagnoseComposition.class,
            "/context/health_care_facility",
            "healthCareFacility",
            PartyIdentified.class,
            this);

    public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(
            DiagnoseComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

    public SelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE = new AqlFieldImp<ProblemDiagnoseEvaluation>(
            DiagnoseComposition.class,
            "/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]",
            "problemDiagnose",
            ProblemDiagnoseEvaluation.class,
            this);

    public SelectAqlField<PartyProxy> COMPOSER =
            new AqlFieldImp<PartyProxy>(DiagnoseComposition.class, "/composer", "composer", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(DiagnoseComposition.class, "/language", "language", Language.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            DiagnoseComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(
            DiagnoseComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

    public SelectAqlField<Territory> TERRITORY =
            new AqlFieldImp<Territory>(DiagnoseComposition.class, "/territory", "territory", Territory.class, this);

    private DiagnoseCompositionContainment() {
        super("openEHR-EHR-COMPOSITION.report.v1");
    }

    public static DiagnoseCompositionContainment getInstance() {
        return new DiagnoseCompositionContainment();
    }
}
