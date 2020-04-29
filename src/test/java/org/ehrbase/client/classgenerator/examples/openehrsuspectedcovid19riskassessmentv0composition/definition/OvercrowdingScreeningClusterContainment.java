package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.Long;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class OvercrowdingScreeningClusterContainment extends Containment {
  public SelectAqlField<OvercrowdingScreeningCluster> OVERCROWDING_SCREENING_CLUSTER = new AqlFieldImp<OvercrowdingScreeningCluster>(OvercrowdingScreeningCluster.class, "", "OvercrowdingScreeningCluster", OvercrowdingScreeningCluster.class, this);

  public SelectAqlField<String> PERSONS_PER_BEDROOM_VALUE = new AqlFieldImp<String>(OvercrowdingScreeningCluster.class, "/items[at0002]/name|value", "personsPerBedroomValue", String.class, this);

  public SelectAqlField<Long> PERSONS_PER_BEDROOM_MAGNITUDE = new AqlFieldImp<Long>(OvercrowdingScreeningCluster.class, "/items[at0002]/value|magnitude", "personsPerBedroomMagnitude", Long.class, this);

  private OvercrowdingScreeningClusterContainment() {
    super("openEHR-EHR-CLUSTER.overcrowding_screening.v0");
  }

  public static OvercrowdingScreeningClusterContainment getInstance() {
    return new OvercrowdingScreeningClusterContainment();
  }
}
