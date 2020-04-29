package org.ehrbase.client.classgenerator.examples.openehrsuspectedcovid19riskassessmentv0composition.definition;

import java.lang.Long;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class DwellingClusterContainment extends Containment {
  public SelectAqlField<DwellingCluster> DWELLING_CLUSTER = new AqlFieldImp<DwellingCluster>(DwellingCluster.class, "", "DwellingCluster", DwellingCluster.class, this);

  public SelectAqlField<Long> NUMBER_OF_BEDROOMS_MAGNITUDE = new AqlFieldImp<Long>(DwellingCluster.class, "/items[at0028]/value|magnitude", "numberOfBedroomsMagnitude", Long.class, this);

  public ListSelectAqlField<OvercrowdingScreeningCluster> OVERCROWDING_SCREENING = new ListAqlFieldImp<OvercrowdingScreeningCluster>(DwellingCluster.class, "/items[openEHR-EHR-CLUSTER.overcrowding_screening.v0]", "overcrowdingScreening", OvercrowdingScreeningCluster.class, this);

  private DwellingClusterContainment() {
    super("openEHR-EHR-CLUSTER.dwelling.v0");
  }

  public static DwellingClusterContainment getInstance() {
    return new DwellingClusterContainment();
  }
}
