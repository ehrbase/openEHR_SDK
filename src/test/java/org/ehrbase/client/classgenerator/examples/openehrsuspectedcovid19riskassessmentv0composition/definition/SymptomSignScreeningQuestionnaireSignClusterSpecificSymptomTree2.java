package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("CLUSTER")
public class SymptomSignScreeningQuestionnaireSignClusterSpecificSymptomTree2 implements SymptomSignScreeningQuestionnaireSignChoiceSpecificSymptom {
  @Path("/items[at0005]/value|defining_code")
  private PresenceDefiningcode presenceDefiningcode;

  @Path("/items[at0006]/value|value")
  private TemporalAccessor onsetValue;

  @Path("/items[at0004]/value|value")
  private String symptomOrSignNameValue;

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

  public void setSymptomOrSignNameValue(String symptomOrSignNameValue) {
     this.symptomOrSignNameValue = symptomOrSignNameValue;
  }

  public String getSymptomOrSignNameValue() {
     return this.symptomOrSignNameValue ;
  }

  public void setCommentValue(String commentValue) {
     this.commentValue = commentValue;
  }

  public String getCommentValue() {
     return this.commentValue ;
  }
}
