package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class ErregertypisierungArtDerTypisierungElement {
  @Path("/value|defining_code")
  private ArtDerTypisierungDefiningCode value;

  public void setValue(ArtDerTypisierungDefiningCode value) {
     this.value = value;
  }

  public ArtDerTypisierungDefiningCode getValue() {
     return this.value ;
  }
}
