package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.808495600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvQuantity implements RMEntity, TestAllTypesChoiceChoice {
  /** Path: Test all types/Test all types/value/value */
  @Path("|magnitude")
  private Double choiceMagnitude;

  /** Path: Test all types/Test all types/value/value */
  @Path("|units")
  private String choiceUnits;

  public void setChoiceMagnitude(Double choiceMagnitude) {
    this.choiceMagnitude = choiceMagnitude;
  }

  public Double getChoiceMagnitude() {
    return this.choiceMagnitude;
  }

  public void setChoiceUnits(String choiceUnits) {
    this.choiceUnits = choiceUnits;
  }

  public String getChoiceUnits() {
    return this.choiceUnits;
  }
}
