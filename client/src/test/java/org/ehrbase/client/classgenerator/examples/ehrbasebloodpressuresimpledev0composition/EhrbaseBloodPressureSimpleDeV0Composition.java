package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.BloodPressureTrainingSampleObservation;
import org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition.DeviceDetailsTrainingSampleCluster;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.sample_encounter.v1")
@Template("ehrbase_blood_pressure_simple.de.v0")
public class EhrbaseBloodPressureSimpleDeV0Composition {
  @Path("/context/other_context[at0001]/items[at0006]/items[openEHR-EHR-CLUSTER.sample_device.v1]")
  private List<DeviceDetailsTrainingSampleCluster> deviceDetailsTrainingSample;

  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  @Path("/context/participations")
  private List<Participation> participations;

  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  @Path("/context/location")
  private String location;

  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  @Path("/content[openEHR-EHR-OBSERVATION.sample_blood_pressure.v1]")
  private List<BloodPressureTrainingSampleObservation> bloodPressureTrainingSample;

  @Path("/composer")
  private PartyProxy composer;

  @Path("/language")
  private Language language;

  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setDeviceDetailsTrainingSample(
      List<DeviceDetailsTrainingSampleCluster> deviceDetailsTrainingSample) {
     this.deviceDetailsTrainingSample = deviceDetailsTrainingSample;
  }

  public List<DeviceDetailsTrainingSampleCluster> getDeviceDetailsTrainingSample() {
     return this.deviceDetailsTrainingSample ;
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

  public void setBloodPressureTrainingSample(
      List<BloodPressureTrainingSampleObservation> bloodPressureTrainingSample) {
     this.bloodPressureTrainingSample = bloodPressureTrainingSample;
  }

  public List<BloodPressureTrainingSampleObservation> getBloodPressureTrainingSample() {
     return this.bloodPressureTrainingSample ;
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
