package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class StatusClusterContainment extends Containment {
  public SelectAqlField<StatusCluster> STATUS_CLUSTER =
      new AqlFieldImp<StatusCluster>(
          StatusCluster.class, "", "StatusCluster", StatusCluster.class, this);

  public SelectAqlField<DiagnosestatusDefiningcode> DIAGNOSESTATUS_DEFININGCODE =
      new AqlFieldImp<DiagnosestatusDefiningcode>(
          StatusCluster.class,
          "/items[at0004]/value|defining_code",
          "diagnosestatusDefiningcode",
          DiagnosestatusDefiningcode.class,
          this);

  private StatusClusterContainment() {
    super("openEHR-EHR-CLUSTER.problem_qualifier.v1");
  }

  public static StatusClusterContainment getInstance() {
    return new StatusClusterContainment();
  }
}
