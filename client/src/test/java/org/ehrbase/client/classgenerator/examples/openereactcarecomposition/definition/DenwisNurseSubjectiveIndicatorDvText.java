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
    date = "2021-02-16T12:59:53.573779300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class DenwisNurseSubjectiveIndicatorDvText implements RMEntity, DenwisNurseSubjectiveIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Nurse subjective indicator/Nurse subjective indicator
   * Description: Nurse recorded subjective observation.
   */
  @Path("|value")
  private String nurseSubjectiveIndicatorValue;

  public void setNurseSubjectiveIndicatorValue(String nurseSubjectiveIndicatorValue) {
     this.nurseSubjectiveIndicatorValue = nurseSubjectiveIndicatorValue;
  }

  public String getNurseSubjectiveIndicatorValue() {
     return this.nurseSubjectiveIndicatorValue ;
  }
}
