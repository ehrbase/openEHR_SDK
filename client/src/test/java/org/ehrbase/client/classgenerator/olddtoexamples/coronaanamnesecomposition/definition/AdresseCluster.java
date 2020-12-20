package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
public class AdresseCluster {
  @Path("/items[at0012]/value|value")
  private String stadtValue;

  @Path("/items[at0015]/value|value")
  private String landValue;

  public void setStadtValue(String stadtValue) {
    this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
    return this.stadtValue;
  }

  public void setLandValue(String landValue) {
    this.landValue = landValue;
  }

  public String getLandValue() {
    return this.landValue;
  }
}
