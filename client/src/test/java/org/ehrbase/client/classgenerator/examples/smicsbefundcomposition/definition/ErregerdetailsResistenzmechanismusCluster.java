package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ErregerdetailsResistenzmechanismusCluster {
  @Path("/items[at0017]/value|value")
  private String resistenzmechanismusBezeichnungValue;

  public void setResistenzmechanismusBezeichnungValue(String resistenzmechanismusBezeichnungValue) {
     this.resistenzmechanismusBezeichnungValue = resistenzmechanismusBezeichnungValue;
  }

  public String getResistenzmechanismusBezeichnungValue() {
     return this.resistenzmechanismusBezeichnungValue ;
  }
}
