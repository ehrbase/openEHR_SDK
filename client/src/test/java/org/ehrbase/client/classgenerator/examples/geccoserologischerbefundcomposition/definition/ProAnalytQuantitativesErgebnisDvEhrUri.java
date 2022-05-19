package org.ehrbase.client.classgenerator.examples.geccoserologischerbefundcomposition.definition;

import com.nedap.archie.rm.datavalues.DvEHRURI;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-05-19T16:20:30.126760900+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("DV_EHR_URI")
public class ProAnalytQuantitativesErgebnisDvEhrUri
    implements RMEntity, ProAnalytQuantitativesErgebnisChoice {
  /**
   * Path: GECCO_Serologischer Befund/Befund/Jedes Ereignis/Labortest-Panel/Pro Analyt/Quantitatives
   * Ergebnis/Quantitatives Ergebnis Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("")
  private DvEHRURI quantitativesErgebnis;

  public void setQuantitativesErgebnis(DvEHRURI quantitativesErgebnis) {
    this.quantitativesErgebnis = quantitativesErgebnis;
  }

  public DvEHRURI getQuantitativesErgebnis() {
    return this.quantitativesErgebnis;
  }
}
