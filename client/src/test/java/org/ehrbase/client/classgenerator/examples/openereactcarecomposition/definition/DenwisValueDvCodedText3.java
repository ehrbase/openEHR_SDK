package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText3 implements RMEntity, DenwisValueChoice3 {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode3 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode3 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode3 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
