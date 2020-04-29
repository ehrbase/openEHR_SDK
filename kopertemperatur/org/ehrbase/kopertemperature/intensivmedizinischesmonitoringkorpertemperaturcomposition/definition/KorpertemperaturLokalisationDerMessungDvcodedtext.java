package org.ehrbase.kopertemperature.intensivmedizinischesmonitoringkorpertemperaturcomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;

@Entity
@OptionFor("DV_CODED_TEXT")
public class KorpertemperaturLokalisationDerMessungDvcodedtext implements KorpertemperaturLokalisationDerMessungChoice {
  @Path("|defining_code")
  private LokalisationDerMessungDefiningcode lokalisationDerMessungDefiningcode;

  public void setLokalisationDerMessungDefiningcode(
      LokalisationDerMessungDefiningcode lokalisationDerMessungDefiningcode) {
     this.lokalisationDerMessungDefiningcode = lokalisationDerMessungDefiningcode;
  }

  public LokalisationDerMessungDefiningcode getLokalisationDerMessungDefiningcode() {
     return this.lokalisationDerMessungDefiningcode ;
  }
}
