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
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class StandortClusterContainment extends Containment {
    public SelectAqlField<StandortCluster> STANDORT_CLUSTER =
            new AqlFieldImp<StandortCluster>(StandortCluster.class, "", "StandortCluster", StandortCluster.class, this);

    public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

    public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0046]/value|value", "standortbeschreibungValue", String.class, this);

    public SelectAqlField<StandortschlusselDefiningCode> STANDORTSCHLUSSEL_DEFINING_CODE =
            new AqlFieldImp<StandortschlusselDefiningCode>(
                    StandortCluster.class,
                    "/items[at0048]/value|defining_code",
                    "standortschlusselDefiningCode",
                    StandortschlusselDefiningCode.class,
                    this);

    public SelectAqlField<String> CAMPUS_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0024]/value|value", "campusValue", String.class, this);

    public SelectAqlField<String> GEBAUDEGRUPPE_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0025]/value|value", "gebaudegruppeValue", String.class, this);

    public SelectAqlField<String> EBENE_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0028]/value|value", "ebeneValue", String.class, this);

    public SelectAqlField<String> STATIONSKENNUNG_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0027]/value|value", "stationskennungValue", String.class, this);

    public SelectAqlField<String> ZIMMERKENNUNG_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0029]/value|value", "zimmerkennungValue", String.class, this);

    public SelectAqlField<String> BETTPLATZKENNUNG_VALUE = new AqlFieldImp<String>(
            StandortCluster.class, "/items[at0034]/value|value", "bettplatzkennungValue", String.class, this);

    public SelectAqlField<DetailsZumBettCluster> DETAILS_ZUM_BETT = new AqlFieldImp<DetailsZumBettCluster>(
            StandortCluster.class,
            "/items[openEHR-EHR-CLUSTER.device.v1]",
            "detailsZumBett",
            DetailsZumBettCluster.class,
            this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            StandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private StandortClusterContainment() {
        super("openEHR-EHR-CLUSTER.location.v1");
    }

    public static StandortClusterContainment getInstance() {
        return new StandortClusterContainment();
    }
}
