package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.516503600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class LatestCovid19TestOverallTestStatusDvCodedText
    implements RMEntity, LatestCovid19TestOverallTestStatusChoice {
  /** Path: open_eREACT-Care/Assessment/Covid/Latest Covid-19 test/Any event/value/value */
  @Path("|defining_code")
  private OverallTestStatusDefiningCode overallTestStatusDefiningCode;

  public void setOverallTestStatusDefiningCode(
      OverallTestStatusDefiningCode overallTestStatusDefiningCode) {
    this.overallTestStatusDefiningCode = overallTestStatusDefiningCode;
  }

  public OverallTestStatusDefiningCode getOverallTestStatusDefiningCode() {
    return this.overallTestStatusDefiningCode;
  }
}
