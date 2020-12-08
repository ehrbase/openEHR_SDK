package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import java.lang.Long;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_COUNT")
public class TestAllTypesChoiceDvCount implements RMEntity, TestAllTypesChoiceChoice {
  /**
   * Test all types/Test all types/value/value
   */
  @Path("|magnitude")
  private Long choiceMagnitude;

  public void setChoiceMagnitude(Long choiceMagnitude) {
     this.choiceMagnitude = choiceMagnitude;
  }

  public Long getChoiceMagnitude() {
     return this.choiceMagnitude ;
  }
}
