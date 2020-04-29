package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class TravelEventTravelDestinationCluster2 {
  @Path("/items[at0024]")
  private List<Cluster> additionalDestinationDetails;

  @Path("/items[at0012]/value|value")
  private String regionValue;

  @Path("/items[at0011]/value|value")
  private String countryValue;

  @Path("/items[at0013]/value|value")
  private String cityValue;

  public void setAdditionalDestinationDetails(List<Cluster> additionalDestinationDetails) {
     this.additionalDestinationDetails = additionalDestinationDetails;
  }

  public List<Cluster> getAdditionalDestinationDetails() {
     return this.additionalDestinationDetails ;
  }

  public void setRegionValue(String regionValue) {
     this.regionValue = regionValue;
  }

  public String getRegionValue() {
     return this.regionValue ;
  }

  public void setCountryValue(String countryValue) {
     this.countryValue = countryValue;
  }

  public String getCountryValue() {
     return this.countryValue ;
  }

  public void setCityValue(String cityValue) {
     this.cityValue = cityValue;
  }

  public String getCityValue() {
     return this.cityValue ;
  }
}
