package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText2 implements DenwisValueChoice2 {
  @Path("|defining_code")
  private ValueDefiningCode2 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode2 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode2 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
