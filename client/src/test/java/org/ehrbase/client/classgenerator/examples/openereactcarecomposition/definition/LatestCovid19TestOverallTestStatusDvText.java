package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_TEXT")
public class LatestCovid19TestOverallTestStatusDvText implements RMEntity, LatestCovid19TestOverallTestStatusChoice {
  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/value/value
   */
  @Path("|value")
  private String overallTestStatusValue;

  public void setOverallTestStatusValue(String overallTestStatusValue) {
     this.overallTestStatusValue = overallTestStatusValue;
  }

  public String getOverallTestStatusValue() {
     return this.overallTestStatusValue ;
  }
}
