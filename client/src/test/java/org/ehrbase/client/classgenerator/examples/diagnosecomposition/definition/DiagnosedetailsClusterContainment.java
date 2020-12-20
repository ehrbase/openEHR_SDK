package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DiagnosedetailsClusterContainment extends Containment {
  public SelectAqlField<DiagnosedetailsCluster> DIAGNOSEDETAILS_CLUSTER =
      new AqlFieldImp<DiagnosedetailsCluster>(
          DiagnosedetailsCluster.class,
          "",
          "DiagnosedetailsCluster",
          DiagnosedetailsCluster.class,
          this);

  public SelectAqlField<String> BEGRUNDUNG_VON_AUSNAHMEN_VALUE =
      new AqlFieldImp<String>(
          DiagnosedetailsCluster.class,
          "/items[at0001]/value|value",
          "begrundungVonAusnahmenValue",
          String.class,
          this);

  public SelectAqlField<String> DIAGNOSETYP_VALUE =
      new AqlFieldImp<String>(
          DiagnosedetailsCluster.class,
          "/items[at0002]/value|value",
          "diagnosetypValue",
          String.class,
          this);

  public SelectAqlField<Boolean> VORHANDEN_BEI_AUFNAHME_VALUE =
      new AqlFieldImp<Boolean>(
          DiagnosedetailsCluster.class,
          "/items[at0016]/value|value",
          "vorhandenBeiAufnahmeValue",
          Boolean.class,
          this);

  public SelectAqlField<Boolean> VORHANDEN_BEI_ENTLASSUNG_VALUE =
      new AqlFieldImp<Boolean>(
          DiagnosedetailsCluster.class,
          "/items[at0017]/value|value",
          "vorhandenBeiEntlassungValue",
          Boolean.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          DiagnosedetailsCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private DiagnosedetailsClusterContainment() {
    super("openEHR-EHR-CLUSTER.diagnose_details.v0");
  }

  public static DiagnosedetailsClusterContainment getInstance() {
    return new DiagnosedetailsClusterContainment();
  }
}
