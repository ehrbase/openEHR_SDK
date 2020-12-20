package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class LaboranalytResultatClusterContainment extends Containment {
  public SelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT_CLUSTER =
      new AqlFieldImp<LaboranalytResultatCluster>(
          LaboranalytResultatCluster.class,
          "",
          "LaboranalytResultatCluster",
          LaboranalytResultatCluster.class,
          this);

  public SelectAqlField<AntibiotikumDefiningCode> ANTIBIOTIKUM_DEFINING_CODE =
      new AqlFieldImp<AntibiotikumDefiningCode>(
          LaboranalytResultatCluster.class,
          "/items[at0024]/value|defining_code",
          "antibiotikumDefiningCode",
          AntibiotikumDefiningCode.class,
          this);

  public SelectAqlField<Double> MINIMALE_HEMMKONZENTRATION_MAGNITUDE =
      new AqlFieldImp<Double>(
          LaboranalytResultatCluster.class,
          "/items[at0001]/value|magnitude",
          "minimaleHemmkonzentrationMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> MINIMALE_HEMMKONZENTRATION_UNITS =
      new AqlFieldImp<String>(
          LaboranalytResultatCluster.class,
          "/items[at0001]/value|units",
          "minimaleHemmkonzentrationUnits",
          String.class,
          this);

  public ListSelectAqlField<Cluster> ANALYSEERGEBNIS_DETAILS =
      new ListAqlFieldImp<Cluster>(
          LaboranalytResultatCluster.class,
          "/items[at0014]",
          "analyseergebnisDetails",
          Cluster.class,
          this);

  public SelectAqlField<String> RESISTENZ_VALUE =
      new AqlFieldImp<String>(
          LaboranalytResultatCluster.class,
          "/items[at0004]/value|value",
          "resistenzValue",
          String.class,
          this);

  public SelectAqlField<String> KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          LaboranalytResultatCluster.class,
          "/items[at0003]/value|value",
          "kommentarValue",
          String.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          LaboranalytResultatCluster.class,
          "/feeder_audit",
          "feederAudit",
          FeederAudit.class,
          this);

  private LaboranalytResultatClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_analyte.v1");
  }

  public static LaboranalytResultatClusterContainment getInstance() {
    return new LaboranalytResultatClusterContainment();
  }
}
