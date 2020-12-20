package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.012496400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TEXT")
public class BodyTemperatureLocationOfMeasurementDvText
    implements RMEntity, BodyTemperatureLocationOfMeasurementChoice {
  /** Path: Encounter/Body temperature/value/value */
  @Path("|value")
  private String locationOfMeasurementValue;

  public void setLocationOfMeasurementValue(String locationOfMeasurementValue) {
    this.locationOfMeasurementValue = locationOfMeasurementValue;
  }

  public String getLocationOfMeasurementValue() {
    return this.locationOfMeasurementValue;
  }
}
