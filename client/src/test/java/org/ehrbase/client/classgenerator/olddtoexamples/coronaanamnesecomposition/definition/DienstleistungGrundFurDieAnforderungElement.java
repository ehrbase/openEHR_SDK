package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class DienstleistungGrundFurDieAnforderungElement {
  @Path("/value|value")
  private String value;

  public void setValue(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
