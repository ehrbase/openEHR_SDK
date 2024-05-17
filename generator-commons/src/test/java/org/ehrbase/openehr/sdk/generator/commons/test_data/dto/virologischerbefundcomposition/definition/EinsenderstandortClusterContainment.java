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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class EinsenderstandortClusterContainment extends Containment {
    public SelectAqlField<EinsenderstandortCluster> EINSENDERSTANDORT_CLUSTER = new AqlFieldImp<
            EinsenderstandortCluster>(
            EinsenderstandortCluster.class, "", "EinsenderstandortCluster", EinsenderstandortCluster.class, this);

    public SelectAqlField<String> STANDORTTYP_VALUE = new AqlFieldImp<String>(
            EinsenderstandortCluster.class, "/items[at0040]/value|value", "standorttypValue", String.class, this);

    public SelectAqlField<String> STANDORTBESCHREIBUNG_VALUE = new AqlFieldImp<String>(
            EinsenderstandortCluster.class,
            "/items[at0046]/value|value",
            "standortbeschreibungValue",
            String.class,
            this);

    public SelectAqlField<String> STANDORTSCHLUSSEL_VALUE = new AqlFieldImp<String>(
            EinsenderstandortCluster.class, "/items[at0048]/value|value", "standortschlusselValue", String.class, this);

    public ListSelectAqlField<Cluster> DETAILS = new ListAqlFieldImp<Cluster>(
            EinsenderstandortCluster.class, "/items[at0047]", "details", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            EinsenderstandortCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private EinsenderstandortClusterContainment() {
        super("openEHR-EHR-CLUSTER.location.v1");
    }

    public static EinsenderstandortClusterContainment getInstance() {
        return new EinsenderstandortClusterContainment();
    }
}
