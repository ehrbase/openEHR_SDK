package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class ProblemDiagnoseSchweregradDvText implements ProblemDiagnoseSchweregradChoice {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/value/value
   */
  @Path("|value")
  private String schweregradValue;

  public void setSchweregradValue(String schweregradValue) {
     this.schweregradValue = schweregradValue;
  }

  public String getSchweregradValue() {
     return this.schweregradValue ;
  }
}
