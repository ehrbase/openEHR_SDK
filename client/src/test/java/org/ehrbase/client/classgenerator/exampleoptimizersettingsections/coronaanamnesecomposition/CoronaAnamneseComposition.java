package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
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
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FieberOderErhohteKorpertemperaturObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.FragebogenZumMedikationsScreeningObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GeschichteHistorieObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestorterGeruchssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GestorterGeschmackssinnObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.GesundheitsbezogenerBerufEvaluation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HeiserkeitObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HistorieDerReiseObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.HustenObservation;
import org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition.KorpertemperaturObservation;
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
    date = "2020-12-09T11:37:53.366285600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@Template("Corona_Anamnese")
public class CoronaAnamneseComposition implements CompositionEntity {
  /**
   * Bericht/context/Bericht ID
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Bericht/context/Status
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Bericht/context/Erweiterung
   */
  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> erweiterung;

  /**
   * Bericht/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Bericht/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Bericht/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Bericht/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Bericht/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Bericht/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Bericht/Geschichte/Historie
   */
  @Path("/content[openEHR-EHR-OBSERVATION.story.v1]")
  private List<GeschichteHistorieObservation> geschichteHistorie;

  /**
   * Bericht/Symptome/Husten
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Husten']")
  private HustenObservation husten;

  /**
   * Bericht/Symptome/Schnupfen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Schnupfen']")
  private SchnupfenObservation schnupfen;

  /**
   * Bericht/Symptome/Heiserkeit
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Heiserkeit']")
  private HeiserkeitObservation heiserkeit;

  /**
   * Bericht/Symptome/Fieber oder erhöhte Körpertemperatur
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Fieber oder erhöhte Körpertemperatur']")
  private FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur;

  /**
   * Bericht/Symptome/Körpertemperatur
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.body_temperature.v2]")
  private KorpertemperaturObservation korpertemperatur;

  /**
   * Bericht/Symptome/Gestörter Geruchssinn
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geruchssinn']")
  private GestorterGeruchssinnObservation gestorterGeruchssinn;

  /**
   * Bericht/Symptome/Gestörter Geschmackssinn
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Gestörter Geschmackssinn']")
  private GestorterGeschmackssinnObservation gestorterGeschmackssinn;

  /**
   * Bericht/Symptome/Durchfall
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Durchfall']")
  private DurchfallObservation durchfall;

  /**
   * Bericht/Symptome/Weitere Symptome
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Symptome']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Weitere Symptome']")
  private WeitereSymptomeObservation weitereSymptome;

  /**
   * Bericht/Kontakt/Personenkontakt
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
  private PersonenkontaktObservation personenkontakt;

  /**
   * Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Kontakt']/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
  private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

  /**
   * Bericht/Risikogebiet/Historie der Reise
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_history.v0]")
  private List<HistorieDerReiseObservation> historieDerReise;

  /**
   * Bericht/Risikogebiet/Reisefall
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Risikogebiet']/items[openEHR-EHR-OBSERVATION.travel_event.v0]")
  private List<ReisefallObservation> reisefall;

  /**
   * Bericht/Allgemeine Angaben/Andere aktuelle Erkrankungen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Andere aktuelle Erkrankungen']")
  private AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen;

  /**
   * Bericht/Allgemeine Angaben/Chronische Erkrankungen
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.symptom_sign_screening.v0 and name/value='Chronische Erkrankungen']")
  private ChronischeErkrankungenObservation chronischeErkrankungen;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diagnose
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private List<ProblemDiagnoseEvaluation> problemDiagnose;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-OBSERVATION.medication_use.v0]")
  private FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.occupation_summary.v1 and name/value='Gesundheitsbezogener Beruf']")
  private GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.living_arrangement.v0]")
  private List<WohnsituationEvaluation> wohnsituation;

  /**
   * Bericht/Allgemeine Angaben/Bewertung des Gesundheitsrisikos
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.health_risk.v1]")
  private List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos;

  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-EVALUATION.problem_diagnosis_covid.v1 and name/value='Problem/Diganose Coronovirus']")
  private ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus;

  /**
   * Bericht/Allgemeine Angaben/Dienstleistung
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Allgemeine Angaben']/items[openEHR-EHR-INSTRUCTION.service_request.v1]")
  private List<DienstleistungInstruction> dienstleistung;

  /**
   * Bericht/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Bericht/language
   */
  @Path("/language")
  private Language language;

  /**
   * Bericht/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Bericht/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setBerichtIdValue(String berichtIdValue) {
     this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
     return this.berichtIdValue ;
  }

  public void setStatusValue(String statusValue) {
     this.statusValue = statusValue;
  }

  public String getStatusValue() {
     return this.statusValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setGeschichteHistorie(List<GeschichteHistorieObservation> geschichteHistorie) {
     this.geschichteHistorie = geschichteHistorie;
  }

  public List<GeschichteHistorieObservation> getGeschichteHistorie() {
     return this.geschichteHistorie ;
  }

  public void setHusten(HustenObservation husten) {
     this.husten = husten;
  }

  public HustenObservation getHusten() {
     return this.husten ;
  }

  public void setSchnupfen(SchnupfenObservation schnupfen) {
     this.schnupfen = schnupfen;
  }

  public SchnupfenObservation getSchnupfen() {
     return this.schnupfen ;
  }

  public void setHeiserkeit(HeiserkeitObservation heiserkeit) {
     this.heiserkeit = heiserkeit;
  }

  public HeiserkeitObservation getHeiserkeit() {
     return this.heiserkeit ;
  }

  public void setFieberOderErhohteKorpertemperatur(
      FieberOderErhohteKorpertemperaturObservation fieberOderErhohteKorpertemperatur) {
     this.fieberOderErhohteKorpertemperatur = fieberOderErhohteKorpertemperatur;
  }

  public FieberOderErhohteKorpertemperaturObservation getFieberOderErhohteKorpertemperatur() {
     return this.fieberOderErhohteKorpertemperatur ;
  }

  public void setKorpertemperatur(KorpertemperaturObservation korpertemperatur) {
     this.korpertemperatur = korpertemperatur;
  }

  public KorpertemperaturObservation getKorpertemperatur() {
     return this.korpertemperatur ;
  }

  public void setGestorterGeruchssinn(GestorterGeruchssinnObservation gestorterGeruchssinn) {
     this.gestorterGeruchssinn = gestorterGeruchssinn;
  }

  public GestorterGeruchssinnObservation getGestorterGeruchssinn() {
     return this.gestorterGeruchssinn ;
  }

  public void setGestorterGeschmackssinn(
      GestorterGeschmackssinnObservation gestorterGeschmackssinn) {
     this.gestorterGeschmackssinn = gestorterGeschmackssinn;
  }

  public GestorterGeschmackssinnObservation getGestorterGeschmackssinn() {
     return this.gestorterGeschmackssinn ;
  }

  public void setDurchfall(DurchfallObservation durchfall) {
     this.durchfall = durchfall;
  }

  public DurchfallObservation getDurchfall() {
     return this.durchfall ;
  }

  public void setWeitereSymptome(WeitereSymptomeObservation weitereSymptome) {
     this.weitereSymptome = weitereSymptome;
  }

  public WeitereSymptomeObservation getWeitereSymptome() {
     return this.weitereSymptome ;
  }

  public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
     this.personenkontakt = personenkontakt;
  }

  public PersonenkontaktObservation getPersonenkontakt() {
     return this.personenkontakt ;
  }

  public void setAufenthaltInGesundheitseinrichtung(
      AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
     this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
  }

  public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
     return this.aufenthaltInGesundheitseinrichtung ;
  }

  public void setHistorieDerReise(List<HistorieDerReiseObservation> historieDerReise) {
     this.historieDerReise = historieDerReise;
  }

  public List<HistorieDerReiseObservation> getHistorieDerReise() {
     return this.historieDerReise ;
  }

  public void setReisefall(List<ReisefallObservation> reisefall) {
     this.reisefall = reisefall;
  }

  public List<ReisefallObservation> getReisefall() {
     return this.reisefall ;
  }

  public void setAndereAktuelleErkrankungen(
      AndereAktuelleErkrankungenObservation andereAktuelleErkrankungen) {
     this.andereAktuelleErkrankungen = andereAktuelleErkrankungen;
  }

  public AndereAktuelleErkrankungenObservation getAndereAktuelleErkrankungen() {
     return this.andereAktuelleErkrankungen ;
  }

  public void setChronischeErkrankungen(ChronischeErkrankungenObservation chronischeErkrankungen) {
     this.chronischeErkrankungen = chronischeErkrankungen;
  }

  public ChronischeErkrankungenObservation getChronischeErkrankungen() {
     return this.chronischeErkrankungen ;
  }

  public void setProblemDiagnose(List<ProblemDiagnoseEvaluation> problemDiagnose) {
     this.problemDiagnose = problemDiagnose;
  }

  public List<ProblemDiagnoseEvaluation> getProblemDiagnose() {
     return this.problemDiagnose ;
  }

  public void setFragebogenZumMedikationsScreening(
      FragebogenZumMedikationsScreeningObservation fragebogenZumMedikationsScreening) {
     this.fragebogenZumMedikationsScreening = fragebogenZumMedikationsScreening;
  }

  public FragebogenZumMedikationsScreeningObservation getFragebogenZumMedikationsScreening() {
     return this.fragebogenZumMedikationsScreening ;
  }

  public void setGesundheitsbezogenerBeruf(
      GesundheitsbezogenerBerufEvaluation gesundheitsbezogenerBeruf) {
     this.gesundheitsbezogenerBeruf = gesundheitsbezogenerBeruf;
  }

  public GesundheitsbezogenerBerufEvaluation getGesundheitsbezogenerBeruf() {
     return this.gesundheitsbezogenerBeruf ;
  }

  public void setWohnsituation(List<WohnsituationEvaluation> wohnsituation) {
     this.wohnsituation = wohnsituation;
  }

  public List<WohnsituationEvaluation> getWohnsituation() {
     return this.wohnsituation ;
  }

  public void setBewertungDesGesundheitsrisikos(
      List<BewertungDesGesundheitsrisikosEvaluation> bewertungDesGesundheitsrisikos) {
     this.bewertungDesGesundheitsrisikos = bewertungDesGesundheitsrisikos;
  }

  public List<BewertungDesGesundheitsrisikosEvaluation> getBewertungDesGesundheitsrisikos() {
     return this.bewertungDesGesundheitsrisikos ;
  }

  public void setProblemDiganoseCoronovirus(
      ProblemDiganoseCoronovirusEvaluation problemDiganoseCoronovirus) {
     this.problemDiganoseCoronovirus = problemDiganoseCoronovirus;
  }

  public ProblemDiganoseCoronovirusEvaluation getProblemDiganoseCoronovirus() {
     return this.problemDiganoseCoronovirus ;
  }

  public void setDienstleistung(List<DienstleistungInstruction> dienstleistung) {
     this.dienstleistung = dienstleistung;
  }

  public List<DienstleistungInstruction> getDienstleistung() {
     return this.dienstleistung ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
