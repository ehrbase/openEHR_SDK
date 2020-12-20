package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class TestAllTypesSectionContainment extends Containment {
  public SelectAqlField<TestAllTypesSection> TEST_ALL_TYPES_SECTION =
      new AqlFieldImp<TestAllTypesSection>(
          TestAllTypesSection.class, "", "TestAllTypesSection", TestAllTypesSection.class, this);

  public ListSelectAqlField<TestAllTypesAction> TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesAction>(
          TestAllTypesSection.class,
          "/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]",
          "testAllTypes",
          TestAllTypesAction.class,
          this);

  public ListSelectAqlField<TestAllTypesInstruction> TEST_ALL_TYPES_SECTION3 =
      new ListAqlFieldImp<TestAllTypesInstruction>(
          TestAllTypesSection.class,
          "/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]",
          "testAllTypesSection3",
          TestAllTypesInstruction.class,
          this);

  public ListSelectAqlField<TestAllTypesAdminEntry> TEST_ALL_TYPES_SECTION2 =
      new ListAqlFieldImp<TestAllTypesAdminEntry>(
          TestAllTypesSection.class,
          "/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]",
          "testAllTypesSection2",
          TestAllTypesAdminEntry.class,
          this);

  private TestAllTypesSectionContainment() {
    super("openEHR-EHR-SECTION.test_all_types.v1");
  }

  public static TestAllTypesSectionContainment getInstance() {
    return new TestAllTypesSectionContainment();
  }
}
