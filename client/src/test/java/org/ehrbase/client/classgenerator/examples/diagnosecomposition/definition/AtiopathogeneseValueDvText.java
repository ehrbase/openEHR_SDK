package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_TEXT")
public class AtiopathogeneseValueDvText implements RMEntity, AtiopathogeneseValueChoice {
  /**
   * COVID-19-Diagnose/Problem/Diagnose/Ätiopathogenese/value/value
   */
  @Path("|value")
  private String valueValue;

  public void setValueValue(String valueValue) {
     this.valueValue = valueValue;
  }

  public String getValueValue() {
     return this.valueValue ;
  }
}
