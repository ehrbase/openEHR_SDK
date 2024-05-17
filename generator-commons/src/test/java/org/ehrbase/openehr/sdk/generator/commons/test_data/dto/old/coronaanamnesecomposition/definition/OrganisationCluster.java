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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.old.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;

@Entity
@Archetype("openEHR-EHR-CLUSTER.organisation_cc.v0")
public class OrganisationCluster {
    @Path("/items[at0014]")
    private List<Cluster> telefon;

    @Path("/items[at0012]/value|value")
    private String nameDerEinrichtungValue;

    @Path("/items[at0017]")
    private List<Cluster> teilVon;

    @Path("/items[at0016]")
    private List<Cluster> kontakt;

    @Path("/items[openEHR-EHR-CLUSTER.address_cc.v0]")
    private List<AdresseCluster> adresse;

    @Path("/items[at0018]")
    private List<Cluster> identifier;

    public void setTelefon(List<Cluster> telefon) {
        this.telefon = telefon;
    }

    public List<Cluster> getTelefon() {
        return this.telefon;
    }

    public void setNameDerEinrichtungValue(String nameDerEinrichtungValue) {
        this.nameDerEinrichtungValue = nameDerEinrichtungValue;
    }

    public String getNameDerEinrichtungValue() {
        return this.nameDerEinrichtungValue;
    }

    public void setTeilVon(List<Cluster> teilVon) {
        this.teilVon = teilVon;
    }

    public List<Cluster> getTeilVon() {
        return this.teilVon;
    }

    public void setKontakt(List<Cluster> kontakt) {
        this.kontakt = kontakt;
    }

    public List<Cluster> getKontakt() {
        return this.kontakt;
    }

    public void setAdresse(List<AdresseCluster> adresse) {
        this.adresse = adresse;
    }

    public List<AdresseCluster> getAdresse() {
        return this.adresse;
    }

    public void setIdentifier(List<Cluster> identifier) {
        this.identifier = identifier;
    }

    public List<Cluster> getIdentifier() {
        return this.identifier;
    }
}
