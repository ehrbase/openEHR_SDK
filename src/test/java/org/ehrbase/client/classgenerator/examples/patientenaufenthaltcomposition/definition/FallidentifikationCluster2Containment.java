package org.ehrbase.client.classgenerator.examples.patientenaufenthaltcomposition.definition;

import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class FallidentifikationCluster2Containment extends Containment {
  public SelectAqlField<FallidentifikationCluster2> FALLIDENTIFIKATION_CLUSTER2 = new AqlFieldImp<FallidentifikationCluster2>(FallidentifikationCluster2.class, "", "FallidentifikationCluster2", FallidentifikationCluster2.class, this);

  public SelectAqlField<String> FALL_KENNUNG_VALUE = new AqlFieldImp<String>(FallidentifikationCluster2.class, "/items[at0001]/value|value", "fallKennungValue", String.class, this);

  public SelectAqlField<String> FALL_KENNUNG_VALUE_NAME = new AqlFieldImp<String>(FallidentifikationCluster2.class, "/items[at0001]/name|value", "fallKennungValueName", String.class, this);

  private FallidentifikationCluster2Containment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static FallidentifikationCluster2Containment getInstance() {
    return new FallidentifikationCluster2Containment();
  }
}
