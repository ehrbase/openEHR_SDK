package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class KontaktSection {
  @Path(
      "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']")
  private AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung;

  @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']")
  private PersonenkontaktObservation personenkontakt;

  public void setAufenthaltInGesundheitseinrichtung(
      AufenthaltInGesundheitseinrichtungObservation aufenthaltInGesundheitseinrichtung) {
    this.aufenthaltInGesundheitseinrichtung = aufenthaltInGesundheitseinrichtung;
  }

  public AufenthaltInGesundheitseinrichtungObservation getAufenthaltInGesundheitseinrichtung() {
    return this.aufenthaltInGesundheitseinrichtung;
  }

  public void setPersonenkontakt(PersonenkontaktObservation personenkontakt) {
    this.personenkontakt = personenkontakt;
  }

  public PersonenkontaktObservation getPersonenkontakt() {
    return this.personenkontakt;
  }
}
