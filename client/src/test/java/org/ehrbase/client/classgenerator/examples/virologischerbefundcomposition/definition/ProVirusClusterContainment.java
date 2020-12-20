package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ProVirusClusterContainment extends Containment {
  public SelectAqlField<ProVirusCluster> PRO_VIRUS_CLUSTER =
      new AqlFieldImp<ProVirusCluster>(
          ProVirusCluster.class, "", "ProVirusCluster", ProVirusCluster.class, this);

  public SelectAqlField<Long> ANALYSEERGEBNIS_REIHENFOLGE_MAGNITUDE =
      new AqlFieldImp<Long>(
          ProVirusCluster.class,
          "/items[at0027]/value|magnitude",
          "analyseergebnisReihenfolgeMagnitude",
          Long.class,
          this);

  public SelectAqlField<String> VIRUS_VALUE =
      new AqlFieldImp<String>(
          ProVirusCluster.class, "/items[at0024]/value|value", "virusValue", String.class, this);

  public SelectAqlField<String> NACHWEIS_VALUE =
      new AqlFieldImp<String>(
          ProVirusCluster.class, "/items[at0001]/value|value", "nachweisValue", String.class, this);

  public SelectAqlField<Double> VIRUSLAST_CT_WERT_MAGNITUDE =
      new AqlFieldImp<Double>(
          ProVirusCluster.class,
          "/items[at0001]/value|magnitude",
          "viruslastCtWertMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> VIRUSLAST_CT_WERT_UNITS =
      new AqlFieldImp<String>(
          ProVirusCluster.class,
          "/items[at0001]/value|units",
          "viruslastCtWertUnits",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS =
      new ListAqlFieldImp<Cluster>(
          ProVirusCluster.class, "/items[at0014]", "analyseergebnisDetails", Cluster.class, this);

  public SelectAqlField<DvIdentifier> ZUGEHORIGE_LABORPROBE =
      new AqlFieldImp<DvIdentifier>(
          ProVirusCluster.class,
          "/items[at0026]/value",
          "zugehorigeLaborprobe",
          DvIdentifier.class,
          this);

  public SelectAqlField<String> KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          ProVirusCluster.class,
          "/items[at0003]/value|value",
          "kommentarValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          ProVirusCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ProVirusClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static ProVirusClusterContainment getInstance() {
    return new ProVirusClusterContainment();
  }
}
