package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import java.lang.Long;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_COUNT")
public class TestAllTypesChoiceDvCount implements TestAllTypesChoiceChoice {
  @Path("|magnitude")
  private Long choiceMagnitude;

  public void setChoiceMagnitude(Long choiceMagnitude) {
     this.choiceMagnitude = choiceMagnitude;
  }

  public Long getChoiceMagnitude() {
     return this.choiceMagnitude ;
  }
}
