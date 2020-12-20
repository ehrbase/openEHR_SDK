package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_TEXT")
public class BewertungDesGesundheitsrisikosBewertungDesGesundheitsrisikosRisikofaktorDvtextS_
    implements BewertungDesGesundheitsrisikosRisikofaktorChoice {
  @Path("|value")
  private String risikofaktorValue;

  public void setRisikofaktorValue(String risikofaktorValue) {
    this.risikofaktorValue = risikofaktorValue;
  }

  public String getRisikofaktorValue() {
    return this.risikofaktorValue;
  }
}
