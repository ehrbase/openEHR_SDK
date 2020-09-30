package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class OtherSymptomClusterContainment extends Containment {
  public SelectAqlField<OtherSymptomCluster> OTHER_SYMPTOM_CLUSTER = new AqlFieldImp<OtherSymptomCluster>(OtherSymptomCluster.class, "", "OtherSymptomCluster", OtherSymptomCluster.class, this);

  public ListSelectAqlField<Cluster> SIGN = new ListAqlFieldImp<Cluster>(OtherSymptomCluster.class, "/items[at0063]", "sign", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(OtherSymptomCluster.class, "/items[at0153]", "specificDetails", Cluster.class, this);

  public SelectAqlField<String> SYMPTOM_SIGN_NAME_VALUE = new AqlFieldImp<String>(OtherSymptomCluster.class, "/items[at0001.1]/value|value", "symptomSignNameValue", String.class, this);

  public ListSelectAqlField<Cluster> PREVIOUS_EPISODES = new ListAqlFieldImp<Cluster>(OtherSymptomCluster.class, "/items[at0146]", "previousEpisodes", Cluster.class, this);

  public SelectAqlField<PresenceDefiningcode> PRESENCE_DEFININGCODE = new AqlFieldImp<PresenceDefiningcode>(OtherSymptomCluster.class, "/items[at0.1]/value|defining_code", "presenceDefiningcode", PresenceDefiningcode.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(OtherSymptomCluster.class, "/items[at0147]", "structuredBodySite", Cluster.class, this);

  private OtherSymptomClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0");
  }

  public static OtherSymptomClusterContainment getInstance() {
    return new OtherSymptomClusterContainment();
  }
}
