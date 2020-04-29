package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Definingcode;

@Entity
public class ServiceRequestReasonForRequestElement {
  @Path("/value|defining_code")
  private Definingcode definingcode;

  public void setDefiningcode(Definingcode definingcode) {
     this.definingcode = definingcode;
  }

  public Definingcode getDefiningcode() {
     return this.definingcode ;
  }
}
