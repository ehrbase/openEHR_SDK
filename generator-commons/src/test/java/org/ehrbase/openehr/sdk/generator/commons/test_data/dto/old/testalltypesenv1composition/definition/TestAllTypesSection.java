/*
 * Copyright (c) 2022 vitasystems GmbH and Hannover Medical School.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.definition;

import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

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
