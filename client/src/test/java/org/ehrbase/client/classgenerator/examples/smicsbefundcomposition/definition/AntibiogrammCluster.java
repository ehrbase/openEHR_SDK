package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
public class AntibiogrammCluster {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Antibiogramm/Laboranalyt-Resultat
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
  private List<LaboranalytResultatCluster> laboranalytResultat;

  public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
     this.laboranalytResultat = laboranalytResultat;
  }

  public List<LaboranalytResultatCluster> getLaboranalytResultat() {
     return this.laboranalytResultat ;
  }
}
