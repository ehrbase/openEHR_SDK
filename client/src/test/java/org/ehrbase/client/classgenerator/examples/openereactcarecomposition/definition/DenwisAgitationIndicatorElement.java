package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisAgitationIndicatorElement {
  @Path("/value")
  @Choice
  private DenwisValueChoice2 value;

  public void setValue(DenwisValueChoice2 value) {
     this.value = value;
  }

  public DenwisValueChoice2 getValue() {
     return this.value ;
  }
}
