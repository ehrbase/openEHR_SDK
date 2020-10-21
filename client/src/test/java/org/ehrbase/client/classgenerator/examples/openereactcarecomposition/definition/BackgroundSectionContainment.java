package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class BackgroundSectionContainment extends Containment {
  public SelectAqlField<BackgroundSection> BACKGROUND_SECTION = new AqlFieldImp<BackgroundSection>(BackgroundSection.class, "", "BackgroundSection", BackgroundSection.class, this);

    public SelectAqlField<MedicationEvaluation> MEDICATION = new AqlFieldImp<MedicationEvaluation>(BackgroundSection.class, "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Medication']", "medication", MedicationEvaluation.class, this);

    public SelectAqlField<WeightObservation> WEIGHT = new AqlFieldImp<WeightObservation>(BackgroundSection.class, "/items[openEHR-EHR-OBSERVATION.body_weight.v2 and name/value='Weight']", "weight", WeightObservation.class, this);

    public SelectAqlField<FrailtyObservation> FRAILTY = new AqlFieldImp<FrailtyObservation>(BackgroundSection.class, "/items[openEHR-EHR-OBSERVATION.clinical_frailty_scale.v1 and name/value='Frailty']", "frailty", FrailtyObservation.class, this);

    public SelectAqlField<HeightObservation> HEIGHT = new AqlFieldImp<HeightObservation>(BackgroundSection.class, "/items[openEHR-EHR-OBSERVATION.height.v2 and name/value='Height']", "height", HeightObservation.class, this);

    public SelectAqlField<AllergiesEvaluation> ALLERGIES = new AqlFieldImp<AllergiesEvaluation>(BackgroundSection.class, "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Allergies']", "allergies", AllergiesEvaluation.class, this);

    public SelectAqlField<PastHistoryEvaluation> PAST_HISTORY = new AqlFieldImp<PastHistoryEvaluation>(BackgroundSection.class, "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Past history']", "pastHistory", PastHistoryEvaluation.class, this);

    private BackgroundSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static BackgroundSectionContainment getInstance() {
        return new BackgroundSectionContainment();
    }
}
