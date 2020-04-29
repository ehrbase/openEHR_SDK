package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_QUANTITY")
public class LuftsauerstoffDvquantity implements LuftsauerstoffOrgEhrbaseEhrEncodeWrappersSnakecase255990ccChoice {
  @Path("|magnitude")
  private Double magnitude;

  @Path("|units")
  private String units;

  public void setMagnitude(Double magnitude) {
     this.magnitude = magnitude;
  }

  public Double getMagnitude() {
     return this.magnitude ;
  }

  public void setUnits(String units) {
     this.units = units;
  }

  public String getUnits() {
     return this.units ;
  }
}
