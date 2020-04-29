package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SymptomOrSignNameDefiningcode;

@Entity
@OptionFor("CLUSTER")
public class SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree implements SymptomSignScreeningQuestionnaireSignChoiceSpecificSymptom {
  @Path("/items[at0005]/value|defining_code")
  private PresenceDefiningcode presenceDefiningcode;

  @Path("/items[at0006]/value|value")
  private TemporalAccessor onsetValue;

  @Path("/items[at0004]/value|defining_code")
  private SymptomOrSignNameDefiningcode symptomOrSignNameDefiningcode;

  @Path("/items[at0035]/value|value")
  private String commentValue;

  public void setPresenceDefiningcode(PresenceDefiningcode presenceDefiningcode) {
     this.presenceDefiningcode = presenceDefiningcode;
  }

  public PresenceDefiningcode getPresenceDefiningcode() {
     return this.presenceDefiningcode ;
  }

  public void setOnsetValue(TemporalAccessor onsetValue) {
     this.onsetValue = onsetValue;
  }

  public TemporalAccessor getOnsetValue() {
     return this.onsetValue ;
  }

  public void setSymptomOrSignNameDefiningcode(
      SymptomOrSignNameDefiningcode symptomOrSignNameDefiningcode) {
     this.symptomOrSignNameDefiningcode = symptomOrSignNameDefiningcode;
  }

  public SymptomOrSignNameDefiningcode getSymptomOrSignNameDefiningcode() {
     return this.symptomOrSignNameDefiningcode ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }
}
