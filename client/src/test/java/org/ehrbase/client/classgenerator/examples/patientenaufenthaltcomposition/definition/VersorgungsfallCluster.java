package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
public class VersorgungsfallCluster {
  @Path("/items[at0001 and name/value='Zugehörige Versorgungsfall-Kennung']/value|value")
  private String zugehorigeVersorgungsfallKennungValue;

  public void setZugehorigeVersorgungsfallKennungValue(
      String zugehorigeVersorgungsfallKennungValue) {
     this.zugehorigeVersorgungsfallKennungValue = zugehorigeVersorgungsfallKennungValue;
  }

  public String getZugehorigeVersorgungsfallKennungValue() {
     return this.zugehorigeVersorgungsfallKennungValue ;
  }
}
