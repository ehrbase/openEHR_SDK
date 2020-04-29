package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.HealthRiskDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class HealthRiskAssessmentEvaluationContainment extends Containment {
  public SelectAqlField<HealthRiskAssessmentEvaluation> HEALTH_RISK_ASSESSMENT_EVALUATION = new AqlFieldImp<HealthRiskAssessmentEvaluation>(HealthRiskAssessmentEvaluation.class, "", "HealthRiskAssessmentEvaluation", HealthRiskAssessmentEvaluation.class, this);

  public SelectAqlField<TemporalAccessor> LAST_UPDATED_VALUE = new AqlFieldImp<TemporalAccessor>(HealthRiskAssessmentEvaluation.class, "/protocol[at0010]/items[at0024]/value|value", "lastUpdatedValue", TemporalAccessor.class, this);

  public SelectAqlField<HealthRiskAssessmentRiskFactorChoice> RISK_FACTOR = new AqlFieldImp<HealthRiskAssessmentRiskFactorChoice>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0016]/items[at0013]/value", "riskFactor", HealthRiskAssessmentRiskFactorChoice.class, this);

  public SelectAqlField<String> RISK_ASSESSMENT_VALUE = new AqlFieldImp<String>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0003]/value|value", "riskAssessmentValue", String.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(HealthRiskAssessmentEvaluation.class, "/protocol[at0010]/items[at0011]", "extension", Cluster.class, this);

  public SelectAqlField<HealthRiskDefiningcode> HEALTH_RISK_DEFININGCODE = new AqlFieldImp<HealthRiskDefiningcode>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "healthRiskDefiningcode", HealthRiskDefiningcode.class, this);

  public SelectAqlField<HealthRiskAssessmentRiskFactorsChoice> RISK_FACTORS = new AqlFieldImp<HealthRiskAssessmentRiskFactorsChoice>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0016]/name", "riskFactors", HealthRiskAssessmentRiskFactorsChoice.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(HealthRiskAssessmentEvaluation.class, "/language", "language", Language.class, this);

  public SelectAqlField<HealthRiskAssessmentPresenceChoice> PRESENCE = new AqlFieldImp<HealthRiskAssessmentPresenceChoice>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0016]/items[at0017]/value", "presence", HealthRiskAssessmentPresenceChoice.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(HealthRiskAssessmentEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<String> ASSESSMENT_METHOD_VALUE = new AqlFieldImp<String>(HealthRiskAssessmentEvaluation.class, "/protocol[at0010]/items[at0025]/value|value", "assessmentMethodValue", String.class, this);

  public SelectAqlField<HealthRiskAssessmentDetailChoice> DETAIL = new AqlFieldImp<HealthRiskAssessmentDetailChoice>(HealthRiskAssessmentEvaluation.class, "/data[at0001]/items[at0016]/items[at0027]", "detail", HealthRiskAssessmentDetailChoice.class, this);

  private HealthRiskAssessmentEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.health_risk.v1");
  }

  public static HealthRiskAssessmentEvaluationContainment getInstance() {
    return new HealthRiskAssessmentEvaluationContainment();
  }
}
