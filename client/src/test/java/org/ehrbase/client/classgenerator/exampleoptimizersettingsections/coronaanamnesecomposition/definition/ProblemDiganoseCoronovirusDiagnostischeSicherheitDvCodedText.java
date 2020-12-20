package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:13.491032300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_CODED_TEXT")
public class ProblemDiganoseCoronovirusDiagnostischeSicherheitDvCodedText
    implements RMEntity, ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice {
  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value/value */
  @Path("|defining_code")
  private DiagnostischeSicherheitDefiningCode diagnostischeSicherheitDefiningCode;

  public void setDiagnostischeSicherheitDefiningCode(
      DiagnostischeSicherheitDefiningCode diagnostischeSicherheitDefiningCode) {
    this.diagnostischeSicherheitDefiningCode = diagnostischeSicherheitDefiningCode;
  }

  public DiagnostischeSicherheitDefiningCode getDiagnostischeSicherheitDefiningCode() {
    return this.diagnostischeSicherheitDefiningCode;
  }
}
