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
@Archetype("openEHR-EHR-OBSERVATION.pulse.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.551502600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class PulseObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Pulse Rate Description: The rate,
   * measured in beats per minute.
   */
  @Path(
      "/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|magnitude")
  private Double pulseRateMagnitude;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Pulse Rate Description: The rate,
   * measured in beats per minute.
   */
  @Path(
      "/data[at0002]/events[at0003]/data[at0001]/items[at0004 and name/value='Pulse Rate']/value|units")
  private String pulseRateUnits;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/Exertion Description: Details about
   * physical exertion being undertaken during the examination.
   */
  @Path("/data[at0002]/events[at0003]/state[at0012]/items[at1017]")
  private List<Cluster> exertion;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Any event/time */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Pulse/origin */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Device Description: Details about the device used
   * to measure the pulse rate or heart rate.
   */
  @Path("/protocol[at0010]/items[at1013]")
  private Cluster device;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Pulse/Extension Description: Additional information
   * required to capture local content or to align with other reference models/formalisms. Comment:
   * For example: local information requirements or additional metadata to align with FHIR or CIMI
   * equivalents.
   */
  @Path("/protocol[at0010]/items[at1056]")
  private List<Cluster> extension;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Pulse/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Pulse/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Pulse/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPulseRateMagnitude(Double pulseRateMagnitude) {
    this.pulseRateMagnitude = pulseRateMagnitude;
  }

  public Double getPulseRateMagnitude() {
    return this.pulseRateMagnitude;
  }

  public void setPulseRateUnits(String pulseRateUnits) {
    this.pulseRateUnits = pulseRateUnits;
  }

  public String getPulseRateUnits() {
    return this.pulseRateUnits;
  }

  public void setExertion(List<Cluster> exertion) {
    this.exertion = exertion;
  }

  public List<Cluster> getExertion() {
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
