package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ErregertypisierungArtDerTypisierungElement {
  /**
   * SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Eigenschaften des beteiligten Erregers/Erregertypisierung/Art der Typisierung
   */
  @Path("/value|defining_code")
  private ArtDerTypisierungDefiningCode value;

  public void setValue(ArtDerTypisierungDefiningCode value) {
     this.value = value;
  }

  public ArtDerTypisierungDefiningCode getValue() {
     return this.value ;
  }
}
