package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_CODED_TEXT")
public class SepsisScreeningValueDvCodedText implements RMEntity, SepsisScreeningValueChoice {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/value/value
   */
  @Path("|defining_code")
  private ValueDefiningCode7 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode7 valueDefiningCode) {
     this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode7 getValueDefiningCode() {
     return this.valueDefiningCode ;
  }
}
