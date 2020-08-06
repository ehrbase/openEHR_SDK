package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-SECTION.test_all_types.v1")
public class TestAllTypesSection {
    @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]")
    private List<TestAllTypesAction> testAllTypes;

    @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]")
    private List<TestAllTypesInstruction> testAllTypesItemsOpenehrEhrInstructionTestAllTypesV1;

    @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
    private List<TestAllTypesAdminEntry> testAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1;

    public void setTestAllTypes(List<TestAllTypesAction> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypesAction> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setTestAllTypesItemsOpenehrEhrInstructionTestAllTypesV1(
            List<TestAllTypesInstruction> testAllTypesItemsOpenehrEhrInstructionTestAllTypesV1) {
        this.testAllTypesItemsOpenehrEhrInstructionTestAllTypesV1 = testAllTypesItemsOpenehrEhrInstructionTestAllTypesV1;
    }

    public List<TestAllTypesInstruction> getTestAllTypesItemsOpenehrEhrInstructionTestAllTypesV1() {
        return this.testAllTypesItemsOpenehrEhrInstructionTestAllTypesV1;
    }

    public void setTestAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1(
            List<TestAllTypesAdminEntry> testAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1) {
        this.testAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1 = testAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1;
    }

    public List<TestAllTypesAdminEntry> getTestAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1() {
        return this.testAllTypesItemsOpenehrEhrAdminEntryTestAllTypesV1;
    }
}
