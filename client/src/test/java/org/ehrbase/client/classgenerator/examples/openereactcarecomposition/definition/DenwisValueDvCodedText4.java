package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText4 implements DenwisValueChoice4 {
  @Path("|defining_code")
  private ValueDefiningCode4 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode4 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode4 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
