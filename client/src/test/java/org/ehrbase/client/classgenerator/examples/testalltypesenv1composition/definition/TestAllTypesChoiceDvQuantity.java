package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

import javax.annotation.processing.Generated;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-08T15:38:06.346881500+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvQuantity implements RMEntity, TestAllTypesChoiceChoice {
  /**
   * Path: Test all types/Test all types/choice/choice
   * Description: *
   */
  @Path("|magnitude")
  private Double choiceMagnitude;

  /**
   * Path: Test all types/Test all types/choice/choice
   * Description: *
   */
  @Path("|units")
  private String choiceUnits;

  public void setChoiceMagnitude(Double choiceMagnitude) {
     this.choiceMagnitude = choiceMagnitude;
  }

  public Double getChoiceMagnitude() {
     return this.choiceMagnitude ;
  }

  public void setChoiceUnits(String choiceUnits) {
     this.choiceUnits = choiceUnits;
  }

  public String getChoiceUnits() {
     return this.choiceUnits ;
  }
}
