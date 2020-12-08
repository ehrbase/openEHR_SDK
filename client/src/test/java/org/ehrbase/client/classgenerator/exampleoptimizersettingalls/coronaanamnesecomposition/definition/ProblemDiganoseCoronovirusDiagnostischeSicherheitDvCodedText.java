package org.ehrbase.client.classgenerator.exampleoptimizersettingalls.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@OptionFor("DV_CODED_TEXT")
public class ProblemDiganoseCoronovirusDiagnostischeSicherheitDvCodedText implements RMEntity, ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice {
  /**
   * Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value/value
   */
  @Path("|defining_code")
  private DiagnostischeSicherheitDefiningCode diagnostischeSicherheitDefiningCode;

  public void setDiagnostischeSicherheitDefiningCode(
      DiagnostischeSicherheitDefiningCode diagnostischeSicherheitDefiningCode) {
     this.diagnostischeSicherheitDefiningCode = diagnostischeSicherheitDefiningCode;
  }

  public DiagnostischeSicherheitDefiningCode getDiagnostischeSicherheitDefiningCode() {
     return this.diagnostischeSicherheitDefiningCode ;
  }
}
