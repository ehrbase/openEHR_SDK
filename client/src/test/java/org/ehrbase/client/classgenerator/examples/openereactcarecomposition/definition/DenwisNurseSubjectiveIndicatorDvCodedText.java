package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.571780700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisNurseSubjectiveIndicatorDvCodedText implements RMEntity, DenwisNurseSubjectiveIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Nurse subjective indicator/Nurse subjective indicator
   * Description: Nurse recorded subjective observation.
   */
  @Path("|defining_code")
  private NurseSubjectiveIndicatorDefiningCode nurseSubjectiveIndicatorDefiningCode;

  public void setNurseSubjectiveIndicatorDefiningCode(
      NurseSubjectiveIndicatorDefiningCode nurseSubjectiveIndicatorDefiningCode) {
     this.nurseSubjectiveIndicatorDefiningCode = nurseSubjectiveIndicatorDefiningCode;
  }

  public NurseSubjectiveIndicatorDefiningCode getNurseSubjectiveIndicatorDefiningCode() {
     return this.nurseSubjectiveIndicatorDefiningCode ;
  }
}
