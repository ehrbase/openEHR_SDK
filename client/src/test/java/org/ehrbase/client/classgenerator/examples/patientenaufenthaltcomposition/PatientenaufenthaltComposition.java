package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition;

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
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.AbteilungsfallCluster;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsfallCluster;
import org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition.VersorgungsortAdminEntry;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.event_summary.v0")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.891029400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("Patientenaufenthalt")
public class PatientenaufenthaltComposition implements CompositionEntity {
  /**
   * Path: Patientenaufenthalt/context/Versorgungsfall Description: Zur Erfassung von Details zur
   * Identifikation eines Falls im Gesundheitswesen.
   */
  @Path(
      "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0 and name/value='Versorgungsfall']")
  private VersorgungsfallCluster versorgungsfall;

  /**
   * Path: Patientenaufenthalt/context/Abteilungsfall Description: Zur Erfassung von Details zur
   * Identifikation eines Falls im Gesundheitswesen.
   */
  @Path(
      "/context/other_context[at0001]/items[openEHR-EHR-CLUSTER.case_identification.v0 and name/value='Abteilungsfall']")
  private AbteilungsfallCluster abteilungsfall;

  /** Path: Patientenaufenthalt/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: Patientenaufenthalt/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: Patientenaufenthalt/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: Patientenaufenthalt/context/location */
  @Path("/context/location")
  private String location;

  /** Path: Patientenaufenthalt/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: Patientenaufenthalt/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: Patientenaufenthalt/Versorgungsort Description: Zur Erfassung der administrativen
   * Aufenthaltsdaten eines Patienten.
   */
  @Path("/content[openEHR-EHR-ADMIN_ENTRY.hospitalization.v0 and name/value='Versorgungsort']")
  private VersorgungsortAdminEntry versorgungsort;

  /** Path: Patientenaufenthalt/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: Patientenaufenthalt/language */
  @Path("/language")
  private Language language;

  /** Path: Patientenaufenthalt/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Patientenaufenthalt/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: Patientenaufenthalt/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setVersorgungsfall(VersorgungsfallCluster versorgungsfall) {
    this.versorgungsfall = versorgungsfall;
  }

  public VersorgungsfallCluster getVersorgungsfall() {
    return this.versorgungsfall;
  }

  public void setAbteilungsfall(AbteilungsfallCluster abteilungsfall) {
    this.abteilungsfall = abteilungsfall;
  }

  public AbteilungsfallCluster getAbteilungsfall() {
    return this.abteilungsfall;
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

  public void setVersorgungsort(VersorgungsortAdminEntry versorgungsort) {
    this.versorgungsort = versorgungsort;
  }

  public VersorgungsortAdminEntry getVersorgungsort() {
    return this.versorgungsort;
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
