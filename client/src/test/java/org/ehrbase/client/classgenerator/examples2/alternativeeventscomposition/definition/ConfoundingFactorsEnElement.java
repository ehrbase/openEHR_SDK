package org.ehrbase.client.classgenerator.examples2.alternativeeventscomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ConfoundingFactorsEnElement {
  @Path("/value|value")
  private String confoundingFactorsEnValue;

  public void setConfoundingFactorsEnValue(String confoundingFactorsEnValue) {
     this.confoundingFactorsEnValue = confoundingFactorsEnValue;
  }

  public String getConfoundingFactorsEnValue() {
     return this.confoundingFactorsEnValue ;
  }
}
