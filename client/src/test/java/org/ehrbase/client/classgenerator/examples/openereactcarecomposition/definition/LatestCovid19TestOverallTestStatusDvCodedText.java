package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class LatestCovid19TestOverallTestStatusDvCodedText implements LatestCovid19TestOverallTestStatusChoice {
  @Path("|defining_code")
  private OverallTestStatusDefiningCode overallTestStatusDefiningCode;

  public void setOverallTestStatusDefiningCode(
      OverallTestStatusDefiningCode overallTestStatusDefiningCode) {
     this.overallTestStatusDefiningCode = overallTestStatusDefiningCode;
  }

  public OverallTestStatusDefiningCode getOverallTestStatusDefiningCode() {
     return this.overallTestStatusDefiningCode ;
  }
}
