package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

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

@Entity
@Archetype("openEHR-EHR-OBSERVATION.blood_pressure.v2")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.565501400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BloodPressureObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/Systolic Description: Peak
   * systemic arterial blood pressure - measured in systolic or contraction phase of the heart
   * cycle.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|magnitude")
  private Double systolicMagnitude;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/Systolic Description: Peak
   * systemic arterial blood pressure - measured in systolic or contraction phase of the heart
   * cycle.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0004]/value|units")
  private String systolicUnits;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/Diastolic Description: Minimum
   * systemic arterial blood pressure - measured in the diastolic or relaxation phase of the heart
   * cycle.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|magnitude")
  private Double diastolicMagnitude;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/Diastolic Description: Minimum
   * systemic arterial blood pressure - measured in the diastolic or relaxation phase of the heart
   * cycle.
   */
  @Path("/data[at0001]/events[at0006]/data[at0003]/items[at0005]/value|units")
  private String diastolicUnits;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/Exertion Description: Details
   * about physical activity undertaken at the time of blood pressure measurement.
   */
  @Path("/data[at0001]/events[at0006]/state[at0007]/items[at1030]")
  private Cluster exertion;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Any event/time */
  @Path("/data[at0001]/events[at0006]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/origin */
  @Path("/data[at0001]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Structured measurement location
   * Description: Structured anatomical location of where the measurement was taken.
   */
  @Path("/protocol[at0011]/items[at1057]")
  private List<Cluster> structuredMeasurementLocation;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Device Description: Details about
   * sphygmomanometer or other device used to measure the blood pressure.
   */
  @Path("/protocol[at0011]/items[at1025]")
  private Cluster device;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/Extension Description: Additional
   * information required to capture local context or to align with other reference
   * models/formalisms. Comment: For example: Local hospital departmental infomation or additional
   * metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/protocol[at0011]/items[at1058]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Blood pressure/feeder_audit */
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

  public void setExertion(Cluster exertion) {
    this.exertion = exertion;
  }

  public Cluster getExertion() {
    return this.exertion;
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

  public void setStructuredMeasurementLocation(List<Cluster> structuredMeasurementLocation) {
    this.structuredMeasurementLocation = structuredMeasurementLocation;
  }

  public List<Cluster> getStructuredMeasurementLocation() {
    return this.structuredMeasurementLocation;
  }

  public void setDevice(Cluster device) {
    this.device = device;
  }

  public Cluster getDevice() {
    return this.device;
  }

  public void setExtension(List<Cluster> extension) {
    this.extension = extension;
  }

  public List<Cluster> getExtension() {
    return this.extension;
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
