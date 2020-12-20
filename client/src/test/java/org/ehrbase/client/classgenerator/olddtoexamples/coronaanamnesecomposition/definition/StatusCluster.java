package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.problem_qualifier.v1")
public class StatusCluster {
  @Path("/items[at0004]/value|defining_code")
  private DiagnosestatusDefiningcode diagnosestatusDefiningcode;

  public void setDiagnosestatusDefiningcode(DiagnosestatusDefiningcode diagnosestatusDefiningcode) {
    this.diagnosestatusDefiningcode = diagnosestatusDefiningcode;
  }

  public DiagnosestatusDefiningcode getDiagnosestatusDefiningcode() {
    return this.diagnosestatusDefiningcode;
  }
}
