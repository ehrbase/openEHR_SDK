package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

public class Covid19ExposureEvaluationContainment extends Containment {
  public SelectAqlField<Covid19ExposureEvaluation> COVID19_EXPOSURE_EVALUATION = new AqlFieldImp<Covid19ExposureEvaluation>(Covid19ExposureEvaluation.class, "", "Covid19ExposureEvaluation", Covid19ExposureEvaluation.class, this);

  public SelectAqlField<HealthRiskDefiningCode> HEALTH_RISK_DEFINING_CODE = new AqlFieldImp<HealthRiskDefiningCode>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0002.1]/value|defining_code", "healthRiskDefiningCode", HealthRiskDefiningCode.class, this);

  public SelectAqlField<RiskFactorDefiningCode> RISK_FACTOR_DEFINING_CODE = new AqlFieldImp<RiskFactorDefiningCode>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code", "riskFactorDefiningCode", RiskFactorDefiningCode.class, this);

  public SelectAqlField<PresenceDefiningCode2> PRESENCE_DEFINING_CODE = new AqlFieldImp<PresenceDefiningCode2>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code", "presenceDefiningCode", PresenceDefiningCode2.class, this);

  public ListSelectAqlField<Cluster> CARE_HOME_HAS_SUSPECTED_CONFIRMED_COVID19_DETAIL = new ListAqlFieldImp<Cluster>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0027.1]", "careHomeHasSuspectedConfirmedCovid19Detail", Cluster.class, this);

  public SelectAqlField<RiskFactorDefiningCode2> RISK_FACTOR_DEFINING_CODE2 = new AqlFieldImp<RiskFactorDefiningCode2>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code", "riskFactorDefiningCode2", RiskFactorDefiningCode2.class, this);

  public SelectAqlField<PresenceDefiningCode2> PRESENCE_DEFINING_CODE2 = new AqlFieldImp<PresenceDefiningCode2>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code", "presenceDefiningCode2", PresenceDefiningCode2.class, this);

  public ListSelectAqlField<Cluster> CONTACT_WITH_CONFIRMED_CASE_DETAIL = new ListAqlFieldImp<Cluster>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0027.1]", "contactWithConfirmedCaseDetail", Cluster.class, this);

  public SelectAqlField<RiskFactorDefiningCode3> RISK_FACTOR_DEFINING_CODE3 = new AqlFieldImp<RiskFactorDefiningCode3>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0013.1]/value|defining_code", "riskFactorDefiningCode3", RiskFactorDefiningCode3.class, this);

  public SelectAqlField<PresenceDefiningCode2> PRESENCE_DEFINING_CODE3 = new AqlFieldImp<PresenceDefiningCode2>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0017.1]/value|defining_code", "presenceDefiningCode3", PresenceDefiningCode2.class, this);

  public ListSelectAqlField<Cluster> OTHER_RESIDENTS_HOUSEHOLD_MEMBERS_UNWELL_DETAIL = new ListAqlFieldImp<Cluster>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0016]/items[at0027.1]", "otherResidentsHouseholdMembersUnwellDetail", Cluster.class, this);

  public SelectAqlField<RiskAssessmentDefiningCode> RISK_ASSESSMENT_DEFINING_CODE = new AqlFieldImp<RiskAssessmentDefiningCode>(Covid19ExposureEvaluation.class, "/data[at0001]/items[at0003.1]/value|defining_code", "riskAssessmentDefiningCode", RiskAssessmentDefiningCode.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(Covid19ExposureEvaluation.class, "/protocol[at0010]/items[at0011]", "extension", Cluster.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(Covid19ExposureEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(Covid19ExposureEvaluation.class, "/language", "language", Language.class, this);

  private Covid19ExposureEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.health_risk-covid.v0");
  }

  public static Covid19ExposureEvaluationContainment getInstance() {
    return new Covid19ExposureEvaluationContainment();
  }
}
