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
    date = "2021-02-16T12:59:53.545779900+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_TEXT")
public class DenwisMentationIndicatorDvText implements RMEntity, DenwisMentationIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Mentation indicator/Mentation indicator
   * Description: Nurse recorded changes in mentation.
   */
  @Path("|value")
  private String mentationIndicatorValue;

  public void setMentationIndicatorValue(String mentationIndicatorValue) {
     this.mentationIndicatorValue = mentationIndicatorValue;
  }

  public String getMentationIndicatorValue() {
     return this.mentationIndicatorValue ;
  }
}
