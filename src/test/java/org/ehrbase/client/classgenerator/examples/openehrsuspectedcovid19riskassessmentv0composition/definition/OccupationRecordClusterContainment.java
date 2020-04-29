package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class OccupationRecordClusterContainment extends Containment {
  public SelectAqlField<OccupationRecordCluster> OCCUPATION_RECORD_CLUSTER = new AqlFieldImp<OccupationRecordCluster>(OccupationRecordCluster.class, "", "OccupationRecordCluster", OccupationRecordCluster.class, this);

  public SelectAqlField<String> ROLE_VALUE = new AqlFieldImp<String>(OccupationRecordCluster.class, "/items[at0005]/value|value", "roleValue", String.class, this);

  public ListSelectAqlField<OrganisationCluster> ORGANISATION = new ListAqlFieldImp<OrganisationCluster>(OccupationRecordCluster.class, "/items[openEHR-EHR-CLUSTER.organisation_cc.v0]", "organisation", OrganisationCluster.class, this);

  public ListSelectAqlField<Cluster> ADDITIONAL_DETAILS = new ListAqlFieldImp<Cluster>(OccupationRecordCluster.class, "/items[at0018]", "additionalDetails", Cluster.class, this);

  private OccupationRecordClusterContainment() {
    super("openEHR-EHR-CLUSTER.occupation_record.v1");
  }

  public static OccupationRecordClusterContainment getInstance() {
    return new OccupationRecordClusterContainment();
  }
}
