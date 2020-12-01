package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisBreathingIndicatorElement {
  @Path("/value|defining_code")
  private BreathingIndicatorDefiningCode value;

  public void setValue(BreathingIndicatorDefiningCode value) {
     this.value = value;
  }

  public BreathingIndicatorDefiningCode getValue() {
     return this.value ;
  }
}
