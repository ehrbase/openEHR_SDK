package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class BodyTemperatureLocationOfMeasurementDvCodedText implements BodyTemperatureLocationOfMeasurementChoice {
  /**
   * Encounter/Body temperature/value/value
   */
  @Path("|defining_code")
  private LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode;

  public void setLocationOfMeasurementDefiningCode(
      LocationOfMeasurementDefiningCode locationOfMeasurementDefiningCode) {
     this.locationOfMeasurementDefiningCode = locationOfMeasurementDefiningCode;
  }

  public LocationOfMeasurementDefiningCode getLocationOfMeasurementDefiningCode() {
     return this.locationOfMeasurementDefiningCode ;
  }
}
