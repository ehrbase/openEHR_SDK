package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class StandortCluster2Containment extends Containment {
  public SelectAqlField<StandortCluster2> STANDORT_CLUSTER2 = new AqlFieldImp<StandortCluster2>(StandortCluster2.class, "", "StandortCluster2", StandortCluster2.class, this);

  public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(StandortCluster2.class, "/items[at0047]", "details", Cluster.class, this);

  public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(StandortCluster2.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

  public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(StandortCluster2.class, "/items[at0046]/value|value", "standortbeschreibungValue", String.class, this);

  public SelectAqlField<String> STANDORTSCHLUSSEL_VALUE = new AqlFieldImp<String>(StandortCluster2.class, "/items[at0048]/value|value", "standortschlusselValue", String.class, this);

  private StandortCluster2Containment() {
    super("openEHR-EHR-CLUSTER.location.v1");
  }

  public static StandortCluster2Containment getInstance() {
    return new StandortCluster2Containment();
  }
}
