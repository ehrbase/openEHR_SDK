package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.627060400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_TEXT")
public class ProblemDiganoseCoronovirusDiagnostischeSicherheitDvText
    implements RMEntity, ProblemDiganoseCoronovirusDiagnostischeSicherheitChoice {
  /** Path: Bericht/Allgemeine Angaben/Problem/Diganose Coronovirus/value/value */
  @Path("|value")
  private String diagnostischeSicherheitValue;

  public void setDiagnostischeSicherheitValue(String diagnostischeSicherheitValue) {
    this.diagnostischeSicherheitValue = diagnostischeSicherheitValue;
  }

  public String getDiagnostischeSicherheitValue() {
    return this.diagnostischeSicherheitValue;
  }
}
