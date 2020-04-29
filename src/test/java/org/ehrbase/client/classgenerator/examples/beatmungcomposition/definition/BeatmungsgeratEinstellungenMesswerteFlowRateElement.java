package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class BeatmungsgeratEinstellungenMesswerteFlowRateElement {
  @Path("/value|magnitude")
  private Double magnitude;

  @Path("/value|units")
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
