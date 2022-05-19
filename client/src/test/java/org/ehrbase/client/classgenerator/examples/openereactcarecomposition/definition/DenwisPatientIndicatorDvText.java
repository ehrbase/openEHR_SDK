package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.567782200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class DenwisPatientIndicatorDvText implements RMEntity, DenwisPatientIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Patient indicator/Patient indicator
   * Description: Nurse recorded patient indicators.
   */
  @Path("|value")
  private String patientIndicatorValue;

  public void setPatientIndicatorValue(String patientIndicatorValue) {
     this.patientIndicatorValue = patientIndicatorValue;
  }

  public String getPatientIndicatorValue() {
     return this.patientIndicatorValue ;
  }
}
