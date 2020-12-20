package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.EntryEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.538500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class TemperatureObservation implements EntryEntity {
  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/Temperature Description: The
   * measured body temperature (as a surrogate for the whole body).
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperatureMagnitude;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/Temperature Description: The
   * measured body temperature (as a surrogate for the whole body).
   */
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperatureUnits;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/Environmental Conditions
   * Description: Details about the environmental conditions at the time of temperature measurement.
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private Cluster environmentalConditions;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/Exertion Description: Details
   * about the exertion of the person at the time of temperature measurement.
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster exertion;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/Menstrual Cycle Description:
   * Details about the menstrual cycle of a woman.
   */
  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0058]")
  private Element menstrualCycle;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Any event/time */
  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Temperature/origin */
  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  /**
   * Path: open_eREACT-Care/Assessment/NEWS2/Temperature/Device Description: Details about the
   * device use to measure body temperature.
   */
  @Path("/protocol[at0020]/items[at0059]")
  private Cluster device;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Temperature/subject */
  @Path("/subject")
  private PartyProxy subject;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Temperature/language */
  @Path("/language")
  private Language language;

  /** Path: open_eREACT-Care/Assessment/NEWS2/Temperature/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setTemperatureMagnitude(Double temperatureMagnitude) {
    this.temperatureMagnitude = temperatureMagnitude;
  }

  public Double getTemperatureMagnitude() {
    return this.temperatureMagnitude;
  }

  public void setTemperatureUnits(String temperatureUnits) {
    this.temperatureUnits = temperatureUnits;
  }

  public String getTemperatureUnits() {
    return this.temperatureUnits;
  }

  public void setEnvironmentalConditions(Cluster environmentalConditions) {
    this.environmentalConditions = environmentalConditions;
  }

  public Cluster getEnvironmentalConditions() {
    return this.environmentalConditions;
  }

  public void setExertion(Cluster exertion) {
    this.exertion = exertion;
  }

  public Cluster getExertion() {
    return this.exertion;
  }

  public void setMenstrualCycle(Element menstrualCycle) {
    this.menstrualCycle = menstrualCycle;
  }

  public Element getMenstrualCycle() {
    return this.menstrualCycle;
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
