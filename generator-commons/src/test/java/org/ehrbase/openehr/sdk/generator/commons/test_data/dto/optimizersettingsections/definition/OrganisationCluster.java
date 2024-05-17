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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.optimizersettingsections.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.organisation_cc.v0")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.472033300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class OrganisationCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Identifier
     * Description: Die Kennung(en) der Organisation.
     */
    @Path("/items[at0018]")
    private List<Cluster> identifier;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Name der Einrichtung
     * Description: Der Name, der der Organisation zugeordnet ist.
     */
    @Path("/items[at0012 and name/value='Name der Einrichtung']/value|value")
    private String nameDerEinrichtungValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Telefon
     * Description: Kontaktdaten für die Organisation.
     */
    @Path("/items[at0014]")
    private List<Cluster> telefon;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Adresse
     * Description: Details zur Adresse abgestimmt mit FHIR-Ressource.
     */
    @Path("/items[openEHR-EHR-CLUSTER.address_cc.v0]")
    private List<AdresseCluster> adresse;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Teil von
     * Description: Die Organisation, zu der diese Organisation gehört.
     */
    @Path("/items[at0017]")
    private List<Cluster> teilVon;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/Kontakt
     * Description: Ansprechpartner für die Organisation für einen bestimmten Zweck.
     */
    @Path("/items[at0016]")
    private List<Cluster> kontakt;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setIdentifier(List<Cluster> identifier) {
        this.identifier = identifier;
    }

    public List<Cluster> getIdentifier() {
        return this.identifier;
    }

    public void setNameDerEinrichtungValue(String nameDerEinrichtungValue) {
        this.nameDerEinrichtungValue = nameDerEinrichtungValue;
    }

    public String getNameDerEinrichtungValue() {
        return this.nameDerEinrichtungValue;
    }

    public void setTelefon(List<Cluster> telefon) {
        this.telefon = telefon;
    }

    public List<Cluster> getTelefon() {
        return this.telefon;
    }

    public void setAdresse(List<AdresseCluster> adresse) {
        this.adresse = adresse;
    }

    public List<AdresseCluster> getAdresse() {
        return this.adresse;
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

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
