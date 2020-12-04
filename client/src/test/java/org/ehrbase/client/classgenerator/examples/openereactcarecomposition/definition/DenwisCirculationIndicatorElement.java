package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisCirculationIndicatorElement {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/Circulation indicator
   */
  @Path("/value|defining_code")
  private CirculationIndicatorDefiningCode value;

  public void setValue(CirculationIndicatorDefiningCode value) {
     this.value = value;
  }

  public CirculationIndicatorDefiningCode getValue() {
     return this.value ;
  }
}
