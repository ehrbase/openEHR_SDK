package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-09T11:37:50.978761100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null"
)
@OptionFor("DV_QUANTITY")
public class TestAllTypesChoiceDvQuantity implements RMEntity, TestAllTypesChoiceChoice {
  /**
   * Test all types/Test all types/value/value
   */
  @Path("|magnitude")
  private Double choiceMagnitude;

  /**
   * Test all types/Test all types/value/value
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
