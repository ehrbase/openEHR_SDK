package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.kontakt.v1")
public class KontaktSection {
  @Path("/items[openEHR-EHR-OBSERVATION.exposure_assessment.v0]")
  @Choice
  private KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice exposureScreeningQuestionnaireEn;

  public void setExposureScreeningQuestionnaireEn(
      KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice exposureScreeningQuestionnaireEn) {
     this.exposureScreeningQuestionnaireEn = exposureScreeningQuestionnaireEn;
  }

  public KontaktOrgEhrbaseEhrEncodeWrappersSnakecase732c9b5cExposureScreeningQuestionnaireEnChoice getExposureScreeningQuestionnaireEn(
      ) {
     return this.exposureScreeningQuestionnaireEn ;
  }
}
