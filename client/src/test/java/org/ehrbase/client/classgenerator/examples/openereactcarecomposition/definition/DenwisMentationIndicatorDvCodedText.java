package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-02-16T12:59:53.546779700+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_CODED_TEXT")
public class DenwisMentationIndicatorDvCodedText implements RMEntity, DenwisMentationIndicatorChoice {
  /**
   * Path: open_eREACT-Care/Assessment/DENWIS/Point in time/Mentation indicator/Mentation indicator
   * Description: Nurse recorded changes in mentation.
   */
  @Path("|defining_code")
  private MentationIndicatorDefiningCode mentationIndicatorDefiningCode;

  public void setMentationIndicatorDefiningCode(
      MentationIndicatorDefiningCode mentationIndicatorDefiningCode) {
     this.mentationIndicatorDefiningCode = mentationIndicatorDefiningCode;
  }

  public MentationIndicatorDefiningCode getMentationIndicatorDefiningCode() {
     return this.mentationIndicatorDefiningCode ;
  }
}
