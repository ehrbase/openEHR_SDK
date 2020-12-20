package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.quantity.DvOrdinal;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class ErregerdetailsClusterContainment extends Containment {
  public SelectAqlField<ErregerdetailsCluster> ERREGERDETAILS_CLUSTER =
      new AqlFieldImp<ErregerdetailsCluster>(
          ErregerdetailsCluster.class,
          "",
          "ErregerdetailsCluster",
          ErregerdetailsCluster.class,
          this);

  public ListSelectAqlField<ErregerdetailsKeimSubtypElement> KEIM_SUBTYP =
      new ListAqlFieldImp<ErregerdetailsKeimSubtypElement>(
          ErregerdetailsCluster.class,
          "/items[at0047]",
          "keimSubtyp",
          ErregerdetailsKeimSubtypElement.class,
          this);

  public SelectAqlField<Double> KEIMZAHL_MAGNITUDE =
      new AqlFieldImp<Double>(
          ErregerdetailsCluster.class,
          "/items[at0035]/value|magnitude",
          "keimzahlMagnitude",
          Double.class,
          this);

  public SelectAqlField<String> KEIMZAHL_UNITS =
      new AqlFieldImp<String>(
          ErregerdetailsCluster.class,
          "/items[at0035]/value|units",
          "keimzahlUnits",
          String.class,
          this);

  public SelectAqlField<DvOrdinal> HAUFIGKEIT =
      new AqlFieldImp<DvOrdinal>(
          ErregerdetailsCluster.class, "/items[at0003]/value", "haufigkeit", DvOrdinal.class, this);

  public SelectAqlField<String> VIRULENZFAKTOR_VALUE =
      new AqlFieldImp<String>(
          ErregerdetailsCluster.class,
          "/items[at0016]/value|value",
          "virulenzfaktorValue",
          String.class,
          this);

  public SelectAqlField<AntibiogrammCluster> ANTIBIOGRAMM =
      new AqlFieldImp<AntibiogrammCluster>(
          ErregerdetailsCluster.class,
          "/items[openEHR-EHR-CLUSTER.laboratory_test_panel.v0]",
          "antibiogramm",
          AntibiogrammCluster.class,
          this);

  public ListSelectAqlField<ErregerdetailsResistenzmechanismusCluster> RESISTENZMECHANISMUS =
      new ListAqlFieldImp<ErregerdetailsResistenzmechanismusCluster>(
          ErregerdetailsCluster.class,
          "/items[at0057]",
          "resistenzmechanismus",
          ErregerdetailsResistenzmechanismusCluster.class,
          this);

  public SelectAqlField<MreKlasseDefiningCode> MRE_KLASSE_DEFINING_CODE =
      new AqlFieldImp<MreKlasseDefiningCode>(
          ErregerdetailsCluster.class,
          "/items[at0018]/value|defining_code",
          "mreKlasseDefiningCode",
          MreKlasseDefiningCode.class,
          this);

  public SelectAqlField<String> KOMMENTAR_VALUE =
      new AqlFieldImp<String>(
          ErregerdetailsCluster.class,
          "/items[at0062]/value|value",
          "kommentarValue",
          String.class,
          this);

  public ListSelectAqlField<Cluster> WEITERE_ERGANZUNGEN =
      new ListAqlFieldImp<Cluster>(
          ErregerdetailsCluster.class, "/items[at0059]", "weitereErganzungen", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          ErregerdetailsCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private ErregerdetailsClusterContainment() {
    super("openEHR-EHR-CLUSTER.erregerdetails.v1");
  }

  public static ErregerdetailsClusterContainment getInstance() {
    return new ErregerdetailsClusterContainment();
  }
}
