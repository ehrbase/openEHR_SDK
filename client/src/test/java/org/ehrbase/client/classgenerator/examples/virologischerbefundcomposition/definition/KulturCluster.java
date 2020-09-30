package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_panel.v0")
public class KulturCluster {
    @Path("/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1 and name/value='Pro Virus']")
    private List<ProVirusCluster> proVirus;

    public void setProVirus(List<ProVirusCluster> proVirus) {
        this.proVirus = proVirus;
    }

    public List<ProVirusCluster> getProVirus() {
        return this.proVirus;
    }
}
