package org.ehrbase.client.classgenerator.examples.diagnosecomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
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
import org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition.ProblemDiagnoseEvaluation;
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
    date = "2020-12-10T13:06:12.339026+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("Diagnose")
public class DiagnoseComposition implements CompositionEntity {
  /**
   * Path: COVID-19-Diagnose/context/Bericht ID Description: Identifizierungsmerkmal des Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Path: COVID-19-Diagnose/context/Fallidentifikation Description: Zur Erfassung von Details zur
   * Identifikation eines Falls im Gesundheitswesen.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  private FallidentifikationCluster fallidentifikation;

  /** Path: COVID-19-Diagnose/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: COVID-19-Diagnose/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: COVID-19-Diagnose/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: COVID-19-Diagnose/context/location */
  @Path("/context/location")
  private String location;

  /** Path: COVID-19-Diagnose/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: COVID-19-Diagnose/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: COVID-19-Diagnose/Problem/Diagnose Description: Angaben über einen einzelnen
   * identifizierten Gesundheitszustand, eine Verletzung, eine Behinderung oder ein Problem, welches
   * das körperliche, geistige und/oder soziale Wohlergehen einer Einzelperson beeinträchtigt.
   * Comment: Eine klare Abgrenzung zwischen Problem und Diagnose ist in der Praxis nicht einfach zu
   * erreichen. Für die Zwecke der klinischen Dokumentation mit diesem Archetyp werden Problem und
   * Diagnose als ein Kontinuum betrachtet, mit zunehmendem Detaillierungsgrad und unterstützenden
   * Beweisen, die in der Regel dem Etikett "Diagnose" Gewicht verleihen.
   */
  @Path("/content[openEHR-EHR-EVALUATION.problem_diagnosis.v1]")
  private ProblemDiagnoseEvaluation problemDiagnose;

  /** Path: COVID-19-Diagnose/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: COVID-19-Diagnose/language */
  @Path("/language")
  private Language language;

  /** Path: COVID-19-Diagnose/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: COVID-19-Diagnose/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: COVID-19-Diagnose/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setBerichtIdValue(String berichtIdValue) {
    this.berichtIdValue = berichtIdValue;
  }

  public String getBerichtIdValue() {
    return this.berichtIdValue;
  }

  public void setFallidentifikation(FallidentifikationCluster fallidentifikation) {
    this.fallidentifikation = fallidentifikation;
  }

  public FallidentifikationCluster getFallidentifikation() {
    return this.fallidentifikation;
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

  public void setProblemDiagnose(ProblemDiagnoseEvaluation problemDiagnose) {
    this.problemDiagnose = problemDiagnose;
  }

  public ProblemDiagnoseEvaluation getProblemDiagnose() {
    return this.problemDiagnose;
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
