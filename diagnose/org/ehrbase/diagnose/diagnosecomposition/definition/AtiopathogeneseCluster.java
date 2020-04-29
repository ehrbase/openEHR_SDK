package org.ehrbase.diagnose.diagnosecomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.etiology.v1")
public class AtiopathogeneseCluster {
  @Path("/items[at0001]")
  private List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit;

  @Path("/items[at0017]")
  private List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens;

  public void setAtiologieDerKrankheit(
      List<AtiopathogeneseAtiologieDerKrankheitElement> atiologieDerKrankheit) {
     this.atiologieDerKrankheit = atiologieDerKrankheit;
  }

  public List<AtiopathogeneseAtiologieDerKrankheitElement> getAtiologieDerKrankheit() {
     return this.atiologieDerKrankheit ;
  }

  public void setBeschreibungDesEntstehens(
      List<AtiopathogeneseBeschreibungDesEntstehensElement> beschreibungDesEntstehens) {
     this.beschreibungDesEntstehens = beschreibungDesEntstehens;
  }

  public List<AtiopathogeneseBeschreibungDesEntstehensElement> getBeschreibungDesEntstehens() {
     return this.beschreibungDesEntstehens ;
  }
}
