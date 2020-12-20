package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.016497700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TEXT")
public class BodyTemperatureBodyExposureDvText
    implements RMEntity, BodyTemperatureBodyExposureChoice {
  /** Path: Encounter/Body temperature/Any event/value/value */
  @Path("|value")
  private String bodyExposureValue;

  public void setBodyExposureValue(String bodyExposureValue) {
    this.bodyExposureValue = bodyExposureValue;
  }

  public String getBodyExposureValue() {
    return this.bodyExposureValue;
  }
}
