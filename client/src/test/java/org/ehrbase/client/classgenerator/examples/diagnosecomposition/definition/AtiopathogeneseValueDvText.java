package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class AtiopathogeneseValueDvText implements AtiopathogeneseValueChoice {
  @Path("|value")
  private String valueValue;

  public void setValueValue(String valueValue) {
     this.valueValue = valueValue;
  }

  public String getValueValue() {
     return this.valueValue ;
  }
}