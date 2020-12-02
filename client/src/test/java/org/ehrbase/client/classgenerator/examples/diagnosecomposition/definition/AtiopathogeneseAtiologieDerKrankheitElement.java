package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement {
  @Path("/value")
  @Choice
  private AtiopathogeneseValueChoice value;

  public void setValue(AtiopathogeneseValueChoice value) {
     this.value = value;
  }

  public AtiopathogeneseValueChoice getValue() {
     return this.value ;
  }
}
