package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KulturClusterContainment extends Containment {
  public SelectAqlField<KulturCluster> KULTUR_CLUSTER =
      new AqlFieldImp<KulturCluster>(
          KulturCluster.class, "", "KulturCluster", KulturCluster.class, this);

  public ListSelectAqlField<ProVirusCluster> PRO_VIRUS =
      new ListAqlFieldImp<ProVirusCluster>(
          KulturCluster.class,
          "/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
          "proVirus",
          ProVirusCluster.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          KulturCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private KulturClusterContainment() {
    super("openEHR-EHR-CLUSTER.laboratory_test_panel.v0");
  }

  public static KulturClusterContainment getInstance() {
    return new KulturClusterContainment();
  }
}
