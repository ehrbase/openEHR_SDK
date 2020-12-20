package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.411499600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class SepsisScreeningValueDvCodedText implements RMEntity, SepsisScreeningValueChoice {
  /** Path: open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/value/value */
  @Path("|defining_code")
  private ValueDefiningCode7 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode7 valueDefiningCode) {
    this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode7 getValueDefiningCode() {
    return this.valueDefiningCode;
  }
}
