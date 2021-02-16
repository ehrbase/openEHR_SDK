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
    date = "2021-02-16T12:59:53.551781600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class DenwisAgitationIndicatorDvText implements RMEntity, DenwisAgitationIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Agitation indicator/Agitation indicator
   * Description: Nurse recorded changes in agitation.
   */
  @Path("|value")
  private String agitationIndicatorValue;

  public void setAgitationIndicatorValue(String agitationIndicatorValue) {
     this.agitationIndicatorValue = agitationIndicatorValue;
  }

  public String getAgitationIndicatorValue() {
     return this.agitationIndicatorValue ;
  }
}
