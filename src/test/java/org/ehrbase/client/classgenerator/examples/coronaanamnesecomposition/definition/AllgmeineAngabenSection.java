package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import java.util.List;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.allgemeine_angaben.v1")
public class AllgmeineAngabenSection {
  @Path("/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
  private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

  @Path("/items[openEHR-EHR-EVALUATION.occupation_summary.v1]")
  private ZusammenfassungDerBeschaftigungEvaluation zusammenfassungDerBeschaftigung;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_aktuelle.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen8 anzeichen;

  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<DiagnoseEvaluation> diagnose;

  @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  @Path("/items[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

  @Path("/items[openEHR-EHR-OBSERVATION.symptom_sign_screening_chronisch.v0]")
  private AnzeichenObservationScreeningFragebogenZurSymptomen9 anzeichenScreeningFragebogenZurSymptomen;

  @Path("/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]")
  private DiagnoseEvaluationProblem diagnoseProblem;



    @Path("/name")
  private DvText name;

  public void setFragebogenZumMedikationsScreening(
      FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening) {
     this.fragebogenZumMedikationsScreening = fragebogenZumMedikationsScreening;
  }

  public FragebogenZumMedikationsScreeningObservation getFragebogenZumMedikationsScreening() {
     return this.fragebogenZumMedikationsScreening ;
  }

  public void setZusammenfassungDerBeschaftigung(
      ZusammenfassungDerBeschaftigungEvaluation zusammenfassungDerBeschaftigung) {
     this.zusammenfassungDerBeschaftigung = zusammenfassungDerBeschaftigung;
  }

  public ZusammenfassungDerBeschaftigungEvaluation getZusammenfassungDerBeschaftigung() {
     return this.zusammenfassungDerBeschaftigung ;
  }

  public void setAnzeichen(AnzeichenObservationScreeningFragebogenZurSymptomen8 anzeichen) {
     this.anzeichen = anzeichen;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen8 getAnzeichen() {
     return this.anzeichen ;
  }

  public void setDiagnose(List<DiagnoseEvaluation> diagnose) {
     this.diagnose = diagnose;
  }

  public List<DiagnoseEvaluation> getDiagnose() {
     return this.diagnose ;
  }

  public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
     this.dienstleistung = dienstleistung;
  }

  public List<DienstleistungInstruction> getDienstleistung() {
     return this.dienstleistung ;
  }

  public void setBewertungDesGesundheitsrisikos(
      List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
     this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
  }

  public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
     return this.bewertungDesGesundheitsrisikos ;
  }

  public void setAnzeichenScreeningFragebogenZurSymptomen(
      AnzeichenObservationScreeningFragebogenZurSymptomen9 anzeichenScreeningFragebogenZurSymptomen) {
     this.anzeichenScreeningFragebogenZurSymptomen = anzeichenScreeningFragebogenZurSymptomen;
  }

  public AnzeichenObservationScreeningFragebogenZurSymptomen9 getAnzeichenScreeningFragebogenZurSymptomen(
      ) {
     return this.anzeichenScreeningFragebogenZurSymptomen ;
  }

  public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public List<WohnsituationEvaluation> getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setDiagnoseProblem(DiagnoseEvaluationProblem diagnoseProblem) {
     this.diagnoseProblem = diagnoseProblem;
  }

  public DiagnoseEvaluationProblem getDiagnoseProblem() {
     return this.diagnoseProblem ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
