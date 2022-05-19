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
package org.ehrbase.client.classgenerator.examples.testalltypesenv1composition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.SelectAqlField;

public class TestAllTypesClusterContainment extends Containment {
    public SelectAqlField<TestAllTypesCluster> TEST_ALL_TYPES_CLUSTER = new AqlFieldImp<TestAllTypesCluster>(
            TestAllTypesCluster.class, "", "TestAllTypesCluster", TestAllTypesCluster.class, this);

    public SelectAqlField<Boolean> BOOLEAN2_VALUE = new AqlFieldImp<Boolean>(
            TestAllTypesCluster.class,
            "/items[at0001]/items[at0002]/items[at0003]/value|value",
            "boolean2Value",
            Boolean.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            TestAllTypesCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private TestAllTypesClusterContainment() {
        super("openEHR-EHR-CLUSTER.test_all_types.v1");
    }

    public static TestAllTypesClusterContainment getInstance() {
        return new TestAllTypesClusterContainment();
    }
}
