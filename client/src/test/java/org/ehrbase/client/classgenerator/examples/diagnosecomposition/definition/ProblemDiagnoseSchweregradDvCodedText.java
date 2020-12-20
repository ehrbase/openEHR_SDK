package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.359025800+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class ProblemDiagnoseSchweregradDvCodedText
    implements RMEntity, ProblemDiagnoseSchweregradChoice {
  /** Path: COVID-19-Diagnose/Problem/Diagnose/value/value */
  @Path("|defining_code")
  private SchweregradDefiningCode schweregradDefiningCode;

  public void setSchweregradDefiningCode(SchweregradDefiningCode schweregradDefiningCode) {
    this.schweregradDefiningCode = schweregradDefiningCode;
  }

  public SchweregradDefiningCode getSchweregradDefiningCode() {
    return this.schweregradDefiningCode;
  }
}
