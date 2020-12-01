package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
public class StandortCluster {
  @Path("/items[at0040]/value|value")
  private String standorttypValue;

  @Path("/items[at0046]/value|value")
  private String standortbeschreibungValue;

  @Path("/items[at0048]/value|value")
  private String standortschlusselValue;

  @Path("/items[at0027]/value|value")
  private String stationValue;

  @Path("/items[at0029]/value|value")
  private String zimmerValue;

  @Path("/items[at0047]")
  private List<Cluster> details;

  public void setStandorttypValue(String standorttypValue) {
     this.standorttypValue = standorttypValue;
  }

  public String getStandorttypValue() {
     return this.standorttypValue ;
  }

  public void setStandortbeschreibungValue(String standortbeschreibungValue) {
     this.standortbeschreibungValue = standortbeschreibungValue;
  }

  public String getStandortbeschreibungValue() {
     return this.standortbeschreibungValue ;
  }

  public void setStandortschlusselValue(String standortschlusselValue) {
     this.standortschlusselValue = standortschlusselValue;
  }

  public String getStandortschlusselValue() {
     return this.standortschlusselValue ;
  }

  public void setStationValue(String stationValue) {
     this.stationValue = stationValue;
  }

  public String getStationValue() {
     return this.stationValue ;
  }

  public void setZimmerValue(String zimmerValue) {
     this.zimmerValue = zimmerValue;
  }

  public String getZimmerValue() {
     return this.zimmerValue ;
  }

  public void setDetails(List<Cluster> details) {
     this.details = details;
  }

  public List<Cluster> getDetails() {
     return this.details ;
  }
}
