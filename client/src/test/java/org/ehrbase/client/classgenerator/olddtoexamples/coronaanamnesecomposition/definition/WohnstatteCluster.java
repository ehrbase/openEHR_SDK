package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
public class WohnstatteCluster {
  @Path("/items[at0028]/value|magnitude")
  private Long anzahlDerSchlafzimmerMagnitude;

  @Path("/items[at0003]")
  private List<Cluster> erganzendeDetails;

  public void setAnzahlDerSchlafzimmerMagnitude(Long anzahlDerSchlafzimmerMagnitude) {
    this.anzahlDerSchlafzimmerMagnitude = anzahlDerSchlafzimmerMagnitude;
  }

  public Long getAnzahlDerSchlafzimmerMagnitude() {
    return this.anzahlDerSchlafzimmerMagnitude;
  }

  public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
    this.erganzendeDetails = erganzendeDetails;
  }

  public List<Cluster> getErganzendeDetails() {
    return this.erganzendeDetails;
  }
}
