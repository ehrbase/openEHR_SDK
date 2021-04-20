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
    date = "2021-04-19T11:36:01.986124+07:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.3.0"
)
@OptionFor("DV_TEXT")
public class LaboranalytResultatErgebnisStatusDvText implements RMEntity, LaboranalytResultatErgebnisStatusChoice {
  /**
   * Path: Laborbefund/Laborergebnis/Jedes Ereignis/Laboranalyt-Resultat/Ergebnis-Status/Ergebnis-Status
   * Description: Status des Analyseergebnisses.
   */
  @Path("|value")
  private String ergebnisStatusValue;

  public void setErgebnisStatusValue(String ergebnisStatusValue) {
     this.ergebnisStatusValue = ergebnisStatusValue;
  }

  public String getErgebnisStatusValue() {
     return this.ergebnisStatusValue ;
  }
}
