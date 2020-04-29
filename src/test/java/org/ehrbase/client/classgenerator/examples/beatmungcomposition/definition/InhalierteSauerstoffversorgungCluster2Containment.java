package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.Boolean;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class InhalierteSauerstoffversorgungCluster2Containment extends Containment {
  public SelectAqlField<InhalierteSauerstoffversorgungCluster2> INHALIERTE_SAUERSTOFFVERSORGUNG_CLUSTER2 = new AqlFieldImp<InhalierteSauerstoffversorgungCluster2>(InhalierteSauerstoffversorgungCluster2.class, "", "InhalierteSauerstoffversorgungCluster2", InhalierteSauerstoffversorgungCluster2.class, this);

  public SelectAqlField<MaskenbedingtesErythemDefiningcode> MASKENBEDINGTES_ERYTHEM_DEFININGCODE = new AqlFieldImp<MaskenbedingtesErythemDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0017]/items[at0018]/value|defining_code", "maskenbedingtesErythemDefiningcode", MaskenbedingtesErythemDefiningcode.class, this);

  public SelectAqlField<Cluster> DETAILS_DER_TRACHEALKANULE = new AqlFieldImp<Cluster>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0.97]", "detailsDerTrachealkanule", Cluster.class, this);

  public SelectAqlField<Boolean> EIGENVERABREICHUNG_VALUE = new AqlFieldImp<Boolean>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0095]/value|value", "eigenverabreichungValue", Boolean.class, this);

  public ListSelectAqlField<InhalierteSauerstoffversorgungLeckageDerMaskeElementMaske> LECKAGE_DER_MASKE = new ListAqlFieldImp<InhalierteSauerstoffversorgungLeckageDerMaskeElementMaske>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0017]/items[at0019]", "leckageDerMaske", InhalierteSauerstoffversorgungLeckageDerMaskeElementMaske.class, this);

  public SelectAqlField<Double> ATMOSPHARISCHER_DRUCK_MAGNITUDE = new AqlFieldImp<Double>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0094]/value|magnitude", "atmospharischerDruckMagnitude", Double.class, this);

  public SelectAqlField<String> ATMOSPHARISCHER_DRUCK_UNITS = new AqlFieldImp<String>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0094]/value|units", "atmospharischerDruckUnits", String.class, this);

  public SelectAqlField<Cluster> DETAILS_DES_TRACHEALTUBUS = new AqlFieldImp<Cluster>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0097]", "detailsDesTrachealtubus", Cluster.class, this);

  public SelectAqlField<BeatmungsschlauchsystemDefiningcode> BEATMUNGSSCHLAUCHSYSTEM_DEFININGCODE = new AqlFieldImp<BeatmungsschlauchsystemDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0007]/value|defining_code", "beatmungsschlauchsystemDefiningcode", BeatmungsschlauchsystemDefiningcode.class, this);

  public SelectAqlField<LuftsauerstoffCluster> LUFTSAUERSTOFF = new AqlFieldImp<LuftsauerstoffCluster>(InhalierteSauerstoffversorgungCluster2.class, "/items[openEHR-EHR-CLUSTER.ambient_oxygen.v0]", "luftsauerstoff", LuftsauerstoffCluster.class, this);

  public SelectAqlField<DurchflussmodusDefiningcode> DURCHFLUSSMODUS_DEFININGCODE = new AqlFieldImp<DurchflussmodusDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0.107]/value|defining_code", "durchflussmodusDefiningcode", DurchflussmodusDefiningcode.class, this);

  public SelectAqlField<TemporalAmount> DAUER_DER_THERAPIE_VALUE = new AqlFieldImp<TemporalAmount>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0093]/value|value", "dauerDerTherapieValue", TemporalAmount.class, this);

  public SelectAqlField<NameDesGasesSauerstoffDefiningcode> NAME_DES_GASES_SAUERSTOFF_DEFININGCODE = new AqlFieldImp<NameDesGasesSauerstoffDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0022.1]/value|defining_code", "nameDesGasesSauerstoffDefiningcode", NameDesGasesSauerstoffDefiningcode.class, this);

  public SelectAqlField<MaskengroEDefiningcode> MASKENGRO_E_DEFININGCODE = new AqlFieldImp<MaskengroEDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0017]/items[at0004]/value|defining_code", "maskengroEDefiningcode", MaskengroEDefiningcode.class, this);

  public SelectAqlField<Boolean> EINGESETZTER_ATEMGASBEFEUCHTER_VALUE = new AqlFieldImp<Boolean>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0003]/items[at0016]/value|value", "eingesetzterAtemgasbefeuchterValue", Boolean.class, this);

  public SelectAqlField<MethodeDefiningcode> METHODE_DEFININGCODE = new AqlFieldImp<MethodeDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0001]/value|defining_code", "methodeDefiningcode", MethodeDefiningcode.class, this);

  public SelectAqlField<MechanismusDefiningcode> MECHANISMUS_DEFININGCODE = new AqlFieldImp<MechanismusDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0002]/value|defining_code", "mechanismusDefiningcode", MechanismusDefiningcode.class, this);

  public ListSelectAqlField<Cluster> GERAT = new ListAqlFieldImp<Cluster>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0023]", "gerat", Cluster.class, this);

  public SelectAqlField<ArtDesAtemgasbefeuchtersDefiningcode> ART_DES_ATEMGASBEFEUCHTERS_DEFININGCODE = new AqlFieldImp<ArtDesAtemgasbefeuchtersDefiningcode>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0003]/items[at0009]/value|defining_code", "artDesAtemgasbefeuchtersDefiningcode", ArtDesAtemgasbefeuchtersDefiningcode.class, this);

  public SelectAqlField<TemporalAccessor> DATUM_UND_UHRZEIT_DES_LETZTEN_WECHSELS_DES_WASSERS_DES_ATEMGASBEFEUCHTERS_VALUE = new AqlFieldImp<TemporalAccessor>(InhalierteSauerstoffversorgungCluster2.class, "/items[at0003]/items[at0005]/value|value", "datumUndUhrzeitDesLetztenWechselsDesWassersDesAtemgasbefeuchtersValue", TemporalAccessor.class, this);

  private InhalierteSauerstoffversorgungCluster2Containment() {
    super("openEHR-EHR-CLUSTER.gas_delivery-oxygen.v0");
  }

  public static InhalierteSauerstoffversorgungCluster2Containment getInstance() {
    return new InhalierteSauerstoffversorgungCluster2Containment();
  }
}
