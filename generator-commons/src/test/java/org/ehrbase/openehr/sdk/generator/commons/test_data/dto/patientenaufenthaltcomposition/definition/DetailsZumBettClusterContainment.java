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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.patientenaufenthaltcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvIdentifier;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class DetailsZumBettClusterContainment extends Containment {
    public SelectAqlField<DetailsZumBettCluster> DETAILS_ZUM_BETT_CLUSTER = new AqlFieldImp<DetailsZumBettCluster>(
            DetailsZumBettCluster.class, "", "DetailsZumBettCluster", DetailsZumBettCluster.class, this);

    public SelectAqlField<String> GERATENAME_VALUE = new AqlFieldImp<String>(
            DetailsZumBettCluster.class, "/items[at0001]/value|value", "geratenameValue", String.class, this);

    public SelectAqlField<String> GERATETYP_VALUE = new AqlFieldImp<String>(
            DetailsZumBettCluster.class, "/items[at0003]/value|value", "geratetypValue", String.class, this);

    public ListSelectAqlField<Cluster> EIGENSCHAFTEN = new ListAqlFieldImp<Cluster>(
            DetailsZumBettCluster.class, "/items[at0009]", "eigenschaften", Cluster.class, this);

    public SelectAqlField<DvIdentifier> EINDEUTIGE_IDENTIFIKATIONSNUMMER_ID = new AqlFieldImp<DvIdentifier>(
            DetailsZumBettCluster.class,
            "/items[at0021]/value",
            "eindeutigeIdentifikationsnummerId",
            DvIdentifier.class,
            this);

    public ListSelectAqlField<Cluster> GERATEVERWALTUNG = new ListAqlFieldImp<Cluster>(
            DetailsZumBettCluster.class, "/items[at0019]", "gerateverwaltung", Cluster.class, this);

    public ListSelectAqlField<Cluster> KOMPONENTEN = new ListAqlFieldImp<Cluster>(
            DetailsZumBettCluster.class, "/items[at0018]", "komponenten", Cluster.class, this);

    public ListSelectAqlField<Cluster> ERWEITERUNG = new ListAqlFieldImp<Cluster>(
            DetailsZumBettCluster.class, "/items[at0026]", "erweiterung", Cluster.class, this);

    public ListSelectAqlField<Cluster> MULTIMEDIA = new ListAqlFieldImp<Cluster>(
            DetailsZumBettCluster.class, "/items[at0027]", "multimedia", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            DetailsZumBettCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private DetailsZumBettClusterContainment() {
        super("openEHR-EHR-CLUSTER.device.v1");
    }

    public static DetailsZumBettClusterContainment getInstance() {
        return new DetailsZumBettClusterContainment();
    }
}
