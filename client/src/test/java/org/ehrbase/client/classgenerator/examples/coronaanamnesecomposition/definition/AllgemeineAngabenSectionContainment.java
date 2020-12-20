package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AllgemeineAngabenSectionContainment extends Containment {
  public SelectAqlField<AllgemeineAngabenSection> ALLGEMEINE_ANGABEN_SECTION =
      new AqlFieldImp<AllgemeineAngabenSection>(
          AllgemeineAngabenSection.class,
          "",
          "AllgemeineAngabenSection",
          AllgemeineAngabenSection.class,
          this);

  public SelectAqlField<AndereAktuelleErkrankungenObservation> ANDERE_AKTUELLE_ERKRANKUNGEN =
      new AqlFieldImp<AndereAktuelleErkrankungenObservation>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
          "andereAktuelleErkrankungen",
          AndereAktuelleErkrankungenObservation.class,
          this);

  public SelectAqlField<ChronischeErkrankungenObservation> CHRONISCHE_ERKRANKUNGEN =
      new AqlFieldImp<ChronischeErkrankungenObservation>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0]",
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

  public SelectAqlField<FragebogenZumMedikationsScreeningObservation>
      FRAGEBOGEN_ZUM_MEDIKATIONS_SCREENING =
          new AqlFieldImp<FragebogenZumMedikationsScreeningObservation>(
              AllgemeineAngabenSection.class,
              "/items[openEHR-EHR-OBSERVATION.medication_use.v0]",
              "fragebogenZumMedikationsScreening",
              FragebogenZumMedikationsScreeningObservation.class,
              this);

  public SelectAqlField<GesundheitsbezogenerBerufEvaluation> GESUNDHEITSBEZOGENER_BERUF =
      new AqlFieldImp<GesundheitsbezogenerBerufEvaluation>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-EVALUATION.occupation_summary.v1]",
          "gesundheitsbezogenerBeruf",
          GesundheitsbezogenerBerufEvaluation.class,
          this);

  public ListSelectAqlField<WohnsituationEvaluation> WOHNSITUATION =
      new ListAqlFieldImp<WohnsituationEvaluation>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-EVALUATION.living_arrangement.v0]",
          "wohnsituation",
          WohnsituationEvaluation.class,
          this);

  public ListSelectAqlField<BewertungDesGesundheitsrisikosEvaluation>
      BEWERTUNG_DES_GESUNDHEITSRISIKOS =
          new ListAqlFieldImp<BewertungDesGesundheitsrisikosEvaluation>(
              AllgemeineAngabenSection.class,
              "/items[openEHR-EHR-EVALUATION.health_risk.v1]",
              "bewertungDesGesundheitsrisikos",
              BewertungDesGesundheitsrisikosEvaluation.class,
              this);

  public SelectAqlField<ProblemDiganoseCoronovirusEvaluation> PROBLEM_DIGANOSE_CORONOVIRUS =
      new AqlFieldImp<ProblemDiganoseCoronovirusEvaluation>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1]",
          "problemDiganoseCoronovirus",
          ProblemDiganoseCoronovirusEvaluation.class,
          this);

  public ListSelectAqlField<DienstleistungInstruction> DIENSTLEISTUNG =
      new ListAqlFieldImp<DienstleistungInstruction>(
          AllgemeineAngabenSection.class,
          "/items[openEHR-EHR-INSTRUCTION.service_request.v1]",
          "dienstleistung",
          DienstleistungInstruction.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AllgemeineAngabenSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AllgemeineAngabenSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static AllgemeineAngabenSectionContainment getInstance() {
    return new AllgemeineAngabenSectionContainment();
  }
}
