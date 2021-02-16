package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.556778500+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisPainIndicatorDvCodedText implements RMEntity, DenwisPainIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Pain indicator/Pain indicator
   * Description: Nurse recorded changes in pain.
   */
  @Path("|defining_code")
  private PainIndicatorDefiningCode painIndicatorDefiningCode;

  public void setPainIndicatorDefiningCode(PainIndicatorDefiningCode painIndicatorDefiningCode) {
     this.painIndicatorDefiningCode = painIndicatorDefiningCode;
  }

  public PainIndicatorDefiningCode getPainIndicatorDefiningCode() {
     return this.painIndicatorDefiningCode ;
  }
}
