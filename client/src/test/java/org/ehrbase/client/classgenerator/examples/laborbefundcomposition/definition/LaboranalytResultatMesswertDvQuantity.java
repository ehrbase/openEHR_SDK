package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import java.lang.Double;
import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.967746+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_QUANTITY")
public class LaboranalytResultatMesswertDvQuantity implements RMEntity, LaboranalytResultatMesswertChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("|magnitude")
  private Double messwertMagnitude;

  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("|units")
  private String messwertUnits;

  public void setMesswertMagnitude(Double messwertMagnitude) {
     this.messwertMagnitude = messwertMagnitude;
  }

  public Double getMesswertMagnitude() {
     return this.messwertMagnitude ;
  }

  public void setMesswertUnits(String messwertUnits) {
     this.messwertUnits = messwertUnits;
  }

  public String getMesswertUnits() {
     return this.messwertUnits ;
  }
}
