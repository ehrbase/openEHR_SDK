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
    date = "2020-12-10T13:06:10.947499100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BloodPressureTrainingSampleObservation implements EntryEntity {
  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Systolic
   * Description: Peak systemic arterial blood pressure over one cycle - measured in systolic or
   * contraction phase of the heart cycle
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|magnitude")
  private Double systolicMagnitude;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Systolic
   * Description: Peak systemic arterial blood pressure over one cycle - measured in systolic or
   * contraction phase of the heart cycle
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/value|units")
  private String systolicUnits;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/blood pressure/Systolic/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0004]/null_flavour|defining_code")
  private NullFlavour systolicNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Diastolic
   * Description: Minimum systemic arterial blood pressure over one cycle - measured in the
   * diastolic or relaxation phase
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolicMagnitude;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Diastolic
   * Description: Minimum systemic arterial blood pressure over one cycle - measured in the
   * diastolic or relaxation phase
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/value|units")
  private String diastolicUnits;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/blood pressure/Diastolic/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0005]/null_flavour|defining_code")
  private NullFlavour diastolicNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Mean
   * Arterial Pressure Description: The average arterial pressure that occurs over the entire course
   * of the heart contraction and relaxation cycle. In non-invasive blood pressure measurements the
   * MAP is calculated using (2 x Systolic Blood Pressure + Diastolic Blood Pressure) divided by 3.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|magnitude")
  private Double meanArterialPressureMagnitude;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Mean
   * Arterial Pressure Description: The average arterial pressure that occurs over the entire course
   * of the heart contraction and relaxation cycle. In non-invasive blood pressure measurements the
   * MAP is calculated using (2 x Systolic Blood Pressure + Diastolic Blood Pressure) divided by 3.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/value|units")
  private String meanArterialPressureUnits;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/blood pressure/Mean Arterial Pressure/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1006]/null_flavour|defining_code")
  private NullFlavour meanArterialPressureNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Pulse
   * Pressure Description: The difference between the systolic and diastolic pressure over one
   * contraction cycle.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|magnitude")
  private Double pulsePressureMagnitude;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Pulse
   * Pressure Description: The difference between the systolic and diastolic pressure over one
   * contraction cycle.
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/value|units")
  private String pulsePressureUnits;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/blood pressure/Pulse Pressure/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at1007]/null_flavour|defining_code")
  private NullFlavour pulsePressureNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Comment
   * Description: Comment on blood pressure reading
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/value|value")
  private String commentValue;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/blood pressure/Comment/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/data[at0003]/items[at0033]/null_flavour|defining_code")
  private NullFlavour commentNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Position
   * Description: The position of the person at the time of measuring blood pressure
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/value|defining_code")
  private PositionDefiningCode positionDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/state structure/Position/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at0008]/null_flavour|defining_code")
  private NullFlavour positionNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Level of
   * Exertion Description: Details about physical activity undertaken as the blood pressure is
   * recorded
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1030]")
  private List<Cluster> levelOfExertion;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Tilt
   * Description: The tilt of the surface on which the person is lying
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|magnitude")
  private Double tiltMagnitude;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/Tilt
   * Description: The tilt of the surface on which the person is lying
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/value|units")
  private String tiltUnits;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/history/Baseline
   * reading/state structure/Tilt/null_flavour
   */
  @Path("/data[at0001]/events[at0002]/state[at0007]/items[at1005]/null_flavour|defining_code")
  private NullFlavour tiltNullFlavourDefiningCode;

  /** Path: Encounter (training sample)/Blood pressure (Training sample)/Baseline reading/time */
  @Path("/data[at0001]/events[at0002]/time|value")
  private TemporalAccessor timeValue;

  /** Path: Encounter (training sample)/Blood pressure (Training sample)/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Cuff size Description: The
   * size of the cuff used for blood pressure measurement
   */
  @Path("/protocol[at0011]/items[at0013]/value|defining_code")
  private CuffSizeDefiningCode cuffSizeDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/list structure/Cuff
   * size/null_flavour
   */
  @Path("/protocol[at0011]/items[at0013]/null_flavour|defining_code")
  private NullFlavour cuffSizeNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Location of measurement
   * Description: The site of the measurement of the blood pressure
   */
  @Path("/protocol[at0011]/items[at0014]/value|defining_code")
  private LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/list structure/Location of
   * measurement/null_flavour
   */
  @Path("/protocol[at0011]/items[at0014]/null_flavour|defining_code")
  private NullFlavour locationOfMeasurementNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Korotkoff sounds
   * Description: Record which Korotkoff sound is used for determining diastolic pressure
   */
  @Path("/protocol[at0011]/items[at1010]/value|defining_code")
  private KorotkoffSoundsDefiningCode korotkoffSoundsDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/list structure/Korotkoff
   * sounds/null_flavour
   */
  @Path("/protocol[at0011]/items[at1010]/null_flavour|defining_code")
  private NullFlavour korotkoffSoundsNullFlavourDefiningCode;

  /**
   * Path: Encounter (training sample)/Blood pressure (Training sample)/Device Description: Details
   * about sphygmomanometer or other device used to measure the blood pressure
   */
  @Path("/protocol[at0011]/items[at1025]")
  private List<Cluster> device;

  /** Path: Encounter (training sample)/Blood pressure (Training sample)/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: Encounter (training sample)/Blood pressure (Training sample)/language */
  @Path("/language")
  private Language language;

  /** Path: Encounter (training sample)/Blood pressure (Training sample)/feeder_audit */
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
