package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisTrajectoryIndicatorElement {
  @Path("/value")
  @Choice
  private DenwisValueChoice4 value;

  public void setValue(DenwisValueChoice4 value) {
     this.value = value;
  }

  public DenwisValueChoice4 getValue() {
     return this.value ;
  }
}
