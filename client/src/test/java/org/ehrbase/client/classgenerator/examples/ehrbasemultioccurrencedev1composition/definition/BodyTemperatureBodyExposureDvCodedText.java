package org.ehrbase.client.classgenerator.examples.ehrbasemultioccurrencedev1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class BodyTemperatureBodyExposureDvCodedText implements BodyTemperatureBodyExposureChoice {
  @Path("|defining_code")
  private BodyExposureDefiningCode bodyExposureDefiningCode;

  public void setBodyExposureDefiningCode(BodyExposureDefiningCode bodyExposureDefiningCode) {
     this.bodyExposureDefiningCode = bodyExposureDefiningCode;
  }

  public BodyExposureDefiningCode getBodyExposureDefiningCode() {
     return this.bodyExposureDefiningCode ;
  }
}
