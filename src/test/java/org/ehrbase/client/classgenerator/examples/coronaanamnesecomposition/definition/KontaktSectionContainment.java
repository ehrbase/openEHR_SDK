package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KontaktSectionContainment extends Containment {
  public SelectAqlField<KontaktSection> KONTAKT_SECTION = new AqlFieldImp<KontaktSection>(KontaktSection.class, "", "KontaktSection", KontaktSection.class, this);

  public SelectAqlField<KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice> EXPOSURE_SCREENING_QUESTIONNAIRE_EN = new AqlFieldImp<KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice>(KontaktSection.class, "/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]", "exposureScreeningQuestionnaireEn", KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice.class, this);

  private KontaktSectionContainment() {
    super("openEHR-EHR-SECTION.kontakt.v1");
  }

  public static KontaktSectionContainment getInstance() {
    return new KontaktSectionContainment();
  }
}
