package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;
import java.util.List;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
public class BloodPressureTrainingSampleObservation {
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double systolicMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String systolicUnits;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolicMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
  private String diastolicUnits;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
  private Double meanArterialPressureMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
  private String meanArterialPressureUnits;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
  private Double pulsePressureMagnitude;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
  private String pulsePressureUnits;

  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
  private String commentValue;

  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code")
  private PositionDefiningCode positionDefiningCode;

  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1030]")
  private List<Cluster> levelOfExertion;

  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
  private Double tiltMagnitude;

  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
  private String tiltUnits;

  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0011]/items[at0013]/value|defining_code")
  private CuffSizeDefiningCode cuffSizeDefiningCode;

  @Path("/protocol[at0011]/items[at0014]/value|defining_code")
  private LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode;

  @Path("/protocol[at0011]/items[at1010]/value|defining_code")
  private KorotkoffSoundsDefiningCode korotkoffSoundsDefiningCode;

  @Path("/protocol[at0011]/items[at1025]")
  private List<Cluster> device;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setSystolicMagnitude(Double systolicMagnitude) {
     this.systolicMagnitude = systolicMagnitude;
  }

  public Double getSystolicMagnitude() {
     return this.systolicMagnitude ;
  }

  public void setSystolicUnits(String systolicUnits) {
     this.systolicUnits = systolicUnits;
  }

  public String getSystolicUnits() {
     return this.systolicUnits ;
  }

  public void setDiastolicMagnitude(Double diastolicMagnitude) {
     this.diastolicMagnitude = diastolicMagnitude;
  }

  public Double getDiastolicMagnitude() {
     return this.diastolicMagnitude ;
  }

  public void setDiastolicUnits(String diastolicUnits) {
     this.diastolicUnits = diastolicUnits;
  }

  public String getDiastolicUnits() {
     return this.diastolicUnits ;
  }

  public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
     this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
  }

  public Double getMeanArterialPressureMagnitude() {
     return this.meanArterialPressureMagnitude ;
  }

  public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
     this.meanArterialPressureUnits = meanArterialPressureUnits;
  }

  public String getMeanArterialPressureUnits() {
     return this.meanArterialPressureUnits ;
  }

  public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
     this.pulsePressureMagnitude = pulsePressureMagnitude;
  }

  public Double getPulsePressureMagnitude() {
     return this.pulsePressureMagnitude ;
  }

  public void setPulsePressureUnits(String pulsePressureUnits) {
     this.pulsePressureUnits = pulsePressureUnits;
  }

  public String getPulsePressureUnits() {
     return this.pulsePressureUnits ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }

  public void setPositionDefiningCode(PositionDefiningCode positionDefiningCode) {
     this.positionDefiningCode = positionDefiningCode;
  }

  public PositionDefiningCode getPositionDefiningCode() {
     return this.positionDefiningCode ;
  }

  public void setLevelOfExertion(List<Cluster> levelOfExertion) {
     this.levelOfExertion = levelOfExertion;
  }

  public List<Cluster> getLevelOfExertion() {
     return this.levelOfExertion ;
  }

  public void setTiltMagnitude(Double tiltMagnitude) {
     this.tiltMagnitude = tiltMagnitude;
  }

  public Double getTiltMagnitude() {
     return this.tiltMagnitude ;
  }

  public void setTiltUnits(String tiltUnits) {
     this.tiltUnits = tiltUnits;
  }

  public String getTiltUnits() {
     return this.tiltUnits ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setOriginValue(TemporalAccessor originValue) {
     this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
     return this.originValue ;
  }

  public void setCuffSizeDefiningCode(CuffSizeDefiningCode cuffSizeDefiningCode) {
     this.cuffSizeDefiningCode = cuffSizeDefiningCode;
  }

  public CuffSizeDefiningCode getCuffSizeDefiningCode() {
     return this.cuffSizeDefiningCode ;
  }

  public void setLocationOfMeasurementDefiningCode(
      LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode) {
     this.locationOfMeasurementDefiningCode = locationOfMeasurementDefiningCode;
  }

  public LocationOfMeasurementDefiningCode getLocationOfMeasurementDefiningCode() {
     return this.locationOfMeasurementDefiningCode ;
  }

  public void setKorotkoffSoundsDefiningCode(
      KorotkoffSoundsDefiningCode korotkoffSoundsDefiningCode) {
     this.korotkoffSoundsDefiningCode = korotkoffSoundsDefiningCode;
  }

  public KorotkoffSoundsDefiningCode getKorotkoffSoundsDefiningCode() {
     return this.korotkoffSoundsDefiningCode ;
  }

  public void setDevice(List<Cluster> device) {
     this.device = device;
  }

  public List<Cluster> getDevice() {
     return this.device ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }
}
