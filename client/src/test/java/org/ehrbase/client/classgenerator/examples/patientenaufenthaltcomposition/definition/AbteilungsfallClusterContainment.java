package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AbteilungsfallClusterContainment extends Containment {
  public SelectAqlField<AbteilungsfallCluster> ABTEILUNGSFALL_CLUSTER =
      new AqlFieldImp<AbteilungsfallCluster>(
          AbteilungsfallCluster.class,
          "",
          "AbteilungsfallCluster",
          AbteilungsfallCluster.class,
          this);

  public SelectAqlField<String> ZUGEHORIGE_ABTEILUNGSFALL_KENNUNG_VALUE =
      new AqlFieldImp<String>(
          AbteilungsfallCluster.class,
          "/items[at0001]/value|value",
          "zugehorigeAbteilungsfallKennungValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          AbteilungsfallCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private AbteilungsfallClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static AbteilungsfallClusterContainment getInstance() {
    return new AbteilungsfallClusterContainment();
  }
}
