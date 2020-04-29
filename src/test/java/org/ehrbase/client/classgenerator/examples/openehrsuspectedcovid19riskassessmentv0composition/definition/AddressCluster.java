package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.address_cc.v0")
public class AddressCluster {
  @Path("/items[at0012]/value|value")
  private String cityValue;

  @Path("/items[at0015]/value|value")
  private String countryValue;

  public void setCityValue(String cityValue) {
     this.cityValue = cityValue;
  }

  public String getCityValue() {
     return this.cityValue ;
  }

  public void setCountryValue(String countryValue) {
     this.countryValue = countryValue;
  }

  public String getCountryValue() {
     return this.countryValue ;
  }
}
