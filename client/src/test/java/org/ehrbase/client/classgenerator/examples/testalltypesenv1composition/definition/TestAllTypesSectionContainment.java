package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class TestAllTypesSectionContainment extends Containment {
  public SelectAqlField<TestAllTypesSection> TEST_ALL_TYPES_SECTION =
      new AqlFieldImp<TestAllTypesSection>(
          TestAllTypesSection.class, "", "TestAllTypesSection", TestAllTypesSection.class, this);

  public ListSelectAqlField<TestAllTypesInstruction> SECTION3_TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesInstruction>(
          TestAllTypesSection.class,
          "/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]",
          "section3TestAllTypes",
          TestAllTypesInstruction.class,
          this);

  public ListSelectAqlField<TestAllTypesAction> SECTION3_TEST_ALL_TYPES2 =
      new ListAqlFieldImp<TestAllTypesAction>(
          TestAllTypesSection.class,
          "/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]",
          "section3TestAllTypes2",
          TestAllTypesAction.class,
          this);

  public ListSelectAqlField<TestAllTypesAdminEntry> SECTION2_TEST_ALL_TYPES =
      new ListAqlFieldImp<TestAllTypesAdminEntry>(
          TestAllTypesSection.class,
          "/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]",
          "section2TestAllTypes",
          TestAllTypesAdminEntry.class,
          this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT =
      new AqlFieldImp<FeederAudit>(
          TestAllTypesSection.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  private TestAllTypesSectionContainment() {
    super("openEHR-EHR-SECTION.test_all_types.v1");
  }

  public static TestAllTypesSectionContainment getInstance() {
    return new TestAllTypesSectionContainment();
  }
}
