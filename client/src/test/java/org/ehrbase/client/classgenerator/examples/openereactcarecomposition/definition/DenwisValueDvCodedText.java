package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText implements RMEntity, DenwisValueChoice {
  /**
   * open_eREACT-Care/Assessment/DENWIS/Point in time/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
