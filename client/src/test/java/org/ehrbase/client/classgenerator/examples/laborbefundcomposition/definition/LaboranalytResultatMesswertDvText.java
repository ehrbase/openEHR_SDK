package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import java.lang.String;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.RMEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-04-19T11:36:01.966761+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class LaboranalytResultatMesswertDvText implements RMEntity, LaboranalytResultatMesswertChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Messwert/Messwert
   * Description: (Mess-)Wert des Analyt-Resultats.
   */
  @Path("|value")
  private String messwertValue;

  public void setMesswertValue(String messwertValue) {
     this.messwertValue = messwertValue;
  }

  public String getMesswertValue() {
     return this.messwertValue ;
  }
}
