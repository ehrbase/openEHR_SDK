package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreening999FlagElement {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/999 Flag
   */
  @Path("/value|defining_code")
  private N999FlagDefiningCode value;

  public void setValue(N999FlagDefiningCode value) {
     this.value = value;
  }

  public N999FlagDefiningCode getValue() {
     return this.value ;
  }
}
