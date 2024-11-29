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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class KulturClusterContainment extends Containment {
    public SelectAqlField<KulturCluster> KULTUR_CLUSTER =
            new AqlFieldImp<KulturCluster>(KulturCluster.class, "", "KulturCluster", KulturCluster.class, this);

    public ListSelectAqlField<ProVirusCluster> PRO_VIRUS = new ListAqlFieldImp<ProVirusCluster>(
            KulturCluster.class,
            "/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
            "proVirus",
            ProVirusCluster.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT =
            new AqlFieldImp<FeederAudit>(KulturCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private KulturClusterContainment() {
        super("openEHR-EHR-CLUSTER.laboratory_test_panel.v0");
    }

    public static KulturClusterContainment getInstance() {
        return new KulturClusterContainment();
    }
}
