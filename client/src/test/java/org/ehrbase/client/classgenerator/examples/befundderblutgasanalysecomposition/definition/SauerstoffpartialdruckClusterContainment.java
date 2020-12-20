package org.ehrbase.client.classgenerator.examples.befundderblutgasanalysecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class SauerstoffpartialdruckClusterContainment extends Containment {
  public SelectAqlField<SauerstoffpartialdruckCluster> SAUERSTOFFPARTIALDRUCK_CLUSTER =
      new AqlFieldImp<SauerstoffpartialdruckCluster>(
          SauerstoffpartialdruckCluster.class,
          "",
          "SauerstoffpartialdruckCluster",
          SauerstoffpartialdruckCluster.class,
          this);

  public SelectAqlField<UntersuchterAnalytDefiningCode2> UNTERSUCHTER_ANALYT_DEFINING_CODE =
      new AqlFieldImp<UntersuchterAnalytDefiningCode2>(
          SauerstoffpartialdruckCluster.class,
          "/items[at0024]/value|defining_code",
          "untersuchterAnalytDefiningCode",
          UntersuchterAnalytDefiningCode2.class,
          this);

  public SelectAqlField<Double> ANALYT_RESULTAT_MAGNITUDE =
      new AqlFieldImp<Double>(
          SauerstoffpartialdruckCluster.class,
          "/items[at0001]/value|magnitude",
          "analytResultatMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> ANALYT_RESULTAT_UNITS =
      new AqlFieldImp<String>(
          SauerstoffpartialdruckCluster.class,
          "/items[at0001]/value|units",
          "analytResultatUnits",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS =
      new ListAqlFieldImp<Cluster>(
          SauerstoffpartialdruckCluster.class,
          "/items[at0014]",
          "analyseergebnisDetails",
          Cluster.class,
          this);

  public SelectAqlField<String> ERGEBNIS_STATUS_VALUE =
      new AqlFieldImp<String>(
          SauerstoffpartialdruckCluster.class,
          "/items[at0005]/value|value",
          "ergebnisStatusValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          SauerstoffpartialdruckCluster.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private SauerstoffpartialdruckClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static SauerstoffpartialdruckClusterContainment getInstance() {
    return new SauerstoffpartialdruckClusterContainment();
  }
}
