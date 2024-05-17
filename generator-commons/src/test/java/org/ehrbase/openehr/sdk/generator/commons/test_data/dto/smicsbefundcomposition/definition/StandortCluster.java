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
package org.ehrbase.openehr.sdk.generator.commons.test_data.dto.smicsbefundcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Archetype;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Entity;
import org.ehrbase.openehr.sdk.generator.commons.annotations.Path;
import org.ehrbase.openehr.sdk.generator.commons.interfaces.LocatableEntity;

@Entity
@Archetype("openEHR-EHR-CLUSTER.location.v1")
@Generated(
        value = "org.ehrbase.client.classgenerator.ClassGenerator",
        date = "2021-02-16T12:58:09.819436200+01:00",
        comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class StandortCluster implements LocatableEntity {
    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standorttyp
     * Description: Kategorisierung des Standorttyps, z.B. Klinik, zu Hause.
     */
    @Path("/items[at0040]/value|value")
    private String standorttypValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standortbeschreibung
     * Description: Das Feld enthält die Freitextbeschreibung des Standorts, z.B. Throax-, Herz- und Gefäßchirurgie.
     */
    @Path("/items[at0046]/value|value")
    private String standortbeschreibungValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Standortschlüssel
     * Description: Kodierung des Standortes, z.B. der Fachabteilungsschlüssel (z. B. 2000 Thoraxchirurgie).
     */
    @Path("/items[at0048]/value|value")
    private String standortschlusselValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Station
     * Description: Eine Station ist Teil einer medizinischen Einrichtung, die Räume und andere Arten von Orten enthalten kann.
     */
    @Path("/items[at0027]/value|value")
    private String stationValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Zimmer
     * Description: Ein Ort, der als Zimmer deklariert wurde. Bei einigen Standorten kann das Zimmer im Flur liegen zB: Station XYZ Flur 2. Hierbei liegt der Bettstellplatz 2 auf dem Flur der jeweiligen Station.
     */
    @Path("/items[at0029]/value|value")
    private String zimmerValue;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/Details
     * Description: Für die Erfassung weiterer Angaben über das Bett oder der Adresse. Verwenden Sie dazu den Archetyp CLUSTER.device oder CLUSTER.address.
     */
    @Path("/items[at0047]")
    private List<Cluster> details;

    /**
     * Path: SmICS Befund/SmICS-Ergebnis/Jedes Ereignis/Standort/feeder_audit
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

    public void setStationValue(String stationValue) {
        this.stationValue = stationValue;
    }

    public String getStationValue() {
        return this.stationValue;
    }

    public void setZimmerValue(String zimmerValue) {
        this.zimmerValue = zimmerValue;
    }

    public String getZimmerValue() {
        return this.zimmerValue;
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
