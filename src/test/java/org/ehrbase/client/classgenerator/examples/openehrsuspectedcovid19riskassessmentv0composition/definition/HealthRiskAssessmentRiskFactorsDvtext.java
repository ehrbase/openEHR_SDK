package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class HealthRiskAssessmentRiskFactorsDvtext implements HealthRiskAssessmentRiskFactorsChoice {
  @Path("|value")
  private String riskFactorsValue;

  public void setRiskFactorsValue(String riskFactorsValue) {
     this.riskFactorsValue = riskFactorsValue;
  }

  public String getRiskFactorsValue() {
     return this.riskFactorsValue ;
  }
}
