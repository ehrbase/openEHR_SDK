package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class NeuroSymptomClusterContainment extends Containment {
  public SelectAqlField<NeuroSymptomCluster> NEURO_SYMPTOM_CLUSTER = new AqlFieldImp<NeuroSymptomCluster>(NeuroSymptomCluster.class, "", "NeuroSymptomCluster", NeuroSymptomCluster.class, this);

    public ListSelectAqlField<Cluster> SIGN = new ListAqlFieldImp<Cluster>(NeuroSymptomCluster.class, "/items[at0063]", "sign", Cluster.class, this);

    public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(NeuroSymptomCluster.class, "/items[at0153]", "specificDetails", Cluster.class, this);

    public SelectAqlField<SymptomSignNameDefiningcode> SYMPTOM_SIGN_NAME_DEFININGCODE = new AqlFieldImp<SymptomSignNameDefiningcode>(NeuroSymptomCluster.class, "/items[at0001.1]/value|defining_code", "symptomSignNameDefiningcode", SymptomSignNameDefiningcode.class, this);

    public ListSelectAqlField<Cluster> PREVIOUS_EPISODES = new ListAqlFieldImp<Cluster>(NeuroSymptomCluster.class, "/items[at0146]", "previousEpisodes", Cluster.class, this);

    public SelectAqlField<PresenceDefiningcode> PRESENCE_DEFININGCODE = new AqlFieldImp<PresenceDefiningcode>(NeuroSymptomCluster.class, "/items[at0.1]/value|defining_code", "presenceDefiningcode", PresenceDefiningcode.class, this);

    public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(NeuroSymptomCluster.class, "/items[at0147]", "structuredBodySite", Cluster.class, this);

    private NeuroSymptomClusterContainment() {
        super("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0");
    }

    public static NeuroSymptomClusterContainment getInstance() {
        return new NeuroSymptomClusterContainment();
    }
}
