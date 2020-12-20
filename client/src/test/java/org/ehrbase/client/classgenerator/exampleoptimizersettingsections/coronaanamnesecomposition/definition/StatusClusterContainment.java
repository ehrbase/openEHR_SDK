package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class StatusClusterContainment extends Containment {
  public SelectAqlField<StatusCluster> STATUS_CLUSTER =
      new AqlFieldImp<StatusCluster>(
          StatusCluster.class, "", "StatusCluster", StatusCluster.class, this);

  public SelectAqlField<DiagnosestatusDefiningCode> DIAGNOSESTATUS_DEFINING_CODE =
      new AqlFieldImp<DiagnosestatusDefiningCode>(
          StatusCluster.class,
          "/items[at0004]/value|defining_code",
          "diagnosestatusDefiningCode",
          DiagnosestatusDefiningCode.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          StatusCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private StatusClusterContainment() {
    super("openEHR-EHR-CLUSTER.problem_qualifier.v1");
  }

  public static StatusClusterContainment getInstance() {
    return new StatusClusterContainment();
  }
}
