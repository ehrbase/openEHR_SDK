/*
 * Copyright (c) 2024 Vitasystems GmbH and Hannover Medical School.
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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class WohnstaetteClusterContainment extends Containment {
    public SelectAqlField<WohnstaetteCluster> WOHNSTAETTE_CLUSTER = new AqlFieldImp<WohnstaetteCluster>(
            WohnstaetteCluster.class, "", "WohnstaetteCluster", WohnstaetteCluster.class, this);

    public SelectAqlField<Long> ANZAHL_DER_SCHLAFZIMMER_MAGNITUDE = new AqlFieldImp<Long>(
            WohnstaetteCluster.class,
            "/items[at0028]/value|magnitude",
            "anzahlDerSchlafzimmerMagnitude",
            Long.class,
            this);

    public ListSelectAqlField<Cluster> ERGAENZENDE_DETAILS = new ListAqlFieldImp<Cluster>(
            WohnstaetteCluster.class, "/items[at0003]", "ergaenzendeDetails", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            WohnstaetteCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private WohnstaetteClusterContainment() {
        super("openEHR-EHR-CLUSTER.dwelling.v0");
    }

    public static WohnstaetteClusterContainment getInstance() {
        return new WohnstaetteClusterContainment();
    }
}
