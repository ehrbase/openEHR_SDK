package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement {
  @Path("/value")
  @Choice
  private AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase70e29e14Choice value;

  public void setValue(AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase70e29e14Choice value) {
     this.value = value;
  }

  public AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase70e29e14Choice getValue() {
     return this.value ;
  }
}
