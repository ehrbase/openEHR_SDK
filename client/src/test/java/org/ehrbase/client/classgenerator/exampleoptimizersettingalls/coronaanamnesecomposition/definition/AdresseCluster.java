package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
public class AdresseCluster {
  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Stadt
   */
  @Path("/items[at0012]/value|value")
  private String stadtValue;

  /**
   * Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse/Land
   */
  @Path("/items[at0015]/value|value")
  private String landValue;

  public void setStadtValue(String stadtValue) {
     this.stadtValue = stadtValue;
  }

  public String getStadtValue() {
     return this.stadtValue ;
  }

  public void setLandValue(String landValue) {
     this.landValue = landValue;
  }

  public String getLandValue() {
     return this.landValue ;
  }
}