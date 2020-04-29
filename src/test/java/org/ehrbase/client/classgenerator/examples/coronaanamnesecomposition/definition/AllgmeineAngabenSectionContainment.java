package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AllgmeineAngabenSectionContainment extends Containment {
  public SelectAqlField<AllgmeineAngabenSection> ALLGMEINE_ANGABEN_SECTION = new AqlFieldImp<AllgmeineAngabenSection>(AllgmeineAngabenSection.class, "", "AllgmeineAngabenSection", AllgmeineAngabenSection.class, this);

  public SelectAqlField<FragebogenZumMedikationsScreeningObservation> FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING = new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-OBSERVATION.medication_use.v0]", "fragebogenZumMedikationsScreening", FragebogenZumMedikationsScreeningObservation.class, this);

  public SelectAqlField<ZusammenfassungDerBeschaftigungEvaluation> ZUSAMMENFASSUNG_DER_BESCHAFTIGUNG = new AqlFieldImp<ZusammenfassungDerBeschaftigungEvaluation>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.occupation_summary.v1]", "zusammenfassungDerBeschaftigung", ZusammenfassungDerBeschaftigungEvaluation.class, this);

  public SelectAqlField<AnzeichenObservationScreeningFragebogenZurSymptomen8> ANZEICHEN = new AqlFieldImp<AnzeichenObservationScreeningFragebogenZurSymptomen8>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_aktuelle.v0]", "anzeichen", AnzeichenObservationScreeningFragebogenZurSymptomen8.class, this);

  public ListSelectAqlField<DiagnoseEvaluation> DIAGNOSE = new ListAqlFieldImp<DiagnoseEvaluation>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]", "diagnose", DiagnoseEvaluation.class, this);

  public ListSelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG = new ListAqlFieldImp<DienstleistungInstruction>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-INSTRUCTION.service_request.v1]", "dienstleistung", DienstleistungInstruction.class, this);

  public ListSelectAqlField<BewertungDesGesundheitsrisikosEvaluation> BEWERTUNG_DES_GESUNDHEITSRISIKOS = new ListAqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.health_risk.v1]", "bewertungDesGesundheitsrisikos", BewertungDesGesundheitsrisikosEvaluation.class, this);

  public SelectAqlField<AnzeichenObservationScreeningFragebogenZurSymptomen9> ANZEICHEN_SCREENING_FRAGEBOGEN_ZUR_SYMPTOMEN = new AqlFieldImp<AnzeichenObservationScreeningFragebogenZurSymptomen9>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_chronisch.v0]", "anzeichenScreeningFragebogenZurSymptomen", AnzeichenObservationScreeningFragebogenZurSymptomen9.class, this);

  public ListSelectAqlField<WohnsituationEvaluation> WOHNSITUATION = new ListAqlFieldImp<WohnsituationEvaluation>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.living_arrangement.v0]", "wohnsituation", WohnsituationEvaluation.class, this);

  public SelectAqlField<DiagnoseEvaluationProblem> DIAGNOSE_PROBLEM = new AqlFieldImp<DiagnoseEvaluationProblem>(AllgmeineAngabenSection.class, "/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]", "diagnoseProblem", DiagnoseEvaluationProblem.class, this);

  private AllgmeineAngabenSectionContainment() {
    super("openEHR-EHR-SECTION.allgemeine_angaben.v1");
  }

  public static AllgmeineAngabenSectionContainment getInstance() {
    return new AllgmeineAngabenSectionContainment();
  }
}
