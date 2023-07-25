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

import com.nedap.archie.rm.generic.PartyProxy;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.shareddefinition.Language;

public class TestAllTypesAdminEntryContainment extends Containment {
    public SelectAqlField<TestAllTypesAdminEntry> TEST_ALL_TYPES_ADMIN_ENTRY = new AqlFieldImp<TestAllTypesAdminEntry>(
            TestAllTypesAdminEntry.class, "", "TestAllTypesAdminEntry", TestAllTypesAdminEntry.class, this);

    public SelectAqlField<PartyProxy> SUBJECT =
            new AqlFieldImp<PartyProxy>(TestAllTypesAdminEntry.class, "/subject", "subject", PartyProxy.class, this);

    public SelectAqlField<Language> LANGUAGE =
            new AqlFieldImp<Language>(TestAllTypesAdminEntry.class, "/language", "language", Language.class, this);

    public SelectAqlField<Long> COUNT3_MAGNITUDE = new AqlFieldImp<Long>(
            TestAllTypesAdminEntry.class,
            "/data[at0001]/item[at0002]/value|magnitude",
            "count3Magnitude",
            Long.class,
            this);

    private TestAllTypesAdminEntryContainment() {
        super("openEHR-EHR-ADMIN_ENTRY.test_all_types.v1");
    }

    public static TestAllTypesAdminEntryContainment getInstance() {
        return new TestAllTypesAdminEntryContainment();
    }
}
