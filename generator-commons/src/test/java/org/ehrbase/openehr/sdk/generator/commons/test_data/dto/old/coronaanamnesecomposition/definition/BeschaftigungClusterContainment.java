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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import org.ehrbase.openehr.sdk.generator.commons.aql.containment.Containment;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.AqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListAqlFieldImp;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.ListSelectAqlField;
import org.ehrbase.openehr.sdk.generator.commons.aql.field.SelectAqlField;

public class BeschaftigungClusterContainment extends Containment {
    public SelectAqlField<BeschaftigungCluster> BESCHAFTIGUNG_CLUSTER = new AqlFieldImp<BeschaftigungCluster>(
            BeschaftigungCluster.class, "", "BeschaftigungCluster", BeschaftigungCluster.class, this);

    public SelectAqlField<String> BERUFSBEZEICHNUNG_ROLLE_VALUE = new AqlFieldImp<String>(
            BeschaftigungCluster.class,
            "/items[at0005]/value|value",
            "berufsbezeichnungRolleValue",
            String.class,
            this);

    public ListSelectAqlField<OrganisationCluster> ORGANISATION = new ListAqlFieldImp<OrganisationCluster>(
            BeschaftigungCluster.class,
            "/items[openEHR-EHR-CLUSTER.organisation_cc.v0]",
            "organisation",
            OrganisationCluster.class,
            this);

    public ListSelectAqlField<Cluster> ZUSATZLICHE_ANGABEN = new ListAqlFieldImp<Cluster>(
            BeschaftigungCluster.class, "/items[at0018]", "zusatzlicheAngaben", Cluster.class, this);

    private BeschaftigungClusterContainment() {
        super("openEHR-EHR-CLUSTER.occupation_record.v1");
    }

    public static BeschaftigungClusterContainment getInstance() {
        return new BeschaftigungClusterContainment();
    }
}
