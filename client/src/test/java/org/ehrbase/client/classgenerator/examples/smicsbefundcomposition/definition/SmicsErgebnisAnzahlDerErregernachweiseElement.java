package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import java.lang.Long;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SmicsErgebnisAnzahlDerErregernachweiseElement {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Anzahl der Erregernachweise
   */
  @Path("/value|magnitude")
  private Long value;

  public void setValue(Long value) {
     this.value = value;
  }

  public Long getValue() {
     return this.value ;
  }
}
