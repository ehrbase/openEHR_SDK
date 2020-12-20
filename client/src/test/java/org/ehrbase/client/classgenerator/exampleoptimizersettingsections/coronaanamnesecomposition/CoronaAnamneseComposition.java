package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.AndereAktuelleErkrankungenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.AufenthaltInGesundheitseinrichtungObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.BewertungDesGesundheitsrisikosEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ChronischeErkrankungenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.DienstleistungInstruction;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.DurchfallObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FieberOderErhoehteKoerpertemperaturObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FragebogenZumMedikationsScreeningObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestoerterGeruchssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestoerterGeschmackssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GesundheitsbezogenerBerufEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HeiserkeitObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HistorieDerReiseObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HustenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.KoerpertemperaturObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.PersonenkontaktObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ProblemDiagnoseEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ProblemDiganoseCoronovirusEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.ReisefallObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.SchnupfenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.WeitereSymptomeObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.WohnsituationEvaluation;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.359036500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("Corona_Anamnese")
public class CoronaAnamneseComposition implements CompositionEntity {
  /** Path: Bericht/context/Bericht ID Description: Identifizierungsmerkmal des Berichts. */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Path: Bericht/context/Status Description: Der Status des gesamten Berichts. Hinweis: Dies ist
   * nicht der Status einer Berichtskomponente.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Path: Bericht/context/Erweiterung Description: Zusätzliche Informationen zur Erfassung lokaler
   * Inhalte oder Anpassung an andere Referenzmodelle/Formalismen. Comment: Zum Beispiel: lokaler
   * Informationsbedarf oder zusätzliche Metadaten zur Anpassung an FHIR-Ressourcen oder
   * CIMI-Modelle.
   */
  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> erweiterung;

  /** Path: Bericht/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: Bericht/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: Bericht/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: Bericht/context/location */
  @Path("/context/location")
  private String location;

  /** Path: Bericht/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: Bericht/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Bericht/Geschichte/Historie Description: Die subjektive klinische Vorgeschichte des
   * Pflegebedürftigen, wie sie direkt von der Person erfasst oder einem Kliniker von der Person
   * oder einem Pfleger gemeldet wurde.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<GeschichteHistorieObservation> geschichteHistorie;

  /**
   * Path: Bericht/Symptome/Husten Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
  private HustenObservation husten;

  /**
   * Path: Bericht/Symptome/Schnupfen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
  private SchnupfenObservation schnupfen;

  /**
   * Path: Bericht/Symptome/Heiserkeit Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
  private HeiserkeitObservation heiserkeit;

  /**
   * Path: Bericht/Symptome/Fieber oder erhöhte Körpertemperatur Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
  private FieberOderErhoehteKoerpertemperaturObservation fieberOderErhoehteKoerpertemperatur;

  /**
   * Path: Bericht/Symptome/Körpertemperatur Description: Eine Messung der Körpertemperatur, als
   * Surrogat für die Temperatur des gesamten Körper der Person.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KoerpertemperaturObservation koerpertemperatur;

  /**
   * Path: Bericht/Symptome/Gestörter Geruchssinn Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
  private GestoerterGeruchssinnObservation gestoerterGeruchssinn;

  /**
   * Path: Bericht/Symptome/Gestörter Geschmackssinn Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
  private GestoerterGeschmackssinnObservation gestoerterGeschmackssinn;

  /**
   * Path: Bericht/Symptome/Durchfall Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
  private DurchfallObservation durchfall;

  /**
   * Path: Bericht/Symptome/Weitere Symptome Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
  private WeitereSymptomeObservation weitereSymptome;

  /**
   * Path: Bericht/Kontakt/Personenkontakt Description: *An individual- or self-reported
   * questionnaire screening for exposure to a chemical, physical or biological agent which has
   * caused or may cause harm to an individual.(en)
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
  private PersonenkontaktObservation personenkontakt;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung Description: *An individual- or
   * self-reported questionnaire screening for exposure to a chemical, physical or biological agent
   * which has caused or may cause harm to an individual.(en)
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
  private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

  /**
   * Path: Bericht/Risikogebiet/Historie der Reise Description: Einzelheiten einer Reise im Hinblick
   * auf die Exposition gegenüber potenziellen Risiken.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
  private List<HistorieDerReiseObservation> historieDerReise;

  /**
   * Path: Bericht/Risikogebiet/Reisefall Description: Details zur Reise während eines bestimmten
   * Zeitraums.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<ReisefallObservation> reisefall;

  /**
   * Path: Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']")
  private AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen;

  /**
   * Path: Bericht/Allgemeine Angaben/Chronische Erkrankungen Description: Ein Personen- oder
   * Selbstbeurteilungs-Screening-Fragebogen zur Ermittlung von Symptomen und Anzeichen.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']")
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
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening Description: Aggregierte
   * Informationen über die Verabreichung oder die Einnahme eines bestimmten Medikaments oder einer
   * bestimmten Art/Klasse von Medikamenten bei oder während eines Ereignisses, wie z.B. einem
   * bestimmten Zeitpunkt oder einer bestimmten Zeitdauer.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
  private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

  /**
   * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf Description: Zusammenfassung oder
   * beständige Information zu aktuellen und früheren Jobs und / oder Rollen einer Person.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']")
  private GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf;

  /**
   * Path: Bericht/Allgemeine Angaben/Wohnsituation Description: Die Umstände über eine Person, das
   * allein oder mit anderen zusammen lebt. Comment: Diese Information bietet einen Einblick in die
   * tägliche Unterstützung, zu der eine Person in ihrer häuslichen Umgebung - sowohl körperlich als
   * auch emotional - Zugang hat.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  /**
   * Path: Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos Description: Bewertung der
   * möglichen und wahrscheinlichen gesundheitsschädigenden Wirkungen anhand der identifizierten
   * Risikofaktoren.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.health_risk.v1]")
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
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']")
  private ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus;

  /**
   * Path: Bericht/Allgemeine Angaben/Dienstleistung Description: Antrag auf eine
   * gesundheitsbezogene Dienstleistung oder Aktivität, die von einem Kliniker, einer Klinik oder
   * einer Einrichtung erbracht werden soll.
   */
  @Path(
      "/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  /** Path: Bericht/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: Bericht/language */
  @Path("/language")
  private Language language;

  /** Path: Bericht/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Bericht/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: Bericht/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setBerichtIdValue(String berichtIdValue) {
    this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
    return this.berichtIdValue;
  }

  public void setStatusValue(String statusValue) {
    this.statusValue = statusValue;
  }

  public String getStatusValue() {
    return this.statusValue;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
    this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
    return this.erweiterung;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
    this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
    return this.startTimeValue;
  }

  public void setParticipations(List<Participation> participations) {
    this.participations = participations;
  }

  public List<Participation> getParticipations() {
    return this.participations;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
    this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
    return this.endTimeValue;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
    this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
    return this.healthCareFacility;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
    this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
    return this.settingDefiningCode;
  }

  public void setGeschichteHistorie(List<GeschichteHistorieObservation> geschichteHistorie) {
    this.geschichteHistorie = geschichteHistorie;
  }

  public List<GeschichteHistorieObservation> getGeschichteHistorie() {
    return this.geschichteHistorie;
  }

  public void setHusten(HustenObservation husten) {
    this.husten = husten;
  }

  public HustenObservation getHusten() {
    return this.husten;
  }

  public void setSchnupfen(SchnupfenObservation schnupfen) {
    this.schnupfen = schnupfen;
  }

  public SchnupfenObservation getSchnupfen() {
    return this.schnupfen;
  }

  public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
    this.heiserkeit = heiserkeit;
  }

  public HeiserkeitObservation getHeiserkeit() {
    return this.heiserkeit;
  }

  public void setFieberOderErhoehteKoerpertemperatur(
      FieberOderErhoehteKoerpertemperaturObservation fieberOderErhoehteKoerpertemperatur) {
    this.fieberOderErhoehteKoerpertemperatur = fieberOderErhoehteKoerpertemperatur;
  }

  public FieberOderErhoehteKoerpertemperaturObservation getFieberOderErhoehteKoerpertemperatur() {
    return this.fieberOderErhoehteKoerpertemperatur;
  }

  public void setKoerpertemperatur(KoerpertemperaturObservation koerpertemperatur) {
    this.koerpertemperatur = koerpertemperatur;
  }

  public KoerpertemperaturObservation getKoerpertemperatur() {
    return this.koerpertemperatur;
  }

  public void setGestoerterGeruchssinn(GestoerterGeruchssinnObservation gestoerterGeruchssinn) {
    this.gestoerterGeruchssinn = gestoerterGeruchssinn;
  }

  public GestoerterGeruchssinnObservation getGestoerterGeruchssinn() {
    return this.gestoerterGeruchssinn;
  }

  public void setGestoerterGeschmackssinn(
      GestoerterGeschmackssinnObservation gestoerterGeschmackssinn) {
    this.gestoerterGeschmackssinn = gestoerterGeschmackssinn;
  }

  public GestoerterGeschmackssinnObservation getGestoerterGeschmackssinn() {
    return this.gestoerterGeschmackssinn;
  }

  public void setDurchfall(DurchfallObservation durchfall) {
    this.durchfall = durchfall;
  }

  public DurchfallObservation getDurchfall() {
    return this.durchfall;
  }

  public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
    this.weitereSymptome = weitereSymptome;
  }

  public WeitereSymptomeObservation getWeitereSymptome() {
    return this.weitereSymptome;
  }

  public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
    this.personenkontakt = personenkontakt;
  }

  public PersonenkontaktObservation getPersonenkontakt() {
    return this.personenkontakt;
  }

  public void setAufenthaltInGesundheitseinrichtung(
      AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
    this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
  }

  public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
    return this.aufenthaltInGesundheitseinrichtung;
  }

  public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
    this.historieDerReise = historieDerReise;
  }

  public List<HistorieDerReiseObservation> getHistorieDerReise() {
    return this.historieDerReise;
  }

  public void setReisefall(List<ReisefallObservation> reisefall) {
    this.reisefall = reisefall;
  }

  public List<ReisefallObservation> getReisefall() {
    return this.reisefall;
  }

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

  public void setComposer(PartyProxy composer) {
    this.composer = composer;
  }

  public PartyProxy getComposer() {
    return this.composer;
  }

  public void setLanguage(Language language) {
    this.language = language;
  }

  public Language getLanguage() {
    return this.language;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
    this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
    return this.categoryDefiningCode;
  }

  public void setTerritory(Territory territory) {
    this.territory = territory;
  }

  public Territory getTerritory() {
    return this.territory;
  }

  public VersionUid getVersionUid() {
    return this.versionUid;
  }

  public void setVersionUid(VersionUid versionUid) {
    this.versionUid = versionUid;
  }
}
