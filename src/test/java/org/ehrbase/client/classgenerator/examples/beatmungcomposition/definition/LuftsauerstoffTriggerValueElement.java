package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class LuftsauerstoffTriggerValueElement {
  @Path("/value")
  @Choice
  private LuftsauerstoffOrgEhrbaseEhrEncodeWrappersSnakecase255990ccChoice value;

  public void setValue(LuftsauerstoffOrgEhrbaseEhrEncodeWrappersSnakecase255990ccChoice value) {
     this.value = value;
  }

  public LuftsauerstoffOrgEhrbaseEhrEncodeWrappersSnakecase255990ccChoice getValue() {
     return this.value ;
  }
}
