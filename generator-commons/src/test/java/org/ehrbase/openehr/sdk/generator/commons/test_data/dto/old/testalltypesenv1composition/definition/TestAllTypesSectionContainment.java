/*
 * Copyright (c) 2022 vitasystems GmbH.
 *
 * This file is part of project openEHR_SDK
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.testalltypesenv1composition.definition;

import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class TestAllTypesSectionContainment extends Containment {
    public SelectAqlField<TestAllTypesSection> TEST_ALL_TYPES_SECTION = new AqlFieldImp<TestAllTypesSection>(
            TestAllTypesSection.class, "", "TestAllTypesSection", TestAllTypesSection.class, this);

    public ListSelectAqlField<TestAllTypesAction> TEST_ALL_TYPES = new ListAqlFieldImp<TestAllTypesAction>(
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
