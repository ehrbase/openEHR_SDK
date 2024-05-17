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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class AbteilungsfallClusterContainment extends Containment {
    public SelectAqlField<AbteilungsfallCluster> ABTEILUNGSFALL_CLUSTER = new AqlFieldImp<AbteilungsfallCluster>(
            AbteilungsfallCluster.class, "", "AbteilungsfallCluster", AbteilungsfallCluster.class, this);

    public SelectAqlField<String> ZUGEHORIGE_ABTEILUNGSFALL_KENNUNG_VALUE = new AqlFieldImp<String>(
            AbteilungsfallCluster.class,
            "/items[at0001]/value|value",
            "zugehorigeAbteilungsfallKennungValue",
            String.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AbteilungsfallCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AbteilungsfallClusterContainment() {
        super("openEHR-EHR-CLUSTER.case_identification.v0");
    }

    public static AbteilungsfallClusterContainment getInstance() {
        return new AbteilungsfallClusterContainment();
    }
}
