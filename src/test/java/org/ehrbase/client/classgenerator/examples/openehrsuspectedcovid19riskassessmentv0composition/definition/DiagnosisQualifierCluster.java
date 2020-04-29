package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
public class DiagnosisQualifierCluster {
  @Path("/items[at0004]/value|defining_code")
  private DiagnosticStatusDefiningcode diagnosticStatusDefiningcode;

  public void setDiagnosticStatusDefiningcode(
      DiagnosticStatusDefiningcode diagnosticStatusDefiningcode) {
     this.diagnosticStatusDefiningcode = diagnosticStatusDefiningcode;
  }

  public DiagnosticStatusDefiningcode getDiagnosticStatusDefiningcode() {
     return this.diagnosticStatusDefiningcode ;
  }
}
