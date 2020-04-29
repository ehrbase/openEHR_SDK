package org.ehrbase.client.classgenerator.examples.beatmungcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class MedizingeratClusterContainment extends Containment {
  public SelectAqlField<MedizingeratCluster> MEDIZINGERAT_CLUSTER = new AqlFieldImp<MedizingeratCluster>(MedizingeratCluster.class, "", "MedizingeratCluster", MedizingeratCluster.class, this);

  public SelectAqlField<TemporalAccessor> HERSTELLUNGSDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(MedizingeratCluster.class, "/items[at0005]/value|value", "herstellungsdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<String> CHARGENNUMMER_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0006]/value|value", "chargennummerValue", String.class, this);

  public SelectAqlField<DvIdentifier> EINDEUTIGE_IDENTIFIKATIONSNUMMER_ID = new AqlFieldImp<DvIdentifier>(MedizingeratCluster.class, "/items[at0021]/value", "eindeutigeIdentifikationsnummerId", DvIdentifier.class, this);

  public SelectAqlField<String> KATALOGNUMMER_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0022]/value|value", "katalognummerValue", String.class, this);

  public SelectAqlField<String> MODELLNUMMER_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0023]/value|value", "modellnummerValue", String.class, this);

  public SelectAqlField<String> GERATETYP_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0003]/value|value", "geratetypValue", String.class, this);

  public SelectAqlField<String> HERSTELLER_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0004]/value|value", "herstellerValue", String.class, this);

  public SelectAqlField<TemporalAccessor> ABLAUFDATUM_VALUE = new AqlFieldImp<TemporalAccessor>(MedizingeratCluster.class, "/items[at0007]/value|value", "ablaufdatumValue", TemporalAccessor.class, this);

  public SelectAqlField<String> KOMMENTAR_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0008]/value|value", "kommentarValue", String.class, this);

  public ListSelectAqlField<Cluster> EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0009]", "eigenschaften", Cluster.class, this);

  public SelectAqlField<String> GERATENAME_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0001]/value|value", "geratenameValue", String.class, this);

  public SelectAqlField<String> BESCHREIBUNG_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0002]/value|value", "beschreibungValue", String.class, this);

  public ListSelectAqlField<MedizingeratWeitereIDElement> WEITERE_ID = new ListAqlFieldImp<MedizingeratWeitereIDElement>(MedizingeratCluster.class, "/items[at0024]", "weitereId", MedizingeratWeitereIDElement.class, this);

  public SelectAqlField<String> SOFTWAREVERSION_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0025]/value|value", "softwareversionValue", String.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0026]", "erweiterung", Cluster.class, this);

  public SelectAqlField<String> SERIENNUMMER_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0020]/value|value", "seriennummerValue", String.class, this);

  public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0027]", "multimedia", Cluster.class, this);

  public ListSelectAqlField<Cluster> GERATEVERWALTUNG = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0019]", "gerateverwaltung", Cluster.class, this);

  public ListSelectAqlField<Cluster> KOMPONENTEN = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0018]", "komponenten", Cluster.class, this);

  private MedizingeratClusterContainment() {
    super("openEHR-EHR-CLUSTER.device.v1");
  }

  public static MedizingeratClusterContainment getInstance() {
    return new MedizingeratClusterContainment();
  }
}
