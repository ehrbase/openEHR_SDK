package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.Long;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.dwelling.v0")
public class DwellingCluster {
  @Path("/items[at0028]/value|magnitude")
  private Long numberOfBedroomsMagnitude;

  @Path("/items[openEHR-EHR-CLUSTER.overcrowding_screening.v0]")
  private List<OvercrowdingScreeningCluster> overcrowdingScreening;

  public void setNumberOfBedroomsMagnitude(Long numberOfBedroomsMagnitude) {
     this.numberOfBedroomsMagnitude = numberOfBedroomsMagnitude;
  }

  public Long getNumberOfBedroomsMagnitude() {
     return this.numberOfBedroomsMagnitude ;
  }

  public void setOvercrowdingScreening(List<OvercrowdingScreeningCluster> overcrowdingScreening) {
     this.overcrowdingScreening = overcrowdingScreening;
  }

  public List<OvercrowdingScreeningCluster> getOvercrowdingScreening() {
     return this.overcrowdingScreening ;
  }
}
