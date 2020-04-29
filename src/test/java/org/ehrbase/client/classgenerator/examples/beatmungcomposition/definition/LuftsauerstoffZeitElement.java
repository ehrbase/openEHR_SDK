package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.time.temporal.TemporalAmount;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class LuftsauerstoffZeitElement {
  @Path("/value|value")
  private TemporalAmount value;

  public void setValue(TemporalAmount value) {
     this.value = value;
  }

  public TemporalAmount getValue() {
     return this.value ;
  }
}
