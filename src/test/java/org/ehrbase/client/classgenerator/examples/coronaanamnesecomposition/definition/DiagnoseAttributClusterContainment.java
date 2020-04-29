package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DiagnoseAttributClusterContainment extends Containment {
  public SelectAqlField<DiagnoseAttributCluster> DIAGNOSE_ATTRIBUT_CLUSTER = new AqlFieldImp<DiagnoseAttributCluster>(DiagnoseAttributCluster.class, "", "DiagnoseAttributCluster", DiagnoseAttributCluster.class, this);

  public SelectAqlField<DiagnosestatusDefiningcode> DIAGNOSESTATUS_DEFININGCODE = new AqlFieldImp<DiagnosestatusDefiningcode>(DiagnoseAttributCluster.class, "/items[at0004]/value|defining_code", "diagnosestatusDefiningcode", DiagnosestatusDefiningcode.class, this);

  private DiagnoseAttributClusterContainment() {
    super("openEHR-EHR-CLUSTER.problem_qualifier.v1");
  }

  public static DiagnoseAttributClusterContainment getInstance() {
    return new DiagnoseAttributClusterContainment();
  }
}
