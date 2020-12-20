package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class VersorgungsfallClusterContainment extends Containment {
  public SelectAqlField<VersorgungsfallCluster> VERSORGUNGSFALL_CLUSTER =
      new AqlFieldImp<VersorgungsfallCluster>(
          VersorgungsfallCluster.class,
          "",
          "VersorgungsfallCluster",
          VersorgungsfallCluster.class,
          this);

  public SelectAqlField<String> ZUGEHORIGE_VERSORGUNGSFALL_KENNUNG_VALUE =
      new AqlFieldImp<String>(
          VersorgungsfallCluster.class,
          "/items[at0001]/value|value",
          "zugehorigeVersorgungsfallKennungValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          VersorgungsfallCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private VersorgungsfallClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static VersorgungsfallClusterContainment getInstance() {
    return new VersorgungsfallClusterContainment();
  }
}
