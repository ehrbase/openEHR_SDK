package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.551781600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisAgitationIndicatorDvCodedText implements RMEntity, DenwisAgitationIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Agitation indicator/Agitation indicator
   * Description: Nurse recorded changes in agitation.
   */
  @Path("|defining_code")
  private AgitationIndicatorDefiningCode agitationIndicatorDefiningCode;

  public void setAgitationIndicatorDefiningCode(
      AgitationIndicatorDefiningCode agitationIndicatorDefiningCode) {
     this.agitationIndicatorDefiningCode = agitationIndicatorDefiningCode;
  }

  public AgitationIndicatorDefiningCode getAgitationIndicatorDefiningCode() {
     return this.agitationIndicatorDefiningCode ;
  }
}
