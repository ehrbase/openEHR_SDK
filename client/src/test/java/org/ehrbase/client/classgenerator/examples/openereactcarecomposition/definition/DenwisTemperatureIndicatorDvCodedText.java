package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.527780+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisTemperatureIndicatorDvCodedText implements RMEntity, DenwisTemperatureIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Temperature indicator/Temperature indicator
   * Description: Nurse recorded changes in temperature.
   */
  @Path("|defining_code")
  private TemperatureIndicatorDefiningCode temperatureIndicatorDefiningCode;

  public void setTemperatureIndicatorDefiningCode(
      TemperatureIndicatorDefiningCode temperatureIndicatorDefiningCode) {
     this.temperatureIndicatorDefiningCode = temperatureIndicatorDefiningCode;
  }

  public TemperatureIndicatorDefiningCode getTemperatureIndicatorDefiningCode() {
     return this.temperatureIndicatorDefiningCode ;
  }
}
