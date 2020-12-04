package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisPatientIndicatorElement {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value
   */
  @Path("/value")
  @Choice
  private DenwisValueChoice5 value;

  public void setValue(DenwisValueChoice5 value) {
     this.value = value;
  }

  public DenwisValueChoice5 getValue() {
     return this.value ;
  }
}
