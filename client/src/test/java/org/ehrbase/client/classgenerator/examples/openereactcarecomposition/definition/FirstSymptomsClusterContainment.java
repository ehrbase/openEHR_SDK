package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.examples.shareddefinition.SymptomSignNameDefiningcode;

import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;

public class FirstSymptomsClusterContainment extends Containment {
  public SelectAqlField<FirstSymptomsCluster> FIRST_SYMPTOMS_CLUSTER = new AqlFieldImp<FirstSymptomsCluster>(FirstSymptomsCluster.class, "", "FirstSymptomsCluster", FirstSymptomsCluster.class, this);

  public SelectAqlField<TemporalAmount> DURATION_VALUE = new AqlFieldImp<TemporalAmount>(FirstSymptomsCluster.class, "/items[at0028]/value|value", "durationValue", TemporalAmount.class, this);

  public ListSelectAqlField<Cluster> SIGN = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0063]", "sign", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0153]", "specificDetails", Cluster.class, this);

  public SelectAqlField<SymptomSignNameDefiningcode> SYMPTOM_SIGN_NAME_DEFININGCODE = new AqlFieldImp<SymptomSignNameDefiningcode>(FirstSymptomsCluster.class, "/items[at0001.1]/value|defining_code", "symptomSignNameDefiningcode", SymptomSignNameDefiningcode.class, this);

  public SelectAqlField<TrendDefiningcode> TREND_DEFININGCODE = new AqlFieldImp<TrendDefiningcode>(FirstSymptomsCluster.class, "/items[at0180]/value|defining_code", "trendDefiningcode", TrendDefiningcode.class, this);

  public ListSelectAqlField<Cluster> PREVIOUS_EPISODES = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0146]", "previousEpisodes", Cluster.class, this);

  public SelectAqlField<PresenceDefiningcode> PRESENCE_DEFININGCODE = new AqlFieldImp<PresenceDefiningcode>(FirstSymptomsCluster.class, "/items[at0.1]/value|defining_code", "presenceDefiningcode", PresenceDefiningcode.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0147]", "structuredBodySite", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATE_OF_ONSET_OF_FIRST_SYMPTOMS_VALUE = new AqlFieldImp<TemporalAccessor>(FirstSymptomsCluster.class, "/items[at0152]/value|value", "dateOfOnsetOfFirstSymptomsValue", TemporalAccessor.class, this);

  public SelectAqlField<String> IMPACT_VALUE = new AqlFieldImp<String>(FirstSymptomsCluster.class, "/items[at0155]/value|value", "impactValue", String.class, this);

  private FirstSymptomsClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0");
  }

  public static FirstSymptomsClusterContainment getInstance() {
    return new FirstSymptomsClusterContainment();
  }
}
