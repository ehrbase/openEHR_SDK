package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningRiskFactorsForSepsisElement {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Risk factors for sepsis
   */
  @Path("/value|defining_code")
  private RiskFactorsForSepsisDefiningCode value;

  public void setValue(RiskFactorsForSepsisDefiningCode value) {
     this.value = value;
  }

  public RiskFactorsForSepsisDefiningCode getValue() {
     return this.value ;
  }
}
