package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.Long;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_COUNT")
public class LuftsauerstoffDvcount implements LuftsauerstoffOrgEhrbaseEhrEncodeWrappersSnakecase255990ccChoice {
  @Path("|magnitude")
  private Long magnitude;

  public void setMagnitude(Long magnitude) {
     this.magnitude = magnitude;
  }

  public Long getMagnitude() {
     return this.magnitude ;
  }
}
