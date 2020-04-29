package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.ScreeningPurposeDefiningcode;

@Entity
@OptionFor("POINT_EVENT")
public class SymptomSignScreeningQuestionnaireAnyEventPointEvent implements SymptomSignScreeningQuestionnaireAnyEventChoice {
  @Path("/time|value")
  private TemporalAccessor timeValue;

  @Path("/data[at0003]/items[at0034]/value|defining_code")
  private ScreeningPurposeDefiningcode screeningPurposeDefiningcode;

  @Path("/data[at0003]/items[at0026]")
  private List<Cluster> additionalDetails;

  @Path("/data[at0003]/items[at0022]")
  @Choice
  private List<SymptomSignScreeningQuestionnaireSignChoice> sign;

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setScreeningPurposeDefiningcode(
      ScreeningPurposeDefiningcode screeningPurposeDefiningcode) {
     this.screeningPurposeDefiningcode = screeningPurposeDefiningcode;
  }

  public ScreeningPurposeDefiningcode getScreeningPurposeDefiningcode() {
     return this.screeningPurposeDefiningcode ;
  }

  public void setAdditionalDetails(List<Cluster> additionalDetails) {
     this.additionalDetails = additionalDetails;
  }

  public List<Cluster> getAdditionalDetails() {
     return this.additionalDetails ;
  }

  public void setSign(List<SymptomSignScreeningQuestionnaireSignChoice> sign) {
     this.sign = sign;
  }

  public List<SymptomSignScreeningQuestionnaireSignChoice> getSign() {
     return this.sign ;
  }
}
