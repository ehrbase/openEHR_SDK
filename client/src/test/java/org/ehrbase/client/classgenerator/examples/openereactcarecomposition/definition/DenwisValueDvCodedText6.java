package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText6 implements DenwisValueChoice6 {
  @Path("|defining_code")
  private ValueDefiningCode6 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode6 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode6 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
