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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingalls.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class OrganisationClusterContainment extends Containment {
    public SelectAqlField<OrganisationCluster> ORGANISATION_CLUSTER = new AqlFieldImp<OrganisationCluster>(
            OrganisationCluster.class, "", "OrganisationCluster", OrganisationCluster.class, this);

    public ListSelectAqlField<Cluster> IDENTIFIER = new ListAqlFieldImp<Cluster>(
            OrganisationCluster.class, "/items[at0018]", "identifier", Cluster.class, this);

    public SelectAqlField<String> NAME_DER_EINRICHTUNG_VALUE = new AqlFieldImp<String>(
            OrganisationCluster.class, "/items[at0012]/value|value", "nameDerEinrichtungValue", String.class, this);

    public ListSelectAqlField<Cluster> TELEFON =
            new ListAqlFieldImp<Cluster>(OrganisationCluster.class, "/items[at0014]", "telefon", Cluster.class, this);

    public ListSelectAqlField<AdresseCluster> ADRESSE = new ListAqlFieldImp<AdresseCluster>(
            OrganisationCluster.class,
            "/items[openEHR-EHR-CLUSTER.address_cc.v0]",
            "adresse",
            AdresseCluster.class,
            this);

    public ListSelectAqlField<Cluster> TEIL_VON =
            new ListAqlFieldImp<Cluster>(OrganisationCluster.class, "/items[at0017]", "teilVon", Cluster.class, this);

    public ListSelectAqlField<Cluster> KONTAKT =
            new ListAqlFieldImp<Cluster>(OrganisationCluster.class, "/items[at0016]", "kontakt", Cluster.class, this);

    public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(
            OrganisationCluster.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

    private OrganisationClusterContainment() {
        super("openEHR-EHR-CLUSTER.organisation_cc.v0");
    }

    public static OrganisationClusterContainment getInstance() {
        return new OrganisationClusterContainment();
    }
}
