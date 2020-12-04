package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
public class KulturCluster {
  /**
   * Virologischer Befund/Befund/Jedes Ereignis/Kultur/Pro Virus
   */
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Virus']")
  private List<ProVirusCluster> proVirus;

  public void setProVirus(List<ProVirusCluster> proVirus) {
     this.proVirus = proVirus;
  }

  public List<ProVirusCluster> getProVirus() {
     return this.proVirus ;
  }
}
