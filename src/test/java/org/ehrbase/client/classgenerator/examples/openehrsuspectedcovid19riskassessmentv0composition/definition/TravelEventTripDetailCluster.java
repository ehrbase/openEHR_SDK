package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class TravelEventTripDetailCluster {
  @Path("/items[at0010]")
  private List<TravelEventTravelDestinationCluster> travelDestination;

  @Path("/items[at0025]")
  private List<Cluster> additionalTripDetails;

  @Path("/items[at0019]/value|value")
  private TemporalAccessor dateOfReturnValue;

  public void setTravelDestination(List<TravelEventTravelDestinationCluster> travelDestination) {
     this.travelDestination = travelDestination;
  }

  public List<TravelEventTravelDestinationCluster> getTravelDestination() {
     return this.travelDestination ;
  }

  public void setAdditionalTripDetails(List<Cluster> additionalTripDetails) {
     this.additionalTripDetails = additionalTripDetails;
  }

  public List<Cluster> getAdditionalTripDetails() {
     return this.additionalTripDetails ;
  }

  public void setDateOfReturnValue(TemporalAccessor dateOfReturnValue) {
     this.dateOfReturnValue = dateOfReturnValue;
  }

  public TemporalAccessor getDateOfReturnValue() {
     return this.dateOfReturnValue ;
  }
}
