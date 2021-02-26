package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.566780200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisPatientIndicatorDvCodedText implements RMEntity, DenwisPatientIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Patient indicator/Patient indicator
   * Description: Nurse recorded patient indicators.
   */
  @Path("|defining_code")
  private PatientIndicatorDefiningCode patientIndicatorDefiningCode;

  public void setPatientIndicatorDefiningCode(
      PatientIndicatorDefiningCode patientIndicatorDefiningCode) {
     this.patientIndicatorDefiningCode = patientIndicatorDefiningCode;
  }

  public PatientIndicatorDefiningCode getPatientIndicatorDefiningCode() {
     return this.patientIndicatorDefiningCode ;
  }
}
