package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisPainIndicatorElement {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value
   */
  @Path("/value")
  @Choice
  private DenwisValueChoice3 value;

  public void setValue(DenwisValueChoice3 value) {
     this.value = value;
  }

  public DenwisValueChoice3 getValue() {
     return this.value ;
  }
}
