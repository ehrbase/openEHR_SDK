package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KontaktSectionContainment extends Containment {
  public SelectAqlField<KontaktSection> KONTAKT_SECTION =
      new AqlFieldImp<KontaktSection>(
          KontaktSection.class, "", "KontaktSection", KontaktSection.class, this);

  public SelectAqlField<AufenthaltInGesundheitseinrichtungObservation>
      AUFENTHALT_IN_GESUNDHEITSEINRICHTUNG =
          new AqlFieldImp<AufenthaltInGesundheitseinrichtungObservation>(
              KontaktSection.class,
              "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Aufenthalt in Gesundheitseinrichtung']",
              "aufenthaltInGesundheitseinrichtung",
              AufenthaltInGesundheitseinrichtungObservation.class,
              this);

  public SelectAqlField<PersonenkontaktObservation> PERSONENKONTAKT =
      new AqlFieldImp<PersonenkontaktObservation>(
          KontaktSection.class,
          "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0 and name/value='Personenkontakt']",
          "personenkontakt",
          PersonenkontaktObservation.class,
          this);

  private KontaktSectionContainment() {
    super("openEHR-EHR-SECTION.adhoc.v1");
  }

  public static KontaktSectionContainment getInstance() {
    return new KontaktSectionContainment();
  }
}
