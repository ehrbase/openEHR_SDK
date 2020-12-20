package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.592024900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class AllgemeineAngabenSection implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']")
  private AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen;

  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']")
  private ChronischeErkrankungenObservation chronischeErkrankungen;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diagnose Description: Angaben über einen einzelnen
   * identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches
   * das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu
   * erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und
   * Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden
   * Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening Description: Aggregierte
   * Informationen über die Verabreichung oder die Einnahme eines bestimmten Medikaments oder einer
   * bestimmten Art/Klasse von Medikamenten bei oder während eines Ereignisses, wie z.B. einem
   * bestimmten Zeitpunkt oder einer bestimmten Zeitdauer.
   */
  @Path("/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
  private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf Description: Zusammenfassung oder
   * beständige Information zu aktuellen und früheren Jobs und / oder Rollen einer Person.
   */
  @Path(
      "/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']")
  private GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf;

  /**
   * Path: Bericht/Allgemeine Angaben/Wohnsituation Description: Die Umstände über eine Person, das
   * allein oder mit anderen zusammen lebt. Comment: Diese Information bietet einen Einblick in die
   * tägliche Unterstützung, zu der eine Person in ihrer häuslichen Umgebung - sowohl körperlich als
   * auch emotional - Zugang hat.
   */
  @Path("/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos Description: Bewertung der
   * möglichen und wahrscheinlichen gesundheitsschädigenden Wirkungen anhand der identifizierten
   * Risikofaktoren.
   */
  @Path("/items[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

  /**
   * Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus Description: Angaben über einen
   * einzelnen identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein
   * Problem, welches das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson
   * beeinträchtigt. Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis
   * nicht einfach zu erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp
   * werden Problem und Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad
   * und unterstützenden Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path(
      "/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']")
  private ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung Description: Antrag auf eine
   * gesundheitsbezogene Dienstleistung oder Aktivität, die von einem Kliniker, einer Klinik oder
   * einer Einrichtung erbracht werden soll.
   */
  @Path("/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  /** Path: Bericht/Allgemeine Angaben/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setAndereAktuelleErkrankungen(
      AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen) {
    this.andereAktuelleErkrankungen = andereAktuelleErkrankungen;
  }

  public AndereAktuelleErkrankungenObservation getAndereAktuelleErkrankungen() {
    return this.andereAktuelleErkrankungen;
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

  public void setFragebogenZumMedikationsScreening(
      FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening) {
    this.fragebogenZumMedikationsScreening = fragebogenZumMedikationsScreening;
  }

  public FragebogenZumMedikationsScreeningObservation getFragebogenZumMedikationsScreening() {
    return this.fragebogenZumMedikationsScreening;
  }

  public void setGesundheitsbezogenerBeruf(
      GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf) {
    this.gesundheitsbezogenerBeruf = gesundheitsbezogenerBeruf;
  }

  public GesundheitsbezogenerBerufEvaluation getGesundheitsbezogenerBeruf() {
    return this.gesundheitsbezogenerBeruf;
  }

  public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
    this.wohnsituation = wohnsituation;
  }

  public List<WohnsituationEvaluation> getWohnsituation() {
    return this.wohnsituation;
  }

  public void setBewertungDesGesundheitsrisikos(
      List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
    this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
  }

  public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
    return this.bewertungDesGesundheitsrisikos;
  }

  public void setProblemDiganoseCoronovirus(
      ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus) {
    this.problemDiganoseCoronovirus = problemDiganoseCoronovirus;
  }

  public ProblemDiganoseCoronovirusEvaluation getProblemDiganoseCoronovirus() {
    return this.problemDiganoseCoronovirus;
  }

  public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
    this.dienstleistung = dienstleistung;
  }

  public List<DienstleistungInstruction> getDienstleistung() {
    return this.dienstleistung;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
