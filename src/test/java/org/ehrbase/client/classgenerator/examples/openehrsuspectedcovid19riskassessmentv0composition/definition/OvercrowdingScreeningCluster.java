package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.overcrowding_screening.v0")
public class OvercrowdingScreeningCluster {
  @Path("/items[at0002]/name|value")
  private String personsPerBedroomValue;

  @Path("/items[at0002]/value|magnitude")
  private Long personsPerBedroomMagnitude;

  public void setPersonsPerBedroomValue(String personsPerBedroomValue) {
     this.personsPerBedroomValue = personsPerBedroomValue;
  }

  public String getPersonsPerBedroomValue() {
     return this.personsPerBedroomValue ;
  }

  public void setPersonsPerBedroomMagnitude(Long personsPerBedroomMagnitude) {
     this.personsPerBedroomMagnitude = personsPerBedroomMagnitude;
  }

  public Long getPersonsPerBedroomMagnitude() {
     return this.personsPerBedroomMagnitude ;
  }
}
