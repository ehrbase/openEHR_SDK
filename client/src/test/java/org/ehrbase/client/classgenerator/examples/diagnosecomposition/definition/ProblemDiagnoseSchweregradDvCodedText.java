package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class ProblemDiagnoseSchweregradDvCodedText implements ProblemDiagnoseSchweregradChoice {
  @Path("|defining_code")
  private SchweregradDefiningCode schweregradDefiningCode;

  public void setSchweregradDefiningCode(SchweregradDefiningCode schweregradDefiningCode) {
     this.schweregradDefiningCode = schweregradDefiningCode;
  }

  public SchweregradDefiningCode getSchweregradDefiningCode() {
     return this.schweregradDefiningCode ;
  }
}
