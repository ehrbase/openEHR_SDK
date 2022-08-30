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
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class StandortClusterContainment extends Containment {
    public SelectAqlField<StandortCluster> STANDORT_CLUSTER =
            new AqlFieldImp<StandortCluster>(StandortCluster.class, "", "StandortCluster", StandortCluster.class, this);

    public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

    public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0046]/value|value", "standortbeschreibungValue", String.class, this);

    public SelectAqlField<String> STANDORTSCHLUSSEL_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0048]/value|value", "standortschlusselValue", String.class, this);

    public SelectAqlField<String> STATION_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0027]/value|value", "stationValue", String.class, this);

    public SelectAqlField<String> ZIMMER_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0029]/value|value", "zimmerValue", String.class, this);

    public ListSelectAqlField<Cluster> DETAILS =
            new ListAqlFieldImp<Cluster>(StandortCluster.class, "/items[at0047]", "details", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            StandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private StandortClusterContainment() {
        super("openEHR-EHR-CLUSTER.location.v1");
    }

    public static StandortClusterContainment getInstance() {
        return new StandortClusterContainment();
    }
}
