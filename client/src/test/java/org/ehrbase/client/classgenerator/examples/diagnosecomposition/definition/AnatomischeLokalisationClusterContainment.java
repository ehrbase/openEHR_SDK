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
package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class AnatomischeLokalisationClusterContainment extends Containment {
    public SelectAqlField<AnatomischeLokalisationCluster> ANATOMISCHE_LOKALISATION_CLUSTER =
            new AqlFieldImp<AnatomischeLokalisationCluster>(
                    AnatomischeLokalisationCluster.class,
                    "",
                    "AnatomischeLokalisationCluster",
                    AnatomischeLokalisationCluster.class,
                    this);

    public SelectAqlField<String> NAME_DER_KORPERSTELLE_VALUE = new AqlFieldImp<String>(
            AnatomischeLokalisationCluster.class,
            "/items[at0001]/value|value",
            "nameDerKorperstelleValue",
            String.class,
            this);

    public SelectAqlField<LateralitatDefiningCode> LATERALITAT_DEFINING_CODE = new AqlFieldImp<LateralitatDefiningCode>(
            AnatomischeLokalisationCluster.class,
            "/items[at0002]/value|defining_code",
            "lateralitatDefiningCode",
            LateralitatDefiningCode.class,
            this);

    public ListSelectAqlField<Cluster> ALTERNATIVE_STRUKTUR = new ListAqlFieldImp<Cluster>(
            AnatomischeLokalisationCluster.class, "/items[at0053]", "alternativeStruktur", Cluster.class, this);

    public ListSelectAqlField<Cluster> MULTIMEDIALE_DARSTELLUNG = new ListAqlFieldImp<Cluster>(
            AnatomischeLokalisationCluster.class, "/items[at0054]", "multimedialeDarstellung", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            AnatomischeLokalisationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private AnatomischeLokalisationClusterContainment() {
        super("openEHR-EHR-CLUSTER.anatomical_location.v1");
    }

    public static AnatomischeLokalisationClusterContainment getInstance() {
        return new AnatomischeLokalisationClusterContainment();
    }
}
