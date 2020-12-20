package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class EinsenderstandortClusterContainment extends Containment {
  public SelectAqlField<EinsenderstandortCluster> EINSENDERSTANDORT_CLUSTER =
      new AqlFieldImp<EinsenderstandortCluster>(
          EinsenderstandortCluster.class,
          "",
          "EinsenderstandortCluster",
          EinsenderstandortCluster.class,
          this);

  public SelectAqlField<String> STANDORTTYP_VALUE =
      new AqlFieldImp<String>(
          EinsenderstandortCluster.class,
          "/items[at0040]/value|value",
          "standorttypValue",
          String.class,
          this);

  public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE =
      new AqlFieldImp<String>(
          EinsenderstandortCluster.class,
          "/items[at0046]/value|value",
          "standortbeschreibungValue",
          String.class,
          this);

  public SelectAqlField<String> STANDORTSCHLUSSEL_VALUE =
      new AqlFieldImp<String>(
          EinsenderstandortCluster.class,
          "/items[at0048]/value|value",
          "standortschlusselValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> DETAILS =
      new ListAqlFieldImp<Cluster>(
          EinsenderstandortCluster.class, "/items[at0047]", "details", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          EinsenderstandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private EinsenderstandortClusterContainment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static EinsenderstandortClusterContainment getInstance() {
    return new EinsenderstandortClusterContainment();
  }
}
