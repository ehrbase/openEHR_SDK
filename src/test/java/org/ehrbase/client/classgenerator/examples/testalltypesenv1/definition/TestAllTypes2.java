package org.ehrbase.client.classgenerator.examples.testalltypesenv1.definition;

import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

import java.util.List;

@Entity
@Archetype("openEHR-EHR-SECTION.test_all_types.v1")
public class TestAllTypes2 {
    @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-ACTION.test_all_types.v1]")
    private List<TestAllTypes3> testAllTypes;

    @Path("/items[at0001]/items[at0002]/items[openEHR-EHR-INSTRUCTION.test_all_types.v1]")
    private List<TestAllTypes5> testalltypesItemsOpenehrEhrInstructionTestAllTypesV1;

    @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
    private List<TestAllTypes6> testalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1;

    public void setTestAllTypes(List<TestAllTypes3> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes3> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setTestalltypesItemsOpenehrEhrInstructionTestAllTypesV1(
            List<TestAllTypes5> testalltypesItemsOpenehrEhrInstructionTestAllTypesV1) {
        this.testalltypesItemsOpenehrEhrInstructionTestAllTypesV1 = testalltypesItemsOpenehrEhrInstructionTestAllTypesV1;
    }

    public List<TestAllTypes5> getTestalltypesItemsOpenehrEhrInstructionTestAllTypesV1() {
        return this.testalltypesItemsOpenehrEhrInstructionTestAllTypesV1;
    }

    public void setTestalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1(
            List<TestAllTypes6> testalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1) {
        this.testalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1 = testalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1;
    }

    public List<TestAllTypes6> getTestalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1() {
        return this.testalltypesItemsOpenehrEhrAdminEntryTestAllTypesV1;
    }
}
