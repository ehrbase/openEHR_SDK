package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_TEXT")
public class ProblemDiganoseCoronovirusDiagnostischeSicherheitDvText implements RMEntity, ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice {
  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value/value
   */
  @Path("|value")
  private String diagnostischeSicherheitValue;

  public void setDiagnostischeSicherheitValue(String diagnostischeSicherheitValue) {
     this.diagnostischeSicherheitValue = diagnostischeSicherheitValue;
  }

  public String getDiagnostischeSicherheitValue() {
     return this.diagnostischeSicherheitValue ;
  }
}
