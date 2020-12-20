package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class BeschaeftigungClusterContainment extends Containment {
  public SelectAqlField<BeschaeftigungCluster> BESCHAEFTIGUNG_CLUSTER =
      new AqlFieldImp<BeschaeftigungCluster>(
          BeschaeftigungCluster.class,
          "",
          "BeschaeftigungCluster",
          BeschaeftigungCluster.class,
          this);

  public SelectAqlField<String> BERUFSBEZEICHNUNG_ROLLE_VALUE =
      new AqlFieldImp<String>(
          BeschaeftigungCluster.class,
          "/items[at0005]/value|value",
          "berufsbezeichnungRolleValue",
          String.class,
          this);

  public ListSelectAqlField<OrganisationCluster> ORGANISATION =
      new ListAqlFieldImp<OrganisationCluster>(
          BeschaeftigungCluster.class,
          "/items[openEHR-EHR-CLUSTER.organisation_cc.v0]",
          "organisation",
          OrganisationCluster.class,
          this);

  public ListSelectAqlField<Cluster> ZUSAETZLICHE_ANGABEN =
      new ListAqlFieldImp<Cluster>(
          BeschaeftigungCluster.class,
          "/items[at0018]",
          "zusaetzlicheAngaben",
          Cluster.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          BeschaeftigungCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private BeschaeftigungClusterContainment() {
    super("openEHR-EHR-CLUSTER.occupation_record.v1");
  }

  public static BeschaeftigungClusterContainment getInstance() {
    return new BeschaeftigungClusterContainment();
  }
}
