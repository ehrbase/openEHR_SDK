package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class MedizingeratClusterContainment extends Containment {
  public SelectAqlField<MedizingeratCluster> MEDIZINGERAT_CLUSTER = new AqlFieldImp<MedizingeratCluster>(MedizingeratCluster.class, "", "MedizingeratCluster", MedizingeratCluster.class, this);

  public ListSelectAqlField<Cluster> EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0009]", "eigenschaften", Cluster.class, this);

  public SelectAqlField<DvIdentifier> EINDEUTIGE_IDENTIFIKATIONSNUMMER_ID = new AqlFieldImp<DvIdentifier>(MedizingeratCluster.class, "/items[at0021]/value", "eindeutigeIdentifikationsnummerId", DvIdentifier.class, this);

  public SelectAqlField<String> GERATETYP_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0003]/value|value", "geratetypValue", String.class, this);

  public SelectAqlField<String> GERATENAME_VALUE = new AqlFieldImp<String>(MedizingeratCluster.class, "/items[at0001]/value|value", "geratenameValue", String.class, this);

  public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(MedizingeratCluster.class, "/items[at0026]", "erweiterung", Cluster.class, this);

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
