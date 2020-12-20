package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class TestAllTypesClusterContainment extends Containment {
  public SelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES_CLUSTER =
      new AqlFieldImp<TestAllTypesCluster>(
          TestAllTypesCluster.class, "", "TestAllTypesCluster", TestAllTypesCluster.class, this);

  public SelectAqlField<Boolean> BOOLEAN2_VALUE =
      new AqlFieldImp<Boolean>(
          TestAllTypesCluster.class,
          "/items[at0001]/items[at0002]/items[at0003]/value|value",
          "boolean2Value",
          Boolean.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TestAllTypesCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private TestAllTypesClusterContainment() {
    super("openEHR-EHR-CLUSTER.test_all_types.v1");
  }

  public static TestAllTypesClusterContainment getInstance() {
    return new TestAllTypesClusterContainment();
  }
}
