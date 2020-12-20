package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.398501200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class DenwisValueDvCodedText5 implements RMEntity, DenwisValueChoice5 {
  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/value/value */
  @Path("|defining_code")
  private ValueDefiningCode5 valueDefiningCode;

  public void setValueDefiningCode(ValueDefiningCode5 valueDefiningCode) {
    this.valueDefiningCode = valueDefiningCode;
  }

  public ValueDefiningCode5 getValueDefiningCode() {
    return this.valueDefiningCode;
  }
}
