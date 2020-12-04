package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Double;
import java.lang.Long;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class BodyTemperatureAnyEventPointEvent implements BodyTemperatureAnyEventChoice {
  /**
   * Encounter/Body temperature/Any event/Temperature
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double temperatureMagnitude;

  /**
   * Encounter/Body temperature/Any event/Temperature
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String temperatureUnits;

  /**
   * Encounter/Body temperature/Any event/Description of thermal stress
   */
  @Path("/state[at0029]/items[at0041]/value|value")
  private String descriptionOfThermalStressValue;

  /**
   * Encounter/Body temperature/Any event/Current day of menstrual cycle
   */
  @Path("/state[at0029]/items[at0065]/value|magnitude")
  private Long currentDayOfMenstrualCycleMagnitude;

  /**
   * Encounter/Body temperature/Any event/Environmental conditions
   */
  @Path("/state[at0029]/items[at0056]")
  private List<Cluster> environmentalConditions;

  /**
   * Encounter/Body temperature/Any event/Exertion
   */
  @Path("/state[at0029]/items[at0057]")
  private Cluster exertion;

  /**
   * Encounter/Body temperature/Any event/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Encounter/Body temperature/Any event/value
   */
  @Path("/state[at0029]/items[at0030]/value")
  @Choice
  private BodyTemperatureBodyExposureChoice bodyExposure;

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

  public void setDescriptionOfThermalStressValue(String descriptionOfThermalStressValue) {
     this.descriptionOfThermalStressValue = descriptionOfThermalStressValue;
  }

  public String getDescriptionOfThermalStressValue() {
     return this.descriptionOfThermalStressValue ;
  }

  public void setCurrentDayOfMenstrualCycleMagnitude(Long currentDayOfMenstrualCycleMagnitude) {
     this.currentDayOfMenstrualCycleMagnitude = currentDayOfMenstrualCycleMagnitude;
  }

  public Long getCurrentDayOfMenstrualCycleMagnitude() {
     return this.currentDayOfMenstrualCycleMagnitude ;
  }

  public void setEnvironmentalConditions(List<Cluster> environmentalConditions) {
     this.environmentalConditions = environmentalConditions;
  }

  public List<Cluster> getEnvironmentalConditions() {
     return this.environmentalConditions ;
  }

  public void setExertion(Cluster exertion) {
     this.exertion = exertion;
  }

  public Cluster getExertion() {
     return this.exertion ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setBodyExposure(BodyTemperatureBodyExposureChoice bodyExposure) {
     this.bodyExposure = bodyExposure;
  }

  public BodyTemperatureBodyExposureChoice getBodyExposure() {
     return this.bodyExposure ;
  }
}
