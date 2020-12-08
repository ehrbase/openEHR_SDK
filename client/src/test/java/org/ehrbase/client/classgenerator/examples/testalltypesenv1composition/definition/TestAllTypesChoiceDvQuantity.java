package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import java.lang.Double;
import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
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
