package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Long;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
public class WohnstatteCluster {
  /**
   * Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Anzahl der Schlafzimmer
   */
  @Path("/items[at0028]/value|magnitude")
  private Long anzahlDerSchlafzimmerMagnitude;

  /**
   * Bericht/Allgemeine Angaben/Wohnsituation/Wohnstätte/Ergänzende Details
   */
  @Path("/items[at0003]")
  private List<Cluster> erganzendeDetails;

  public void setAnzahlDerSchlafzimmerMagnitude(Long anzahlDerSchlafzimmerMagnitude) {
     this.anzahlDerSchlafzimmerMagnitude = anzahlDerSchlafzimmerMagnitude;
  }

  public Long getAnzahlDerSchlafzimmerMagnitude() {
     return this.anzahlDerSchlafzimmerMagnitude ;
  }

  public void setErganzendeDetails(List<Cluster> erganzendeDetails) {
     this.erganzendeDetails = erganzendeDetails;
  }

  public List<Cluster> getErganzendeDetails() {
     return this.erganzendeDetails ;
  }
}
