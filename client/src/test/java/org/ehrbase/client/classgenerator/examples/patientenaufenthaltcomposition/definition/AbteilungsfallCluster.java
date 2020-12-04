package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.case_identification.v0")
public class AbteilungsfallCluster {
  /**
   * Patientenaufenthalt/context/Abteilungsfall/Zugehörige Abteilungsfall-Kennung
   */
  @Path("/items[at0001 and name/value='Zugehörige Abteilungsfall-Kennung']/value|value")
  private String zugehorigeAbteilungsfallKennungValue;

  public void setZugehorigeAbteilungsfallKennungValue(String zugehorigeAbteilungsfallKennungValue) {
     this.zugehorigeAbteilungsfallKennungValue = zugehorigeAbteilungsfallKennungValue;
  }

  public String getZugehorigeAbteilungsfallKennungValue() {
     return this.zugehorigeAbteilungsfallKennungValue ;
  }
}
