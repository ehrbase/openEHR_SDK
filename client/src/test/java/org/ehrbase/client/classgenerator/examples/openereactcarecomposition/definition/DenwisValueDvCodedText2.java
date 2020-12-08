package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText2 implements RMEntity, DenwisValueChoice2 {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode2 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode2 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode2 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
