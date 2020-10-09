package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1")
public class SauerstoffpartialdruckCluster {
  @Path("/items[at0005]/value|value")
  private String ergebnisStatusValue;

  @Path("/items[at0024]/value|defining_code")
  private UntersuchterAnalytDefiningcode2 untersuchterAnalytDefiningcode;

  @Path("/items[at0001]/value|magnitude")
  private Double analytResultatMagnitude;

  @Path("/items[at0001]/value|units")
  private String analytResultatUnits;

  @Path("/items[at0014]")
  private List<Cluster> analyseergebnisDetails;

  public void setErgebnisStatusValue(String ergebnisStatusValue) {
     this.ergebnisStatusValue = ergebnisStatusValue;
  }

  public String getErgebnisStatusValue() {
     return this.ergebnisStatusValue ;
  }

  public void setUntersuchterAnalytDefiningcode(
      UntersuchterAnalytDefiningcode2 untersuchterAnalytDefiningcode) {
     this.untersuchterAnalytDefiningcode = untersuchterAnalytDefiningcode;
  }

  public UntersuchterAnalytDefiningcode2 getUntersuchterAnalytDefiningcode() {
     return this.untersuchterAnalytDefiningcode ;
  }

  public void setAnalytResultatMagnitude(Double analytResultatMagnitude) {
     this.analytResultatMagnitude = analytResultatMagnitude;
  }

  public Double getAnalytResultatMagnitude() {
     return this.analytResultatMagnitude ;
  }

  public void setAnalytResultatUnits(String analytResultatUnits) {
     this.analytResultatUnits = analytResultatUnits;
  }

  public String getAnalytResultatUnits() {
     return this.analytResultatUnits ;
  }

  public void setAnalyseergebnisDetails(List<Cluster> analyseergebnisDetails) {
     this.analyseergebnisDetails = analyseergebnisDetails;
  }

  public List<Cluster> getAnalyseergebnisDetails() {
     return this.analyseergebnisDetails ;
  }
}
