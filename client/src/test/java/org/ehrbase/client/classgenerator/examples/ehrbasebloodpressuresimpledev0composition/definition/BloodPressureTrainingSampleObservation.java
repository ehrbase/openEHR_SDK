package org.ehrbase.client.classgenerator.examples.ehrbasebloodpressuresimpledev0composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.sample_blood_pressure.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T15:54:55.460274800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BloodPressureTrainingSampleObservation implements EntryEntity {
  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double systolicMagnitude;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String systolicUnits;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/blood
   * pressure/Systolic/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour systolicNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolicMagnitude;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
  private String diastolicUnits;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/blood
   * pressure/Diastolic/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour diastolicNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
  private Double meanArterialPressureMagnitude;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
  private String meanArterialPressureUnits;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/blood
   * pressure/Mean Arterial Pressure/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/null_flavour|defining_code")
  private NullFlavour meanArterialPressureNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
  private Double pulsePressureMagnitude;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
  private String pulsePressureUnits;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/blood
   * pressure/Pulse Pressure/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/null_flavour|defining_code")
  private NullFlavour pulsePressureNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
  private String commentValue;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/blood
   * pressure/Comment/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/null_flavour|defining_code")
  private NullFlavour commentNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code")
  private PositionDefiningCode positionDefiningCode;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/state
   * structure/Position/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/null_flavour|defining_code")
  private NullFlavour positionNullFlavourDefiningCode;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Level of Exertion
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1030]")
  private List<Cluster> levelOfExertion;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
  private Double tiltMagnitude;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/value */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
  private String tiltUnits;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/history/Baseline reading/state
   * structure/Tilt/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/null_flavour|defining_code")
  private NullFlavour tiltNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Encounter (training sample)/Blood pressure (Training sample)/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /** Encounter (training sample)/Blood pressure (Training sample)/value */
  @Path("/protocol[at0011]/items[at0013]/value|defining_code")
  private CuffSizeDefiningCode cuffSizeDefiningCode;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/list structure/Cuff
   * size/null_flavour
   */
  @Path("/protocol[at0011]/items[at0013]/null_flavour|defining_code")
  private NullFlavour cuffSizeNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/value */
  @Path("/protocol[at0011]/items[at0014]/value|defining_code")
  private LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/list structure/Location of
   * measurement/null_flavour
   */
  @Path("/protocol[at0011]/items[at0014]/null_flavour|defining_code")
  private NullFlavour locationOfMeasurementNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/value */
  @Path("/protocol[at0011]/items[at1010]/value|defining_code")
  private KorotkoffSoundsDefiningCode korotkoffSoundsDefiningCode;

  /**
   * Encounter (training sample)/Blood pressure (Training sample)/list structure/Korotkoff
   * sounds/null_flavour
   */
  @Path("/protocol[at0011]/items[at1010]/null_flavour|defining_code")
  private NullFlavour korotkoffSoundsNullFlavourDefiningCode;

  /** Encounter (training sample)/Blood pressure (Training sample)/Device */
  @Path("/protocol[at0011]/items[at1025]")
  private List<Cluster> device;

  /** Encounter (training sample)/Blood pressure (Training sample)/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Encounter (training sample)/Blood pressure (Training sample)/language */
  @Path("/language")
  private Language language;

  /** Encounter (training sample)/Blood pressure (Training sample)/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setSystolicMagnitude(Double systolicMagnitude) {
    this.systolicMagnitude = systolicMagnitude;
  }

  public Double getSystolicMagnitude() {
    return this.systolicMagnitude;
  }

  public void setSystolicUnits(String systolicUnits) {
    this.systolicUnits = systolicUnits;
  }

  public String getSystolicUnits() {
    return this.systolicUnits;
  }

  public void setSystolicNullFlavourDefiningCode(NullFlavour systolicNullFlavourDefiningCode) {
    this.systolicNullFlavourDefiningCode = systolicNullFlavourDefiningCode;
  }

  public NullFlavour getSystolicNullFlavourDefiningCode() {
    return this.systolicNullFlavourDefiningCode;
  }

  public void setDiastolicMagnitude(Double diastolicMagnitude) {
    this.diastolicMagnitude = diastolicMagnitude;
  }

  public Double getDiastolicMagnitude() {
    return this.diastolicMagnitude;
  }

  public void setDiastolicUnits(String diastolicUnits) {
    this.diastolicUnits = diastolicUnits;
  }

  public String getDiastolicUnits() {
    return this.diastolicUnits;
  }

  public void setDiastolicNullFlavourDefiningCode(NullFlavour diastolicNullFlavourDefiningCode) {
    this.diastolicNullFlavourDefiningCode = diastolicNullFlavourDefiningCode;
  }

  public NullFlavour getDiastolicNullFlavourDefiningCode() {
    return this.diastolicNullFlavourDefiningCode;
  }

  public void setMeanArterialPressureMagnitude(Double meanArterialPressureMagnitude) {
    this.meanArterialPressureMagnitude = meanArterialPressureMagnitude;
  }

  public Double getMeanArterialPressureMagnitude() {
    return this.meanArterialPressureMagnitude;
  }

  public void setMeanArterialPressureUnits(String meanArterialPressureUnits) {
    this.meanArterialPressureUnits = meanArterialPressureUnits;
  }

  public String getMeanArterialPressureUnits() {
    return this.meanArterialPressureUnits;
  }

  public void setMeanArterialPressureNullFlavourDefiningCode(
      NullFlavour meanArterialPressureNullFlavourDefiningCode) {
    this.meanArterialPressureNullFlavourDefiningCode = meanArterialPressureNullFlavourDefiningCode;
  }

  public NullFlavour getMeanArterialPressureNullFlavourDefiningCode() {
    return this.meanArterialPressureNullFlavourDefiningCode;
  }

  public void setPulsePressureMagnitude(Double pulsePressureMagnitude) {
    this.pulsePressureMagnitude = pulsePressureMagnitude;
  }

  public Double getPulsePressureMagnitude() {
    return this.pulsePressureMagnitude;
  }

  public void setPulsePressureUnits(String pulsePressureUnits) {
    this.pulsePressureUnits = pulsePressureUnits;
  }

  public String getPulsePressureUnits() {
    return this.pulsePressureUnits;
  }

  public void setPulsePressureNullFlavourDefiningCode(
      NullFlavour pulsePressureNullFlavourDefiningCode) {
    this.pulsePressureNullFlavourDefiningCode = pulsePressureNullFlavourDefiningCode;
  }

  public NullFlavour getPulsePressureNullFlavourDefiningCode() {
    return this.pulsePressureNullFlavourDefiningCode;
  }

  public void setCommentValue(String commentValue) {
    this.commentValue = commentValue;
  }

  public String getCommentValue() {
    return this.commentValue;
  }

  public void setCommentNullFlavourDefiningCode(NullFlavour commentNullFlavourDefiningCode) {
    this.commentNullFlavourDefiningCode = commentNullFlavourDefiningCode;
  }

  public NullFlavour getCommentNullFlavourDefiningCode() {
    return this.commentNullFlavourDefiningCode;
  }

  public void setPositionDefiningCode(PositionDefiningCode positionDefiningCode) {
    this.positionDefiningCode = positionDefiningCode;
  }

  public PositionDefiningCode getPositionDefiningCode() {
    return this.positionDefiningCode;
  }

  public void setPositionNullFlavourDefiningCode(NullFlavour positionNullFlavourDefiningCode) {
    this.positionNullFlavourDefiningCode = positionNullFlavourDefiningCode;
  }

  public NullFlavour getPositionNullFlavourDefiningCode() {
    return this.positionNullFlavourDefiningCode;
  }

  public void setLevelOfExertion(List<Cluster> levelOfExertion) {
    this.levelOfExertion = levelOfExertion;
  }

  public List<Cluster> getLevelOfExertion() {
    return this.levelOfExertion;
  }

  public void setTiltMagnitude(Double tiltMagnitude) {
    this.tiltMagnitude = tiltMagnitude;
  }

  public Double getTiltMagnitude() {
    return this.tiltMagnitude;
  }

  public void setTiltUnits(String tiltUnits) {
    this.tiltUnits = tiltUnits;
  }

  public String getTiltUnits() {
    return this.tiltUnits;
  }

  public void setTiltNullFlavourDefiningCode(NullFlavour tiltNullFlavourDefiningCode) {
    this.tiltNullFlavourDefiningCode = tiltNullFlavourDefiningCode;
  }

  public NullFlavour getTiltNullFlavourDefiningCode() {
    return this.tiltNullFlavourDefiningCode;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setOriginValue(TemporalAccessor originValue) {
    this.originValue = originValue;
  }

  public TemporalAccessor getOriginValue() {
    return this.originValue;
  }

  public void setCuffSizeDefiningCode(CuffSizeDefiningCode cuffSizeDefiningCode) {
    this.cuffSizeDefiningCode = cuffSizeDefiningCode;
  }

  public CuffSizeDefiningCode getCuffSizeDefiningCode() {
    return this.cuffSizeDefiningCode;
  }

  public void setCuffSizeNullFlavourDefiningCode(NullFlavour cuffSizeNullFlavourDefiningCode) {
    this.cuffSizeNullFlavourDefiningCode = cuffSizeNullFlavourDefiningCode;
  }

  public NullFlavour getCuffSizeNullFlavourDefiningCode() {
    return this.cuffSizeNullFlavourDefiningCode;
  }

  public void setLocationOfMeasurementDefiningCode(
      LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode) {
    this.locationOfMeasurementDefiningCode = locationOfMeasurementDefiningCode;
  }

  public LocationOfMeasurementDefiningCode getLocationOfMeasurementDefiningCode() {
    return this.locationOfMeasurementDefiningCode;
  }

  public void setLocationOfMeasurementNullFlavourDefiningCode(
      NullFlavour locationOfMeasurementNullFlavourDefiningCode) {
    this.locationOfMeasurementNullFlavourDefiningCode =
        locationOfMeasurementNullFlavourDefiningCode;
  }

  public NullFlavour getLocationOfMeasurementNullFlavourDefiningCode() {
    return this.locationOfMeasurementNullFlavourDefiningCode;
  }

  public void setKorotkoffSoundsDefiningCode(
      KorotkoffSoundsDefiningCode korotkoffSoundsDefiningCode) {
    this.korotkoffSoundsDefiningCode = korotkoffSoundsDefiningCode;
  }

  public KorotkoffSoundsDefiningCode getKorotkoffSoundsDefiningCode() {
    return this.korotkoffSoundsDefiningCode;
  }

  public void setKorotkoffSoundsNullFlavourDefiningCode(
      NullFlavour korotkoffSoundsNullFlavourDefiningCode) {
    this.korotkoffSoundsNullFlavourDefiningCode = korotkoffSoundsNullFlavourDefiningCode;
  }

  public NullFlavour getKorotkoffSoundsNullFlavourDefiningCode() {
    return this.korotkoffSoundsNullFlavourDefiningCode;
  }

  public void setDevice(List<Cluster> device) {
    this.device = device;
  }

  public List<Cluster> getDevice() {
    return this.device;
  }

  public void setSubject(PartyProxy subject) {
    this.subject = subject;
  }

  public PartyProxy getSubject() {
    return this.subject;
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
}
