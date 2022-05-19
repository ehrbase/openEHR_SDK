package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class LaborbereichClusterContainment extends Containment {
  public SelectAqlField<LaborbereichCluster> LABORBEREICH_CLUSTER = new AqlFieldImp<LaborbereichCluster>(LaborbereichCluster.class, "", "LaborbereichCluster", LaborbereichCluster.class, this);

  public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(LaborbereichCluster.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

  public SelectAqlField<NullFlavour> STANDORTTYP_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbereichCluster.class, "/items[at0040]/null_flavour|defining_code", "standorttypNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> LABORBEREICH_VALUE = new AqlFieldImp<String>(LaborbereichCluster.class, "/items[at0046]/value|value", "laborbereichValue", String.class, this);

  public SelectAqlField<NullFlavour> LABORBEREICH_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbereichCluster.class, "/items[at0046]/null_flavour|defining_code", "laborbereichNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<String> KODE_DES_LABORBEREICHS_VALUE = new AqlFieldImp<String>(LaborbereichCluster.class, "/items[at0048]/value|value", "kodeDesLaborbereichsValue", String.class, this);

  public SelectAqlField<NullFlavour> KODE_DES_LABORBEREICHS_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(LaborbereichCluster.class, "/items[at0048]/null_flavour|defining_code", "kodeDesLaborbereichsNullFlavourDefiningCode", NullFlavour.class, this);

  public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(LaborbereichCluster.class, "/items[at0047]", "details", Cluster.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(LaborbereichCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private LaborbereichClusterContainment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static LaborbereichClusterContainment getInstance() {
    return new LaborbereichClusterContainment();
  }
}
