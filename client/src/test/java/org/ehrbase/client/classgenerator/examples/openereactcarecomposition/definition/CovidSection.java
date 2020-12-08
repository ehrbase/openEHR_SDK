package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class CovidSection implements LocatableEntity {
  /**
   * open_eREACT-Care/Assessment/Covid/Covid symptoms
   */
  @Path("/items[openEHR-EHR-OBSERVATION.story.v1 and name/value='Covid symptoms']")
  private CovidSymptomsObservation covidSymptoms;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid-19 exposure
   */
  @Path("/items[openEHR-EHR-EVALUATION.health_risk-covid.v0 and name/value='Covid-19 exposure']")
  private Covid19ExposureEvaluation covid19Exposure;

  /**
   * open_eREACT-Care/Assessment/Covid/Latest Covid-19 test
   */
  @Path("/items[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Latest Covid-19 test']")
  private LatestCovid19TestObservation latestCovid19Test;

  /**
   * open_eREACT-Care/Assessment/Covid/Covid notes
   */
  @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Covid notes']")
  private CovidNotesEvaluation covidNotes;

  /**
   * open_eREACT-Care/Assessment/Covid/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setCovidSymptoms(CovidSymptomsObservation covidSymptoms) {
     this.covidSymptoms = covidSymptoms;
  }

  public CovidSymptomsObservation getCovidSymptoms() {
     return this.covidSymptoms ;
  }

  public void setCovid19Exposure(Covid19ExposureEvaluation covid19Exposure) {
     this.covid19Exposure = covid19Exposure;
  }

  public Covid19ExposureEvaluation getCovid19Exposure() {
     return this.covid19Exposure ;
  }

  public void setLatestCovid19Test(LatestCovid19TestObservation latestCovid19Test) {
     this.latestCovid19Test = latestCovid19Test;
  }

  public LatestCovid19TestObservation getLatestCovid19Test() {
     return this.latestCovid19Test ;
  }

  public void setCovidNotes(CovidNotesEvaluation covidNotes) {
     this.covidNotes = covidNotes;
  }

  public CovidNotesEvaluation getCovidNotes() {
     return this.covidNotes ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
