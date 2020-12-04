package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.adhoc.v1")
public class BackgroundSection {
  /**
   * open_eREACT-Care/Background/Height
   */
  @Path("/items[openEHR-EHR-OBSERVATION.height.v2 and name/value='Height']")
  private HeightObservation height;

  /**
   * open_eREACT-Care/Background/Weight
   */
  @Path("/items[openEHR-EHR-OBSERVATION.body_weight.v2 and name/value='Weight']")
  private WeightObservation weight;

  /**
   * open_eREACT-Care/Background/Frailty
   */
  @Path("/items[openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1 and name/value='Frailty']")
  private FrailtyObservation frailty;

  /**
   * open_eREACT-Care/Background/Past history
   */
  @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Past history']")
  private PastHistoryEvaluation pastHistory;

  /**
   * open_eREACT-Care/Background/Medication
   */
  @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Medication']")
  private MedicationEvaluation medication;

  /**
   * open_eREACT-Care/Background/Allergies
   */
  @Path("/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Allergies']")
  private AllergiesEvaluation allergies;

  /**
   * open_eREACT-Care/Background/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  public void setHeight(HeightObservation height) {
     this.height = height;
  }

  public HeightObservation getHeight() {
     return this.height ;
  }

  public void setWeight(WeightObservation weight) {
     this.weight = weight;
  }

  public WeightObservation getWeight() {
     return this.weight ;
  }

  public void setFrailty(FrailtyObservation frailty) {
     this.frailty = frailty;
  }

  public FrailtyObservation getFrailty() {
     return this.frailty ;
  }

  public void setPastHistory(PastHistoryEvaluation pastHistory) {
     this.pastHistory = pastHistory;
  }

  public PastHistoryEvaluation getPastHistory() {
     return this.pastHistory ;
  }

  public void setMedication(MedicationEvaluation medication) {
     this.medication = medication;
  }

  public MedicationEvaluation getMedication() {
     return this.medication ;
  }

  public void setAllergies(AllergiesEvaluation allergies) {
     this.allergies = allergies;
  }

  public AllergiesEvaluation getAllergies() {
     return this.allergies ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }
}
