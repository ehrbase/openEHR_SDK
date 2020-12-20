package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:12.552026100+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class KontaktSection implements LocatableEntity {
  /**
   * Path: Bericht/Kontakt/Personenkontakt Description: *An individual- or self-reported
   * questionnaire screening for exposure to a chemical, physical or biological agent which has
   * caused or may cause harm to an individual.(en)
   */
  @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
  private PersonenkontaktObservation personenkontakt;

  /**
   * Path: Bericht/Kontakt/Aufenthalt in Gesundheitseinrichtung Description: *An individual- or
   * self-reported questionnaire screening for exposure to a chemical, physical or biological agent
   * which has caused or may cause harm to an individual.(en)
   */
  @Path(
      "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
  private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

  /** Path: Bericht/Kontakt/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
    this.personenkontakt = personenkontakt;
  }

  public PersonenkontaktObservation getPersonenkontakt() {
    return this.personenkontakt;
  }

  public void setAufenthaltInGesundheitseinrichtung(
      AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
    this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
  }

  public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
    return this.aufenthaltInGesundheitseinrichtung;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }
}
