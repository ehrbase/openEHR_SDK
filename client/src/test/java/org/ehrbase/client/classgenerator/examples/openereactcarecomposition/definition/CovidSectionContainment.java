package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class CovidSectionContainment extends Containment {
    public SelectAqlField<CovidSection> COVID_SECTION = new AqlFieldImp<CovidSection>(CovidSection.class, "", "CovidSection", CovidSection.class, this);

    public SelectAqlField<CovidSymptomsObservation> COVID_SYMPTOMS = new AqlFieldImp<CovidSymptomsObservation>(CovidSection.class, "/items[openEHR-EHR-OBSERVATION.story.v1 and name/value='Covid symptoms']", "covidSymptoms", CovidSymptomsObservation.class, this);

    public SelectAqlField<LatestCovid19TestObservation> LATEST_COVID19_TEST = new AqlFieldImp<LatestCovid19TestObservation>(CovidSection.class, "/items[openEHR-EHR-OBSERVATION.laboratory_test_result.v1 and name/value='Latest Covid-19 test']", "latestCovid19Test", LatestCovid19TestObservation.class, this);

    public SelectAqlField<CovidNotesEvaluation> COVID_NOTES = new AqlFieldImp<CovidNotesEvaluation>(CovidSection.class, "/items[openEHR-EHR-EVALUATION.clinical_synopsis.v1 and name/value='Covid notes']", "covidNotes", CovidNotesEvaluation.class, this);

    public SelectAqlField<Covid19ExposureEvaluation> COVID19_EXPOSURE = new AqlFieldImp<Covid19ExposureEvaluation>(CovidSection.class, "/items[openEHR-EHR-EVALUATION.health_risk-covid.v0 and name/value='Covid-19 exposure']", "covid19Exposure", Covid19ExposureEvaluation.class, this);

    private CovidSectionContainment() {
        super("openEHR-EHR-SECTION.adhoc.v1");
    }

    public static CovidSectionContainment getInstance() {
        return new CovidSectionContainment();
    }
}
