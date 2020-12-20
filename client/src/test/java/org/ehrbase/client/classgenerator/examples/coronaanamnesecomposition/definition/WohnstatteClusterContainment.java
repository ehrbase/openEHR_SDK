package org.ehrbase.client.classgenerator.examples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class WohnstatteClusterContainment extends Containment {
  public SelectAqlField<WohnstatteCluster> WOHNSTATTE_CLUSTER =
      new AqlFieldImp<WohnstatteCluster>(
          WohnstatteCluster.class, "", "WohnstatteCluster", WohnstatteCluster.class, this);

  public SelectAqlField<Long> ANZAHL_DER_SCHLAFZIMMER_MAGNITUDE =
      new AqlFieldImp<Long>(
          WohnstatteCluster.class,
          "/items[at0028]/value|magnitude",
          "anzahlDerSchlafzimmerMagnitude",
          Long.class,
          this);

  public ListSelectAqlField<Cluster> ERGANZENDE_DETAILS =
      new ListAqlFieldImp<Cluster>(
          WohnstatteCluster.class, "/items[at0003]", "erganzendeDetails", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          WohnstatteCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private WohnstatteClusterContainment() {
    super("openEHR-EHR-CLUSTER.dwelling.v0");
  }

  public static WohnstatteClusterContainment getInstance() {
    return new WohnstatteClusterContainment();
  }
}
