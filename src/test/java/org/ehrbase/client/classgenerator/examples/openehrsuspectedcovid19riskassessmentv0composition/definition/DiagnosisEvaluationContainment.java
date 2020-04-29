package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.DiagnosisNameDefiningcode;
import org.ehrbase.client.classgenerator.examples.shareddefinition.Language;

public class DiagnosisEvaluationContainment extends Containment {
  public SelectAqlField<DiagnosisEvaluation> DIAGNOSIS_EVALUATION = new AqlFieldImp<DiagnosisEvaluation>(DiagnosisEvaluation.class, "", "DiagnosisEvaluation", DiagnosisEvaluation.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(DiagnosisEvaluation.class, "/protocol[at0032]/items[at0071]", "extension", Cluster.class, this);

  public SelectAqlField<DiagnosisNameDefiningcode> DIAGNOSIS_NAME_DEFININGCODE = new AqlFieldImp<DiagnosisNameDefiningcode>(DiagnosisEvaluation.class, "/data[at0001]/items[at0002]/value|defining_code", "diagnosisNameDefiningcode", DiagnosisNameDefiningcode.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(DiagnosisEvaluation.class, "/data[at0001]/items[at0039]", "structuredBodySite", Cluster.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(DiagnosisEvaluation.class, "/language", "language", Language.class, this);

  public ListSelectAqlField<DiagnosisQualifierCluster> DIAGNOSIS_QUALIFIER = new ListAqlFieldImp<DiagnosisQualifierCluster>(DiagnosisEvaluation.class, "/data[at0001]/items[openEHR-EHR-CLUSTER.problem_qualifier.v1]", "diagnosisQualifier", DiagnosisQualifierCluster.class, this);

  public SelectAqlField<TemporalAccessor> LAST_UPDATED_VALUE = new AqlFieldImp<TemporalAccessor>(DiagnosisEvaluation.class, "/protocol[at0032]/items[at0070]/value|value", "lastUpdatedValue", TemporalAccessor.class, this);

  public SelectAqlField<PartyProxy> SUBJECT = new AqlFieldImp<PartyProxy>(DiagnosisEvaluation.class, "/subject", "subject", PartyProxy.class, this);

  public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(DiagnosisEvaluation.class, "/data[at0001]/items[at0043]", "specificDetails", Cluster.class, this);

  private DiagnosisEvaluationContainment() {
    super("openEHR-EHR-EVALUATION.problem_diagnosis.v1");
  }

  public static DiagnosisEvaluationContainment getInstance() {
    return new DiagnosisEvaluationContainment();
  }
}
