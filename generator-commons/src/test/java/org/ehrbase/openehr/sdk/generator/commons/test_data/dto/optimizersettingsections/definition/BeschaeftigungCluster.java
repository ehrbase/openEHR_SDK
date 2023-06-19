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
@Archetype("openEHR-EHR-CLUSTER.occupation_record.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.470033300+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class BeschaeftigungCluster implements LocatableEntity {
    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Berufsbezeichnung/Rolle
     * Description: Die Hauptberufsbezeichnung oder die Rolle der Person.
     */
    @Path("/items[at0005]/value|value")
    private String berufsbezeichnungRolleValue;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Organisation
     * Description: Mit FHIR-Ressource abgestimmt Details zur Organisation.
     */
    @Path("/items[openEHR-EHR-CLUSTER.organisation_cc.v0]")
    private List<OrganisationCluster> organisation;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/Zusätzliche Angaben
     * Description: Weitere Angaben zu einer Beschäftigung.
     * Comment: Zum Beispiel: Standort und Bedingungen am Arbeitsplatz oder Kampfzonenerfahrung.
     */
    @Path("/items[at0018]")
    private List<Cluster> zusaetzlicheAngaben;

    /**
     * Path: Bericht/Allgemeine Angaben/Gesundheitsbezogener Beruf/Beschäftigung/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

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

    public void setZusaetzlicheAngaben(List<Cluster> zusaetzlicheAngaben) {
        this.zusaetzlicheAngaben = zusaetzlicheAngaben;
    }

    public List<Cluster> getZusaetzlicheAngaben() {
        return this.zusaetzlicheAngaben;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
