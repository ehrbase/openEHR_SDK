package org.ehrbase.diagnose.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class AtiopathogeneseAtiologieDerKrankheitElement {
  @Path("/value")
  @Choice
  private AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase1cf2fed4Choice value;

  public void setValue(AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase1cf2fed4Choice value) {
     this.value = value;
  }

  public AtiopathogeneseOrgEhrbaseEhrEncodeWrappersSnakecase1cf2fed4Choice getValue() {
     return this.value ;
  }
}
