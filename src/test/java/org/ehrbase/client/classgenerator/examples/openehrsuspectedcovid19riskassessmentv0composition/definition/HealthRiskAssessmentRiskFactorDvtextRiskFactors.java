package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class HealthRiskAssessmentRiskFactorDvtextRiskFactors implements HealthRiskAssessmentRiskFactorChoice {
  @Path("|value")
  private String riskFactorValue;

  public void setRiskFactorValue(String riskFactorValue) {
     this.riskFactorValue = riskFactorValue;
  }

  public String getRiskFactorValue() {
     return this.riskFactorValue ;
  }
}
