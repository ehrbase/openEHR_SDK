package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.603026200+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class FragebogenZumMedikationsScreeningSpezifischeMedikamenteCluster
    implements LocatableEntity {
  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Name des Medikaments
   * Description: Name des Medikaments oder der Medikamentenunterklasse.
   */
  @Path("/items[at0021]/value|value")
  private String nameDesMedikamentsValue;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/Medikament in Verwendung?
   * Description: Wendet die Person das Medikament oder Unterklasse des Medikaments bei oder während
   * des genannten Ereignisses an?
   */
  @Path("/items[at0024]/value|defining_code")
  private MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode;

  /**
   * Path: Bericht/Allgemeine Angaben/Fragebogen zum Medikations-Screening/Beliebiges
   * Ereignis/Spezifische Medikamentenklasse/Spezifische Medikamente/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setNameDesMedikamentsValue(String nameDesMedikamentsValue) {
    this.nameDesMedikamentsValue = nameDesMedikamentsValue;
  }

  public String getNameDesMedikamentsValue() {
    return this.nameDesMedikamentsValue;
  }

  public void setMedikamentInVerwendungDefiningCode(
      MedikamentInVerwendungDefiningCode medikamentInVerwendungDefiningCode) {
    this.medikamentInVerwendungDefiningCode = medikamentInVerwendungDefiningCode;
  }

  public MedikamentInVerwendungDefiningCode getMedikamentInVerwendungDefiningCode() {
    return this.medikamentInVerwendungDefiningCode;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
