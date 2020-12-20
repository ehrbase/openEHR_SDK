package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.datavalues.DataValue;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class TestAllTypesClusterContainment extends Containment {
  public SelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES_CLUSTER =
      new AqlFieldImp<TestAllTypesCluster>(
          TestAllTypesCluster.class, "", "TestAllTypesCluster", TestAllTypesCluster.class, this);

  public SelectAqlField<DataValue> VALUE =
      new AqlFieldImp<DataValue>(
          TestAllTypesCluster.class,
          "/items[at0001]/items[at0002]/items[at0003]/value",
          "value",
          DataValue.class,
          this);

  public SelectAqlField<Boolean> BOOLEAN2_VALUE =
      new AqlFieldImp<Boolean>(
          TestAllTypesCluster.class,
          "/items[at0001]/items[at0002]/items[at0003]/value\\n",
          "boolean2Value",
          Boolean.class,
          this);

  private TestAllTypesClusterContainment() {
    super("openEHR-EHR-CLUSTER.test_all_types.v1");
  }

  public static TestAllTypesClusterContainment getInstance() {
    return new TestAllTypesClusterContainment();
  }
}
