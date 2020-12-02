package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisTemperatureIndicatorElement {
  @Path("/value")
  @Choice
  private DenwisValueChoice value;

  public void setValue(DenwisValueChoice value) {
     this.value = value;
  }

  public DenwisValueChoice getValue() {
     return this.value ;
  }
}
