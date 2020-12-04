package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import java.lang.String;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster {
  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Name des Medikaments
   */
  @Path("/items[at0021]/value|value")
  private String nameDesMedikamentsValue;

  /**
   * Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Medikament in Verwendung?
   */
  @Path("/items[at0024]/value|defining_code")
  private MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode;

  public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
     this.nameDesMedikamentsValue = nameDesMedikamentsValue;
  }

  public String getNameDesMedikamentsValue() {
     return this.nameDesMedikamentsValue ;
  }

  public void setMedikamentInVerwendungDefiningCode(
      MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode) {
     this.medikamentInVerwendungDefiningCode = medikamentInVerwendungDefiningCode;
  }

  public MedikamentInVerwendungDefiningCode getMedikamentInVerwendungDefiningCode() {
     return this.medikamentInVerwendungDefiningCode ;
  }
}