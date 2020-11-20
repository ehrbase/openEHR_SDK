package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class Covid19ExposureEvaluationContainment extends Containment {
  public SelectAqlField<Covid19ExposureEvaluation> COVID19_EXPOSURE_EVALUATION = new AqlFieldImp<Covid19ExposureEvaluation>(Covid19ExposureEvaluation.class, "", "Covid19ExposureEvaluation", Covid19ExposureEvaluation.class, this);

    public SelectAqlField<Covid19ExposureRiskFactorChoice> RISK_FACTOR = new AqlFieldImp<Covid19ExposureRiskFactorChoice>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0013.1]/value", "riskFactor", Covid19ExposureRiskFactorChoice.class, this);

    public SelectAqlField<Covid19ExposureDetailChoice> DETAIL = new AqlFieldImp<Covid19ExposureDetailChoice>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0027.1]", "detail", Covid19ExposureDetailChoice.class, this);

    public SelectAqlField<HealthRiskDefiningcode> HEALTH_RISK_DEFININGCODE = new AqlFieldImp<HealthRiskDefiningcode>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0002.1]/value|defining_code", "healthRiskDefiningcode", HealthRiskDefiningcode.class, this);

    public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(Covid19ExposureEvaluation.class, "/protocol[at0010]/items[at0011]", "extension", Cluster.class, this);

    public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(Covid19ExposureEvaluation.class, "/language", "language", Language.class, this);

    public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(Covid19ExposureEvaluation.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<RiskAssessmentDefiningcode> RISK_ASSESSMENT_DEFININGCODE = new AqlFieldImp<RiskAssessmentDefiningcode>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0003.1]/value|defining_code", "riskAssessmentDefiningcode", RiskAssessmentDefiningcode.class, this);

    public SelectAqlField<Covid19ExposurePresenceChoice> PRESENCE = new AqlFieldImp<Covid19ExposurePresenceChoice>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0017.1]/value", "presence", Covid19ExposurePresenceChoice.class, this);

    private Covid19ExposureEvaluationContainment() {
        super("openEHR-EHR-EVALUATION.health_risk-covid.v0");
    }

    public static Covid19ExposureEvaluationContainment getInstance() {
        return new Covid19ExposureEvaluationContainment();
    }
}
