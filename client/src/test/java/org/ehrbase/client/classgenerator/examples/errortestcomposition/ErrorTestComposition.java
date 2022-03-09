package org.ehrbase.client.classgenerator.examples.errortestcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.*;
import org.ehrbase.client.classgenerator.examples.errortestcomposition.definition.LaboratoryTestResultObservation;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

import javax.annotation.processing.Generated;
import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.report.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2022-03-02T14:11:00.795599800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@Template("ErrorTest")
public class ErrorTestComposition implements CompositionEntity {
  /** Path: ErrorTest/category */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /** Path: ErrorTest/context/Report ID Description: Identification information about the report. */
  @Path("/context/other_context[at0001]/items[at0002]/value|value")
  private String reportIdValue;

  /**
   * Path: ErrorTest/context/Extension Description: Additional information required to capture local
   * context or to align with other reference models/formalisms. Comment: For example: local
   * information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/context/other_context[at0001]/items[at0006]")
  private List<Cluster> extension;

  /** Path: ErrorTest/context/start_time */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /** Path: ErrorTest/context/participations */
  @Path("/context/participations")
  private List<Participation> participations;

  /** Path: ErrorTest/context/end_time */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /** Path: ErrorTest/context/location */
  @Path("/context/location")
  private String location;

  /** Path: ErrorTest/context/health_care_facility */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /** Path: ErrorTest/context/setting */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: ErrorTest/Laboratory test result Description: The result, including findings and the
   * laboratory's interpretation, of an investigation performed on specimens collected from an
   * individual or related to that individual.
   */
  @Path("/content[openEHR-EHR-OBSERVATION.laboratory_test_result.v1]")
  private LaboratoryTestResultObservation laboratoryTestResult;

  /** Path: ErrorTest/composer */
  @Path("/composer")
  private PartyProxy composer;

  /** Path: ErrorTest/language */
  @Path("/language")
  private Language language;

  /** Path: ErrorTest/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: ErrorTest/territory */
  @Path("/territory")
  private Territory territory;

  @Id private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
    this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
    return this.categoryDefiningCode;
  }

  public void setReportIdValue(String reportIdValue) {
    this.reportIdValue = reportIdValue;
  }

  public String getReportIdValue() {
    return this.reportIdValue;
  }

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

  public void setLaboratoryTestResult(LaboratoryTestResultObservation laboratoryTestResult) {
    this.laboratoryTestResult = laboratoryTestResult;
  }

  public LaboratoryTestResultObservation getLaboratoryTestResult() {
    return this.laboratoryTestResult;
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
