package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class KorpertemperaturKorperexpositionDvcodedtextStatus implements KorpertemperaturKorperexpositionChoiceStatus {
  @Path("|defining_code")
  private KorperexpositionDefiningcode korperexpositionDefiningcode;

  public void setKorperexpositionDefiningcode(
      KorperexpositionDefiningcode korperexpositionDefiningcode) {
     this.korperexpositionDefiningcode = korperexpositionDefiningcode;
  }

  public KorperexpositionDefiningcode getKorperexpositionDefiningcode() {
     return this.korperexpositionDefiningcode ;
  }
}
