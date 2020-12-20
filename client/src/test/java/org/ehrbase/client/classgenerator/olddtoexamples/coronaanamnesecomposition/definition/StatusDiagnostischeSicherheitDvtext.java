package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class StatusDiagnostischeSicherheitDvtext implements StatusDiagnostischeSicherheitChoice {
  @Path("|value")
  private String diagnostischeSicherheitValue;

  public void setDiagnostischeSicherheitValue(String diagnostischeSicherheitValue) {
    this.diagnostischeSicherheitValue = diagnostischeSicherheitValue;
  }

  public String getDiagnostischeSicherheitValue() {
    return this.diagnostischeSicherheitValue;
  }
}
