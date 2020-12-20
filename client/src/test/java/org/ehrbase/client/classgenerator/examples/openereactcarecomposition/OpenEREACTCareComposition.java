package org.ehrbase.client.classgenerator.examples.openereactcarecomposition;

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
import org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition.AssessmentSection;
import org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition.BackgroundSection;
import org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition.ResponseSection;
import org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition.SituationSection;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.encounter.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.310500500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("open_eREACT-Care")
public class OpenEREACTCareComposition implements CompositionEntity {
  /**
   * Path: open_eREACT-Care/context/Extension Description: Additional information required to
   * capture local context or to align with other reference models/formalisms. Comment: e.g. Local
   * hospital departmental infomation or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: open_eREACT-Care/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: open_eREACT-Care/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: open_eREACT-Care/context/location */
  @Path("/context/location")
  private String location;

  /** Path: open_eREACT-Care/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: open_eREACT-Care/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: open_eREACT-Care/Situation Description: A generic section header which should be renamed
   * in a template to suit a specific clinical context.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Situation']")
  private SituationSection situation;

  /**
   * Path: open_eREACT-Care/Background Description: A generic section header which should be renamed
   * in a template to suit a specific clinical context.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Background']")
  private BackgroundSection background;

  /**
   * Path: open_eREACT-Care/Assessment Description: A generic section header which should be renamed
   * in a template to suit a specific clinical context.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Assessment']")
  private AssessmentSection assessment;

  /**
   * Path: open_eREACT-Care/Response Description: A generic section header which should be renamed
   * in a template to suit a specific clinical context.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Response']")
  private ResponseSection response;

  /** Path: open_eREACT-Care/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: open_eREACT-Care/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: open_eREACT-Care/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: open_eREACT-Care/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setExtension(List<Cluster> extension) {
    this.extension = extension;
  }

  public List<Cluster> getExtension() {
    return this.extension;
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

  public void setSituation(SituationSection situation) {
    this.situation = situation;
  }

  public SituationSection getSituation() {
    return this.situation;
  }

  public void setBackground(BackgroundSection background) {
    this.background = background;
  }

  public BackgroundSection getBackground() {
    return this.background;
  }

  public void setAssessment(AssessmentSection assessment) {
    this.assessment = assessment;
  }

  public AssessmentSection getAssessment() {
    return this.assessment;
  }

  public void setResponse(ResponseSection response) {
    this.response = response;
  }

  public ResponseSection getResponse() {
    return this.response;
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
