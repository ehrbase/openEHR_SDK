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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
public class BeschaftigungCluster {
    @Path("/items[at0005]/value|value")
    private String berufsbezeichnungRolleValue;

    @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
    private List<OrganisationCluster> organisation;

    @Path("/items[at0018]")
    private List<Cluster> zusatzlicheAngaben;

    public void setBerufsbezeichnungRolleValue(String berufsbezeichnungRolleValue) {
        this.berufsbezeichnungRolleValue = berufsbezeichnungRolleValue;
    }

    public String getBerufsbezeichnungRolleValue() {
        return this.berufsbezeichnungRolleValue;
    }

    public void setOrganisation(List<OrganisationCluster> organisation) {
        this.organisation = organisation;
    }

    public List<OrganisationCluster> getOrganisation() {
        return this.organisation;
    }

    public void setZusatzlicheAngaben(List<Cluster> zusatzlicheAngaben) {
        this.zusatzlicheAngaben = zusatzlicheAngaben;
    }

    public List<Cluster> getZusatzlicheAngaben() {
        return this.zusatzlicheAngaben;
    }
}
