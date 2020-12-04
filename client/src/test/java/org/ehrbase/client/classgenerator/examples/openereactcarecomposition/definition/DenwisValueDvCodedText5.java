package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText5 implements DenwisValueChoice5 {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode5 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode5 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode5 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
