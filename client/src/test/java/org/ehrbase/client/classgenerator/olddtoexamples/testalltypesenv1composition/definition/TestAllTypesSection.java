package org.ehrbase.client.classgenerator.olddtoexamples.testalltypesenv1composition.definition;

import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
@Archetype("openEHR-EHR-SECTION.test_all_types.v1")
public class TestAllTypesSection {
  @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]")
  private List<TestAllTypesAction> testAllTypes;

  @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]")
  private List<TestAllTypesInstruction> testAllTypesSection3;

  @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
  private List<TestAllTypesAdminEntry> testAllTypesSection2;

  public void setTestAllTypes(List<TestAllTypesAction> testAllTypes) {
    this.testAllTypes = testAllTypes;
  }

  public List<TestAllTypesAction> getTestAllTypes() {
    return this.testAllTypes;
  }

  public void setTestAllTypesSection3(List<TestAllTypesInstruction> testAllTypesSection3) {
    this.testAllTypesSection3 = testAllTypesSection3;
  }

  public List<TestAllTypesInstruction> getTestAllTypesSection3() {
    return this.testAllTypesSection3;
  }

  public void setTestAllTypesSection2(List<TestAllTypesAdminEntry> testAllTypesSection2) {
    this.testAllTypesSection2 = testAllTypesSection2;
  }

  public List<TestAllTypesAdminEntry> getTestAllTypesSection2() {
    return this.testAllTypesSection2;
  }
}
