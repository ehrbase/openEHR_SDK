package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("POINT_EVENT")
public class TravelEventAnyIntervalEventPointEvent implements TravelEventAnyIntervalEventChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]/name|value")
  private String travelValue;

  @Path("/data[at0003]/items[at0004]/value|defining_code")
  private TravelDefiningcode travelDefiningcode;

  @Path("/data[at0003]/items[at0008]")
  private List<TravelEventTripDetailCluster> tripDetail;

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setTravelValue(String travelValue) {
     this.travelValue = travelValue;
  }

  public String getTravelValue() {
     return this.travelValue ;
  }

  public void setTravelDefiningcode(TravelDefiningcode travelDefiningcode) {
     this.travelDefiningcode = travelDefiningcode;
  }

  public TravelDefiningcode getTravelDefiningcode() {
     return this.travelDefiningcode ;
  }

  public void setTripDetail(List<TravelEventTripDetailCluster> tripDetail) {
     this.tripDetail = tripDetail;
  }

  public List<TravelEventTripDetailCluster> getTripDetail() {
     return this.tripDetail ;
  }
}
