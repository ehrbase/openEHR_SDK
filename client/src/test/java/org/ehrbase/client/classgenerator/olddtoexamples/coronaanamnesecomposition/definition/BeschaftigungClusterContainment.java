package org.ehrbase.client.classgenerator.olddtoexamples.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class BeschaftigungClusterContainment extends Containment {
  public SelectAqlField<BeschaftigungCluster> BESCHAFTIGUNG_CLUSTER =
      new AqlFieldImp<BeschaftigungCluster>(
          BeschaftigungCluster.class, "", "BeschaftigungCluster", BeschaftigungCluster.class, this);

  public SelectAqlField<String> BERUFSBEZEICHNUNG_ROLLE_VALUE =
      new AqlFieldImp<String>(
          BeschaftigungCluster.class,
          "/items[at0005]/value|value",
          "berufsbezeichnungRolleValue",
          String.class,
          this);

  public ListSelectAqlField<OrganisationCluster> ORGANISATION =
      new ListAqlFieldImp<OrganisationCluster>(
          BeschaftigungCluster.class,
          "/items[openEHR-EHR-CLUSTER.organisation_cc.v0]",
          "organisation",
          OrganisationCluster.class,
          this);

  public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN =
      new ListAqlFieldImp<Cluster>(
          BeschaftigungCluster.class, "/items[at0018]", "zusatzlicheAngaben", Cluster.class, this);

  private BeschaftigungClusterContainment() {
    super("openEHR-EHR-CLUSTER.occupation_record.v1");
  }

  public static BeschaftigungClusterContainment getInstance() {
    return new BeschaftigungClusterContainment();
  }
}
