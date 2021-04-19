package org.ehrbase.client.classgenerator.examples.laborbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.String;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

public class FallidentifikationClusterContainment extends Containment {
  public SelectAqlField<FallidentifikationCluster> FALLIDENTIFIKATION_CLUSTER = new AqlFieldImp<FallidentifikationCluster>(FallidentifikationCluster.class, "", "FallidentifikationCluster", FallidentifikationCluster.class, this);

  public SelectAqlField<String> FALL_KENNUNG_VALUE = new AqlFieldImp<String>(FallidentifikationCluster.class, "/items[at0001]/value|value", "fallKennungValue", String.class, this);

  public SelectAqlField<NullFlavour> FALL_KENNUNG_NULL_FLAVOUR_DEFINING_CODE = new AqlFieldImp<NullFlavour>(FallidentifikationCluster.class, "/items[at0001]/null_flavour|defining_code", "fallKennungNullFlavourDefiningCode", NullFlavour.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(FallidentifikationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private FallidentifikationClusterContainment() {
    super("openEHR-EHR-CLUSTER.case_identification.v0");
  }

  public static FallidentifikationClusterContainment getInstance() {
    return new FallidentifikationClusterContainment();
  }
}
