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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class StatusClusterContainment extends Containment {
    public SelectAqlField<StatusCluster> STATUS_CLUSTER =
            new AqlFieldImp<StatusCluster>(StatusCluster.class, "", "StatusCluster", StatusCluster.class, this);

    public SelectAqlField<DiagnosestatusDefiningCode> DIAGNOSESTATUS_DEFINING_CODE =
            new AqlFieldImp<DiagnosestatusDefiningCode>(
                    StatusCluster.class,
                    "/items[at0004]/value|defining_code",
                    "diagnosestatusDefiningCode",
                    DiagnosestatusDefiningCode.class,
                    this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(StatusCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private StatusClusterContainment() {
        super("openEHR-EHR-CLUSTER.problem_qualifier.v1");
    }

    public static StatusClusterContainment getInstance() {
        return new StatusClusterContainment();
    }
}
