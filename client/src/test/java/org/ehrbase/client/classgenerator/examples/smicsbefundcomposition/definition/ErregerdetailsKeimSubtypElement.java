package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ErregerdetailsKeimSubtypElement {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregerdetails/Keim Subtyp
   */
  @Path("/value|value")
  private String value;

  public void setValue(String value) {
     this.value = value;
  }

  public String getValue() {
     return this.value ;
  }
}
