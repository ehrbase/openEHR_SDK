package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.129759800+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_COUNT")
public class ProAnalytQuantitativesErgebnisDvCount
    implements RMEntity, ProAnalytQuantitativesErgebnisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives
   * Ergebnis/Quantitatives Ergebnis Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("|magnitude")
  private Long quantitativesErgebnisMagnitude;

  public void setQuantitativesErgebnisMagnitude(Long quantitativesErgebnisMagnitude) {
    this.quantitativesErgebnisMagnitude = quantitativesErgebnisMagnitude;
  }

  public Long getQuantitativesErgebnisMagnitude() {
    return this.quantitativesErgebnisMagnitude;
  }
}
