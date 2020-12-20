package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class PhWertClusterContainment extends Containment {
  public SelectAqlField<PhWertCluster> PH_WERT_CLUSTER =
      new AqlFieldImp<PhWertCluster>(
          PhWertCluster.class, "", "PhWertCluster", PhWertCluster.class, this);

  public SelectAqlField<UntersuchterAnalytDefiningCode3> UNTERSUCHTER_ANALYT_DEFINING_CODE =
      new AqlFieldImp<UntersuchterAnalytDefiningCode3>(
          PhWertCluster.class,
          "/items[at0024]/value|defining_code",
          "untersuchterAnalytDefiningCode",
          UntersuchterAnalytDefiningCode3.class,
          this);

  public SelectAqlField<Double> ANALYT_RESULTAT_MAGNITUDE =
      new AqlFieldImp<Double>(
          PhWertCluster.class,
          "/items[at0001]/value|magnitude",
          "analytResultatMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> ANALYT_RESULTAT_UNITS =
      new AqlFieldImp<String>(
          PhWertCluster.class,
          "/items[at0001]/value|units",
          "analytResultatUnits",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS =
      new ListAqlFieldImp<Cluster>(
          PhWertCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  public SelectAqlField<String> ERGEBNIS_STATUS_VALUE =
      new AqlFieldImp<String>(
          PhWertCluster.class,
          "/items[at0005]/value|value",
          "ergebnisStatusValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          PhWertCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private PhWertClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static PhWertClusterContainment getInstance() {
    return new PhWertClusterContainment();
  }
}
