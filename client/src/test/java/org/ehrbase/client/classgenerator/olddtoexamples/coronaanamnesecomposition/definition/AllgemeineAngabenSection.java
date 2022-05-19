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
package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class AllgemeineAngabenSection {
    @Path("/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
    private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

    @Path("/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']")
    private GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']")
    private ChronischeErkrankungenObservation chronischeErkrankungen;

    @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
    private List<ProblemDiagnoseEvaluation> problemDiagnose;

    @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
    private List<DienstleistungInstruction> dienstleistung;

    @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']")
    private ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus;

    @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']")
    private AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen;

    @Path("/items[openEHR-EHR-EVALUATION.health_risk.v1]")
    private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

    @Path("/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
    private List<WohnsituationEvaluation> wohnsituation;

    public void setFragebogenZumMedikationsScreening(
            FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening) {
        this.fragebogenZumMedikationsScreening = fragebogenZumMedikationsScreening;
    }

    public FragebogenZumMedikationsScreeningObservation getFragebogenZumMedikationsScreening() {
        return this.fragebogenZumMedikationsScreening;
    }

    public void setGesundheitsbezogenerBeruf(GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf) {
        this.gesundheitsbezogenerBeruf = gesundheitsbezogenerBeruf;
    }

    public GesundheitsbezogenerBerufEvaluation getGesundheitsbezogenerBeruf() {
        return this.gesundheitsbezogenerBeruf;
    }

    public void setChronischeErkrankungen(ChronischeErkrankungenObservation chronischeErkrankungen) {
        this.chronischeErkrankungen = chronischeErkrankungen;
    }

    public ChronischeErkrankungenObservation getChronischeErkrankungen() {
        return this.chronischeErkrankungen;
    }

    public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
        this.problemDiagnose = problemDiagnose;
    }

    public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
        return this.problemDiagnose;
    }

    public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
        this.dienstleistung = dienstleistung;
    }

    public List<DienstleistungInstruction> getDienstleistung() {
        return this.dienstleistung;
    }

    public void setProblemDiganoseCoronovirus(ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus) {
        this.problemDiganoseCoronovirus = problemDiganoseCoronovirus;
    }

    public ProblemDiganoseCoronovirusEvaluation getProblemDiganoseCoronovirus() {
        return this.problemDiganoseCoronovirus;
    }

    public void setAndereAktuelleErkrankungen(AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen) {
        this.andereAktuelleErkrankungen = andereAktuelleErkrankungen;
    }

    public AndereAktuelleErkrankungenObservation getAndereAktuelleErkrankungen() {
        return this.andereAktuelleErkrankungen;
    }

    public void setBewertungDesGesundheitsrisikos(
            List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
        this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
    }

    public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
        return this.bewertungDesGesundheitsrisikos;
    }

    public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
        this.wohnsituation = wohnsituation;
    }

    public List<WohnsituationEvaluation> getWohnsituation() {
        return this.wohnsituation;
    }
}
