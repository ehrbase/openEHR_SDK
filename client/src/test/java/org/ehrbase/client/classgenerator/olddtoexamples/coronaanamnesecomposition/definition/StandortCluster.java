package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster {
  @Path("/items[at0047]")
  private List<Cluster> details;

  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  public void setDetails(List<Cluster> details) {
    this.details = details;
  }

  public List<Cluster> getDetails() {
    return this.details;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
    this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
    return this.standortbeschreibungValue;
  }
}
