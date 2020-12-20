package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition;

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
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.BefundObservation;
import org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition.FallidentifikationCluster;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report-result.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.753036100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("Virologischer Befund")
public class VirologischerBefundComposition implements CompositionEntity {
  /**
   * Path: Virologischer Befund/context/Bericht-ID Description: Eindeutige Identifikation des
   * Berichts.
   */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String berichtIdValue;

  /**
   * Path: Virologischer Befund/context/Status Description: Status des gesamten Ergebnisberichts.
   * Hinweis: Dies ist nicht der Status einzelner Berichtskomponenten.
   */
  @Path("/context/other_context[at0001]/items[at0005]/value|value")
  private String statusValue;

  /**
   * Path: Virologischer Befund/context/Fallidentifikation Description: Zur Erfassung von Details
   * zur Identifikation eines Falls im Gesundheitswesen.
   */
  @Path("/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0]")
  private FallidentifikationCluster fallidentifikation;

  /** Path: Virologischer Befund/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: Virologischer Befund/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: Virologischer Befund/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: Virologischer Befund/context/location */
  @Path("/context/location")
  private String location;

  /** Path: Virologischer Befund/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: Virologischer Befund/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Virologischer Befund/Befund Description: Das Ergebnis - einschließlich der Befunde und
   * der Interpretation des Labors - einer Untersuchung, die an Proben durchgeführt wurde, die von
   * einer Einzelperson stammen oder mit dieser Person zusammenhängen.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Befund']")
  private BefundObservation befund;

  /** Path: Virologischer Befund/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: Virologischer Befund/language */
  @Path("/language")
  private Language language;

  /** Path: Virologischer Befund/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Virologischer Befund/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: Virologischer Befund/territory */
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

  public void setBefund(BefundObservation befund) {
    this.befund = befund;
  }

  public BefundObservation getBefund() {
    return this.befund;
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
