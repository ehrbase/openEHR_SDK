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
    private List<TestAllTypes5> testAllTypes2;

    @Path("/items[at0001]/items[openEHR-EHR-ADMIN_ENTRY.test_all_types.v1]")
    private List<TestAllTypes6> testAllTypes3;

    public void setTestAllTypes(List<TestAllTypes3> testAllTypes) {
        this.testAllTypes = testAllTypes;
    }

    public List<TestAllTypes3> getTestAllTypes() {
        return this.testAllTypes;
    }

    public void setTestAllTypes2(List<TestAllTypes5> testAllTypes2) {
        this.testAllTypes2 = testAllTypes2;
    }

    public List<TestAllTypes5> getTestAllTypes2() {
        return this.testAllTypes2;
    }

    public void setTestAllTypes3(List<TestAllTypes6> testAllTypes3) {
        this.testAllTypes3 = testAllTypes3;
    }

    public List<TestAllTypes6> getTestAllTypes3() {
        return this.testAllTypes3;
    }
}
