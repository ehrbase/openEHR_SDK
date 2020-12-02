package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DenwisNurseSubjectiveIndicatorElement {
  @Path("/value")
  @Choice
  private DenwisValueChoice6 value;

  public void setValue(DenwisValueChoice6 value) {
     this.value = value;
  }

  public DenwisValueChoice6 getValue() {
     return this.value ;
  }
}
