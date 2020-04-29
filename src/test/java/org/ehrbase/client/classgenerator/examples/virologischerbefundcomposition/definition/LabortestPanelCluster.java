package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import java.util.List;

import com.nedap.archie.rm.datavalues.DvText;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
public class LabortestPanelCluster {
  @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]")
  private List<LaboranalytResultatCluster> laboranalytResultat;

  public void setLaboranalytResultat(List<LaboranalytResultatCluster> laboranalytResultat) {
     this.laboranalytResultat = laboranalytResultat;
  }

    @Path("/name")
    private DvText name;

  public List<LaboranalytResultatCluster> getLaboranalytResultat() {
     return this.laboranalytResultat ;
  }

    public DvText getName() {
        return name;
    }

    public void setName(DvText name) {
        this.name = name;
    }
}
