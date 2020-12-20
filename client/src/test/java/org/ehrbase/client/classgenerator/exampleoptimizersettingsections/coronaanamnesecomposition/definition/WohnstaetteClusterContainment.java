package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class WohnstaetteClusterContainment extends Containment {
  public SelectAqlField<WohnstaetteCluster> WOHNSTAETTE_CLUSTER =
      new AqlFieldImp<WohnstaetteCluster>(
          WohnstaetteCluster.class, "", "WohnstaetteCluster", WohnstaetteCluster.class, this);

  public SelectAqlField<Long> ANZAHL_DER_SCHLAFZIMMER_MAGNITUDE =
      new AqlFieldImp<Long>(
          WohnstaetteCluster.class,
          "/items[at0028]/value|magnitude",
          "anzahlDerSchlafzimmerMagnitude",
          Long.class,
          this);

  public ListSelectAqlField<Cluster> ERGAENZENDE_DETAILS =
      new ListAqlFieldImp<Cluster>(
          WohnstaetteCluster.class, "/items[at0003]", "ergaenzendeDetails", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          WohnstaetteCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private WohnstaetteClusterContainment() {
    super("openEHR-EHR-CLUSTER.dwelling.v0");
  }

  public static WohnstaetteClusterContainment getInstance() {
    return new WohnstaetteClusterContainment();
  }
}
