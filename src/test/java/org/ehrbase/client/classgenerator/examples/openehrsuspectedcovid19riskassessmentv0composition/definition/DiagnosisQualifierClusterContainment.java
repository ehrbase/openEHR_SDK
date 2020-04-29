package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DiagnosisQualifierClusterContainment extends Containment {
  public SelectAqlField<DiagnosisQualifierCluster> DIAGNOSIS_QUALIFIER_CLUSTER = new AqlFieldImp<DiagnosisQualifierCluster>(DiagnosisQualifierCluster.class, "", "DiagnosisQualifierCluster", DiagnosisQualifierCluster.class, this);

  public SelectAqlField<DiagnosticStatusDefiningcode> DIAGNOSTIC_STATUS_DEFININGCODE = new AqlFieldImp<DiagnosticStatusDefiningcode>(DiagnosisQualifierCluster.class, "/items[at0004]/value|defining_code", "diagnosticStatusDefiningcode", DiagnosticStatusDefiningcode.class, this);

  private DiagnosisQualifierClusterContainment() {
    super("openEHR-EHR-CLUSTER.problem_qualifier.v1");
  }

  public static DiagnosisQualifierClusterContainment getInstance() {
    return new DiagnosisQualifierClusterContainment();
  }
}
