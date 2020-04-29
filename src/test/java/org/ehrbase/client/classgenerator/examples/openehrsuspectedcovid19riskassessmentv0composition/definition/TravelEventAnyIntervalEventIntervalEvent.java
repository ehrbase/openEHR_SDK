package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.MathFunctionDefiningcode;

@Entity
@OptionFor("INTERVAL_EVENT")
public class TravelEventAnyIntervalEventIntervalEvent implements TravelEventAnyIntervalEventChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0004]/name|value")
  private String travelValue;

  @Path("/data[at0003]/items[at0004]/value|defining_code")
  private TravelDefiningcode travelDefiningcode;

  @Path("/math_function|defining_code")
  private MathFunctionDefiningcode mathFunctionDefiningcode;

  @Path("/data[at0003]/items[at0008]")
  private List<TravelEventTripDetailClusterTree> tripDetail;

  @Path("/width|value")
  private TemporalAmount widthValue;

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

  public void setMathFunctionDefiningcode(MathFunctionDefiningcode mathFunctionDefiningcode) {
     this.mathFunctionDefiningcode = mathFunctionDefiningcode;
  }

  public MathFunctionDefiningcode getMathFunctionDefiningcode() {
     return this.mathFunctionDefiningcode ;
  }

  public void setTripDetail(List<TravelEventTripDetailClusterTree> tripDetail) {
     this.tripDetail = tripDetail;
  }

  public List<TravelEventTripDetailClusterTree> getTripDetail() {
     return this.tripDetail ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }
}
