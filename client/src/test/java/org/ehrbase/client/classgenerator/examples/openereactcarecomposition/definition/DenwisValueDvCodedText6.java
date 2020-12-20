package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.400502400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText6 implements RMEntity, DenwisValueChoice6 {
  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/value/value */
  @Path("|defining_code")
  private ValueDefiningCode6 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode6 valueDefiningCode) {
    this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode6 getValueDefiningCode() {
    return this.valueDefiningCode;
  }
}
