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
package org.ehrbase.client.classgenerator.examples.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AntibiogrammClusterContainment extends Containment {
    public SelectAqlField<AntibiogrammCluster> ANTIBIOGRAMM_CLUSTER = new AqlFieldImp<AntibiogrammCluster>(
            AntibiogrammCluster.class, "", "AntibiogrammCluster", AntibiogrammCluster.class, this);

    public ListSelectAqlField<LaboranalytResultatCluster> LABORANALYT_RESULTAT =
            new ListAqlFieldImp<LaboranalytResultatCluster>(
                    AntibiogrammCluster.class,
                    "/items[openEHR-EHR-CLUSTER.laboratory_test_analyte.v1]",
                    "laboranalytResultat",
                    LaboranalytResultatCluster.class,
                    this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AntibiogrammCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AntibiogrammClusterContainment() {
        super("openEHR-EHR-CLUSTER.laboratory_test_panel.v0");
    }

    public static AntibiogrammClusterContainment getInstance() {
        return new AntibiogrammClusterContainment();
    }
}
