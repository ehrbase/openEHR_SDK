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
package org.ehrbase.client.classgenerator.examples.virologischerbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2020-12-10T13:06:13.773035600+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class EmpfangerstandortCluster implements LocatableEntity {
    /**
     * Path: Virologischer Befund/Befund/Empfängerstandort/Standorttyp
     * Description: Kategorisierung des Standorttyps, z.B. Klinik, zu Hause.
     */
    @Path("/items[at0040]/value|value")
    private String standorttypValue;

    /**
     * Path: Virologischer Befund/Befund/Empfängerstandort/Standortbeschreibung
     * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
     */
    @Path("/items[at0046]/value|value")
    private String standortbeschreibungValue;

    /**
     * Path: Virologischer Befund/Befund/Empfängerstandort/Standortschlüssel
     * Description: Kodierung des Standortes, z.B. der Fachabteilungsschlüssel (z. B. 2000 Thoraxchirurgie).
     */
    @Path("/items[at0048]/value|value")
    private String standortschlusselValue;

    /**
     * Path: Virologischer Befund/Befund/Empfängerstandort/Details
     * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
     */
    @Path("/items[at0047]")
    private List<Cluster> details;

    /**
     * Path: Virologischer Befund/Befund/Empfängerstandort/feeder_audit
     */
    @Path("/feeder_audit")
    private FeederAudit feederAudit;

    public void setStandorttypValue(String standorttypValue) {
        this.standorttypValue = standorttypValue;
    }

    public String getStandorttypValue() {
        return this.standorttypValue;
    }

    public void setStandortbeschreibungValue(String standortbeschreibungValue) {
        this.standortbeschreibungValue = standortbeschreibungValue;
    }

    public String getStandortbeschreibungValue() {
        return this.standortbeschreibungValue;
    }

    public void setStandortschlusselValue(String standortschlusselValue) {
        this.standortschlusselValue = standortschlusselValue;
    }

    public String getStandortschlusselValue() {
        return this.standortschlusselValue;
    }

    public void setDetails(List<Cluster> details) {
        this.details = details;
    }

    public List<Cluster> getDetails() {
        return this.details;
    }

    public void setFeederAudit(FeederAudit feederAudit) {
        this.feederAudit = feederAudit;
    }

    public FeederAudit getFeederAudit() {
        return this.feederAudit;
    }
}
