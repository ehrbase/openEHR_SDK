package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datastructures.Element;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

import java.time.temporal.TemporalAccessor;

@Entity
@Archetype("openEHR-EHR-OBSERVATION.body_temperature.v1")
public class TemperatureObservation {
  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|magnitude")
  private Double temperatureMagnitude;

  @Path("/data[at0002]/events[at0003]/data[at0001]/items[at0004]/value|units")
  private String temperatureUnits;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0056]")
  private Cluster environmentalConditions;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0057]")
  private Cluster exertion;

  @Path("/data[at0002]/events[at0003]/state[at0029]/items[at0058]")
  private Element menstrualCycle;

  @Path("/data[at0002]/events[at0003]/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0002]/origin|value")
  private TemporalAccessor originValue;

  @Path("/protocol[at0020]/items[at0059]")
  private Cluster device;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  public void setTemperatureMagnitude(Double temperatureMagnitude) {
     this.temperatureMagnitude = temperatureMagnitude;
  }

  public Double getTemperatureMagnitude() {
     return this.temperatureMagnitude ;
  }

  public void setTemperatureUnits(String temperatureUnits) {
     this.temperatureUnits = temperatureUnits;
  }

  public String getTemperatureUnits() {
     return this.temperatureUnits ;
  }

  public void setEnvironmentalConditions(Cluster environmentalConditions) {
     this.environmentalConditions = environmentalConditions;
  }

  public Cluster getEnvironmentalConditions() {
     return this.environmentalConditions ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
  }

  public void setMenstrualCycle(Element menstrualCycle) {
     this.menstrualCycle = menstrualCycle;
  }

  public Element getMenstrualCycle() {
     return this.menstrualCycle ;
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

  public void setDevice(Cluster device) {
     this.device = device;
  }

  public Cluster getDevice() {
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
