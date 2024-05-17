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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class AllgemeineAngabenSectionContainment extends Containment {
    public SelectAqlField<AllgemeineAngabenSection> ALLGEMEINE_ANGABEN_SECTION = new AqlFieldImp<
            AllgemeineAngabenSection>(
            AllgemeineAngabenSection.class, "", "AllgemeineAngabenSection", AllgemeineAngabenSection.class, this);

    public SelectAqlField<FragebogenZumMedikationsScreeningObservation> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING =
            new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-OBSERVATION.medication_use.v0]",
                    "fragebogenZumMedikationsScreening",
                    FragebogenZumMedikationsScreeningObservation.class,
                    this);

    public SelectAqlField<GesundheitsbezogenerBerufEvaluation> GESUNDHEITSBEZOGENER_BERUF =
            new AqlFieldImp<GesundheitsbezogenerBerufEvaluation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']",
                    "gesundheitsbezogenerBeruf",
                    GesundheitsbezogenerBerufEvaluation.class,
                    this);

    public SelectAqlField<ChronischeErkrankungenObservation> CHRONISCHE_ERKRANKUNGEN =
            new AqlFieldImp<ChronischeErkrankungenObservation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']",
                    "chronischeErkrankungen",
                    ChronischeErkrankungenObservation.class,
                    this);

    public ListSelectAqlField<ProblemDiagnoseEvaluation> PROBLEM_DIAGNOSE =
            new ListAqlFieldImp<ProblemDiagnoseEvaluation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]",
                    "problemDiagnose",
                    ProblemDiagnoseEvaluation.class,
                    this);

    public ListSelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG =
            new ListAqlFieldImp<DienstleistungInstruction>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-INSTRUCTION.service_request.v1]",
                    "dienstleistung",
                    DienstleistungInstruction.class,
                    this);

    public SelectAqlField<ProblemDiganoseCoronovirusEvaluation> PROBLEM_DIGANOSE_CORONOVIRUS =
            new AqlFieldImp<ProblemDiganoseCoronovirusEvaluation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']",
                    "problemDiganoseCoronovirus",
                    ProblemDiganoseCoronovirusEvaluation.class,
                    this);

    public SelectAqlField<AndereAktuelleErkrankungenObservation> ANDERE_AKTUELLE_ERKRANKUNGEN =
            new AqlFieldImp<AndereAktuelleErkrankungenObservation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']",
                    "andereAktuelleErkrankungen",
                    AndereAktuelleErkrankungenObservation.class,
                    this);

    public ListSelectAqlField<BewertungDesGesundheitsrisikosEvaluation> BEWERTUNG_DES_GESUNDHEITSRISIKOS =
            new ListAqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(
                    AllgemeineAngabenSection.class,
                    "/items[openEHR-EHR-EVALUATION.health_risk.v1]",
                    "bewertungDesGesundheitsrisikos",
                    BewertungDesGesundheitsrisikosEvaluation.class,
                    this);

    public ListSelectAqlField<WohnsituationEvaluation> WOHNSITUATION = new ListAqlFieldImp<WohnsituationEvaluation>(
            AllgemeineAngabenSection.class,
            "/items[openEHR-EHR-EVALUATION.living_arrangement.v0]",
            "wohnsituation",
            WohnsituationEvaluation.class,
            this);

    private AllgemeineAngabenSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static AllgemeineAngabenSectionContainment getInstance() {
        return new AllgemeineAngabenSectionContainment();
    }
}
