package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningRedFlagAcuteElement {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Red flag (acute)
   */
  @Path("/value|defining_code")
  private RedFlagAcuteDefiningCode value;

  public void setValue(RedFlagAcuteDefiningCode value) {
     this.value = value;
  }

  public RedFlagAcuteDefiningCode getValue() {
     return this.value ;
  }
}
